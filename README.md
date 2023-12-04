# MyTodo-rear

2023年秋季学期《智能移动平台应用开发》课程PJ的后端

## 运行指南

### 数据库配置

方式一：运行 `src/main/resources/init.sql` 文件

方式二：手动运行下面的 SQL 语句

```shell
mysql> CREATE DATABASE my_todo;
mysql> CREATE USER 'my_todo_admin'@'%' IDENTIFIED BY 'password123';
mysql> GRANT ALL ON my_todo.* TO 'my_todo_admin'@'%';
mysql> FLUSH PRIVILEGES;
mysql> quit
```