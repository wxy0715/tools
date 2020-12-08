import request from '@/utils/request'

export function login(obj) {
  return request({
    url: '/api/login',
    method: 'POST',
    type:"JSON",
    data: obj
  })
}

//发送登录验证码
export function sendLoginCode(obj) {
  return request({
    url: '/api/sendLoginCode?phone='+obj,
    method: 'GET',
  })
}
//发送注册验证码
export function sendRegisterCode(obj) {
  return request({
    url: '/api/sendRegisterCode?phone='+obj,
    method: 'GET',
  })
}

//注册
export function register(obj) {
  return request({
    url: '/api/register',
    method: 'POST',
    type:"JSON",
    data: obj
  })
}

//找回
export function lost(obj) {
  return request({
    url: '/api/lost',
    method: 'POST',
    type:"JSON",
    data: obj
  })
}
