<template>
  <div>
    <h2>Hello page</h2>
    <p>자동 배포 테스트 중입니다</p>
    <button @click="sendRequest">전송 보내기</button>
    <br>
    <div v-if="response">{{response}}</div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data: function () {
    return {
      response: null,
    }
  },
  methods: {
    sendRequest: function () {
      axios({
        method: 'get',
        url: 'http://j6b202.p.ssafy.io:8080/hello/',
      })
      .then(res => {
        console.log(res.data)
        this.response = res.data.message
      })
      .catch(err => {
        console.log(err)
        alert('이 메세지가 보인단 건 실-패라는 뜻입니다')
      })
    }
  }
 }
</script>

<style>

</style>
