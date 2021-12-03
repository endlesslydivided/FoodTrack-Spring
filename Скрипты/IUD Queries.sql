use DietManager;


--Insert inside Users 
go
Create Procedure DUserAdd
		@Is_Admin bit,
		@User_Login varchar(25),
		@User_Password varchar(60)
AS
Begin
	Insert into Users(IsAdmin,UserLogin,UserPassword)
	values(@Is_Admin, @User_Login, @User_Password);
End;


--Delete from Users 
go
create Procedure DUserDel
		@User_Id int
AS
Begin
	Delete from Reports where IdReport = @User_Id;
	Delete from Users where Id = @User_Id;
End;

--Update Users
go
Create Procedure DUserUpdate
		@Id int,
		@Is_Admin bit,
		@User_Login varchar(25),
		@User_Password varchar(60)
AS
Begin
	Update Users
	set UserLogin = @User_Login ,
	UserPassword = @User_Password,
	IsAdmin = @Is_Admin
	where Id = @Id;
End;

--***************************************************
--***************************************************

--Insert inside UsersParams
go
Create Procedure DUsersParamsAdd
		@Id_Params int,
		@Params_Date datetime,
		@User_Weight decimal(4,1),
		@User_Height int
AS
Begin
	Insert into UsersParams(IdParams, ParamsDate,UserWeight,UserHeight)
	values(@Id_Params, @Params_Date, @User_Weight, @User_Height);
End;

--Delete from UsersParams 
go
create Procedure DUsersParamsDelete
		@User_Params_Id int
AS
Begin
	Delete from UsersParams where Id = @User_Params_Id;
End;

--Update UsersParams
go
Create Procedure DUsersParamsUpdate
		@Id int,
		@Params_Date datetime,
		@User_Weight decimal(4,1),
		@User_Height int
AS
Begin
	Update UsersParams
	set ParamsDate = @Params_Date ,
	UserWeight = @User_Weight,
	UserHeight = @User_Height
	where Id = @Id;
End;

--***************************************************
--***************************************************

--Insert inside UsersData
go
Create Procedure DUsersDataAdd
		@Id_Data int,
		@Full_Name varchar(300),
		@Birthday date
AS
Begin
	Insert into UsersData(IdData, FullName,Birthday)
	values(@Id_Data, @Full_Name, @Birthday);
End;

--Delete from UsersData 
go
create Procedure DUsersDataDelete
		@User_Data_Id int
AS
Begin
	Delete from UsersData where Id = @User_Data_Id;
End;

--Update UsersData
go
Create Procedure DUsersDataUpdate
		@Id int,
		@Full_Name varchar(300),
		@Birthday date
AS
Begin
	Update UsersData
	set 
	FullName = @Full_Name,
	Birthday = @Birthday
	where Id = @Id;
End;


--***************************************************
--***************************************************

--Insert inside FoodCategories
go
Create Procedure DFoodCategoriesAdd
		@Category_Name varchar(50)
AS
Begin
	Insert into FoodCategories(CategoryName)
	values(@Category_Name);
End;

--Delete from FoodCategories 
go
create Procedure DFoodCategoriesDelete
		@Food_Categories_Id int
AS
Begin
	Delete from FoodCategories where Id = @Food_Categories_Id;
End;


--Update FoodCategories
go
Create Procedure DFoodCategoriesUpdate
		@Id int,
		@Category_Name varchar(50)
AS
Begin
	Update FoodCategories
	set 
	CategoryName = @Category_Name
	where Id = @Id;
End;

--***************************************************
--***************************************************

--Insert inside Products
go
Create Procedure DProductsAdd
		@IdAdded int,
		@Product_Name varchar(200),
		@Calories_Gram decimal(7,2),
		@Proteins_Gram decimal(7,2),
		@Fats_Gram decimal(7,2),
		@Carbohydrates_Gram decimal(7,2),
		@Food_Category varchar(50)
AS
Begin
	Insert into Products(IdAdded,ProductName,CaloriesGram,ProteinsGram,FatsGram,CarbohydratesGram,FoodCategory)
	values(@IdAdded,@Product_Name,@Calories_Gram,@Proteins_Gram,@Fats_Gram,@Carbohydrates_Gram,@Food_Category);
End;


--Delete from Products 
go
create Procedure DProductsDelete
		@Id int
AS
Begin
	Delete from Products where Id = @Id;
End;

--Update Products
go
Create Procedure DProductsUpdate
		@Id int,
		@Product_Name varchar(200),
		@Calories_Gram decimal(7,2),
		@Proteins_Gram decimal(7,2),
		@Fats_Gram decimal(7,2),
		@Carbohydrates_Gram decimal(7,2),
		@Food_Category varchar(50)
AS
Begin
	Update Products
	set 
	FoodCategory = @Food_Category,
	ProductName = @Product_Name,
	CaloriesGram = @Calories_Gram,
	ProteinsGram = @Proteins_Gram,
	FatsGram = @Fats_Gram,
	CarbohydratesGram = @Carbohydrates_Gram
	where Id = @Id;
End;


--***************************************************
--***************************************************

--Insert inside Reports 
go
Create Procedure DReportsAdd
		@Id_Report int,
		@Product_Name varchar(200),
		@Report_Date date,
		@Eat_Period varchar(8),
		@Day_Gram decimal(7,2),
		@Day_Calories decimal(7,2),
		@Day_Proteins decimal(7,2),
		@Day_Fats decimal(7,2),
		@Day_Carbohydrates decimal(7,2) 
AS
Begin
	Insert into Reports(IdReport,ProductName,ReportDate,EatPeriod,DayGram,DayCalories,DayProteins,DayFats,DayCarbohydrates)
	values(@Id_Report, @Product_Name, @Report_Date,@Eat_Period,@Day_Gram,@Day_Calories,@Day_Proteins,@Day_Fats,@Day_Carbohydrates);
End;


--Delete from Reports 
go
create Procedure DReportsDelete
		@Reports_Id int
AS
Begin
	Delete from Reports where Id = @Reports_Id;
End;

--Update Reports
go
Create Procedure DReportsUpdate
		@Id int, 
		@Product_Name varchar(200),
		@Report_Date date,
		@Eat_Period varchar(8),
		@Day_Gram decimal(7,2),
		@Day_Calories decimal(7,2),
		@Day_Proteins decimal(7,2),
		@Day_Fats decimal(7,2),
		@Day_Carbohydrates decimal(7,2)  
AS
Begin
	Update Reports
	set
	ProductName = @Product_Name,
	ReportDate = @Report_Date,
	EatPeriod = @Eat_Period,
	DayGram = @Day_Gram,
	DayCalories = @Day_Calories,
	DayProteins = @Day_Proteins,
	DayFats = @Day_Fats,
	DayCarbohydrates = @Day_Carbohydrates
	where Id = @Id;
End;

