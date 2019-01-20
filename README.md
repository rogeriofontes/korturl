# korturl

execute application with maven:

```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

execute application with docker:

```
package docker:build
docker run -p 8083:8083 -t korturl
```

find Url by Shorted key (Method GET):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X GET localhost:8083/api/v1/{key}
```

insert Url to change for short URL creation (Method POST):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"url":"http://www.xxxx.com"}' http://localhost:8083/api/v1
```

find Url statistics (Method GET):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X GET localhost:8083/api/v1/{key}
```

Create mysql database

```
sudo mysql --password
--
mysql> create database korturl; -- Create the new database
mysql> create user 'korturl'@'localhost' identified by 'admin'; -- Creates the user
mysql> grant all on db_example.* to 'korturl'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```

Call swagger Urls:

```
http://localhost:8083/swagger-ui.html#/url-resources
http://localhost:8083/v2/api-docs
```

Used frameworks
- Spring MVC
- Spring Data JPA
- Caffeine Cache
- HikariCP
- Swagger
- Flyway
- Lombok

Next steps - FrontEnd in Angular or React, JWT.