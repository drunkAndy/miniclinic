# MiniClinic

## 專案介紹

MiniClinic 是一個社區診所管理系統，提供：

* 病患線上掛號
* 醫師登入驗證
* 今日掛號查詢
* 掛號狀態管理
* 掛號取消功能
* 密碼修改功能

## 線上 Demo

部署完成後請填入：

https://your-render-url.onrender.com

## 技術棧

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Thymeleaf
* SQLite（Development）
* PostgreSQL（Production）
* Docker
* Render
* GitHub

## 本機執行方式

```bash
git clone https://github.com/drunkAndy/miniclinic.git

cd miniclinic

mvn spring-boot:run
```

## 預設帳密

醫師編號：

```text
D001
```

密碼：

```text
password123
```

（若資料不同請依 data.sql 內容修改）

## 作者

Andy Liu
