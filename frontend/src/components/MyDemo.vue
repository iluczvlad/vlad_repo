<template>
  <div>
    <md-table>
      <md-table-row>
        <md-table-head md-numeric>ID</md-table-head>
        <md-table-head>detox</md-table-head>
        <md-table-head>kcal</md-table-head>
        <md-table-head>name</md-table-head>
        <md-table-head>taste</md-table-head>
      </md-table-row>

      <md-table-row v-for="ingredient in ingredients" :key="ingredient.id">
        <md-table-cell md-numeric>{{ingredient.id}}</md-table-cell>
        <md-table-cell>{{ingredient.detox}}</md-table-cell>
        <md-table-cell>{{ingredient.kcal}}</md-table-cell>
        <md-table-cell>{{ingredient.name}}</md-table-cell>
        <md-table-cell>{{ingredient.taste}}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import {getAllIngredients} from '@/api/ingredient.js'

export default {
  name: 'MyDemo',//nume componenta
  data() {
    return {
      ingredients: []
    }
  },
  mounted() {
    getAllIngredients().then(response => {
      this.ingredients=response;
    })
  }
}
</script>