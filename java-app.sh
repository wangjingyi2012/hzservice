#!/bin/bash

LOG_FILE="/webapp/java/logfile.log"

echo "Starting script execution" > "$LOG_FILE"

# 构建 Docker 镜像
docker buildx build -t hzschool:v1 -f /webapp/java/Dockerfile /webapp/java >> "$LOG_FILE" 2>&1
if [ $? -ne 0 ]; then
    echo "Docker build failed" >> "$LOG_FILE"
fi

# 停止正在运行的容器
docker stop hzschool >> "$LOG_FILE" 2>&1
if [ $? -ne 0 ]; then
    echo "Docker stop failed" >> "$LOG_FILE"
fi

# 删除已存在的容器
docker rm hzschool >> "$LOG_FILE" 2>&1
if [ $? -ne 0 ]; then
    echo "Docker rm failed" >> "$LOG_FILE"
fi

# 运行新的 Docker 容器
docker run -d -p 81:80 -v /webapp/images/uploads/:/webapp/images/uploads/ -e TZ=Asia/Shanghai --name hzschool hzschool:v1 >> "$LOG_FILE" 2>&1
if [ $? -ne 0 ]; then
    echo "Docker run failed" >> "$LOG_FILE"
fi

# 删除 JAR 包
rm -f hzservice-0.0.1-SNAPSHOT.jar >> "$LOG_FILE" 2>&1
if [ $? -ne 0 ]; then
    echo "File removal failed" >> "$LOG_FILE"
fi

echo "Script execution completed" >> "$LOG_FILE"
