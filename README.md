# IAMDemo
Simple SpringBoot project that implements Identiy and Access Management features.

You should run iam_sql_queries.sql before staring up the application.

There are three types of users: **Admin, Writer and Approver.**

By default the query will create an Admin user with name "jacob". The password of the Admin user is be "fun123". Passwords are encoded intro BCrypt.

Log in as jacob. Once you are logged in, you can create new Writer or Approver from User Dashboard.

## Writer

**Writers** can write quotes and push it to the database. But these quotes will not be viewed in the homepage. Neither will they be returned on API calls.
This is because the quotes must be approved by **Approver**.

## Approver

Approvers approve quotes from various writers so that these quotes will be available publicly (in the homepage as well as through API).

## API

You can access all the approved quotes through **/api/quotes**

I have not focused much on the Frontend portion. This project was developed solely for the purpose of demonstrating how IAM features can be implemented through Spring Framework.

Many fancy features are yet to be added to this project.
