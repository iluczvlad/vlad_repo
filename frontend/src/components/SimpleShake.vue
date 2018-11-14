<template>
    <div class="simple-shake">
        <md-button class="md-raised" @click="makeSimpleShake">Make a Shake</md-button>
        <md-table class="simple-shake__table">
            <md-table-row>
                <md-table-head>Name</md-table-head>
                <md-table-head>Kcal</md-table-head>
                <md-table-head>Portion</md-table-head>
            </md-table-row>

            <md-table-row v-for="ing in simpleShakeArr" :key="ing.id">
                <md-table-cell class="table__name">{{ing.name}}</md-table-cell>
                <md-table-cell>{{ing.kcal}}</md-table-cell>
                <md-table-cell>{{ing.portion}}</md-table-cell>
            </md-table-row>
    </md-table>
    <div>
        Total kcal {{totalKcal}}
    </div>
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
        simpleShakeArr() {
            return Object.entries(this.simpleShake).map(arr => arr[1])
        },
        totalKcal() {
            return this.simpleShakeArr.map(it => it.kcal).reduce((acc, it) => acc + it, 0)
        }
    }
}
</script>

<style scoped>
.simple-shake {
    display: flex;  
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%
}
.simple-shake__table {
    width: 75%;
}
.table__name {
    text-transform: capitalize;
}
</style>
