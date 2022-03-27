import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token : ''
  },

  getters: {
    loginStatus(){
      const token = JSON.parse(localStorage.getItem('userInfo')).accessToken
      return !!token
    }
  },

  mutations: {
    SET_MY_TOKEN(state, token){
      state.token = token
    }

  },
  actions: {
    createUser({dispatch}, data){
      const userData = data.userData
      const signUpVue = data.this
      const url = '/api/users'
      axios.post(url, {...userData})
      .then(() => {
        dispatch('getToken', {userEmail: userData.userEmail, password: userData.password})
      })
      .then(() => {
        alert('회원가입 성공!')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
        signUpVue.$emit('close-sign-up')
      })
      .catch(()=>{
        alert('이메일 인증 실패')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
      })
    },

    getToken({commit}, inputData){
      const url = '/api/auth/login'
      axios.post(url, {...inputData})
      .then((res) => {
        localStorage.setItem('userInfo', JSON.stringify(res.data))
        commit('SET_MY_TOKEN')
      })

    }

  },
  modules: {
  }
})
