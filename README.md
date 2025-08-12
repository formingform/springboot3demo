### database独立部署
1. 首先create好database, 并把user/password，设置到yml中
2. 编译项目，在linux上，项目根目录，执行：./gradle -x test build
3. 编译docker镜像，在linux上，项目根目录，执行：docker build  -f ./docker/web/Dockerfile -t springboot3demo .
4. 运行docker容器，在linux上，项目根目录，执行：docker run -p 8080:4080 springboot3demo
5. 打开浏览器，输入地址：http://ip:port/springboot3demo/api/user/1，测试是否成功


### database也用docker部署，使用docker-compose,运行应用容器，和database容器

