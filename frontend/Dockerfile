FROM node:18.17.1-slim

WORKDIR /app
COPY . .

RUN yarn add moment
RUN yarn install

EXPOSE 3000

CMD ["yarn", "start"]