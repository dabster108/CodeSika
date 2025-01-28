CREATE DATABASE databaseassignment;
USE databaseassignment;

CREATE TABLE Address (
    AddressID VARCHAR(10) PRIMARY KEY,
    City VARCHAR(50),
    Country VARCHAR(50)
);

INSERT INTO Address (AddressID, City, Country) VALUES
('A001', 'Kathmandu', 'Nepal'),
('A002', 'Pokhara', 'Nepal'),
('A003', 'Lalitpur', 'Nepal'),
('A004', 'Bhaktapur', 'Nepal'),
('A005', 'Chitwan', 'Nepal'),
('A006', 'Hetauda', 'Nepal');
SELECT * FROM Address;

-- 2. Create Membership Table
CREATE TABLE Membership (
    MembershipID VARCHAR(10) PRIMARY KEY,
    MembershipType VARCHAR(50)
);

-- Insert data into Membership Table
INSERT INTO Membership (MembershipID, MembershipType) VALUES
('M001', 'Gold'),
('M002', 'Silver'),
('M003', 'Platinum');
SELECT * FROM Membership;

-- 3. Create Customer Table
CREATE TABLE Customer (
    CustomerID VARCHAR(10) PRIMARY KEY,
    FullName VARCHAR(100),
    Phone VARCHAR(15),
    Email VARCHAR(100),
    AddressID VARCHAR(10),
    MembershipID VARCHAR(10),
    RegistrationDate DATE,
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID),
    FOREIGN KEY (MembershipID) REFERENCES Membership(MembershipID)
);

-- Insert data into Customer Table
INSERT INTO Customer (CustomerID, FullName, Phone, Email, AddressID, MembershipID, RegistrationDate) VALUES
('C001', 'Ram Shrestha', '9841234567', 'ram@gmail.com', 'A001', 'M001', '2022-01-15'),
('C002', 'Sita Sharma', '9812345678', 'sita@gmail.com', 'A002', 'M002', '2021-05-22'),
('C003', 'Hari Bhattarai', '9801122334', 'hari@gmail.com', 'A003', 'M003', '2020-11-10'),
('C004', 'Gita Rai', '9856543210', 'gita@outlook.com', 'A004', 'M001', '2021-07-19'),
('C005', 'Nabin Karki', '9807654321', 'nabin@gmail.com', 'A005', 'M002', '2023-03-30'),
('C006', 'Sarita Giri', '9841123456', 'sarita@gmail.com', 'A006', 'M003', '2020-08-25');

SELECT * FROM Customer;

-- 4. Create Category Table
CREATE TABLE Category (
    CategoryID VARCHAR(10) PRIMARY KEY,
    CategoryName VARCHAR(50)
);

-- Insert data into Category Table
INSERT INTO Category (CategoryID, CategoryName) VALUES
('Ca001', 'Food'),
('Ca002', 'Electronics'),
('Ca003', 'Dairy'),
('Ca004', 'Household Goods');
SELECT * FROM Category;

-- 5. Create Brand Table
CREATE TABLE Brand (
    BrandID VARCHAR(10) PRIMARY KEY,
    BrandName VARCHAR(50)
);

-- Insert data into Brand Table
INSERT INTO Brand (BrandID, BrandName) VALUES
('B001', 'ABC Rice'),
('B002', 'Apple'),
('B003', 'Samsung'),
('B004', 'DDC'),
('B005', 'XYZ Flour'),
('B006', 'CleanX'),
('B007', 'HP');
SELECT * FROM Brand;

-- 6. Create Product Table
CREATE TABLE Product (
    ProductID VARCHAR(10) PRIMARY KEY,
    ProductName VARCHAR(100),
    CategoryID VARCHAR(10),
    BrandID VARCHAR(10),
    Price DECIMAL(10, 2),
    StockQuantity INT,
    SupplierID VARCHAR(10),
    ImportDate DATE,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
    FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
);

-- Insert data into Product Table
INSERT INTO Product (ProductID, ProductName, CategoryID, BrandID, Price, StockQuantity, SupplierID, ImportDate) VALUES
('P001', 'Rice 10kg', 'Ca001', 'B001', 1200, 50, 'S001', '2024-06-10'),
('P002', 'iPhone 14 Pro', 'Ca002', 'B002', 150000, 10, 'S002', '2024-05-05'),
('P003', 'LED TV 42 inches', 'Ca002', 'B003', 55000, 20, 'S003', '2024-06-01'),
('P004', 'Milk 1L', 'Ca003', 'B004', 100, 200, 'S004', '2024-07-11'),
('P005', 'Wheat Flour 5kg', 'Ca001', 'B005', 600, 75, 'S001', '2024-06-15'),
('P006', 'Detergent Powder 1kg', 'Ca004', 'B006', 200, 100, 'S005', '2024-06-20'),
('P007', 'Laptop HP Pavilion', 'Ca002', 'B007', 80000, 15, 'S002', '2024-04-22');
SELECT * FROM Product;

-- 7. Create Supplier Table
CREATE TABLE Supplier (
    SupplierID VARCHAR(10) PRIMARY KEY,
    SupplierName VARCHAR(100),
    ContactPerson VARCHAR(100),
    Phone VARCHAR(15),
    AddressID VARCHAR(10),
    Email VARCHAR(100),
    LastDeliveryDate DATE,
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID)
);

-- Insert data into Supplier Table
INSERT INTO Supplier (SupplierID, SupplierName, ContactPerson, Phone, AddressID, Email, LastDeliveryDate) VALUES
('S001', 'ABC Distributors', 'Ramesh Tiwari', '9801234567', 'A001', 'ramesh@abcdistrib.com', '2024-06-15'),
('S002', 'Tech Innovators', 'Sushil Manandhar', '9845678901', 'A003', 'sushil@techinnov.com', '2024-05-05'),
('S003', 'Samsung Nepal', 'Hari Khadka', '9812345678', 'A004', 'hari@samsung.com.np', '2024-06-01'),
('S004', 'DDC', 'Sunita Sharma', '9807654321', 'A006', 'sunita@ddc.com', '2024-07-11'),
('S005', 'CleanX Supplies', 'Ramila Shrestha', '9851234567', 'A002', 'ramila@cleanx.com', '2024-06-20'),
('S006', 'HP Nepal', 'Bimal Rai', '9843210987', 'A001', 'bimal@hpnepal.com', '2024-04-22');
SELECT * FROM Supplier;

-- 8. Create PaymentMethod Table
CREATE TABLE PaymentMethod (
    PaymentMethodID VARCHAR(10) PRIMARY KEY,
    PaymentMethod VARCHAR(50)
);

-- Insert data into PaymentMethod Table
INSERT INTO PaymentMethod (PaymentMethodID, PaymentMethod) VALUES
('PY001', 'Cash'),
('PY002', 'Credit Card'),
('PY003', 'Debit Card'),
('PY004', 'Mobile Payment'),
('PY005', 'Bank Transfer');
SELECT * FROM PaymentMethod;

-- 9. Create Cashier Table
CREATE TABLE Cashier (
    CashierID VARCHAR(10) PRIMARY KEY,
    CashierName VARCHAR(100)
);

-- Insert data into Cashier Table
INSERT INTO Cashier (CashierID, CashierName) VALUES
('E001', 'Santosh Koirala'),
('E002', 'Sita Sharma'),
('E003', 'Hari Gurung'),
('E004', 'Gita Rai'),
('E005', 'Sarita Giri'),
('E006', 'Nabin Karki');
SELECT * FROM Cashier;

-- 10. Create Sales Table
CREATE TABLE Sales (
    SalesID VARCHAR(10) PRIMARY KEY,
    Date DATE,
    CustomerID VARCHAR(10),
    ProductID VARCHAR(10),
    Quantity INT,
    TotalAmount DECIMAL(10, 2),
    PaymentMethodID VARCHAR(10),
    CashierID VARCHAR(10),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethod(PaymentMethodID),
    FOREIGN KEY (CashierID) REFERENCES Cashier(CashierID)
);

-- Insert data into Sales Table
INSERT INTO Sales (SalesID, Date, CustomerID, ProductID, Quantity, TotalAmount, PaymentMethodID, CashierID) VALUES
('S001', '2024-08-10', 'C001', 'P001', 1, 1200, 'PY001', 'E001'),
('S002', '2024-08-11', 'C003', 'P002', 1, 150000, 'PY002', 'E002'),
('S003', '2024-08-12', 'C004', 'P003', 1, 55000, 'PY003', 'E003'),
('S004', '2024-08-13', 'C002', 'P004', 5, 500, 'PY001', 'E004'),
('S005', '2024-08-14', 'C005', 'P005', 2, 1200, 'PY004', 'E005'),
('S006', '2024-08-15', 'C006', 'P007', 1, 80000, 'PY002', 'E006');
SELECT * FROM Sales;

-- 11. Create Warehouse Table
CREATE TABLE Warehouse (
    WarehouseID VARCHAR(10) PRIMARY KEY,
    WarehouseLocation VARCHAR(100)
);

-- Insert data into Warehouse Table
INSERT INTO Warehouse (WarehouseID, WarehouseLocation) VALUES
('W001', 'Warehouse A'),
('W002', 'Warehouse B'),
('W003', 'Warehouse C'),
('W004', 'Warehouse D'),
('W005', 'Warehouse E');
SELECT * FROM Warehouse;

-- 12. Create Inventory Table
CREATE TABLE Inventory (
    ProductID VARCHAR(10),
    StockQuantity INT,
    ReorderLevel INT,
    LastRestockDate DATE,
    SupplierID VARCHAR(10),
    WarehouseID VARCHAR(10),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID)
);

-- Insert data into Inventory Table
INSERT INTO Inventory (ProductID, StockQuantity, ReorderLevel, LastRestockDate, SupplierID, WarehouseID) VALUES
('P001', 50, 10, '2024-06-15', 'S001', 'W001'),
('P002', 10, 5, '2024-05-05', 'S002', 'W002'),
('P003', 20, 7, '2024-06-01', 'S003', 'W003'),
('P004', 200, 50, '2024-07-11', 'S004', 'W004'),
('P005', 75, 20, '2024-06-15', 'S001', 'W001'),
('P006', 100, 30, '2024-06-20', 'S005', 'W005'),
('P007', 15, 5, '2024-04-22', 'S002', 'W002');
SELECT * FROM Inventory;

-- 13. Create Department Table
CREATE TABLE Department (
    DepartmentID VARCHAR(10) PRIMARY KEY,
    DepartmentName VARCHAR(100),
    ManagerID VARCHAR(10),
    LocationID VARCHAR(10),
    Phone VARCHAR(15),
    Budget DECIMAL(15, 2)
);

-- Insert data into Department Table
INSERT INTO Department (DepartmentID, DepartmentName, ManagerID, LocationID, Phone, Budget) VALUES
('D001', 'Sales', 'E002', 'L001', '9841234567', 5000000),
('D002', 'Inventory', 'E003', 'L002', '9812345678', 3000000),
('D003', 'IT', 'E005', 'L003', '9801122334', 2000000),
('D004', 'Customer Care', 'E006', 'L001', '9856543210', 1000000),
('D005', 'HR', 'E004', 'L003', '9807654321', 1500000),
('D006', 'Procurement', 'E001', 'L004', '9841123456', 4000000);

SELECT * FROM Department;

-- 14. Create Employee Table
CREATE TABLE Employee (
    EmployeeID VARCHAR(10) PRIMARY KEY,
    FullName VARCHAR(100),
    Position VARCHAR(50),
    DepartmentID VARCHAR(10),
    Phone VARCHAR(15),
    Email VARCHAR(100),
    HireDate DATE,
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);

-- Insert data into Employee Table
INSERT INTO Employee (EmployeeID, FullName, Position, DepartmentID, Phone, Email, HireDate) VALUES
('E001', 'Santosh Koirala', 'Cashier', 'D001', '9841234567', 'santosh@bhatbhateni.com', '2022-01-15'),
('E002', 'Sita Sharma', 'Sales Executive', 'D001', '9812345678', 'sita@bhatbhateni.com', '2021-05-22'),
('E003', 'Hari Gurung', 'Inventory Manager', 'D002', '9801122334', 'hari@bhatbhateni.com', '2020-11-10'),
('E004', 'Gita Rai', 'Cashier', 'D001', '9856543210', 'gita@bhatbhateni.com', '2021-07-19'),
('E005', 'Nabin Karki', 'IT Support', 'D003', '9807654321', 'nabin@bhatbhateni.com', '2023-03-30'),
('E006', 'Sarita Giri', 'Customer Service', 'D004', '9841123456', 'sarita@bhatbhateni.com', '2020-08-25');
SELECT * FROM Employee;

-- 15. Create Location Table
CREATE TABLE Location (
    LocationID VARCHAR(10) PRIMARY KEY,
    Location VARCHAR(100)
);

-- Insert data into Location Table
INSERT INTO Location (LocationID, Location) VALUES
('L001', 'Main Building'),
('L002', 'Warehouse A'),
('L003', 'Main Office'),
('L004', 'Procurement Office');
SELECT * FROM Location;

-- 16. Create Orders Table
CREATE TABLE Orders (
    OrderID VARCHAR(10) PRIMARY KEY,
    Date DATE,
    SupplierID VARCHAR(10),
    ProductID VARCHAR(10),
    Quantity INT,
    TotalCost DECIMAL(15, 2),
    ExpectedDelivery DATE,
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Insert data into Orders Table
INSERT INTO Orders (OrderID, Date, SupplierID, ProductID, Quantity, TotalCost, ExpectedDelivery) VALUES
('O001', '2024-06-15', 'S001', 'P001', 100, 120000, '2024-06-20'),
('O002', '2024-05-05', 'S002', 'P002', 20, 3000000, '2024-05-10'),
('O003', '2024-06-01', 'S003', 'P003', 50, 2750000, '2024-06-05'),
('O004', '2024-07-11', 'S004', 'P004', 500, 50000, '2024-07-15'),
('O005', '2024-06-15', 'S001', 'P005', 200, 120000, '2024-06-20'),
('O006', '2024-06-20', 'S005', 'P006', 150, 30000, '2024-06-25');
SELECT * FROM Orders;

-- 17. Create Payment Table
CREATE TABLE Payment (
    PaymentID VARCHAR(10) PRIMARY KEY,
    Date DATE,
    CustomerID VARCHAR(10),
    OrderID VARCHAR(10),
    AmountPaid DECIMAL(15, 2),
    PaymentMethodID VARCHAR(10),
    PaymentStatus VARCHAR(50),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethod(PaymentMethodID)
);

-- Insert data into Payment Table
INSERT INTO Payment (PaymentID, Date, CustomerID, OrderID, AmountPaid, PaymentMethodID, PaymentStatus) VALUES
('PAY001', '2024-06-20', 'C001', 'O001', 120000, 'PY001', 'Completed'),
('PAY002', '2024-05-10', 'C002', 'O002', 3000000, 'PY002', 'Completed'),
('PAY003', '2024-06-05', 'C003', 'O003', 2750000, 'PY005', 'Completed'),
('PAY004', '2024-07-15', 'C004', 'O004', 50000, 'PY004', 'Completed'),
('PAY005', '2024-06-20', 'C005', 'O005', 120000, 'PY003', 'Completed'),
('PAY006', '2024-06-25', 'C006', 'O006', 30000, 'PY001', 'Completed');
SELECT * FROM Payment;

-- 18. Create Returns Table
CREATE TABLE Returns (
    ReturnID VARCHAR(10) PRIMARY KEY,
    Date DATE,
    SalesID VARCHAR(10),
    CustomerID VARCHAR(10),
    ProductID VARCHAR(10),
    QuantityReturned INT,
    Reason VARCHAR(255),
    RefundAmount DECIMAL(10, 2),
    FOREIGN KEY (SalesID) REFERENCES Sales(SalesID),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Insert data into Returns Table
INSERT INTO Returns (ReturnID, Date, SalesID, CustomerID, ProductID, QuantityReturned, Reason, RefundAmount) VALUES
('R001', '2024-08-12', 'S002', 'C003', 'P002', 1, 'Defective Product', 150000),
('R002', '2024-08-15', 'S006', 'C006', 'P007', 1, 'Wrong Item', 80000),
('R003', '2024-08-14', 'S005', 'C005', 'P005', 2, 'Poor Quality', 1200),
('R004', '2024-08-13', 'S004', 'C002', 'P004', 5, 'Expired Product', 500),
('R005', '2024-08-11', 'S003', 'C004', 'P003', 1, 'Not as Described', 55000),
('R006', '2024-08-10', 'S001', 'C001', 'P001', 1, 'Damaged', 1200);
SELECT * FROM Returns;

-- 19. Create TargetAudience Table
CREATE TABLE TargetAudience (
    TargetAudienceID VARCHAR(10) PRIMARY KEY,
    TargetAudience VARCHAR(50)
);

-- Insert data into TargetAudience Table
INSERT INTO TargetAudience (TargetAudienceID, TargetAudience) VALUES
('TA001', 'All Customers'),
('TA002', 'Members Only'),
('TA003', 'Parents'),
('TA004', 'Loyalty Members');
SELECT * FROM TargetAudience;

-- 20. Create Promotion Table
CREATE TABLE Promotion (
    PromotionID VARCHAR(10) PRIMARY KEY,
    PromotionName VARCHAR(100),
    ProductID VARCHAR(10),
    DiscountPercentage DECIMAL(5, 2),
    StartDate DATE,
    EndDate DATE,
    TargetAudienceID VARCHAR(10),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (TargetAudienceID) REFERENCES TargetAudience(TargetAudienceID)
);

-- Insert data into Promotion Table
INSERT INTO Promotion (PromotionID, PromotionName, ProductID, DiscountPercentage, StartDate, EndDate, TargetAudienceID) VALUES
('PR001', 'Dashain Offer', 'P001', 10, '2024-09-01', '2024-09-30', 'TA001'),
('PR002', 'Electronics Sale', 'P002', 15, '2024-08-01', '2024-08-31', 'TA002'),
('PR003', 'Back to School', 'P005', 5, '2024-07-15', '2024-08-15', 'TA003'),
('PR004', 'Summer Sale', 'P006', 20, '2024-06-01', '2024-06-30', 'TA001'),
('PR005', 'New Year Offer', 'P007', 25, '2024-12-15', '2024-12-31', 'TA001'),
('PR006', 'Loyalty Bonus', 'P003', 5, '2024-08-01', '2024-08-15', 'TA004');
SELECT * FROM Promotion;

-- 21. Create Feedback Table
CREATE TABLE Feedback (
    FeedbackID VARCHAR(10) PRIMARY KEY,
    Date DATE,
    CustomerID VARCHAR(10),
    EmployeeID VARCHAR(10),
    FeedbackText VARCHAR(255),
    Rating INT,
    FollowUpRequired VARCHAR(3),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- Insert data into Feedback Table
INSERT INTO Feedback (FeedbackID, Date, CustomerID, EmployeeID, FeedbackText, Rating, FollowUpRequired) VALUES
('F001', '2024-08-10', 'C001', 'E006', 'Excellent service by staff', 5, 'No'),
('F002', '2024-08-11', 'C002', 'E004', 'Good product selection', 4, 'No'),
('F003', '2024-08-12', 'C003', 'E005', 'Poor website experience', 2, 'Yes'),
('F004', '2024-08-13', 'C004', 'E003', 'Inventory not up to date', 3, 'Yes'),
('F005', '2024-08-14', 'C005', 'E002', 'Friendly and helpful staff', 5, 'No'),
('F006', '2024-08-15', 'C006', 'E001', 'Quick and easy checkout process', 4, 'No');
SELECT * FROM Feedback;


-- Step 1: Disable safe update mode
SET SQL_SAFE_UPDATES = 0;

-- Step 2: Reset the prices to their original values
UPDATE Product
SET Price = CASE
    WHEN ProductID = 'P002' THEN 150000.00 -- Original price for iPhone 14 Pro
    WHEN ProductID = 'P003' THEN 55000.00  -- Original price for LED TV 42 Inches
    WHEN ProductID = 'P007' THEN 80000.00  -- Original price for Laptop HP Pavilion
    WHEN ProductID = 'P008' THEN 12000.00  -- Original price for Wireless Earbuds
    ELSE Price -- Leave other products unchanged
END
WHERE CategoryID = 'Ca002'; -- 'Ca002' is the CategoryID for Electronics

-- Step 3: Verify the reset
SELECT ProductID, ProductName, Price
FROM Product
WHERE CategoryID = 'Ca002';

-- Step 4: Re-enable safe update mode (optional)
SET SQL_SAFE_UPDATES = 1;









-- b 
UPDATE Product
SET Price = Price * 1.10
WHERE CategoryID = 'Ca002' AND ProductID IS NOT NULL;




UPDATE Product
SET Price = Price * 1.10
WHERE CategoryID = 'Ca002' AND ProductID IS NOT NULL;
SELECT ProductID, ProductName, Price
FROM Product
WHERE CategoryID = 'Ca002';






-- c
DELETE FROM Sales
WHERE TotalAmount < 500;
SELECT * FROM Sales;


-- d
INSERT INTO Product (ProductID, ProductName, CategoryID, BrandID, Price, StockQuantity, SupplierID, ImportDate)
VALUES ('P008', 'Wireless Earbuds', 'Ca002', 'B002', 12000, 30, 'S002', '2024-08-25');
INSERT INTO Inventory (ProductID, StockQuantity, ReorderLevel, LastRestockDate, SupplierID, WarehouseID)
VALUES ('P008', 30, 10, '2024-08-25', 'S002', 'W002');
SELECT * FROM Product WHERE ProductID = 'P008';
SELECT * FROM Inventory WHERE ProductID = 'P008'; 


-- e 
SELECT 
    c.CashierID,
    c.CashierName,
    COUNT(s.SalesID) AS NumberOfTransactions,
    SUM(s.TotalAmount) AS TotalAmountHandled
FROM 
    Sales s
JOIN 
    Cashier c ON s.CashierID = c.CashierID
GROUP BY 
    c.CashierID, c.CashierName
ORDER BY 
    TotalAmountHandled DESC;
    
    
 -- f
START TRANSACTION;
SET SQL_SAFE_UPDATES = 0;
UPDATE Product
SET StockQuantity = ROUND(StockQuantity * 0.85);
SELECT ProductID, ProductName, StockQuantity FROM Product;
SET SQL_SAFE_UPDATES = 1;
COMMIT;



-- g
SELECT 
    S.SalesID,
    S.Date AS SaleDate, C.CustomerID, C.FullName AS CustomerName,C.Email AS CustomerEmail,
    P.ProductID, P.ProductName, P.Price AS ProductPrice, S.Quantity, S.TotalAmount, E.EmployeeID,
    E.FullName AS CashierName, E.Position AS CashierPosition
FROM Sales S JOIN Customer C ON S.CustomerID = C.CustomerID JOIN 
    Product P ON S.ProductID = P.ProductID
JOIN  Cashier Ca ON S.CashierID = Ca.CashierID
JOIN  Employee E ON Ca.CashierID = E.EmployeeID
WHERE S.TotalAmount > 1000 AND P.CategoryID = 'Ca002' ORDER BY 
    S.TotalAmount DESC;
    
    
    
    
-- h
SELECT 
    C.CategoryID,
    C.CategoryName,
    COUNT(S.SalesID) AS TotalProductsSold,
    SUM(S.TotalAmount) AS TotalSalesAmount,
    AVG(S.TotalAmount) AS AverageSalesAmount
FROM 
    Sales S
JOIN 
    Product P ON S.ProductID = P.ProductID
JOIN 
    Category C ON P.CategoryID = C.CategoryID
GROUP BY 
    C.CategoryID, C.CategoryName
ORDER BY 
    TotalSalesAmount DESC;
    
    
    
    
-- i
SELECT 
    C.CustomerID, C.FullName AS CustomerName, C.Email AS CustomerEmail
FROM  Sales S JOIN Customer C ON S.CustomerID = C.CustomerID
JOIN Product P ON S.ProductID = P.ProductID JOIN Category Cat ON P.CategoryID = Cat.CategoryID
WHERE  Cat.CategoryName = 'Electronics' GROUP BY C.CustomerID, C.FullName, C.Email
HAVING 
    C.CustomerID NOT IN (
SELECT DISTINCT C2.CustomerID FROM Sales S2 JOIN Customer C2 ON S2.CustomerID = C2.CustomerID
JOIN Product P2 ON S2.ProductID = P2.ProductID JOIN Category Cat2 ON P2.CategoryID = Cat2.CategoryID
	WHERE 
            Cat2.CategoryName <> 'Electronics'
    );
