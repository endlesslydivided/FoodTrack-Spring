
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

  var navLinks = document.getElementById('mySidenav');
  var actionButtons = document.getElementById('actionButtons');
  var mainPageContent = document.getElementById('mainPageContent');
  if(window.localStorage.getItem('user') == undefined)
  {
      actionButtons.innerHTML =`  
    <a href="/signIn" class=" text-decoration-none">
      <input type="button" value="Вход" class="btn btn-secondary border"/>
    </a>
    <a href="/signUp" class=" text-decoration-none">
      <input type="button" value="Регистрация" class="btn btn-secondary border"/>
    </a>`;
    mainPageContent.innerHTML +=
    `
    <div class="col-6 p-5">
          <h5>Войдите в аккаунт или зарегистрируйтесь для пользования функционалом приложения</h5>
        </div>
        <div class="col-6 p-5">
        <a href="/signIn" class=" text-decoration-none">
          <input type="button" value="Вход" class="btn w-100 rounded-0  btn-outline-dark border-dark m-2 "/>
        </a>
        <a href="/signUp" class=" text-decoration-none">
          <input type="button" value="Регистрация" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
        </a>
        </div>
    `
    navLinks.innerHTML += `
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
            <a href="/user" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
            <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
        </div>
        </div>`
        mainPageContent.innerHTML +=
    `
    <div class="col-6 p-5">
          <h5>Перейдите на страницу пользователя для пользования функционалом приложения</h5>
        </div>
        <div class="col-6 p-5">
        <a href="/user" class=" text-decoration-none">
          <input type="button" value="Страница пользователя" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
        </a>
        </div>
    `
    navLinks.innerHTML += `
    <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>

    <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
    <h6><a  href="/user" class=" text-decoration-none">Страница пользователя</a></h6>
    `
        
        ;
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
            <a href="/user" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
            <a href="/admin" role="button" class=" dropdown-item text-decoration-none">Страница администратора</a>
            <a onclick="logOut()" role="button" class=" dropdown-item text-decoration-none">Выход</a>
        </div>
        </div>`;

        mainPageContent.innerHTML +=
    `
    <div class="col-6 p-5">
          <h5>Перейдите на страницу пользователя или администратора для пользования функционалом приложения</h5>
        </div>
        <div class="col-6 p-5">
        <a href="/user" class=" text-decoration-none">
          <input type="button" value="Страница пользователя" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
        </a>
        <a href="/admin"class=" text-decoration-none">
          <input type="button" value="Страница администратора" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
        </a>
        </div>
    `
    navLinks.innerHTML += `
          <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>
            <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
            <h6><a href="/user" >Страница пользователя</a></h6>
            <h6><a href="/admin" >Страница администратора</a></h6>
            `
      }
  }
};