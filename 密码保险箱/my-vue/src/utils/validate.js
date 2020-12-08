/**
 * Created by jiachenpan on 16/11/18.
 */

export function isvalidUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 大写字母*/
export function validateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validatAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}
/**
 * 检查email格式
 */
export function checkEmail(str){
  const reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
    return reg.test(str)
}
/**
 *检查用户名
* 只包含中文、英文、下划线
*/
export function checkName(str){
  const reg = /^([\u4e00-\u9fa5]|[A-Za-z0-9_])+$/
  return reg.test(str)
}
/**
 * 检查密码， 密码(长度在6~30之间，只能包含字母、数字和下划线)：
 */
export function checkPsw(str){
  const reg = /^[A-Za-z0-9_]{6,30}$/
  return reg.test(str)
}

	/**
	 * 验证身份证号码
	 * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @return 验证成功返回true，验证失败返回false
	 */
  export function checkIdCard(str) {
		const reg = /^[1-9]\\d{13,16}[a-zA-Z0-9]{1}$/
    return reg.test(str)
	}

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	 *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
	 *<p>电信的号段：133、153、180（未启用）、189</p>
	 */
  export function checkMobile(str) {
    const reg = /^(1)\d{10}$/
    return reg.test(str)
	}

