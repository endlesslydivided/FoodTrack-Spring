

window.onload = function() 
{
    var navLinks = document.getElementById('mySidenav');
    var actionButtons = document.getElementById('actionButtons');
    var mainPageContent = document.getElementById('mainPageContent');
    if(getCookie('username') == undefined)
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
      <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>
        <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
        `
    }
    else
    {
        var roles= getCookie('roles'); 
        var username=  getCookie('username');
        if(roles == "ROLE_USER")
        { 
          actionButtons.innerHTML = 
          `<p class="m-0">Привет,${username}</p>
          <div class="dropdown">
          <a class="btn btn-floating m-1 ripple-surface"
          id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
          role="button">
              <i class="far fa-user"></i>
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenu">
              <a href="/user" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a href="/signIn/logout" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`
          mainPageContent.innerHTML +=
      `
      <div class="col-6 p-5">
            <h5>Перейдите на страницу пользователя для пользования функционалом приложения</h5>
          </div>
          <div class="col-6 p-5">
          <a  href="/user" class=" text-decoration-none">
            <input type="button" value="Страница пользователя" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
          </a>
          </div>
      `
      navLinks.innerHTML += `
      <a href="javascript:void(0)" class="closebtn"  onclick="closeNav()">×</a>
        <h6><a href="/" class=" text-decoration-none">Главная страница</a></h6>
        <h6><a href="/user" >Страница пользователя</a></h6>
        `
          ;
        }
        else if(roles == "ROLE_ADMIN")
        {
          actionButtons.innerHTML = `
          <p class="m-0">Привет,${username} </p>
          <div class="dropdown">
          <a class="btn btn-floating m-1 ripple-surface"
          id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
          role="button">
              <i class="far fa-user"></i>
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenu">
          <a href="/user" role="button" class=" dropdown-item text-decoration-none">Страница пользователя</a>
              <a  href="/admin" role="button" class=" dropdown-item text-decoration-none">Страница администратора</a>
              <a href="/signIn/logout" role="button" class=" dropdown-item text-decoration-none">Выход</a>
          </div>
          </div>`;

          mainPageContent.innerHTML +=
      `
      <div class="col-6 p-5">
            <h5>Перейдите на страницу пользователя или администратора для пользования функционалом приложения</h5>
          </div>
          <div class="col-6 p-5">
          <a  href="/user" class=" text-decoration-none">
            <input type="button" value="Страница пользователя" class="btn w-100 rounded-0 btn-outline-dark border-dark m-2 "/>
          </a>
          <a  href="/admin" class=" text-decoration-none">
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

function getCookie(name) {
  var matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}