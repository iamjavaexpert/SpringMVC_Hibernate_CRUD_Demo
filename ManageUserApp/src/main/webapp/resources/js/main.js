$(document).ready(function(){
	console.log("Welcome");
	if($(".address-repeat").length > 0) {
		$('.address-repeat').each(function() {
			$(this).repeatable_fields({
				after_remove: function(container) {
					//Reset all the field indexes in a row after remove any single row.
					$.each($(".row").not(".template"), function(k,v){
						$.each($(v).find("[name*='userAddresses']"), function(key, val){
					    	$(val).attr("name", "userAddresses["+k+"]."+$(val).attr("name").split(".")[1]);
					    });
					});
					
					// descrese data-rf-row-count attribute value by 1 after remove a row.
					var data_rf_row_count = $(".address-repeat .wrapper .container").attr("data-rf-row-count");
					$(".address-repeat .wrapper .container").attr("data-rf-row-count", parseInt(data_rf_row_count) - 1);
				}
			});
		});
	}
});

function deleteUser(userId, deleteUserUrl) {
	deleteUserUrl = deleteUserUrl + "?userId="+userId;
	
	$.ajax({
		url: deleteUserUrl,
		method: "GET",
		success: function(data) {
			console.log(data);
		},
		error: function(data) {
			console.log(data);
		}
	});
}

var dataColumns = [
	{
		title: "Name",
		render: function(data, type, row) {
			return row.firstName + " " + row.lastName;
		}
	},
	{
		title: "Gender",
		data: "gender"
	},
	{
		title: "UserName",
		data: "userName"
	},
	{
		title: "Email",
		data: "emailAddress"
	}, 
	{
		title: "Address",
		render: function(data, type, row) {
			console.log(row);
			return "-";
		}
	},
	{
		title: "Contact",
		data: "phone"
	}
];

function loadUserTable(ajaxUrl) {
	$('#userTable').DataTable({
		"processing": true,
        "serverSide": true,
        "responsive": true,
        "ajax": {
            "url": ajaxUrl,
            "type": "POST"
        },
        "columns": dataColumns
	});
}

