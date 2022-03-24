<template>
  <div class="home">
    <HomeCarousel />

    <v-btn
      @click="loginDialog = true"
    >
      로그인 테스트 버튼
    </v-btn>

  
    <Login 
      :open="loginDialog"
      @close-login="() => { loginDialog = false }"
    />

  </div>
</template>

<script>
import HomeCarousel from '@/views/home/components/HomeCarousel'
import Login from '@/views/account/dialog/Login'

export default {
  name: 'Home',
  data(){
    return{
      loginDialog : false
    }
  }, 

  components: {
    HomeCarousel,
    Login
  },

  methods: {

  }
}
</script>
<style>
.home{
  width: 100%;
  height: 100%;
}

</style>
