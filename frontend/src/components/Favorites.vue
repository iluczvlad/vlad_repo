<template>
    <div class="md-layout transparent-layout">
        <md-card class="md-layout-item fav-card" v-for="(fav, index) of favorites" :key="fav.id" md-with-hover>
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <div>{{'#' + (index+1)}}</div>
                        <div>
                            <md-button class="md-icon-button md-raised md-accent delete-button"
                                    @click="removeFavorite(fav, index)">
                                <md-icon>delete_forever</md-icon>
                            </md-button>
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
</template>

<script>
import {listFavorites, deleteFavorite} from '@/api/favorite'
import * as storage from '@/service/storage'
import IngredientTable from './IngredientTable.vue'
import {addToShoppingList, getUserByEmail} from "@/api/user"

export default {
    name: 'Favorites',
    components: {
        IngredientTable,
    },
    data(){
        return {
            favorites: [],
            user: null,
        }
    },
    mounted(){
        listFavorites(storage.getEmail()).then(it => this.favorites=it)
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
    },
    methods:{
        removeFavorite(fav, index){
            deleteFavorite(fav.id).then(ok => {
                if (ok){
                    this.favorites.splice(index, 1)
                }
            })
        },
        addToShoppingList(fav) {
          addToShoppingList(this.user.id, fav.ingredients).then((ok) => {
               
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
