use DietManager;

-- Select from Users
go 
Create procedure DUsersSelect @start int, @end int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Users
	) 
	SELECT Id,IsAdmin, UserLogin,UserPassword FROM num_row
	WHERE nom BETWEEN @start AND @end;
	 
end;

go 
Create Procedure DUsersSelectId @Id int
AS
Begin
	SELECT * from Users where Id = 1;
end;

go 
Create Procedure DUsersSelectLogin @Login nvarchar(max)
AS
Begin
	SELECT * from Users where UserLogin = @Login;
end;

-- Select from UsersParams
go 
Create procedure DUsersParamsSelect @start int, @end int
AS
begin
	 	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from UsersParams
	) 
	SELECT Id,IdParams,ParamsDate,UserWeight,UserHeight FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

go 
Create Procedure DUsersParamsSelectId @Id int
AS
Begin
	SELECT * from UsersParams where Id = @Id;
end;

-- Select from UsersData
go 
Create procedure DUsersDataSelect @start int, @end int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from UsersData
	) 
	SELECT Id,IdData,FullName,Birthday FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

go 
Create Procedure DUsersDataSelectId @Id int
AS
Begin
	SELECT * from UsersData where Id = @Id;
end;


-- Select from Products
go 
Create procedure DProductsSelect @start int, @end int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Products
	) 
	SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

go 
Create Procedure DProductsSelectId @Id int
AS
Begin
	SELECT * from Products where Id = @Id;
end;

go 
Create Procedure DProductsSelectName @Name nvarchar(max)
AS
Begin
	SELECT * from Products where ProductName = @Name;
end;


-- Select from Reports
go 
Create procedure DReportsSelect @start int, @end int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Reports
	) 
	SELECT Id,IdReport,ProductName,ReportDate,EatPeriod,DayGram,DayCalories,DayProteins,DayFats,DayCarbohydrates FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

go 
Create Procedure DReportsSelectId @Id int
AS
Begin
	SELECT * from Reports where Id = @Id;
end;


-- Select from FoodCategories
go 
Create procedure DFoodCategoriesSelect @start int, @end int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from FoodCategories
	) 
	SELECT Id,CategoryName FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

	

go 
Create Procedure DFoodCategoriesSelectId @Id int
AS
Begin
	SELECT * from FoodCategories where Id = @Id;
end;

--Count Select
go 
Create Procedure DUsersSelectCount 
AS
Begin
	SELECT Count(*) from Users;
end;

go 
Create Procedure DProductsSelectCount 
AS
Begin
	SELECT Count(*) from Products;
end;


go 
Create Procedure DUsersParamsSelectCount 
AS
Begin
	SELECT Count(*) from UsersParams;
end;

go 
Create Procedure DUsersDataSelectCount 
AS
Begin
	SELECT Count(*) from UsersData;
end;

go 
Create Procedure DReportsSelectCount 
AS
Begin
	SELECT Count(*) from Reports;
end;

go 
Create Procedure DFoodCategoriesSelectCount 
AS
Begin
	SELECT Count(*) from FoodCategories;
end;



go 
Create Procedure DUsersSelectCountS @Search nvarchar(max)
AS
Begin
	SELECT Count(*) from Users where (Id like '%' + @Search + '%' or UserLogin like '%' + @Search + '%' or UserPassword like '%' + @Search + '%' or IsAdmin like '%' + @Search + '%' );
end;

go 
Create Procedure DProductsSelectCountS @Search nvarchar(max)  
AS
Begin
	SELECT Count(*) from Products where (Id like '%' + @Search + '%' or IdAdded like '%' + @Search + '%' or ProductName like '%' + @Search + '%' or 
	CaloriesGram like '%' + @Search + '%' or ProteinsGram  like '%' + @Search + '%' or FatsGram like '%' + @Search + '%' or CarbohydratesGram like '%' + @Search + '%' or
	FoodCategory like '%' + @Search + '%');
end;

go 
Create procedure DProductsSelectUserCount @Id int
AS
begin
	SELECT  count(*) from Products where IdAdded = @Id
end;

go 
Create Procedure DUsersParamsSelectCountS @Search nvarchar(max) 
AS
Begin
	SELECT Count(*) from UsersParams where (Id like '%' + @Search + '%' or IdParams like '%' + @Search + '%'  or ParamsDate like '%' + @Search + '%'  or
	UserWeight like '%' + @Search + '%'  or UserHeight like '%' + @Search + '%');
end;

go 
Create Procedure DUsersParamsSelectCountIdParams @Id int
AS
Begin
	SELECT count(*)from UsersParams where IdParams = @Id
end;

go 
Create Procedure DUsersDataSelectCountS @Search nvarchar(max) 
AS
Begin
	SELECT Count(*) from UsersData where (Id like '%' + @Search + '%' or IdData like '%' + @Search + '%' or FullName like '%' + @Search + '%' or
	Birthday like '%' + @Search + '%');
end;

go 
Create Procedure DReportsSelectCountS @Search nvarchar(max) 
AS
Begin
	SELECT Count(*) from Reports where (Id like '%' + @Search + '%' or IdReport like '%' + @Search + '%' or ProductName like '%' + @Search + '%' or
	ReportDate like '%' + @Search + '%' or
	EatPeriod like '%' + @Search + '%' or
	DayGram like '%' + @Search + '%' or
	DayCalories like '%' + @Search + '%' or
	DayProteins like '%' + @Search + '%' or
	DayFats like '%' + @Search + '%' or
	DayCarbohydrates like '%' + @Search + '%');
end;

go 
Create Procedure DFoodCategoriesSelectCountS @Search nvarchar(max) 
AS
Begin
	SELECT Count(*) from FoodCategories where (Id like '%' + @Search + '%' or CategoryName like '%' + @Search + '%');
end;

go 
Create procedure DProductsSelectUserCountS @Id int, @Seacrh nvarchar(max) 
AS
begin
	SELECT count(*) from Products where IdAdded = @Id and ProductName like '%' + @Seacrh +'%';
end;

go 
Create procedure DReportsSelectDatePeriodCount @Date nvarchar(max), @Period nvarchar(max),@Id int
AS
begin
		SELECT Count(*) from Reports where IdReport = @Id and EatPeriod like '%' + @Period + '%' and ReportDate like @Date
end;

go 
Create procedure DProductsSelectCategoryUserCountS @Id int, @Seacrh nvarchar(max) ,@Category nvarchar(max)
AS
begin
	if(@Seacrh like 'null')
	begin
		SELECT count(*) from Products where IdAdded = @Id and FoodCategory = @Category
	end
	else
	begin
			SELECT count(*) from Products where IdAdded = @Id and ProductName like '%' + @Seacrh +'%'and FoodCategory = @Category
	end
end;

go 
Create procedure DProductsSelectUserS @start int, @end int, @Id int, @Seacrh nvarchar(max) 
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Products where IdAdded = @Id and ProductName like '%' + @Seacrh +'%'
	) 
	SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
	WHERE (nom BETWEEN @start AND @end);
end;

go 
Create procedure DProductsSelectCategoryCountS @Seacrh nvarchar(max) ,@Category nvarchar(max)
AS
begin
	if(@Seacrh like 'null')
	begin
		SELECT count(*) from Products where FoodCategory = @Category
	end
	else
	begin
			SELECT count(*) from Products where ProductName like '%' + @Seacrh +'%'and FoodCategory = @Category
	end
end;



-- Search Procedures
go 

Create procedure DUsersSelectS @start int, @end int, @Search nvarchar(max)
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Users where Id like '%' + @Search + '%' or UserLogin like '%' + @Search + '%' or UserPassword like '%' + @Search + '%' or IsAdmin like '%' + @Search + '%'
	) 
	SELECT Id,IsAdmin, UserLogin,UserPassword FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
	 
end;


go 
Create procedure DUsersParamsSelectS @start int, @end int , @Search nvarchar(max)
AS
begin
	 	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from UsersParams where Id like '%' + @Search + '%' or IdParams like '%' + @Search + '%'  or ParamsDate like '%' + @Search + '%'  or
		UserWeight like '%' + @Search + '%'  or UserHeight like '%' + @Search + '%'
	) 
	SELECT Id,IdParams,ParamsDate,UserWeight,UserHeight FROM num_row
	WHERE (nom BETWEEN @start AND @end);
end;

go 
Create Procedure DUsersParamsSelectIdParams @start int, @end int, @Id int
AS
Begin
 	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from UsersParams where IdParams = @Id
	) 
	SELECT Id,IdParams,ParamsDate,UserWeight,UserHeight FROM num_row
	WHERE nom BETWEEN @start AND @end;
end;

go 
Create procedure DUsersDataSelectS @start int, @end int, @Search nvarchar(max)
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from UsersData where Id like '%' + @Search + '%' or IdData like '%' + @Search + '%' or FullName like '%' + @Search + '%' or
		Birthday like '%' + @Search + '%'
	) 
	SELECT Id,IdData,FullName,Birthday FROM num_row
	WHERE (nom BETWEEN @start AND @end)  ;
end;


go 
Create procedure DProductsSelectS @start int, @end int, @Search nvarchar(max)
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Products where Id like '%' + @Search + '%' or IdAdded like '%' + @Search + '%' or ProductName like '%' + @Search + '%' or 
		CaloriesGram like '%' + @Search + '%' or ProteinsGram  like '%' + @Search + '%' or FatsGram like '%' + @Search + '%' or CarbohydratesGram like '%' + @Search + '%' or
		FoodCategory like '%' + @Search + '%'
	) 
	SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
end;

go 
Create procedure DReportsSelectS @start int, @end int, @Search nvarchar(max)
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Reports where Id like '%' + @Search + '%' or IdReport like '%' + @Search + '%' or ProductName like '%' + @Search + '%' or
		ReportDate like '%' + @Search + '%' or
		EatPeriod like '%' + @Search + '%' or
		DayGram like '%' + @Search + '%' or
		DayCalories like '%' + @Search + '%' or
		DayProteins like '%' + @Search + '%' or
		DayFats like '%' + @Search + '%' or
		DayCarbohydrates like '%' + @Search + '%'
	) 
	SELECT Id,IdReport,ProductName,ReportDate,EatPeriod,DayGram,DayCalories,DayProteins,DayFats,DayCarbohydrates FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
end;

go 
Create procedure DFoodCategoriesSelectS @start int, @end int, @Search nvarchar(max)
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from FoodCategories where Id like '%' + @Search + '%' or CategoryName like '%' + @Search + '%'
	) 
	SELECT Id,CategoryName FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
end;

go 
Create procedure DReportsSelectDatePeriod @start int, @end int, @Date nvarchar(max), @Period nvarchar(max),@Id int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Reports where IdReport = @Id and EatPeriod like '%' + @Period + '%' and ReportDate like @Date
	) 
	SELECT Id,IdReport,ProductName,ReportDate,EatPeriod,DayGram,DayCalories,DayProteins,DayFats,DayCarbohydrates FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
end;

go 
Create procedure DProductsSelectUser @start int, @end int, @Id int
AS
begin
	WITH num_row AS
	(
		SELECT row_number() OVER (ORDER BY Id) as nom , 		
		* from Products where IdAdded = @Id
	) 
	SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
	WHERE (nom BETWEEN @start AND @end) ;
end;

go 
Create procedure DProductsSelectCategoryUserS @start int, @end int, @Id int, @Seacrh nvarchar(max) ,@Category nvarchar(max)
AS
begin
	if(@Seacrh like 'null')
	begin
		WITH num_row AS
		(
			SELECT row_number() OVER (ORDER BY Id) as nom , 		
			* from Products where IdAdded = @Id and FoodCategory = @Category
		) 
		SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
		WHERE (nom BETWEEN @start AND @end)
	end
	else
	begin
		WITH num_row AS
		(
			SELECT row_number() OVER (ORDER BY Id) as nom , 		
			* from Products where IdAdded = @Id and ProductName like '%' + @Seacrh +'%'and FoodCategory = @Category
		) 
		SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
		WHERE (nom BETWEEN @start AND @end)
	end
end;

go 
Create procedure DProductsSelectCategoryS @start int, @end int, @Seacrh nvarchar(max) ,@Category nvarchar(max)
AS
begin
	if(@Seacrh like 'null')
	begin
		WITH num_row AS
		(
			SELECT row_number() OVER (ORDER BY Id) as nom , 		
			* from Products where FoodCategory = @Category
		) 
		SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
		WHERE (nom BETWEEN @start AND @end)
	end
	else
	begin
		WITH num_row AS
		(
			SELECT row_number() OVER (ORDER BY Id) as nom , 		
			* from Products where ProductName like '%' + @Seacrh +'%'and FoodCategory = @Category
		) 
		SELECT Id,IdAdded,ProductName,CaloriesGram,ProteinsGram,CarbohydratesGram,FatsGram,FoodCategory FROM num_row
		WHERE (nom BETWEEN @start AND @end)
	end
end;


--Select All Procedures

go 
Create procedure DFoodCategoriesSelectAll 
AS
begin
	SELECT * FROM FoodCategories;
end;

go 
Create procedure DProductsSelectAll 
AS
begin
	SELECT * FROM Products;
end;

go 
Create procedure DReportsSelectAll 
AS
begin
	SELECT * FROM Reports;
end;

go 
Create procedure DUsersDataSelectAll 
AS
begin
	SELECT * FROM UsersData;
end;

go 
Create procedure DUsersParamsSelectAll 
AS
begin
	SELECT * FROM UsersParams;
end;

go 
Create procedure DUsersSelectAll 
AS
begin
	SELECT * FROM Users;
end;
