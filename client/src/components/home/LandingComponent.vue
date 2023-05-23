<template>
    <div class="landing full-vertical d-flex align-items-center justify-content-center">
        <div class="w-100 h-100">
            <div class="container align-self-center">
                <div class="col-12">
                    <h1 class="font-weight-bold text-center text-white ">{{ $t('landing_title') }}</h1>
                    <p class="lead text-white text-center">
                        {{ $t('landing_lead') }}
                    </p>
                    <div class="d-flex justify-content-center flex-row">
                        <b-button pill class="uniq-btn mr-3" to="/cars">{{ $t('search_cars') }}</b-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: "landing-component",
}
</script>

<style scoped lang="scss">
    .landing {
        background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('@/assets/landing.jfif');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
    }

</style>