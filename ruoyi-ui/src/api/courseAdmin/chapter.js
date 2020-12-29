import request from '@/utils/request'

// 查询大章列表
export function listChapter(query) {
  return request({
    url: '/courseAdmin/chapter/list',
    method: 'get',
    params: query
  })
}

// 查询大章详细
export function getChapter(id) {
  return request({
    url: '/courseAdmin/chapter/' + id,
    method: 'get'
  })
}

// 新增大章
export function addChapter(data) {
  return request({
    url: '/courseAdmin/chapter',
    method: 'post',
    data: data
  })
}

// 修改大章
export function updateChapter(data) {
  return request({
    url: '/courseAdmin/chapter',
    method: 'put',
    data: data
  })
}

// 删除大章
export function delChapter(id) {
  return request({
    url: '/courseAdmin/chapter/' + id,
    method: 'delete'
  })
}

// 查询所有大章
export function getAllChapter(id) {
  return request({
    url: '/courseAdmin/chapter/selectAll',
    method: 'get'
  })
}
