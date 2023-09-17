import request from '@/utils/request'

//
export function nodeManager(query) {
  return request({
    url: '/ceshi/manager/node',
    method: 'get',
    params: query
  })
}
//

// 查询测试列表
export function listManager(query) {
  return request({
    url: '/ceshi/manager/list',
    method: 'get',
    params: query
  })
}

// 查询测试详细
export function getManager(id) {
  return request({
    url: '/ceshi/manager/' + id,
    method: 'get'
  })
}

// 新增测试
export function addManager(data) {
  return request({
    url: '/ceshi/manager',
    method: 'post',
    data: data
  })
}

// 修改测试
export function updateManager(data) {
  return request({
    url: '/ceshi/manager',
    method: 'put',
    data: data
  })
}

// 删除测试
export function delManager(id) {
  return request({
    url: '/ceshi/manager/' + id,
    method: 'delete'
  })
}
