<template>
    <div class="register">
        <form novalidate class="md-layout" @submit.prevent="validateUser">
        <md-card class="md-layout-item md-size-50 md-small-size-100">
            <md-card-header>
            <div class="md-title">Sign Up</div>
            </md-card-header>

            <md-card-content>

            <md-field :class="getValidationClass('name')">
                <label for="first-name">Name</label>
                <md-input name="first-name" id="first-name" autocomplete="given-name" v-model="form.name" :disabled="sending" />
                <span class="md-error" v-if="!$v.form.name.required">The first name is required</span>
                <span class="md-error" v-else-if="!$v.form.name.minlength">Invalid first name</span>
            </md-field>

            <md-field :class="getValidationClass('email')">
                <label for="email">Email</label>
                <md-input type="email" name="email" id="email" autocomplete="email" v-model="form.email" :disabled="sending" />
                <span class="md-error" v-if="!$v.form.email.required">The email is required</span>
                <span class="md-error" v-else-if="!$v.form.email.email">Invalid email</span>
                <span class="md-error" v-else-if="!$v.form.email.isUnique">Email already in use</span>
            </md-field>

            <md-field :class="getValidationClass('password')">
                <label for="password">Password</label>
                <md-input name="password" type="password" id="password" v-model="form.password" :disabled="sending" />
                <span class="md-error" v-if="!$v.form.password.required">The password is required</span>
                <span class="md-error" v-else-if="!$v.form.password.minlength">Invalid password</span>
            </md-field>
            <!--added by vlad-->
            <md-field :class="getValidationClass('password1')">
                <label for="password1">Re-Enter Password</label>
                <md-input name="password1" type="password" id="password1" v-model="form.password1" :disabled="sending" />
                <span class="md-error" v-if="!$v.form.password1.required">The password is required</span>
                <span class="md-error" v-else-if="!$v.form.password1.sameAsPassword">The two passwords do not match</span>
            </md-field>
            <md-field class="role">
                <md-select v-model="form.role">
                    <md-option value="ADMIN">Admin</md-option>
                    <md-option value="CLERK">Clerk</md-option>
                </md-select>
            </md-field>
            </md-card-content>

            <md-progress-bar md-mode="indeterminate" v-if="sending" />

            <md-card-actions>
            <md-button type="submit" class="md-primary md-raised" :disabled="sending">Create</md-button>
            </md-card-actions>
        </md-card>

        <md-snackbar :md-active.sync="userSaved">The user {{ lastUser }} was saved with success!</md-snackbar>
        </form>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {
required,
email,
minLength,
sameAs
} from 'vuelidate/lib/validators'
import { checkEmail, registerUser } from '@/api/user.js'

export default {
    name: 'register',
    mixins: [validationMixin],
    data: () => ({
      emailInUse: false,
      form: {
        name: null,
        password: null,
        password1: null,
        email: null,
        role: 'CLERK',
      },
      userSaved: false,
      sending: false,
      lastUser: null
    }),
    validations: {
      form: {
        name: {
          required,
          minLength: minLength(3)
        },
        password: {
          required,
          minLength: minLength(6)
        },
        password1: {
          required,
          sameAsPassword: sameAs('password')
        },
        email: {
          required,
          email,
          isUnique(value) {
            if (value === '') return true
            return new Promise((resolve, reject) => {
              checkEmail(value).then((resp) => resolve((typeof resp === 'string') && resp === 'false'))
            })
          }
        }
      }
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
      clearForm () {
        this.$v.$reset()
        this.form.name = null
        this.form.password = null
        this.form.password1 = null
        this.form.email = null
      },
      saveUser () {
        this.sending = true
        const user = JSON.stringify({
            name: this.form.name,
            password: this.form.password,
            email: this.form.email,
            role: this.form.role,
        })
        registerUser(user).then(() => {
            this.lastUser = `${this.form.name}`
            this.userSaved = true
            this.sending = false
            this.clearForm()
        })
      },
      validateUser () {
        this.$v.$touch()
        if (!this.$v.$invalid) {
          this.saveUser()
        }
      }
    }
}
</script>

<style scoped>
.register {

}
</style>
