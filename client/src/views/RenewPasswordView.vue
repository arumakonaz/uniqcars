<template>
  <div class="login-register-form-container">
    <div
      class="container w-100 h-100 d-flex align-contents-center justify-content-center"
    >
      <b-form @submit="onRenewPassword" class="login-register-form bg-dark rounded">
        <h1 class="text-white">{{ $t("renew_password_title") }}</h1>
        <p class="mb-5 text-secondary">
          {{ $t("renew_password_lead") }}
        </p>

        <fieldset class="my-2">
          <label class="text-white" for="email">{{ $t("email") }}</label>
          <b-input-group>
            <b-form-input
              type="email"
              class="bg-dark text-white border-1 border-secondary"
              id="email"
              :placeholder="$t('email')"
              required
              trim
              disabled
              v-model="email"
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
          <label class="text-white" for="code">{{ $t("code") }}</label>
          <b-input-group>
            <b-form-input
              type="number"
              class="bg-dark text-white border-1 border-secondary"
              id="code"
              :placeholder="$t('code')"
              required
              trim
              v-model="code"
              size="lg"
              maxlength="3"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon icon="lock" class="text-white" size="lg" />
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="password">{{
            $t("new_password")
          }}</label>
          <b-input-group>
            <b-form-input
              type="password"
              class="bg-dark text-white border-1 border-secondary"
              id="password"
              :placeholder="$t('new_password')"
              required
              trim
              v-model="password"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  icon="user-secret"
                  class="text-white"
                  size="lg"
                />
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="password2">{{
            $t("confirm_password")
          }}</label>
          <b-input-group>
            <b-form-input
              type="password"
              class="bg-dark text-white border-1 border-secondary"
              id="password2"
              :placeholder="$t('confirm_password')"
              required
              trim
              v-model="password2"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  icon="user-secret"
                  class="text-white"
                  size="lg"
                />
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <b-button
          class="mt-4"
          size="lg"
          block
          variant="primary"
          :disabled="isSendBtnDisabled"
          @click="onRenewPassword"
          >{{ $t("to_send") }}</b-button
        >

        <div class="mt-4 text-white text-center d-flex flex-column">
          <span>
            {{ $t("have_account") }}
            <b-link to="/login" class="text-info">
              {{ $t("to_login") }}
            </b-link>
          </span>
        </div>
      </b-form>
    </div>
  </div>
</template>
    
    <script>
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faEnvelope,
  faUserSecret,
  faLock,
} from "@fortawesome/free-solid-svg-icons";

library.add(faEnvelope, faUserSecret, faLock);
export default {
  name: "renew-password-view",
  data() {
    return {
      email: "",
      code: "",
      password: "",
      password2: "",
    };
  },

  mounted() {
    if (!(this.storeUser && this.storeUser.email)) {
      this.$router.push("/login");
    } else {
      this.email = this.storeUser.email;
    }
  },

  methods: {
    onRenewPassword() {
      this.$store
        .dispatch("sendRenewedPassword", {
          email: this.email,
          otpCode: this.code,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/login");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  computed: {
    isSendBtnDisabled() {
      const arePwdEqual = this.password === this.password2;
      return (
        !this.email ||
        !this.code ||
        !this.password ||
        !this.password2 ||
        !arePwdEqual
      );
    },

    storeUser() {
      return this.$store.state.authStore.user;
    },
  },
};
</script>
