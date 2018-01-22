Order Management Application CLI
===

Table of Contents
---
1. Overview
2. Set Environment Variables
3. Import Database Schema

---

Overview
---
<p>This is a small CLI application using Java and MySQL. I could have chosen almost any domain, but decided on order management due to the straight forward relationships. It took me just 3 days to get the application developed from front to end, which includes a db schema, multi-tier Java application with DAOs, Services, Domain objects, Controllers, and a CLI user interface. I plan on using this project to learn more technologies.</p>

Set Environment Variables
---
```
JDBC_DRIVER=com.mysql.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/tester
USER=root
PASSWORD=secret
```

Docker - Start Container
---
```
docker run --name mysql -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=secret mysql
```

Import Database Schema
---
```
docker cp path/to/schema.sql mysql:/path/to/destination
docker exec -it mysql bash
mysql -u root -p tester < /path/to/schema.sql
```