<template>
    <div id="header">
        <div class="logo">
            LOGO
        </div>
        <div>
            <button v-if="!isLoggedIn" @click="login">Log In</button>
            <button v-if="!isLoggedIn">Sign Up</button>
            <button v-if="isLoggedIn" @click="logout">Log Out</button>
        </div>
    </div>
</template>

<script>
export default {
  name: 'header',
  data: function () {
      return {
          isLoggedIn: false,
      }
  },
  methods: {
      login: function () {
          this.isLoggedIn = true;
      },
      logout: function () {
          this.isLoggedIn = false;
      }
      
  }
};
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
