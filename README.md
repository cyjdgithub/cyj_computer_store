# 电脑商城项目
# Computer Store Project

## 项目简介
## Project Introduction

这是一个简单的电脑商城系统，后端使用 **Spring Boot**、**MyBatis** 和 **MySQL**（通过 Navicat 连接），前端使用 **jQuery**、**AJAX** 和 **Bootstrap 3**。该系统允许用户浏览商品，进行用户注册、登录，并提供个人资料管理、购物车、订单生成等功能。

This is a simple computer store system. The backend uses **Spring Boot**, **MyBatis**, and **MySQL** (connected via Navicat), while the frontend uses **jQuery**, **AJAX**, and **Bootstrap 3**. This system allows users to browse products, register and log in, and provides personal profile management, shopping cart, and order generation features.

## 功能概述
## Features Overview

- **首页**：展示商城的产品信息。
- **index.html**: Displays the product information of the store.

- **登录与注册**：用户可以进行注册和登录操作。
- **Login and Registration**: Users can register and log in to the system.

- **个人资料管理**：
  - 修改个人资料。
  - 上传头像。
  - 更改密码。(MD5: salt+password+salt)
  - 地址管理。
  - **Personal Profile Management**:
    - Modify personal information.
    - Upload profile picture.
    - Change password.
    - Manage addresses.

- **购物车管理**：用户可以将商品添加到购物车并进行管理。
- **Shopping Cart Management**: Users can add products to the shopping cart and manage them.

- **订单管理**：支持用户生成订单，进行购物。
- **Order Management**: Users can generate orders and make purchases.

## 技术栈
## Technology Stack

- **后端**：Spring Boot、MyBatis、MySQL（通过 Navicat 连接）
- **Backend**: Spring Boot, MyBatis, MySQL (connected via Navicat)

- **前端**：jQuery、AJAX、Bootstrap 3
- **Frontend**: jQuery, AJAX, Bootstrap 3

## 项目结构
## Project Structure

- **后端**：负责处理业务逻辑，提供 API 接口供前端调用。
- **Backend**: Handles business logic and provides API endpoints for the frontend.

- **前端**：实现用户界面，采用 jQuery 和 AJAX 进行异步请求，Bootstrap 3 提供响应式布局。
- **Frontend**: Implements the user interface, using jQuery and AJAX for asynchronous requests, and Bootstrap 3 for responsive layout.

## 数据库
## Database

数据库使用 MySQL，SQL 脚本文件已随项目提供。你可以通过 Navicat 或其他 MySQL 客户端连接数据库并执行脚本文件来初始化数据库。
The database uses MySQL, and the SQL script file is provided with the project. You can connect to the database using Navicat or other MySQL clients and execute the script file to initialize the database.

## 运行步骤
## Running Steps

### 1. 配置数据库
### 1. Configure the Database

1. 在 MySQL 中创建一个新的数据库。
   - Create a new database in MySQL.

2. 使用 Navicat 或其他工具连接到数据库。
   - Connect to the database using Navicat or another tool.

3. 导入提供的 SQL 脚本文件，初始化数据库。
   - Import the provided SQL script file to initialize the database.

### 2. 配置应用
### 2. Configure the Application

- 修改 `application.properties` 或 `application.yml` 文件中的数据库连接配置，确保它与本地数据库匹配。
  - Modify the database connection configuration in the `application.properties` or `application.yml` file to match your local database.

### 3. 启动后端服务
### 3. Start the Backend Service

- 在 IDE 中打开项目，运行 `Spring Boot` 应用，或者通过命令行启动：
  ```bash
  mvn spring-boot:run

### 访问地址
### access address

可以是：
- [http://localhost:8989/index.html](http://localhost:8989/index.html)
- [http://localhost:8989/login.html](http://localhost:8989/login.html)
- [http://localhost:8989/register.html](http://localhost:8989/register.html)

注：8989是我自己设置的端口号

The access addresses might be:

- [http://localhost:8989/index.html](http://localhost:8989/index.html)
- [http://localhost:8989/login.html](http://localhost:8989/login.html)
- [http://localhost:8989/register.html](http://localhost:8989/register.html)

(Note: 8989 is the port number I have set.)

