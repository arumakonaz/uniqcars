<template>
    <div class="text-light container">
        <div v-html="policyHtml"></div>
    </div>
</template>

<script>

export default {
    computed: {
        policyHtml() {
            return this.$t('privacy_policy_html');
        }
    }
}
</script>