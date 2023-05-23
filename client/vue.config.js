const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  pwa: {
    name: "UNIQCARS",
    themeColor: "#98c1d9",
    icons: {
      favicon32: 'public/img/favicon-16x16.png',
      favicon16: 'public/img/favicon-32x32.png',
      appleTouchIcon: 'public/img/apple-touch-icon.png',
      maskIcon: 'img/safari-pinned-tab.svg',
      msTileImage: 'img/mstile-150x150.png'
    }
  }
})
