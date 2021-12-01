
const url = "http://localhost:8080";

function alertAddMes(message)
{
    $(".alert .alertMessage").append(message);
}
function  closeAlert()
{
    $(".alert").hide('close');
}

 
function sendRegisterForm() {
    $(".alert .alertMessage").text("");
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let lastname = document.getElementById("lastname").value;
    let login =document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let date = document.getElementById("date").value;
    let weight = document.getElementById("weight").value;
    let height = document.getElementById("height").value;

    if(name.length < 1  || name.length >  100) {alertAddMes("Имя: 1-100 символов."); }
    if(surname.length < 1  || surname.length >  100) {alertAddMes("Фамилия: 1-100 символов.");}
    if(lastname.length < 1  || lastname.length >  100) {alertAddMes("Отчество: 1-100 символов.");}
    if(login.length < 8  || login.length >  25) {alertAddMes("Логин: 8-25 символов.");}
    if(password.length < 5  || password.length >  20) {alertAddMes("Пароль: 5-20 символов.");}
    if(new Date(date) < '01.01.2000'  || new Date(date) > new Date || date == "") {alertAddMes("Неверная дата.");}
    if(weight < 10  || weight >  300) {alertAddMes("Вес: от 10 до 300."); }
    if(height < 10  || height >  300) {alertAddMes("Рост: от 10 до 300."); }

    if($(".alert .alertMessage").text() != "")
    {
        $(".alert").show('close');
        $(".alert").alert();
        return false;
    }
    closeAlert();    
    fetch(url + '/signUp/register', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    userName: name,
                    userSurname: surname,
                    userLastname: lastname,
                    userLogin:login,
                    userPassword: password,
                    userBirthday: date,
                    userWeight: weight,
                    userHeight: height
                }
            )
        })
  .then(response => 
    {
        if (response.status === 303) 
        {
        window.location.href = response.url;
        }
        if (response.status === 400) 
        {
            $(".alert .alertMessage").text("");
            alertAddMes(response.statusText);
            $(".alert").show('close');
            $(".alert").alert();
        }
        if (response.redirected) 
        {
        window.location.href = response.url;
        }
    }
    )
}
