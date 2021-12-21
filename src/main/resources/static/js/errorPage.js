
window.onload = function() 
{
    var navLinks = document.getElementById('mySidenav');
    var actionButtons = document.getElementById('actionButtons');
    if(getCookie('username') == undefined)
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