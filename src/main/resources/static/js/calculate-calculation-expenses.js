function getCount(parent) {
	var relevantChildren = 0;
	var children = parent.childNodes.length;
	for (var i = 0; i < children; i++) {
		if (parent.childNodes[i].nodeType != 3) {
			relevantChildren++;
		}
	}
	return relevantChildren;
}


function addDailyAllowancesRows(counter) {
	var dailyAllowancesRowElement = document.getElementById("dailyAllowances1").outerHTML;
	var rowReplace = dailyAllowancesRowElement.replace("dailyAllowances1", "dailyAllowances"+counter);
	rowReplace = rowReplace.replace("dailyAllowances1", "dailyAllowances"+counter);
	rowReplace = rowReplace.replace("dailyAllowances1", "dailyAllowances"+counter);
	rowReplace = rowReplace.replace("dailyAllowanceId1", "dailyAllowanceId"+counter);
	rowReplace = rowReplace.replace("dailyAllowanceId1", "dailyAllowanceId"+counter);
	rowReplace = rowReplace.replace("numberOfHours1", "numberOfHours"+counter);
	rowReplace = rowReplace.replace("numberOfHours1", "numberOfHours"+counter);
	rowReplace = rowReplace.replace("numberOfHours1", "numberOfHours"+counter);
	rowReplace = rowReplace.replace("numberOfDailyAllowances1", "numberOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("numberOfDailyAllowances1", "numberOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("numberOfDailyAllowances1", "numberOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("amountOfDailyAllowances1", "amountOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("amountOfDailyAllowances1", "amountOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("amountOfDailyAllowances1", "amountOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("totalAmountOfDailyAllowances1", "totalAmountOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("totalAmountOfDailyAllowances1", "totalAmountOfDailyAllowances"+counter);
	rowReplace = rowReplace.replace("totalAmountOfDailyAllowances1", "totalAmountOfDailyAllowances"+counter);
	jQuery('#dailyAllowances').append(rowReplace);
}

$(document).ready(function() {
	var rCounter = getCount(document.getElementById("dailyAllowances"));
	$('#dailyAllowancesCounter').val(rCounter);
	$("#add").click(function() {
		rCounter++;
		addDailyAllowancesRows(rCounter);
		$('#dailyAllowancesCounter').val(rCounter);
		return false;
	});
	
	$("#remove").click(function() {
		if (rCounter > 1) {
			$('#dailyAllowances' + rCounter).remove();
			rCounter--;
			$('#dailyAllowancesCounter').val(rCounter);
		}
		return false;
	});

	$('.dailyCalculate').on('change', function(){
		var regex = /(\d+)/g;
		rowNumber = this.id.match(regex);
		elementDailyAllowance = document.getElementById("numberOfDailyAllowances"+rowNumber);
		elementAmount = document.getElementById("amountOfDailyAllowances"+rowNumber);
		elementTotalAmount = document.getElementById("totalAmountOfDailyAllowances"+rowNumber);
		startTravelDate = document.getElementById("startTravelDate").innerHTML;
		
		if (elementDailyAllowance.value%1!=0){
			elementDailyAllowance.value=parseInt(elementDailyAllowance.value);
		}
		
		$.ajax({
			url:"/api/calculateAmount",
			type:"get",
			data:{
				numberOfDailyAllowances : elementDailyAllowance.value,
				travelDate : startTravelDate,
				amountOfDailyAllowancesId : elementAmount.value
			},
			dataType: 'text',
			success: function(response){
				elementTotalAmount.value=parseFloat(response).toFixed(2);
				calculateTotal();
			},
			error: function(error){
				elementTotalAmount.value=0;
			}
		});
	});
	
	function calculateTotal(){
		var total = 0;
		for(i=1; i<= parseInt(document.getElementById("dailyAllowancesCounter").value,10); i++){
			console.log("vrijednosti:"+document.getElementById("totalAmountOfDailyAllowances"+i).value);
			total += parseFloat(document.getElementById("totalAmountOfDailyAllowances"+i).value);
		}
		document.getElementById("totalAmountDailyAllowances").value=parseFloat(total).toFixed(2);
		calculateFinalTotal();
	}
	
	
	
	$('.relationCalculate').on('change', function(){
		var total = 0;
		$('.relationCalculate').each(function() {
			total += parseFloat(this.value);
		});
		document.getElementById("totalTransportationAmount").value=parseFloat(total).toFixed(2);
		calculateFinalTotal();
	});
	
	
	$('.otherCalculate').on('change', function(){
		var total = 0;
		$('.otherCalculate').each(function() {
			total += parseFloat(this.value);
		});
		document.getElementById("totalOtherCost").value=parseFloat(total).toFixed(2);
		calculateFinalTotal();
	});
	
	$('#totalAmountDailyAllowances').change(function(){
		calculateFinalTotal();
	});
	
	$('#totalTransportationAmount').change(function(){
		calculateFinalTotal();
	});
	
	$('#totalOtherCost').change(function(){
		calculateFinalTotal();
	});
	
	function calculateFinalTotal(){
		document.getElementById("totalCostBusinessTrip").value=parseFloat(parseFloat(document.getElementById("totalAmountDailyAllowances").value)+parseFloat(document.getElementById("totalTransportationAmount").value)+parseFloat(document.getElementById("totalOtherCost").value)).toFixed(2);
		document.getElementById("remainsForPaymentReturn").value=parseFloat(parseFloat(document.getElementById("deductedForPaidOutImprest").value)+parseFloat(document.getElementById("totalCostBusinessTrip").value)).toFixed(2);
	}
	
	$("input[type=number]").on("change",function(){
		var element = this;
		if(!element.classList.contains("int")){
			element.value = parseFloat(element.value).toFixed(2);
		}
	});
	
});