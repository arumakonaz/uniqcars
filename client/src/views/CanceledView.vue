<template>
  <div
    class="d-flex align-items-center justify-content-center vh-100 text-light"
  >
    <div class="text-center">
      <h1 class="display-1 fw-bold text-danger">{{ $t('canceled') }}</h1>
      <p class="lead">
        {{ $t("invoice_canceled_lead") }}
      </p>
      <b-link to="/" class="btn btn-info">
        {{ $t("go_home") }}
      </b-link>
    </div>
  </div>
</template>
