<template>
  <div class="confirmation-wrapper d-flex justify-center">
    <v-form class="confirm-form">
      <v-text-field
        v-model="confirmationNumber"
        label="이메일 인증 번호 입력"
        required
      >
      <span
        slot="append"
      >
        {{ timerCount }}
      </span>
      </v-text-field>

    <v-btn 
      outlined
      @click="signUp"
      >
        회원가입
    </v-btn>
    </v-form>
  </div>
</template>

<script>
export default {
  name: 'EmailConfirmation',
  data(){
    return{
      timerCount: 30
    }
  },

  methods:{
    startTimer(){
      if(this.timerCount > 0){
        setTimeout(() => {
          this.timerCount -= 1
        }, 1000)
      }
    }
  },

  watch: {
    timerCount: {
      handler(count){
        if(count > 0){
          setTimeout(() => {
            this.timerCount--
          }, 1000)
        } else{
          this.$router.push({name : 'SignUp'})
        }
      }
    },

    // 즉시 동작
    immediate: true 
  },

  created(){
    this.startTimer()
  }
}
</script>

<style>
.confirm-form{
  margin-top: 100px;
  width: 50%;
}

</style>