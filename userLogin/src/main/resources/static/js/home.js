var homeApi = (function() {
	
	function _getTimeAndDate() {
		let currentDate = "";
		var today = new Date();
		currentDate = today.getDate()+"/"+(today.getMonth()+1)+"/"+today.getFullYear()+" - "+
					  today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
		return currentDate;
	}

	function _getInfo() {
		console.log("IN GET INFO:");
		$.ajax({
			url: "/app/connect",
			method: "GET",
			headers: {
			    "Authorization": "Bearer "+document.cookie,
			}
		}).then(function(response) {
			let obj = JSON.parse(JSON.stringify(response));
			$("#messageField").text("You are authorized now!");
			$("#serverMsg").text("Server says: "+obj.msg);
		}).catch(function(err) {
			$("#messageField").text("You are not authorized!");
		});
	}
	
	function getInfo() {
		console.log("Send at: "+_getTimeAndDate());
		_getInfo();
	}

	return {
		getInfo: getInfo
	}
})();