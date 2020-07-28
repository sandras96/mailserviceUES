var currentUserId = "";
var loggedInUsername = "";


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
	
	loginStatus();
	
});

function loginStatus(){
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	console.log("currentUserId: "+currentUserId);
	console.log("usrname" + loggedInUsername);
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
	
	
	}
	}

function logout(){
    sessionStorage.setItem("id", null);
    sessionStorage.setItem("username", null);
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
			if (typeof(Storage) !== "undefined") {
        	    sessionStorage.setItem("id", response.id);
        		sessionStorage.setItem("username", response.username);
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

/*function loadAccounts(userId){
	console.log("usao u load accounts")
	$.ajax({
		type: 'get',
		url: "http://localhost:8080/mailservice/accounts/user/" + userId,
		
		cache: false,
		success: function(response){
			
			for(var i=0; i<response.length; i++){
				account = response[i];
				accountId = account.id;
				console.log("akount id je" + accountId)
			}
			getAccounts(response, userId);
			
			
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status="404"){
				alert(textStatus);
			}
			console.log(textStatus)
			
		}
    });
	
	
}*/

/*function getAccounts(accounts, userId){
	 var $select = $("#selAccounts");
//	 $select.find("option").remove(); 
	for (var i = 0; i < accounts.length; i++) {
		 $("<option>").val(accounts[i].id).text(accounts[i].displayName).appendTo($select);
		
	}

	$select.change(function(){
		
		var accountId = $('#selAccounts').find(":selected").val();
		
		var params = $.param({
			accountId : accountId,
		}) ; 
		console.log("params " + params)
		$.ajax({
		type: 'get',
		url: "http://localhost:8080/mailservice/messages/account/" + accountId,
		
		cache: false,
		success: function(response){
			$('.inbox_chat').empty();
			 $('.incoming_msg').empty();
			initMessages(response);
		},
					
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
		
		
	})
};
function initMessages(messages){
	for (var i = 0; i < messages.length; i++) {
		appendMessage(messages[i]);
		
	}
};
function appendMessage(message){
	var div1 = $('<div class="chat_list active_chat" value='+message.id+'><div class="chat_people">'+
                '<div class="chat_img"  value='+message.id+'> <img src="/img/emailicon.jpg" alt="sunil" id="img'+message.id+'" value='+message.id+'></div>'+
                ' <div class="chat_ib"><h5 class="inner"id="subject">Subject: '+message.subject+'<span class="chat_date" id="datetime">'+message.dateTime+'</span></h5>'+
                '<p class="inner"><span id="from" >from: '+message.from+' </span></p><p id="content" class="inner">'+message.content+'</p></div></div></div>'  );
	
	div1.appendTo($('.inbox_chat'));
	var imgId = "#img" + message.id;
	var imgbtn = $(imgId);
	imgbtn.click(function(e) {
		  e.preventDefault();
		  var imageId = $(this).attr("value"); 
		  $.ajax({
				type: 'get',
				url: "http://localhost:8080/mailservice/messages/" + imageId,
				
				cache: false,
				success: function(response){
					console.log("horaaaaj" + response.subject)
					var div2 = $('<div class="incoming_msg_img"> <img src="img/profile.jpg" alt="sunil"> </div><div class="received_msg">'+
							'<div class="received_withd_msg"><span class="time_date">'+response.dateTime+'</span>'+
							' <p>from: '+response.from+'</p>'+
							'<p>to: '+response.to+'</p>'+
							' <p>subject: '+response.subject+'</p>'+
							'<p>'+response.content+'</p></div>');
					
					div2.appendTo($('.incoming_msg'));
					
				},
				
							error: function (jqXHR, textStatus, errorThrown) {  
								if(jqXHR.status=="404"){
									alert(textStatus);
								}
							}
				
						});			

		  $('.incoming_msg').empty();
	}
	
	
	);
	
		
}*/
/*function loadContacts(userId){
	$.ajax({
		type: 'get',
		url: "http://localhost:8080/mailservice/contacts/user/" + userId,
		
		cache: false,
		success: function(response){
			for(var i=0; i<response.length; i++){
				contact = response[i];
				contactId = contact.id;
				console.log("contacttt" + contact.firstname)
				
			
			
				
				var usersDiv = $("#usersDiv1");
						
						var tableRow= $('<tr></tr>');
						var img = $('<td><div class="glyphicon glyphicon-user"></div></td>');
						var displayName = $('<td>'+contact.displayName+'</td>');
						var firstname = $('<td>'+contact.firstname+'</td>');
						var lastname = $('<td>'+contact.lastname+'</td>');
						var email = $('<td>'+contact.email+'</td>');
						var note = $('<td>'+contact.text+'</td>');
						var btnRemove= $('<td><button type="button" onClick="deleteUser()" class="removeUser btn btn-xs" value="'+userId+'"><span class="glyphicon glyphicon-remove"></span></button></td>')
						var btnEdit= $('<td><button type="button" data-toggle="modal" data-target="#editUserModal" onClick="editUserModal('+userId+')" class="editUser btn btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></td>')
						
						tableRow.append(img);
						tableRow.append(displayName);
							tableRow.append(firstname);
							tableRow.append(lastname);
							tableRow.append(email);
							tableRow.append(note);
							tableRow.append(btnEdit);
							tableRow.append(btnRemove);
							usersDiv.append(tableRow);
						}
					},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
		
		

}*/
/*function signUp(){
	var usernameInput = $('#inputUsername1').val().trim();
	var passwordInput = $('#inputPassword1').val();
	var nameInput = $('#inputName').val().trim();
	if(usernameInput == "" || passwordInput == "" || nameInput ==""){
		alert("Please fill all fields.");
		return;
	}
	
	var data = {
			'username' : usernameInput,
			'password' : passwordInput,
			'name' : nameInput,
			'userType' : "PUBLISHER"
	}
	console.log(data)
	$.ajax({
		contentType : 'application/json',
		url: 'http://localhost:8080/api/users/',
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
function editProfileButtonModal(){
	console.log("otvorio se edit profile modal")
	$.ajax({
		type: 'GET',
        url: 'http://localhost:8080/api/users/'+currentUserId,
		cache: false,
        success: function (response) {
        	$('#editPassword').val(response.password);
        	$('#editName').val(response.name);
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
		
	});
}


		
function editProfile(){
	var editPassword = $('#editPassword').val();
	var editName = $('#editName').val().trim();
	var photo = $('#newImgProfile')[0].files[0];
	var checked = false;
	if($('#imgUploadCheckEditProfile').prop('checked')){
		checked = true;
	}
	if(typeof photo == 'undefined' && checked==true){
		console.log("alohaaa")
		alert("Picture must be uploaded, or uncheck checkbox.");
		return;
	}else 
	if(typeof photo != 'undefined' && checked==false){
		alert("Please check checkbox, or remove uploaded file");
		return;
	}
	var data={
			'password': editPassword,
			'name': editName,
			'userType':currentUserUserType
	}
	console.log(data);
	$.ajax({
		type: 'put',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/users/'+currentUserId,
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	if(checked == true){
        		uploadPicUser(currentUserId, photo);
        	}
        	console.log(response);
        	alert("Successful!")
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}
function uploadPicUser(userId,photo){
	console.log("SLICICAA")
	var data = new FormData();
	data.append("id",userId);
	data.append("photo",photo);
	
	console.log("slicica" + data)
	$.ajax({
		type: 'POST',
        url: 'http://localhost:8080/api/users/upload_photo',
        contentType: false,
        data: data,
		cache: false,
		processData: false,
        success: function (response) {
        	console.log("Pic user upload success.");
           
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
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


function currentDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	var today = yyyy+"-"+mm+"-"+dd;
	
	return today;
}
function getRandomCordinate() {
	var to = 180;
	var from = -180;
	var fixed = 4;
    return (Math.random() * (to - from) + from).toFixed(fixed) * 1;
    // .toFixed() returns string, so ' * 1' is a trick to convert to number
}*/

