import request from '@/utils/request'

// 查询小节列表
export function listSection(query) {
  return request({
    url: '/courseAdmin/section/list',
    method: 'get',
    params: query
  })
}

// 查询小节详细
export function getSection(id) {
  return request({
    url: '/courseAdmin/section/' + id,
    method: 'get'
  })
}

// 新增小节
export function addSection(data) {
  return request({
    url: '/courseAdmin/section',
    method: 'post',
    data: data
  })
}

// 修改小节
export function updateSection(data) {
  return request({
    url: '/courseAdmin/section',
    method: 'put',
    data: data
  })
}

// 删除小节
export function delSection(id) {
  return request({
    url: '/courseAdmin/section/' + id,
    method: 'delete'
  })
}
