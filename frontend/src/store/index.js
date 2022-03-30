import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token : '',
    myContents: [],

    preferencePosters: [],
    historyPosters : []

  },

  getters: {
    loginStatus(state){
      return !!state.token
    }
  },

  mutations: {
    SET_MY_TOKEN(state, token){
      state.token = token
    },

    SET_MY_PREFERENCE(state, posters){
      state.preferencePosters = posters
    },

    SET_MY_HISTORY(state, history){
      state.historyPosters = history
    }
  },

  actions: {
    // 이메일 체크
    checkMultiEmail({state}, inputEmail){
      state
      const url = `/api/users/${inputEmail}`
      axios.get(url)
      .then((res) => {
        if(res.data.emailCheck){
          alert('이메일 중복검사 통과')
          return true
        } else{
          alert('이메일 중복검사 실패')
          return false
        }
      })
      .catch(() => {
        alert('이메일 중복검사 실패')
        return false
      })
    },

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

    setToken({commit}, token){
      commit('SET_MY_TOKEN', token)
    },

    getToken({commit}, inputData){
      const url = '/api/auth/login'
      axios.post(url, {...inputData})
      .then((res) => {
        localStorage.setItem('accessToken', res.data.accessToken)
        commit('SET_MY_TOKEN', res.data.accessToken)
      })

    },

    getMyPreference({commit, state}){
      const url = '/api/users/profile/preference'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        commit('SET_MY_PREFERENCE', res.data.items)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    getMyHistory({commit, state}){
        const url = '/api/users/profile/watchHistory'
        axios.get(url, {
          headers: {
            Authorization : `Bearer ${state.token}`
          }
        })
        .then((res) => {
          commit('SET_MY_HISTORY', res.data.items)
        })
    }



  },
  modules: {
  }
})
