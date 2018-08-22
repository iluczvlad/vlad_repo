<template>
    <div id="app-content">
        <img src="@/assets/sh.jpg" class="cherry">
        <input v-model="message">
        <label>{{message}}</label>
        <button @click="changeMessage">Change message</button>
    </div>
</template>

<script>
export default {
  name: 'app-content',
  data: function () {
    return {
        message: '',
    }
  },
  methods: {
      changeMessage: function () {
          this.message = 'qwerty';
      }
  }
};
</script>

<style lang="less" scoped>
#app-content {
    width: 100%;
}
.cherry {
    width: 200px;

}
</style>
