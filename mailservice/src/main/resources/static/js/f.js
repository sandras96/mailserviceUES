var currentUserId = "";
var currentUserType = "";
var token = "";


$(document).ready(function(){
	loginStatus();
	$('.pass_show').append('<span class="ptxt">Show</span>'); 
});

function loginStatus(){
	currentUserId = sessionStorage.getItem("id");
	currentUserType = sessionStorage.getItem("userRole");
	token = localStorage.getItem("token");
	
	console.log("currentUserId: "+currentUserId);
	console.log("currentUserType: " + currentUserType);
	
	$('#contactsAllButton').hide();
	
	if(currentUserId!= "null"){
		console.log("currentUserId nije null")
	$('#logoutButton').show();
	$("#contactsButton").show();
	$("#accountsButton").show();
	$('#profileButton').show();
	$("#mailsButton").show();
	$('#loginButton').hide();
	$('#registerButton').hide();
	
	if(currentUserType == "ADMIN"){
		console.log("ADMINN")
		$('#contactsAllButton').show();
	}

	}else{
		console.log("currentUserId jeste null")
		$('#loginButton').show();
		$('#registerButton').show();
		$('#logoutButton').hide();
		$("#contactsButton").hide();
		$("#accountsButton").hide();
		$("#mailsButton").hide();
		$('#contactsAllButton').hide();
		$('#profileButton').hide();
		
		
		}
	}

function loginAuth(){
	var username =  $('#inputUsername').val().trim();
	var password = $('#inputPassword').val().trim();
	if(username=="" || password==""){
		alert("Please fill all fields.")
		return;
	}
	
	var data = {
			'username':username,
			'password':password
	}
	console.log(data);
	console.log("usao u login()");

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
        url: 'https://localhost:8080/api/auth/login',
        data: JSON.stringify(data),
        dataType: 'json',
        crossDomain: true,
		cache: false,
		processData: false,
		success:function(response){
			if (typeof(Storage) !== "undefined") {
        	    console.log(response);
        	    token = response.access_token;
        	    localStorage.setItem("token",token);
        	    console.log(token);
				sessionStorage.setItem("id", response.userId);
        	    username = response.username;
        	    sessionStorage.setItem("userRole", response.userAutority);
        	    console.log(response.userAutority);
        	    window.location.href = "index.html";
        	} else {
        	    alert("Sorry, your browser does not support Web Storage...");
        	}
        	//location.reload(); 
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status="404"){
				alert("User does not exist.");
			}
			
		}
	});

}


function logout(){
    sessionStorage.setItem("id", null);
    sessionStorage.setItem("userRole", null);
    localStorage.setItem("token", null);
    window.location.href = "index.html";
}



function signUp(){
	var usernameInput = $('#inputUsername1').val().trim();
	var passwordInput = $('#inputPassword1').val();
	var inputFirstname = $('#inputFirstname').val().trim();
	var inputLastname = $('#inputLastname').val().trim();
	var userType = $('#roleSelectAdd').val();
	console.log("usrType" + userType);
	if(usernameInput == "" || passwordInput == "" || inputFirstname =="" || inputLastname ==""){
		alert("Please fill all fields.");
		return;
	}
	
	var data = {
			'username' : usernameInput,
			'password' : passwordInput,
			'firstname' : inputFirstname,
			'lastname' : inputLastname,
			'authority' : userType,
			
	}
	console.log(data)
	$.ajax({
		contentType : 'application/json',
		url: 'https://localhost:8080/api/auth/register',
		type: 'POST',
		data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
		success: function(response){
			alert("Registration successful.")
			location.reload();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}



function showMyProfile(){
	var token = localStorage.getItem("token");
	console.log("show profile")
	$.ajax({
		contentType : 'application/json',
		url: 'https://localhost:8080/mailservice/users/' + currentUserId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
        dataType: 'json',
		cache: false,
		processData: false,
		success: function(response){
			console.log(response)
			$("#editFirstname").attr('placeholder', response.firstname);
			$("#editLastname").attr('placeholder', response.lastname);
			$('#roleSelectEdit').val(response.authority);
			if(currentUserType == "USER"){
				$('select[name="select-states"]').attr('disabled', 'disabled');
			}
				
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}

function editUser(){
	var token = localStorage.getItem("token");
	var editFirstname = $('#editFirstname').val().trim();
	var editLastname = $('#editLastname').val().trim();
	var userType = $('#roleSelectEdit').val();
	console.log("usrType" + userType);
	
	var data = {
			'firstname' : editFirstname,
			'lastname' : editLastname,
			'authority' : userType,
			
	}
	console.log(data)
	$.ajax({
		contentType : 'application/json',
		url: 'https://localhost:8080/mailservice/users/editUser/' + currentUserId,
		headers:{Authorization:"Bearer " + token},
		type: 'PUT',
		data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
		success: function(response){
			console.log(response)
			alert("Success")
			sessionStorage.setItem("userRole", response.authority);
			location.reload();
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}
function changePassword(){
	var token = localStorage.getItem("token");
	var currentPassword = $('#currentPassword').val().trim();
	var newPassword = $('#newPassword').val().trim();
	var confirmPassword = $('#confirmPassword').val().trim();
	var token = localStorage.getItem("token");
	if(newPassword != confirmPassword){
		alert("**Password doesn't match!")
	}
	var data ={
			'oldPassword':currentPassword,
			'newPassword':newPassword
	}
	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url: 'https://localhost:8080/api/auth/change-password',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="403"){
				alert("error");
			}
		}
        
    })
    window.location.href = "index.html";

}

  
function formatDate(tempDate) {
	var date = new Date(tempDate);
	var monthNames = [
	  "January", "February", "March",
	  "April", "May", "June", "July",
	  "August", "September", "October",
	  "November", "December"
	];

	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();

	return day + '. ' + monthNames[monthIndex] + ' of ' + year+'.';
	//return day + '. ' + parseInt(monthIndex+1) + '. ' + year+'.';
}


$(document).on('click','.pass_show .ptxt', function(){ 

$(this).text($(this).text() == "Show" ? "Hide" : "Show"); 

$(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; }); 

});  


/*function login(){
	console.log("u loginu sammm")
	var username= $('#inputUsername').val().trim();
	var password = $('#inputPassword').val();
	if(username == "" || password ==""){
		alert("Please fill all fields.")
		return;
	}
	console.log("usr je:" + username + "pass je" + password)
	$.ajax({
		type: 'get',
		url: "http://localhost:8080/mailservice/users/" + username +"/" + password,
		
		cache: false,
		success: function(response){
			console.log(response)
			if (typeof(Storage) !== "undefined") {
        	    sessionStorage.setItem("id", response.id);
        		sessionStorage.setItem("username", response.username);
        		sessionStorage.setItem("usertype", response.userType);
        	} else {
        	    alert("Sorry, your browser doesn't support Web Storage...");
        	}
		
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status="404"){
				alert("User doesn't exist.");
			}
			console.log(textStatus)
			
		}
    });
}*/
