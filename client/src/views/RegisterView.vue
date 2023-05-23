<template>
  <div class="login-register-form-container">
    <div
      class="container w-100 h-100 d-flex align-contents-center justify-content-center"
    >
      <b-form @submit="onRegister" class="login-register-form bg-dark rounded">
        <h1 class="text-white">{{ $t("register_title") }}</h1>
        <p class="mb-5 text-secondary">
          {{ $t("register_lead") }}
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
              trum
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

        <b-button
          class="mt-4"
          size="lg"
          block
          variant="primary"
          :disabled="isRegisterBtnDisabled"
          @click="onRegister"
          >{{ $t("to_send") }}
        </b-button>

        <fieldset class="my-4">
          <b-input-group>
            <b-form-checkbox
              id="checkbox-1"
              v-model="checked"
              name="checkbox-1"
              value="accepted"
              unchecked-value="not_accepted"
              class="bg-dark text-white"
            >
              {{ $t("register_terms_1") }}
              <b-link to="/privacy-policy" class="text-info">{{
                $t("register_terms_2")
              }}</b-link>
            </b-form-checkbox>
          </b-input-group>
        </fieldset>

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
import { faEnvelope } from "@fortawesome/free-solid-svg-icons";

library.add(faEnvelope);
export default {
  name: "register-view",
  data() {
    return {
      email: "",
      checked: false,
    };
  },
  methods: {
    onRegister() {
      this.$store
        .dispatch("sendResetPasswordEmail", this.email)
        .then(() => {
          this.$router.push("/renew-password");
        })
        .catch((error) => {
          console.log(error);
          this.$bvToast.toast(this.$t("forgot_pwd_error"), {
            title: this.$t("error"),
            variant: "danger",
            solid: true,
            autoHideDelay: 5000,
            toaster: "b-toaster-bottom-right",
          });
        });
    },
  },

  computed: {
    isRegisterBtnDisabled() {
      return !this.email || !this.checked;
    },
  },
};
</script>