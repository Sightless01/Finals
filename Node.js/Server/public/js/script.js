function validateForm() {
  var x = document.forms["myForm"]["fname"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }
};

function checkPass() {

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

function register() {
  (function() {
    'use strict';
    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();
  let errors = [];
  let errorRep = "";
  let fname = document.getElementById("fname").value;
  fname == "" ? errors.push({ "error" : "Empty name" }) : "";
  let uType = document.getElementById("company").checked ? "company" : "client";
  let uname = document.getElementById("uname").value;
  uname == "" ? errors.push({ "error" : "Empty username" }) : "";
  let eaddress = document.getElementById("eaddress").value;
  eaddress.includes("@") ? "" : errors.push({ "error" : "invalid Email" });
  let paddress = document.getElementById("paddress").value;
  paddress == "" ? errors.push({ "error" : "Empty address" }) : "";
  let contactnum = document.getElementById("contactnum").value;
  isNaN(contactnum) ? errors.push({ "error" : "Is not a valid number" }) : "";
  let password = document.getElementById("password").value;
  password == "" ? errors.push({ "error" : "Empty password" }) : "";
  if(errors.length != 0) {
    console.log(errors);
    errors.forEach(error => {
      console.log(error);
      errorRep += (error.error + "\n");
    });
    alert(errorRep);
  } else {
    console.log("test");
    let data = {};
    data.fname = fname;
    data.utype = uType;
    data.uname = uname;
    data.eaddress = eaddress;
    data.paddress = paddress;
    data.contactnum = contactnum;
    data.password = password;
    data.redirect = $("#redirect").val();
    $.ajax({
      type: 'post',
      datatype: 'json',
      data: JSON.stringify(data),
      contentType: 'application/json',
      url: 'http://localhost:5001/register',
      statusCode: {
        400: msg => {
          let response = "Error!\n";
          responseJSON = JSON.parse(msg.responseText);
          responseJSON.forEach(res => {
            response += (res.error + "\n");
          })
          alert(response);
        }
      },
      success: function(data) {
        console.log(data);
        responseJSON = JSON.parse(data);
        alert("success!\n" + "Redirecting to: " + responseJSON.redirect);
        window.location.href = responseJSON.redirect;
       } //node.js server is running
    });
  }
}
