<template>
    <div id="header">
        <div class="logo">
            LOGO
        </div>
        <div>
            <sh-button :text="'Log In'" :disabled="false" v-if="!isLoggedIn" @click="login"/>
            <sh-button v-if="!isLoggedIn">Sign Up</sh-button>
            <sh-button v-if="isLoggedIn" @click="logout">Log Out</sh-button>
        </div>
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
import ShButton from '@/components/ShButton.vue'
export default Vue.extend({
  name: 'header',
  components: {
      ShButton
  },
  data() {
      return {
          isLoggedIn: false
      };
  },
  methods: {
      login() {
          this.isLoggedIn = true;
      },
      logout() {
          this.isLoggedIn = false;
      }
  }
});
</script>

<style lang="less" scoped>
#header {
    width: 100%;
    height: 80px;
    border-bottom: 1px solid black;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.logo {
    width: 100px;
    height: 80%;
    background: green;
}
</style>
