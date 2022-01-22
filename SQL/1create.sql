        Syntax
        CREATE TABLE table_name (
            column1 datatype,
            column2 datatype,
            column3 datatype,
        ....
        );

        The column parameters specify the names of the columns of the table.
        The datatype parameter specifies the type of data the columncan hold (e.g. varchar, integer, date, etc.).





    # EXAMPLE:





create table student(
    RollNumber varchar(50),
    FirstName varchar(255);
    LastName varchar(255);
    Branch varchar(100);
    Marks int,
    FatherName varchar(200),
    MotherName varchar(200)
);


Create Table Student (
    RollNumber int, 
    Name varchar(50), 
    Marks decimal(3,4),
    FatherName varchar(50),
    MotherName varchar(50),
    AddmissionNumber int
);
