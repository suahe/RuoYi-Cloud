import request from '@/utils/request'

// 查询讲师列表
export function listTeacher(query) {
  return request({
    url: '/courseAdmin/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询讲师详细
export function getTeacher(id) {
  return request({
    url: '/courseAdmin/teacher/' + id,
    method: 'get'
  })
}

// 新增讲师
export function addTeacher(data) {
  return request({
    url: '/courseAdmin/teacher',
    method: 'post',
    data: data
  })
}

// 修改讲师
export function updateTeacher(data) {
  return request({
    url: '/courseAdmin/teacher',
    method: 'put',
    data: data
  })
}

// 删除讲师
export function delTeacher(id) {
  return request({
    url: '/courseAdmin/teacher/' + id,
    method: 'delete'
  })
}
// 查询所有讲师
export function getAllTeacher() {
  return request({
    url: '/courseAdmin/teacher/selectAll',
    method: 'get'
  })
}
