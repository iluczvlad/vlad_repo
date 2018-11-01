<template>
    <div>
        <md-button class="md-raised" @click="makeSimpleShake">Make a Shake</md-button>
        <md-table>
            <md-table-row>
                <md-table-head>name</md-table-head>
                <md-table-head>kcal</md-table-head>
                <md-table-head>portion</md-table-head>
            </md-table-row>

            <md-table-row v-for="(ing, index) in simpleShakeArr" :key="ing.id + index">
                <md-table-cell>{{ing.name}}</md-table-cell>
                <md-table-cell>{{ing.kcal}}</md-table-cell>
                <md-table-cell>{{ing.portion}}</md-table-cell>
            </md-table-row>
    </md-table>
    </div>
</template>

<script>
import {generateSimpleShake} from '@/api/shake.js'

export default {
    name: 'SimpleShake',
    data() {
        return {
            simpleShake: {}
        }
    },
    methods: {
        makeSimpleShake() {
            generateSimpleShake().then((response) => {
                this.simpleShake=response
            })
        }
    },
    computed: {
        simpleShakeArr(){
            return Object.entries(this.simpleShake).map(arr => arr[1])
        }
    }
}
</script>

<style scoped>

</style>
