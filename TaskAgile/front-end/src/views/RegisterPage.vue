<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png">
          <div class="tagline">Open source task management tool</div>
        </div>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{errorMessage}}</div>
          <div class="form-group">
            <label>Username</label>
            <input type="text" class="form-control" id="username" value="" v-model="form.username">
          </div>
          <div class="form-group">
            <label>Email address</label>
            <input type="email" class="form-control" id="emailAddress" value="" v-model="form.emailAddress">
          </div>
          <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" id="password" value="" v-model="form.password">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
        </form>

      </div>
    </div>
    <footer class="footer">
      <span class="copyright">...</span>
      <ul class="footer-links list-inline float-right">...</ul>
    </footer>
  </div>
</template>
<style lang="scss" scoped>
  .container {max-width: 900px;}
  .register-form {margin-top: 50px; max-width: 320px;}
  .logo-wrapper {margin-bottom: 40px}
  .footer {width: 100%; line-height: 40px; margin-top: 50px;}
</style>
<script>
import registrationService from '../services/registration'
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'

export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(100)
      }
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }
      registrationService.register(this.form).then(() => {
        this.$router.push({ name: 'LoginPage' })
      }).catch((error) => {
        this.errorMessage = 'Failed to register user. Reason ' + (error.message ? error.message : 'Unknown') + '.'
      })
    }
  }
}
</script>
