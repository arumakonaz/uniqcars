<template>
  <div class="container-fluid cars-view-container">
    <div class="container notranslate bg-dark text-light">
      <cars-filter-component @applyFilters="applyFilters" :loading="loading" />

      <div class="d-flex align-content-start justify-content-center flex-wrap">
        <div v-for="car in cars" :key="car.id">
          <car-item-component :car="car" />
        </div>
      </div>
      <template v-if="!noPagination && allPages && allPages.length > 0">
        <div class="d-flex w-100 align-content-center justify-content-center">
          <b-pagination
            pills
            v-model="currPage"
            :total-rows="paginationInfo.totalRows"
            :per-page="paginationInfo.perPage"
            :current-page="currPage"
            @change="changePage"
          ></b-pagination>
        </div>
      </template>
    </div>
  </div>
</template>
<script>
import { CARS_LIST_URL } from "@/utils/api";
import CarsFilterComponent from "@/components/carsFilter/CarsFilterComponent.vue";
import CarItemComponent from "@/components/carsView/CarItemComponent.vue";

export default {
  components: {
    CarsFilterComponent,
    CarItemComponent,
  },
  name: "cars-view",
  data: () => ({
    loading: false,
    cars: [],
    filterUrl: "",
    currPage: 1,
    paginationInfo: {
      totalRows: 1,
      perPage: 5,
      maxPages: 1
    },
    noPagination: false
  }),
  mounted() {
    this.getCars();
  },
  methods: {
    applyFilters(filterUrl) {
      this.filterUrl = filterUrl;
      this.$router.push({ query: { filter: filterUrl } });
      this.getCars();
    },

    changePage(page) {
      this.currPage = page;
      this.getCars();
    },

    getCars() {
      this.loading = true;
      let params = '?locale=' + this.$i18n.locale;
      params += "&page=" + this.currPage;
      params += this.filterUrl;
      console.log(this.currPage);

      this.$http
        .get(CARS_LIST_URL + params)
        .then((response) => {
          this.cars.splice(0, this.cars.length);
          this.cars.push(...response.data.cars);
          this.paginationInfo.totalRows = response.data.maxPage * this.paginationInfo.perPage;
          this.paginationInfo.maxPages = response.data.maxPage;
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
  computed: {
    allPages() {
      return [...Array(this.paginationInfo.maxPages).keys()].map(i => i + 1)
    }
  },
};
</script>

<style scoped lang="scss">
.cars-view-container {
    background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url("~@/assets/carView.webp");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    min-height: 100vh;
    background-attachment: fixed;
}
</style>