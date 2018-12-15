<template>
    <div class="md-layout">
        <div class="md-layout options-initial" :class="{hidden: optionsHidden}">
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
                <md-field>
                <label for="maxsol">Solid Ingredients</label>
                <md-select v-model="options.maxsol" id="maxsol">
                    <md-option value="3">3</md-option>
                    <md-option value="4">4</md-option>
                    <md-option value="5">5</md-option>
                    <md-option value="6">6</md-option>
                    <md-option value="7">7</md-option>
                    <md-option value="8">8</md-option>
                </md-select>
                </md-field>
            </div>
            <div class="md-layout-item">
                <md-field>
                <label for="maxliq">Liquid Ingredients</label>
                <md-select v-model="options.maxliq" id="maxliq">
                    <md-option value="1">1</md-option>
                    <md-option value="2">2</md-option>
                </md-select>
                </md-field>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.detox">Detox</md-switch>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.lactose">Lactose</md-switch>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.vegan">Vegan</md-switch>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.sweetener">Sweetener</md-switch>
            </div>
            <div class="md-layout-item">
                <md-radio class="md-accent" v-model="options.temp" :value="0">Normal Temp</md-radio>
                <md-radio class="md-primary" v-model="options.temp" :value="1">Cold Temp</md-radio>
            </div>
        </div>
        <div class="md-layout-item item--margin-auto">
            <md-button class="md-icon-button md-primary" v-if="optionsHidden" @click="showOptions">
                <md-icon>arrow_drop_down_circle</md-icon>
            </md-button>
        </div>
        <div class="md-layout-item item--margin-auto">    
            <md-button class="md-raised" @click="makeComplexShake">{{generateButtonText}}</md-button>
        </div>
        <IngredientTable v-if="shake" :shake-arr="complexShakeArr"/>
    </div>
</template>

<script>
import {generateComplexShake} from '@/api/shake'
import IngredientTable from './IngredientTable.vue'

export default {
    name: "ComplexShake",
    components: {
        IngredientTable
    },
    data() {
        return {
            options: {
                kcal: '250-300',
                detox: false,
                temp: 0,
                vegan: false,
                sweetener: true,
                maxsol: 3,
                maxliq: 1,
                lactose: true,
            },
            optionsHidden: false,
            hasMadeShake: false,
            shake: null,
        }
    },
    methods: {
        toRecipe() {
            return {
                minKcal: parseInt(this.options.kcal.split('-')[0]),
                maxKcal: parseInt(this.options.kcal.split('-')[1]),
                detox: this.options.detox,
                vegan: this.options.vegan,
                temp: this.options.temp,
                sweetener: this.options.sweetener,
                maxsol: this.options.maxsol,
                maxliq: this.options.maxliq,
                lactose: this.options.lactose,
            }
        },
        makeComplexShake() {
            //console.log(this.toRecipe())
            this.optionsHidden=true
            this.hasMadeShake=true
            generateComplexShake(this.toRecipe()).then((shake)=>{
                //console.log(shake)
                console.table(shake.liquids)
                console.table(shake.solids)
                console.table(shake.spices)
                this.shake=shake
            })
        },
        showOptions() {
            this.optionsHidden=false;
        }
    },
    computed: {
        generateButtonText(){
            return this.hasMadeShake ? "MAKE ANOTHER SHAKE":"MAKE A SHAKE"
        },
        complexShakeArr(){
            return [...this.shake.liquids, ...this.shake.solids, ...this.shake.spices]
        }
    }
}
</script>

<style scoped>
.md-layout{
    flex-direction: column;
    justify-content: center;
}
.options-initial{
    max-height: 100%;
    overflow: hidden;
    transition: max-height 0s ease;
}
.hidden{
    max-height: 0;
}
.item--margin-auto{
    margin: auto;
}
</style>
