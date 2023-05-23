<template>
  <div v-if="!loading">
    <div class="text-light container-fluid" :style="{
      backgroundImage: 'linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url(' + partner.image + ')',
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      backgroundRepeat: 'no-repeat',
      minHeight: '60vh',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
    }">
      <div class="container-fluid">
        <div class="row text-uppercase">
          <div class="col">
          </div>
          <div class="col ml-auto">
            <h1>
              {{ $t('welcome') }}
            </h1>
            <h1>
              {{ partner.name }} {{ $t('center') }} {{ partner.location }}
            </h1>
          </div>
        </div>
      </div>
    </div>
    <div class="mb-5">
      <h1 class="text-uppercase text-light text-center m-5">{{ $t('model_range') }}</h1>
      <div class="slider mt-5" id="slider">
        <carousel id="carousel-1" :autoplay="false" :autoplayTimeout="4000" autoplayHoverPause :perPage="3" controls fade
          :paginationEnabled="false" navigationEnabled navigationNextLabel="❯" navigationPrevLabel="❮">
          <slide v-for="item in partner.modelRangeList" :key="item.id" class="p-3" :style="{
            'background-image':
              `linear-gradient(
                                          0deg, 
                                          rgba(0,0,0,0.6474964985994398) 20%, 
                                          rgba(29,29,29,0) 
                                          100%
                                        ),
                    url(${item.image ? item.image : blankCar})`,
  'background-size': 'cover',
  'background-position': 'center',
  'background-repeat': 'no-repeat',
  height: '500px',
}">
            <div class="w-100 h-100 d-flex align-items-start justify-content-start flex-column">
              <h3 class="text-light mr-3">
                {{ item.name }}
              </h3>
              <h3 class="text-light mr-3">
                {{ item.price }} $
              </h3>
            </div>
          </slide>
        </carousel>
      </div>
    </div>
    <div class="container mt-5">
      <h1 class="text-uppercase text-light text-center m-5">{{ $t('media') }}</h1>
      <div class="slider mt-5" id="slider-media">
        <carousel id="carousel-1" :autoplay="false" :autoplayTimeout="4000" autoplayHoverPause :perPage="3" controls fade
          :paginationEnabled="false" navigationEnabled navigationNextLabel="❯" navigationPrevLabel="❮">
          <slide v-for="item in partner.mediaList" :key="item.id" class="p-3">
            <div class="card bg-dark m-3" style="width: 18rem;">
              <img :src="item.image" class="card-img-top" />
              <div class="card-body text-center">
                <p class="mt-2 card-title text-white">
                  {{ $t(item.name) }}
                </p>
                <p class="m-0 mb-1 text-white">
                  <font-awesome-icon icon="location-dot" class="mr-2" size="1x" />
                  {{ item.location }}
                </p>
              </div>
            </div>
          </slide>
        </carousel>
      </div>
    </div>
  </div>
  <div v-else>
    <b-spinner class="mt-5" label="Loading..."></b-spinner>
  </div>
</template>

<script>
import { getPartnerById } from "@/utils/api";
import { Carousel, Slide } from "vue-carousel";

import { library } from '@fortawesome/fontawesome-svg-core'
import { faCalendar, faLocationDot } from '@fortawesome/free-solid-svg-icons'
library.add(faCalendar, faLocationDot)

export default {
  name: "SinglePartnerView",
  components: {
    Carousel,
    Slide,
  },
  data: () => ({
    partner: {},
    loading: false,
  }),
  mounted() {
    this.getPartner();
  },

  computed: {
    partnerId() {
      return this.$route.params.id;
    },
  },

  methods: {
    getPartner() {
      this.loading = true;
      getPartnerById(this.partnerId)
        .then((response) => {
          this.partner = response.data;
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  }
}

</script>

<style scoped lang="scss"></style>