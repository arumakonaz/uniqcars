<template>
  <div>
    <div key="filter1" class="mb-2">
      <div id="year_of_manufacture_group">
        <label for="year_of_manufacture_group">{{
          $t("year_of_manufacture")
        }}</label>
        <div class="row">
          <div class="col">
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('from')"
              aria-label="from"
              v-bind:value="value.year_of_manufacture.from"
              v-on:input="update('year_of_manufacture', 'from', $event)"
              type="number"
              min="1900"
              :max="new Date().getFullYear()"
            ></b-form-input>
          </div>
          <div class="col">
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('to')"
              aria-label="to"
              v-bind:value="value.year_of_manufacture.to"
              v-on:input="update('year_of_manufacture', 'to', $event)"
              type="number"
              min="1900"
              :max="new Date().getFullYear()"
            ></b-form-input>
          </div>
        </div>
      </div>
    </div>

    <div key="filter2" class="mb-2">
      <label for="price_group">{{ $t("price") }}</label>
      <div id="price_group" class="row">
        <div class="col">
          <b-input-group>
            <b-input-group-prepend>
              <b-button variant="outline-light" disabled>₸</b-button>
            </b-input-group-prepend>
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('from')"
              aria-label="from"
              v-bind:value="value.price.from"
              v-on:input="update('price', 'from', $event)"
              type="number"
              min="0"
            ></b-form-input>
          </b-input-group>
        </div>
        <div class="col">
          <b-input-group>
            <b-input-group-prepend>
              <b-button variant="outline-light" disabled>₸</b-button>
            </b-input-group-prepend>
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('to')"
              aria-label="to"
              v-bind:value="value.price.to"
              v-on:input="update('price', 'to', $event)"
              type="number"
              min="0"
            ></b-form-input>
          </b-input-group>
        </div>
      </div>
    </div>

    <div key="filter3" class="mb-2">
      <label for="milleage_group" class="notranslate">{{
        $t("milleage")
      }}</label>
      <div id="milleage_group" class="row">
        <div class="col">
          <b-form-input
            class="bg-dark text-white border-1 border-secondary"
            :placeholder="$t('from')"
            aria-label="from"
            v-bind:value="value.milleage.from"
            v-on:input="update('milleage', 'from', $event)"
            type="number"
            min="0"
          ></b-form-input>
        </div>
        <div class="col">
          <b-form-input
            class="bg-dark text-white border-1 border-secondary"
            :placeholder="$t('to')"
            aria-label="to"
            v-bind:value="value.milleage.to"
            v-on:input="update('milleage', 'to', $event)"
            type="number"
            min="0"
          ></b-form-input>
        </div>
      </div>
    </div>

    <div key="filter4" class="mb-2">
      <label for="gearbox_group" class="notranslate">{{ $t("gearbox") }}</label>
      <b-input-group id="gearbox_group" class="mb-2">
        <b-form-radio-group
          id="gearbox_group_radio"
          v-bind:value="value.gearbox"
          v-on:input="update('gearbox', null, $event)"
          :options="gearboxOptions"
          aria-describedby="gearbox"
        ></b-form-radio-group>
      </b-input-group>
    </div>

    <div key="filter5" class="mb-2">
      <div id="engine_volume_group">
        <label for="engine_volume_group" class="notranslate">{{
          $t("engine_volume")
        }}</label>
        <div class="row">
          <div class="col">
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('from')"
              aria-label="from"
              v-bind:value="value.engine_volume.from"
              v-on:input="update('engine_volume', 'from', $event)"
              type="number"
              min="0"
              max="10000"
            ></b-form-input>
          </div>
          <div class="col">
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              :placeholder="$t('to')"
              aria-label="to"
              v-bind:value="value.engine_volume.to"
              v-on:input="update('engine_volume', 'to', $event)"
              type="number"
              min="0"
              max="10000"
            ></b-form-input>
          </div>
        </div>
      </div>

      <div key="filter6" class="mb-2">
        <div id="engine_type_group">
          <label for="engine_type_group" class="notranslate">{{
            $t("engine_type")
          }}</label>
          <div>
            <b-form-select
              class="bg-dark text-white border-1 border-secondary"
              v-bind:value="value.engineType"
              v-on:input="update('engineType', null, $event)"
              plain
              :options="engineTypeOptions"
            ></b-form-select>
          </div>
        </div>
      </div>

      <div key="filter7" class="mb-2">
        <div id="sorting_group">
          <label for="sorting_group" class="notranslate">{{
            $t("sorting")
          }}</label>
          <div>
            <b-form-select
              class="bg-dark text-white border-1 border-secondary"
              v-bind:value="value.sorting"
              v-on:input="update('sorting', null, $event)"
              plain
              :options="sortingOptions"
            ></b-form-select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {
  name: "OtherFilters",

  props: {
    value: {
      type: Object,
      required: true,
    },
  },

  data() {
    return {
      isValid: true,
    };
  },

  methods: {
    update(key, subkey, value) {
      if (subkey) {
        this.$emit("input", {
          ...this.value,
          [key]: {
            ...this.value[key],
            [subkey]: value,
          },
        });
      } else {
        this.$emit("input", {
          ...this.value,
          [key]: value,
        });
      }
    },
  },

  computed: {
    gearboxOptions() {
      return [
        {
          text: this.$t("automatic"),
          value: "AUTO",
        },
        {
          text: this.$t("manual"),
          value: "MANUAL",
        },
      ];
    },
    engineTypeOptions() {
      return [
        { value: "", text: this.$t("select_an_option") },
        { value: "GASOLINE", text: this.$t("gasoline") },
        { value: "DIESEL", text: this.$t("diesel") },
        { value: "HYBRID", text: this.$t("hybrid") },
        { value: "ELECTRO", text: this.$t("electric") },
      ];
    },
    sortingOptions() {
      return [
        { value: "", text: this.$t("select_an_option") },
        // { value: "newer_first", text: this.$t("newly_published_asc") },
        { value: "priceSort=asc", text: this.$t("price_asc") },
        { value: "priceSort=desc", text: this.$t("price_desc") },
        { value: "yearOfManufactureSort=asc", text: this.$t("age_asc") },
        { value: "milleageSort=asc", text: this.$t("milleage_asc") },
      ];
    },
  },
};
</script>
  
  <style>
</style>
  