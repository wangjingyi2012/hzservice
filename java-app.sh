rm -f hzservice-0.0.1-SNAPSHOT.jar
docker build -t hzschool:v1 .

# Check if the container is running and stop it if it is
if [ "$(docker ps -q -f name=hzschool)" ]; then
    docker stop hzschool
fi

# Check if the container exists (either running or stopped) and remove it if it does
if [ "$(docker ps -aq -f status=exited -f name=hzschool)" ]; then
    docker rm hzschool
fi

docker run -d -p 81:80 -v /webapp/images/uploads/:/webapp/images/uploads/ -e TZ=Asia/Shanghai --name hzschool hzschool:v1
