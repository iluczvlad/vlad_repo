<template>
    <div class="shake__table">
        <md-table class="shake__table--75">
            <md-table-row>
                <md-table-head>Name</md-table-head>
                <md-table-head>Kcal</md-table-head>
                <md-table-head>Portion</md-table-head>
            </md-table-row>

            <md-table-row v-for="ing in shakeArr" :key="ing.id">
                <md-table-cell class="table__name">{{ing.name}}</md-table-cell>
                <md-table-cell>{{ing.kcal}}</md-table-cell>
                <md-table-cell>{{ing.portion}}</md-table-cell>
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
        }
    },
    computed: {
        totalKcal() {
            return this.shakeArr.map(it => it.kcal).reduce((acc, it) => acc + it, 0)
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
