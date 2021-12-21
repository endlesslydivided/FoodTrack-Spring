function authHeader() 
{
    const token = getCookie("token");
  
    if (token != "") 
    {
      return { Authorization: token};
    } else {
      return {};
    }
  }

  function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
      "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
  }
  