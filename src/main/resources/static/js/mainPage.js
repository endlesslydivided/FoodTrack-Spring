
const url = "http://localhost:8080";

function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}
function  closeAlert()
{
    $(".alert").hide('close');
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
          getAll("FoodCategory");
          getCategories();
        }
    }
};