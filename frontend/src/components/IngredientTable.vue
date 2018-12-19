<template>
    <div class="shake__table">
        <md-table class="shake__table--75">
            <md-table-row>
                <md-table-head>Name</md-table-head>
                <md-table-head>Kcal</md-table-head>
                <md-table-head>Portion</md-table-head>
                <md-table-head v-if="showCheckBox"></md-table-head>
                <md-table-head v-if="showDeleteButton"></md-table-head>
            </md-table-row>

            <md-table-row v-for="(ing, index) in shakeArr" :key="ing.id">
                <md-table-cell class="table__name">{{ing.name}}</md-table-cell>
                <md-table-cell>{{ing.kcal}}</md-table-cell>
                <md-table-cell>{{ing.portion}}</md-table-cell>
                <md-table-cell v-if="showCheckBox">
                    <md-checkbox v-model="checkboxes" :value="ing" @change="onChange"></md-checkbox>
                </md-table-cell>
                <md-table-cell v-if="showDeleteButton">
                    <md-button class="md-icon-button md-raised md-accent delete-button"
                                @click="onButtonClick(ing, index)">
                        <md-icon>delete_forever</md-icon>
                    </md-button>
                </md-table-cell>
                
            </md-table-row>
        </md-table>
        <div>
            Total kcal {{totalKcal}}
        </div>
    </div>
</template>

<script>
export default {
    name: "IngredientTable",
    props: {
        shakeArr: {
            type: Array,
            default: [],
        },
        showCheckBox: {
            type: Boolean,
            default: false,
        },
        showDeleteButton: {
            type: Boolean,
            default: false,
        },
    },
    data(){
        return {
            checkboxes: [],
        }
    },
    computed: {
        totalKcal() {
            return this.shakeArr.map(it => it.kcal).reduce((acc, it) => acc + it, 0)
        }
    },
    methods: {
        onChange(){
            this.$emit("change", this.checkboxes)
        },
        onButtonClick(ing, index){
            this.$emit("button-click", ing, index)
        }
    }

}
</script>

<style scoped>
.shake__table {
    display: flex;  
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%
}
.shake__table--75 {
}
.table__name {
    text-transform: capitalize;
}
</style>
