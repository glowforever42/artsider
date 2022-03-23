<template>
  <v-dialog
  v-model="confirmDialog"
  dark
  max-width="500px"
  @click:outside="() => {$emit('close-dialog') }"
  >
    <v-card>
      <v-container>
        <v-form  
          v-if="confirmDialog"
          class="confirm-form">
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
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'EmailConfirmation',
  props:{
    userData : Object,
    confirmDialog : Boolean
  },

  data(){
    return{
      confirmationNumber : '',
      timerCount: 30,
      timer: null
    }
  },

  methods:{
    startTimer(){
      if(this.timerCount > 0){
        this.timer = setInterval(() => {
              this.timerCount -= 1
          }, 1000)
        } 
    },

    signUp(){
      this.confirmationNumber = ''
      this.$emit('close-dialog')
    }
  },

  watch: {
    confirmDialog: function(){
      if(this.confirmDialog){
        this.timerCount = 30
        this.startTimer()
      } else{
        clearInterval(this.timer)
      }
    }
  },

}
</script>

<style>


</style>