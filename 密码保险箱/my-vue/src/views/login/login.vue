<template>
<div class="login_container">
  <canvas id="canvas"></canvas>
  <!-- 登录表单区域 -->
  <div class="login_box" v-show="type==='1'">
    <el-form ref="loginFormRef" class="login_form" label-width="0px" :model="loginForm" :rules="loginFormRules">
      <el-select v-model="loginForm.value" placeholder="请选择">
        <el-option v-for="item in loginForm.loginMethods" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <!-- 普通登录 -->
      <div v-show="loginForm.value==='1'">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名或手机号" v-model="loginForm.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input placeholder="密码" v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <!-- 验证码 -->
        <el-form-item prop="code" class="codeStyle">
          <el-input placeholder="验证码" v-model="loginForm.code"></el-input>
          <canvas id="canvas_code" @click="redraw()"></canvas>
        </el-form-item>

      </div>

      <!-- 手机号验证码登录 -->
      <div v-show="loginForm.value==='2'">
        <!-- 手机号码 -->
        <el-form-item prop="phone">
          <el-input placeholder="请输入手机号" v-model="loginForm.phone" class="phone"></el-input>
          <el-button type="warning" class="Vcode3" @click="sendCode3()">发送验证码</el-button>
        </el-form-item>
        <!-- 验证码 -->
        <el-form-item prop="phoneCode">
          <el-input placeholder="请输入验证码,5分钟内有效" v-model="loginForm.phoneCode"></el-input>
        </el-form-item>
      </div>

      <!-- 按钮区域 -->
      <el-form-item class="btns">
        <el-button type="success" @click="login">登录</el-button>
        <el-button type="warning" plain @click="registerType()">注册</el-button>
        <el-button type="danger" plain @click="lostType()">忘记密码</el-button>
        <el-button type="info" @click="resetLoginForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- 注册表单区域 -->
  <div class="register_box" v-show="type==='2'">
    <el-form ref="registerFormRef" class="login_form" label-width="0px" :model="registerForm" :rules="registerFormRules">
      <!-- 手机号 -->
      <el-form-item prop="phone">
        <el-input placeholder="请输入手机号" v-model="registerForm.phone" style="width: 293px;"></el-input>
        <el-button type="warning" @click="sendCode2()" class="Vcode2">发送验证码</el-button>
      </el-form-item>
      <!-- 验证码 -->
      <el-form-item prop="phoneCode">
        <el-input placeholder="请输入验证码,5分钟内有效" v-model="registerForm.phoneCode"></el-input>
      </el-form-item>
      <!-- 用户名 -->
      <el-form-item prop="username">
        <el-input placeholder="请输入用户名" v-model="registerForm.username"></el-input>
      </el-form-item>
      <!-- 密码 -->
      <el-form-item prop="password">
        <el-input placeholder="请输入密码" v-model="registerForm.password" type="password"></el-input>
      </el-form-item>
      <!-- 验证密码 -->
      <el-form-item prop="checkPassword">
        <el-input placeholder="请确认密码" v-model="registerForm.checkPassword" type="password"></el-input>
      </el-form-item>

      <!-- 按钮区域 -->
      <el-form-item class="btns">
        <el-button type="success" @click="loginType()">返回登录</el-button>
        <el-button type="warning" plain @click="register">确定注册</el-button>
        <el-button type="info" @click="resetRegisterForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- 忘记密码区域 -->
  <div class="lost_box" v-show="type==='3'">
    <el-form ref="lostFormRef" class="login_form" label-width="0px" :model="lostForm" :rules="lostFormRules">
      <!-- 手机号码 -->
      <el-form-item prop="phone">
        <el-input placeholder="请输入手机号" v-model="lostForm.phone" class="phone"></el-input>
        <el-button type="warning" @click="sendCode1()" class="Vcode1">发送验证码</el-button>
      </el-form-item>
      <!-- 验证码 -->
      <el-form-item prop="phoneCode">
        <el-input placeholder="请输入验证码,5分钟内有效" v-model="lostForm.phoneCode"></el-input>
      </el-form-item>
      <!-- 按钮区域 -->
      <el-form-item class="btns">
        <el-button type="success" @click="loginType()">返回登录</el-button>
        <el-button type="warning" plain @click="lost">确定找回</el-button>
        <el-button type="info" @click="resetLostForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import scripts from '@/assets/login/script.js' //背景图
import {
  getToken,
  setToken,
  removeToken
} from '@/utils/auth'
import {
  draw
} from '@/static/js/code.js'
import {
  checkName,
  checkPsw,
  checkMobile
} from '@/utils/validate.js'
import {
  login,
  sendLoginCode,
  register,
  sendRegisterCode,
  lost
} from '@/api/login/login.js'
export default {
  components: {
    Name: 'login'
  },
  mixins: [scripts],
  data() {
    //-----------------------------登录----------------------------
    //登录密码
    var loginValidatePass = (rule, value, callback) => {
      if (this.loginForm.value === '1') {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else if (!checkPsw(value)) {
          callback(new Error('密码格式不正确'));
        } else {
          callback();
        }
      }
      callback();
    };
    //登录用户名或手机号
    var loginValidateUsername = (rule, value, callback) => {
      if (this.loginForm.value === '1') {
        if (value === '') {
          callback(new Error('请输入用户名或手机号'));
        } else if (!checkName(value) && !checkMobile(value)) {
          callback("用户名或手机号格式不正确");
        } else {
          callback();
        }
      }
      callback();
    };
    //登录手机号
    var loginValidatePhone = (rule, value, callback) => {
      if (this.loginForm.value === '2') {
        if (value === '') {
          callback(new Error('请输入手机号'));
        } else if (!checkMobile(value)) {
          callback("手机号格式不正确");
        } else {
          callback();
        }
      }
      callback();
    };
    //登录验证码
    var loginValidatePhoneCode = (rule, value, callback) => {
      if (this.loginForm.value === '2') {
        if (value === '') {
          callback(new Error('请输入验证码'));
        } else {
          callback();
        }
      }
      callback();
    };

    //-----------------------------注册----------------------------
    //注册手机号
    var registValidatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号'));
      } else if (!checkMobile(value)) {
        callback("手机号格式不正确");
      } else {
        callback();
      }
    };
    //注册用户名
    var registValidateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else if (!checkName(value)) {
        callback("用户名格式不正确");
      } else {
        callback();
      }
    };
    //注册密码
    var registValidatePwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (!checkPsw(value)) {
        callback(new Error('密码格式不正确'));
      } else {
        if (this.registerForm.checkPassword !== '') {
          this.$refs.registerFormRef.validateField('checkPassword');
        }
        callback();
      }
    };
    //注册验证密码
    var registValidatePwd1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    //注册验证码
    var registValidatePhoneCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        callback();
      }
    };

    //-----------------------------找回密码----------------------------
    //验证码
    var lostValidatePhoneCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        callback();
      }
    }
    //手机号
    var lostValidatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号'));
      } else if (!checkMobile(value)) {
        callback("手机号格式不正确");
      } else {
        callback();
      }
    }

    return {
      code: [], //图形验证码
      type: '1', //1-登录,2-注册,3-忘记密码
      // 这是登录表单的数据绑定对象
      loginForm: {
        username: '',
        password: '',
        phone: '',
        code: '',
        phoneCode: '',
        value: '1', //1-普通登录,2-手机号验证码登录
        loginMethods: [{
          value: '1',
          label: '普通登录'
        }, {
          value: '2',
          label: '手机验证码登录'
        }],
      },
      // 这是表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [{
          validator: loginValidateUsername,
          trigger: 'blur'
        }],
        // 验证密码是否合法
        password: [{
          validator: loginValidatePass,
          trigger: 'blur'
        }],
        phone: [{
          validator: loginValidatePhone,
          trigger: 'blur'
        }],
        phoneCode: [{
          validator: loginValidatePhoneCode,
          trigger: 'blur'
        }]
      },
      registerForm: {
        phone: '',
        username: '',
        password: '',
        checkPassword: '',
        phoneCode: ''
      },
      registerFormRules: {
        // 验证用户名是否合法
        username: [{
          validator: registValidateUsername,
          trigger: 'blur'
        }],
        // 验证密码是否合法
        password: [{
          validator: registValidatePwd,
          trigger: 'blur'
        }],
        checkPassword: [{
          validator: registValidatePwd1,
          trigger: 'blur'
        }],
        phone: [{
          validator: registValidatePhone,
          trigger: 'blur'
        }],
        phoneCode: [{
          validator: registValidatePhoneCode,
          trigger: 'blur'
        }]
      },
      lostForm: {
        phone: '',
        phoneCode: '',
      },
      lostFormRules: {
        phone: [{
          validator: lostValidatePhone,
          trigger: 'blur'
        }],
        phoneCode: [{
          validator: lostValidatePhoneCode,
          trigger: 'blur'
        }]
      }
    }
  },
  mounted() {
    draw(this.code)
  },
  watch: {
    //监听下拉框判断是不是普通登录
    'loginForm.value': function (val) {
      if (val === '1') {
        draw(this.code)
      }
    }
  },
  methods: {
    //清空表单
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields()
    },
    resetRegisterForm() {
      this.$refs.registerFormRef.resetFields()
    },
    resetLostForm() {
      this.$refs.lostFormRef.resetFields()
    },
    //改变登录-注册-忘记密码
    loginType() {
      this.type = '1'
    },
    registerType() {
      this.type = '2'
    },
    lostType() {
      this.type = '3'
    },
    //重新生成验证码
    redraw() {
      draw(this.code)
    },
    //发送验证码
    sendCode1() {
      const that = this
      if (!checkMobile(this.lostForm.phone)) {
        this.$message.error({
          showClose: true,
          message: "请输入正确的手机号"
        })
        return
      }
      new Promise((resolve, reject) => {
        sendLoginCode(that.lostForm.phone).then(response => {
          console.log(response)
          if (response.data.code === 2000) {
            that.$message.success({
              showClose: true,
              message: "发送验证码成功"
            })
          }
          let num = 60
          let arr = document.getElementsByClassName('Vcode1')[0];
          var codeNum = setInterval(function () {
            arr.setAttribute("disabled", true)
            arr.innerText = "还剩余" + (num - 1) + "s";
            num = num - 1
            if (num < 1) {
              arr.innerText = "发送验证码"
              arr.removeAttribute("disabled")
              clearInterval(codeNum)
            }
          }, 1000)
        }).catch(error => {
          reject(error)
        })
      })
    },
    sendCode2() {
      const that = this
      if (!checkMobile(this.registerForm.phone)) {
        this.$message.error({
          showClose: true,
          message: "请输入正确的手机号"
        })
        return
      }
      new Promise((resolve, reject) => {
        sendRegisterCode(that.registerForm.phone).then(response => {
          console.log(response)
          if (response.data.code === 2000) {
            that.$message.success({
              showClose: true,
              message: "发送验证码成功"
            })
          }
          let num = 60
          let arr = document.getElementsByClassName('Vcode2')[0];
          var codeNum = setInterval(function () {
            arr.setAttribute("disabled", true)
            arr.innerText = "还剩余" + (num - 1) + "s";
            num = num - 1
            if (num < 1) {
              arr.innerText = "发送验证码"
              arr.removeAttribute("disabled")
              clearInterval(codeNum)
            }
          }, 1000)
        }).catch(error => {
          reject(error)
        })
      })
    },
    sendCode3() {
      const that = this
      if (!checkMobile(this.loginForm.phone)) {
        this.$message.error({
          showClose: true,
          message: "请输入正确的手机号"
        })
        return
      }
      new Promise((resolve, reject) => {
        sendLoginCode(that.loginForm.phone).then(response => {
          console.log(response)
          if (response.data.code === 2000) {
            that.$message.success({
              showClose: true,
              message: "发送验证码成功"
            })
          }
          let num = 60
          let arr = document.getElementsByClassName('Vcode3')[0];
          var codeNum = setInterval(function () {
            arr.setAttribute("disabled", true)
            arr.innerText = "还剩余" + (num - 1) + "s";
            num = num - 1
            if (num < 1) {
              arr.innerText = "发送验证码"
              arr.removeAttribute("disabled")
              clearInterval(codeNum)
            }
          }, 1000)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //登录
    async login() {
      this.$refs.loginFormRef.validate(async valid => {
        let [code1, code2] = [this.loginForm.code.trim().toLowerCase(), this.code.join('').trim()]
        if (code1 !== code2 && this.loginForm.value === '1') {
          this.$message.error({
            showClose: true,
            message: "验证码不正确"
          })
          return
        }
        if (!valid) return
        const {
          data: res
        } = await login(JSON.parse(JSON.stringify(this.loginForm)))
        if (res.code === 2000) {
          this.$message.success({
            showClose: true,
            message: "登录成功"
          })
        }
        console.log("登录返回数据:" + JSON.stringify(res))
        setToken(res.data.token) //cookie
        this.$store.commit("SET_TOKEN", res.data.token) //token
        console.log("登陆成功token为:" + this.$store.getters.wxytoken)
        // 2. 通过编程式导航跳转到后台主页，路由地址是 /home
        this.$router.push('/home')
      })
    },
    //注册
    async register() {
      this.$refs.registerFormRef.validate(async valid => {
        if (!valid) return
      })
      const {
        data: res
      } = await register(JSON.parse(JSON.stringify(this.registerForm)))
      if (res.code === 2000) {
        this.$message.success({
          showClose: true,
          message: "注册成功"
        })
        this.type = '1';
        //清空表单
        resetRegisterForm()
      }
    },
    //找回密码
    async lost() {
      this.$refs.lostFormRef.validate(async valid => {
        if (!valid) return
      })
      const {
        data: res
      } = await lost(JSON.parse(JSON.stringify(this.lostForm)))
      if (res.code === 2000) {
        this.$message.success({
          showClose: true,
          duration: 30 * 1000,
          message: JSON.stringify(res.data)
        })
        this.type = '1';
        //清空表单
        resetLostForm()
      }
    },
  }
}
</script>

<style lang="scss" scoped>
html,
body,
.login_container {
  height: 100%;
  width: 100%;
  padding: 0;
  margin: 0;
  background: #74ebd5;
  /* fallback for old browsers */
  background: -webkit-linear-gradient(to bottom, #ACB6E5, #74ebd5);
  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to bottom, #ACB6E5, #74ebd5);

  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  canvas#canvas {
    background: #74ebd5;
    /* fallback for old browsers */
    background: -webkit-linear-gradient(to bottom, #ACB6E5, #74ebd5);
    /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to bottom, #ACB6E5, #74ebd5);
    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  }

  .login_box {
    width: 450px;
    height: 355px;
    background: rgba(255, 255, 255, 0.4);
    border-radius: 3px;
    position: absolute;
    left: 80%;
    top: 33%;
    transform: translate(-50%, -50%);
  }

  .register_box {
    width: 450px;
    height: 426px;
    background: rgba(255, 255, 255, 0.4);
    border-radius: 3px;
    position: absolute;
    left: 80%;
    top: 36%;
    transform: translate(-50%, -50%);
  }

  .lost_box {
    width: 450px;
    height: 217px;
    background: rgba(255, 255, 255, 0.4);
    border-radius: 3px;
    position: absolute;
    left: 80%;
    top: 34%;
    transform: translate(-50%, -50%);
  }

  .login_form {
    position: absolute;
    top: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;

    .el-select {
      width: 410px;
      height: 40px;
      margin-top: 30px;
    }

    .el-form-item {
      margin-top: 30px;
    }

    .phone {
      width: 295px;
    }

    .codeStyle {
      margin-left: -57px !important;

      .el-input {
        width: 200px;
      }
    }

    #canvas_code {
      vertical-align: bottom;
    }
  }

  .btns {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
