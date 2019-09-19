$(".save_button").on('click', function() {

	var userInfoList = [];

	$("#new tr").each(function() {
		var currentRow = $(this);

		var S_id = currentRow.find("td:eq(0)").text();
		var E_id = currentRow.find("td:eq(1)").text();
		var Year = currentRow.find("td:eq(2)").text();
		var Month = currentRow.find("td:eq(3)").text();
		var Date = currentRow.find("td:eq(4)").text();
		var In_time = currentRow.find("td:eq(5)").text();
		var Out_time = currentRow.find("td:eq(6)").text();
		var Lunch_break = currentRow.find("td:eq(7)").text();
		var Total_hours = currentRow.find("td:eq(8)").text();
		var Holiday = currentRow.find("td:eq(9)").text();
		var Project_id = currentRow.find("td:eq(10)").text();
		var Remarks = currentRow.find("td:eq(11)").text();

		var user = {};
		user.serialId = S_id;
		user.employeeId = E_id;
		user.year = Year;
		user.month = Month;
		user.date = Date;
		user.inTime = In_time;
		user.outTime = Out_time;
		user.lunchTime = Lunch_break;
		user.totalHours = Total_hours;
		user.holiday = Holiday;
		user.projectId = Project_id;
		user.remarks = Remarks;

		userInfoList.push(user);
	});
	saveData(userInfoList);

});

function saveData(userInfoList) {
	$.ajax({
		type : "POST",
		url : "/save",
		data : JSON.stringify(userInfoList),
		contentType : "application/json",
		success : function(isCreated) {
			if(isCreated){
				window.location.href="http://localhost:8080/"
			}
			else{
				alert("update is failed");
			}
		}
	});
}
