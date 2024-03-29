# MyTodo-rear

2023年秋季学期《智能移动平台应用开发》课程PJ的后端

## 技术栈

- Java 17
- Spring Boot 3
- MySQL 8.0
- 数据库操作：Jpa
- 日志：Slf4j

# 开发指南

## 数据库配置

方式一：运行 `src/main/resources/init.sql` 文件

方式二：手动运行下面的 SQL 语句

```shell
mysql> CREATE DATABASE my_todo;
mysql> CREATE USER 'my_todo_admin'@'%' IDENTIFIED BY 'password123';
mysql> GRANT ALL ON my_todo.* TO 'my_todo_admin'@'%';
mysql> FLUSH PRIVILEGES;
mysql> quit
```

# 使用docker与docker-compose进行部署

## 使用docker-compose部署mysql

启动MySQL-Docker

```Shell
cd mysql-docker
docker-compose up -d
```

## 后端docker镜像打包

1. 修改配置文件`src\main\resources\application.properties`，启用prod-docker配置

```properties
spring.profiles.active=prod
```

2. 打包jar：maven-->MyTodo-->生命周期-->package （如果测试无法通过，可能需要使用`@Disabled`注解禁用测试）

3. 构建docker镜像

```shell
docker build -t mytodo-rear:1.0.0 .
```

## 使用docker部署后端

```shell
docker run -d \
--add-host=host.docker.internal:host-gateway \
--name mytodo-rear \
-p 8788:8787 \
-e SERVER_PORT=8787 \
-e MYSQL_HOST=host.docker.internal \
-e MYSQL_PORT=9003 \
-e MYSQL_USER_NAME=my_todo_admin \
-e MYSQL_USER_PASSWORD=password123 \
mytodo-rear:1.0.0 
```

- `-d` :后台运行容器，并返回容器ID
- `-add-host=host.docker.internal:host-gateway` : 添加一个自定义的主机到容器中，将容器内部的"host.docker.internal"
  解析为Docker宿主机的IP地址
- `--name` :指定容器名
- `-p` :指定端口映射
- `-e` :指定环境变量，环境变量配置参考下面的表格；
    - `host.docker.internal`是Docker Desktop中的一个特殊域名，用于在容器内访问宿主机的地址
- 最后的`mytodo-rear:1.0.0`指定镜像

### 环境变量配置（见配置文件`application-prod-docker.properties`）

| 变量名                   | 说明         | 默认值         |
|-----------------------|------------|-------------|
| `SERVER_PORT`         | 服务端口       | `8787`      |
| `MYSQL_HOST`          | MySQL 主机地址 | `localhost` |
| `MYSQL_PORT`          | MySQL 端口   | `3306`      |
| `MYSQL_USER_NAME`     | MySQL 用户名  | `root`      |
| `MYSQL_USER_PASSWORD` | MySQL 密码   | `root`      |
| `MYSQL_DATABASE`      | MySQL 数据库名 | `my_todo`   |