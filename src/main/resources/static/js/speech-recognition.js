function startDictation() {

				if (window.hasOwnProperty('webkitSpeechRecognition')) {
					      var recognition = new webkitSpeechRecognition();

					      recognition.continuous = false;
					      recognition.interimResults = false;

					      recognition.lang = "en-US";
					      recognition.start();

					      recognition.onresult = function(e) {
					        var result = e.results[0][0].transcript;
					        recognition.stop();
					        switch (result) {
								case "users":
									document.getElementById("users").click();
									break;
								case "new product":
									document.getElementById("new-product").click();
									break;
								case "products":
									document.getElementById("products").click();
									break;
								case "smart shop":
									document.getElementById("smart-shopping").click();
									break;
								case "shop":
									document.getElementById("shopping").click();
									break;
								case "profile":
									document.getElementById("profile").click();
									break;
								case "product number":
									document.getElementById("product_no").focus();
									break;
								case "product name":
									document.getElementById("product_name").focus();								
									break;
								case "description":
									document.getElementById("description").focus();
									break;
								case "price EUR":
									document.getElementById("price_EUR").focus();
									break;
								case "price USD":
									document.getElementById("price_USD").focus();
									break;

							default:
								alert(result);
								break;
							}
					      };

					      recognition.onerror = function(e) {
					        recognition.stop();
					      }
					}
}
