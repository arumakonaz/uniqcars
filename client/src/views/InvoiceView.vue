<template>
  <h1 class="text-center display-3 m-5 text-light">{{$t('redirecting_to_payment')}}</h1>
</template>

<script>
import { GET_USER_URL, CREATE_ORDER_URL } from "@/utils/api";

export default {
  name: "InvoiceView",
  mounted() {
    this.createInvoice();
  },
  methods: {
    createInvoice() {
      this.$http
        .get(GET_USER_URL)
        .then(() => {
          this.goToPayment()
        })
        .catch((error) => {
          console.log(error);
          localStorage.setItem("carToBuy", this.carId);
          this.$router.push({ name: "login" });
        })
        .finally(() => {
          this.loading = false;
        });
    },
    goToPayment() {
      const useBonus = localStorage.getItem('useBonus') === 'true'

      if (useBonus) {
        localStorage.removeItem('useBonus')
      }

      this.$http
        .get(CREATE_ORDER_URL + `?carId=${this.carId}&useBonus=${useBonus}`)
        .then((response) => {
          window.location.href = response.data;
        })
        .catch((error) => {
          console.log(error);
        })
    }
  },
  computed: {
    carId() {
      return this.$route.params.carId;
    },
    computed: {
      storeUser() {
        return this.$store.state.authStore.user;
      },
    },
  },
};
</script>
