<template>
  <div class="w-100 h-100 d-flex partners-bg">
    <div class="container-fluid align-self-center">
      <div class="col-12">
        <div class="container d-flex justify-content-start flex-column mb-5">
          <h4 class="text-white mr-3">Партнеры</h4>
          <div class="d-flex justify-content-around flex-wrap mb-5">
            <div
              class="card bg-dark parner-card m-3"
              v-for="item in partners"
              :key="item.title"
            >
              <div
                class="card-img-top"
                :style="{
                  'background-image': 'url(' + item.image + ')',
                  'background-size': 'cover',
                  'background-position': 'center',
                  'background-repeat': 'no-repeat',
                  height: '150px',
                }"
              ></div>
              <div class="card-body">
                <h3 
                class="mt-2 mb-4 font-weight-lighter card-title text-white hover-link"
                @click="() => { $router.push({ name: 'partner', params: { id: item.id } }) }"
                >
                  {{ item.name }}
                </h3>
                <p class="m-0 mb-1 font-weight-lighter text-white">
                  <font-awesome-icon
                    icon="location-dot"
                    class="mr-2"
                    size="1x"
                  />
                  {{ item.location }}
                </p>
                <p class="m-0 mb-2 font-weight-lighter text-white">
                  <font-awesome-icon icon="calendar" class="mr-2" size="1x" />
                  {{ item.year }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { GET_PARTNERS_URL } from "@/utils/api";

import { library } from "@fortawesome/fontawesome-svg-core";
import { faCalendar, faLocationDot } from "@fortawesome/free-solid-svg-icons";

library.add(faCalendar, faLocationDot);

export default {
  name: "PartnersComponent",
  data: () => {
    return {
      loading: false,
      partners: [],
    };
  },

  mounted() {
    this.getPartners();
  },

  methods: {
    async getPartners() {
      this.loading = true;
      this.$http
        .get(GET_PARTNERS_URL + "?locale=" + this.$i18n.locale)
        .then((response) => {
          this.partners = response.data.partnerPojos;
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

<style scoped lang="scss">
.parner-card {
  width: 19rem;
}

.partners-bg {
  background: rgb(15, 15, 15);
  background: linear-gradient(0deg, rgb(24, 24, 24) 0%, rgba(0, 0, 0, 1) 100%);
}
</style>