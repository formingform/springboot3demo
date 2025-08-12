FROM docker.io/mysql 
 
MAINTAINER gym "joey@matrixelements.com"
 
EXPOSE 3306

ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

# 数据库创建脚本
ENV INIT_SQL demo-init.sql
COPY ./scripts/demo-init.sql ./$INIT_SQL $AUTO_RUN_DIR/
RUN chmod 777 $AUTO_RUN_DIR/$INIT_SQL
 