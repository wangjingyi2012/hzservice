# 使用JDK1.8作为基础镜像
FROM openjdk:8-jre

# 指定作者信息
LABEL maintainer="wjy"
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

#WORKDIR /webapp/java
COPY hzservice-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 81

# 设置启动命令
CMD ["java", "-jar", "/app.jar"]