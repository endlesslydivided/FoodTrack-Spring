
const url = "http://localhost:8080";

function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}
function  closeAlert()
{
    $(".alert").hide('close');
}

function sendLoginForm() {
    $(".alert .alertMessage").text("");
    let login =document.getElementById("login").value;
    let password = document.getElementById("password").value;


   
    if(login.length < 8  || login.length >  25) {alertAddMes("Логин: 8-25 символов.");}
    if(password.length < 5  || password.length >  20) {alertAddMes("Пароль: 5-20 символов.");}
    

    if($(".alert .alertMessage").text() != "")
    {
        $(".alert").show('close');
        $(".alert").alert();
        return false;
    }
    closeAlert();    
    fetch(url + '/signIn/login', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    userLogin:login,
                    userPassword: password,
                }
            )
        }).then( response => 
            {
                if (response.status === 200) 
                {
                    window.location.href = response.url;
    
                }
                else 
                {
                    return response.json();       

                }
            }
           )
  .then(pdata => 
    {
        if (pdata != null) 
        {
            throw pdata["errorMessage"];
        }
    }
    ).catch(message => {messageShow(message)});
}


function set_cookie ( name, value, exp_y, exp_m, exp_d, path, domain, secure )
{
  var cookie_string = name + "=" + escape ( value );
 
  if ( exp_y )
  {
    var expires = new Date ( exp_y, exp_m, exp_d );
    cookie_string += "; expires=" + expires.toGMTString();
  }
 
  if ( path )
        cookie_string += "; path=" + escape ( path );
 
  if ( domain )
        cookie_string += "; domain=" + escape ( domain );
  
  if ( secure )
        cookie_string += "; secure";
  
  document.cookie = cookie_string;
}

$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
});

function messageShow(message)
{
    $(".alert .alertMessage").text("");
    alertAddMes(message);
    $(".alert").show('close');
    $(".alert").alert();
}
function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}

function closeAlert()
{
    $(".alert").hide('close');
}