<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" class="register-link">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
#register {
  margin-top: 50px;
}

.form-register {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}

.form-register .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}

.form-register input[type="text"],
.form-register input[type="password"] {
  margin-bottom: 10px;
}

.h3 {
  color: #333333;
  font-size: 24px;
  font-weight: bold;
}

.alert {
  margin-top: 10px;
}

.register-link {
  color: #333333;
  margin-top: 10px;
  display: block;
  text-decoration: none;
}

.btn-primary {
  background-color: #42b983;
  border-color: #42b983;
}

.btn-primary:hover,
.btn-primary:focus,
.btn-primary:active {
  background-color: #359d71;
  border-color: #359d71;
}

.btn-primary:active:focus {
  box-shadow: 0 0 0 0.2rem rgba(67, 197, 144, 0.5);
}
</style>
