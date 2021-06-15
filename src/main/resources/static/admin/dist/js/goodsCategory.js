var MouseOnSearchResultUl  //全局变量
var arr=[];
function clickButton(thi,categoryId){
			debugger;
			 if (arr.includes(categoryId)) {
				return;
			} 
			arr.push(categoryId);	
								
		    $.ajax({
            type: 'POST',//方法类型
            url: '/admin/searchCategory',
            contentType: 'application/json',
            data: JSON.stringify(categoryId),
            success: function (result) {
				//サーバーが成功した場合
                if (result.resultCode == 200) {
				debugger;	
												
					showResult(thi,result);
                } else {
                    	swal(result.message, {
                        icon: "error",
                    });
                }
                
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
             }
         })  
	             
};


function showResult(thi,result){
	var tcJoinCategoryList = result.data.tcJoinCategory;
	var gsList = result.data.gsList;
	
	 
	 
	 var option = " ";
	 var cloneUl = $(".unique").clone().removeClass("unique");
	
	 
	for (var j = 0; j < tcJoinCategoryList.length; j++) {

		var el =cloneUl.find(".dumyLi").clone().removeClass("dumyLi");
		for (var i = 0; i < gsList.length; i++) {
			var se = $('<select/>');

			//<option value="gsM[i].id">  gsM[i].name  </option>
			option += '<option value=\"' + gsList[i].id + '\">' + gsList[i].name + '</option>'
			se.html(option);

			if (tcJoinCategoryList[j].id == null) {
				se.val(gsList[0].id);
				//	$(".select").val(gsList[0].campaign);
			}

			if (tcJoinCategoryList[j].id != null && gsList[i].id == tcJoinCategoryList[j].id) {
				se.val(gsList[i].id);
				//	se.val(gsList[i].campaign);			
			}
		}

		el.find("input:first-child").before(se);
		if (tcJoinCategoryList[j].id != null) {
			el.find(".secondCheck").prop('checked', true);
		}
		//	el.find(".secondCheck").prop('checked',true);
		//	cloneUl.find(".secondCheck").prop('checked',true);
		var sd = el.find("input:nth-child(5)");
		sd.val(formatDate(tcJoinCategoryList[j].startDate));

		var ed = el.find("input:nth-child(7)");
		ed.val(formatDate(tcJoinCategoryList[j].endDate));
		/*se.val(formatDate(tcJoinCategoryList[i].startDate));
		se.val(formatDate(tcJoinCategoryList[i].endDate));*/

		var link = el.find("a");
		link.text(tcJoinCategoryList[j].categoryName);
		//	cloneUl.find('.button2').attr('onClick','clickButton(' +this +','+tcJoinCategoryList[i].categoryId+');');
		el.find("#plus1").attr('onclick', 'clickButton(this,' + tcJoinCategoryList[j].categoryId + ')');
		//	cloneUl.find("#stupid").find(" button:first-child").attr('onclick','closeButton(this)');
		//	arr = arr.filter(item => !itemToBeRemoved.includes(tcJoinCategoryList[i].categoryId));
		cloneUl.find(".dumyLi").before(el);

	}
  

	cloneUl.show();
	//appendToSearchBar(thi,$(".abc"));
	var rect = thi.getBoundingClientRect();//转换成dom加[0]  convert jquery object to dom by searchBar[0]
		/*thi.value = 'top:' + rect.top+'\r\n'; 
        thi.value += 'left:' + rect.left+'\r\n'; 
        thi.value += 'bottom:' + rect.bottom+'\r\n'; 
        thi.value += 'right:' + rect.right+'\r\n';*/
	console.log(rect.top,rect.right,rect.bottom,rect.left);
	cloneUl.css({top: rect.top,left: rect.right,position:'fixed'});//相对定位relative  绝对定位absolute
	$("#mainContent").append(cloneUl);
	
	/*function closeButton () {
		cloneUl.find("#closeButton").hide();
	}*/
	cloneUl.find("#stupid").find(" button:first-child").click(function(){
		 
		cloneUl.find("#stupid").remove();
	})
/*	cloneUl.find("#closeButton").click(function(){		
    cloneUl.find("#stupid").remove();
 });*/	
}



function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
	
$(".abc").mousemove(function(){
	MouseOnSearchResultUl = true;
});
$(".abc").mouseleave(function(){
	MouseOnSearchResultUl = false;
})

//add delete TC record by coca 2021/05/30
debugger;
$(".check").change(function() {
 var ischecked = $(this).is(':checked');
 if (!ischecked) {
  var categoryId = $(this).val();

 $.ajax({
  type: 'POST',//方法类型
  url: '/admin/deleteTableCategory',
  contentType: 'application/json',
  data: JSON.stringify(categoryId),
  success: function(result) {
   if (result.resultCode == 200) {
     swal("ご削除出来ました！" ,{
      icon:"success",
     });
   } else {
    swal(result.message, {
     icon: "error",
    });
   }
   ;
  },
  error: function() {
   swal("操作失败", {
    icon: "error",
   });
  }
 });
  }else{
	     var id= $(this).parent().parent().find(".custom-select1").val();		   
		 var startDate = $(this).parent().find("#date1").val();
		 var endDate = $(this).parent().find("#date2").val()
		 var categoryId = $(this).val();
	var data ={
		"id":id,
		"categoryId":categoryId,
		 "startDate":startDate,
		 "endDate":endDate,
 	};
$.ajax({
  type: 'POST',//方法类型
  url: '/admin/insertTc',
  contentType: 'application/json',
  data: JSON.stringify(data),
  success: function(result) {
   if (result.resultCode == 200) {
     swal("ご挿入出来ました！" ,{
      icon:"success",
     });
   } else {
    swal(result.message, {
     icon: "error",
    });
   }
   ;
  },
  error: function() {
   swal("操作失败", {
    icon: "error",
   });
  }
 });	
}
});

//2021/06/02 insertSale 绑定modal上的保存按钮
$(function(){
 $("#giveawayBtn").click(function(){
  $(".modal").fadeIn();
 });
 $("#cancell").click(function(){
  $(".modal").fadeOut();
 });
});

/*$(function(){
  //なにかしらの処理
   var primaryGoodsId = $("#campaigSentPGid").val();
   
  $.ajax({
        type: 'POST',//方法类型
        url: '/admin/giveawayCompaignSent',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
//サーバーが成功した場合
            if (result.resultCode == 200) {
			    var levelTwoSelect = '';
                var secondLevelCategories = result.data.gdlist;
                var length2 = secondLevelCategories.length;
                for (var i = 0; i < length2; i++) {
                    levelTwoSelect += '<option value=\"' + secondLevelCategories[i].goodsId + '\">' + secondLevelCategories[i].goodsName + '</option>';
                }
                $('#subGoodsId').html(levelTwoSelect);
            } else {
                 swal(result.message, {
                    icon: "error",
                });
            }
            
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
         }
     })
});*/


 $("#saveSaleButton").click(function(){ 
 var primaryGoodsId = $("#campaigSentPGid").val();
 var subGoodsId = $("#campaigSentSGid").val();
 var data = {
  "primaryGoodsId":primaryGoodsId,
 "subGoodsId":subGoodsId,
    };   
    $.ajax({
        type: 'POST',//方法类型
        url: '/admin/insertCompaignSent',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
//サーバーが成功した場合
            if (result.resultCode == 200) {
   debugger;     
     swal("ご挿入出来ました！" ,{
      icon:"success",
     });
            } else {
                 swal(result.message, {
                    icon: "error",
                });
            }
            
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
         }
     })
     $(".modal").fadeOut();
  });
  