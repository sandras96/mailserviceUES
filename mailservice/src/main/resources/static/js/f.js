var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";


$(document).ready(function(){
	var loginButton = $('#loginButton');
	loginButton.show();
	var registerButton = $('#registerButton');
	registerButton.show();
	var logoutButton = $('#logoutButton');
	logoutButton.hide();
	$("#contactsButton").hide();
	$("#accountsButton").hide();
	$("#mailsButton").hide();
	$('#contactsAllButton').hide();
	
	loginStatus();
	
});

function loginStatus(){
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("userRole");
	console.log("currentUserId: "+currentUserId);
	console.log("usrname: " + loggedInUsername);
	console.log("currentUserType: " + currentUserType);
	if(currentUserId!= "null"){
//	loadAccounts(currentUserId);
//	loadContacts(currentUserId);
	$("#loggedInUsr").text("LoggedInUser: " +loggedInUsername);
	$('#logoutButton').show();
	$("#contactsButton").show();
	$("#accountsButton").show();
	$("#mailsButton").show();
	$('#loginButton').hide();
	$('#registerButton').hide();
	if(currentUserType == "ADMIN"){
		$('#contactsAllButton').show();
	}
	
	}
	}

function logout(){
    sessionStorage.setItem("id", null);
    sessionStorage.setItem("username", null);
    sessionStorage.setItem("userRole", null);
    localStorage.setItem("token", null);
    window.location.href = "index.html";
}

function login(){
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
			location.reload(); 
		}
	});

}
