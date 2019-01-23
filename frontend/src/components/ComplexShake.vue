<template>
    <div class="md-layout transparent-layout">
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
        <div class="md-layout-item buttons">
            <div style="width: 52px"></div>
            <md-button class="md-icon-button md-raised" @click="makeComplexShake">
                <md-icon>local_drink</md-icon>
            </md-button>
            <md-button class="md-icon-button md-accent" v-if="optionsHidden" @click="showOptions">
                <md-icon>close</md-icon>
            </md-button>
            <div v-if="!optionsHidden" style="width: 52px"></div>
        </div>
        <IngredientTable v-if="shake"
                         :shake-arr="complexShakeArr"
                         :show-check-box="true"
                         @change="updateShoppingList"/>
        <div v-if="hasMadeShake && shake" class="md-layout-item item--margin-auto">
            <md-button class="md-raised" @click="addToFavs">Add Recipe to Favorites</md-button>
        </div>
        <!-- <div v-if="hasMadeShake" class="md-layout-item item--margin-auto">
            <md-button class="md-raised" @click="addToShpLst">Add Items to Shopping list</md-button>
        </div> -->
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showFavSnackbar">
            <span>Added to Favorites</span>
            <md-button class="md-primary" @click="showFavSnackbar = false">Ok</md-button>
        </md-snackbar>
        <!-- <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showShoppingListSnackbar">
            <span>Added to Shopping List</span>
            <md-button class="md-primary" @click="showShoppingListSnackbar = false">Ok</md-button>
        </md-snackbar> -->
    </div>
</template>

<script>
import {generateComplexShake} from '@/api/shake'
import IngredientTable from './IngredientTable.vue'
import * as storage from '@/service/storage'
import {getUserByEmail, addToShoppingList} from '@/api/user'
import {saveFavorite} from '@/api/favorite'

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
            showFavSnackbar: false,
            shoppingList: [],
            user: null,
            showShoppingListSnackbar: false,

        }
    },
    mounted(){
        getUserByEmail(storage.getEmail()).then(user => {
            this.options.lactose=user.lactoseIntolerant ? !user.lactoseIntolerant : true
            this.user=user
        })
    },
    methods: {
        toRecipe() {
            return {
                userEmail: storage.getEmail(),
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
            try {
                generateComplexShake(this.toRecipe()).then((shake)=>{

                this.shake=shake
            })
            }catch(e){
                console.log(e);
            }
        },
        showOptions() {
            this.optionsHidden=false;
            this.shake=null;
        },
        addToFavs(){
            saveFavorite({
                ingredients: this.complexShakeArr,
                userEmail: storage.getEmail(),
            }).then(ok=>{
                if (ok){
                    this.showFavSnackbar = true
                }
            })
        },
        updateShoppingList(shoppingList){
            this.shoppingList=shoppingList
        },
        addToShpLst(){
            addToShoppingList(this.user.id, this.shoppingList).then(ok => {
                if (ok){
                    this.showShoppingListSnackbar =true
                }
            })
        },
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
.md-layout {
    flex-direction: column;
    justify-content: center;
}
.options-initial {
    max-height: 100%;
    overflow: hidden;
}

@keyframes maxheight {
    from {
        max-height: 100%;
    }
    to {
        max-height: 0;
    }
}

.hidden{
    max-height: 0;
    animation: maxheight 0s;
    animation-timing-function: ease-out;
}
.item--margin-auto{
    margin: auto;
}

.buttons{
    width: 100%;
    margin-bottom: 10px;
    display: flex;
    justify-content: space-between;
}
</style>
