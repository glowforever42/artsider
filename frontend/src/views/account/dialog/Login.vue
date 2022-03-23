<template>
  <v-dialog
  v-model="loginDialog"
  dark
  max-width="500px"
  @close:outside="() => { $emit('close-login') }"
  >
  <v-card>
    <v-card-title>
      <span class="text-h2"> 로그인 </span>
    </v-card-title>

    <v-container>

    </v-container>
  </v-card>

  </v-dialog>
</template>

<script>
export default { 
  name:'Login',
  props:{
    loginDialog : Boolean
  }

}
</script>

<style>

</style>