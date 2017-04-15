function validateUsername(username) {
	var re = /^[a-z0-9_.]+$/;
    return re.test(username);
}

$(document).ready(function() {
	$('#username').keyup(function() {
		$('#usernameResult').html(checkStrength($('#username').val()))
	})
	function checkStrength(username) {
		$('#username').removeClass()
		$('#username').addClass('form-control')			
		$('#usernameResult').removeClass()		
		if (!validateUsername(username)) {
			$('#username').addClass('bad')
			$('#usernameResult').addClass('short')
			return 'The username can only consist of lowercase, number, dot and underscore!'
		}
		else {
			if (username.length<5 || username.length>15) {
				$('#username').addClass('bad')
				$('#usernameResult').addClass('short')
				return 'The username must be more than 4 and less than 15 characters long!'
			}
			searchAjax()
		}
	}
});

function searchAjax() {
	
	var search = {}
	search["username"] = $("#username").val();
	
	$('#username').removeClass()
	$('#username').addClass('form-control')			
	$('#usernameResult').removeClass()		

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/getUser",
		data : search,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			$('#username').addClass('bad')
			$('#usernameResult').addClass('short')
			$('#usernameResult').html("This username is already taken!")
		},
		error : function(e) {
			console.log("ERROR: ", e);
			$('#username').addClass('goood')
			$('#usernameResult').addClass('strong')
			$('#usernameResult').html("")
		}
	});
}

$(document).ready(function() {
					$('#password').keyup(function() {
						$('#strengthResult').html(checkStrength($('#password').val()))
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

$(document).ready(function() {
	$('#cpassword').keyup(function() {
		$('#matcherResult').html(checkStrength($('#password').val(),$('#cpassword').val()))
	})
	function checkStrength(password, cpassword) {
		$('#cpassword').removeClass()
		$('#cpassword').addClass('form-control')			
		$('#matcherResult').removeClass()		
		if (password!==cpassword) {
			$('#cpassword').addClass('bad')
			$('#matcherResult').addClass('short')
			return 'Passwords don\'t match!'
		}
		else {
			$('#cpassword').addClass('goood')
			$('#matcherResult').addClass('strong')
			return 'Passwords match!'
		}
	}
});

function validateEmail(email) {
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

$(document).ready(function() {
	$('#email').keyup(function() {
		$('#emailResult').html(checkStrength($('#email').val()))
	})
	function checkStrength(email) {
		$('#email').removeClass()
		$('#emailResult').removeClass()
		$('#email').addClass('form-control')
		if (!validateEmail(email)) {
			$('#email').addClass('bad')
			$('#emailResult').addClass('short')
			return 'The input is not a valid email address!'
		}
		else {
			$('#email').addClass('goood')
			$('#emailResult').addClass('strong')
			return ''
		}
	}
});