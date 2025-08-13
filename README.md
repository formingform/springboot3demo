### database独立部署
1. 首先create好database, 并把user/password，设置到yml中
2. 编译项目，在linux上，项目根目录，执行：./gradle -x test build
3. 编译docker镜像，在linux上，项目根目录，执行：docker build  -f ./docker/web/Dockerfile -t springboot3demo .
4. 运行docker容器，在linux上，项目根目录，执行：docker run -p 8080:4080 springboot3demo
5. 打开浏览器，输入地址：http://ip:port/springboot3demo/api/user/1，测试是否成功


### database也用docker部署，使用docker-compose,运行应用容器，和database容器
1. 编译项目，在linux上，项目根目录，执行：./gradle -x test build
2. 确定数据库名称，用户名，密码，修改docker-compose/.evn文件，以及scripts/demo-init.sql
3. 在项目根目录执行：docker-compose up -f ./docker-compose/docker-compose.yml -d
4. 打开浏览器，输入地址：http://ip:port/springboot3demo/api/user/1，测试是否成功

以上两种方法，都需要首先编译出springboot应用的jar，当然有可以把这个编译过程，放入docker/web/Dockerfile中


### 其他常用命令：

##### 启动docker compose
```shell
docker-compose -f ./docker-compose/docker-compose.yml up -d
```
缺省情况下，docker-compose命令在项目根目录执行，此时会寻找同目录的docker-compose.yml文件，如果docker-compose.yml文件不在根目录或者文件名不同，则通过 -f 参数指定


##### 查看docker运行的容器
```shell
docker ps
```

##### 一步登录docker中mysql
```shell
docker exec -it MySqlDockerContainerName mysql -u 用户名 -p 密码
```

##### 多步登录docker中mysql
1. 登录运行MySQL的docker容器
```shell
docker exec -it MySqlDockerContainerName /bin/bash 
```
2. 登录数据库
```shell
mysql -u 用户名 -p
```
3. 显示数据库列表
```shell
show databases;
```
4. 操作业务数据库
```shell
use 数据库名;
show tables;
select * from 业务表;
```

##### 退出登录的容器
```shell
exit
```

##### 停止docker-compose服务
```shell
docker-compose -f docker-compose/docker-compose.yml down
```
这里 docker-compose/docker-compose.yml 是你的 docker compose 配置文件的名称。如果你使用的是默认的 docker-compose.yml 文件，则只需运行：
```shell
docker-compose down
```
这个命令会停止所有容器，并删除它们。如果你的 docker compose 文件中还定义了网络或卷（volumes），它们也会被删除。

##### 删除容器、网络和卷（可选）
如果你想要更彻底地清理环境，包括删除所有相关的容器、网络和卷，你可以使用以下命令：
```shell
docker-compose -f docker-compose-db.yml down --volumes --rmi all
```
这个命令会删除所有容器、网络、默认网络、卷以及镜像。其中：

--volumes 选项会删除所有声明的卷。

--rmi all 选项会删除所有镜像，包括那些没有被任何容器引用的镜像。

#####  清理未使用的数据（可选）
如果你想要清理所有未被任何容器使用的数据（例如孤立的卷和镜像），可以使用以下命令：
```shell
docker system prune
```

这会提示你确认操作，输入 y 后继续执行。

**注意：**

在执行这些操作之前，请确保你已经备份了任何重要数据，因为这些命令会永久删除数据。

对于生产环境，特别是在多人协作的项目中，直接删除容器和网络可能会引起问题。确保这是你想要的，或者你有替代方案来重新配置环境。

使用 --rmi all 要谨慎，因为它会删除所有未被任何容器使用的镜像，这可能会清理掉一些你可能还想保留的镜像。如果你只是想删除与当前 Compose 文件相关的镜像，可以使用 docker image prune 结合适当的过滤条件。

通过上述步骤，你可以完全停止并卸载一个使用 Docker Compose 管理的服务。


#####  查看docker-compose日志
```shell
docker-compose logs
```