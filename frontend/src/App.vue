<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <Header/>
    <div class="app__spacer">
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "@/components/Header.vue"
import * as storage from '@/service/storage'

export default {
  name: 'app',
  components: {
    Header,
  },
  // data() {
  //   return {
  //     userLoggedIn: storage.isLoggedIn()
  //   }
  // },
  // watch: {
  //   $route() {
  //     this.userLoggedIn = storage.isLoggedIn()
  //   }
  // },
  // computed: {
  //   userLoggedIn(){
  //     debugger
  //     return this.$refs.header.userLoggedIn
  //   }
  // }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  display: flex;
  flex-direction: column;
  align-items: center
}
.app__spacer {
  height: 20px;
}
input{
  color: black !important;
  -webkit-text-fill-color: black !important;
}
</style>
