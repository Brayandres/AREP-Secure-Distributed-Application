var registerApi = (function() {

	var _email = "";
	var _password = "";

	class LoginDto {
		constructor(email, password) {
			this.email = email;
			this.password = password;
		}
	}

	function _getTimeAndDate() {
		let currentDate = "";
		var today = new Date();
		currentDate = today.getDate()+"/"+(today.getMonth()+1)+"/"+today.getFullYear()+" - "+
					  today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
		return currentDate;
	}

	function _fieldsAreNotEmpty() {
		let isValid = false;
		let email = $("#emailField").val();
		let password = $("#passwordField").val();
		if (!(
			email === "" || email == null ||
			password === "" || password == null
		)) {
			_email = email;
			_password = password;
			isValid = true;
		}
		return isValid;
	}

	function _postNewUser() {
		console.log("IN POST USR:");
		const loginDto = new LoginDto(_email, _password);
		let postPromise = $.ajax({
			url: "/user",
			type: "POST",
			data: JSON.stringify(loginDto),
			contentType: "application/json"
		});
		postPromise.then(
			function(data) {
				console.log("  Done!");
				let obj = JSON.parse(JSON.stringify(data));
				console.log("    Data: "+JSON.stringify(data)+"\n");
				window.location.href = "login.html";
			},
			function(data) {
				console.log("  Error!");
				console.log("    Data: "+JSON.stringify(data)+"\n");
			}
		);
	}

	function registerNewUser() {
		if (_fieldsAreNotEmpty()) {
			console.log("Send at: "+_getTimeAndDate());
			_postNewUser();
		}
		else {
			window.alert("No field can be empty!");
		}
	}

	return {
		registerNewUser: registerNewUser
	}
})();