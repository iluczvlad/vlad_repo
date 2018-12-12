<template>
    <div class="md-layout">
        <div class="md-layout-item">
            <md-field>
            <label for="maxkcal">Max Kcal</label>
            <md-select v-model="options.kcal" id="maxkcal">
                <md-option value="150-200">150-200</md-option>
                <md-option value="200-250">200-250</md-option>
                <md-option value="250-300">250-300</md-option>
                <md-option value="300-350">300-350</md-option>
                <md-option value="400-500">400-500</md-option>
                <md-option value="500-10000">over 500</md-option>
            </md-select>
            </md-field>
        </div>
        <div class="md-layout-item">
            <md-switch v-model="options.detox">Detox</md-switch>
        </div>
        <div class="md-layout-item">
            <md-radio v-model="options.temp" :value="0">Normal Temp</md-radio>
            <md-radio v-model="options.temp" :value="1">Cold Temp</md-radio>
        </div>
        
        <md-button class="md-raised" @click="makeComplexShake">Make a Shake</md-button>
    </div>
</template>

<script>
import {generateComplexShake} from '@/api/shake'

export default {
    data() {
        return {
            options: {
                kcal: '250-300',
                detox: false,
                temp: 0,
                // TODO ADD OTHERS
            }
        }
    },
    methods: {
        toRecipe() {
            return {
                minKcal: parseInt(this.options.kcal.split('-')[0]),
                maxKcal: parseInt(this.options.kcal.split('-')[1]),
                detox: this.options.detox,
                // TODO add others
            }
        },
        makeComplexShake() {
            console.log(this.toRecipe())
            generateComplexShake(toRecipe()).then((shake)=>{
                console.log(shake)
            })
        }
    }
}
</script>

<style scoped>
.md-layout{
    flex-direction: column;
}
</style>
