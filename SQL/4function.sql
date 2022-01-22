/* Create a table called NAMES */
create table student(roll int, name varchar(255), city varchar(255), marks int);

/* Create few records in this table */
insert into student values(1, 'abhishek', "delhi",78);
insert into student values(2, 'akhil', "mumbai" ,88);
insert into student values(3, 'alan', "kolkata", 60);
insert into student values(4, 'anupam', "chennai" , 87);
insert into student values(5, 'ayush', "delhi" , 75);
insert into student values(6, 'aryan', "mumbai", 88);
insert into student values(7, 'preet', "delhi", 85);
insert into student values(8, 'muskan', "delhi", 95);
insert into student values(9, 'yash', "punjab", 85);
insert into student values(10, 'paras', "haryana", 89);
insert into student values(11, 'priyanka', "delhi", 85);
insert into student values(12, 'anurag', "bihar", 92);
insert into student values(13, 'vineet', "gujrat", 74);
insert into student values(14, 'rohit', "delhi", 97);
insert into student values(15, 'manushi', "kolkata", 68);
insert into student values(16, 'suraj', "bihar", 89);

-- FUNCTION IN SQL

-- 1.  MAX()
select MAX(marks)  from student where city = "delhi";          -- 97

select roll, name , city , MAX(marks) from student where city = "delhi";  -- 14|rohit|delhi|97

select name, MAX(marks) from student where city = "delhi";  -- rohit|97


select *, MAX(marks) from student where city = "delhi";   -- 14|rohit|delhi|97|97-- NOTE: yaha marks do baar print ho jayege
    
    
-- 2. MIN()
select min(marks) from student;   -- 60

select name, min(marks) from student where city = "delhi";  -- ayush|75


-- 3. AVG() 
 select avg(marks) from student;
 select avg(marks) from student where city = "delhi";
 select avg(marks) from student where city = "mumbai";

-- 4. SUM()

select sum(marks) from student;

select sum(marks) from student where city = "delhi";

select sum(marks) from student where city = "delhi" or city = "mumbai";
select sum(marks) from student where city in("delhi", "mumbai");

select  count(city) from student where city = "delhi";
select  count(city) from student where city = "mumbai";

select *, max(marks) from student where city = "mumbai";



-- group by clause
select city, sum(marks) from student group by city;

select city, sum(marks) from student group by city order by city asc;

select city, sum(marks) from student group by city order by sum(marks) desc;  -- sum of salary ke hisab se arrange kar dega descending order me.


-- NOTE:

-- (i).  count(column) 
--  pehle to group by karege then kissi ek group me dekhege ki total kitne rows ha utne element ke count print kar dege..(and do for all groups)


-- below both statement have exactly same result because count(city) === count(marks) these are equal to count(roll) because yaha roll ek unique identity ha 
select count(city), city from student group by city order by count(city) desc;
select count(marks), city from student group by city order by count(marks) desc;

select count(distinct marks), city from student group by city order by count(marks) desc;  -- yaha delhi me 5 ata as do student ke 88 marks ha 

-- ye likhna jada sahi hoga[i.e-> count(roll) ] because roll ek unique identity ha so kabhi duplicy nahi ayegi
select count(roll), city from student group by city order by count(roll) desc;
select count(distinct roll), city from student group by city order by count(roll) desc;  -- but yaha delhi me 6 hi ayega as roll to ek unique qty ha


select count(marks), city from student group by city order by count(marks) desc;



-- (ii).  count(distinct column) 
-- pehle to group by karege then kissi ek group me dekhege ki distinct elements kitne ha vohi print karege..(and do for all groups)
select count(distinct marks), city from student group by city order by count(marks) desc;
select count(distinct marks), city from student group by city order by count(distinct marks) desc;


--(iii). count(*) 

select count(*) from student;

select count(*), city from student group by city order by count(*) desc;


-- (iv). count(1)

-- count(1) is exactly same as count(*)  -- -> END RESULT DONO KA SAME HI AYEGA 100% PAKKA

select count(*) from student;

select count(*), city from student group by city order by count(*) desc;


