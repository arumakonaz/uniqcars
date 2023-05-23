<template>
    <div class="card mx-3 my-3 bg-dark border border-secondary rounded" style="width: 18rem">
      <b-skeleton-img
        v-if="loading"
        class="card-img-top"
        height="150px"
      />
      <div 
        v-else
        class="card-img-top"
        :style="{
            'background-image': 'url(' + image + ')',
            'background-size': 'cover',
            'background-position': 'center',
            'background-repeat': 'no-repeat',                              
            'height': '150px',
        }"
      ></div>
      <div class="card-body d-flex flex-column justify-content-between align-items-center">
        <h5 class="card-title text-center">{{ car.name }}</h5>
        <div class="card-text mt-auto">
          <span class="text-center">
            <h5>
              <span class="text-primary my-1 text-info">
                ₸{{ car.price }}
              </span>
            </h5>
          </span>
          <br/>
          <div class="text-light mb-2">
            {{ car.location }}, {{ car.mileage }} km
          </div>
        </div>
        <b-link block class="btn btn-outline-info block w-100 notranslate" variant="outline-primary" :to="`/cars/${car.id}`" target="_blank">
          {{ $t('more_info') }}
        </b-link>
      </div>
    </div>
  </template>
  
  <script>

import blankCar from "@/assets/blankCar.jpg"
  
  export default {
    name: 'CarItemComponent',
    props: {
      car: {
        type: Object,
        required: true
      }
    },

    data: () => {
      return {
        image: blankCar,
        loading: true
      }
    },

    mounted() {
      if (this.car.images && this.car.images.length > 0) {
        this.image = this.car.images[0].link;
      }
      this.loading = false;
    }

  }
  </script>
  
  <style scoped lang="scss">
    .card-center-element {
      min-height: 40%;
      margin-top: auto;
    }
  </style>
  