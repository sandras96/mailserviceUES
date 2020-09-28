var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("userRole");
	searchAll();
	
});


function searchAll(){
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/contacts/getAll",
		headers:{Authorization:"Bearer " + token},
		cache: false,
		contentType: 'application/json',
		success: function(response){
			initContacts(response);
					},
			error: function (jqXHR, textStatus, errorThrown) {  
				if(jqXHR.status=="403"){
					alert("Unauthorized!");
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
		//	tableRow.append(btnEdit);
		//	tableRow.append(btnRemove);
			usersDiv.append(tableRow);
					
					
				}
		}
