CREATE TABLE EMPLOYEES(
    ID INT PRIMARY KEY,
   NAME VARCHAR(255),
   AGE  INT,
  SALARY INT,
  PICTURE VARCHAR(255),
  constraint uq1 unique (NAME)
);