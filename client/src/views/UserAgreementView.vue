<template>
    <div class="container text-light">
        <div v-html="userAgreementHtml"></div>
    </div>
</template>

<script>

export default {
    computed: {
        userAgreementHtml() {
            return this.$t('user_agreement_html');
        }
    }
}
</script>