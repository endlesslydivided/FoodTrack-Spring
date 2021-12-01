
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
                    return response.json();       
                }
                if (response.status === 400) 
                {
                    $(".alert .alertMessage").text("");
                    alertAddMes(response.statusText);
                    $(".alert").show('close');
                    $(".alert").alert();
                    return;
                }
            }
           )
  .then(response => 
    {
        if (response.token) {
            localStorage.setItem("user", JSON.stringify(response));
            window.location.href = url;
        }
    }
    )
}
