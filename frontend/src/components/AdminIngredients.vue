<template>
    <md-table>
      <md-table-row class="small-padding">
        <md-table-head>Name</md-table-head>
        <md-table-head>Detox</md-table-head>
        <md-table-head>Lactose</md-table-head>
        <md-table-head>Vegan</md-table-head>
        <md-table-head class="kcal">Kcal</md-table-head>
        <md-table-head class="portion">Portion</md-table-head>
        <md-table-head>Sweetener</md-table-head>
        <md-table-head>Temperature</md-table-head>
        <md-table-head>Type</md-table-head>
        <md-table-head></md-table-head>
      </md-table-row>

      <md-table-row class="small-padding" :class="{first:i===0}" v-for="(u,i) in users" :key="u.id">
        <md-table-cell>
            <md-field>
                <md-input v-model="u.name"></md-input>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <md-checkbox v-model="u.detox"></md-checkbox>
        </md-table-cell>
        <md-table-cell>
            <md-checkbox v-model="u.hasLactose"></md-checkbox>
        </md-table-cell>
        <md-table-cell>
            <md-checkbox v-model="u.vegan"></md-checkbox>
        </md-table-cell>
        <md-table-cell>
            <md-field class="kcal">
                <md-input v-model="u.kcal"></md-input>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <md-field class="portion">
                <md-input v-model="u.portion"></md-input>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <md-checkbox v-model="u.sweetener"></md-checkbox>
        </md-table-cell>
        <md-table-cell>
            <md-field class="role">
                <md-select v-model="u.temp">
                    <md-option value="COLD">Cold</md-option>
                    <md-option value="NORMAL">Normal</md-option>
                </md-select>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <md-field class="role">
                <md-select v-model="u.type">
                    <md-option value="SOLID">Solid</md-option>
                    <md-option value="LIQUID">Liquid</md-option>
                    <md-option value="SPICE">Spice</md-option>
                    <md-option value="SPICED">Diabetic Spice</md-option>
                </md-select>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <div style="display: flex;">
                <md-button class="md-icon-button" @click="upus(u, i)">
                    <md-icon>{{i===0?'add_box':'save'}}</md-icon>
                </md-button>
                <md-button class="md-icon-button" @click="dlus(u.id, i)" v-if="i!==0">
                    <md-icon>delete_forever</md-icon>
                </md-button>
            </div>
            
        </md-table-cell>
      </md-table-row>
    </md-table>
</template>

<script>
import {getAllIngredients, saveIngredient, deleteIngredient} from '@/api/ingredient'

export default {
    data(){
        return {
            users: [],
            ita: {
                detox: false,
                hasLactose: false,
                id: null,
                kcal: null,
                name: '',
                portion: null,
                sweetener: false,
                temp: null,
                type: null,
                vegan: false,
            }
        }
    },
    mounted(){
        this.$parent.$el.style.width="100%"
        this.$parent.$el.style.paddingLeft="5px"
        this.$parent.$el.style.paddingRight="5px"
        getAllIngredients().then(u => {
            this.users = [this.ita, ...u]
            this.sortIng()
        })
    },
    methods:{
        upus(dto, i){
            saveIngredient(dto).then(() => {
                this.ita = {
                    detox: false,
                    hasLactose: false,
                    id: null,
                    kcal: null,
                    name: '',
                    portion: null,
                    sweetener: false,
                    temp: null,
                    type: null,
                    vegan: false,
                }
                getAllIngredients().then(u => {
                    this.users = [this.ita, ...u]
                    this.sortIng()
                })
            })
        },
        dlus(id, i){
            deleteIngredient(id).then((ok) => {
                if(ok){
                    this.users.splice(i,1)
                }
            })
        },
        sortIng(){
            this.users.sort(function(a, b) {
                var nameA = a.name.toUpperCase(); // ignore upper and lowercase
                var nameB = b.name.toUpperCase(); // ignore upper and lowercase
                if (nameA < nameB) {
                    return -1;
                }
                if (nameA > nameB) {
                    return 1;
                }

                // names must be equal
                return 0;
            })
        }
    }
}
</script>

<style scoped>
.kcal {
    width: 40px !important;
}
.portion {
    width: 60px !important;
}
.role{
    width: 90px !important;
}
.first {
    background: #40E0D0
}
</style>

<style>
.small-padding > * > * {
}
</style>
