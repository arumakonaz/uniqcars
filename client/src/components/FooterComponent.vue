<template>
  <div class="container-fluid my-5">
    <div class="container">
        <div class="row mx-2">
            <h1 class="font-weight-lighter text-light">UNIQCARS</h1>
        </div>
        <div class="row mx-2">
            <b-link to="/privacy-policy" class="font-weight-lighter text-light">
                {{ $t('privacy_policy') }}
            </b-link>
        </div>
        <div class="row mx-2">
            <b-link to="/user-agreement" class="font-weight-lighter text-light">
                {{ $t('user_agreement') }}
            </b-link>
            <b-link href="https://www.instagram.com/unique_cars/" class="ml-auto font-weight-lighter text-light">
                <font-awesome-icon :icon="['fab', 'instagram']" />
                unique_cars
            </b-link>
        </div>
        <hr class="border-top border-secondary"/>
        <div class="row mx-2 text-light">
            <div class="m-auto">
                Copyright © {{ new Date().getFullYear() }}. UNIQCARS. All rights reserved.
            </div>            
        </div>
    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faInstagram } from '@fortawesome/free-brands-svg-icons'

library.add(faInstagram)

export default {

}
</script>

<style>

</style>