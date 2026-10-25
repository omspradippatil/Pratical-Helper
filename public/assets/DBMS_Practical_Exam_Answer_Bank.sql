-- =========================================================
-- DBMS PRACTICAL EXAM - COMPLETE SQL ANSWER BANK
-- MySQL / MySQL Workbench
-- Comments explain what each command does.
-- Run each practical section separately.
-- =========================================================

CREATE DATABASE IF NOT EXISTS DBMS_Practicals;
USE DBMS_Practicals;

-- PRACTICAL 1: SALESMAN AND ORDERS
CREATE TABLE Salesman (
    Salesman_id INT PRIMARY KEY,
    Name VARCHAR(30),
    City VARCHAR(30),
    Commission DECIMAL(4,2)
);

CREATE TABLE Orders (
    Ord_no INT PRIMARY KEY,
    Purch_amt DECIMAL(8,2),
    Ord_date DATE,
    Customer_id INT,
    Salesman_id INT,
    FOREIGN KEY (Salesman_id) REFERENCES Salesman(Salesman_id)
);

INSERT INTO Salesman VALUES
(5001,'James Hoog','New York',0.15),
(5002,'Nail Knite','Paris',0.13),
(5003,'Pit Alex','London',0.11),
(5006,'Mc Lyon','Paris',0.14);

INSERT INTO Orders VALUES
(70001,150.50,'2012-10-05',3005,5002),
(70002,65.26,'2012-10-05',3002,5001),
(70004,110.50,'2012-08-17',3009,5003),
(70007,948.50,'2012-09-10',3005,5002);

-- Retrieve all records.
SELECT * FROM Salesman;
SELECT * FROM Orders;

-- Filter orders with purchase amount greater than 100.
SELECT Ord_no, Purch_amt, Customer_id
FROM Orders
WHERE Purch_amt > 100.00;

-- Count orders and calculate total purchase amount per salesman.
SELECT Salesman_id, COUNT(Ord_no) AS Total_Orders,
       SUM(Purch_amt) AS Total_Amt
FROM Orders GROUP BY Salesman_id;

-- Categorize orders by purchase amount.
SELECT Ord_no, Purch_amt,
       CASE WHEN Purch_amt >= 500 THEN 'High Value'
            ELSE 'Regular Value' END AS Category
FROM Orders;

-- Create a view joining orders with salesman details.
CREATE VIEW Sales_Summary_View AS
SELECT O.Ord_no, O.Purch_amt, S.Name AS Salesman_Name,
       S.City, S.Commission
FROM Orders O
INNER JOIN Salesman S ON O.Salesman_id = S.Salesman_id;

-- Display orders handled by Paris salesmen.
SELECT * FROM Sales_Summary_View WHERE City = 'Paris';


-- PRACTICAL 2: COMPANY AND EMP_COMPANY
CREATE TABLE Company (
    Cname VARCHAR(30),
    City VARCHAR(30)
);

CREATE TABLE Emp_company (
    Ename VARCHAR(30) PRIMARY KEY,
    Cname VARCHAR(30),
    Salary DECIMAL(10,2),
    Address VARCHAR(50)
);

INSERT INTO Company VALUES
('CC','Madras'),('TATA','Bombay'),('ACC','Nagpur'),
('CMC','Bombay'),('CMC','Madras'),('TATA','Madras');

INSERT INTO Emp_company VALUES
('Anil','ACC',1500.00,'Mumbai'),
('Shankar','TATA',2000.00,'Bihar'),
('Jaya','CMC',1800.00,'Gujrat'),
('Sunil','CMC',1700.00,'Mumbai'),
('Vijay','TATA',5000.00,'Gujrat'),
('Prakesh','TATA',3000.00,'Satara'),
('Ajay','ACC',8000.00,'Latur'),
('Amol','ACC',1000.00,'Bihar');

-- Add Bonus column with a default amount.
ALTER TABLE Emp_company ADD Bonus DECIMAL(8,2) DEFAULT 500.00;

-- Rename Address to Location (MySQL 8.0+).
ALTER TABLE Emp_company RENAME COLUMN Address TO Location;

-- Display table structure.
DESC Emp_company;

-- Search names beginning with A.
SELECT Ename, Cname, Salary
FROM Emp_company WHERE Ename LIKE 'A%';

-- Filter TATA or ACC employees earning at least 3000; highest salary first.
SELECT Ename, Cname, Salary, Location
FROM Emp_company
WHERE Cname IN ('TATA','ACC') AND Salary >= 3000.00
ORDER BY Salary DESC;

-- Company-wise employee count, average salary and total salary.
SELECT Cname, COUNT(*) AS Headcount,
       AVG(Salary) AS Avg_Sal, SUM(Salary) AS Total_Sal
FROM Emp_company GROUP BY Cname;

-- Destructive operation: uncomment only if specifically asked.
-- TRUNCATE TABLE Company;


-- PRACTICAL 3: EMPLOYEE / FACULTY DEPARTMENT ANALYSIS
CREATE TABLE Emp (
    Empno INT PRIMARY KEY,
    Ename VARCHAR(30),
    Job VARCHAR(20),
    Deptno INT,
    Sal DECIMAL(10,2)
);

INSERT INTO Emp VALUES
(11,'Arun','ASP',1,8000.00),
(12,'Akash','AP',2,5000.00),
(13,'Prachi','ASP',1,12000.00),
(14,'Rajesh','Prof',2,20000.00),
(15,'Rupali','Prof',1,20000.00);

-- Retrieve all records.
SELECT * FROM Emp;

-- Display unique job titles.
SELECT DISTINCT Job FROM Emp ORDER BY Job;

-- Salary range from 8000 to 15000 inclusive.
SELECT Empno, Ename, Job, Sal
FROM Emp WHERE Sal BETWEEN 8000.00 AND 15000.00;

-- Summarize departments with total salary above 20000.
SELECT Deptno, COUNT(Empno) AS Faculty_Count,
       MIN(Sal) AS Min_Sal, MAX(Sal) AS Max_Sal,
       SUM(Sal) AS Total_Sal_Budget
FROM Emp
GROUP BY Deptno
HAVING SUM(Sal) > 20000;

-- Create a view of professors.
CREATE VIEW Senior_Faculty AS
SELECT Empno, Ename, Deptno, Sal FROM Emp WHERE Job = 'Prof';

SELECT * FROM Senior_Faculty;

-- Give ASP employees a 10% raise. Run only once.
UPDATE Emp SET Sal = Sal * 1.10 WHERE Job = 'ASP';
SELECT Ename, Job, Sal FROM Emp WHERE Job = 'ASP';


-- PRACTICAL 4: ACCOUNT AND BORROW
CREATE TABLE Account (
    Actno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amt DECIMAL(10,2)
);

CREATE TABLE Borrow (
    Loanno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amount DECIMAL(12,2)
);

INSERT INTO Account VALUES
(100,'Anil','VRCE',1000),(101,'Sunil','AJNI',5000),
(102,'Mehul','Karolbaug',3500),(104,'Madhuri','Chandni',1200),
(105,'Promod','MGRoad',3000),(106,'Sandip','Andheri',2000),
(107,'Shivani','Virar',1000),(108,'Kranti','Nehru Palace',5000);

INSERT INTO Borrow VALUES
(201,'Anil','VRCE',100000),(206,'Mehul','AJNI',500000),
(311,'Sunil','Dharampeth',300000),(321,'Madhuri','Andheri',200000),
(375,'Pramod','Virar',800000),(481,'Kranti','nehrurd',300000);

-- Retrieve all accounts and loans.
SELECT * FROM Account;
SELECT * FROM Borrow;

-- Calculate 5% interest and balance for accounts with amount >= 3000.
SELECT Actno, Cname, Amt,
       ROUND(Amt * 0.05, 2) AS Interest_Earned,
       ROUND(Amt * 1.05, 2) AS Total_Balance
FROM Account WHERE Amt >= 3000;

-- Find customers with both an account and a loan.
SELECT DISTINCT A.Cname, A.Bname AS Acc_Branch,
       A.Amt AS Deposit, B.Amount AS Loan_Amt
FROM Account A
INNER JOIN Borrow B ON A.Cname = B.Cname;

-- Delete accounts below 1200; uncomment only if required.
-- DELETE FROM Account WHERE Amt < 1200.00;

-- Create branch deposit totals view.
CREATE VIEW Branch_Totals AS
SELECT Bname, SUM(Amt) AS Total_Deposits
FROM Account GROUP BY Bname;

SELECT * FROM Branch_Totals;

-- Destructive: uncomment only if the exam asks to clear Borrow.
-- TRUNCATE TABLE Borrow;


-- PRACTICAL 5: CUSTOMER AND LOAN MATCHING
CREATE TABLE Customer (
    Cname VARCHAR(30) PRIMARY KEY,
    City VARCHAR(30)
);

INSERT INTO Customer VALUES
('Anil','Calcutta'),('Sunil','Delhi'),('Mehul','Baroda'),
('Mandar','Patna'),('Madhuri','Nagpur'),('Pramod','Nagpur'),
('Sandip','Surat'),('Shivani','Bombay'),('Kranti','Bombay'),
('Naren','Bombay');

-- Display all customers.
SELECT * FROM Customer;

-- Customers in Bombay or Nagpur, including those without a loan.
SELECT C.Cname, C.City, B.Loanno, B.Amount
FROM Customer C
LEFT JOIN Borrow B ON C.Cname = B.Cname
WHERE C.City IN ('Bombay','Nagpur');

-- Update Anil's city.
UPDATE Customer SET City = 'Pune' WHERE Cname = 'Anil';

-- Delete Naren's customer record.
DELETE FROM Customer WHERE Cname = 'Naren';

-- Create and display a customer view.
CREATE VIEW Customer_View AS SELECT * FROM Customer;
SELECT * FROM Customer_View;


-- QUICK REVISION COMMANDS

-- Retrieve all rows:
-- SELECT * FROM table_name;

-- Filter:
-- SELECT * FROM table_name WHERE column_name = 'value';

-- Compare:
-- SELECT * FROM table_name WHERE column_name >= 100;

-- Search names beginning with A:
-- SELECT * FROM table_name WHERE name LIKE 'A%';

-- Sort ascending or descending:
-- SELECT * FROM table_name ORDER BY column_name ASC;
-- SELECT * FROM table_name ORDER BY column_name DESC;

-- Unique values:
-- SELECT DISTINCT column_name FROM table_name;

-- Range:
-- SELECT * FROM table_name WHERE column_name BETWEEN 10 AND 50;

-- Aggregate functions:
-- SELECT COUNT(*), SUM(column_name), AVG(column_name),
--        MAX(column_name), MIN(column_name) FROM table_name;

-- Update one matching row:
-- UPDATE table_name SET column_name = 'new_value' WHERE id = 1;

-- Delete one matching row:
-- DELETE FROM table_name WHERE id = 1;

-- Copy table and data:
-- CREATE TABLE table_copy AS SELECT * FROM table_name;

-- Create a view:
-- CREATE VIEW view_name AS SELECT * FROM table_name;

-- Drop a table:
-- DROP TABLE table_name;

-- Remove all rows but keep table structure:
-- TRUNCATE TABLE table_name;

-- Show table structure:
-- DESC table_name;

-- List tables:
-- SHOW TABLES;
