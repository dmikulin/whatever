/*<![CDATA[*/

$('.datepicker').pickadate({
	selectMonths : true,
	selectYear : true,
	changeYear : true,
	changeMonth : true,
	format : 'dd.mm.yyyy'
});

function dateToString(date) {
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var output = (day < 10 ? '0' : '') + day + '.' + (month < 10 ? '0' : '')
			+ month + '.' + date.getFullYear();
	return output;
}

$(document).ready(function() {
	$('select').material_select();
	var $input = $('#warrant_date').pickadate();
	var $input2 = $('#travel_date').pickadate();
	var pickerWarrantDate = $input.pickadate('picker');
	var pickerTravelDate = $input2.pickadate('picker');

	var travel_date = new Date();
	var warrant_date = new Date();
	warrant_date.setTime(warrant_date.getTime() - 86400000);
	while (warrant_date.getDay() == 6 || warrant_date.getDay() == 0) {
		warrant_date.setTime(warrant_date.getTime() - 86400000);
	}
	pickerWarrantDate.set('select', dateToString(warrant_date))
	pickerTravelDate.set('select', dateToString(travel_date))

});

$(document).ready(function() {
	$('.modal').modal();
});

$("#typeOfVehicle").change(function(){
	var typeOfVehicleValue = this.value;
	if(typeOfVehicleValue==-1){
		var defaultOption = document.getElementById("vehicleDefault").outerHTML;
		var $selectDropdown = $("#vehicle").empty().html(defaultOption);
		$('select').material_select();
		return;
	}
	refreshVehicles(typeOfVehicleValue);
});

function refreshVehicles(typeOfVehicleValue){
	$.ajax({
		url:"/api/getVehicleByType",
		type:"get",
		data:{
			typeOfVehicle : typeOfVehicleValue
		},
		dataType: 'json',
		success: function(response){
			var select = document.getElementById('vehicle');
			var defaultOption = document.getElementById("vehicleDefault").outerHTML;
			var $selectDropdown = $("#vehicle").empty().html(defaultOption);

			$.each(response,function(i,vehicle){
				var option = document.createElement('option');
				option.value = vehicle.vehicleId;
				option.innerHTML = vehicle.brand+", "+vehicle.model+", "+vehicle.licensePlate;
				select.appendChild(option);
			});
			$('select').material_select();
		},
		error: function(error){
			var defaultOption = document.getElementById("vehicleDefault").outerHTML;
			var $selectDropdown = $("#vehicle").empty().html(defaultOption);
			$('select').material_select();
		}
	});
}

$("#actionAdd").click(function(){
	var brandValue = document.getElementById("brand").value;
	var modelValue = document.getElementById("model").value;
	var plateValue = document.getElementById("plate").value;
	var selectElement = document.getElementById("typeOfVehicleNew");
	var typeOfVehicleValue = selectElement.options[selectElement.selectedIndex].value;
	
	var verifyBrand = brandValue.replace(" ","");
	var verifyModel = modelValue.replace(" ","");
	var verifyPlate = plateValue.replace(" ","");
	var verifyed = 0;
	if(verifyBrand.length>0){
		verifyed++;
		document.getElementById("brand").classList.remove("invalid");
	}else{
		$("#brand").addClass("invalid");
	}
	if(verifyModel.length>0){
		verifyed++;
		document.getElementById("model").classList.remove("invalid");
	}else{
		$("#model").addClass("invalid");
	}
	if(verifyPlate.length>0){
		verifyed++;
		document.getElementById("plate").classList.remove("invalid");
	}else{
		$("#plate").addClass("invalid");
	}
	if(typeOfVehicleValue.length>0 && typeOfVehicleValue!='-1'){
		verifyed++;
		document.getElementById("typeOfVehicleNew").classList.remove("invalid");
		$('select').material_select();
	}else{
		var element = document.querySelector("div.selectDiv div input");	
		element.style = "border-bottom: 1px solid #F44336; box-shadow:0 1px 0 0 #F44336;";
	}
	if(verifyed==4){
		$.ajax({
			url:"/api/newVehicle",
			type:"get",
			data:{
				typeOfVehicle : typeOfVehicleValue,
				brand : brandValue,
				model : modelValue,
				licensePlate : plateValue
			},
			dataType: 'json',
			success: function(response){
				successRequest = true;
				refreshVehicles(typeOfVehicleValue);
				$('#vehicleModal').modal('close');
			},
			error: function(error){
			}
		});
	}
});

$("#typeOfVehicleNew").change(function(){
	if(typeOfVehicle.length>0 && typeOfVehicle!='-1'){
		var element = document.querySelector("div.selectDiv div input");
		element.style = "";
	}
});

var places = [];

$("#travel_destination").keypress(function(){
	var placeValue = document.getElementById("travel_destination").value;
	$.ajax({
		url:"/api/getPlaces",
		type:"get",
		data:{
			place : placeValue
		},
		dataType: 'text',
		success: function(response){
			var responseObj = JSON.parse(response);
			places=[];
			$.each(responseObj.predictions,function(i,place){
				places[place.description]=null;
				console.log(place.description);
			})
			$('input.autocomplete').autocomplete({
			    data: places
			});
		},
		error: function(error){
		}
	});
});

$(function() {
  $('input.autocomplete').autocomplete({
    data: places
  });
});

function word(duration) {
	var durationWords;
	switch(duration) {
	    case 1: durationWords = "jedan";  break;
	    case 2: durationWords = "dva"; break;
	    case 3: durationWords = "tri"; break;
	    case 4: durationWords = "četiri"; break;
	    case 5: durationWords = "pet"; break;
	    case 6: durationWords = "šest"; break;
	    case 7: durationWords = "sedam"; break;
	    case 8: durationWords = "osam"; break;
	    case 9: durationWords = "devet"; break;
	}
	return durationWords;
}

$("#travel_duration").change(function(){
	
	var duration = document.getElementById("travel_duration").value;	
	var durationWords;
	
	if(duration<1){
		durationWords="";
	}
	else if(duration<10){
		if(duration==1)	durationWords = word(Number(duration))+" dan";
		else durationWords = word(Number(duration))+" dana";
	}
	else if(duration<20){
		if(duration==10) durationWords = "deset dana";
		else if(duration==11) durationWords = "jedanaest dana";
		else if(duration==14) durationWords = "četrnaest dana";
		else if(duration==16) durationWords = "šesnaest dana";
		else durationWords = word(duration%10)+"naest dana";		
	}
	else if(duration<100) {
		desetice =  Math.floor(duration/10);
		jedinice = duration%10;
		
		if(desetice==5) durationWords ="pedeset"
		else if(desetice==4) durationWords ="četrdeset"
		else if(desetice==6) durationWords ="šezdeset"
		else if(desetice==9) durationWords ="devedeset"
		else durationWords = word(desetice)+ "deset";
		
		if(jedinice==0) durationWords += " dana";
		else if(jedinice==1) durationWords += " i "+ word(jedinice) + " dan";
		else durationWords += " i "+ word(jedinice) + " dana";

	}else {
		durationWords="";
	}
	
	document.getElementById('travel_duration_words').focus();
	document.getElementById('travel_duration_words').value = durationWords; 
	document.getElementById("travel_duration").focus();
	
});
	
	
/* ]]> */