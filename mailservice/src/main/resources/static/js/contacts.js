var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";
var token = "";
var moviesDiv = $('#moviesDiv');
$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	
	console.log("currentUserId je " + currentUserId);
	currentUserType = sessionStorage.getItem("userRole");
	loadContacts(currentUserId);
	
	
	
});


function loadContacts(userId){
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/contacts/user/" + userId,
		headers:{Authorization:"Bearer " + token},
		cache: false,
		success: function(response){
			initContacts(response);
			/*for(var i=0; i<response.length; i++){
				contact = response[i];
				contactId = contact.id;
				console.log("contacttt" + response)
		//		getAllPhotos(contactId);
				var usersDiv = $("#usersDiv1");
						
						var tableRow= $('<tr></tr>');
						var img = $('<td><div><button type="button" class="btn btn-link btn-lg" data-toggle="modal" data-target="#showPicturesModal">'
								+'<span class="glyphicon glyphicon-user" style="color: black" value="'+contactId+'" onClick="showPicturesModal('+contactId+')"></span><span style="color: black"></span>'
							+'</button></div></td>');
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
							
							
						}*/
						
					},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});

		}

function deleteContact(deleteId){
	var token = localStorage.getItem("token");
	if (!confirm('Are you sure you want to delete this contact?' + deleteId)) return;
	$.ajax({
		url: "https://localhost:8080/mailservice/contacts/deleteContact/"+deleteId,
		headers:{Authorization:"Bearer " + token},
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
	var token = localStorage.getItem("token");
	$("#buttonEdit").val(editId);
	$.ajax({
		url: "https://localhost:8080/mailservice/contacts/" + editId,
		headers:{Authorization:"Bearer " + token},
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
	var token = localStorage.getItem("token");
	console.log("heeej")
	var editDisplayname = $('#editDisplayname').val();
	var editEmail =$('#editEmail').val();
	var editFirstname = $('#editFirstname').val();
	var editLastname = $('#editLastname').val();
	var editNote = $('#editNote').val();
	var valEditButton = $("#buttonEdit").val();
	
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
		url: "https://localhost:8080/mailservice/contacts/editContact/"+ valEditButton,
		headers:{Authorization:"Bearer " + token},
		 contentType: 'application/json',
		  data: JSON.stringify(data),
		    dataType: 'json',
			cache: false,
			processData: false,
		    success: function (response) {
		    	
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
	var token = localStorage.getItem("token");
	var displayName = $('#addDisplayname').val();
	var email = $('#addEmail').val();
	var firstname = $('#addFirstname').val();
	var lastname = $('#addLastName').val();
	var note = $('#addNote').val();
	if(displayName == "" || email =="" || firstname == ""|| lastname =="" || note == ""){
		alert("Please fill all fields");
	}
	var photo = $('#newImgUser')[0].files[0];
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
	}
	var data = new FormData();
	data.append('displayName', displayName);
	data.append('email', email);
	data.append('firstname', firstname);
	data.append('lastname', lastname);
	data.append('text', note);
	data.append('euser', currentUserId);
	if(checked == true){
		data.append('photo', photo);
	}
	console.log(data);
	$.ajax({
		contentType : 'application/json',
		url: 'https://localhost:8080/mailservice/contacts/addContact',
		headers:{Authorization:"Bearer " + token},
		type: 'POST',
		contentType: false,
	    data: data,
	    cache: false,
		processData: false,
		success: function(response){
			if(checked==true){
        		uploadImage(response.id,photo);
			}
			console.log(photo)
			alert("Successful!")
			location.reload();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}

function uploadImage(id,photo){
	var token = localStorage.getItem("token");
	console.log(id+" "+photo)
	var data = new FormData();
	data.append("id",id)
	data.append("photo",photo)
	console.log("photo je" + photo)
	$.ajax({
		type: 'POST',
        url: 'https://localhost:8080/mailservice/contacts/upload_photo',
        headers:{Authorization:"Bearer " + token},
        contentType: false,
        data: data,
		cache: false,
		processData: false,
        success: function (response) {
        	
        	console.log(response)
        	console.log("Success");
        	location.reload();
        	
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function uploadMorePic(){
	var token = localStorage.getItem("token");
	var contactId = document.getElementById("uploadPic").value;
	console.log("contactIddd " + contactId)
	var photo = $('#newPic')[0].files[0];
	var checked = false;
	if($('#idCheck').prop('checked')){
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
		uploadImage(contactId,photo);
	}else{
		location.reload();
	}
}

function showPicturesModal(id){
	var token = localStorage.getItem("token");
	console.log("showPicturesModal")
	$("#photosDiv").empty();
	document.getElementById("uploadPic").value = id;
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/photo/contact/" + id,
		headers:{Authorization:"Bearer " + token},
		cache: false,
		success: function(response){
			console.log(response)
			if(response.length == 0){
				var divColumn = $('<div><p><b>No photos</b></p></div><br/><br/><br/><br/>');
				$("#photosDiv").append(divColumn);
			}else{
				initPhotos(response, id);	
			}		
			},
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
			
	    });
	}

function initPhotos(photos, id){
	console.log("initPhotos" + photos)
	$("#photosDiv").empty();
	for (var i = 0; i < photos.length; i++) {
		appendPhoto(photos[i],id);
		}
	
	};

function appendPhoto(photo,id){
	console.log("appendPhoto")
	
	var divColumn = $('<div class="col-md-6"></div>');
	var divThumbnail = $('<div class="thumbnail"></div>');
	var contactPhoto = 'data:image/gif;base64,'+photo.pic;
	var img = $('<img src="' + contactPhoto + '" alt="Lights" style="width:100%">');
	divThumbnail.append(img);
	
	divColumn.append(divThumbnail);
	$("#photosDiv").append(divColumn);
	
}

function booleanSearch(){
	var token = localStorage.getItem("token");
	var field1= $("#field1").val();
	var value1=$("#value1").val().trim();
	var field2= $("#field2").val();
	var value2=$("#value2").val().trim();
	var operation=$("#operation").val();
	console.log(value1 + value2);
	if(value1 == "" || value2 == "" ){
		alert("All fields must be filled.");
		return;
	}
	var data = JSON.stringify({"field1":field1, "value1":value1, "field2":field2, "value2":value2 , "operation":operation});
	$.ajax({
	        type: "POST",
	        url: "https://localhost:8080/elasticsearch/search/contactBool" ,
	        headers:{Authorization:"Bearer " + token},
	        data: data,
	        contentType: 'application/json',
	        success: function (data) {
	            console.log("SUCCESS : ", data);
	        },
	        error: function (e) {
	        	
	        	alert(e);

	        }
	    });
}
function searchica(){
	console.log("srcicaaa")
	searchFunction();
}

function searchFunction(){
	console.log("ON CHANGE SELECT")
	 var typeField = $("#tipField").val();
	 var value =$("#value").val().trim().toLowerCase();
	 console.log("valuee je " + value)
	 if(typeField == "FIRSTNAME"){
		 if(value==""){
			 loadContacts(currentUserId);
		 }else{
			 search("searchByFirstName/"+value)
		 }
	 }else if(typeField == "LASTNAME"){
		 if(value == ""){
			 loadContacts(currentUserId)
		 }else{
			 search("searchByLastName/" + value);
		 }
	 }else if(typeField == "NOTE"){
		 if(value == ""){
			 loadContacts(currentUserId)
		 }else{
			 search("searchByNote/" + value)
		 }
	 }

}

function search(value){
	var token = localStorage.getItem("token");
	console.log("SEARCH + VALUE");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search/" + value + "/" + currentUserId,
		headers:{Authorization:"Bearer " + token},
		cache: false,
		contentType: 'application/json',
		success: function(response){
			initContacts(response);
			console.log(response)
		},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
		}

function initContacts(contacts){
	
	var usersDiv = $("#usersDiv1");
	usersDiv.empty();
	console.log(contacts)
	for(var i=0; i<contacts.length; i++){
		contact = contacts[i];
		contactId = contact.id;
		console.log("contacttt" + contact.firstname)
		
		var tableRow= $('<tr></tr>');
		var img = $('<td><div><button type="button" class="btn btn-link btn-lg" data-toggle="modal" data-target="#showPicturesModal">'
				+'<span class="glyphicon glyphicon-user" style="color: black" value="'+contactId+'" onClick="showPicturesModal('+contactId+')"></span><span style="color: black"></span>'
			+'</button></div></td>');
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
		}
