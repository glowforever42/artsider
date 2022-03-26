<template>
  <div id="profile">
    <v-container>
      <div class="profile-info d-flex flex-wrap">
        <v-card
          width="300"
          height="300"
          flat
          class="d-inline-block"
        >
          <v-img
            max-width="100%"
            max-height="100%"
            :aspect-ratio="1"
            src="@/assets/profile_default.png"
          >
          </v-img>
        </v-card>
        <div class="profile-detail d-inline-block ms-5">
          <h1> 김공연 </h1>
          <div class="profile-tag-wrapper">
            <h2> 선호 태그 </h2>
            <v-chip-group
              column
            >
              <v-chip
                v-for="(tag, i) in userTags"
                :key="i"
                large
                label
              >
                <span class="font-weight-bold"> {{ tag }} </span> 
              </v-chip>
            </v-chip-group>
          </div>
        </div>
      </div>

      <div class="profile-menu" style="margin-top:50px">
        <li 
          class="li-btn mr-5 p-4" 
          :class="{'click-item': clickItem1}"
          @click="() => { clickItem1 = true, clickItem2 = false, clickItem3 = false}"
        >
          <span> 공연 보관함 </span>
        </li>
        <li 
          class="li-btn mr-5 p-4"
          :class="{'click-item': clickItem2}"
          @click="() => { clickItem1 = false, clickItem2 = true, clickItem3 = false}"  
        >
          <span> 리뷰 보관함 </span>
        </li>
        <li 
          class="li-btn mr-5 p-4"
          :class="{'click-item': clickItem3}"
          @click="() => { clickItem1 = false, clickItem2 = false, clickItem3 = true}"
        >
          <span> 관심 공연 캘린더</span>
        </li>
      </div>

    <hr>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  data(){
    return{
      userName: '김공연',
      userTags: ['좋아요', '명배우', '웃겨요'],
      clickItem1: false,
      clickItem2: false,
      clickItem3: false
    }
  },


}
</script>

<style>
.li-btn{
  display: inline-block;
  list-style: none;
  cursor: pointer;
}

.li-btn span{
  font-size: 1.5rem;
}

.click-item{
  font-weight: bold;
}
</style>