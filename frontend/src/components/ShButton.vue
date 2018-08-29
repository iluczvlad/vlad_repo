<template>
    <button class="default-button" :disabled="disabled" @click="$emit('click', $event)">
        <slot>
            {{text}}
        </slot>
    </button>
</template>

<script lang="ts">
import Vue from 'vue'
export default Vue.extend({
    name: 'sh-button',
    props: {
        text: {
            type: String,
            default: ''
        },
        disabled: {
            type: Boolean,
            default: false
        }
    }
})
</script>

<style lang="less" scoped>
.default-button {
    background: green;
    font-size: 20px;

    &:hover {
        background: limegreen;
    }
    &:focus {
        outline: none;
    }
}
</style>
