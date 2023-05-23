import {
    getAxiosInstance,
    AUTH_TOKEN_URL,
    AUTH_SEND_OTP_CODE_URL,
    AUTH_RENEW_PASSWORD_URL,
    GET_USER_URL
} from '@/utils/api'

const state = () => ({
    token: null,
    user: null,
    locale: null,
})

const getters = {
    isAuthenticated: state => !!state.token,
    authStatus: state => state.status,
    user: state => state.user,
    locale: state => state.locale
}

const actions = {
    async getUser({ commit }) {
        const response = await getAxiosInstance().get(GET_USER_URL)
        commit('sendResetSuccess', response.data)
    },

    async login({ commit, state, dispatch }, user) {
        const response = await getAxiosInstance().post(AUTH_TOKEN_URL, {
            username: user.username,
            password: user.password,
            grant_type: 'password',
            'Accept-Language': state.locale ? state.locale : 'ru'
        }, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        const token = response.data.access_token
        localStorage.setItem('token', token)
        
        dispatch('getUser')
        commit('auth_success', token)
    },

    async sendResetPasswordEmail({ commit }, email) {
        const response = await getAxiosInstance()
            .get(`${AUTH_SEND_OTP_CODE_URL}?email=${email}`)
        if (response.data === false) {
            return Promise.reject(new Error('Email not found'))
        }

        localStorage.removeItem('token')
        commit('auth_success', null)

        commit('sendResetSuccess', {
            email: email
        })

        return Promise.resolve()
    },

    // eslint-disable-next-line no-unused-vars
    async sendRenewedPassword({ commit }, data) {
        const response = await getAxiosInstance()
            .post(AUTH_RENEW_PASSWORD_URL, {
                userPojo: {
                    email: data.email,
                    otpCode: data.otpCode,
                    password: data.password
                }
            })
        if (response.data === false) {
            return Promise.reject(new Error('Email not found'))
        }

        return Promise.resolve()
    },
    
    async logout({ commit }) {
        localStorage.removeItem('token')
        commit('logout')
        window.location = '/'
    }
}

const mutations = {
    auth_success(state, token) {
        state.token = token
    },
    logout(state) {
        state.token = null
    },
    setLocale(state, locale) {
        state.locale = locale
        localStorage.setItem('lang', locale)
    },
    sendResetSuccess(state, user) {
        state.user = user
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
