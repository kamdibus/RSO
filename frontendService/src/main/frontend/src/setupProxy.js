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
    app.use(proxy('/api/users',
        {
            target: 'http://localhost:8081/',
            changeOrigin: true,
        }
    ))
    app.use(proxy('/api/invoices',
        {
            target: 'http://localhost:8084/',
            changeOrigin: true,
        }
    ))
};