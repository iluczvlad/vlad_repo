<template>
    <div>
        <div v-if="user && (!user.shoppingList || user.shoppingList.length === 0)">
            Your Shopping List is empty.
        </div>
        <div v-if="user && user.shoppingList && user.shoppingList.length"  
            class="shplst-container">
            <md-card v-for="(sli, index) in user.shoppingList" :key="sli.id"  class="shplst-item">
                <md-card-content>
                    <div class="chips">
                        <md-chip v-for="(ing) of sli.ingredients"
                            :key="ing.id" class="md-primary">
                            {{ing.name}}
                        </md-chip>
                    </div>
                    <div style="display: flex; align-items: center;">
                        <md-chip>
                            {{calculateCalories(sli) + ' kcal'}}
                        </md-chip>
                        <md-button class="md-icon-button md-raised md-accent delete-button"
                                            @click="deleteSLI(sli, index)">
                                    <md-icon>delete_forever</md-icon>
                        </md-button>
                    </div>
                    
                </md-card-content>
            </md-card>
            <md-button v-if="user && user.shoppingList && user.shoppingList.length"
                class="checkout-button md-raised">
                checkout
            </md-button>
        </div>
        
    </div>
</template>

<script>
import IngredientTable from '@/components/IngredientTable.vue'
import {getUserByEmail, deleteFromShoppingList} from '@/api/user'
import * as storage from '@/service/storage'

export default {
    name: "ShoppingList",
    components: {
        IngredientTable,
    },
    data() {
        return {
            user: null,
        }
    },
    mounted() {
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
    },
    methods: {
        deleteSLI(sli, index){
            deleteFromShoppingList(this.user.id, sli.id).then(ok => {
                if (ok){
                    // TODO show snackbar maybe
                    this.user.shoppingList.splice(index, 1)
                }
            })

        },
        calculateCalories(sli) {
            return sli.ingredients.reduce((acc,it) => acc+it.kcal,0)
        }
    }

}
</script>

<style scoped>
.shplst-item {
    margin-bottom: 3px;
    border-radius: 40px;
    width: 100%;
}
.shplst-item > * {
    padding-bottom: 16px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    
}
.shplst-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 15px;
}
.chips {
    display: flex;
    align-items: stretch;
    flex-wrap: wrap;
}
.chips > * {
    margin: 1px 0;
    margin-right: 1px;
}
.checkout-button {
    margin: 10px 0;
    width: fit-content;
}
</style>
