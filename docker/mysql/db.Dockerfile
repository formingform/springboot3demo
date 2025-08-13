FROM docker.io/mysql 
 
LABEL joey="joey@matrixelements.com"
 
COPY ../../scripts/demo-init.sql /docker-entrypoint-initdb.d/xzbd.sql

 