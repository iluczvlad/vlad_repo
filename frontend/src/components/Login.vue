<template>
    <div class="register">
        <form novalidate class="md-layout" @submit.prevent="validateUser">
        <md-card class="md-layout-item md-size-50 md-small-size-100">
            <md-card-header>
            <div class="md-title">Login</div>
            </md-card-header>

            <md-card-content>
                
            <div class="md-layout md-gutter">
                <div class="md-layout-item md-small-size-100">
                <md-field :class="getValidationClass('email')">
                    <label for="email">Email</label>
                    <md-input type="email" name="email" id="email" autocomplete="email" v-model="form.email" />
                    <span class="md-error" v-if="!$v.form.email.required">The email is required</span>
                    <span class="md-error" v-else-if="!$v.form.email.email">Invalid email</span>
                </md-field>
                <md-field :class="getValidationClass('password')">
                    <label for="password">Password</label>
                    <md-input name="password" type="password" id="password" autocomplete="family-name" v-model="form.password" />
                    <span class="md-error" v-if="!$v.form.password.required">The password is required</span>
                    <span class="md-error" v-else-if="!$v.form.password.minlength">Invalid password</span>
                </md-field>
                </div>
            </div>

            </md-card-content>

            <md-card-actions>
            <md-button type="submit" class="md-primary md-raised">Login</md-button>
            </md-card-actions>
        </md-card>
        </form>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {
required,
email,
minLength,
} from 'vuelidate/lib/validators'
import * as storage from '@/service/storage'

export default {
    name: 'register',
    mixins: [validationMixin],
    data: () => ({
      form: {
        name: null,
        password: null,
        email: null,
      },
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
        email: {
          required,
          email
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
        this.form.email = null
      },
      loginUser () {
        storage.login(this.form.email, this.form.password)
        this.$router.push('')
      },
      validateUser () {
        this.$v.$touch()

        if (this.$v.$invalid) {
          this.loginUser()
        }
      }
    }
}
</script>

<style scoped>
.register {
    width: 75%;

}
</style>