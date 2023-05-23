<template>
  <div class="home">
    <landing-component/>
    <what-we-offer-component/>
    <popular-component/>
    <partners-component/>
  </div>
</template>

<script>
import LandingComponent from '@/components/home/LandingComponent.vue'
import WhatWeOfferComponent from '@/components/home/WhatWeOfferComponent.vue'
import PopularComponent from '@/components/home/PopularComponent.vue'
import PartnersComponent from '@/components/home/PartnersComponent.vue'

export default {
  name: 'HomeView',
  components: { 
    LandingComponent,
    WhatWeOfferComponent,
    PopularComponent,
    PartnersComponent
  }
}

</script>