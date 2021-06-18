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
	var list = result.data.list;
	var gsList = result.data.gsList;
	
	 
	 
	 
	 var cloneUl = $(".unique").clone().removeClass("unique");
	
	 
	for (var j = 0; j < list.length; j++) {
		var option = "";//the 1st arrList will be covered by the second
		var temp="";
		var se = $('<select/>',{id:"inputGroupSelect04"});
		var el =cloneUl.find(".dumyLi").clone().removeClass("dumyLi");
		for (var i = 0; i < gsList.length; i++) {
			
			
						//<option value="gsM[i].id">  gsM[i].name  </option>
			option += '<option value=\"' + gsList[i].id + '\">' + gsList[i].name + '</option>'
			

		/*	if (tcJoinCategoryList[j].id == null) {
				se.val(gsList[0].id);
				//	$(".select").val(gsList[0].campaign);
			}*/

			if (list[j].id != null && gsList[i].id == list[j].id) {
				
				el.find("#isCheck").prop('checked', true);
				temp=gsList[i].id;
							
			}
		
			
		}
		se.html(option);
		if(temp!=""){
			se.val(temp);
		}
		el.find("input:first-child").before(se);
		//	el.find(".secondCheck").prop('checked',true);
		//	cloneUl.find(".secondCheck").prop('checked',true);
		var sd = el.find("input:nth-child(7)");
		sd.val(list[j].startDate);

		var ed = el.find("input:nth-child(9)");
		ed.val(list[j].endDate);		
  		var cId = el.find("input:nth-child(3)");
  		cId.val(list[j].categoryId);
  		var gcId = el.find("input:nth-child(4)");
  		gcId.val(list[j].goodsCategoryId);
  		
  		/*var cGd = el.find("input:nth-child(4)");
  		cGd.val(list[j].goodsId);	*/	

		var link = el.find("a");
		link.text(list[j].categoryName);
		link.text(list[j].goodsName);
		//	cloneUl.find('.button2').attr('onClick','clickButton(' +this +','+tcJoinCategoryList[i].categoryId+');');
		el.find("#plus1").attr('onclick', 'clickButton(this,' + list[j].categoryId + ')');
		el.find("#isCheck").attr('onchange', 'insertDel(this)');
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



/*function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}*/
	
$(".abc").mousemove(function(){
	MouseOnSearchResultUl = true;
});
$(".abc").mouseleave(function(){
	MouseOnSearchResultUl = false;
})

//add delete TC record by coca 2021/05/30

function insertDel(thi) {
	debugger;
	var id = $(thi).parent().parent().find("#inputGroupSelect04").val();
	if(id==1){
		var goodsName=$(thi).parent().find("#mdlCtyNm").text();
		var goodsCategoryId = $(thi).parent().find("#hidgid").val();
		$("#campaignSentModal").find("#campaigSentPGid").val(goodsName);			
		//var goodsId = $(thi).parent().find("#hidgid").val();
			
		$("#campaignSentModal").modal('show');	
		$.ajax({
			type: 'POST',//方法类型
			url: '/admin/giveawayCompaignSent',
			contentType: 'application/json',
			data: JSON.stringify(goodsCategoryId),
			success: function(result) {
				//サーバーが成功した場合
				if (result.resultCode == 200) {
					debugger;
					/*swal("ご挿入出来ました！", {
						icon: "success",
					});*/
					 debugger;
					var goodsList = result.data;
															
					for (var i = 0; i < goodsList.length; i++) {
					$("#subGoodsId").append('<option value=\"' + goodsList[i].goodsId + '\">' + goodsList[i].goodsName + '</option>');												
					}
				
				} else {
					swal(result.message, {
						icon: "error",
					});
				}

			},
			error: function() {
				swal("操作失败", {
					icon: "error",
				});
			}
		})
		return;		
	}else{
	var flag = $(thi).is(':checked');
	var id = $(thi).parent().parent().find("#inputGroupSelect04").val();
	var startDate = $(thi).parent().find("#date1").val();
	var endDate = $(thi).parent().find("#date2").val()
	var categoryId = $(thi).parent().find("#hidChk").val();
	var data = {
		"flag":flag,
		"id": id,
		"categoryId": categoryId,
		"startDate": startDate,
		"endDate": endDate,
	};

	$.ajax({
		type: 'POST',//方法类型
		url: '/admin/insertTc',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(result) {
			if (result.resultCode == 200) {
				if(flag){
					swal("ご挿入出来ました！", {
					icon: "success",
				});
				}else{
					swal("ご削除出来ました！", {
						icon: "success",
					});
				}
				
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
};
}
	
/*//add delete TC record by coca 2021/05/30
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
*/
//2021/06/02 insertSale 绑定modal上的保存按钮

 $("#giveawayBtn").click(function(){
  $(".modal").fadeIn();
 });
 $("#cancell").click(function(){
  $(".modal").fadeOut();
 });


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

  