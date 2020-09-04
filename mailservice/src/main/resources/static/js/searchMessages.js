var currentUserId = "";
var loggedInUsername = "";
var currentUserType = "";

$(document).ready(function() {
	currentUserId = sessionStorage.getItem("id");
	loggedInUsername = sessionStorage.getItem("username");
	currentUserType = sessionStorage.getItem("currentUserType");
	
	/*searchAllByAccount();*/
	
	getAllmessages();
	
	
});

function getAllmessages(){
	var accountId = $('#selAccounts').find(":selected").val();
	console.log("fuuuuuuuuuuu" + accountId)
	$.ajax({
		type: 'get',
		url: "http://localhost:8080/elasticsearch/search-messages/" + 1,
		cache: false,
		contentType: 'application/json',
		success: function(response){
			$('.inbox_chat').empty();
			$('.incoming_msg').empty();
		//	initMessages(response);
		},
			
			error: function (jqXHR, textStatus, errorThrown) {  
				if(jqXHR.status=="404"){
					alert(textStatus, errorThrown);
				}
			}
		});

}