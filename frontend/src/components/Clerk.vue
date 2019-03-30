<template>
    <div class="clerk">
        <md-card class="request" v-for="request in requests" :key="request.id">
            <md-card-header>
                <div class="md-title"> {{request.basic.name}}</div>
                <div class="md-subhead">{{new Date(request.placementDate).toLocaleString('en-GB')}}</div>
                </md-card-header>
            <div v-if="request.basic && request.basic.shoppingList && request.basic.shoppingList.length"  
                class="shplst-container">
                <md-card v-for="(sli) in request.basic.shoppingList" :key="sli.id"  class="shplst-item">
                    <md-card-content>
                        <div class="chips">
                            <md-chip v-for="(ing) of sli.ingredients"
                                :key="ing.id" class="md-primary">
                                {{ing.name}}
                            </md-chip>
                        </div>
                        <div style="display: flex; align-items: center;">
                            <md-chip>
                                {{calculatePrice(sli) + ' RON'}}
                            </md-chip>
                        </div>
                        
                    </md-card-content>
                </md-card>
                <md-chip style="margin-top: 10px;">
                    {{'Total: ' + calculateRequestPrice(request) + ' RON'}}
                </md-chip>
                <md-button v-if="request.basic && request.basic.shoppingList && request.basic.shoppingList.length"
                    @click="confirm(request)"
                    class="checkout-button md-raised">
                    Confirm
                </md-button>
                    
            </div>
        </md-card>
    </div>
</template>

<script>

import {getUserByEmail} from '@/api/user'
import {getAllRequests, confirmRequest} from '@/api/request'
import * as storage from '@/service/storage'

export default {
    data() {
        return {
            requests: [],
            user: null,
        }
    },
    mounted() {
        getUserByEmail(storage.getEmail()).then(user => this.user=user)
        getAllRequests().then(requests => this.requests=requests)
    },
    methods: {
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
        calculateRequestPrice(request) {
            return request.basic.shoppingList.reduce((acc,it) => acc + this.calculatePrice(it), 0)
        },
        confirm(request) {
            confirmRequest({
                ...request,
                clerkId: this.user.id,
            }).then((ok) => {
                getAllRequests().then(requests => this.requests=requests)
            })
        }
    }
}
</script>

<style>
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
.clerk {
}
.request {
    margin-top: 15px;
    padding: 15px;
    background: rgba(73, 255, 1, 0.349) !important;
}
</style>
