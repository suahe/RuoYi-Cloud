<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['courceAdmin:category:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['courceAdmin:category:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <div style="float: left;width: 49%;">
      <el-table id="category-table"
        v-loading="loading"
        :data="categoryList"
        row-key="id"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        @row-click="rowClick"
        highlight-current-row
      >
        <el-table-column prop="name" label="分类名称" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="sort" label="排序"></el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="text"
                       icon="el-icon-edit"
                       @click="handleUpdate(scope.row)"
                       v-hasPermi="['system:menu:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-plus"
              @click="handleAdd(scope.row)"
              v-hasPermi="['system:menu:add']"
            >新增</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:menu:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="float: right;width: 49%;">
      <el-table v-loading="loading" :data="courseList" ref="courseTable" @select-all="handleSelectCourse" @select="handleSelectCourse" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="id" align="center" prop="id" />
        <el-table-column label="名称" align="center" prop="name" />
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getCourseList"
      />
    </div>

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上级分类">
          <treeselect
            v-model="form.parent"
            :options="categoryOptions"
            :normalizer="normalizer"
            :show-count="true"
            placeholder="选择上级分类"
          />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="顺序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入顺序" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCategory, getCategory, delCategory, addCategory, updateCategory } from "@/api/courseAdmin/category";
import { listCourse, getAllCourse ,addCourseCategory } from "@/api/courseAdmin/course";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";

export default {
  name: "Category",
  components: {Treeselect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //当前分类id
      categoryId: null,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 分类表格数据
      categoryList: [],
      // 课程管理表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //分类下拉选择
      categoryOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getCourseList();
  },
  methods: {
    /** 查询分类列表 */
    getList() {
      this.loading = true;
      listCategory().then(response => {
        this.categoryList = this.handleTree(response.rows, "id","parent");
        this.loading = false;
      });
    },
    /** 查询课程管理列表 */
    getCourseList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.handleSelectedCourse();
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 转换分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    /** 查询分类下拉树结构 */
    getTreeselect() {
      listCategory().then(response => {
        this.categoryOptions = [];
        const category = { id: 0, name: '主类目', children: [] };
        category.children = this.handleTree(response.rows, "id","parent");
        this.categoryOptions.push(category);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        parent: null,
        name: null,
        sort: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getCourseList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //选中数据
    handleSelectCourse(courses) {
      console.log(courses);
      let courseIds = [];
      courses.forEach(item =>{
        courseIds.push(item.id);
      })
      console.log("categoryId="+this.categoryId+",courseIds="+courseIds)
      let params = {categoryId:this.categoryId,courseIds:courseIds}
      addCourseCategory(params);
    },
    //多选框选中数据
    handleSelectionChange(selection){
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parent = row.id;
      } else {
        this.form.parent = 0;
      }
      this.open = true;
      this.title = "添加分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const id = row.id || this.ids
      getCategory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCategory(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategory(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除分类编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCategory(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('courceAdmin/category/export', {
        ...this.queryParams
      }, `courceAdmin_category.xlsx`)
    },
    /**选中树形表格行加载课程列表 **/
    rowClick (row) {
      this.categoryId = row.id;
      this.handleSelectedCourse();
    },
    handleSelectedCourse(){
      this.clearSelection();
      getAllCourse(this.categoryId).then(response=>{
        if(response&&response.length>0){
          for(let i=0; i<this.courseList.length; i++) {
            response.forEach(item => {
              if(this.courseList[i].id==item.id){
                this.$refs.courseTable.toggleRowSelection(this.courseList[i]);
              }
            });
          }
        }else {
          this.clearSelection();
        }
      })
    },
    //清除所有数据
    clearSelection(){
      this.$refs.courseTable.clearSelection();
    }
  }
};
</script>
<style>
  /* 用来设置当前页面element全局table 选中某行时的背景色*/
  .el-table__body tr.current-row>td{
    background-color: #1ab394 !important;
  }
</style>
