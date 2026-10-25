
-- =========================================================
-- DBMS PRACTICAL EXAM - COMPLETE SQL COMMAND ANSWER BANK
-- Database: MySQL
-- Instructions: Run one practical section at a time.
-- =========================================================


-- 0. CREATE AND SELECT DATABASE
-- Creates a database for all these practicals.
CREATE DATABASE IF NOT EXISTS DBMS_Practicals;
USE DBMS_Practicals;

-- Shows all tables in the selected database.
SHOW TABLES;


-- =========================================================
-- PRACTICAL 1: EMPLOYEE AND EMP_COMPANY
-- Operations: Create, Retrieve, Filter, Update, Aggregate
-- =========================================================

-- Create Employee table.
CREATE TABLE Employee (
    Ename VARCHAR(30) PRIMARY KEY,
    City VARCHAR(30)
);

-- Insert employee names and cities.
INSERT INTO Employee VALUES
('Anil','Nagpur'),
('Shankar','Bombay'),
('Jaya','Madras'),
('Sunil','Bombay'),
('Vijay','Delhi'),
('Prakesh','Calcutta'),
('Ajay','Madras'),
('Amol','Nagpur');

-- Display all employees.
SELECT * FROM Employee;

-- Display employees living in Bombay.
SELECT * FROM Employee WHERE City = 'Bombay';

-- Change Anil's city to Pune.
UPDATE Employee SET City = 'Pune' WHERE Ename = 'Anil';

-- Display employee-company and salary information.
CREATE TABLE Emp_Company (
    Ename VARCHAR(30),
    Cname VARCHAR(30),
    Salary DECIMAL(10,2)
);

INSERT INTO Emp_Company VALUES
('Anil','ACC',1500),
('Shankar','TATA',2000),
('Jaya','CMC',1800),
('Sunil','CMC',1700),
('Vijay','TATA',5000),
('Prakesh','TATA',3000),
('Ajay','ACC',8000),
('Amol','ACC',1000);

-- Display all employee-company records.
SELECT * FROM Emp_Company;

-- Display employees earning more than 3000.
SELECT * FROM Emp_Company WHERE Salary > 3000;

-- Count all employees.
SELECT COUNT(*) AS TotalEmployees FROM Employee;

-- Calculate total salary.
SELECT SUM(Salary) AS TotalSalary FROM Emp_Company;

-- Calculate average salary.
SELECT AVG(Salary) AS AverageSalary FROM Emp_Company;

-- Find highest and lowest salaries.
SELECT MAX(Salary) AS HighestSalary,
       MIN(Salary) AS LowestSalary
FROM Emp_Company;

-- Calculate total salary company-wise.
SELECT Cname, SUM(Salary) AS TotalSalary
FROM Emp_Company
GROUP BY Cname;


-- =========================================================
-- PRACTICAL 2: SALESMAN AND ORDERS
-- Operations: Create, Compare, Retrieve, Filter, Update, Join
-- =========================================================

-- Create Salesman table.
CREATE TABLE Salesman (
    Salesman_id INT PRIMARY KEY,
    Name VARCHAR(40),
    City VARCHAR(30),
    Commission DECIMAL(4,2)
);

-- Create Orders table.
CREATE TABLE Orders (
    Ord_no INT PRIMARY KEY,
    Purch_amt DECIMAL(10,2),
    Ord_date DATE,
    Customer_id INT,
    Salesman_id INT
);

-- Insert salesman records.
INSERT INTO Salesman VALUES
(5001,'James Hoog','New York',0.15),
(5002,'Nail Knite','Paris',0.13),
(5003,'Pit Alex','London',0.11),
(5006,'Mc Lyon','Paris',0.14);

-- Insert order records.
INSERT INTO Orders VALUES
(70001,150.50,'2012-10-05',3005,5002),
(70002,65.26,'2012-10-05',3002,5001),
(70004,110.50,'2012-08-17',3009,5003),
(70007,948.50,'2012-09-10',3005,5002);

-- Display all salesmen.
SELECT * FROM Salesman;

-- Display all orders.
SELECT * FROM Orders;

-- Compare commission values.
SELECT * FROM Salesman WHERE Commission > 0.12;

-- Filter orders with purchase amount greater than 100.
SELECT * FROM Orders WHERE Purch_amt > 100;

-- Find orders with purchase amount between 100 and 500.
SELECT * FROM Orders WHERE Purch_amt BETWEEN 100 AND 500;

-- Update the purchase amount of order 70001.
UPDATE Orders SET Purch_amt = 200 WHERE Ord_no = 70001;

-- Sort orders by purchase amount from highest to lowest.
SELECT * FROM Orders ORDER BY Purch_amt DESC;

-- Join Orders and Salesman to display salesman names with orders.
SELECT Orders.Ord_no, Orders.Purch_amt,
       Salesman.Name, Salesman.City
FROM Orders
JOIN Salesman
ON Orders.Salesman_id = Salesman.Salesman_id;

-- Create a view containing salesmen from Paris.
CREATE VIEW ParisSalesmen AS
SELECT * FROM Salesman WHERE City = 'Paris';

-- Display the view.
SELECT * FROM ParisSalesmen;


-- =========================================================
-- PRACTICAL 3: EMPLOYEE SALARY TABLE
-- Operations: Create, Retrieve, Update, Copy, Drop
-- =========================================================

-- Create the employee salary table.
CREATE TABLE EmpSalary (
    Ename VARCHAR(30) PRIMARY KEY,
    City VARCHAR(30),
    Salary INT
);

-- Insert employee salary records.
INSERT INTO EmpSalary VALUES
('Anil','Nagpur',1000),
('Shankar','Bombay',2000),
('Jaya','Madras',3000),
('Sunil','Bombay',4000),
('Vijay','Delhi',5000),
('Prakesh','Calcutta',6000),
('Ajay','Madras',7000),
('Amol','Nagpur',8000);

-- Retrieve all records.
SELECT * FROM EmpSalary;

-- Update Anil's salary.
UPDATE EmpSalary SET Salary = 1500 WHERE Ename = 'Anil';

-- Copy the table and its current data into a new table.
CREATE TABLE EmpSalary_Copy AS
SELECT * FROM EmpSalary;

-- Display copied data.
SELECT * FROM EmpSalary_Copy;

-- Delete the copied table structure and its data.
DROP TABLE EmpSalary_Copy;


-- =========================================================
-- PRACTICAL 4: ACCOUNT
-- Operations: Create, Retrieve, Count, Update, Calculate
-- =========================================================

-- Create Account table.
CREATE TABLE Account (
    Actno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amt DECIMAL(10,2)
);

-- Insert account records.
INSERT INTO Account VALUES
(100,'Anil','VRCE',1000),
(101,'Sunil','AJNI',5000),
(102,'Mehul','Karolbaug',3500),
(104,'Madhuri','Chandni',1200),
(105,'Promod','MGRoad',3000),
(106,'Sandip','Andheri',2000),
(107,'Shivani','Virar',1000),
(108,'Kranti','Nehru Palace',5000);

-- Display all accounts.
SELECT * FROM Account;

-- Count total accounts.
SELECT COUNT(*) AS TotalAccounts FROM Account;

-- Update the amount of account 100.
UPDATE Account SET Amt = 1500 WHERE Actno = 100;

-- Calculate a 10% increase without changing stored data.
SELECT Actno, Cname, Amt, Amt * 1.10 AS AmountAfter10Percent
FROM Account;

-- Calculate total and average account amounts.
SELECT SUM(Amt) AS TotalAmount,
       AVG(Amt) AS AverageAmount
FROM Account;

-- Display accounts with amounts greater than 2000.
SELECT * FROM Account WHERE Amt > 2000;

-- Create a view of accounts with amounts above 2000.
CREATE VIEW LargeAccounts AS
SELECT * FROM Account WHERE Amt > 2000;

-- Display the view.
SELECT * FROM LargeAccounts;


-- =========================================================
-- PRACTICAL 5: BORROW
-- Operations: Create, Retrieve, Filter, Update, View
-- =========================================================

-- Create Borrow table.
CREATE TABLE Borrow (
    Loanno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amount DECIMAL(10,2)
);

-- Insert loan records.
INSERT INTO Borrow VALUES
(201,'Anil','VRCE',100000),
(206,'Mehul','AJNI',500000),
(311,'Sunil','Dharampeth',300000),
(321,'Madhuri','Andheri',200000),
(375,'Pramod','Virar',800000),
(481,'Kranti','Nehru Road',300000);

-- Retrieve all loan records.
SELECT * FROM Borrow;

-- Filter loans above 300000.
SELECT * FROM Borrow WHERE Amount > 300000;

-- Update the amount of loan 201.
UPDATE Borrow SET Amount = 150000 WHERE Loanno = 201;

-- Create a view for loans of at least 300000.
CREATE VIEW HighValueLoans AS
SELECT * FROM Borrow WHERE Amount >= 300000;

-- Display the view.
SELECT * FROM HighValueLoans;


-- =========================================================
-- PRACTICAL 6: COMPANY AND EMPLOYEE-COMPANY
-- Operations: Create, Retrieve, Aggregate, Filter
-- =========================================================

-- Create Company table.
CREATE TABLE Company (
    Cname VARCHAR(30),
    City VARCHAR(30)
);

-- Insert company records.
INSERT INTO Company VALUES
('CC','Madras'),
('TATA','Bombay'),
('ACC','Nagpur'),
('CMC','Bombay'),
('CMC','Madras'),
('TATA','Madras');

-- Create employee-company details with address.
CREATE TABLE Emp_Company2 (
    Ename VARCHAR(30),
    Cname VARCHAR(30),
    Salary DECIMAL(10,2),
    Address VARCHAR(30)
);

-- Insert employee-company records.
INSERT INTO Emp_Company2 VALUES
('Anil','ACC',1500,'Mumbai'),
('Shankar','TATA',2000,'Bihar'),
('Jaya','CMC',1800,'Gujrat'),
('Sunil','CMC',1700,'Mumbai'),
('Vijay','TATA',5000,'Gujrat'),
('Prakesh','TATA',3000,'Satara'),
('Ajay','ACC',8000,'Latur'),
('Amol','ACC',1000,'Bihar');

-- Display all companies.
SELECT * FROM Company;

-- Display all employee-company details.
SELECT * FROM Emp_Company2;

-- Calculate total salary for each company.
SELECT Cname, SUM(Salary) AS TotalSalary
FROM Emp_Company2
GROUP BY Cname;

-- Display employees earning more than 3000.
SELECT * FROM Emp_Company2 WHERE Salary > 3000;

-- Filter employees living in Mumbai.
SELECT * FROM Emp_Company2 WHERE Address = 'Mumbai';

-- Filter salary between 1500 and 5000, including both limits.
SELECT * FROM Emp_Company2 WHERE Salary BETWEEN 1500 AND 5000;

-- Join employee-company data with company cities.
SELECT Emp_Company2.Ename, Emp_Company2.Cname,
       Emp_Company2.Salary, Company.City
FROM Emp_Company2
JOIN Company ON Emp_Company2.Cname = Company.Cname;


-- =========================================================
-- PRACTICAL 7: EMPLOYEE JOB TABLE
-- Operations: Create, Retrieve, Sort, Filter, Search
-- =========================================================

-- Create EmployeeJob table.
CREATE TABLE EmployeeJob (
    Empno INT PRIMARY KEY,
    Ename VARCHAR(30),
    Job VARCHAR(20),
    Deptno INT,
    Sal INT
);

-- Insert records from the question sheet.
INSERT INTO EmployeeJob VALUES
(11,'Arun','ASP',1,8000),
(12,'Akash','AP',2,5000),
(13,'Prachi','ASP',1,12000),
(14,'Rajesh','Prof',2,20000),
(15,'Rupali','Prof',1,20000);

-- Retrieve all employees.
SELECT * FROM EmployeeJob;

-- Sort by salary from lowest to highest.
SELECT * FROM EmployeeJob ORDER BY Sal ASC;

-- Sort by salary from highest to lowest.
SELECT * FROM EmployeeJob ORDER BY Sal DESC;

-- Filter employees in department 1.
SELECT * FROM EmployeeJob WHERE Deptno = 1;

-- Search for names starting with A.
SELECT * FROM EmployeeJob WHERE Ename LIKE 'A%';

-- Search for names containing 'a'.
SELECT * FROM EmployeeJob WHERE Ename LIKE '%a%';

-- Display unique job titles.
SELECT DISTINCT Job FROM EmployeeJob;

-- Display unique department numbers.
SELECT DISTINCT Deptno FROM EmployeeJob;

-- Filter salary from 5000 to 12000 inclusive.
SELECT * FROM EmployeeJob WHERE Sal BETWEEN 5000 AND 12000;

-- Count employees.
SELECT COUNT(*) AS TotalEmployees FROM EmployeeJob;

-- Calculate total, average, highest and lowest salaries.
SELECT SUM(Sal) AS TotalSalary,
       AVG(Sal) AS AverageSalary,
       MAX(Sal) AS HighestSalary,
       MIN(Sal) AS LowestSalary
FROM EmployeeJob;

-- Summarize employee count and salary by department.
SELECT Deptno, COUNT(*) AS Employees,
       SUM(Sal) AS DepartmentSalary
FROM EmployeeJob
GROUP BY Deptno;

-- Create a view of department 1 employees.
CREATE VIEW Dept1Employees AS
SELECT * FROM EmployeeJob WHERE Deptno = 1;

-- Display the view.
SELECT * FROM Dept1Employees;


-- =========================================================
-- PRACTICAL 8: EMPLOYEE DISTINCT, RANGE AND SUMMARY
-- Reuse EmployeeJob created in Practical 7.
-- =========================================================

-- Retrieve all records.
SELECT * FROM EmployeeJob;

-- Show only different job titles.
SELECT DISTINCT Job FROM EmployeeJob;

-- Filter employees in a salary range.
SELECT * FROM EmployeeJob WHERE Sal BETWEEN 8000 AND 20000;

-- Count employees in each job title.
SELECT Job, COUNT(*) AS Total
FROM EmployeeJob
GROUP BY Job;

-- Summarize salaries by job title.
SELECT Job, SUM(Sal) AS TotalSalary,
       AVG(Sal) AS AverageSalary
FROM EmployeeJob
GROUP BY Job;

-- Count distinct job titles.
SELECT COUNT(DISTINCT Job) AS UniqueJobs
FROM EmployeeJob;

-- Create a view of all employee job records.
CREATE VIEW AllEmployeeJobs AS
SELECT * FROM EmployeeJob;

-- Display the view.
SELECT * FROM AllEmployeeJobs;


-- =========================================================
-- PRACTICAL 9: LOAN
-- Operations: Create, Retrieve, Aggregate, View, Truncate
-- =========================================================

-- Create Loan table.
CREATE TABLE Loan (
    Loanno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amount INT
);

-- Insert loan data.
INSERT INTO Loan VALUES
(201,'Anil','VRCE',100000),
(206,'Mehul','AJNI',500000),
(311,'Sunil','Dharampeth',300000),
(321,'Madhuri','Andheri',200000),
(375,'Pramod','Virar',800000),
(481,'Kranti','Nehru Road',300000);

-- Retrieve all loans.
SELECT * FROM Loan;

-- Calculate total and average loan amount.
SELECT SUM(Amount) AS TotalLoan,
       AVG(Amount) AS AverageLoan
FROM Loan;

-- Count loans.
SELECT COUNT(*) AS TotalLoans FROM Loan;

-- Create a view of all loans.
CREATE VIEW LoanView AS
SELECT * FROM Loan;

-- Display the view.
SELECT * FROM LoanView;

-- DANGEROUS: Remove all rows but keep the table.
-- Run this only if the exam specifically asks for TRUNCATE.
-- TRUNCATE TABLE Loan;


-- =========================================================
-- PRACTICAL 10: CUSTOMER AND LOAN CUSTOMER
-- Operations: Create, Retrieve, Delete, Update, View
-- =========================================================

-- Create Customer table.
CREATE TABLE Customer (
    Cname VARCHAR(30) PRIMARY KEY,
    City VARCHAR(30)
);

-- Insert customer records.
INSERT INTO Customer VALUES
('Anil','Calcutta'),
('Sunil','Delhi'),
('Mehul','Baroda'),
('Mandar','Patna'),
('Madhuri','Nagpur'),
('Pramod','Nagpur'),
('Sandip','Surat'),
('Shivani','Bombay'),
('Kranti','Bombay'),
('Naren','Bombay');

-- Create loan customer table.
CREATE TABLE LoanCustomer (
    Loanno INT PRIMARY KEY,
    Cname VARCHAR(30),
    Bname VARCHAR(30),
    Amount INT
);

-- Insert loan records exactly as shown in the sheet.
INSERT INTO LoanCustomer VALUES
(201,'Ana','VRCE',100000),
(202,'Suresh','AJNI',500000),
(310,'Jazz','Dharampeth',300000),
(320,'Manish','Andheri',200000),
(375,'Akash','Virar',800000),
(481,'Karan','Nehru Road',300000);

-- Display all customers.
SELECT * FROM Customer;

-- Display all loan records.
SELECT * FROM LoanCustomer;

-- Filter customers from Bombay.
SELECT * FROM Customer WHERE City = 'Bombay';

-- Update Anil's city to Pune.
UPDATE Customer SET City = 'Pune' WHERE Cname = 'Anil';

-- Delete Naren's record.
DELETE FROM Customer WHERE Cname = 'Naren';

-- Create and display a customer view.
CREATE VIEW CustomerView AS
SELECT * FROM Customer;

SELECT * FROM CustomerView;

-- Join customer and loan records where names match.
SELECT Customer.Cname, Customer.City,
       LoanCustomer.Bname, LoanCustomer.Amount
FROM Customer
JOIN LoanCustomer
ON Customer.Cname = LoanCustomer.Cname;


-- =========================================================
-- QUICK REVISION: COMMON SQL COMMANDS
-- =========================================================

-- CREATE: creates a new table.
-- SELECT: retrieves records.
-- WHERE: filters records using a condition.
-- UPDATE: changes existing records.
-- DELETE: removes selected records.
-- COUNT: counts rows or values.
-- SUM: calculates the total.
-- AVG: calculates the average.
-- MAX / MIN: finds highest / lowest values.
-- ORDER BY: sorts the result.
-- DISTINCT: removes duplicate values from results.
-- BETWEEN: checks whether a value is within a range.
-- LIKE: searches using a pattern.
-- GROUP BY: groups rows for aggregate calculations.
-- JOIN: combines related data from multiple tables.
-- VIEW: saves a SELECT query as a virtual table.
-- DROP: removes a table or other database object.
-- TRUNCATE: removes all rows but keeps the table structure.
-- DESC table_name: displays table structure.
-- SHOW TABLES: lists tables in the current database.

-- Example table structure:
-- DESC Employee;

-- IMPORTANT:
-- Always use WHERE with UPDATE and DELETE when only
-- one or some rows must change.
-- Do not run TRUNCATE unless all rows should be removed.
