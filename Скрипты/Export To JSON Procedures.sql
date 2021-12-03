use DietManager;

--Export ProductsJSON
go
Create Procedure ExportProductsJSON
	@filePath nvarchar(256)
AS
Begin

	EXEC master.dbo.sp_configure 'show advanced options', 1
		RECONFIGURE WITH OVERRIDE
	EXEC master.dbo.sp_configure 'xp_cmdshell', 1
		RECONFIGURE WITH OVERRIDE;
	
	DECLARE @sqlStr VARCHAR(1000)
	DECLARE @sqlCmd VARCHAR(1000)

	SET @sqlStr = 'USE DietManager; SELECT * FROM Products FOR JSON PATH '
	SET @sqlCmd = 'bcp "' + @sqlStr + '" queryout ' + @filePath + ' -t, -w -S . -d master -T'
	EXEC xp_cmdshell @sqlCmd;
End;

DROP procedure ExportProductsJSON;
