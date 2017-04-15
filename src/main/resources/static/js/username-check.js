var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Passwords Don't Match");
	} else {
		confirm_password.setCustomValidity('');
	}
}
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

$("#username").change(function() {
	element = this;
	username = this.value;
	$.ajax({
		url : "/api/usernameExists",
		type : "get",
		data : {
			username : username
		},
		dataType : 'text',
		success : function(response) {
			if (response == 'true') {
				element.classList.add("invalid");
			} else {
				element.classList.add("valid");
			}
		},
		error : function(error) {
		}
	});
});

$("#email").change(function() {
	element = this;
	email = this.value;
	$.ajax({
		url : "/api/emailExists",
		type : "get",
		data : {
			email : email
		},
		dataType : 'text',
		success : function(response) {
			if (response == 'true') {
				element.classList.add("invalid");
			} else {
				element.classList.add("valid");
			}
		},
		error : function(error) {
		}
	});
});

$(document)
		.ready(
				function() {
					$('#password').keyup(
							function() {
								$('#strengthResult').html(
										checkStrength($('#password').val()))
							})
					function checkStrength(password) {
						var strength = 0
						if (password.length < 6) {
							$('#strengthResult').removeClass()
							$('#strengthResult').addClass('short')
							return 'Too short'
						}
						if (password.length > 7)
							strength += 1
							// If password contains both lower and uppercase
							// characters, increase strength value.
						if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))
							strength += 1
							// If it has numbers and characters, increase
							// strength value.
						if (password.match(/([a-zA-Z])/)
								&& password.match(/([0-9])/))
							strength += 1
							// If it has one special character, increase
							// strength value.
						if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))
							strength += 1
							// If it has two special characters, increase
							// strength value.
						if (password
								.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/))
							strength += 1
							// Calculated strength value, we can return messages
							// If value is less than 2
						if (strength < 2) {
							$('#strengthResult').removeClass()
							$('#strengthResult').addClass('weak')
							return 'Weak'
						} else if (strength == 2) {
							$('#strengthResult').removeClass()
							$('#strengthResult').addClass('good')
							return 'Good'
						} else {
							$('#strengthResult').removeClass()
							$('#strengthResult').addClass('strong')
							return 'Strong'
						}
					}
				});
