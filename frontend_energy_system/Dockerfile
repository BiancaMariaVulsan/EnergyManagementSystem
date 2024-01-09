FROM node:18.18.2 AS builder

WORKDIR /app
COPY package*.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.19

COPY ./nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/frontend_energy_system /usr/share/nginx/html
