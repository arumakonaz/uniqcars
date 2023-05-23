<template>
  <div
    class="d-flex align-items-center justify-content-center vh-100 text-light"
  >
    <div class="text-center">
      <h1 class="display-1 fw-bold text-info">{{ $t('success') }}</h1>
      <p class="lead">
        {{ $t("invoice_success_lead") }}
      </p>
      <b-link to="/" class="btn btn-info">
        {{ $t("go_home") }}
      </b-link>
    </div>
  </div>
</template>

<script>
import { CHECK_ORDER_URL } from "@/utils/api";

export default {
  name: "ApprovedView",
  data() {
    return {
      success: false,
    };
  },
  mounted() {
    this.checkOrder();
  },
  methods: {
    checkOrder() {
      const token = this.$route.query.token;

      this.$http
        .get(
          CHECK_ORDER_URL +
            `?approveLink=https://www.paypal.com/checkoutnow?token=${token}`
        )
        .then(() => {
          this.success = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>