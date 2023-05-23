<template>
    <div class="container-fluid cars-view-container">
        <div v-if="loading">
            <b-skeleton class="mb-2" type="input"></b-skeleton>
            <b-skeleton animation="wave"></b-skeleton>
            <b-skeleton animation="wave"></b-skeleton>
            <b-skeleton animation="wave"></b-skeleton>
            <b-skeleton animation="wave"></b-skeleton>
        </div>
        <div class="d-flex justify-content-around flex-wrap mb-5" v-else>
            <div class="card bg-dark partner-card-item m-3" v-for="item in partners" :key="item.name">
                <div class="card-img-top" :style="{
                    'background-image': 'url(' + item.image + ')',
                    'background-size': 'cover',
                    'background-position': 'center',
                    'background-repeat': 'no-repeat',
                    'height': '150px',
                }"></div>
                <div class="card-body">
                    <h3 
                    class="mt-2 mb-4 font-weight-lighter card-title text-white hover-link"
                    @click="() => { $router.push({ name: 'partner', params: { id: item.id } }) }"
                    >
                        {{ item.name }}
                    </h3>
                    <p class="m-0 mb-1 font-weight-lighter text-white">
                        <font-awesome-icon icon="location-dot" class="mr-2" size="1x" />
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
</template>

<script>
import { GET_PARTNERS_URL } from "@/utils/api";

import { library } from '@fortawesome/fontawesome-svg-core'
import { faCalendar, faLocationDot } from '@fortawesome/free-solid-svg-icons'
library.add(faCalendar, faLocationDot)

export default {
    name: "partners-view",
    data: () => ({
        loading: false,
        partners: [],
    }),

    mounted() {
        this.getPartners();
    },

    methods: {
        async getPartners() {
            this.loading = true;
            this.$http
                .get(GET_PARTNERS_URL + "?locale=" + this.$i18n.locale)
                .then((response) => {
                    this.partners = response.data.partnerPojos
                })
                .catch((error) => {
                    console.log(error);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
    },
}

</script>

<style scoped lang="scss">
.partner-card-item {
    width: 300px;
    height: 300px;
    border-radius: 10px;
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}
</style>