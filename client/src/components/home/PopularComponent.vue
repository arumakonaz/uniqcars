<template>
  <div class="w-100 h-100 d-flex my-5">
    <div class="container-fluid align-self-center" v-if="!loading">
      <div class="col-12">
        <div class="container d-flex justify-content-start flex-row mb-5">
          <h4 class="text-white mr-3">{{$t("popular")}}</h4>
          <b-button class="uniq-btn" to="/cars">{{$t("see_all")}}</b-button>
        </div>
        <div id="slider" class="slider">
          <carousel
            id="carousel-1"
            :autoplay="false"
            :autoplayTimeout="4000"
            autoplayHoverPause
            :perPage="1.4"
            controls
            fade
            :paginationEnabled="false"
            navigationEnabled
            navigationNextLabel="❯"
            navigationPrevLabel="❮"
          >
            <slide
              v-for="item in cars"
              :key="item.id"
              class="p-3 m-1 car-slide mr-5"
              :style="{
                'background-image': 
                `linear-gradient(
                  0deg, 
                  rgba(0,0,0,0.6474964985994398) 20%, 
                  rgba(29,29,29,0) 
                  100%
                ),
                url(${item.images.length > 0 ? item.images[0].link : blankCar})`,
                'background-size': 'cover',
                'background-position': 'center',
                'background-repeat': 'no-repeat',
                height: '300px',
              }"
            >
              <div
                class="w-100 h-100 d-flex align-items-end justify-content-end"
              >
                <h3
                class="font-weight-bolder text-light mr-3 hover-link"
                @click="() => $router.push(`/cars/${item.id}`)"
                >
                  {{ `${getBrandName(item.name)} ${getModelName(item.name)}` }}
                </h3>
              </div>
            </slide>
          </carousel>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { GET_POPULAR_CARS_URL } from "@/utils/api";
import blankCar from "@/assets/blankCar.jpg";

import { Carousel, Slide } from "vue-carousel";

export default {
  name: "PopularComponent",
  components: {
    Carousel,
    Slide,
  },
  data: () => {
    return {
      current: 0,
      direction: 1,
      transitionName: "fade",
      loading: false,
      cars: [],
      blankCar,
    };
  },
  mounted() {
    this.getPopularCars();
  },
  methods: {
    slide(dir) {
      this.direction = dir;
      dir === 1
        ? (this.transitionName = "slide-next")
        : (this.transitionName = "slide-prev");
      var len = this.slides.length;
      this.current = (this.current + (dir % len) + len) % len;
    },

    getBrandName(fullName) {
      return fullName ? fullName.split(" ")[0] : "";
    },

    getModelName(fullName) {
      return fullName ? fullName.split(" ").slice(1).join(" ") : "";
    },

    getPopularCars() {
      this.loading = true;
      this.$http
        .get(GET_POPULAR_CARS_URL + "?locale=" + this.$i18n.locale)
        .then((response) => {
          this.cars = response.data.cars;
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style lang="scss">
.car-slide {
  border-radius: 1.5rem;
}

.VueCarousel-navigation {
  max-width: 60% !important;

  button {
    color: white !important;
    width: 50px !important;
    height: 50px !important;
    border-radius: 50% !important;
    background-color: #1a1a1a !important;
    padding: 0px !important;
    margin: 0px 30px !important;
    transform: none !important;
  }
}
</style>