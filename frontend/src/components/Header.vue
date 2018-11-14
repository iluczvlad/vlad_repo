<template>
    <div class="header">
        
        <router-link to="/"><md-icon>menu</md-icon></router-link>
            
        <div class="log-buttons">
            <md-button class="md-raised md-primary">Login</md-button>
            <md-button class="md-raised md-accent" @click="$router.push('register')">Sign Up</md-button>
            <md-button class="md-raised md-accent">Sign Out</md-button>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.header{
    width: 100%;
    height: 50px;
    background-color: green;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.logo{
    width: 100px;
    height: 100%;
    background-color: aqua;
    cursor: pointer;
}
</style>
