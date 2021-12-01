
const url = "http://localhost:8080";

var currentPage = 1;


function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}

function closeAlert()
{
    $(".alert").hide('close');
}

function updateAllDiet()
{
    getAll("null",10,1,"null","breakfast");
    getAll("null",10,1,"null","lunch");
    getAll("null",10,1,"null","dinnerDay");
    getAll("null",10,1,"null","afternoon");
    getAll("null",10,1,"null","dinnerNight");

}

function getAll(table = null,limit = 10,page = 1,search=null,tablepart =null,mode=null) {       
    
    if(tablepart != "null")  
   { 
        var date = document.getElementById("DTDS").value;
        var userName = JSON.parse(window.localStorage.getItem("user")).username; 
        var table;
        fetch(url + `/user/productsDiet?username=${userName}&limit=${limit}&page=${page}&date=${date}&period=${tablepart}`, 
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
            var productsTable;
            switch(tablepart)
            {
                case "breakfast":productsTable = document.getElementById("BDT");break;
                case "lunch":productsTable = document.getElementById("LDT");break;
                case "dinnerDay":productsTable = document.getElementById("DDDT");break;
                case "afternoon":productsTable = document.getElementById("ADT");break;
                case "dinnerNight":productsTable = document.getElementById("DNDT");break;
            }
            switch(tablepart)
            {
                case "breakfast":table = "BDT";break;
                case "lunch":table = "LDT";break;
                case "dinnerDay":table="DDDT";break;
                case "afternoon":table ="ADT";break;
                case "dinnerNight":table = "DNDT";break;
            }
            productsTable.innerHTML = "";

            pdata.content.forEach(element => {
                productsTable.innerHTML += 
                `<td>${element.productName}</td> <td>${element.caloriesGram}</td> <td>${element.proteinsGram}</td>
                <td>${element.fatsGram}</td><td>${element.carbohydratesGram}</td>
                <td>
                    <button type="button" onclick="deleteR('${table}',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                    <button type="button" onclick="edit('${table}',${element.id})" data-target="#UpdateProduct" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                </td>` 
                
            });

            makePagination("null",pdata,search,tablepart);
        }
        ).catch(message => {messageShow(message)});      
    }
    else if(table == "ProductsUserCollection")
    {
        var idAdded = JSON.parse(localStorage.getItem('user')).id;

    fetch(url + `/user/productsUser?idAdded=${idAdded}&limit=${limit}&page=${page}`, 
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
        var productsTable = document.getElementById("ProductsUserCollection");
        productsTable.innerHTML = "";

        pdata.content.forEach(element => {
            productsTable.innerHTML += 
            `<td>${element.productName}</td> <td>${element.caloriesGram}</td> <td>${element.proteinsGram}</td>
            <td>${element.fatsGram}</td><td>${element.carbohydratesGram}</td>
            <td>
                <button type="button" onclick="deleteR('ProductsUserCollection',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                <button type="button" onclick="edit('ProductsUserCollection',${element.id})" data-target="#UpdateProduct" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
            </td>` 
            
        });
        makePagination("ProductsUserCollection",pdata,search,"null");
    }
    ).catch(message => {messageShow(message)});      
    }
    else if (table == "UsersParamsCollection")
    {
        var userName = localStorage.getItem('user').username;

        fetch(url + `/user/usersparams?userName=${userName}&limit=${limit}&page=${page}`, 
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
            var productsTable = document.getElementById("UsersParamsCollection");
            productsTable.innerHTML = "";
    
            pdata.content.forEach(element => {
                productsTable.innerHTML += 
                `<td>${element.userHeight}</td> <td>${element.userWeight}</td> <td>${element.paramsDate}</td>
                <td>
                    <button type="button" onclick="deleteR('UsersParamsCollection',${element.id})" class="btn btn-outline-dark rounded-0">Удалить</button>
                    <button type="button" onclick="edit('UsersParamsCollection',${element.id})" data-target="#UpdateProduct" data-toggle="modal"  class="btn btn-outline-dark rounded-0">Изменить</button>
                </td>` 
                
            });
    
            makePagination("UsersParamsCollection",pdata,search,"null");
        }
        ).catch(message => {messageShow(message)});     
    }
    else if (table == "ProductsToAddCollection")
    {
        var productNameToSearch = document.getElementById("PTADProductName").value;
        var checkCategory = document.getElementById("DACNC").checked;
        var collection = document.getElementById("ProductsToAddCollection");
    
        if(mode == "usersProducts")
        {
            var username = localStorage.getItem("user").username;

            fetch(url + `/user/products?productName=${productNameToSearch == ""? "null":productNameToSearch}&category=${checkCategory?document.getElementById("DACN").value:"null"}&username=${username}`, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
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
                    pdata.content.forEach(element => {
                        collection.innerHTML += 
                        `<div class="col-4 col-sm-6 col-md-3 col-lg-3 col-xl-3 my-1">
                        <div class="card ">
                          <div class="card-body">
                              <h5 class="card-title text-left">${element.productName}</h5>
                              <h6 class="card-subtitle mb-2 text-muted">${element.foodCategory}</h6>
                              <p class="card-text m-0">Калорий: ${element.caloriesGram} ккал</p>
                              <p class="card-text m-0">Белков: ${element.proteinsGram} грамм</p>
                              <p class="card-text m-0">Жиров: ${element.fatsGram} грамм</p>
                              <p class="card-text m-0">Углеводов: ${element.carbohydratesGram} грамм</p>
                              <div class="text-center"></div>
                              <button onclick="insertUsersDietProduct('${element.productName}')" class="btn btn-outline-dark m-0 ">Добавить</button>
                          </div>
                        </div>
                      </div>   ` 
                        
                    });
                    
                    makePagination("ProductsToAddCollection",pdata,search,"null");
                }
                ).catch(message => {messageShow(message)});      
        }
    
        if(mode == "allProducts")
        {
            fetch(url + `/user/products?productName=${productNameToSearch}&category=${checkCategory?document.getElementById("DACN").value:"null"}`, 
            {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization}
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
                    pdata.content.forEach(element => {
                        collection.innerHTML += 
                        `<div class="col-4 col-sm-6 col-md-3 col-lg-3 col-xl-3 my-1">
                        <div class="card ">
                          <div class="card-body">
                              <h5 class="card-title text-left">${element.productName}</h5>
                              <h6 class="card-subtitle mb-2 text-muted">${element.foodCategory}</h6>
                              <p class="card-text m-0">Калорий: ${element.caloriesGram} ккал</p>
                              <p class="card-text m-0">Белков: ${element.proteinsGram} грамм</p>
                              <p class="card-text m-0">Жиров: ${element.fatsGram} грамм</p>
                              <p class="card-text m-0">Углеводов: ${element.carbohydratesGram} грамм</p>
                              <div class="text-center"></div>
                              <button onclick="insertUsersDietProduct('${element.productName}')" class="btn btn-outline-dark m-0 ">Добавить</button>
                          </div>
                        </div>
                      </div>   ` 
                        
                    });
                    
                    makePagination("ProductsToAddCollection",pdata,search,"null");
                }
                ).catch(message => {messageShow(message)});      
        }
    }
}


function logOut()
{
    window.localStorage.removeItem("user");
    window.location.href = "http://localhost:8080";
}

function goToUserPage() {
    fetch(url + "/user", {headers: authHeader()}).then(response=>
        {
          console.log(response);
          if (response.status === 200) 
            {
            window.location.href = response.url;
            }
        });
}

function goToAdminPage() {
  fetch(url + "/admin", {headers: authHeader()}).then(response=>
      {
        console.log(response);
        if (response.status === 200) 
            {
            window.location.href = response.url;
            }
      });
}

window.onload = function() 
{

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
    }
    else
    {
        var user= JSON.parse(window.localStorage.getItem("user")); 
        if(user.roles[0] == "USER")
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
              <a onclick="goToUserPage()" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`;
        }
        else if(user.roles[0] == "ADMIN")
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
              <a onclick="goToUserPage()" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a onclick="goToAdminPage()" role="button" class=" dropdown-item text-decoration-none">Страница администратора</a>
              <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`;
        }
        var date = new Date();
        var curr_date = date.getDate();
        var curr_month = date.getMonth() + 1;
        var curr_year = date.getFullYear();
        var date_format = curr_year + "-" + curr_month + "-" + (curr_date < 10? '0' + curr_date: curr_date);
        document.getElementById("DTDS").value = date_format;
        document.getElementById("AUPD").value = date_format;
        updateAllDiet();
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

function getCategories()
{

    fetch(url + '/user/foodcategories_products', 
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
        let DACN =document.getElementById("DACN");
        let PTACategoryName =document.getElementById("PTACategoryName");
        pdata.forEach(element => {
            DACN.innerHTML += `<option value="${element.categoryName}">${element.categoryName}</option>`
            PTACategoryName.innerHTML += `<option value="${element.categoryName}">${element.categoryName}</option>`
        })
    });
}

function insert(table) {
    var showAlert = false;
    switch(table)
    {
        case "ProductsUserCollection":
        {
            let productName = document.getElementById("PTAProductName").value;
            let caloriesGram = document.getElementById("PTACaloriesGram").value;
            let proteinsGram = document.getElementById("PTAProteinsGram").value;
            let carbohydratesGram = document.getElementById("PTACarbohydratesGram").value;
            let fatsGram = document.getElementById("PTAFatsGram").value;
            let foodCategory = document.getElementById("PTACategoryName").value;

            $(".alert .alertMessage").text("");
            if(productName.length < 1  || productName.length >  200) {alertAddMes("Название продукта: 1-200 символов."); showAlert = true;}
            if(caloriesGram <= 0  || caloriesGram >  1000) {alertAddMes("Количество калорий на 100 грамм: 0-1000 единиц."); showAlert = true;}
            if(proteinsGram <= 0  || proteinsGram >  100) {alertAddMes("Количество белков на 100 грамм: 0-100 единиц."); showAlert = true;}
            if(carbohydratesGram <= 0  || carbohydratesGram >  100) {alertAddMes("Количество углеводов на 100 грамм: 0-100 единиц."); showAlert = true;}
            if(fatsGram <= 0  || fatsGram >  100) {alertAddMes("Количество жиров на 100 грамм: 0-100 единиц."); showAlert = true;}
            if(foodCategory.length == 0) {alertAddMes("Поле 'Название категории' не заполнено"); showAlert = true;}


            if(showAlert)
            {
                $(".alert").show('close');
                $(".alert").alert();
                return false;
            }
            closeAlert();    
            fetch(url + '/user/products', 
            {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
            body: JSON.stringify(
                {
                    idAdded :JSON.parse(localStorage.getItem("user")).id,
                    productName : productName,
                    caloriesGram : caloriesGram,
                    proteinsGram : proteinsGram,
                    fatsGram : fatsGram,
                    carbohydratesGram : carbohydratesGram,
                    foodCategory : foodCategory
                }
            )
        }).then( response => 
            {
                if (response.status === 200) 
                {
                    messageShow("Продукт добавлен успешно");
                    getAll("null",limit = 10,currentPage,search=null,"null")  
                    return;      
                }
                else
                {
                    throw "Выберите другие значения полей. Продукт не удалось добавить в коллекцию."
                }
            }
           ).catch(message => {messageShow(message)})
           break;
        }
        case "UsersParamsCollection":
            {
                let userHeight =document.getElementById("UPTAUserHeight").value;
                let userWeight =document.getElementById("UPTAUserWeight").value;
                let reportDate =document.getElementById("AUPD").value;
                $(".alert .alertMessage").text("");

                if(Date.parse(reportDate) > Date.now()) {alertAddMes("Неверная дата."); showAlert = true;}
                if(userHeight < 30  || userHeight>  300) {alertAddMes("Рост: 30-300 единиц."); showAlert = true;}
                if(userWeight < 10  || userWeight>  300) {alertAddMes("Вес: 10-300 единиц."); showAlert = true;}

                if(showAlert)
                {
                    $(".alert").show('close');
                    $(".alert").alert();
                    return false;
                }
                closeAlert();    
                fetch(url + '/user/userparams', 
                {
                method: 'POST',
                headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
                body: JSON.stringify(
                    {
                        idParams :JSON.parse(localStorage.getItem("user")).id,
                        userHeight : productName,
                        userWeight : userWeight
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

function insertUsersDietProduct(productName) {
    var showAlert = false;
    let grams = document.getElementById("DAG").value;

    $(".alert .alertMessage").text("");
    if(productName.length < 1  || productName.length >  200) {alertAddMes("Название продукта: 1-200 символов."); showAlert = true;}
    if(grams < 0  || grams >  10000) {alertAddMes("Количество грамм: 1-10000 единиц."); showAlert = true;}

    if(showAlert)
    {
        $(".alert").show('close');
        $(".alert").alert();
        return false;
    }
    closeAlert();    
    fetch(url + '/user/productsDiet', 
    {
    method: 'POST',
    headers: {'Content-Type': 'application/json', 'Authorization' : authHeader().Authorization},
    body: JSON.stringify(
        {
            productName : productName,
            grams : grams
        }
    )
    }).then( response => 
    {
        if (response.status === 200) 
        {
            messageShow("Продукт добавлен успешно");
            updateAllDiet();  
            return;      
        }
        else
        {
            throw "Выберите другие значения полей."
        }
    }
    ).catch(message => {messageShow(message)});        
}


function makePagination(table = null,pdata,search=null,tablepart = null)
{
    var Pagination;
    currentPage = pdata.currentPage;
    if(table != "null")
    {
        switch(table)
        {
            case "ProductsToAddCollection":Pagination = document.getElementById("AUDP"); break;
            case "ProductsUserCollection":Pagination = document.getElementById("PUCTP"); break;
            case "UsersParamsCollection":Pagination = document.getElementById("UPCTP"); break;         
        }
    }
    else if(tablepart != "null")
    {
        switch(tablepart)
        {
            case "breakfast":Pagination = document.getElementById("BDTP");break;
            case "lunch":Pagination = document.getElementById("LDTP");break;
            case "dinnerDay":Pagination = document.getElementById("DDDTP");break;
            case "afternoon":Pagination = document.getElementById("ADTP");break;
            case "dinnerNight":Pagination = document.getElementById("DNDTP");break;           
        }
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
            <a class="page-link" onclick="getAll('${table}',10,${pdata.currentPage - 1},'${search}','${tablepart}') " tabindex="-1">Предыдущая</a>
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
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}','${tablepart}')">${i}<span class="sr-only">(current)</span></a>
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
                <a class="page-link" onclick="getAll('${table}',10,${i - 1},'${search}','${tablepart}')">...<span class="sr-only">(current)</span></a>
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
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}','${tablepart}')">${i}<span class="sr-only">(current)</span></a>
            </li>
            `                       
        }
        if(i == pdata.currentPage + 1 && pdata.totalPages >= 5 && i <= pdata.totalPages - 2)
        {
            Pagination.innerHTML +=  
            `
            <li class="page-item">
                <a class="page-link" onclick="getAll('${table}',10,${i + 1},'${search}','${tablepart}')">...<span class="sr-only">(current)</span></a>
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
                <a class="page-link" onclick="getAll('${table}',10,${i},'${search}','${tablepart}')">${i}<span class="sr-only">(current)</span></a>
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
            <a class="page-link" onclick="getAll('${table}',10,${pdata.currentPage + 1},'${search}','${tablepart}')">Следующая</a>
        </li>
        `
    }
}