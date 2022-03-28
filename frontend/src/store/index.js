import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token : '',
    myContents: [],
  },
  mutations: {
    SET_MY_CONTENTS(state, posters){
      state.myContents = posters
    }
  },
  actions: {
    getMyContents({commit}, token){
      const url = '/api/users/profile/preference'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${token}`
        }
      })
      .then((res) => {
        commit('SET_MY_CONTENTS', res.data.items)
      })
      .catch(() => {

      })
    }
  },
  modules: {
  }
})
