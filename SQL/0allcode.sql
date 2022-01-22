
/* Create a table called NAMES */
create table student(roll int, name varchar(255), city varchar(255), marks int);

/* Create few records in this table */
insert into student values(1, 'abhishek', "delhi",78);
insert into student values(2, 'akhil', "mumbai" ,86);
insert into student values(3, 'alan', "kolkata", 60);
insert into student values(4, 'anupam', "chennai" , 87);
insert into student values(5, 'ayush', "delhi" , 75);
insert into student values(6, 'aryan', "mumbai", 80);
insert into student values(7, 'preet', "delhi", 85);
insert into student values(8, 'muskan', "delhi", 95);
insert into student values(9, 'yash', "punjab", 85);
insert into student values(10, 'paras', "haryana", 89);
insert into student values(11, 'priyanka', "delhi", 85);
insert into student values(12, 'anurag', "bihar", 92);
insert into student values(13, 'vineet', "gujrat", 74);
insert into student values(14, 'rohit', "delhi", 97);

/* Display all the records from the table */
-- select roll, name, marks from student;
-- select * from student;
-- select * from student;

-- select roll, name from student;

-- select roll, name, marks from student where marks >= 80;

-- select * from student where marks <= 80 and roll < 5;

-- select name , city from student where city = "delhi" or city = "mumbai" ;

-- select Distinct city from student;

-- BETWEEN CLAUSE
-- select * from student where marks >= 60 and marks <= 85;
-- select * from student where marks between 60 and 85;

-- select * from student where marks not between 60 and 85;

-- select * from student where name between "anupam" and "paras";

-- IN CLAUSE
-- select * from student where city = "delhi" or city = "mumbai" or city = "kokata" or city = "chennai";
-- select * from student where city in("delhi", "mumbai", "kolkata", "chennai");



-- LIKE clause is used to find the similar records in the table

-- select * from student where name = "anupam";

-- select * from student where name like "a%";

-- select * from student where name like "a_____";

-- Question 1.  Select all records where the value of the City column does NOT start with the letter "d" and ends with letter "i" ;
-- Answer 1.    select * from student where city not like "d%i";



-- ORDER BY:
-- select marks from student order by marks; 

-- print the records in assecending order

-- select marks from student order by marks;
-- select marks from student order by marks asc;   


-- print the records in descending order

-- select * from student order by marks desc;


-- select * from student order by roll, marks;        
-- select * from student order by roll asc, marks asc;


-- WE CAN USE TWO COLUMN BY THEIR 
-- select * from student order by marks desc, roll asc;

-- FUNCTION IN SQL

-- 1.  MAX()
-- select MAX(marks)  from student where city = "delhi";          -- 97

-- select roll, name , city , MAX(marks) from student where city = "delhi";  -- 14|rohit|delhi|97

-- select name, MAX(marks) from student where city = "delhi";  -- rohit|97


-- select *, MAX(marks) from student where city = "delhi";   -- 14|rohit|delhi|97|97-- NOTE: yaha marks do baar print ho jayege
    
    
-- 2. MIN()
-- select min(marks) from student;   -- 60

-- select name, min(marks) from student where city = "delhi";  -- ayush|75


-- 3. AVG() 
--  select avg(marks) from student;
--  select avg(marks) from student where city = "delhi";
--  select avg(marks) from student where city = "mumbai";

-- 4. SUM()

-- select sum(marks) from student;

-- select sum(marks) from student where city = "delhi";

-- select sum(marks) from student where city = "delhi" or city = "mumbai";
-- select sum(marks) from student where city in("delhi", "mumbai");

select  count(city) from student where city = "delhi";
select  count(city) from student where city = "mumbai";

-- select *, max(marks) from student where city = "mumbai";




















