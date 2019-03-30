<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <Header/>
    <div class="app__spacer">
    </div>
    <md-content class="app-content md-scrollbar">
    <router-view></router-view>
    </md-content>
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
}
</script>

<style>
.element::-webkit-scrollbar { width: 0 !important }
#app {
  font-family: 'Aleo', serif;
  font-weight: bold !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  display: flex;
  flex-direction: column;
  align-items: center;
  background: no-repeat center/120% url('./assets/welcome.jpg');
  height: 100vh;
}
.header{
  height: 50px;
}
.app__spacer {
  height: 20px;
}
.app-content{
  height: calc(100vh - 90px);
  width: 75%;
  overflow: auto;
  padding: 10px 30px;
  background: rgba(255, 255, 255, .3) !important;
}
input{
  color: black !important;
  -webkit-text-fill-color: black !important;
}
.md-button-content{
  text-transform: none;
}
.transparent-layout{
    
}
</style>
