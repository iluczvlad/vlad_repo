<template>
    <form novalidate class="md-layout contact-form" @submit.prevent="validateUser">
        <md-card class="md-layout-item md-size-50 md-small-size-100">
            <md-card-header>
            <div class="md-title">Contact</div>
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
            </md-field>

            <md-field>
                <label for="message">Message</label>
                <md-textarea id="message" v-model="form.message" md-autogrow :disabled="sending"></md-textarea>
                <span class="md-error" v-if="!$v.form.message.required">The message is required</span>
            </md-field>
            </md-card-content>



            <md-card-actions>
            <md-button type="submit" class="md-primary md-raised" :disabled="sending">Send</md-button>
            </md-card-actions>
        </md-card>
        </form>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {
required,
email,
minLength,
sameAs
} from 'vuelidate/lib/validators'
export default {
    mixins: [validationMixin],
    data: () => ({

      form: {
        name: null,
        message: null,
        email: null,
      },
      sending: false,
    }),
    validations: {
      form: {
        name: {
          required,
          minLength: minLength(3)
        },
        message: {
          required,
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
    }
}
</script>

<style>
.contact-form {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 85%
}
</style>
