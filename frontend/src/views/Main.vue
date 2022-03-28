<template>
<div>
  <div class=recommand-list>
  <Popular />
  </div>
  <div class=recommand-list>
  <ComingEnd />
  </div>
  <div class=recommand-list>
  <ComingSoon />
  </div>
  <div class=recommand-list>
  <PreferenceTag />
  </div>
</div>
</template>

<script>
import Swiper from 'swiper/js/swiper.esm.bundle'
import 'swiper/css/swiper.css'

import Popular from '../views/show/recommand/Popular.vue'
import ComingEnd from '../views/show/recommand/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/ComingEnd.vue'
import PreferenceTag from '../views/show/recommand/PreferenceTag.vue'


export default {
  name: 'swiperTest',
  data(){
    return{
      swiper: null,
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    PreferenceTag,
  },
  mounted(){
    this.swiper = new Swiper('.swiper', {
      slidesPerView: 7,
      slidePerGroup: 5,
      direction: 'horizontal',
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
    })
  }
}
</script>
<style>

.swiper {
  position: relative;
  width: 100%;
  height: 40%;
  
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: gray;

  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
}

.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}

.recommand-list {
  margin-bottom: 4rem;
}

</style>