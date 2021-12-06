Use DietManager;

go
--------------------Random symbol sequence procedure-----------------------
Create Procedure CreateSymbolCharSequence
	@size integer,
	@Sequence varchar(200) OUTPUT
AS
Begin
	SET @Sequence = (
	SELECT
		c1 AS [text()]
	FROM
		(
		SELECT TOP (@size) c1
		FROM
		  (
		VALUES ('А'), ('Б'), ('В'), ('Г'), ('Д'), ('Е'), ('Ё'), ('Ж'), ('З'), ('И'),('Й'), ('К'), ('Л'), ('М'), ('Н'), ('О'), ('П'), ('Р'), ('С'), ('Т'),
		  ('У'), ('Ф'), ('Х'), ('X'), ('Ц'), ('Ч'),('Ш'), ('Щ'), ('Ъ'), ('Ы'), ('Ь'), ('Э'), ('Ю'), ('Я'),
		  ('а'), ('б'), ('в'), ('г'), ('д'), ('е'), ('ё'), ('ж'), ('з'), ('и'),('й'), ('к'), ('л'), ('м'), ('н'), ('о'), ('п'), ('р'), ('с'), ('т'),
		  ('у'), ('ф'), ('х'), ('x'), ('ц'), ('ч'),('ш'), ('щ'), ('ъ'), ('ы'), ('ь'), ('э'), ('ю'), ('я')
		) AS T1(c1)
		ORDER BY ABS(CHECKSUM(NEWID()))
		) AS T2
	FOR XML PATH('')
	);
End;

go
--------------------Random number sequence procedure-----------------------
Create Procedure CreateNumberSequence
	@size integer,
	@Sequence nvarchar(100) OUTPUT
AS
Begin
	SET @Sequence = (
	SELECT
		c1 AS [text()]
	FROM
		(
		SELECT TOP (@size) c1
		FROM (VALUES ('0'), ('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8'), ('9')) AS T1(c1) ORDER BY ABS(CHECKSUM(NEWID()))
		) AS T2
	FOR XML PATH('')
	);
End;

--------------------|AI_PRODUCTS|-----------------------
go
Create Procedure AI_PRODUCTS AS
Begin
DECLARE @Product_Name varchar(200),
		@Calories_Gram decimal(7,2),
		@Proteins_Gram decimal(7,2),
		@Fats_Gram decimal(7,2),
		@Carbohydrates_Gram decimal(7,2),
		@number int;
SET @number = 1;
While @number <= 100000
	BEGIN
	DECLARE @interval_Chars int;
	DECLARE @interval_Number int;
	Declare @Food_Category varchar(50);

	set @interval_Chars = (SELECT FLOOR(RAND()*(50-10)+10));
	set @interval_Number = (SELECT FLOOR(RAND()*(2-1)+1));

	exec CreateSymbolCharSequence @interval_Chars, @Product_Name OUTPUT;
	exec CreateNumberSequence @interval_Number, @Calories_Gram OUTPUT;
	exec CreateNumberSequence @interval_Number, @Proteins_Gram OUTPUT;
	exec CreateNumberSequence @interval_Number, @Fats_Gram OUTPUT;
	exec CreateNumberSequence @interval_Number, @Carbohydrates_Gram OUTPUT;

	SET @Food_Category = (
		SELECT
			c1 AS [text()]
		FROM
			(
			SELECT TOP (1) c1
			FROM (SELECT CategoryName from FoodCategories) AS T1(c1) ORDER BY ABS(CHECKSUM(NEWID()))
			) AS T2
		FOR XML PATH('')
	);
	Insert into Products(IdAdded,ProductName,CaloriesGram,ProteinsGram,FatsGram,CarbohydratesGram,FoodCategory)
		values(null,@Product_Name,@Calories_Gram,@Proteins_Gram,@Fats_Gram,@Carbohydrates_Gram,@Food_Category);
	SET @number = @number + 1;
	END;
End;

--------------------|AI_REPORTS|-----------------------
go
Create Procedure AI_REPORTS AS
Begin
DECLARE 
		@IdReport int,
		@Product_Name varchar(200),
		@Report_Date date,
		@Eat_Period varchar(8),
		@Day_Gram decimal(8,2),
		@Day_Calories decimal(8,2),
		@Day_Proteins decimal(8,2),
		@Day_Fats decimal(8,2),
		@Day_Carbohydrates decimal(8,2),
		@number int;
SET @number = 1;
While @number <= 100000
	BEGIN
	SET @IdReport = (
	SELECT c1 AS [text()] FROM (SELECT TOP (1) c1 FROM (SELECT Id from Users) AS T1(c1) ORDER BY ABS(CHECKSUM(NEWID())) ) AS T2 FOR XML PATH('')
	);
	SET @Product_Name = (
	SELECT c1 AS [text()] FROM (SELECT TOP (1) c1 FROM (SELECT ProductName from Products) AS T1(c1) ORDER BY ABS(CHECKSUM(NEWID())) ) AS T2 FOR XML PATH('')
	);
	SET @Eat_Period = (
	SELECT c1 AS [text()] FROM (SELECT TOP (1) c1 FROM (VALUES ('Завтрак'),('Обед'),('Ужин'),('Полдник'),('Ланч')) AS T1(c1) ORDER BY ABS(CHECKSUM(NEWID())) ) AS T2 FOR XML PATH('')
	);
	SET @Report_Date = (SELECT DATEADD(DAY, FLOOR(rand()*500), '20200101'));
	SET @Day_Gram =  (SELECT FLOOR(RAND()*(200-10)+10));
	SET @Day_Calories = @Day_Gram * (SELECT TOP(1) CaloriesGram from Products where ProductName = @Product_Name) * 0.01;
	SET @Day_Proteins = @Day_Gram * (SELECT TOP(1) ProteinsGram from Products where ProductName = @Product_Name) * 0.01;
	SET @Day_Fats = @Day_Gram * (SELECT TOP(1) FatsGram from Products where ProductName = @Product_Name) * 0.01;
	SET @Day_Carbohydrates = @Day_Gram * (SELECT TOP(1) CarbohydratesGram from Products where ProductName = @Product_Name) * 0.01;

	Insert into Reports(IdReport,ProductName,ReportDate,EatPeriod,DayGram,DayCalories,DayProteins,DayFats,DayCarbohydrates)
		values(@IdReport,
		@Product_Name,
		@Report_Date,
		@Eat_Period,
		@Day_Gram,
		@Day_Calories,
		@Day_Proteins,
		@Day_Fats,
		@Day_Carbohydrates);
	SET @number = @number + 1;
	END;
End;

EXECUTE AI_PRODUCTS;
EXECUTE AI_REPORTS;


