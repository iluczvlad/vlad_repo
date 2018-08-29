<template>
    <div id="app-content">
        <img src="@/assets/sh.jpg" class="cherry">
        <sh-input :placeholder="message"/>
        <label>{{message}}</label>
        <button @click="changeMessage">Change message</button>
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
import ShInput from './ShInput.vue';

export default Vue.extend({
  name: 'app-content',
  components: {
      ShInput
  },
  data() {
    return {
        message: 'ytyvyvyf',
    };
  },
  methods: {
      changeMessage() {
          this.message = 'qwerty';
      }
  }
});
</script>

<style lang="less" scoped>
#app-content {
    width: 100%;
}
.cherry {
    width: 200px;

}
</style>
