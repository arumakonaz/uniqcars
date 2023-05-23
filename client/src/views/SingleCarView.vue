<template>
  <div class="container bg-primary font-weight-light text-light">
    <div class="row">
      <div class="col desc-item">
        <div class="row">
          <div class="col text-center mt-3">
            <h2>{{ car.name }}</h2>
          </div>
        </div>
      </div>
    </div>
    <hr />
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-12">
        <template v-if="loading || !currentImage">
          <div class="main-image">
            <b-skeleton-img></b-skeleton-img>
          </div>
        </template>
        <template v-else>
          <div class="w-100 text-center">
            <b-img
              class="main-image"
              rounded
              :src="currentImage.link.toString()"
              :alt="car.name"
              @click="showImageModal"
            ></b-img>
          </div>
          <div class="image-list-container m-auto mb-3">
            <div class="image-list-items mt-4">
              <b-img
                v-for="(image, index) in car.images"
                rounded
                :key="image.id"
                :src="image.link"
                :class="
                  'image-list m-1 border ' +
                  (index === imageIndex ? ' border-primary' : '')
                "
                @click="imageIndex = index"
              ></b-img>
            </div>
          </div>
        </template>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-12">
        <template v-if="loading">
          <b-skeleton class="mb-2" type="input"></b-skeleton>
          <b-skeleton animation="wave"></b-skeleton>
          <b-skeleton animation="wave"></b-skeleton>
          <b-skeleton animation="wave"></b-skeleton>
          <b-skeleton animation="wave"></b-skeleton>
        </template>
        <template v-else>
          <div class="row desc-item">
            <div class="col">
              <h5>₸{{ car.price }}</h5>
            </div>
            <div class="col">
              <b-button
                id="makeInvoiceButton"
                variant="info"
                class="float-right"
                :to="`/invoice/${car.id}`"
                block
                size="sm"
              >
                {{ $t("buy") }}
              </b-button>
              <b-checkbox
                v-model="useBonus"
                @change="toggleBonus"
                class="float-right"
                size="sm"
              >
              {{ $t('use_bonus') }}
              </b-checkbox>
            </div>
          </div>
          <div
            class="row my-1 desc-item"
            v-for="(value, key) in carDescriptionItems"
            :key="key"
          >
            <div class="col text-secondary notranslate">
              {{ $t(key) }}
            </div>
            <div class="col">
              {{ value }}
            </div>
          </div>
        </template>
      </div>
    </div>
    <hr />
    <p>
      {{ car.description }}
    </p>
    <hr />
    <div class="table-responsive pb-5">
      <table
        class="table table-striped table-bordered mt-5 table-dark"
        v-for="item in carAdditionalData"
        :key="item.id"
      >
        <tbody>
          <tr class="">
            <th colspan="2">
              {{ item.name }}
            </th>
          </tr>
          <tr>
            <td class="w-50">
              {{ item.value }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal
      ref="largeImageModal"
      size="xl"
      centered
      hide-footer
      :title="car.name"
    >
      <b-carousel id="largeImageCarousel" controls fade>
        <b-carousel-slide v-for="item in car.images" :key="item.id">
          <template #img>
            <img
              :src="item.link"
              :alt="car.name"
              class="img-fluid w-100 d-block"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </b-modal>
  </div>
</template>

<script>
import { getCarById } from "@/utils/api";
import blankCar from "@/assets/blankCar.jpg";

export default {
  name: "SingleCarView",
  data: () => ({
    car: {},
    loading: false,
    imageIndex: 0,
    useBonus: false,
  }),
  mounted() {
    this.getCar();
    document.addEventListener("keydown", this.handleKeyDown);
  },
  computed: {
    carId() {
      return this.$route.params.id;
    },

    carDescriptionItems() {
      return {
        chasisType: this.car.chasisType ? this.$t(this.car.chasisType.toLowerCase()) : "",
        location: this.car.location,
        mileage: this.car.mileage,
      };
    },

    carAdditionalData() {
      let data = this.car.additionalData;
      return data ? data.sort((a, b) => a.order - b.order) : [];
    },

    currentImage() {
      return this.car && this.car.images && this.car.images.length
        ? this.car.images[this.imageIndex]
        : blankCar;
    }
  },
  methods: {
    toggleBonus() {
      localStorage.setItem("useBonus", this.useBonus);
    },
    getCar() {
      this.loading = true;
      console.log(this.$route.params.id)
      getCarById(this.$route.params.id)
        .then((response) => {
          this.car = response.data;
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.loading = false;
        });
    },

    showImageModal() {
      if (window.matchMedia("only screen and (max-width: 760px)").matches) {
        return;
      }

      this.$refs.largeImageModal.show();
    },
    handleKeyDown(event) {
      if (this.car) {
        if (event.keyCode === 39) {
          this.imageIndex++;
          if (this.imageIndex >= this.car.images.length) {
            this.imageIndex = 0;
          }
        }

        if (event.keyCode === 37) {
          this.imageIndex--;
          if (this.imageIndex < 0) {
            this.imageIndex = this.car.images.length - 1;
          }
        }
      }
    },
  },
};
</script>

<style scoped lang="scss">
.main-image {
  max-width: 100%;
  cursor: pointer;
}

.desc-item {
  @media (max-width: 768px) {
    margin: auto;
  }
}

.image-list {
  cursor: pointer;
  max-height: 73px;
  max-width: 73px;
}

.image-list-container {
  max-width: 100%;
}

.image-list-items {
  display: flex;
  flex-wrap: wrap;
  justify-content: start;
  align-content: start;

  @media (max-width: 768px) {
    display: flex !important;
    overflow-x: auto !important;
    scrollbar-width: none !important;
    &::-webkit-scrollbar {
      display: none !important;
    }
    flex-wrap: nowrap !important;
  }
}

</style>