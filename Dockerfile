FROM nginx:latest
 
COPY default.conf /etc/seeandyougo-nginx/conf.d/default.conf
 
CMD ["nginx", "-g", "daemon off;"]