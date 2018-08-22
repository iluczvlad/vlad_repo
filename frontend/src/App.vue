<template>
  <div id="app">
    <Header/>
    <AppContent/>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import AppContent from "@/components/AppContent.vue";

export default {
  components: {
    Header,
    AppContent,
  }
}
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
