<template>
    <div class="transparent-layout">
        <div class="md-layout">
            <div class="md-layout-item item--margin-auto">    
                <md-button class="md-raised" @click="savePreferences">Save</md-button>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.diabetic">Diabetic</md-switch>
            </div>
            <div class="md-layout-item">
                <md-switch class="md-primary" v-model="options.lactoseIntolerant">Lactose Intolerant</md-switch>
            </div>
            <div class="md-layout-item">
                <md-autocomplete :value="null" @md-selected="addToAllergies" :md-options="ingredients">
                    <label>Allergies</label>
                    <template slot="md-autocomplete-item" slot-scope="{ item, term }">
                        <md-highlight-text :md-term="term">{{ item.name }}</md-highlight-text>
                    </template>
                </md-autocomplete>
            </div>
            <div class="md-layout-item item__alergy">
                <md-chip v-for="(alg, index) of options.allergies"
                         :key="alg.id" class="md-primary"
                         md-deletable
                         @md-delete="deleteFromAllergies(index)">
                    {{alg.name}}
                </md-chip>
            </div>
        </div>
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showSnackbar">
            <span>Preferences saved!</span>
            <md-button class="md-primary" @click="showSnackbar = false">Ok</md-button>
        </md-snackbar>
    </div>
</template>

<script>

import {getAllIngredients} from '@/api/ingredient'
import {getUserByEmail, saveUserPreferences} from '@/api/user'
import * as storage from '@/service/storage'

export default {
    name: "PermPrefs",
    data(){
        return {
            options: {
                diabetic: false,
                lactoseIntolerant: false,
                allergies: [],
            },
            ingredients: [],
            user: null,
            showSnackbar: false
        }
    },
    mounted(){
        getAllIngredients().then(it => this.ingredients=it)
        getUserByEmail(storage.getEmail()).then(it => {
            this.user=it
            this.options.diabetic=this.user.diabetic ? this.user.diabetic:false
            this.options.lactoseIntolerant=this.user.lactoseIntolerant ? this.user.lactoseIntolerant:false
            this.options.allergies=this.user.allergies ? this.user.allergies:[]
        })
    },
    methods: {
        addToAllergies(ing){
            this.options.allergies.push(ing)
        },
        savePreferences(){
            const dto={
                id: this.user.id,
                name: this.user.name,
                diabetic: this.options.diabetic,
                lactoseIntolerant: this.options.lactoseIntolerant,
                allergies: this.options.allergies,
            }
            saveUserPreferences(dto).then(ok => {
                if (ok){
                    this.showSnackbar=true
                }
            })
        },
        deleteFromAllergies(index){
            this.options.allergies.splice(index, 1)
        }
    }
}
</script>

<style scoped>
.md-layout{
    flex-direction: column;
}
.item--margin-auto{
    margin: auto;
}
.item__alergy{
    max-width: 350px;
}
.item__alergy > * {
    margin: 3px;
}

</style>
