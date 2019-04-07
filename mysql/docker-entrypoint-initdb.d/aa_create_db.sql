create database my_budget_db;
create user 'mybudget'@'%' identified by 'test_password';
grant all on my_budget_db.* to 'mybudget'@'%';