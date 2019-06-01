const proxy = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(proxy('/api/offers',
        {
            target: 'http://localhost:8080/',
            changeOrigin: true,
            // pathRewrite: {
            //     '^/api/': '/'
            // },
        }
    ))
};