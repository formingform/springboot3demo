FROM docker.io/mysql 
 
MAINTAINER gym "joey@matrixelements.com"
 
COPY ./demo-init.sql /docker-entrypoint-initdb.d/xzbd.sql

 