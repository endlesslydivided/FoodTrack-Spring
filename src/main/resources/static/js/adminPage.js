const url = "http://localhost:8080";

var currentPage = 1;
var foodCategoriesAmount = 0;
var productsAmount = 0;
var importFinished = false;

function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}
function closeAlert()
{
    $(".alert").hide('close');
}

function update(table) {
    var showAlert = false;
    switch(table)
    {
        case "FoodCategory":
        {
            let idCategory =document.getElementById("FCTUID").textContent;
            let categoryName = document.getElementById("FCTUCategoryName").value;
            if(categoryName.length < 1  || categoryName.length >  50) {alertAddMes("Название категории: 1-50 символов."); showAlert = true;}
            if(showAlert)
            {
                $(".alert").show('close');
                $(".alert").alert();
                return false;
            }
            closeAlert();    
            fetch(url + '/admin/foodcategories/'+ idCategory, 
            {
            method: 'PUT',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
            body: JSON.stringify(
                {
                    categoryName:categoryName
                }
            )
        }).then( response => 
            {
                if (response.status === 200) 
                {
                    messageShow("Запись изменена успешно");
                    getAll(table,limit = 10,currentPage,search=null);
                    getCategories();
                    return;      
                }
                else
                {
                    throw "Данная запись нарушает ограничения целостности таблицы. Выберите другие значения полей";
                }
            }
           ).catch(message => {messageShow(message)});
           break;
        }
        case "Product":
            {
                let id = document.getElementById("PTUId").textContent;
                let idAdded = document.getElementById("PTUIdAdded").value;
                let productName = document.getElementById("PTUProductName").value;
                let caloriesGram = document.getElementById("PTUCaloriesGram").value;
                let proteinsGram = document.getElementById("PTUProteinsGram").value;
                let carbohydratesGram = document.getElementById("PTUCarbohydratesGram").value;
                let fatsGram = document.getElementById("PTUFatsGram").value;
                let foodCategory = document.getElementById("PTUCategoryName").value;

                if(productName.length < 1  || productName.length >  200) {alertAddMes("Название продукта: 1-200 символов."); showAlert = true;}
                if(caloriesGram < 0  || caloriesGram >  1000) {alertAddMes("Количество калорий на 100 грамм: 0-1000 единиц."); showAlert = true;}
                if(proteinsGram < 0  || proteinsGram >  100) {alertAddMes("Количество белков на 100 грамм: 0-100 единиц."); showAlert = true;}
                if(carbohydratesGram < 0  || carbohydratesGram >  200) {alertAddMes("Количество углеводов на 100 грамм: 0-100 единиц."); showAlert = true;}
                if(fatsGram < 0  || fatsGram >  200) {alertAddMes("Количество жиров на 100 грамм: 0-100 единиц."); showAlert = true;}
                if(foodCategory.length == 0) {alertAddMes("Поле 'Название категории' не заполнено"); showAlert = true;}


                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/products/'+ id, 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        idAdded :idAdded == "" ? null : idAdded,
                        productName : productName,
                        caloriesGram : caloriesGram,
                        proteinsGram : proteinsGram,
                        fatsGram : fatsGram,
                        carbohydratesGram : carbohydratesGram,
                        caloriesGram : carbohydratesGram,
                        foodCategory : foodCategory
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись изменена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                        return;      
                    }
                    else
                    {
                        throw "Данная запись нарушает ограничения целостности таблиц(ы). Выберите другие значения полей";
                    }
                }
               ).catch(message => {messageShow(message)});
               break;
        }
        case "Report":
            {
                let id =document.getElementById("RTUId").textContent;
                let idReport =document.getElementById("RTUIdReport").value;
                let productName =document.getElementById("RTUProductName").value;
                let reportDate =document.getElementById("RTUReportDate").value;
                let eatPeriod =document.getElementById("RTUEatPeriod").value;
                let dayGram =document.getElementById("RTUDayGram").value;

                if(productName.length < 1  || productName.length >  200) {alertAddMes("Название продукта: 1-200 символов."); showAlert = true;}
                var repDate = Date.parse(reportDate);
                if(Date.parse(reportDate) > Date.now()) {alertAddMes("Неверная дата."); showAlert = true;}
                if(dayGram < 0  || dayGram >  1000) {alertAddMes("Количество грамм: 0-1000 единиц."); showAlert = true;}
             


                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/reports/'+ id, 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        id : id,
                        idReport : idReport,
                        productName : productName,
                        reportDate : reportDate,
                        eatPeriod : eatPeriod,
                        dayGram : dayGram
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись изменена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                        return;      
                    }
                    else
                    {
                        throw "Данная запись нарушает ограничения целостности таблиц(ы). Выберите другие значения полей";
                    }
                }
               ).catch(message => {messageShow(message)});
               break;
        }
        case "User":
            {
                let id =document.getElementById("UTUId").textContent;
                let userLogin =document.getElementById("UTUUserLogin").value;
                let isAdmin =document.getElementById("UTUisAdmin").checked;
                let eMail =document.getElementById("UTUEMail").value;


                if(userLogin.length < 8  || userLogin.length >  25) {alertAddMes("Логин: 8-25 символов."); showAlert = true;}
                if(eMail.length < 10  || userLogin.length >  254) {alertAddMes("Электронная почта: 10-254 символов."); showAlert = true;}

                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/users/'+ id, 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        idEditor: JSON.parse(localStorage.getItem("user")).id,
                        admin : isAdmin ,
                        id : id,
                        userLogin : userLogin,
                        eMail: eMail
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись изменена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                    }
                    else if (response.headers.get('ErrorMessage') == "Admin change admin")
                    {
                        throw "Администратор не может изменять свои данные.";
                    }
                    else
                    {
                        throw "Данная запись нарушает ограничения целостности таблиц(ы). Выберите другие значения полей";
                    }
                }
               ).catch(message => {messageShow(message)});
               break;
        }
        case "UsersParam":
            {
                let id =document.getElementById("UPTUId").textContent;
                let idParams =document.getElementById("UPTUIdParams").textContent;
                let userHeight =document.getElementById("UPTUUserHeigth").value;
                let userWeight =document.getElementById("UPTUUserWeight").value;
                let paramsDate =document.getElementById("UPTUParamsDate").value;

                if(Date.parse(paramsDate) > Date.now()) {alertAddMes("Неверная дата."); showAlert = true;}
                if(userHeight < 30  || userHeight>  300) {alertAddMes("Рост: 30-300 единиц."); showAlert = true;}
                if(userWeight < 10  || userWeight>  300) {alertAddMes("Вес: 10-300 единиц."); showAlert = true;}


                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/usersparams/'+ id, 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        id : id,
                        idParams : idParams,
                        paramsDate : paramsDate,
                        userHeight : userHeight,
                        userWeight : userWeight
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись изменена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                        return;      
                    }
                    else
                    {
                        throw "Данная запись нарушает ограничения целостности таблиц(ы). Выберите другие значения полей";
                    }
                }
               ).catch(message => {messageShow(message)});
               break;
        }
        case "UsersData":
            {
                let id =document.getElementById("UDTUId").textContent;
                let idData =document.getElementById("UDTUIdData").textContent;
                var ValidationExpression="^[^#%&*:<>?/{|}]+$"
                let fullName =document.getElementById("UDTUFullName").value;
                let birthday =document.getElementById("UDTUBirthday").value;

                if(Date.parse(birthday) > Date.now()) {alertAddMes("Неверная дата."); showAlert = true;}
                if(fullName.length < 5  || fullName.length>  300) {alertAddMes("ФИО: 5-300 символов."); showAlert = true;}
                if (!fullName.match(ValidationExpression))
                {alertAddMes("Запрещённые символы в поле ФИО."); showAlert = true;}
                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/usersdata/'+ id, 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        id : id,
                        idData : idData,
                        fullName : fullName,
                        birthday : birthday
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись изменена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                        return;      
                    }
                    else
                    {
                        throw "Данная запись нарушает ограничения целостности таблиц(ы). Выберите другие значения полей";
                    }
                }
               ).catch(message => {messageShow(message)});
               break;
        }
    }  

}

function insert(table) {
    var showAlert = false;
    switch(table)
    {
        case "FoodCategory":
        {
            let categoryName = document.getElementById("FCTACategoryName").value;
            if(categoryName.length < 1  || categoryName.length >  50) {alertAddMes("Название категории: 1-50 символов."); showAlert = true;}
            if(showAlert)
            {
                $(".alert").show('close');
                $(".alert").alert();
                return false;
            }
            closeAlert();    
            fetch(url + '/admin/foodcategories', 
            {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
            body: JSON.stringify(
                {
                    categoryName:categoryName
                }
            )
        }).then( response => 
            {
                if (response.status === 200) 
                {
                    messageShow("Запись добавлена успешно");
                    getAll(table,limit = 10,currentPage,search=null)  
                    return;      
                }
                else
                {
                    throw "Введённые значения нарушают ограничения целостности таблиц(ы). Выберите другие значения полей"
                }
            }
           ).catch(message => {messageShow(message)})
           break;
        }
        case "Product":
            {
                let idAdded =document.getElementById("PTIIdAdded").value;
                let productName =document.getElementById("PTIProductName").value;
                let caloriesGram =document.getElementById("PTICaloriesGram").value;
                let proteinsGram =document.getElementById("PTIProteinsGram").value;
                let carbohydratesGram =document.getElementById("PTICarbohydratesGram").value;
                let fatsGram =document.getElementById("PTIFatsGram").value;
                let foodCategory =document.getElementById("PTICategoryName").textContent.replace('<option>','').replace('</option>','');

                if(productName.length < 1  || productName.length >  200) {alertAddMes("Название продукта: 1-200 символов."); showAlert = true;}
                if(caloriesGram < 1  || caloriesGram >  1000) {alertAddMes("Количество калорий на 100 грамм: 1-1000 единиц."); showAlert = true;}
                if(proteinsGram < 1  || proteinsGram >  100) {alertAddMes("Количество белков на 100 грамм: 1-100 единиц."); showAlert = true;}
                if(carbohydratesGram < 1  || carbohydratesGram >  200) {alertAddMes("Количество углеводов на 100 грамм: 1-100 единиц."); showAlert = true;}
                if(fatsGram < 1  || fatsGram >  200) {alertAddMes("Количество жиров на 100 грамм: 1-100 единиц."); showAlert = true;}
                if(foodCategory.length != 0) {alertAddMes("Поле 'Название категории' не заполнено"); showAlert = true;}

                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/admin/product', 
                {
                method: 'POST',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        idAdded :idAdded,
                        productName : productName,
                        caloriesGram : caloriesGram,
                        proteinsGram : proteinsGram,
                        fatsGram : fatsGram,
                        caloriesGram : carbohydratesGram,
                        foodCategory : foodCategory
                    }
                )
            }).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись добавлена успешно");
                        getAll(table,limit = 10,currentPage,search=null)  
                        return;      
                    }
                    else
                    {
                        throw "Введённые значения нарушают ограничения целостности таблиц(ы). Выберите другие значения полей"
                    }
                }
               ).catch(message => {messageShow(message)})
               break;
        }
       
    }
}

function getAll(table,limit = 10,page = 1,search=null) {
    switch(table)
    {
        case "FoodCategory":
        {        
            search = document.getElementById("FCTS").value != "" ? document.getElementById("FCTS").value : "null";

            fetch(url + `/admin/foodcategories?limit=${limit}&page=${page}&search=${search}`, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
            }
        ).then( response => 
            {
                if (response.status === 200) 
                {
                    return response.json();      
                }
                else
                {
                    throw "Ничего не найдено"
                }
            }
           ).then(pdata =>
            {
                var foodCategoriesTable = document.getElementById("FCT");
                foodCategoriesTable.innerHTML = "";
                foodCategoriesAmount = pdata.wholeAmount;
                pdata.content.forEach(element => {
                    foodCategoriesTable.innerHTML += 
                    `<td>${element.id}</td> <td>${element.categoryName}</td>
                    <td>
                        <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                        <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateCategory" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                    </td>` 
                    
                });

                makePagination(table,pdata);
            }
            ).catch(message => {messageShow(message)});break;
        }
        case "Product":
            {        
                search = document.getElementById("PTS").value != "" ? document.getElementById("PTS").value : "null";
                fetch(url + `/admin/products?limit=${limit}&page=${page}&search=${search}`, 
                {
                method: 'GET',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        return response.json();      
                    }
                    else
                    {
                        throw "Ничего не найдено"
                    }
                }
               ).then(pdata =>
                {
                    var productsTable = document.getElementById("PT");
                    productsTable.innerHTML = "";
                    productsAmount = pdata.wholeAmount;                    

                    pdata.content.forEach(element => {
                        productsTable.innerHTML += 
                        `<td>${element.id}</td> <td>${element.idAdded}</td> <td>${element.productName}</td>
                        <td>${element.caloriesGram}</td><td>${element.proteinsGram}</td><td>${element.fatsGram}</td> <td>${element.carbohydratesGram}</td>
                        <td>${element.foodCategory}</td>
                        <td>
                            <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                            <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateProduct" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                        </td>` 
                        
                    });
                    makePagination(table,pdata);
                    importFinished =  true;

                }
                ).catch(message => {messageShow(message);return false;});break;
        }
        case "Report":
            {        
                search = document.getElementById("RTS").value != "" ? document.getElementById("RTS").value : "null";
                fetch(url + `/admin/reports?limit=${limit}&page=${page}&search=${search}`, 
                {
                method: 'GET',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        return response.json();      
                    }
                    else
                    {
                        throw "Ничего не найдено"
                    }
                }
               ).then(pdata =>
                {
                    var reportTable = document.getElementById("RT");
                    reportTable.innerHTML = "";
                    
                    pdata.content.forEach(element => {
                        reportTable.innerHTML += 
                        `<td>${element.id}</td> <td>${element.idReport}</td> <td>${element.productName}</td>
                        <td>${(new Date(element.reportDate)).toLocaleDateString()}</td><td>${element.eatPeriod}</td><td>${element.dayGram}</td> <td>${element.dayCalories}</td>
                        <td>${element.dayProteins}</td><td>${element.dayFats}</td><td>${element.dayCarbohydrates}</td>
                        <td>
                            <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                            <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateReport" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                        </td>` 
                        
                    });
    
                    makePagination(table,pdata);
                }
                ).catch(message => {messageShow(message)});break;
        }
        case "UsersData":
            {        
                search = document.getElementById("UDTS").value != "" ? document.getElementById("UDTS").value : "null";
                fetch(url + `/admin/usersdata?limit=${limit}&page=${page}&search=${search}`, 
                {
                method: 'GET',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        return response.json();      
                    }
                    else
                    {
                        throw "Ничего не найдено"
                    }
                }
               ).then(pdata =>
                {
                    var usersDataTable = document.getElementById("UDT");
                    usersDataTable.innerHTML = "";
                    
                    pdata.content.forEach(element => {
                        usersDataTable.innerHTML += 
                        `<td>${element.id}</td> <td>${element.idData}</td> <td>${element.fullName}</td>
                        <td>${(new Date(element.birthday)).toLocaleDateString()}</td>
                        <td>
                            <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateUsersData" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                        </td>` 
                        
                    });
    
                    makePagination(table,pdata);
                }
                ).catch(message => {messageShow(message)});break;
        }
        case "UsersParam":
            {        
                search = document.getElementById("UPTS").value != "" ? document.getElementById("UPTS").value : "null";
                fetch(url + `/admin/usersparams?limit=${limit}&page=${page}&search=${search}`, 
                {
                method: 'GET',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        return response.json();      
                    }
                    else
                    {
                        throw "Ничего не найдено"
                    }
                }
               ).then(pdata =>
                {
                    var usersParamsTable = document.getElementById("UPT");
                    usersParamsTable.innerHTML = "";
                    
                    pdata.content.forEach(element => {
                        usersParamsTable.innerHTML += 
                        `<td>${element.id}</td> <td>${element.idParams}</td> <td>${(new Date(element.paramsDate)).toLocaleDateString()}</td>
                        <td>${element.userWeight}</td><td>${element.userHeight}</td>
                        <td>
                            <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                            <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateUsersParam" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                        </td>` 
                        
                    });
    
                    makePagination(table,pdata);
                }
                ).catch(message => {messageShow(message)});break;
        }
        case "User":
            {        
                search = document.getElementById("UTS").value != "" ? document.getElementById("UTS").value : "null";
                fetch(url + `/admin/users?limit=${limit}&page=${page}&search=${search}`, 
                {
                method: 'GET',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        return response.json();      
                    }
                    else
                    {
                        throw "Ничего не найдено"
                    }
                }
               ).then(pdata =>
                {
                    var usersTable = document.getElementById("UT");
                    usersTable.innerHTML = "";
                    
                    pdata.content.forEach(element => {
                        usersTable.innerHTML += 
                        `<td>${element.id}</td> <td>${element.admin}</td> <td>${element.userLogin}</td>
                        <td>${element.userPassword}</td><td>${element.email}</td>
                        <td>
                            <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                            <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateUser" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                            <button type="button" onclick="sendNotify(${element.id})"  class="btn btn-outline-dark rounded-0">Отправка уведомления</button>
                            </td>` 
                        
                    });
    
                    makePagination(table,pdata);
                }
                ).catch(message => {messageShow(message)});break;
        }
    }  
}

function makePagination(table,pdata,search=null)
{
    var Pagination;
    currentPage = pdata.currentPage;
    switch(table)
    {
        case "FoodCategory":      Pagination = document.getElementById("FoodCategoryPagination"); break;
        case "User":             Pagination = document.getElementById("UserPagination"); break;
        case "UsersData":      Pagination = document.getElementById("UsersDataPagination"); break;
        case "UsersParam":      Pagination = document.getElementById("UsersParamPagination"); break;
        case "Product":      Pagination = document.getElementById("ProductPagination"); break;
        case "Report":      Pagination = document.getElementById("ReportPagination"); break;
    }
    Pagination.innerHTML = "";
    if(pdata.currentPage == 1)
    {
        Pagination.innerHTML += 
        `
        <li class="page-item disabled">
            <a class="page-link"  tabindex="-1">Предыдущая</a>
        </li>
        `
    }
    else
    {
        Pagination.innerHTML += 
        `
        <li class="page-item">
            <a class="page-link" onclick="getAll('${table}',10,${pdata.currentPage - 1},'${search}') " tabindex="-1">Предыдущая</a>
        </li>
        `
    }


    for(i = 1; i <= 3 && i <= pdata.totalPages;i++)
    {
        if(i == pdata.currentPage)
        {
            continue;
        }
        else if ( pdata.currentPage >= 6)
        {
            Pagination.innerHTML +=  
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}')">${i}<span class="sr-only">(current)</span></a>
            </li>
            `                       
        }
    }
    for(i = (pdata.currentPage - 2 >= 1? pdata.currentPage - 1 : 1); i <= pdata.currentPage + 3 && i <= pdata.totalPages;i++)
    {
        if(i == pdata.currentPage - 1 && pdata.totalPages >= 5 && i >= 2)
        {
            Pagination.innerHTML +=  
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i - 1},'${search}')">...<span class="sr-only">(current)</span></a>
            </li>
            ` 
        }
        if(i == pdata.currentPage)
        {
            Pagination.innerHTML += 
            `
            <li class="page-item active">
                <a class="page-link">${pdata.currentPage}<span class="sr-only">(current)</span></a>
            </li>
            `
        }
        else
        {
            Pagination.innerHTML +=  
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}')">${i}<span class="sr-only">(current)</span></a>
            </li>
            `                       
        }
        if(i == pdata.currentPage + 1 && pdata.totalPages >= 5 && i <= pdata.totalPages - 2)
        {
            Pagination.innerHTML +=  
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i + 1},'${search}')">...<span class="sr-only">(current)</span></a>
            </li>
            ` 
            break; 
        }
    }

    for(i = pdata.totalPages - 2;i <= pdata.totalPages;i++)
    {
        if(i <= pdata.currentPage + 2)
        {
            continue;
        }
        else
        {
            Pagination.innerHTML += 
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}')">${i}<span class="sr-only">(current)</span></a>
            </li>
            `   
        }
    }

    if(pdata.currentPage == pdata.totalPages)
    {
        Pagination.innerHTML += 
        `
        <li class="page-item disabled">
            <a class="page-link" >Следующая</a>
        </li>
        `
    }
    else
    {
        Pagination.innerHTML += 
        `
        <li class="page-item">
            <a class="page-link" onclick="getAll('${table}',10,${pdata.currentPage + 1},'${search}')">Следующая</a>
        </li>
        `
    }
}

function deleteR(table,id)
{
    switch(table)
    {
        case "FoodCategory":
        {
            fetch(url + '/admin/foodcategories/' + id, 
            {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json', "Authorization" : authHeader().Authorization}
            }
            ).then(() =>
                {
                    messageShow("Запись удалена успешно");
                    getAll(table,limit = 10,currentPage,search=null);
                }
            )
        }
        case "Product":
            {
                fetch(url + '/admin/products/' + id, 
                {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json', "Authorization" : authHeader().Authorization}
                }
                ).then(() =>
                    {
                        messageShow("Запись удалена успешно");
                        getAll(table,limit = 10,currentPage,search=null);
                    }
                )
            }
        case "UsersParam":
        {
            fetch(url + '/admin/usersparams/' + id, 
                {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                }
            ).then( response => 
                {
                    if (response.status === 200) 
                    {
                        messageShow("Запись удалена успешно");
                        getAll(table,10,1,'null','null');
                    }                 
                    else
                    {
                        if(response.headers.get('ErrorMessage') == "Last users params")
                        throw "Нельзя удалить единственную запись параметров пользователя.";
                    }
                
                }).catch(message => {messageShow(message)});
        }
        case "User":
            {
                fetch(url + `/admin/users/${id}/${JSON.parse(localStorage.getItem('user')).id}`,
                    {
                    method: 'DELETE',
                    headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
                    }
                ).then( response => 
                    {
                        if (response.status === 200) 
                        {
                            messageShow("Запись удалена успешно");
                            getAll(table,10,1,'null','null');
                        }                 
                        else
                        {
                            if(response.headers.get('ErrorMessage') == "User self delete")
                            throw "Нельзя удалить данную пользовательскую запись";
                            else 
                            throw "Ошибка удаления";
                        }
                    
                    }).catch(message => {messageShow(message)});
                }
    }

}

function getCategories()
{

    fetch(url + '/admin/foodcategories_products', 
    {
    method: 'GET',
    headers: {'Content-Type': 'application/json', "Authorization" : authHeader().Authorization}
    }
    ).then((response) =>
        {
            return response.json();
        }
    ).then((pdata)=>
    {
        let categoryNameSelectI =document.getElementById("PTICategoryName");
        let categoryNameSelectU =document.getElementById("PTUCategoryName");
        categoryNameSelectI.innerHTML = "";
        categoryNameSelectU.innerHTML  = "";
        pdata.forEach(element => {
            categoryNameSelectI.innerHTML += `<option value="${element.categoryName}">${element.categoryName}</option>`
            categoryNameSelectU.innerHTML += `<option value="${element.categoryName}">${element.categoryName}</option>`
        })
    });
}

function edit(table,id) 
{
    switch(table)
    {
        case "FoodCategory":
        {
            fetch(url + '/admin/foodcategories/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    document.getElementById("FCTUID").textContent = pdata.id;
                    document.getElementById("FCTUCategoryName").value = pdata.categoryName;
                });break;
        }
        case "Product":
        {
            fetch(url + '/admin/products/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    document.getElementById("PTUId").textContent = pdata.id;
                    document.getElementById("PTUIdAdded").value = pdata.idAdded;
                    document.getElementById("PTUProductName").value = pdata.productName;
                    document.getElementById("PTUCaloriesGram").value = pdata.caloriesGram;
                    document.getElementById("PTUProteinsGram").value = pdata.proteinsGram;
                    document.getElementById("PTUCarbohydratesGram").value = pdata.carbohydratesGram;
                    document.getElementById("PTUFatsGram").value = pdata.fatsGram;
                    for (let element of document.getElementById("PTUCategoryName").options) {
                        if(element.value == pdata.foodCategory)
                            {
                                element.selected = true;
                                break;
                            }
                    }
                });break;
        }
        case "Report":
        {
            fetch(url + '/admin/reports/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    var date = new Date(pdata.reportDate);
                    var curr_date = date.getDate();
                    var curr_month = date.getMonth() + 1;
                    var curr_year = date.getFullYear();
                    var date_format = curr_year + "-" + (curr_month < 10? '0' + curr_month: curr_month) + "-" + (curr_date < 10? '0' + curr_date: curr_date);
                    document.getElementById("RTUId").textContent = pdata.id;
                    document.getElementById("RTUIdReport").value = pdata.idReport;
                    document.getElementById("RTUProductName").value = pdata.productName;
                    document.getElementById("RTUReportDate").value = date_format;
                    document.getElementById("RTUEatPeriod").value = pdata.eatPeriod;
                    document.getElementById("RTUDayGram").value = pdata.carbohydratesGram;
                });break;
        }
        case "User":
        {
            fetch(url + '/admin/users/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    document.getElementById("UTUId").textContent = pdata.id;
                    document.getElementById("UTUisAdmin").checked = pdata.admin;
                    document.getElementById("UTUUserLogin").value = pdata.userLogin;
                });break;
        }
        case "UsersData":
        {
            fetch(url + '/admin/usersdata/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    var date = new Date(pdata.birthday);
                    var curr_date = date.getDate();
                    var curr_month = date.getMonth() + 1;
                    var curr_year = date.getFullYear();
                    var date_format = curr_year + "-" + (curr_month < 10? '0' + curr_month: curr_month) + "-" + (curr_date < 10? '0' + curr_date: curr_date);
                    document.getElementById("UDTUId").textContent = pdata.id;
                    document.getElementById("UDTUIdData").textContent = pdata.idData;
                    document.getElementById("UDTUFullName").value = pdata.fullName;
                    document.getElementById("UDTUBirthday").value = date_format;
                });break;
        }
        case "UsersParam":
        {
            fetch(url + '/admin/usersparams/' + id, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
            }
            ).then( response => 
                {
                    return response.json();
                }
            ).then( pdata=>
                {
                    var date = new Date(pdata.paramsDate);
                    var curr_date = date.getDate();
                    var curr_month = date.getMonth() + 1;
                    var curr_year = date.getFullYear();
                    var date_format = curr_year + "-" + (curr_month < 10? '0' + curr_month: curr_month) + "-" + (curr_date < 10? '0' + curr_date: curr_date);
                    document.getElementById("UPTUId").textContent = pdata.id;
                    document.getElementById("UPTUIdParams").textContent = pdata.idParams;
                    document.getElementById("UPTUUserWeight").value = pdata.userWeight;
                    document.getElementById("UPTUUserHeigth").value = pdata.userHeight;
                    document.getElementById("UPTUParamsDate").value = date_format;
                });break;
        }
    }

}

function logOut()
{
    window.localStorage.removeItem("user");
    window.location.href ="http://localhost:8080";
    delete_cookie('username');
    delete_cookie('id');
    delete_cookie('token');
    delete_cookie('roles');

}

window.onload = function() 
{

    var navLinks = document.getElementById('mySidenav');
    var actionButtons = document.getElementById('actionButtons');
    if(window.localStorage.getItem('user') == undefined)
    {
        actionButtons.innerHTML =`  
      <a href="/signIn" class=" text-decoration-none">
        <input type="button" value="Вход" class="btn btn-secondary border"/>
      </a>
      <a href="/signUp" class=" text-decoration-none">
        <input type="button" value="Регистрация" class="btn btn-secondary border"/>
      </a>`;

      navLinks.innerHTML += `
      <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>
      <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>`
    }
    else
    {
        var user= JSON.parse(window.localStorage.getItem("user")); 
        if(user.roles[0] == "ROLE_USER")
        { 
          actionButtons.innerHTML = 
          `<p class="m-0">Привет,${user.username}</p>
          <div class="dropdown">
          <a class="btn btn-floating m-1 ripple-surface"
          id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
          role="button">
              <i class="far fa-user"></i>
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenu">
              <a href="/user"  role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`;
          navLinks.innerHTML += `
          <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>

          <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
          <h6><a  href="/user" >Страница пользователя</a></h6>
          `
        }
        else if(user.roles[0] == "ROLE_ADMIN")
        {
          actionButtons.innerHTML = `
          <p class="m-0">Привет,${user.username} </p>
          <div class="dropdown">
          <a class="btn btn-floating m-1 ripple-surface"
          id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
          role="button">
              <i class="far fa-user"></i>
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenu">
              <a href="/user"  role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a href="/admin" role="button" class=" dropdown-item text-decoration-none">Страница администратора</a>
              <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`;

          navLinks.innerHTML += `
          <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>
            <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
            <h6><a href="/user" >Страница пользователя</a></h6>
            <h6><a href="/admin" >Страница администратора</a></h6>
            `
        }
          getAll("FoodCategory");
          getCategories();
        }
    
};

function messageShow(message)
{
    $(".alert .alertMessage").text("");
    alertAddMes(message);
    $(".alert").show('close');
    $(".alert").alert();
}

function importProductsJSON()
{

    var input = document.createElement('input');
    var foodCategoriesAmountBefore =  foodCategoriesAmount;
    var productsAmountBefore =  productsAmount;
    input.type = 'file';

    input.onchange = e => { 
        var file = e.target.files[0]; 
        let reader = new FileReader();
        reader.readAsText(file);
        $('#importUpdate').modal('show');

        document.getElementById('PIJSONMH').innerHTML = `<h5 class="modal-title">Осуществляется выполнение процедуры.</h5>`;
        document.getElementById('PIJSONMB').innerHTML = `<span>Ожидайте.</span>`
        reader.onload = function() 
        {
          var result = reader.result;
          fetch(url + `/admin/products/importJSON`, 
          {
          method: 'POST',
          headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization},
          body:JSON.stringify(
            {
                valueString : result
            })
          }).then(response => {
              if(response.status === 200)
              {
                getAll('FoodCategory');getAll('Product');
                var  timer = setInterval(() => {
                    if(importFinished)
                    {
                        document.getElementById('PIJSONMH').innerHTML = `<h5 class="modal-title">Выполнение процедуры завершено.</h5>
                        <button class="btn  btn-outline-dark close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true" style="text-align: center;">&times;</span>
                          </button>`;
                        document.getElementById('PIJSONMB').innerHTML = `Вставлено строк в таблицу Products: ${productsAmount - productsAmountBefore}.
                        Вставлено строк в таблицу FoodCategories: ${foodCategoriesAmount - foodCategoriesAmountBefore}.`;
                        clearInterval(timer);
                    }
                }, 8000);         
            }
              else
              {
                $('#importUpdate').modal('hide');
                throw `Ошибка выполнения процедуры` ;
              }
            }).catch(message => { messageShow(message); });
           
        };
       

    }
    input.click();
  

    }

function exportProductsJSON() {
    fetch(url + `/admin/products/exportJSON`, 
        {
        method: 'GET',
        headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
        }
    ).then(response =>
        {
            return response.json();
        }).then(pdata =>
            {
                var blob = new Blob([JSON.stringify(pdata)],  {type : 'application/json'});
                var link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = 'products.json';
                link.click();
            });
  }

function sendNotify(id)
{
    fetch(url + `/sendNotify/${id}`, 
    {
    method: 'GET',
    headers: {'Content-Type': 'application/json','Authorization' : authHeader().Authorization}
    }
    ).then(response =>
    {
        console.log(response);
    });
}