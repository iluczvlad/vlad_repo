<template>
    <md-table>
      <md-table-row class="small-padding">
        <md-table-head>Name</md-table-head>
        <md-table-head>Ingredients</md-table-head>
        <md-table-head></md-table-head>
      </md-table-row>

      <md-table-row class="small-padding" :class="{first:i===0}" v-for="(u,i) in users" :key="u.id">
        <md-table-cell>
            <md-field>
                <md-input v-model="u.name"></md-input>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <div class="md-layout-item">
                <md-autocomplete :value="null" @md-selected="((ing) => addToRecipe(u, ing))" :md-options="ingredients">
                    <template slot="md-autocomplete-item" slot-scope="{ item, term }">
                        <md-highlight-text :md-term="term">{{ item.name }}</md-highlight-text>
                    </template>
                </md-autocomplete>
            </div>
            <div class="md-layout-item item__alergy">
                <md-chip v-for="(alg, index) of u.ingredients"
                         :key="alg.id" class="md-primary"
                         md-deletable
                         @md-delete="">
                    {{alg.name}}
                </md-chip>
            </div>
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
import {listOurRecipe, saveOurRecipe, deleteOurRecipe} from '@/api/our-recipe'
import {getAllIngredients} from '@/api/ingredient'

export default {
    data(){
        return {
            users: [],
            ingredients: [],
            ita: {
                name: '',
                ingredients: [],
            }
        }
    },
    mounted(){
        this.$parent.$el.style.width="100%"
        this.$parent.$el.style.paddingLeft="5px"
        this.$parent.$el.style.paddingRight="5px"
        listOurRecipe().then(u => {
            this.users = [this.ita, ...u]
            this.sortIng()
        })
        getAllIngredients().then(u => {
            this.ingredients = u
        })
    },
    methods:{
        addToRecipe(recipe, ing){
            recipe.ingredients.push(ing)
        },
        upus(dto, i){
            saveOurRecipe(dto).then(() => {
                this.ita = {
                    name: '',
                    ingredients: [],
                }
                listOurRecipe().then(u => {
                    this.users = [this.ita, ...u]
                    this.sortIng()
                })
            })
        },
        dlus(id, i){
            deleteOurRecipe(id).then((ok) => {
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
