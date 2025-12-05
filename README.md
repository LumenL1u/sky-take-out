# Sky外卖平台

## 项目简介

Sky外卖平台是一个功能完备的在线订餐系统，支持商家管理和用户订餐两大核心功能。项目采用模块化设计，包含通用工具类、数据模型定义以及服务端逻辑处理等多个模块。

## 功能特性

- **员工管理**：支持员工登录、退出、信息管理等功能
- **菜品管理**：提供菜品分类、菜品信息维护、口味管理等功能
- **套餐管理**：支持套餐定义、菜品关联管理
- **订单处理**：涵盖订单状态管理、订单统计、订单提交与支付等完整流程
- **购物车系统**：支持多菜品/套餐的临时存储
- **用户系统**：包括用户登录、地址簿管理等功能
- **数据统计**：提供营业额统计、订单完成率、热销商品Top10等数据可视化功能
- **支付集成**：集成微信支付功能
- **文件存储**：支持阿里云OSS文件上传
- **WebSocket通信**：支持实时消息推送（订单提醒等）

## 项目架构

### 模块结构

```
sky-take-out/
├── nginx-1.20.2/          # Nginx反向代理服务器
│   ├── conf/              # Nginx配置文件
│   ├── html/              # 前端静态资源
│   └── nginx.exe          # Nginx可执行文件
│
├── sky-common/            # 通用工具模块
│   ├── constant/          # 常量定义
│   ├── exception/         # 异常处理
│   ├── result/            # 返回结果
│   └── utils/             # 工具类      
│
├── sky-pojo/              # 数据对象模块
│   ├── dto/               # 数据传输对象
│   ├── entity/            # 实体类
│   └── vo/                # 视图对象
│
├── sky-server/            # 核心业务模块
│   ├── controller/        # 控制器层
│   ├── service/           # 服务层
│   ├── mapper/            # 数据访问层
│   └── resources/         # 配置文件
│
└── sql/                   # 数据库脚本
    ├── sky.sql            # 完整数据库初始化脚本
    └── employee.sql       # 员工表初始化脚本
```

### 技术栈

**后端框架**

- Spring Boot 2.7.3
- MyBatis Plus 3.5.14
- Spring AOP (AspectJ)

**数据库**

- MySQL 8.0+
- Druid 连接池

**安全认证**

- JWT (JSON Web Token)
- 自定义拦截器

**接口文档**

- Knife4j 4.4.0
- Swagger/OpenAPI 3.0

**第三方服务**

- 微信支付 API v3
- 阿里云 OSS 3.10.2

**工具库**

- Lombok 1.18.30
- FastJSON 1.2.76
- Apache POI 3.16 (Excel导出)
- Commons Lang 2.6

**Web服务器**

- Nginx 1.20.2 (反向代理、负载均衡、静态资源服务)

## 环境要求

- **JDK**: 1.8 或以上
- **Maven**: 3.6 或以上
- **MySQL**: 8.0 或以上
- **Nginx**: 1.20.2 (项目已包含)
- **Redis**: 可选，用于缓存优化

## 快速开始

### 1. 克隆项目

```bash
git clone https://gitee.com/lumenl1u/sky-take-out.git
cd sky-take-out
```

### 2. 数据库配置

#### 2.1 创建数据库

```sql
CREATE DATABASE sky_take_out DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 2.2 导入初始化脚本

```bash
# 方式1：使用命令行
mysql -u root -p sky_take_out < sql/sky.sql

# 方式2：使用MySQL客户端工具（如Navicat、DataGrip等）
# 连接数据库后，执行 sql/sky.sql 文件
```

#### 2.3 修改数据库配置

编辑 `sky-server/src/main/resources/application-dev.yml`：

```yaml
sky:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    host: localhost         # 修改为你的MySQL地址
    port: 3306              # 修改为你的MySQL端口
    database: sky_take_out  # 数据库名称
    username: root          # 修改为你的MySQL用户名
    password: 123456        # 修改为你的MySQL密码
```

### 3. 配置第三方服务（可选）

如需使用完整功能，请在 `application-dev.yml` 中配置：

```yaml
sky:
  # 阿里云OSS配置
  alioss:
    endpoint: your-endpoint
    access-key-id: your-access-key-id
    access-key-secret: your-access-key-secret
    bucket-name: your-bucket-name

  # 微信支付配置
  wechat:
    appid: your-appid
    secret: your-secret
    mchid: your-mchid
    mch-serial-no: your-mch-serial-no
    private-key-file-path: your-private-key-path
    api-v3-key: your-api-v3-key
    we-chat-pay-cert-file-path: your-cert-path
    notify-url: your-notify-url
    refund-notify-url: your-refund-notify-url
```

### 4. 启动后端服务

1. 使用 IntelliJ IDEA 打开项目
2. 等待 Maven 依赖下载完成
3. 找到 `sky-server/src/main/java/com/sky/SkyApplication.java`
4. 右键运行 `SkyApplication.main()`

启动成功后，后端服务将运行在 `http://localhost:8080`

### 5. 启动Nginx服务器

#### Windows系统

```bash
# 进入nginx目录
cd nginx-1.20.2

# 启动nginx
start nginx.exe

# 或者双击 nginx.exe 文件启动
```

### 6. 访问系统

启动完成后，可以通过以下地址访问：

- **前端管理页面**: http://localhost
- **前端用户页面**: http://localhost/user
- **接口文档（Knife4j）**: http://localhost:8080/doc.html
- **接口文档（Swagger）**: http://localhost:8080/swagger-ui.html

### 7. 默认账号

**管理员账号** :

- 用户名: admin
- 密码: 123456

## 项目配置说明

### Nginx配置

Nginx配置文件位于 `nginx-1.20.2/conf/nginx.conf`，主要配置包括：

- **监听端口**: 80
- **静态资源**: `html/sky` 目录
- **反向代理**:
    - `/api/*` → 转发到后端管理接口 `http://localhost:8080/admin/`
    - `/user/*` → 转发到后端用户接口 `http://localhost:8080/user/`
    - `/ws/*` → WebSocket连接代理
- **负载均衡**: 支持配置多个后端服务器实例

### 应用配置

主配置文件：`sky-server/src/main/resources/application.yml`

- **服务端口**: 8080
- **日志级别**: mapper层debug，service和controller层info
- **JWT配置**:
    - 密钥: itcast
    - 过期时间: 24小时
    - Token名称: token

## API接口文档

项目集成了 Knife4j 和 Swagger，提供完善的接口文档：

### 访问方式

1. **Knife4j文档**（推荐）: http://localhost:8080/doc.html
2. **Swagger文档**: http://localhost:8080/swagger-ui.html

### 接口分组

- **管理端接口**: `/admin/**`
    - 员工管理
    - 菜品管理
    - 套餐管理
    - 订单管理
    - 数据统计等

- **用户端接口**: `/user/**`
    - 用户登录
    - 菜品浏览
    - 购物车
    - 订单提交
    - 地址管理等

### 主要接口示例

**员工登录**

```
POST /admin/employee/login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}
```

**用户登录**

```
POST /user/user/login
Content-Type: application/json

{
  "code": "微信授权码"
}
```

## 统一返回格式

所有接口统一返回JSON格式：

```json
{
  "code": 0,
  "message": "成功",
  "data": {}
}
```

### 状态码说明

- **0**: 操作成功
- **1**: 操作失败
- **2**: 未登录或登录过期

## 常见问题

### 1. 数据库连接失败

- 检查MySQL服务是否启动
- 确认 `application-dev.yml` 中的数据库配置是否正确
- 检查数据库用户权限

### 2. Nginx启动失败

- 检查80端口是否被占用
- 查看 `nginx-1.20.2/logs/error.log` 日志文件
- 确认nginx.conf配置文件语法是否正确

### 3. 前端页面无法访问

- 确认Nginx是否正常启动
- 检查 `nginx-1.20.2/html/sky` 目录是否存在前端文件
- 查看浏览器控制台是否有错误信息

### 4. 接口调用401未授权

- 检查是否已登录并获取token
- 确认请求头中是否携带了正确的token
- 检查token是否过期（默认24小时）

### 5. Maven依赖下载失败

- 配置国内Maven镜像源（阿里云）
- 检查网络连接
- 清理本地Maven仓库缓存

## 开发指南

### 项目结构说明

```
sky-server/src/main/java/com/sky/
├── config/          # 配置类（WebMvc、MyBatis、Redis等）
├── controller/      # 控制器
│   ├── admin/       # 管理端控制器
│   └── user/        # 用户端控制器
├── service/         # 业务逻辑层
│   └── impl/        # 业务实现类
├── mapper/          # 数据访问层
├── interceptor/     # 拦截器（JWT验证等）
├── handler/         # 全局异常处理器
├── aspect/          # AOP切面（日志、权限等）
└── task/            # 定时任务
```

### 代码规范

1. **命名规范**
    - 类名：大驼峰命名法（PascalCase）
    - 方法名/变量名：小驼峰命名法（camelCase）
    - 常量：全大写下划线分隔（UPPER_SNAKE_CASE）

2. **注释规范**
    - 类和方法必须添加JavaDoc注释
    - 复杂业务逻辑添加行内注释
    - 使用Swagger注解描述接口

3. **异常处理**
    - 使用全局异常处理器统一处理
    - 自定义业务异常继承BaseException
    - 避免捕获异常后不处理

## 部署说明

### 生产环境部署

1. **打包项目**

```bash
mvn clean package -DskipTests
```

2. **上传jar包**
   将 `sky-server/target/sky-server-1.0-SNAPSHOT.jar` 上传到服务器

3. **启动应用**

```bash
java -jar sky-server-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

4. **配置Nginx**

- 修改nginx.conf中的upstream配置
- 配置SSL证书（HTTPS）
- 设置静态资源缓存策略

5. **配置开机自启**
   使用systemd或其他服务管理工具配置自动启动

## 贡献指南

欢迎贡献代码！请遵循以下规范：

1. Fork项目并创建新分支
2. 遵循项目代码规范
3. 提交代码前确保单元测试通过
4. 保持代码风格一致
5. 提交PR时请详细描述修改内容
6. 确保没有引入新的警告或错误

## 许可证

本项目采用 Apache-2.0 许可证。

## 联系方式

如有问题或建议，欢迎通过以下方式联系：

- 提交 Issue: https://gitee.com/lumenl1u/sky-take-out/issues
- Pull Request: https://gitee.com/lumenl1u/sky-take-out/pulls

---

**注意**: 本项目仅供学习交流使用，请勿用于商业用途。