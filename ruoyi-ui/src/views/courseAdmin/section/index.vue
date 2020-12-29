<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费" prop="charge">
        <el-select v-model="queryParams.charge" placeholder="请选择收费" clearable size="small">
          <el-option
            v-for="dict in chargeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['courseAdmin:section:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['courseAdmin:section:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['courseAdmin:section:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['courseAdmin:section:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="视频" align="center" prop="video" />
      <el-table-column label="时长(秒)" align="center" prop="time" />
      <el-table-column label="收费" align="center" prop="charge" :formatter="chargeFormat" />
      <el-table-column label="顺序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['courseAdmin:section:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['courseAdmin:section:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改小节对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="标题" prop="title">
            <el-input v-model="form.title" placeholder="请输入标题" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="视频" prop="video">
              <UploadVideo v-model="form.video"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="课程名称" prop="courseId">
            <el-select v-model="form.courseId" placeholder="请选择课程名称">
              <el-option
                v-for="course in courseList"
                :key="course.id"
                :label="course.name"
                :value="course.id"
              ></el-option>
            </el-select>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="大章名称" prop="chapterId">
            <el-select v-model="form.chapterId" placeholder="请选择课程名称">
              <el-option
                v-for="chapter in chapterList"
                :key="chapter.id"
                :label="chapter.name"
                :value="chapter.id"
              ></el-option>
            </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="时长(秒)" prop="time">
            <el-input-number v-model="form.time" placeholder="请输入时长(秒)" controls-position="right" :min="0"/>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收费" prop="charge">
            <el-select v-model="form.charge" placeholder="请选择收费">
              <el-option
                v-for="dict in chargeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="顺序" prop="sort">
            <el-input-number v-model="form.sort" placeholder="请输入顺序" controls-position="right" :min="0"/>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="vod" prop="vod">
            <el-input v-model="form.vod" placeholder="请输入vod" />
          </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSection, getSection, delSection, addSection, updateSection } from "@/api/courseAdmin/section";
import {getAllCourse} from "@/api/courseAdmin/course";
import {getAllChapter} from "@/api/courseAdmin/chapter";
import UploadVideo from '@/components/UploadVideo';

export default {
  name: "Section",
  components: {UploadVideo,},
  data() {
    return {
      //课程集合
      courseList:[],
      //大章集合
      chapterList:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 小节表格数据
      sectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 收费|字典
      chargeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        charge: null,
        chapterId:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        video: [
          { required: true, message: "视频不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "时长(单位秒)不能为空", trigger: "blur" }
        ],
        charge: [
          { required: true, message: "收费|不能为空", trigger: "change" }
        ],
        sort: [
          { required: true, message: "顺序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    const chapterId = this.$route.query.chapterId;
    if(chapterId!=null){
      this.queryParams.chapterId = chapterId;
    }
    this.getList();
    this.getAllCourse();
    this.getAllChapter();
    this.getDicts("course_charge_type").then(response => {
      this.chargeOptions = response.data;
    });
  },
  methods: {
    /** 查询小节列表 */
    getList() {
      this.loading = true;
      listSection(this.queryParams).then(response => {
        this.sectionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /**查询所有课程**/
    getAllCourse(){
      getAllCourse().then(response=>{
        this.courseList = response;
      })
    },
    /**查询所有课程**/
    getAllChapter(){
      getAllChapter().then(response=>{
        this.chapterList = response;
      })
    },
    // 收费|字典翻译
    chargeFormat(row, column) {
      return this.selectDictLabel(this.chargeOptions, row.charge);
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
        title: null,
        courseId: null,
        chapterId: null,
        video: null,
        time: null,
        charge: null,
        sort: null,
        createdAt: null,
        updatedAt: null,
        vod: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加小节";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSection(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改小节";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSection(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSection(this.form).then(response => {
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
      this.$confirm('是否确认删除小节编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSection(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('courseAdmin/section/export', {
        ...this.queryParams
      }, `courseAdmin_section.xlsx`)
    }
  }
};
</script>
