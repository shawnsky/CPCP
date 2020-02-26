module.exports = {
	devServer: {
		proxy: {
			"/api": {
				target: "http://localhost:8081",
				changeOrigin: true,
				ws:true,
				pathRewrite: {
					"^/api": ""
				}
			}
		}
	},
	css: {
        loaderOptions: {
            less: {
                javascriptEnabled: true
            }
        }
    }
}
