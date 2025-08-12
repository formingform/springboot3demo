FROM docker.io/mysql 
 
LABEL joey="joey@matrixelements.com"
 
COPY ./demo-init.sql /docker-entrypoint-initdb.d/xzbd.sql

 