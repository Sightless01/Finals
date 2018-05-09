function validateForm() {
    var x = document.forms["myForm"]["fname"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
}

function checkPass()
{

    var password = document.getElementById('password');
    var repass = document.getElementById('repass');

    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";
  
    if(password.value == repass.value){
 
        repass.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!"
    }else{

        repass.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
}  