<template>
  <div :class="'p-3 ' + (loading ? 'disabled' : '')">
    <b-collapse id="filter-collapse" visible>
      <div class="row my-2">
        <div class="col">
          <label class="notranslate">
            {{ $t("type_of_body") }}
          </label>
          <div
            class="d-flex flex-wrap align-content-center justify-content-center"
          >
            <div
              :class="
                'd-flex flex-column text-center align-items-center chasis-item m-1 rounded border border-4 ' +
                (filters.chasisType == chasis.value
                  ? ' border-info'
                  : 'border-secondary')
              "
              v-for="chasis in chasisTypeOptions"
              :key="chasis.value"
              @click="changeChasis(chasis)"
            >
              <div v-if="loading">
                <b-skeleton-img
                  width="100px"
                  height="80px"
                  class="image-list m-1"
                >
                </b-skeleton-img>
                <b-skeleton width="100%"></b-skeleton>
              </div>
              <div v-else class="d-flex flex-column">
                <b-img
                  rounded
                  :src="chasis.image"
                  class="image-list m-1"
                ></b-img>
                <span>{{ chasis.text }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr class="border-top border-secondary" />
      <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
          <other-filters-component v-model="filters.otherFilters">
          </other-filters-component>
        </div>
      </div>
    </b-collapse>
    <hr class="border-top border-secondary" />
    <div class="row mt-2">
      <div class="col-lg-12 col-md-12 col-sm-12">
        <b-button
          variant="outline-info"
          class="mr-3"
          @click="applyFilters"
        >
          {{ $t("search") }}
        </b-button>
        <b-button
          variant="outline-danger"
          @click="resetFilters"
          class="mr-3"
        >
          {{ $t("reset") }}
        </b-button>
        <b-button v-b-toggle.filter-collapse variant="outline-warning">{{
          $t("show_filter")
        }}</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import hardTop from "@/assets/carBodyFilter/cabrio.webp";
import coupe from "@/assets/carBodyFilter/coupe.webp";
import hatch from "@/assets/carBodyFilter/hatch.webp";
import liftback from "@/assets/carBodyFilter/liftback.webp";
import sedan from "@/assets/carBodyFilter/sedan.webp";
import softTop from "@/assets/carBodyFilter/softtop.webp";
import van from "@/assets/carBodyFilter/van.webp";
import wagon from "@/assets/carBodyFilter/wagon.webp";
import bus from "@/assets/carBodyFilter/bus.png";
import suv from "@/assets/carBodyFilter/suv.webp";
import pickup from "@/assets/carBodyFilter/pickup.webp";
import crossover from "@/assets/carBodyFilter/crossover.webp";
import OtherFiltersComponent from "@/components/carsFilter/OtherFiltersComponent.vue";

const OTHER_FILTERS_DEFAULT = {
  year_of_manufacture: {
    from: 1900,
    to: (new Date()).getFullYear(),
  },
  engine_volume: {
    from: 0,
    to: 9999,
  },
  engineType: "",
  sorting: "",
  price: {
    from: 0,
    to: 99999999,
  },
  milleage: {
    from: 0,
    to: 9999999,
  },
  gearbox: "",
};

export default {
  components: { OtherFiltersComponent },
  name: "cars-filter-component",

  props: {
    loading: {
      type: Boolean,
      default: true,
    },
  },

  data: () => ({
    filters: {
      chasisType: null,
      otherFilters: OTHER_FILTERS_DEFAULT,
    },
    filterUrl: "",
  }),

  mounted () {
    if (this.$route.query.filter) {
      this.filterUrl = this.$route.query.filter;

      const filters = this.$route.query.filter.split(',');

      for (const f of filters) {
        if (f.includes('displacementBetween')) {
          const displacement = f.split(':')[1].split('-');
          this.filters.otherFilters.engine_volume.from = displacement[0];
          this.filters.otherFilters.engine_volume.to = displacement[1];
        }

        if (f.includes('priceBetween')) {
          const price = f.split(':')[1].split('-');
          this.filters.otherFilters.price.from = price[0];
          this.filters.otherFilters.price.to = price[1];
        }

        if (f.includes('mileageBetween')) {
          const mileage = f.split(':')[1].split('-');
          this.filters.otherFilters.milleage.from = mileage[0];
          this.filters.otherFilters.milleage.to = mileage[1];
        }

        if (f.includes('yearBetween')) {
          const year = f.split(':')[1].split('-');
          this.filters.otherFilters.year_of_manufacture.from = year[0];
          this.filters.otherFilters.year_of_manufacture.to = year[1];
        }

        if (f.includes('transmission')) {
          console.log(f);
          this.filters.otherFilters.gearbox = f.split(':')[1];
        }

        if (f.includes('engineType')) {
          this.filters.otherFilters.engineType = f.split(':')[1];
        }

        if (f.includes('sorting')) {
          this.filters.otherFilters.sorting = f.split(':')[1];
        }

        if (f.includes('chassisType')) {
          this.filters.chasisType = f.split(':')[1];
        }
      }
    }
  },

  methods: {
    changeChasis(chasis) {
      this.filters.chasisType = chasis.value;
    },
    applyFilters() {
      this.makeFilterUrl();
      this.$emit("applyFilters", this.filterUrl);
    },
    resetFilters() {
      this.filters = {
        chasisType: null,
        otherFilters: OTHER_FILTERS_DEFAULT,
      };
      this.makeFilterUrl();
      this.$emit("applyFilters", "");
    },

    makeFilterUrl() {
      let url = 'filter='

      if (this.filters.otherFilters.engine_volume.from >= 0 && this.filters.otherFilters.engine_volume.to >= 0) {
        url += `displacementBetween:${this.filters.otherFilters.engine_volume.from}-${this.filters.otherFilters.engine_volume.to},`
      }

      if (this.filters.otherFilters.price.from >= 0 && this.filters.otherFilters.price.to >= 0) {
        url += `priceBetween:${this.filters.otherFilters.price.from}-${this.filters.otherFilters.price.to},`
      }

      if (this.filters.otherFilters.milleage.from >= 0 && this.filters.otherFilters.milleage.to >= 0) {
        url += `mileageBetween:${this.filters.otherFilters.milleage.from}-${this.filters.otherFilters.milleage.to},`
      }

      if (this.filters.otherFilters.year_of_manufacture.from >= 0 && this.filters.otherFilters.year_of_manufacture.to >= 0) {
        url += `yearBetween:${this.filters.otherFilters.year_of_manufacture.from}-${this.filters.otherFilters.year_of_manufacture.to},`
      }

      if (this.filters.otherFilters.gearbox) {
        url += `transmission:${this.filters.otherFilters.gearbox},`
      }

      if (this.filters.otherFilters.engineType) {
        url += `engineType:${this.filters.otherFilters.engineType},`
      }

      if (this.filters.otherFilters.sorting) {
        url += `${this.filters.otherFilters.sorting},`
      }

      if (this.filters.chasisType) {
        url += `chasisType:${this.filters.chasisType},`
      }

      this.filterUrl = url;
    }
  },

  computed: {
    chasisTypeOptions() {
      return [
        { value: "hatchback", text: this.$t("hatchback"), image: hatch },
        { value: "sedan", text: this.$t("sedan"), image: sedan },
        { value: "liftback", text: this.$t("liftback"), image: liftback },
        { value: "wagon", text: this.$t("wagon"), image: wagon },
        { value: "coupe", text: this.$t("coupe"), image: coupe },
        { value: "crossover", text: this.$t("crossover"), image: crossover },
        { value: "suv", text: this.$t("suv"), image: suv },
        { value: "pickup", text: this.$t("pickup"), image: pickup },
        { value: "bus", text: this.$t("bus"), image: bus },
        { value: "van", text: this.$t("van"), image: van },
        { value: "hardtop_convertible", text: this.$t("hardtop_convertible"), image: hardTop },
        { value: "soft_top_convertible", text: this.$t("soft_top_convertible"), image: softTop, },
      ];
    },
  },
};
</script>

<style scoped lang="scss">
.chasis-item {
  cursor: pointer;
  width: 150px;
}
.image-list {
  max-height: 100px;
  max-width: 100px;
}
</style>
