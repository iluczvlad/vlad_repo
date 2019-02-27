<template>
    <div>
        <md-steppers :md-active-step.sync="active" md-linear>
            <md-step id="first" class='step' md-label="Liquids" :md-done.sync="first">
                    <IngredientTable v-if="ing.liquids.length"
                                 class="ing-table" 
                                 :shake-arr="ing.liquids"
                                 :show-check-box="true"
                                 @change="selected.liquids=$event"/>
                <md-button class="md-raised md-primary" :disabled="selected.liquids.length < 1" @click="setDone('first', 'second')">Continue</md-button>
                
            </md-step>

            <md-step id="second" class='step' md-label="Your Fruits" :md-done.sync="second">
                <IngredientTable v-if="ing.solids.length"
                                 class="ing-table" 
                                 :shake-arr="ing.solids"
                                 :show-check-box="true"
                                 @change="selected.solids=$event"/>
                <md-button class="md-raised md-primary" :disabled="selected.solids.length < 1"  @click="setDone('second', 'third')">Continue</md-button>
            </md-step>

            <md-step id="third" class='step' md-label="Your Sweeteners" :md-done.sync="third">
                <IngredientTable v-if="ing.spices.length"
                                 class="ing-table" 
                                 :shake-arr="ing.spices"
                                 :show-check-box="true"
                                 @change="selected.spices=$event"/>
                <md-button class="md-raised md-primary" @click="setDone('third')">Add to Cart</md-button>
            </md-step>

        </md-steppers>
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
</style>
