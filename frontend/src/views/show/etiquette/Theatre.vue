<template>
  <div>
      <h1>공연 에티켓</h1>
<v-btn
  elevation="2"
><router-link :to="{ name: 'musical' }"  style="text-decoration: none; color: inherit;"
>뮤지컬</router-link></v-btn>
<v-btn
  elevation="2"
  color="warning"
><router-link :to="{ name: 'theatre' }"  style="text-decoration: none; color: inherit;"
>연극</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'classic' }"  style="text-decoration: none; color: inherit;"
>클래식</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'opera' }"  style="text-decoration: none; color: inherit;"
>오페라</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'ktraditionalmusic' }"  style="text-decoration: none; color: inherit;"
>국악</router-link>
</v-btn>

    <v-img
    height="500px"
    width="500px"
        src="https://img.khan.co.kr/news/2022/01/09/l_2022011001001017800084101.jpg"
    ></v-img>

<v-btn
  elevation="2"
  color="warning"
></v-btn>

<a href="">
<img src="https://cdn4.iconfinder.com/data/icons/covid-19-62/512/covid-coronavirus-20-256.png">
</a>
<a href="">
<img src="https://cdn4.iconfinder.com/data/icons/airport-and-travel-2/85/burger_hamburger_drink_soda_fast_food-256.png">
</a>
<a href="">
<img src="https://cdn1.iconfinder.com/data/icons/aami-flat-message-bubbles/64/message-46-256.png">
</a>
<a href="">
<img src="https://cdn1.iconfinder.com/data/icons/hand-gestures-color/128/hand-clap-y-256.png">
</a>
<a href="">
<img src="https://cdn0.iconfinder.com/data/icons/simple-basic-i/32/05-256.png">
</a>
<a href="">
<img src="https://cdn2.iconfinder.com/data/icons/basic-flat-icon-set/128/clock-256.png">
</a>
  </div>
  
</template>

<script>
export default {
  name: 'Classic',
  components: {
  }
}
</script>

<style>

</style>