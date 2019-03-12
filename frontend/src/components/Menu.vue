<template>
    <div>
        <md-steppers :md-active-step.sync="active" md-linear>
            <md-step id="first" class='step' md-label="Liquids" :md-done.sync="first">
                    <IngredientTable v-if="ing.liquids.length"
                                ref="firstTable"
                                 class="ing-table" 
                                 :shake-arr="ing.liquids"
                                 :show-check-box="true"
                                 :show-kcal="false"
                                 @change="selected.liquids=$event"/>
                <md-button class="md-raised md-primary" :disabled="selected.liquids.length < 1" @click="setDone('first', 'second')">Continue</md-button>
                
            </md-step>

            <md-step id="second" class='step' md-label="Your Fruits" :md-done.sync="second">
                <IngredientTable v-if="ing.solids.length"
                                ref="secondTable"
                                 class="ing-table" 
                                 :shake-arr="ing.solids"
                                 :show-check-box="true"
                                 :show-kcal="false"
                                 @change="selected.solids=$event"/>
                <md-button class="md-raised md-primary" :disabled="selected.solids.length < 1"  @click="setDone('second', 'third')">Continue</md-button>
            </md-step>

            <md-step id="third" class='step' md-label="Your Sweeteners" :md-done.sync="third">
                <IngredientTable v-if="ing.spices.length"
                                ref="thirdTable"
                                 class="ing-table" 
                                 :shake-arr="ing.spices"
                                 :show-check-box="true"
                                 :show-kcal="false"
                                 @change="selected.spices=$event"/>
                <md-button class="md-raised md-primary" @click="setDone('third')">Add to Cart</md-button>
            </md-step>

        </md-steppers>

        <md-badge v-if="user && user.shoppingList" class="md-primary cart-badge"
                :md-content="user.shoppingList.length">
            <md-button class="md-icon-button md-raised" @click="$router.push('/shplst')">
                <md-icon>shopping_cart</md-icon>
            </md-button>
        </md-badge>
    </div>
</template>

<script>

import {getIngredientsByType} from '@/api/ingredient'
import IngredientTable from './IngredientTable.vue'
import {addToShoppingList, getUserByEmail} from "@/api/user"
import * as storage from '@/service/storage'

export default {
    components: {
        IngredientTable
    },
    data: () => ({
        active: 'first',
        first: false,
        second: false,
        third: false,
        ing: {
            liquids: [],
            solids: [],
            spices: [],
        
        },
        selected: {
            liquids: [],
            solids: [],
            spices: [],
        
        },
        user: null,
    }),
    mounted() {
        getIngredientsByType('LIQUID').then((it) => this.ing.liquids=it)
        getIngredientsByType('SOLID').then((it) => this.ing.solids=it)
        getIngredientsByType('SPICE').then((it) => this.ing.spices=it)
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
    },
    methods: {
      setDone (id, index) {
        this[id] = true
        if (index) {
            this.active = index
        }
        if (id === 'third') {
            this.addToShoppingList()
        }
      },
      addToShoppingList () {
          const ids = [
              ...this.selected.liquids,
              ...this.selected.solids,
              ...this.selected.spices,
          ]
          addToShoppingList(this.user.id, ids).then((ok) => {
                this.active='first'
                this.first=false
                this.second=false
                this.third=false
                this.selected={
                    liquids: [],
                    solids: [],
                    spices: [],
                
                }
            this.$refs.firstTable.emptyCheckboxes()
            this.$refs.secondTable.emptyCheckboxes()
            this.$refs.thirdTable.emptyCheckboxes()
            getUserByEmail(storage.getEmail()).then(user => this.user=user)
          })
      }
    }
}
</script>

<style scoped>
.ing-table {
    max-height: 700px;
}
.step {
    width: 200px;
}
.cart-badge {
    position: fixed;
    top: 100px;
    right: 20px;
}
</style>
