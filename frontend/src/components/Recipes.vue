<template>
    <div class="md-layout transparent-layout">
        <div class="md-layout">
        <md-card class="md-layout-item fav-card" v-for="(fav, index) of favorites" :key="fav.id" md-with-hover>
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <div>{{fav.name}}</div>
                        <div>
                            <md-button class="md-icon-button md-raised delete-button" style="background: green"
                                        @click="addToShoppingList(fav)">
                                <md-icon style="color: white">local_grocery_store</md-icon>
                            </md-button>
                        </div>
                    </div>
                    
                </md-card-header>

                <md-card-content>
                    <IngredientTable :shake-arr="fav.ingredients"/>
                </md-card-content>

                <!-- <md-card-actions>
                <md-button>Action</md-button>
                <md-button>Action</md-button>
                </md-card-actions> -->
            </md-ripple>
        </md-card>
        </div>
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showFavSnackbar">
            <span>Added to Basket</span>
            <md-button class="md-primary" @click="showFavSnackbar = false">Ok</md-button>
        </md-snackbar>
    </div>
</template>

<script>
import {listOurRecipe} from '@/api/our-recipe'
import * as storage from '@/service/storage'
import IngredientTable from './IngredientTable.vue'
import {addToShoppingList, getUserByEmail} from "@/api/user"

export default {
    name: 'OurRecipe',
    components: {
        IngredientTable,
    },
    data(){
        return {
            showFavSnackbar: false,
            favorites: [],
            user: null,
        }
    },
    mounted(){
        listOurRecipe(storage.getEmail()).then(it => this.favorites=it)
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
    },
    methods:{
        addToShoppingList(fav) {
          addToShoppingList(this.user.id, fav.ingredients).then((ok) => {
               this.showFavSnackbar = true
          })
      }
    }
}
</script>

<style scoped>
.fav-card{
    width: 100%;
}
.md-layout{
    flex-direction: column;
    align-items: center;
}
.md-layout-item{
    margin-bottom: 4px;
}
.md-title{
    display: flex;
    justify-content: space-between;
}
</style>
