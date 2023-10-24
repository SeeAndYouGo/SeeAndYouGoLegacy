FROM node:18.17.1-slim

WORKDIR /app
COPY . .

RUN yarn add moment
RUN yarn install
RUN yarn add react-router-dom

EXPOSE 3000

CMD ["yarn", "start"]