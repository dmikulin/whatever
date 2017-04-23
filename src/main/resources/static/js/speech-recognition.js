$(document)
		.ready(
				function() {
	if(annyang){
		var commands = {
			    'users' : function() { document.getElementById("users").click(); },
				'new product' : function() { document.getElementById("new-product").click(); },
				'products' : function() { document.getElementById("products").click(); },
				'smart shop' : function() {	document.getElementById("smart-shopping").click(); },
				'shop' : function() { document.getElementById("shopping").click(); },
				'profile' : function() { document.getElementById("profile").click(); },
				'log out' : function() { document.getElementById("log-out").click(); },
				'product number *val' : function(val) {
					document.getElementById("product_no").focus();
					document.getElementById("product_no").value = val;
				},
				
				'product name *val': function(val) { 
			    	document.getElementById("product_name").focus();
					document.getElementById("product_name").value = val;
			    },
			    'select product category *val': function(val) { 
			    	document.getElementById("productCategory").click();
			    },
			    'add category': function() { 
			    	document.getElementById("addCategory").click();
			    },
			    'description *val': function(val) { 
			    	document.getElementById("description").focus();
					document.getElementById("description").value = val;
			    },
			    'price EUR *val': function(val) { 
			    	document.getElementById("price_EUR").focus();
					document.getElementById("price_EUR").value = val;
			    },
			    'price USD *val': function(val) { 
			    	document.getElementById("price_USD").focus();
					document.getElementById("price_USD").value = val;
			    },
				'save product' : function() { document.getElementById("saveProduct").click(); },
				'category name *val': function(val) { 
			    	document.getElementById("category-name").focus();
					document.getElementById("category-name").value = val;
			    },
			    'category description *val': function(val) { 
			    	document.getElementById("category-description").focus();
					document.getElementById("category-description").value = val;
			    },
				'save category' : function() { document.getElementById("actionAdd").click(); }
			  };
		 annyang.addCommands(commands);
		 SpeechKITT.annyang();
		 SpeechKITT.setStylesheet('//cdnjs.cloudflare.com/ajax/libs/SpeechKITT/0.3.0/themes/flat-midnight-blue.css');
		 SpeechKITT.vroom();
		 
	}	
});
/*
				if (window.hasOwnProperty('webkitSpeechRecognition')) {
					      var recognition = new webkitSpeechRecognition();

					      recognition.continuous = false;
					      recognition.interimResults = false;

					      recognition.lang = "en-US";
					      recognition.start();

					      recognition.onresult = function(e) {
					        var result = e.results[0][0].transcript;					        
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
								case "log out":
									document.getElementById("log-out").click();
									break;
								case "product number":
									document.getElementById("product_no").focus();
									break;
								case "product name":
									document.getElementById("product_name").focus();
									result=e.results[0][0].transcript;
									document.getElementById("product_name").value = result;
									break;
								case "select product category":
									document.getElementById("productCategory").click();								
									break;
								case "add category":
									document.getElementById("addCategory").click();								
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
								case "save":
									document.getElementById("save").click();
									break;

							default:
								alert(result);
								break;
							}
					      };
					      recognition.onerror = function(e) {
					        recognition.stop();
					      }
					}*/

