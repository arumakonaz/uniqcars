<template>
  <div class="login-register-form-container">
    <div
      class="container w-100 h-100 d-flex align-contents-center justify-content-center"
    >
      <b-form class="login-register-form bg-dark rounded">
        <h1 class="text-white">{{$t('contact_us_title')}}</h1>
        <p class="mb-5 text-secondary">{{$t('contact_us_lead')}}</p>

        <fieldset class="my-2">
          <label class="text-white" for="topic">{{$t('contact_us_topic')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="topic"
              :placeholder="$t('contact_us_topic')"
              required
              trum
              v-model="topic"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'envelope']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="name">{{$t('first_name')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="name"
              :placeholder="$t('first_name')"
              required
              trum
              v-model="name"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'user']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="email">{{$t('email')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="email"
              :placeholder="$t('email')"
              required
              trum
              v-model="email"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'mail-bulk']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="phone">{{$t('phone_number')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="phone"
              :placeholder="$t('phone_number')"
              required
              trum
              v-model="phone"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'phone']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <fieldset class="my-2">
          <label class="text-white" for="text">{{$t('contact_us_message')}}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="text"
              :placeholder="$t('contact_us_message')"
              required
              trum
              v-model="text"
              size="lg"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'envelope-open-text']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
              
            </b-input-group-append>
          </b-input-group>
        </fieldset>

        <b-button @click="sendQuestion" class="mt-4" size="lg" block variant="primary">{{$t('contact_us_send')}}</b-button>

        <div class="mt-4 text-white text-center d-flex flex-column">
          <p>{{ $t('contact_center') }}</p>
          <a class="text-info" href="tel:+77076087180">+7 707 608 71 80</a>
          <a class="text-info" href="tel:+77076087180">+7 705 250 25 80</a>
          <a class="text-info" href="tel:+77076087180">+7 747 489 55 34</a>
          
          <p class="mt-2">{{ $t('daily_from_9_to_21') }}</p>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEnvelope, faUser, faPhone, faMailBulk, faEnvelopeOpenText } from '@fortawesome/free-solid-svg-icons'

library.add(faEnvelope, faUser, faPhone, faMailBulk, faEnvelopeOpenText)

import { CONTACT_US_URL } from '@/utils/api'

export default {
  name: "ContactsView",
  data: () => ({
    topic: "",
    name: "",
    email: "",
    phone: "",
    text: "",
  }),

  methods: {
    sendQuestion () {
      this.$http.post(CONTACT_US_URL, {
        emailPojo: {
          body: this.text,
          email: this.email,
          phone: this.phone, 
          name: this.name,
          from: this.email,
        }
      }).then(() => {
        this.$bvToast.toast(this.$t('contact_us_success'), {
          title: this.$t('success'),
          variant: 'success',
          solid: true
        })
      }).catch(() => {
        this.$bvToast.toast(this.$t('contact_us_error'), {
          title: this.$t('error'),
          variant: 'danger',
          solid: true
        })
      })
    }
  }
}

</script>