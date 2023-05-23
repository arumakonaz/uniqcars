<template>
  <div>
    <b-navbar toggleable="lg" variant="dark" small type="dark">
      <b-navbar-brand to="/">UNIQCARS</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <div class="container">
          <b-navbar-nav class="ml-auto">
            <b-nav-item>
              <router-link class="nav-link" to="/cars">
              {{ $t("cars") }}
              </router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link class="nav-link" to="/partners">
                {{ $t("partners") }}
              </router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link class="nav-link" to="/contacts">
                {{ $t("contacts") }}
              </router-link>
            </b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown size="sm" :text="$t('language')" right>
              <b-dropdown-item v-for="lang in langs" :key="lang.name" :value="lang.name" @click="changeLang(lang)">
                <span :class="`fi fi-${lang.flag} fis`"></span>
                {{ $t(lang.name) }}
              </b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item-dropdown v-if="storeUser" size="sm" right>
              <template #button-content>
                <b-avatar size="sm"
                :src="storeUser.image"
                ></b-avatar>
                {{ storeUser.firstName }}
              </template>
              <b-dropdown-item>
                <router-link class="nav-link" to="/profile">
                  {{ $t("profile") }}
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item class="nav-link" @click="$store.dispatch('logout')">
                <span class="text-danger">{{ $t("logout") }}</span>
              </b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item v-else>
              <b-button to="/login" variant="outline-secondary" class="mr-2 font-weight-bold" size="sm">
                {{ $t("login") }}
              </b-button>
            </b-nav-item>
          </b-navbar-nav>
        </div>
      </b-collapse>
    </b-navbar>
  </div>
</template>
  
<script>
export default {
  name: "navbar-component",
  data: () => ({
    langs: [
      { name: "en", flag: "us" },
      { name: "kz", flag: "kz" },
      { name: "ru", flag: "ru" },
    ],
  }),

  mounted() {
    const lang = localStorage.getItem("lang");
    if (lang) {
      this.$i18n.locale = lang;
      this.$store.commit("setLocale", lang);
    }
  },

  methods: {
    changeLang(lang) {
      localStorage.setItem("lang", lang.name);
      this.$i18n.locale = lang.name;
      this.$store.commit("setLocale", lang.name);
    },
  },

  computed: {
    storeUser() {
      return this.$store.state.authStore.user;
    },
  },
};
</script>

<style scoped lang="scss">
nav a {
  font-weight: normal !important;
}
</style>