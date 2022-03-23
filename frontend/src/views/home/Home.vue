<template>
  <div class="home">
    <HomeCarousel />
  </div>
</template>

<script>
import HomeCarousel from '@/views/home/components/HomeCarousel'

export default {
  name: 'Home',
  components: {
    HomeCarousel
  }
}
</script>
<style>
.home{
  width: 100%;
  height: 100%;
}

</style>
