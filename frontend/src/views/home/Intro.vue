<template>
  <v-parallax
    dark
    src="../../assets/intro3.png"
    style="height:100vh;"
  >
    <v-row
      align="center"
      justify="center"
    >
      <v-col
        class="text-center flex-column d-flex justify-center align-center"
        cols="12"
      >
        <v-img
        src="../../assets/123.svg"
        style="width:700px; margin-bottom:40px;"
        >

        </v-img>
        <h1 class="text-h3" style="font-weight: 800; margin-bottom:20px;">
          아트사이더에 오신걸 환영합니다.
        </h1>
      <v-btn
        color="red"
        dark
        @click="() => { loginDialog = true }"
      >
        로그인 하러 가기
      </v-btn>
      <br>
      <hr style="width:400px" class="mx-auto">
      <br>
        <p class="d-flex justify-center align-center sub-heading" style="font-size:16px;">
          회원이 아니신가요 ?
          <span
            text
            class="font-weight-bold"
            style="font-size:16px; color:pink; margin-left: 30px; cursor:pointer;"
            @click="() => { signUpDialog = true }"        
          >가입하기 </span>
        </p>
      </v-col>
    </v-row>

    <Login 
      :open="loginDialog"
      @close-login="() => { loginDialog = false }" 
    />

    <SignUp
      :open="signUpDialog"
      @close-sign-up="() => { signUpDialog = false }"
    />

    <PosterListUp 
      :open="listDialog"
      :posters="initPosters"
      @close-list="() => {listDialog = false}"
    />
  
  </v-parallax>
</template>

<script>
import Login from '@/views/account/dialog/Login'
import SignUp from '@/views/account/dialog/SignUp'
import PosterListUp from '@/views/account/dialog/PosterListUp'

export default {
  name: 'Intro',
  components: {
    Login,
    SignUp,
    PosterListUp
  },

  data(){
    return{
      loginDialog: false,
      signUpDialog: false,
      listDialog: false,

      initPosters : [],
    }
  },

   beforeRouteLeave(to, from) {
    if(to.name === 'Home' && this.$store.state.userInfo.preferTag.length === 0){
      // this.$store.dispatch('deleteToken')
      this.listDialog = true
      // this.initPosters = [
      //   {
      //     id: 316,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/21/21008475_p.gif",
      //     showName: "뮤지컬 〈빨래〉",
      //     tags : ['아이', '미친연기력', '훌륭해요']
      //   },
      //   {
      //     id: 673,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/18/18010195_p.gif",
      //     showName: "연극 핫식스",
      //     tags: ['좋아요', '믿고보는 배우', '대박']
      //   },
      //   {
      //     id: 73,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/21/21013331_p.gif",
      //     showName: "어린이 가족 뮤지컬 〈피터팬〉",
      //     tags: ['좋아요', '꿀잼', '웃음주의']
      //   },
      //           {
      //     id: 316,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/21/21008475_p.gif",
      //     showName: "뮤지컬 〈빨래〉",
      //     tags : ['아이', '미친연기력', '훌륭해요']
      //   },
      //   {
      //     id: 673,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/18/18010195_p.gif",
      //     showName: "연극 핫식스",
      //     tags: ['좋아요', '믿고보는 배우', '대박']
      //   },
      //   {
      //     id: 73,
      //     posterPath: "//ticketimage.interpark.com/Play/image/large/21/21013331_p.gif",
      //     showName: "어린이 가족 뮤지컬 〈피터팬〉",
      //     tags: ['좋아요', '꿀잼', '웃음주의']
      //   }
      // ]
      this.$store.dispatch('getInitPoster')
      .then((res) => {
        this.listDialog = true
        this.initPosters = res.data.items.map((elem) => {
          const temp = {
              ...elem, 
              tags : res.data.tagsList[elem.id].map((tag) =>{
                return tag.tag
            })
          }
          return temp
        })

      })
      .then(() => {
        console.log('초기 공연 목록', this.initPosters)
      })
      return false
    } else{
      from
      return true
    }
  }
}
</script>

<style>
.sub-heading span:hover {
  background-color: rgba(255, 192, 203, 0.2);
}
</style>