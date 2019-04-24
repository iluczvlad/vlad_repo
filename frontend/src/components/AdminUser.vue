<template>
    <md-table>
      <md-table-row>
        <md-table-head md-numeric>ID</md-table-head>
        <md-table-head>Name</md-table-head>
        <md-table-head>Email</md-table-head>
        <md-table-head>Role</md-table-head>
        <md-table-head></md-table-head>
      </md-table-row>

      <md-table-row v-for="(u,i) in users" :key="u.id">
        <md-table-cell md-numeric>{{u.id}}</md-table-cell>
        <md-table-cell>
            <md-field>
                <md-input v-model="u.name"></md-input>
            </md-field>
        </md-table-cell>
        <md-table-cell>{{u.email}}</md-table-cell>
        <md-table-cell>
            <md-field class="role">
                <md-select v-model="u.role" :disabled="u.role==='BASIC'">
                    <md-option value="ADMIN">Admin</md-option>
                    <md-option value="CLERK">Clerk</md-option>
                    <md-option value="BASIC">Basic</md-option>
                </md-select>
            </md-field>
        </md-table-cell>
        <md-table-cell>
            <div style="display: flex;">
                <md-button @click="upus(u)">update</md-button>
                <md-button @click="dlus(u.id, i)">delete</md-button>
            </div>
            
        </md-table-cell>
      </md-table-row>
    </md-table>
</template>

<script>
import {getAllUsers, updateUser, deleteUser} from '@/api/user'

export default {
    data(){
        return {
            users: []
        }
    },
    mounted(){
        getAllUsers().then(u => this.users = u)
    },
    methods:{
        upus(dto){
            updateUser(dto).then(() => {
                
            })
        },
        dlus(id, i){
            deleteUser(id).then((ok) => {
                if(ok){
                    this.users.splice(i,1)
                }
            })
        }
    }
}
</script>

<style scoped>
.role{
    width: 90px !important;
}
</style>
