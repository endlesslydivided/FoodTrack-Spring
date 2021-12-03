use DietManager;

--Import from JSON
go
Create Procedure ImportProductsFromJSON
	@JSONData nvarchar(max)
AS
Begin

	INSERT INTO FoodCategories(CategoryName)
	SELECT DISTINCT CategoryName from
	OPENJSON(replace(replace (@JSONData,CHAR(13),''),CHAR(10),'')) WITH
		(
		CategoryName varchar(100) '$.FoodCategory'
		) AS A
	where CategoryName not in(SELECT CategoryName from FoodCategories);


	INSERT INTO Products(ProductName,CaloriesGram,ProteinsGram,FatsGram,CarbohydratesGram,FoodCategory)
	SELECT DISTINCT A.ProductName,A.CaloriesGram,A.ProteinsGram,A.FatsGram,A.CarbohydratesGram,A.FoodCategory FROM
	OPENJSON(replace(replace (@JSONData,CHAR(13),''),CHAR(10),'')) WITH
		(
		Id int '$.Id',
		ProductName varchar(200) '$.ProductName',
		CaloriesGram decimal(7,2) '$.CaloriesGram',
		ProteinsGram decimal(7,2) '$.ProteinsGram',
		FatsGram decimal(7,2) '$.FatsGram',
		CarbohydratesGram decimal(7,2) '$.CarbohydratesGram',
		FoodCategory varchar(50) '$.FoodCategory'	
		) AS A
	where ProductName not in(SELECT ProductName from Products);


End;


