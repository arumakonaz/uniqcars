<template>
  <div class="login-register-form-container" v-if="!loading">
    <div class="container">
      <div class="row rounded font-weight-bold text-light pb-4">
        <div class="col-12 mt-5">
          <div class="bg-dark rounded text-light card">
            <div class="card-header border-secondary">
              <h3 class="text-info p-2">
                <font-awesome-icon
                :icon="['fa', 'bars']"
                class="text-info"
                size="lg"
              />
              {{ $t("profile") }}
              </h3>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <div class="row">
                    <div class="col-half m-3">
                      <b-avatar class="hover-link" square rounded size="7rem">
                        <img
                          v-if="user.image"
                          :src="user.image"
                          :alt="user.firstName"
                          class="img-fluid"
                          @click.prevent="showAvatarUploadModal = true"
                        />
                        <template v-else>
                          <font-awesome-icon
                            :icon="['fa', 'user']"
                            size="7x"
                            class="text-secondary"
                            @click.prevent="showAvatarUploadModal = true"
                          />
                        </template>
                      </b-avatar>
                    </div>
                    <div class="col-half m-3">
                      <p class="font-weight-bold">
                        {{ user.firstName }}
                        {{ user.lastName }}
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <div class="d-flex align-items-end justify-content-end">
                    <b-button
                      variant="outline-light font-weight-bold"
                      class="mt-3"
                      @click.prevent="showProfileEditModal = true"
                      >{{ $t("edit") }}</b-button
                    >
                  </div>
                </div>
              </div>
              <hr class="border-top border-secondary">
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <label>
                    <font-awesome-icon
                      :icon="['fa', 'coins']"
                      size="lg"
                    />
                    {{ $t("bonus") }}:
                  </label>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <p>{{ user.bonus ? user.bonus : 0 }}</p>
                </div>
              </div>
              <hr class="border-top border-secondary">
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <label>
                    <font-awesome-icon
                      :icon="['fa', 'envelope']"
                      size="lg"
                    />
                    {{ $t("email") }}:
                  </label>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <p>{{ user.email }}</p>
                </div>
              </div>
              <hr class="border-top border-secondary">
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <label>
                    <font-awesome-icon
                      :icon="['fa', 'phone']"
                      size="lg"
                    />
                    {{ $t("phone_number") }}:
                  </label>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                  <p>{{ user.phoneNumber }}</p>
                </div>
              </div>
            </div>
            <div class="card-footer border-secondary">
              <b-button
                variant="outline-danger font-weight-bold"
                class="mt-3"
                @click="logout"
                >{{ $t("logout") }}</b-button
              >
            </div>
          </div>
        </div>

        <div class="col-12 mt-5">
          <div class="bg-dark rounded text-light card">
            <div class="card-header border-secondary">
              <h3 class="text-info p-2">
                <font-awesome-icon
                :icon="['fa', 'credit-card']"
                class="text-info"
                size="lg"
              />
              {{ $t("your_bank_cards") }}
              </h3>
            </div>
            <div class="card-body d-flex justify-content-between flex-wrap">
              <div
                v-for="(card, index) in user.bankCards"
                :key="card.id"
                :class="
                  'font-weight-light bank-card bank-card-bg-' +
                  ((index % 2) + 1)
                "
              >
                <div>
                  <div class="d-flex flex-row justify-content-between">
                    <div class="card-pad">
                      <b-button
                        variant="outline-light"
                        @click.prevent="deleteCard(card.id)"
                        size="sm"
                      >
                        <font-awesome-icon
                          :icon="['fa', 'trash']"
                          class="text-white"
                          size="lg"
                        />
                      </b-button>
                    </div>
                    <div class="card-pad">
                      <div class="w-100 text-right">
                        <font-awesome-icon
                          :icon="['fa', 'wifi']"
                          class="text-white fa-rotate-90"
                          size="lg"
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div>
                  <div class="col-lg-12 col-md-12 col-sm-12">
                    <label class="text-dark">{{ $t("card_name") }}: </label>
                    <p>{{ card.nameOnCard }}</p>
                  </div>
                </div>
                <div class="bg-card-bottom">
                  <div class="col-lg-12 col-md-12 col-sm-12">
                    <label class="text-dark">{{ $t("card_number") }}</label>
                    <p>{{ card.number }}</p>
                  </div>
                </div>
              </div>
              <b-link
                class="bank-card font-weight-light border border-light"
                @click.prevent="showAddCardModal"
              >
                <div
                  class="h-100 d-flex flex-column justify-content-center align-items-center"
                >
                  <font-awesome-icon
                    :icon="['fa', 'square-plus']"
                    class="text-light text-center"
                    size="6x"
                  />
                  <div class="text-light">
                    <h3>{{ $t("add_card") }}</h3>
                  </div>
                </div>
              </b-link>
            </div>
          </div>
        </div>
      </div>
    </div>

    <b-modal
      id="upload-avatar-modal"
      :title="$t('upload_avatar')"
      :visible="showAvatarUploadModal"
      @ok="uploadAvatar"
      @cancel="showAvatarUploadModal = false"
      @hidden="showAvatarUploadModal = false"
      header-bg-variant="dark"
      header-text-variant="light"
      body-bg-variant="dark"
      body-text-variant="light"
      footer-bg-variant="dark"
      footer-text-variant="light"
      variant="info"
    >
      <b-form @submit="uploadAvatar" class="rounded">
        <fieldset class="my-2">
          <label class="text-white" for="avatar">{{ $t("avatar") }}</label>
          <b-input-group>
            <b-form-file
              id="avatar"
              v-model="avatar"
              accept="image/*"
              :placeholder="$t('choose_avatar')"
              :drop-placeholder="$t('drop_avatar_here')"
              @change="avatarChanged"
            ></b-form-file>
            <b-input-group-append>
              <b-button variant="outline-secondary" disabled>
                <font-awesome-icon
                  :icon="['fas', 'upload']"
                  class="text-white"
                  size="lg"
                />
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </fieldset>
      </b-form>

    </b-modal>

    <b-modal
      id="profile-edit-modal"
      :title="$t('edit_profile')"
      :visible="showProfileEditModal"
      @ok="updateProfile"
      @cancel="showProfileEditModal = false"
      @hidden="showProfileEditModal = false"
      header-bg-variant="dark"
      header-text-variant="light"
      body-bg-variant="dark"
      body-text-variant="light"
      footer-bg-variant="dark"
      footer-text-variant="light"
      variant="info"
      size="lg"
    >
      <b-form @submit="updateProfile" class="rounded">
        <fieldset class="my-2">
          <label class="text-white" for="email">{{ $t("email") }}</label>
          <b-input-group>
            <b-form-input
              type="email"
              class="bg-dark text-white border-1 border-secondary"
              id="email"
              :placeholder="$t('email')"
              required
              trum
              v-model="userEditForm.email"
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
          <label class="text-white" for="firstName">{{
            $t("firstName")
          }}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="firstName"
              :placeholder="$t('firstName')"
              required
              v-model="userEditForm.firstName"
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
          <label class="text-white" for="lastName">{{ $t("last_name") }}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="lastName"
              :placeholder="$t('lastName')"
              required
              v-model="userEditForm.lastName"
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
      </b-form>
    </b-modal>

    <b-modal
      id="profile-add-card-modal"
      :title="$t('add_card')"
      :visible="showProfileAddCardModal"
      @ok="addCard"
      @cancel="hideAddCardModal"
      @hidden="hideAddCardModal"
      variant="info"
      header-bg-variant="dark"
      header-text-variant="light"
      body-bg-variant="dark"
      body-text-variant="light"
      footer-bg-variant="dark"
      footer-text-variant="light"
      size="lg"
    >
      <b-form @submit="addCard" class="rounded">
        <fieldset class="my-2">
          <label class="text-white" for="number">{{ $t("card_number") }}</label>
          <b-input-group>
            <b-form-input
              class="bg-dark text-white border-1 border-secondary"
              id="number"
              required
              v-model="addCardForm.number"
              :placeholder="$t('card_number')"
              v-mask="'#### #### #### ####'"
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
          <label class="text-white" for="card_name">{{
            $t("card_name")
          }}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="card_name"
              :placeholder="$t('card_name')"
              required
              v-model="addCardForm.nameOnCard"
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
          <label class="text-white" for="expireDate">{{
            $t("expireDate")
          }}</label>
          <b-input-group>
            <b-form-input
              type="text"
              class="bg-dark text-white border-1 border-secondary"
              id="expireDate"
              :placeholder="$t('expireDate')"
              required
              v-model="addCardForm.expireDate"
              v-mask="'##/##'"
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
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import {
  GET_USER_URL,
  UPDATE_USER_URL,
  ADD_CARD_URL,
  REMOVE_CARD_URL,
  UPLOAD_AVATAR_URL
} from "@/utils/api";

import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faTrash,
  faWifi,
  faSquarePlus,
  faEnvelope,
  faUser,
  faBars,
  faPhone,
  faCreditCard,
  faUpload,
  faCoins,
} from "@fortawesome/free-solid-svg-icons";

library.add(faTrash, faWifi, faSquarePlus, faEnvelope, faUser, faBars, faPhone, faCreditCard, faUpload, faCoins);

export default {
  name: "profile-view",

  data: () => ({
    user: null,
    showProfileEditModal: false,
    showProfileAddCardModal: false,
    showAvatarUploadModal: false,
    loading: true,
    avatar: null,
    userEditForm: {
      firstName: "",
      lastName: "",
      email: "",
    },
    addCardForm: {
      number: "",
      nameOnCard: "",
      expireDate: "",
    },
  }),

  mounted() {
    this.getUserInfo();
  },

  methods: {
    getUserInfo() {
      this.loading = true;
      this.$http
        .get(GET_USER_URL)
        .then((response) => {
          this.user = response.data;
          this.userEditForm.firstName = this.user.firstName;
          this.userEditForm.lastName = this.user.lastName;
          this.userEditForm.email = this.user.email;
        })
        .catch((error) => {
          console.log(error);
          this.$store.dispatch("logout");
        })
        .finally(() => {
          this.loading = false;
        });
    },

    logout() {
      this.$store.dispatch("logout");
    },

    showAddCardModal() {
      this.showProfileAddCardModal = true;
    },

    updateProfile() {
      let userCopy = Object.assign({}, this.user);
      userCopy.firstName = this.userEditForm.firstName;
      userCopy.lastName = this.userEditForm.lastName;
      userCopy.email = this.userEditForm.email;

      this.$http
        .post(UPDATE_USER_URL, {
          userPojo: userCopy,
        })
        .then((response) => {
          if (response.data === false) {
            this.$bvToast.toast(this.$t("error"), {
              title: this.$t("error_editing_profile"),
              variant: "danger",
              solid: true,
            });
          } else {
            this.getUserInfo();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$bvToast.toast(this.$t("error"), {
            title: this.$t("error_editing_profile"),
            variant: "danger",
            solid: true,
          });
        });

      this.showProfileEditModal = false;
    },

    addCard() {
      let monthDayYear = this.addCardForm.expireDate.split("/");
      monthDayYear.splice(1, 0, "1");
      console.log(monthDayYear);
      let dateObject = new Date(monthDayYear);
      this.addCardForm.expireDate =
        dateObject.getFullYear() +
        "-" +
        (dateObject.getMonth() + 1) +
        "-" +
        dateObject.getDate() +
        " 00:00:00.000";

      this.$http
        .post(ADD_CARD_URL, {
          bankCardPojo: this.addCardForm,
        })
        .then((response) => {
          if (response.data === false) {
            this.$bvToast.toast(this.$t("error"), {
              title: this.$t("error_manipulating_card"),
              variant: "danger",
              solid: true,
            });
          } else {
            this.getUserInfo();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$bvToast.toast(this.$t("error"), {
            title: this.$t("error_manipulating_card"),
            variant: "danger",
            solid: true,
          });
        });
      this.showProfileAddCardModal = false;
    },

    deleteCard(cardId) {
      const card = this.user.bankCards.find((card) => card.id === cardId);
      this.$http
        .post(REMOVE_CARD_URL, {
          bankCardPojo: card,
        })
        .then((response) => {
          if (response.data === false) {
            this.$bvToast.toast(this.$t("error"), {
              title: this.$t("error_manipulating_card"),
              variant: "danger",
              solid: true,
            });
          } else {
            this.getUserInfo();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$bvToast.toast(this.$t("error"), {
            title: this.$t("error_manipulating_card"),
            variant: "danger",
            solid: true,
          });
        });
    },

    avatarChanged(event) {
      this.avatar = event.target.files[0];
    },

    uploadAvatar() {
      let formData = new FormData();
      formData.append("file", this.avatar);

      this.$http
        .post(UPLOAD_AVATAR_URL + `?name=avatar.jpg`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((response) => {
          if (response.data === false) {
            this.$bvToast.toast(this.$t("error"), {
              title: this.$t("error_uploading_avatar"),
              variant: "danger",
              solid: true,
            });
          } else {
            this.getUserInfo();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$bvToast.toast(this.$t("error"), {
            title: this.$t("error_uploading_avatar"),
            variant: "danger",
            solid: true,
          });
        });


      this.showAvatarUploadModal = false;
    },

    hideAddCardModal() {
      this.showProfileAddCardModal = false;
      this.addCardForm.number = "";
      this.addCardForm.nameOnCard = "";
      this.addCardForm.expireDate = "";
    },
  },
};
</script>

<style scoped lang="scss">
.bg-card-bottom {
  background: rgb(102, 61, 180);
}
.card-pad {
  padding-left: 2rem;
  padding-right: 2rem;
  padding-top: 1rem;
}
.responsive-pad {
  @media (max-width: 575.98px) {
    padding: 1rem;
  }

  @media (min-width: 576px) and (max-width: 767.98px) {
    padding: 4rem;
  }

  @media (min-width: 768px) {
    padding: 5rem;
  }
}
.bank-card {
  width: 400px;
  min-height: 180px;
  border-radius: 10px;
  margin-bottom: 10px;
  color: white;
  font-weight: bold;
}

.bank-card-bg-1 {
  background: rgb(238, 174, 202);
  background: radial-gradient(
    circle,
    rgba(238, 174, 202, 1) 0%,
    rgba(148, 187, 233, 1) 100%
  );
}

.bank-card-bg-2 {
  background: rgb(63, 94, 251);
  background: radial-gradient(
    circle,
    rgba(63, 94, 251, 1) 0%,
    rgba(252, 70, 107, 1) 100%
  );
}
</style>