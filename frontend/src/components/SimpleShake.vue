<template>
    <div class="simple-shake">
        <md-button class="md-raised" @click="makeSimpleShake">Make a Shake</md-button>
        <IngredientTable v-if="simpleShake" :shake-arr="simpleShakeArr"/>
    </div>
</template>

<script>
import {generateSimpleShake} from '@/api/shake.js'
import IngredientTable from './IngredientTable.vue'

export default {
    name: 'SimpleShake',
    components: {
        IngredientTable,
    },
    data() {
        return {
            simpleShake: null
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
