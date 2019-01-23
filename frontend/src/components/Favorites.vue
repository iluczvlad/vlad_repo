<template>
    <div class="md-layout transparent-layout">
        <md-card class="md-layout-item fav-card" v-for="(fav, index) of favorites" :key="fav.id" md-with-hover>
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <div>{{'#' + (index+1)}}</div>
                        <md-button class="md-icon-button md-raised md-accent delete-button"
                                    @click="removeFavorite(fav, index)">
                            <md-icon>delete_forever</md-icon>
                        </md-button>
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

export default {
    name: 'Favorites',
    components: {
        IngredientTable,
    },
    data(){
        return {
            favorites: [],
        }
    },
    mounted(){
        listFavorites(storage.getEmail()).then(it => this.favorites=it)
    },
    methods:{
        removeFavorite(fav, index){
            deleteFavorite(fav.id).then(ok => {
                if (ok){
                    this.favorites.splice(index, 1)
                }
            })
        }
    }
}
</script>

<style scoped>
.md-layout{
    flex-direction: column;
    align-items: center;
}
.md-layout-item{
    margin-bottom: 4px;
    width: 50%;
}
.md-title{
    display: flex;
    justify-content: space-between;
}
</style>
