var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("userRole");
	/*$("#advancedSearch").hide();
	$( "#showAdvSrch" ).click(function() {
		$("#advancedSearch").show();
		});*/
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
			for(var i=0; i<response.length; i++){
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
							
							
						}
					},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
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
			 searchAll();
		 }else{
			 search("searchByFirstName/"+value)
		 }
	 }else if(typeField == "LASTNAME"){
		 if(value == ""){
			 searchAll();
		 }else{
			 search("searchByLastName/" + value);
		 }
	 }else if(typeField == "NOTE"){
		 if(value == ""){
			 searchAll();
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
				var img = $('<td><div class="glyphicon glyphicon-user"></div></td>');
				var displayName = $('<td>'+contact.displayName+'</td>');
				var firstname = $('<td>'+contact.firstname+'</td>');
				var lastname = $('<td>'+contact.lastname+'</td>');
				var email = $('<td>'+contact.email+'</td>');
				var note = $('<td>'+contact.text+'</td>');
				
				
					tableRow.append(img);
					tableRow.append(displayName);
					tableRow.append(firstname);
					tableRow.append(lastname);
					tableRow.append(email);
					tableRow.append(note);
					usersDiv.append(tableRow);
					
					
				}
		}

/*function search1(){
console.log("search");
var field = $('#tipField').find(":selected").val();
var value=$("#value").val().trim();
console.log("Pretraga :" + field + value + currentUserId);
var data = {
		"id" : currentUserId,
		"firstName" : value,
}

//var data = JSON.stringify({"firstName":value, "userId":currentUserId});
$.ajax({
	type: 'get',
	url: "https://localhost:8080/elasticsearch/search/searchByFirstName/" + currentUserId + "/" + value,
	cache: false,
	contentType: 'application/json',
	success: function(response){
		initContacts(response);
				},
				error: function (jqXHR, textStatus, errorThrown) {  
					if(jqXHR.status=="404"){
						alert(textStatus, errorThrown);
					}
				}
			});
}
*/

function booleanSearch(){
	var token = localStorage.getItem("token");
	var field1= $("#field1").val();
	var value1=$("#value1").val().trim();
	var field2= $("#field2").val();
	var value2=$("#value2").val().trim();
	var operation=$("#operation").val();
	console.log(value1 + " " +  value2);
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
	        success: function (response) {
	        	
	            console.log("SUCCESS : ", response);
	        },
	        error: function (e) {
	        	
	        	alert(e);

	        }
	    });
}
function searchica2(){
	console.log("srcicaaa22222")
	searchMultiMatch();
}

function searchMultiMatch(){
	console.log("ON CHANGE SELECT MULTI MATCH")
	 var typeField = $("#tipFieldMultiM").val();
	 var value =$("#value2").val().toLowerCase();
	 if(typeField == "PHRASE"){
		 if(value==""){
			 searchAll();
		 }else{
			
			 search("contactPhrase/" + value)
		 }
	 }else if(typeField == "FUZZY"){
		 if(value == ""){
			 searchAll();
		 }else{
			
			 search("contactFuzzy/" + value)
		 }
	 }

}

function elasticMultiMatch(path){
	var token = localStorage.getItem("token");
	console.log("MULTI MATCH + DATA i value je " + value + "i path je " + path)
	var data = new FormData();
	data.append('fuzzy', value)
	console.log("data je " + data)
	$.ajax({
		type: 'post',
		url: "https://localhost:8080/elasticsearch/search/" + path,
		headers:{Authorization:"Bearer " + token},
		 contentType: false,
	       data: data,
	       cache: false,
			processData: false,
		success: function(response){
		//	initContacts(response);
			console.log(response)
		},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
		}
