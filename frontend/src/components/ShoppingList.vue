<template>
    <div>
        <div v-if="user && (!user.shoppingList || user.shoppingList.length === 0)">
            Your Shopping List is empty.
        </div>
        <IngredientTable v-if="user && user.shoppingList && user.shoppingList.length !==0"
                         :show-delete-button="true"
                         :shake-arr="user.shoppingList" @button-click="deleteIngredient"/>
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
        deleteIngredient(ing, index){
            
            deleteFromShoppingList(this.user.id, ing.id).then(ok => {
                if (ok){
                    // TODO show snackbar maybe
                    this.user.shoppingList.splice(index, 1)
                }
            })

        }
    }

}
</script>

<style scoped>

</style>
