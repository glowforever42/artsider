<template>
  <v-app-bar 
  app
  scroll-target="#main"
  absolute
  flat
  inverted-scroll
  style="background-color: rgba(255,82,82,1)"
  >
  <v-toolbar-title class="text-uppercase">
    <span class="font-weight-light"> ArtSider </span>
  </v-toolbar-title>
  
  <v-spacer></v-spacer>
  <v-menu
    bottom
  >
    <template v-slot:activator="{on, attrs}">
      <v-btn
        text  
        v-bind="attrs"
        v-on="on"
      >
        공연 카테고리
      <v-icon
      right
      >
        mdi-arrow-down-drop-circle-outline
      </v-icon>
      </v-btn>
    </template>

    <v-list>
      <v-list-item
        v-for="(category, i) in categories"
        :key="i"
      >
        <v-btn text>
          <v-list-item-title> {{ category.title }} </v-list-item-title>
        </v-btn>
      </v-list-item>
    </v-list>

  </v-menu>

    <v-spacer></v-spacer>

  <v-text-field
    solo
    flat
    dense
    outlined
    rounded
    background-color="#FFF0E1"
    color="black"
  >
    <v-btn
      slot="append"
      icon
    >
      <v-icon
        color="gray"
      >
        mdi-magnify
      </v-icon>
    </v-btn>

  </v-text-field>

    <v-spacer></v-spacer>

    <v-btn text color="gray">
      <span> 공연 에티켓 </span>
    </v-btn>

    <v-btn text color="gray">
      <span>로그아웃</span>
    </v-btn>

      <v-btn text color="gray">
      <span>마이페이지</span>
    </v-btn>

  </v-app-bar>
</template>

<script>

export default {
  name: 'NavBar',
  data(){
    return{
      categories: [
        { title: "뮤지컬"},
        { title: "연극"},
        { title: "콘서트"},
        { title: "클래식/오페라/무용"},
        { title: "아동/키즈"}
      ]
    }
  },


  // mounted(){
  //   const header = document.querySelector('header')
  //   const headerHeight = header.clientHeight

  //   const navCard = document.querySelector('.nav-card')
  //   navCard.style.top = `-${headerHeight}`

  //   function scrollF(){
  //     if(document.body.scrollTop > 200 || document.documentElement.scrollTop > 200){
  //       navCard.style.top = '0'
  //     }  else{
  //       navCard.style.top = '-200px'
  //     }
  //   }

  //   window.onscroll = scrollF()

  // }
}
</script>

<style>
.v-text-field.v-text-field--enclosed .v-text-field__details{
  display: none;
}
/* 
.nav-card{
  position: fixed;
  width: 100%;
  transition: top 0.5s;
} */

</style>