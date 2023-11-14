FROM node:18.17.1-slim

WORKDIR /app
COPY . .

RUN yarn add moment
RUN yarn install
RUN yarn add react-router-dom
RUN npm install -g serve
RUN yarn build

EXPOSE 3000

CMD ["serve", "-s", "build"]
