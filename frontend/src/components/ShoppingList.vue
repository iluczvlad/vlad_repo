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
                        <md-chip>
                            {{calculatePrice(sli) + ' RON'}}
                        </md-chip>
                        <md-button class="md-icon-button md-raised md-accent delete-button"
                                            @click="deleteSLI(sli, index)">
                                    <md-icon>delete_forever</md-icon>
                        </md-button>
                        <md-button class="md-icon-button md-raised delete-button" style="background: green"
                                            @click="addToFavs(sli)">
                                    <md-icon style="color: white">save</md-icon>
                        </md-button>
                    </div>
                    
                </md-card-content>
            </md-card>
            <md-button v-if="user && user.shoppingList && user.shoppingList.length" @click="showDialog=true"
                class="checkout-button md-raised">
                checkout
            </md-button>
        </div>
        <md-dialog :md-active.sync="showDialog">
            <form class="md-layout" @submit.prevent="validateData" v-if="!orderWasPlaced">
            <md-dialog-title>Checkout</md-dialog-title>
                <div class="md-layout md-gutter">
                    <div class="md-layout-item md-small-size-100">
                    <md-field :class="getValidationClass('phone')">
                        <label for="phone">Phone Number</label>
                        <md-input type="text" name="phone" id="phone" autocomplete="phone" v-model="form.phone" />
                        <span class="md-error" v-if="!$v.form.phone.required">The phone number is required</span>
                        <span class="md-error" v-else-if="!$v.form.phone.numeric">Invalid phone number</span>
                    </md-field>
                    <md-field :class="getValidationClass('address')">
                        <label for="address">Address</label>
                        <md-input name="address" id="address" autocomplete="address" v-model="form.address" />
                        <span class="md-error" v-if="!$v.form.address.required">The address is required</span>
                    </md-field>
                    </div>
                </div>
            <md-dialog-actions>
                <md-button class="md-primary" @click="showDialog = false">Close</md-button>
                <md-button class="md-primary" type="submit">Send</md-button>
            </md-dialog-actions>
            </form>
            <md-dialog-content v-else>
                Your order has been sent!
            </md-dialog-content>
        </md-dialog> 
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showFavSnackbar">
            <span>Added to Favorites</span>
            <md-button class="md-primary" @click="showFavSnackbar = false">Ok</md-button>
        </md-snackbar>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import IngredientTable from '@/components/IngredientTable.vue'
import {getUserByEmail, deleteFromShoppingList} from '@/api/user'
import * as storage from '@/service/storage'
import {saveFavorite} from '@/api/favorite'
import {
    required,
    numeric,
} from 'vuelidate/lib/validators'
import {placeOrder} from '@/api/request'

export default {
    name: "ShoppingList",
    components: {
        IngredientTable,
    },
    mixins: [validationMixin],
    data() {
        return {
            user: null,
            showDialog: false,
            form: {
                password: null,
                email: null,
            },
            orderWasPlaced: false,
            showFavSnackbar: false,
        }
    },
    validations: {
      form: {
        phone: {
          required,
          numeric,
        },
        address: {
          required,
        }
      }
    },
    mounted() {
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
    },
    methods: {
        getValidationClass (fieldName) {
            const field = this.$v.form[fieldName]
            if (field) {
                return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
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
        },
        calculatePrice(sli) {
            const no = sli.ingredients.length
            if (no >= 3 && no <= 5) {
                return 10;
            } else if (no >= 6 && no <= 8) {
                return 14;
            } else {
                return 17;
            } 
        },
        validateData () {
            this.$v.$touch()
            if (!this.$v.$invalid) {
                this.place()
            }
        },
        place () {
            placeOrder({
                email: this.user.email,
                phone: this.form.phone,
                address:this.form.address,
            }).then((ok) => {
                getUserByEmail(storage.getEmail()).then(user => {
                    this.user=user
                    this.orderWasPlaced=true
                })
            })
        },
        addToFavs(sli){
            saveFavorite({
                ingredients: sli.ingredients,
                userEmail: storage.getEmail(),
            }).then(ok=>{
                if (ok){
                    this.showFavSnackbar = true
                }
            })
        },
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
