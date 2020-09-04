var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	console.log("currentUserId je " + currentUserId);
	currentUserType = sessionStorage.getItem("currentUserType");
	loadContacts(currentUserId);
});


function loadContacts(userId){
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
						var btnRemove= $('<td><button type="button" onClick="deleteContact('+contactId+')" class="removeUser btn btn-xs" value="'+contactId+'"><span class="glyphicon glyphicon-remove"></span></button></td>')
						var btnEdit= $('<td><button type="button" data-toggle="modal" data-target="#editUserModal" onClick="editContactModal('+contactId+')" class="editUser btn btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></td>')
						
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

		}

function deleteContact(deleteId){
	if (!confirm('Are you sure you want to delete this contact?' + deleteId)) return;
	$.ajax({
		url: "http://localhost:8080/mailservice/contacts/deleteContact/"+deleteId,
		type: 'DELETE',
		success : function(response){
			alert("Deleted!")
			location.reload();
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}

function editContactModal(editId){
	$("#buttonEdit").val(editId);
	$.ajax({
		url: "http://localhost:8080/mailservice/contacts/" + editId,
		type: 'GET',
		dataType: 'json',
		cache : false,
		success: function(response){
			console.log(response.lastname)
			$('#editDisplayname').val(response.displayName);
			$('#editEmail').val(response.email);
			$('#editFirstname').val(response.firstname);
			$('#editLastname').val(response.lastname);
			$('#editNote').val(response.text);
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
	});
}

function editContact(){
	console.log("heeej")
	var editDisplayname = $('#editDisplayname').val();
	var editEmail =$('#editEmail').val();
	var editFirstname = $('#editFirstname').val();
	var editLastname = $('#editLastname').val();
	var editNote = $('#editNote').val();
	var valEditButton = $("#buttonEdit").val();
	if(editDisplayname == "" || editEmail == "" || editFirstname == "" || editLastname == "" || editNote == ""){
		alert("Please fill all fields.")
		return null;
	}
	/*var photo = $('#newImgEditUser')[0].files[0];
	console.log(photo)
	var checked = false;
	if($('#imgUploadCheckEditUser').prop('checked')){
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
	if(checked==true){
		uploadPicUser(currentEditUser,photo);
	}*/
	var data = {
			'displayName' : editDisplayname,
			'email' : editEmail,
			'firstname' : editFirstname,
			'lastname' : editLastname,
			'text' : editNote,
			'valEditButton' : valEditButton
	}
	console.log(data)
	$.ajax({
		type: 'PUT',
		url: "http://localhost:8080/mailservice/contacts/editContact/"+ valEditButton,
		 contentType: 'application/json',
		  data: JSON.stringify(data),
		    dataType: 'json',
			cache: false,
			processData: false,
		    success: function (response) {
		    	
		    	/*if(checked==true){
		    		uploadPicUser(currentEditUser,photo);
		    	}*/
		    	console.log(response);
		    	alert("Successful!")
		    	location.reload();
		    },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(jqXHR.status);
			}
		});
}

function addContact(){
	var displayName = $('#addDisplayname').val();
	var email = $('#addEmail').val();
	var firstname = $('#addFirstname').val();
	var lastname = $('#addLastName').val();
	var note = $('#addNote').val();
	if(displayName == "" || email =="" || firstname == ""|| lastname =="" || note == ""){
		alert("Please fill all fields");
	}
/*	var photo = $('#newImgUser')[0].files[0];
	var checked = false;
	if($('#imgUploadCheckUser').prop('checked')){
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
	}*/
	var data = new FormData();
	data.append('displayName', displayName);
	data.append('email', email);
	data.append('firstname', firstname);
	data.append('lastname', lastname);
	data.append('text', note);
	data.append('euser', currentUserId);
	$.ajax({
		contentType : 'application/json',
		url: 'http://localhost:8080/mailservice/contacts/addContact',
		type: 'POST',
		contentType: false,
	    data: data,
	    cache: false,
		processData: false,
		success: function(response){
		/*	if(checked==true){
        		uploadPicUser(response.id,photo);
        	}
			console.log(photo)*/
			alert("Successful!")
			location.reload();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}