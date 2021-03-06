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
$(document).ready( () => {
  $('#subButton').on('click', e => {
    e.preventDefault();
    let errors = [];
    $('#fname').val() == "" ? errors.push({ "error" : "Empty name" }) : "";
    $('#uname').val() == "" ? errors.push({ "error" : "Empty username" }) : "";
    !$('#eaddress').val().includes('@') ? errors.push({ "error" : "Invalid email address" }) : "";
    $('#paddress').val() == "" ? errors.push({ "error" : "Empty address" }) : "";
    $('#password').val() == "" ? errors.push({ "error" : "Empty password" }) : "";
    $('#contactnum').val() == "" ? errors.push({ "error" : "Empty contact number" }) : "";
    $('#password').val() != $('#repass').val() ? errors.push({ "error" : "Password does not match" }) : "";
    if(errors.length != 0) {
      let errorRep = "";
      errors.forEach(error => {
        errorRep += (error.error + "\n");
      });
      alert(errorRep);
    } else {
      let data = {};
      data.name = $('#fname').val();
      data.utype = $('input:radio[name=userType]:checked').val();
      data.uname = $('#uname').val();
      data.email = $('#eaddress').val();
      data.address = $('#paddress').val();
      data.contact = $('#contactnum').val();
      data.password = $('#password').val();
      data.redirect = $("#redirect").val();
      $.ajax({
        type: 'post',
        datatype: 'json',
        data: JSON.stringify(data),
        contentType: 'application/json',
        url: 'http://webtechadmin.org:5001/register',
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
        success: data => {
          responseJSON = JSON.parse(data);
          alert("success!\n" + "Redirecting to: " + responseJSON.redirect);
          window.location.href = responseJSON.redirect;
         } //node.js server is running
      });
    }
  })
  $('#transacButton').on('click', e => {
    e.preventDefault();
    $('.transactions').empty();
    let data = {};
    data.start = $('input[name=start]').val();
    data.end = $('input[name=end]').val();
    $.ajax({
      type: 'post',
      datatype: 'json',
      data: JSON.stringify(data),
      contentType: 'application/json',
      url: 'http://webtechadmin.org:5001/transact',
      success: results => {
        let responseJSON = JSON.parse(results);
        if(responseJSON.length != 0) {
          responseJSON.forEach( result => {
            $('.transactions').append(
            `<div class="row">
          		<div class="col col-4">
                ${result.name}
              </div>
          		<div class="col col-4">
                ${result.count}
              </div>
          		<div class="col col-4">
                ${result.total}
              </div>
        		</div>` )
          })
        } else {
          $('.transactions').append( "<h2>No results found</h2>" );
        }
      }
    })
  })
})
