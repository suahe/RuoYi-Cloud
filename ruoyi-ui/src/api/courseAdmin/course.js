import request from '@/utils/request'

// 查询课程管理列表
export function listCourse(query) {
  return request({
    url: '/courseAdmin/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程管理详细
export function getCourse(id) {
  return request({
    url: '/courseAdmin/course/' + id,
    method: 'get'
  })
}

// 新增课程管理
export function addCourse(data) {
  return request({
    url: '/courseAdmin/course',
    method: 'post',
    data: data
  })
}

// 修改课程管理
export function updateCourse(data) {
  return request({
    url: '/courseAdmin/course',
    method: 'put',
    data: data
  })
}

// 删除课程管理
export function delCourse(id) {
  return request({
    url: '/courseAdmin/course/' + id,
    method: 'delete'
  })
}

// 查询所有课程
export function getAllCourse(categoryId) {
  if(!categoryId){
    categoryId = "";
  }
  return request({
    url: '/courseAdmin/course/selectAll?categoryId='+categoryId,
    method: 'get'
  })
}

// 查询所有课程
export function addCourseCategory(data) {
  return request({
    url: '/courseAdmin/course/addCourseCategory',
    method: 'post',
    data: data
  })
}
