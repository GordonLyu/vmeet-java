# VMeet

**Vmeet**是基于SpringCloud+vue3的社交平台网站。文件保存到服务端，可随时下载，用户的信息与文件不再丢失。

[vmeet-后端](https://github.com/GordonLyu/vmeet-java)

[vmeet-前端](https://github.com/GordonLyu/vmeet-vue)



## 技术栈

后端技术栈：SpringCloud、lombok、eureka、Gateway、OpenFeign、MySQL、Mybaits、redis、WebSocket、Sa-Token、Thymeleaf



## 模块与功能

**模块：**

- `eureka-server`：服务注册中心服务。
- `file-server`：管理文件的上传和下载。如头像上传，聊天文件上传与下载等，提供静态文件访问路径。
- `gateway`：对模块进行 API 的统一管理和过滤。
- `mail-server`：主要用于给用户发送邮箱验证码和邮箱通知，以及验证邮箱验证码。
- `socket-server`：主要处理 WebSocket 的业务。 
- `vmeet-common`：公共包。
- `vmeet-server`：主要业务服务模块，处理业务逻辑。



**已实现功能：**

​		**登录注册模块**：用户名及邮箱的登录注册、邮箱的发送与验证

​		**聊天模块**：实时通话（包括视频、文本和文件），文件的发送与接收下载（图片格式，视频格式、文件格式）

​		**联系人模块**：联系人互删、申请添加以及同意与拒绝添加等



**后续功能？：**

​		动态模块、后台（举报审核等）

  

## 配置

`mail-server`：邮箱配置

```yaml
# 路径：mail-server/src/resources/application.xml
spring:
  mail:
    host: smtp.qq.com # 邮箱host
    username: # 邮箱地址
    password: # 授权码
```



`file-server`：静态资源路径配置

```yaml
# 路径：file-server/src/main/resources/application.xml
file:
  path:
    root: # 静态资源存放路径
    avatar: # 用户头像存放路径
```



## 注意

- 在**未使用 `HTTPS` 协议或局域网连接**下，使用 chrome 内核较新的浏览器时，WebRTC 需要 HTTPS 协议才能使用，由于浏览器权限限制，使用 W ebRTC 的接口需要安全权限开启。

  解决步骤：进入  `chrome://flags/#unsafely-treat-inse cure-origin-as-secure`  启动该选项并输入访问的应用 URL 地址。



