import axios from 'axios'

export function getAxiosInstance() {
  let instance = axios.create({
    baseURL: process.env.baseURL || process.env.apiUrl || 'https://uniqcars.herokuapp.com',
    headers: {
      'Content-Type': 'application/json'
    }
  })

  instance.interceptors.request.use(
    config => {
      const token = localStorage.getItem('token')
      if (token) {
        config.headers.Authorization = `Bearer ${token}`
      } else {
        // add basic http auth headers
        config.headers = {
          ...config.headers,
          Authorization: 'Basic ' + btoa(`${BASIC_HTTP_AUTH.username}:${BASIC_HTTP_AUTH.password}`),
        }
      }
      return config
    }
  )

  instance.interceptors.response.use(
    response => {
      return response
    },
    error => {
      if (error.response.status === 401 && localStorage.getItem('token')) {
        localStorage.removeItem('token')
        window.location.reload()
      }

      return Promise.reject(error)
    }
  )

  return instance
}

const instance = getAxiosInstance()

const BASIC_HTTP_AUTH = {
  username: 'client',
  password: 'secret'
}

export const CARS_LIST_URL = '/rest/v2/services/uniqcars_IntegrationService/getCars'
export const GET_CAR_URL = '/rest/v2/services/uniqcars_IntegrationService/getCarById'
export const AUTH_TOKEN_URL = '/rest/v2/oauth/token'
export const AUTH_SEND_OTP_CODE_URL = '/rest/v2/services/uniqcars_IntegrationService/sendOtpCode'
export const AUTH_RENEW_PASSWORD_URL = '/rest/v2/services/uniqcars_IntegrationService/renewPassword'
export const GET_USER_URL = '/rest/v2/services/uniqcars_IntegrationService/getUser'
export const UPDATE_USER_URL = '/rest/v2/services/uniqcars_IntegrationService/updateUser'
export const ADD_CARD_URL = '/rest/v2/services/uniqcars_IntegrationService/createBankCard'
export const REMOVE_CARD_URL = '/rest/v2/services/uniqcars_IntegrationService/removeBankCard'
export const GET_PARTNERS_URL = '/rest/v2/services/uniqcars_IntegrationService/getPartners'
export const GET_POPULAR_CARS_URL = '/rest/v2/services/uniqcars_IntegrationService/getFavCars'
export const UPLOAD_AVATAR_URL = '/rest/v2/files'
export const GET_PARTNER_BY_ID_URL = 'rest/v2/services/uniqcars_IntegrationService/getPartnerById'
export const CREATE_ORDER_URL = '/rest/v2/services/uniqcars_IntegrationService/createOrder'
export const CHECK_ORDER_URL = '/rest/v2/services/uniqcars_IntegrationService/checkOrder'
export const CONTACT_US_URL = '/rest/v2/services/uniqcars_IntegrationService/sendEmail'

export function getCarById(id) {
  return instance.get(GET_CAR_URL + '?carId=' + id + '&locale=' + localStorage.getItem('lang') || 'kz')
}

export function getPartnerById(id) {
  return instance.get(GET_PARTNER_BY_ID_URL + '?partnerId=' + id + '&locale=' + localStorage.getItem('lang') || 'kz')
}
