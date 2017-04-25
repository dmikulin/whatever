$(document)
		.ready(
				function() {
	if(annyang){
		var commands = {
			    'users' : function() { document.getElementById("users").click(); },
				'new product' : function() { document.getElementById("new-product").click(); },
				'products' : function() { document.getElementById("products").click(); },
				'smart shop' : function() {	document.getElementById("smart-shopping").click(); },				
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
			    'price euro *val': function(val) { 
			    	document.getElementById("price_EUR").focus();
					document.getElementById("price_EUR").value = val;
			    },
			    'price dolar *val': function(val) { 
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
