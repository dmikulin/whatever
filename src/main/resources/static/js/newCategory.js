$(document).ready(function() {
	$('select').material_select();
	$('.modal').modal();
});

function refreshCategory(){
	$.ajax({
		url:"/api/getCategories",
		type:"get",
		data:{},
		dataType: 'json',
		success: function(response){
			var select = document.getElementById('productCategory');
			var defaultOption = document.getElementById("categoryDefault").outerHTML;
			var $selectDropdown = $("#productCategory").empty().html(defaultOption);

			$.each(response,function(i,category){
				var option = document.createElement('option');
				option.value = category.categoryId;
				option.innerHTML = category.name;
				select.appendChild(option);
			});
			$('select').material_select();
		},
		error: function(error){
			var defaultOption = document.getElementById("categoryDefault").outerHTML;
			var $selectDropdown = $("#productCategory").empty().html(defaultOption);
			$('select').material_select();
		}
	});
}

$("#saveCategory").click(function(){
	var categoryNameVal = document.getElementById("category-name").value;
	var categoryDescVal = document.getElementById("category-desc").value;
	
		$.ajax({
			url:"/api/newCategory",
			type:"get",
			data:{
				categoryName : categoryNameVal,
				categoryDesc : categoryDescVal
			},
			dataType: 'json',
			success: function(response){
				successRequest = true;
				refreshCategory();
				$('#categoryModal').modal('close');
			},
			error: function(error){
			}
		});
});
