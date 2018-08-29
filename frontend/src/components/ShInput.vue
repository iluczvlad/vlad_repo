<template>
    <input :placeholder="placeholder">
</template>

<script lang="ts">
import Vue from 'vue'
export default Vue.extend({
    name: 'sh-input',
    props: {
        placeholder: {
            type: String
        }
    }
})
</script>

<style lang="less" scoped>

</style>
