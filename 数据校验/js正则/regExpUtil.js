var RegExpUtil = (function() {
    function RegExpUtil(){}

    /**
     * 检查email格式
     * @param str
     */
    RegExpUtil.prototype.checkEmail=function(str){
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
        return this.RegCheck(str,reg);
    }
    /**
     *检查用户名
     * 只包含中文、英文、下划线
     */
    RegExpUtil.prototype.checkName=function(str){
        var reg = new RegExp("^[一-龥A-Za-z0-9_]+$"); //正则表达式
        return this.RegCheck(str,reg);
    }
    /**
     * 检查密码， 密码(长度在6~30之间，只能包含字母、数字和下划线)：
     */
    RegExpUtil.prototype.checkPsw=function(str){
        var reg = new RegExp("^[A-Za-z0-9_]{6,30}$"); //正则表达式
        return this.RegCheck(str,reg);
    }
    RegExpUtil.prototype.RegCheck=function(str,reg){
        if(str === ""){ //输入不能为空
            console.log("输入不能为空!");
            return false;
        }else if(!reg.test(str)){ //正则验证不通过，格式不对
            console.log("验证不通过!");
            return false;
        }else{
            console.log("通过！");
            return true;
        }
    }

    return RegExpUtil;
})();
//调用
var regExpUtil = new RegExpUtil();
if (regExpUtil.checkEmail(emailValue)) {
    alert("邮箱格式正确");
}else {
    alert("邮箱格式错误");
}