FROM tiangolo/node-frontend:10 as build-stage

WORKDIR /app
COPY src/main/frontend/. /app
RUN npm install
RUN chmod a+x /app/node_modules/.bin/react-scripts
RUN npm run build

FROM nginx:1.15
COPY --from=build-stage /app/build/ /usr/share/nginx/html
COPY --from=build-stage nginx.conf /etc/nginx/conf.d/default.conf
