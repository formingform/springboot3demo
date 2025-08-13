
#使用openjdk 17作为基础镜像
FROM openjdk:17-jdk

#联系人
LABEL authors="joey@matrixelements.com"

#容器工作目录，后续的命令将在此目录中执行。如果不存在，则会创建
WORKDIR /app/lesson

#将本地编译的jar复制到容器中的 /app/lesson目录
#如果镜像已经打包并放到仓库，则不需要
COPY ../../build/libs/springboot3-demo-0.0.1-SNAPSHOT.jar springboot3demo.jar

#容器会监听 8080 端口。这只是文档性质，实际运行时需要用 docker run -p 参数做端口映射。
EXPOSE 8080

#定义容器启动时执行的命令，调用 java -jar app.jar 启动 Spring Boot 应用
#ENTRYPOINT ["java", "-jar", "demo.jar"]
ENTRYPOINT ["java", "-Xms1024m", "-Xmx1024m", "-Dspring.profiles.active=dev", "-jar", "./springboot3demo.jar"]