<template>
  <div class="login-register-form-container">
    <div
      class="container w-100 h-100 d-flex align-contents-center justify-content-center"
    >
      <b-form class="login-register-form bg-dark rounded">
        <h1 class="text-white">{{$t('login_title')}}</h1>
        <p class="mb-5 text-secondary">{{$t('login_lead')}}</p>

        <fieldset class="my-2">
          <label class="text-white" for="username">{{$t('username')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="username"
              :placeholder="$t('username')"
              required
              trum
              v-model="username"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'envelope']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="password">{{$t('password')}}</label>
          <b-input-group>
            <b-form-input
              type="password"
              class="bg-dark text-white border-1 border-secondary"
              id="password"
              :placeholder="$t('password')"
              required
              trum
              v-model="password"
              size="lg"
            >
            </b-form-input>
          </b-input-group>
        </fieldset>

        <b-button @click="onLogin" class="mt-4" size="lg" block variant="primary" :disabled="isLoginBtnDisabled">{{$t('to_login')}}</b-button>

        <div class="mt-4 text-white text-center d-flex flex-column">
          <span>
            {{$t('forgot_password')}}
            <b-link to="/forgot-password" class="text-info">
              {{$t('to_restore')}}
            </b-link>
          </span>
          <span class="mt-2">
            {{$t('no_account')}}
            <b-link to="/register" class="text-info">
              {{$t('to_register')}}
            </b-link>
          </span>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEnvelope } from '@fortawesome/free-solid-svg-icons'

library.add(faEnvelope)
export default {
  name: "login-view",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    onLogin() {
      this.$store.dispatch("login", {
        username: this.username,
        password: this.password
      })
      .then(() => {
        const carToBuy = localStorage.getItem("carToBuy")
        if (carToBuy) {
          localStorage.removeItem("carToBuy");
          this.$router.push(`/invoice/${carToBuy}`);
          return;
        }
        this.$router.push({ name: "home" });
      })
      .catch(err => {
        console.log(err);
        this.$bvToast.toast(this.$t('login_error'), {
          title: this.$t('error'),
          variant: "danger",
          solid: true,
          autoHideDelay: 5000,
          toaster: "b-toaster-bottom-right",
        });
      });
    }
  },

  computed: {
    isLoginBtnDisabled() {
      return !this.username || !this.password;
    }
  }
};
</script>
