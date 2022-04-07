<template>
  <v-dialog
    :value="listDialog"
    max-width="1000px"
    max-height="70%"
    @click:outside="() => {localStorage.removeItem('accessToken'), 
    $store.dispatch('deleteToken'), 
    $emit('close-list') }"
    >
    <v-card style="padding: 3rem;">
      <v-card-title class="d-flex justify-center">
        <span class="text-h4"> 공연 추천 </span>
      </v-card-title>

      <v-container class="d-flex flex-column justify-center align-center" style="padding-bottom:50px">
        <span style="font-weight: 600; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;"> 끌리는 공연 포스터를 골라주세요! </span>
        <v-row class="init-posters-wrapper">
          <template v-for="poster in posters">
            <v-col
              cols="4"
              :key="poster.id"
              @click="clickItem(poster.id)"
              :class="{active: clickedItems.includes(poster.id)}"
            >
              <v-card
                flat
              >
                <v-img
                  :src="poster.posterPath"
                  :aspect-ratio="3/4"
                  width="100%"
                  height="100%"
                >
                </v-img>
                <v-card-actions
                  class="px-0"
                >
                  <v-chip-group
                    column
                  >
                    <v-chip
                      v-for="(tag, i) in poster.tags" 
                      :key="i"
                      outlined
                      color="pink"
                      text-color="red"
                      label
                      > 
                    #{{ tag }} 
                    </v-chip>
                  </v-chip-group>
                </v-card-actions>
              </v-card>
            </v-col>
          </template>
        </v-row>
        <v-btn 
          text
          class="mt-5">
          새로고침
        </v-btn>
        <v-btn
          text
          @click="addContents"
        >
          완료
        </v-btn>
        <!-- <v-btn
          text
          @click="() => { $store.dispatch('deleteToken') ,$emit('close-list')}"
        >
          닫기
        </v-btn> -->
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'PosterListUp',
  props:{
    open: Boolean,
    posters: Array,
  },

  data(){
    return{
      clickedItems : []
    }
  },

  methods:{
    clickItem(idx){
      if(this.clickedItems.includes(idx)){
        this.clickedItems.splice(this.clickedItems.indexOf(idx), 1)
      } else{
        this.clickedItems.push(idx)
      }
    },

    addContents(){
      if(this.clickedItems.length !== 0){
        this.clickedItems.forEach((contentId) => {
          this.$store.dispatch('addPreference', {id: contentId})
        })
      } else{
        localStorage.removeItem('accessToken')
        this.$store.dispatch('deleteToken')
        this.$emit('close-list')
      }
    }
  },

  computed:{
    listDialog(){
      return this.open ? true : false
    },

    InitPosters(){
      return this.posters.length !== 0 ? this.posters : null
    }
  },


}
</script>

<style>
.init-posters-wrapper{
  overflow-y: auto;
}

.active{
  background-color: rgba(82, 79, 79, 0.4);
}

</style>