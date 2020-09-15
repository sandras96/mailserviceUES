var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("userRole");
	
	loadMessages(currentUserId);
	loadAccounts(currentUserId);
	loadTags(currentUserId);
	
	getAllMessages();
	
	$(".searchAndSelect").show();
	
	$("#primary").click(function(){
		console.log("radi li")
		$(".inbox_chat").empty();
		$(".incoming_msg").empty();
		$('#selAccounts').prop('selectedIndex',0);
		$('#tagFilter').prop('selectedIndex',0);
		$('#sortByMessages').prop('selectedIndex',0);
		$("#sortByMessages").show();
		$("#tagFilter").show();
		loadMessages(currentUserId);
		
	});
});

function getAllMessages(){
	console.log("getAllMessages")
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search-messages/",
		headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cache: false,
		success: function(response){
			 
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="404"){
				alert(textStatus, errorThrown);
			}
		}
	});
}

function loadMessages(userId){
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'get',
		url: "https://localhost:8080/elasticsearch/search-messages/" + userId,
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
function getAllmessagesByAccountId(){
	var token = localStorage.getItem("token");
	var accountId = $('#selAccounts').find(":selected").val();
	$("#sortByMessages").hide();
	$("#tagFilter").hide();
	$(".searchAndSelect").hide();
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
	for (var i = 0; i < accounts.length; i++) {
		 $("<option>").val(accounts[i].id).text(accounts[i].displayName).appendTo($select);
		
	}
		
	
	$select.change(function(){
		getAllmessagesByAccountId();
	})
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

function searchica(){
	console.log("srcicaaa")
	searchFunction();
}

function searchFunction(){
	$(".inbox_chat").empty();
	$(".incoming_msg").empty();
		console.log("ON CHANGE SELECT")
	 var typeField = $("#tipField").val();
	 var value =$("#value").val();
	 if(typeField == "SUBJECT"){
		 if(value==""){
			loadMessages(currentUserId)
		 }else{
			 searchMessages("searchBySubject/" + currentUserId + "/" + value);
		 }
	 }else if(typeField == "SENDER"){
		 if(value == ""){
			 loadMessages(currentUserId)
		 }else{
			 searchMessages("searchBySender/" + currentUserId + "/" + value);
		 }
	 }else if(typeField == "CONTENT"){
		 if(value == ""){
			 loadMessages(currentUserId)
		 }else{
			 searchMessages("searchByContent/" + currentUserId + "/" + value);
		 }
	 }

}
function searchMessages(v){
	var token = localStorage.getItem("token");
	$(".inbox_chat").empty();
	$(".incoming_msg").empty();
	console.log("searchMessages");
	var field = $('#tipField').find(":selected").val();
	
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

