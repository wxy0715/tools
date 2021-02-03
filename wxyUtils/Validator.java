package wxyUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 数据校验
 */
public class Validator {
    /**
     * Description:校验实体类中的属性是否正确 <br/>
     * @author wxy
     * @param errorResult：BindingResult类型
     * @return：如果返回值为null，则代表数据完全正确；如果不为null，则返回的是一个封装错误信息map集合
     */

    //------------------常量定义
    public static final String EMAIL = "[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+";
    public static final String QQ = "[1-9][0-9]{4,14}";
    public static final String WECHAT = "[-_a-zA-Z0-9]{6,20}";
    public static final String MOBILE = "1(?:3|4|5|6|7|8|9)\\d{9}";
    //高强度密码效验
    public static final String HIGH_PWD = "(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W\\_]{8,32}";


    //------------------部门
    public static final String DEPT_NAME = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\.|\\-|\\@|\\_|[0-9]){1,32}";

    //------------------用户
    public static final String USER_USERNAME = "([A-Za-z]|\\-|\\_|\\(|\\)|[0-9]){0,32}";
    public static final String USER_REALNAME = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|\\@|\\_|[0-9]){0,32}";
    public static final String USER_USBKEY = "\\w{1,32}$/g";

    //------------------用户分组和设备分组
    public static final String GROUP_NAME = "([A-Za-z0-9]|[\\u4e00-\\u9fa5]|@|_|\\-){0,64}";

    //用户策略
    public static final String POLICY_NAME = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\.|\\-|\\@|\\_|[0-9]){1,32}";

    //------------------验证方法
    /**公共的判断*/
    public static int commonReg(String str, String pattern, Integer length) {
        return Regular(str,pattern,length);
    }

    /**描述是否超过限制长度*/
    public static boolean desc(String str){
        //描述超过限制长度 -FALSE
        return str.trim().length() <= 128;
    }

    /**匹配是否符合正则表达式pattern*/
    private static int Regular(String str,String pattern, Integer length){
        if(null == str || str.trim().length()<=0) {
            //字符串不能为空
            return 1;
        }
        if(length != null && str.trim().length() > length) {
            //超过限制长度
            return 2;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (!m.matches() && !"".equals(str)){
            //不匹配
            return 3;
        }
        //匹配的返回值
        return 4;
    }

/*    public static void main(String[] args) {
        int a = Validator.commonReg("45165315312531d5qwad13q5w1d5q3w1dq5wa3d1qw31d3qw1dqw", DEPT_NAME, 32);
        System.out.println(a);
    }*/
}
