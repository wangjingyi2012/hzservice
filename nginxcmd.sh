docker stop my-nginx
docker rm my-nginx
docker run --name my-nginx -v /webapp/html:/usr/share/nginx/html -v /webapp/conf.d:/etc/nginx/conf.d -v /webapp/images/uploads/:/web/images/uploads/ -p 80:80 -d nginx