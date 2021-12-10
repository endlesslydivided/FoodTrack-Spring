use DietManagerSpring;

--Users Table indexes
CREATE UNIQUE NONCLUSTERED INDEX index_UsersUL ON Users(UserLogin);
CREATE UNIQUE NONCLUSTERED INDEX index_UsersULAsc ON Users(UserLogin Asc);

--FoodCategories Table indexes
CREATE UNIQUE NONCLUSTERED INDEX index_FoodCategoriesCN ON FoodCategories(CategoryName);
CREATE UNIQUE NONCLUSTERED INDEX index_FoodCategoriesCNAsc ON FoodCategories(CategoryName ASC);

--UsersData Table indexes
CREATE UNIQUE NONCLUSTERED INDEX index_DataID ON UsersData(IdData);
CREATE NONCLUSTERED INDEX index_DataFN ON UsersData(FullName);
CREATE NONCLUSTERED INDEX index_DataBD ON UsersData(Birthday);

CREATE UNIQUE NONCLUSTERED INDEX index_DataIDAsc ON UsersData(IdData ASC);
CREATE NONCLUSTERED INDEX index_DataFNAsc ON UsersData(FullName ASC);
CREATE NONCLUSTERED INDEX index_DataBDAsc ON UsersData(Birthday ASC);

--UsersParams Table indexes
CREATE NONCLUSTERED INDEX index_ParamsIP ON UsersParams(IdParams);
CREATE NONCLUSTERED INDEX index_ParamsPD ON UsersParams(ParamsDate);
CREATE NONCLUSTERED INDEX index_ParamsUW ON UsersParams(UserWeight);
CREATE NONCLUSTERED INDEX index_ParamsUH ON UsersParams(UserHeight);

CREATE NONCLUSTERED INDEX index_ParamsIPAsc ON UsersParams(IdParams ASC);
CREATE NONCLUSTERED INDEX index_ParamsPDAsc ON UsersParams(ParamsDate ASC);
CREATE NONCLUSTERED INDEX index_ParamsUWAsc ON UsersParams(UserWeight ASC);
CREATE NONCLUSTERED INDEX index_ParamsUHAsc ON UsersParams(UserHeight ASC);

--Reports Table indexes
CREATE NONCLUSTERED INDEX index_ReportsIR ON Reports(IdReport);
CREATE NONCLUSTERED INDEX index_ReportsPN ON Reports(ProductName);
CREATE NONCLUSTERED INDEX index_ReportsRD ON Reports(ReportDate);
CREATE NONCLUSTERED INDEX index_ReportsEP ON Reports(EatPeriod);
CREATE NONCLUSTERED INDEX index_ReportsDG ON Reports(DayGram);
CREATE NONCLUSTERED INDEX index_ReportsDCal ON Reports(DayCalories);
CREATE NONCLUSTERED INDEX index_ReportsDP ON Reports(DayProteins);
CREATE NONCLUSTERED INDEX index_ReportsDF ON Reports(DayFats);
CREATE NONCLUSTERED INDEX index_ReportsDCar ON Reports(DayCarbohydrates);

CREATE NONCLUSTERED INDEX index_ReportsIRAsc ON Reports(IdReport ASC);
CREATE NONCLUSTERED INDEX index_ReportsPNAsc ON Reports(ProductName ASC);
CREATE NONCLUSTERED INDEX index_ReportsRDAsc ON Reports(ReportDate ASC);
CREATE NONCLUSTERED INDEX index_ReportsEPAsc ON Reports(EatPeriod ASC);
CREATE NONCLUSTERED INDEX index_ReportsDGAsc ON Reports(DayGram ASC);
CREATE NONCLUSTERED INDEX index_ReportsDCalAsc ON Reports(DayCalories ASC);
CREATE NONCLUSTERED INDEX index_ReportsDPAsc ON Reports(DayProteins ASC);
CREATE NONCLUSTERED INDEX index_ReportsDFAsc ON Reports(DayFats ASC);
CREATE NONCLUSTERED INDEX index_ReportsDCarAsc ON Reports(DayCarbohydrates ASC);

--Products Table indexes
CREATE UNIQUE NONCLUSTERED INDEX index_ProductsPN ON Products(ProductName);
CREATE NONCLUSTERED INDEX index_ProductsIA ON Products(IdAdded);
CREATE NONCLUSTERED INDEX index_ProductsCalG ON Products(CaloriesGram);
CREATE NONCLUSTERED INDEX index_ProductsPG ON Products(ProteinsGram);
CREATE NONCLUSTERED INDEX index_ProductsCarG ON Products(CarbohydratesGram);
CREATE NONCLUSTERED INDEX index_ProductsFG ON Products(FoodCategory);
CREATE NONCLUSTERED INDEX index_ProductsCalGAsc ON Products(CaloriesGram ASC);
CREATE NONCLUSTERED INDEX index_ProductsPGAAsc ON Products(ProteinsGram ASC);
CREATE NONCLUSTERED INDEX index_ProductsCarGAsc ON Products(CarbohydratesGram ASC);
CREATE NONCLUSTERED INDEX index_ProductsFGAsc ON Products(FoodCategory ASC);
CREATE NONCLUSTERED INDEX index_ProductsIAAsc ON Products(IdAdded ASC);
CREATE UNIQUE NONCLUSTERED INDEX index_ProductsPNAsc ON Products(ProductName ASC);
