import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    wxytoken: '',
    userInfo: JSON.parse(localStorage.getItem("userInfo"))
  },
  mutations: {
    // set
    SET_TOKEN: (state, wxytoken) => {
      state.wxytoken = wxytoken
      sessionStorage.setItem("wxytoken", wxytoken)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      localStorage.setItem("userInfo", JSON.stringify(userInfo))
    },
    REMOVE_INFO: (state) => {
      state.wxytoken = ''
      state.userInfo = {}
      sessionStorage.setItem("wxytoken", '')
      localStorage.setItem("userInfo", JSON.stringify(''))
    }

  },
  getters: {
    getUser: state => state.userInfo,
    wxytoken: state => state.wxytoken
  },
  actions: {
  },
  modules: {
  }
})
