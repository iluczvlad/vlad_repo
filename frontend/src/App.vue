<template>
  <div id="app">
    <Header/>
    <AppContent/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import Header from '@/components/Header.vue';
import AppContent from '@/components/AppContent.vue';

export default Vue.extend({
  name: 'App',
  components: {
    Header,
    AppContent,
  }
});
</script>

<style lang="less">
body {
  margin: 0;
}
</style>

<style lang="less">
#app {
  width: 100%;
}
</style>
