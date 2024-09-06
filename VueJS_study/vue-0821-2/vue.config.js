const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 아래 부분 추가
  devServer: {
    proxy: {
      '/v1': {
        target: "https://api.coindesk.com",
        changeOrigin: true
      },
      '/api': {
        target: "https://reqres.in",
        changeOrigin: true
      }
    }
  }
})
