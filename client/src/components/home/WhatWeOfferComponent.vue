<template>
  <div class="full-vertical d-flex align-items-center justify-content-center">
    <div class="w-100 h-100">
      <div class="container mt-auto">
        <div class="col-12">
          <h1 class="font-weight-bold text-center text-white mb-5">
            {{ $t('what_we_offer_title') }}
          </h1>
          <div class="d-flex justify-content-around flex-wrap mb-5">
            <div class="card bg-dark offer-card m-3" v-for="card in cards" :key="card.icon">
                <font-awesome-icon :icon="card.icon" class="card-icon" sixe="5x"/>
                <div class="card-body text-center">
                    <p class="mt-2 font-weight-lighter card-title text-white">
                      {{ $t(card.title) }}
                    </p>
                    <b-button block :to="card.link" variant="primary" class="card-btn">
                        {{ $t('learn_more') }}
                    </b-button>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCar, faGears, faGem } from '@fortawesome/free-solid-svg-icons'

library.add(faCar, faGears, faGem)

export default {
  name: "what-we-offer-component",

  data() {
    return {
      cards: [
        {
          icon: "fa-car",
          title: "rare_cars",
          link: "/accessories"
        },
        {
          icon: "fa-gem",
          title: "accessories",
          link: "/accessories"
        },
        {
          icon: "fa-gears",
          title: "spare_parts",
          link: "/spare-parts"
        }
      ]
    };
  }
};
</script>

<style scoped lang="scss">
.card-icon {
    color: #6D4EEA;
    height: 6rem;
    margin-top: 1rem;
}

.card-btn {
    color: #6D4EEA;
}

.offer-card {
  width: 18rem;
}
</style>