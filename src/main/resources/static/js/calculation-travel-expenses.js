$('.datepicker').pickadate({
	selectMonths : true,
	selectYears : true,
	format : 'dd.mm.yyyy'
});

function addRelationRows(counter) {
	var relationRow = '<div class="row" style="margin: 0;" id="relation'
			+ counter + '">';
	relationRow += '<div class="input-field col s12 l5" >';
	relationRow += '<label for="from' + counter + '">OD:</label>';
	relationRow += '<input id="from' + counter + '" name="from' + counter
			+ '" type="text" required="required" /></div>';
	relationRow += '<div class="input-field col s12 l5" >';
	relationRow += '<label for="too' + counter + '">DO:</label>';
	relationRow += '<input id="too' + counter + '" name="too' + counter
			+ '" type="text" required="required" /></div>';
	relationRow += '<div class="input-field col s12 l2" >';
	relationRow += '<label for="distance' + counter
			+ '">Prijedjeni km:</label>';
	relationRow += '<input onchange="calculateDistance()" class="cal-distance" id="distance' + counter + '" name="distance'
			+ counter + '" type="number" required="required" /></div></div>';

	jQuery('#relations').append(relationRow);
}

function addCostRows(counter) {

	var $div = $('select[id^="currency"]:last');
	var $klon = $div.clone().prop('id', 'currency' + counter);
	var $klon = $klon.prop('name', 'currency' + counter);

	var costRow = '<div class="row" style="margin: 0;" id="cost' + counter+ '">';
	costRow += '<div class="row" style="margin: 0; ">';
	costRow += '<div class="input-field col s12 l8" >';
	costRow += '<label for="cost-description' + counter
			+ '">Description cost:</label>';
	costRow += '<input style="margin: 0; " id="cost-description' + counter
			+ '" name="cost-description' + counter
			+ '" type="text" /></div>';
	costRow += '<div class="input-field col s10 l3" >';
	costRow += '<label for="amount' + counter + '">Amount:</label>';
	costRow += '<input style="text-align: right; margin: 0;" id="amount' + counter
			+ '" name="amount' + counter
			+ '" type="number" step="0.01" /></div>';
	costRow += '<div class="input-field col s2 l1" id="val' + counter + '" >';
	costRow += '</div></div>';
	
	costRow += '<div class="row" style="margin: 0;">';
	costRow += '<div class="input-field col s12 l1" ></div>';
	costRow += '<div class="file-field input-field s12 l5" >';
	costRow += '<div class="btn orange">';
	costRow += '<span>Add Receipts</span>';
	costRow += '  <input type="file" name="files" />';
	costRow += '</div>';
	costRow += '<div class="file-path-wrapper">';
	costRow += '  <input class="file-path validate" type="text" />';
	costRow += ' </div>';
	costRow += '</div>';	
	costRow += '<div class="input-field col s12 l6" ></div></div></div>';


	jQuery('#other-costs').append(costRow);
	jQuery('#val' + counter).append($klon);
}

$(document).ready(function() {
	var rCounter = getCount(document.getElementById("relations"));
	var cCounter = getCount(document.getElementById("other-costs"));

	function getCount(parent) {
		var relevantChildren = 0;
		if(parent==null){
			return relevantChildren;
		}
		var children = parent.childNodes.length;
		for (var i = 0; i < children; i++) {
			if (parent.childNodes[i].nodeType != 3) {
				relevantChildren++;
			}
		}
		return relevantChildren;
	}
	if(rCounter!=null){
		$('#relations-counter').val(rCounter);
	}
	$('#cost-counter').val(cCounter);

	$("#add").click(function() {
		rCounter++;
		addRelationRows(rCounter);
		$('#relations-counter').val(rCounter);
		return false;
	});
	$("#remove").click(function() {
		if (rCounter >= 1) {
			$('#relation' + rCounter).remove();
			rCounter--;
			$('#relations-counter').val(rCounter);
		}
		return false;
	});

	$("#add-cost").click(function() {
		cCounter++;
		addCostRows(cCounter);
		$('#cost-counter').val(cCounter);
		return false;
	});
	$("#remove-cost").click(function() {
		if (cCounter > 1) {
			$('#cost' + cCounter).remove();
			cCounter--;
			$('#cost-counter').val(cCounter);
		}
		return false;
	});

	dateValidation();
	$( ".date-validation" ).each(function(index) {
	    $(this).on("click", function(){
	    	dateValidation();
	    });
	});
	
	$("#end-travel-date").change(function(){
		dateValidation();
	});
	
});

var fcs = document.getElementById("final-counter-state");
var ics = document.getElementById("initial-counter-state");

function checkValues() {

	if (parseInt(fcs.value) <= parseInt(ics.value)) {
		fcs.setCustomValidity("Zavrsno stanje mora biti vece!");
	} else {
		fcs.setCustomValidity('');
	}
	if (parseInt(ics.value) <= 0) {
		ics.setCustomValidity("Broj mora biti veci od 0!");
	} else {
		ics.setCustomValidity('');
	}
}

if(fcs!=null){
	fcs.onchange = checkValues;
}
if(fcs!=null){
	ics.onchange = checkValues;
}


$( "#final-counter-state" ).one("click",function() {
	calculateDistance();
});

$( "#initial-counter-state" ).change(function() {
	calculateDistance();
});

function calculateDistance(){
	var element = document.getElementById("final-counter-state");
	var sum = 0;
	$('.cal-distance').each(function(){
		var elementDistance = this;
		sum += parseInt(elementDistance.value);
	});
	initCount = document.getElementById("initial-counter-state").value;
	element.value=sum+parseInt(initCount);
	checkValues();
}

function dateValidation(){
	startTripDateElement = document.getElementById("travel-date");
	endTripDateElement = document.getElementById("end-travel-date");
	departureDateElement = document.getElementById("departure-time");
	departureBorderDateElement = document.getElementById("departure-border-time");
	returnDateElement = document.getElementById("returning-time");
	returnBorderDateElement = document.getElementById("returning-border-time");
	
	startTripDate = stringToDate(startTripDateElement.value);
	endTripDate = stringToDate(endTripDateElement.value+" 23:59");
	departureDate = stringToDate(departureDateElement.value);
	departureBorderDate = stringToDate(departureBorderDateElement.value);
	returnDate = stringToDate(returnDateElement.value);
	returnBorderDate = stringToDate(returnBorderDateElement.value);
	
	var counter = 0;
	
	if(startTripDate.getTime()>endTripDate.getTime()){
		endTripDateElement.className = classReplaceValidWithInvalid(endTripDateElement.className);
	}else{
		endTripDateElement.className = classReplaceInvalidWithValid(endTripDateElement.className);
		counter++;
	}
	
	if((startTripDate.getTime()>departureDate.getTime())||(departureDate.getTime()>endTripDate.getTime())){
		departureDateElement.className = classReplaceValidWithInvalid(departureDateElement.className);
	}else{
		departureDateElement.className = classReplaceInvalidWithValid(departureDateElement.className);
		counter++;
	}
	
	if((departureDate.getTime()>departureBorderDate.getTime())||(departureBorderDate.getTime()>endTripDate.getTime())){
		departureBorderDateElement.className = classReplaceValidWithInvalid(departureBorderDateElement.className);
	}else{
		departureBorderDateElement.className = classReplaceInvalidWithValid(departureBorderDateElement.className);
		counter++;
	}
		
	if((departureBorderDate.getTime()>(returnBorderDate.getTime()))||(returnBorderDate.getTime()> endTripDate.getTime())){
		returnBorderDateElement.className = classReplaceValidWithInvalid(returnBorderDateElement.className);
	}else{
		returnBorderDateElement.className = classReplaceInvalidWithValid(returnBorderDateElement.className);
		counter++;
	}
	
	if((returnBorderDate.getTime()>returnDate.getTime())||(returnDate.getTime()>endTripDate.getTime())){
		returnDateElement.className = classReplaceValidWithInvalid(returnDateElement.className);
	}else{
		returnDateElement.className = classReplaceInvalidWithValid(returnDateElement.className);
		counter++;
	}
	
	if(counter==5){
		document.getElementById("submit").disabled = false;
	}else{
		document.getElementById("submit").disabled = true;
	}
}

function classReplaceValidWithInvalid(cssClass){
	cssClass=cssClass.toString();
	var cssClassFilter = cssClass.replace("valid", "");
	var cssClassFinal = cssClassFilter.replace("invalid", "");
	cssClassFinal += " invalid";
	return cssClassFinal;
}

function classReplaceInvalidWithValid(cssClass){
	cssClass=cssClass.toString();
	var cssClassFilter = cssClass.replace("invalid", "");
	var cssClassFinal = cssClassFilter.replace("valid", "");
	cssClassFinal += " valid";
	return cssClassFinal;
}

function stringToDate(date){
	dateAndTime = date.split(" ");
	dateString = dateAndTime[0];
	if(dateAndTime[1]==null){
		dateAndTime[1]="";
	}
	timeString = dateAndTime[1];
	dateSplit = dateString.split(".");
	timeSplit = timeString.split(":");
	if(dateSplit.length==3){
		if(timeSplit.length==2){
			return new Date(dateSplit[2],dateSplit[1],dateSplit[0],timeSplit[0],timeSplit[1]);
		}else{
			return new Date(dateSplit[2],dateSplit[1],dateSplit[0]);
		}
	}
	return new Date();
}
