var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function(){
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("userRole");
	console.log("currentUserId je " + currentUserId);
	loadAccounts(currentUserId);
	loadMessages(currentUserId);
	loadTags(currentUserId);
	$("#sortByMessages").hide();
	$(".searchAndSelect").hide();
//	$(".messaging").hide();
	
	
	$("#primary").click(function(){
		console.log("radi li")
		$(".inbox_chat").empty();
		$(".incoming_msg").empty();
		$('#selAccounts').prop('selectedIndex',0);
		$('#tagFilter').prop('selectedIndex',0);
		$('#sortByMessages').prop('selectedIndex',0);
		$("#sortByMessages").hide();
		$(".searchAndSelect").hide();
		$("#tagFilter").show();
		loadMessages(currentUserId);
		
	});
});



function loadMessages(userId){
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/messages/user/" + userId,
		headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cache: false,
		success: function(response){
			 
			initMessages(response);
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="404"){
				alert(textStatus, errorThrown);
			}
		}
	});
}

function loadTags(userId){
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/tags/getAll/" + userId,
		headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cache: false,
		success: function(response){
			console.log(response)
			var $select = $("#tagFilter");
			for (var i = 0; i < response.length; i++) {
			$("<option>").val(response[i].id).text(response[i].name).appendTo($select);
			$select.change(function(){
				
				var tagId = $('#tagFilter').find(":selected").val();
				
					$.ajax({
						type: 'get',
						url: "https://localhost:8080/mailservice/tags/message/" + tagId + "/" + userId ,
						cache: false,
						contentType: 'application/json',
						success: function(response){
							console.log("response je " + response);
							$(".inbox_chat").empty();
							$(".incoming_msg").empty();
							initMessages(response);
						},
							
							error: function (jqXHR, textStatus, errorThrown) {  
								if(jqXHR.status=="404"){
									alert(textStatus, errorThrown);
								}
							}
						});
			})
			}
	
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="404"){
				alert(textStatus, errorThrown);
			}
		}
	});
}
	

function loadAccounts(userId){
	var token = localStorage.getItem("token");
	console.log("usao u load accounts")
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/accounts/user/" + userId,
		headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cache: false,
		success: function(response){
			console.log(response)
			for(var i=0; i<response.length; i++){
				account = response[i];
				accountId = account.id;
				
			
	var usersDiv = $("#usersDiv1");
			
			var tableRow= $('<tr></tr>');
			var img = $('<td><div class="glyphicon glyphicon-user"></div></td>');
			var displayname = $('<td>'+account.displayName+'</td>');
			var username = $('<td>'+account.username+'</td>');
			var btnRemove= $('<td><button type="button" onClick="deleteAccount('+accountId+')" class="removeUser btn btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>')
			var btnEdit= $('<td><button type="button" data-toggle="modal" data-target="#editUserModal" onClick="editAccountModal('+accountId+')" class="editUser btn btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></td>')
			
			tableRow.append(img);
				tableRow.append(displayname);
				tableRow.append(username);
				tableRow.append(btnEdit);
				tableRow.append(btnRemove);
				usersDiv.append(tableRow);
			}
			getAccounts(response, userId);
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="404"){
				alert(textStatus, errorThrown);
			}
		}
	});

}

function getAccounts(accounts){
	 var $select = $("#selAccounts");
//	 $select.find("option").remove(); 
	for (var i = 0; i < accounts.length; i++) {
		 $("<option>").val(accounts[i].id).text(accounts[i].displayName).appendTo($select);
		
	}
		
	
	$select.change(function(){
	//	$(".messaging").show();
		getAllmessagesByAccountId();
		/*var accountId = $('#selAccounts').find(":selected").val();
		
			$.ajax({
				type: 'get',
				url: "https://localhost:8080/elasticsearch/search-messages/" + accountId,
				cache: false,
				contentType: 'application/json',
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
				});*/
	})
	}


function initMessages(messages){
	for (var i = 0; i < messages.length; i++) {
		appendMessage(messages[i]);
		
	}
};
function appendMessage(message){
	var token = localStorage.getItem("token");
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
				url: "https://localhost:8080/mailservice/messages/" + imageId,
				headers:{Authorization:"Bearer " + token},
				cache: false,
				success: function(response){
					console.log("horaaaaj" + response.subject)
					var div2 = $('<div class="incoming_msg_img"> <img src="img/profile.jpg" alt="sunil"> </div><div class="received_msg">'+
							'<div class="received_withd_msg"><span class="time_date">'+response.dateTime+'<button style="float: right" type="button" onClick="deleteMessage('+imageId+')" class="removeUser btn btn-xs"><span class="glyphicon glyphicon-remove btn-danger"></span></button></span>'+
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
}

function searchica(){
	console.log("srcicaaa")
	searchFunction();
}
/*function buttonsss(){
	console.log("whaaat")
	var accountId = $('#selAccounts').find(":selected").val();
	console.log(accountId)
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search-messages/",
		cache: false,
		success: function(response){
			console.log(response)
					},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
	}*/

function getAllmessagesByAccountId(){
	var token = localStorage.getItem("token");
	var accountId = $('#selAccounts').find(":selected").val();
	$("#sortByMessages").show();
	$("#tagFilter").hide();
	$(".searchAndSelect").show();
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search-messages/" + accountId,
		headers:{Authorization:"Bearer " + token},
		cache: false,
		contentType: 'application/json',
		success: function(response){
			$('.inbox_chat').empty();
			$('.incoming_msg').empty();
			initMessages(response);
	//		loadTagsByAccount(accountId);
		},
			
			error: function (jqXHR, textStatus, errorThrown) {  
				if(jqXHR.status=="404"){
					alert(textStatus, errorThrown);
				}
			}
		});
}

function searchFunction(){
	var token = localStorage.getItem("token");
	console.log("ON CHANGE SELECT")
	 var typeField = $("#tipField").val();
	 var value =$("#value").val().trim().toLowerCase();
	 var accountId = $('#selAccounts').find(":selected").val();
	 if(typeField == "SUBJECT"){
		 if(value==""){
			getAllmessagesByAccountId();
		 }else{
			 searchMessages("searchBySubject/" + accountId + "/" + value);
		 }
	 }else if(typeField == "SENDER"){
		 if(value == ""){
			 getAllmessagesByAccountId();
		 }else{
			 searchMessages("searchBySender/" + accountId + "/" + value);
		 }
	 }else if(typeField == "CONTENT"){
		 if(value == ""){
			 getAllmessagesByAccountId();
		 }else{
			 searchMessages("searchByContent/" + accountId + "/" + value);
		 }
	 }

}

function searchMessages(v){
	var token = localStorage.getItem("token");
	$(".inbox_chat").empty();
	$(".incoming_msg").empty();
	console.log("searchMessages");
	var field = $('#tipField').find(":selected").val();
//	var value=$("#value").val().trim();
//	var accountId = $('#selAccounts').find(":selected").val();
	console.log("Pretraga :" + field);
	
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search/" + v,
		headers:{Authorization:"Bearer " + token},
		cache: false,
		contentType: 'application/json',
		success: function(response){
			initMessages(response);
			console.log(response)
					},
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
	}

function sortMessages(){
	var sortBy = $('#sortByMessages').val();
	console.log("SORTBY JE"+ sortBy)
	if(sortBy == "SUBJECT"){
			getMessagesSortBy("orderbysubject");
		}else if(sortBy == "SENDER"){
			getMessagesSortBy("orderbysender")
		}else if(sortBy == "DATETIME"){
			getMessagesSortBy("orderbydatetime");
		}else if(sortBy== ""){
			getAllmessagesByAccountId();
		}
}
function getMessagesSortBy(sortby){
	var token = localStorage.getItem("token");
	var accountId = $('#selAccounts').find(":selected").val();
	
	console.log("account id je " + accountId)
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/mailservice/messages/account/" + sortby +"/"+ accountId,
		headers:{Authorization:"Bearer " + token},
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
	
}



function addAccount(){
	var token = localStorage.getItem("token");
	var smtpAddress = $('#addsmtpAddress').val();
	var smtpPort = $('#addsmtpPort').val();
	var inServerType = $('#addinServerType').val();
	var inServerAddress = $('#addinServerAddress').val();
	var inServerPort = $('#addinServerPort').val();
	var username = $('#addusername').val();
	var password = $('#addpassword').val();
	var displayName = $('#adddisplayname').val();
	if(smtpAddress == "" || smtpPort =="" || inServerType == ""|| inServerAddress =="" || inServerPort == ""
		|| username == ""|| password =="" || displayName == ""){
		alert("Please fill all fields");
	}
	var data = new FormData();
	data.append('smtpAddress', smtpAddress);
	data.append('smtpPort', smtpPort);
	data.append('inServerType', inServerType);
	data.append('inServerAddress', inServerAddress);
	data.append('inServerPort', inServerPort);
	data.append('username', username);
	data.append('password', password);
	data.append('displayName', displayName);
	data.append('euser', currentUserId);
	
	console.log(data)
	$.ajax({
		contentType : 'application/json',
		url: 'https://localhost:8080/mailservice/accounts/addAccount',
		headers:{Authorization:"Bearer " + token},
		type: 'POST',
		contentType: false,
	    data: data,
	    cache: false,
		processData: false,
		success: function(response){
			console.log(response)
			alert("Successful!")
			location.reload();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
			
		}
    });
}

function deleteAccount(deleteId){
	var token = localStorage.getItem("token");
	if (!confirm('Are you sure you want to delete this contact?' + deleteId)) return;
	$.ajax({
		url: "https://localhost:8080/mailservice/accounts/deleteAccount/"+deleteId,
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

function editAccountModal(editId){
	var token = localStorage.getItem("token");
	$("#buttonEdit").val(editId);
	$.ajax({
		url: "https://localhost:8080/mailservice/accounts/" + editId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType: 'json',
		cache : false,
		success: function(response){
			console.log(response)
			$('#editsmtpAddress').val(response.smtpAddress);
			$('#editsmtpPort').val(response.smtpPort);
			$('#editinServerType').val(response.inServerType);
			$('#editinServerAddress').val(response.inServerAddress);
			$('#editinServerPort').val(response.inServerPort);
			$('#editusername').val(response.username);
			$('#editpassword').val(response.password);
			$('#editdisplayname').val(response.displayName);
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
	});
}

function editAccount(){
	var token = localStorage.getItem("token");
	console.log("heeej")
	var smtpAddress = $('#editsmtpAddress').val();
	var smtpPort = $('#editsmtpPort').val();
	var inServerType = $('#editinServerType').val();
	var inServerAddress = $('#editinServerAddress').val();
	var inServerPort = $('#editinServerPort').val();
	var username = $('#editusername').val();
	var password = $('#editpassword').val();
	var displayName = $('#editdisplayname').val();
	var valEditButton = $("#buttonEdit").val();
	if(smtpAddress == "" || smtpPort =="" || inServerType == ""|| inServerAddress =="" || inServerPort == ""
		|| username == ""|| password =="" || displayName == ""){
		alert("Please fill all fields");
	}
	var data = {
			'smtpAddress' : smtpAddress,
			'smtpPort' : smtpPort,
			'inServerType' : inServerType,
			'inServerAddress' : inServerAddress,
			'inServerPort' : inServerPort,
			'username' : username,
			'password' : password,
			'displayName' : displayName
	}
	console.log(data)
	$.ajax({
		type: 'PUT',
		url: "https://localhost:8080/mailservice/accounts/editAccount/"+ valEditButton,
		headers:{Authorization:"Bearer " + token},
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

function deleteMessage(deleteId){
	var token = localStorage.getItem("token");
	if (!confirm('Are you sure you want to delete this message?' + deleteId)) return;
	$.ajax({
		url: "https://localhost:8080/mailservice/messages/deleteMessage/"+deleteId,
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

/*function loadTagsByAccount(accountId){
	$.ajax({
		url: "httpss://localhost:8080/mailservice/tags/getAll/" + accountId,
		type: 'GET',
		success : function(response){
			console.log("tagsss" + response)
			getTags(response)
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}
function getTags(tags){
	 var $select = $("#tagFilter");
	for (var i = 0; i < tags.length; i++) {
		 $("<option>").val(tags[i].id).text(tags[i].name).appendTo($select);
		
	}
		
	
	$select.change(function(){
		
		var tagId = $('#tagFilter').find(":selected").val();
		var accountId = $('#selAccounts').find(":selected").val();
		
			$.ajax({
				type: 'get',
				url: "https://localhost:8080/mailservice/tags/message/" + tagId + "/" + accountId ,
				cache: false,
				contentType: 'application/json',
				success: function(response){
					console.log("response je " + response);
					$(".inbox_chat").empty();
					$(".incoming_msg").empty();
					initMessages(response);
				},
					
					error: function (jqXHR, textStatus, errorThrown) {  
						if(jqXHR.status=="404"){
							alert(textStatus, errorThrown);
						}
					}
				});
	})
	}

*/