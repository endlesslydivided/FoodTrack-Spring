use master;

CREATE DATABASE DietManager;
use DietManager;
DROP DATABASE DietManager;

go
SELECT 'GRANT SELECT ON "' + 'dbo' + '"."' + TABLE_NAME + '" TO "booba"' FROM information_schema.tables;
SELECT 'GRANT EXECUTE ON "' + 'dbo' + '"."' + ROUTINE_NAME + '" TO "booba"' FROM information_schema.ROUTINES;

CREATE TABLE Users
(
Id int constraint PK_USERS primary key (Id) identity(1,1),
IsAdmin bit NOT NULL default '0',
UserLogin varchar(25) NOT NULL UNIQUE,
UserPassword varchar(60)  NOT NULL UNIQUE
)

CREATE TABLE UsersParams
(
Id int constraint PK_USERS_PARAMS primary key(Id) identity(1,1),
IdParams int NOT NULL  constraint FK_USERS_PARAMS_USERS foreign key (IdParams) references Users(Id) ON DELETE CASCADE,
ParamsDate date NOT NULL,
UserWeight decimal(4,1) default '0' NOT NULL,
UserHeight int default '0' NOT NULL
)

CREATE TABLE UsersData
(
Id int  constraint PK_USERS_DATA primary key(Id) identity(1,1),
IdData int UNIQUE NOT NULL constraint FK_USERS_DATA_USERS foreign key (IdData) references Users(Id) ON DELETE CASCADE ,
FullName varchar(300) NOT NULL,
Birthday date  NOT NULL
)

CREATE TABLE FoodCategories
(
Id int constraint PK_FCATEGORIES primary key (Id) identity(1,1),
CategoryName varchar(50) NOT NULL UNIQUE 
)

CREATE TABLE Products
(
Id int constraint PK_PRODUCTS primary key(Id) identity(1,1), 
IdAdded int constraint FK_PRODUCTS_USERS foreign key(IdAdded) references Users(Id)  ON DELETE SET NULL,
ProductName varchar(200) NOT NULL UNIQUE,
CaloriesGram decimal(7,2) NOT NULL default '0',
ProteinsGram decimal(7,2) NOT NULL default '0',
FatsGram decimal(7,2) NOT NULL default '0',
CarbohydratesGram decimal(7,2) NOT NULL default '0',
FoodCategory varchar(50) NOT NULL constraint FK_PRODUCTS_FCATEGORY foreign key (FoodCategory) references FoodCategories(CategoryName) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE Reports
(
Id int  constraint PK_REPORTS primary key(Id) identity(1,1),
IdReport int NOT NULL constraint FK_REPORTS_USERS foreign key (IdReport) references Users(Id),
ProductName varchar(200) NOT NULL constraint FK_REPORTS_PRODUCTS foreign key (ProductName) references Products(ProductName) ON UPDATE CASCADE ON DELETE CASCADE,
ReportDate date NOT NULL,
EatPeriod varchar(8) NOT NULL,
DayGram decimal(8,2) NOT NULL default '0',
DayCalories decimal(8,2) NOT NULL default '0',
DayProteins decimal(8,2) NOT NULL default '0',
DayFats decimal(8,2) NOT NULL default '0',
DayCarbohydrates decimal(8,2) NOT NULL default '0'
)


