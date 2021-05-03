	$(function(){
	debugger;
	$(".previousPage").css("pointer-events","none").css("color","grey");
	  });
	  
 	$("#zv-cqa-select-sort").change(function(){
	paging(2);
      });
      
	$(".nextPage").click(function(){
	paging(0);
	$(".previousPage").css("pointer-events","auto").css("color","#009e96");	
	  });   
	  
	$(".previousPage").click(function(){
	paging(1);	
	  });  
	// レビューをもっと見るクリックイベント　added by coca 2021/05/03
	$("#showMoreReviewBtn").click(function(){
	var goodsId = getGoodsId();
	var data ={
		"goodsId": goodsId
	          };
		debugger;  
        $.ajax({
            type: 'POST',//方法类型
            url: "/goods/showMoreReviews",
            contentType: 'application/json',
            data: JSON.stringify(goodsId),
            success: function (result) {
	//サーバーが成功した場合
                if (result.resultCode == 200) {
	                var list = result.data;
	                if(list=== undefined){
					swal(error, {
                        icon: "error",
                    });
	            }      
				if(list !=undefined && list.length!=0 ){
					for(i=0;i<list.length;i++){
						var el=$(".hiddenList").clone().removeClass("hiddenList");
						$(".hiddenList").before(el);
					}
				}
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        })	
	}); 
	 
	function getGoodsId(){
	// get url
	var path = window.location.pathname;
	//split with /
	var ar = path.split("/");
	//get array length
	var len = ar.length;
	//get goodsId
	var goodsId = ar[len-1];
	
	return goodsId;
	}  
	$("#ZVPostQuestionButton").click(function(){
	var question = $("#ZVQuestionTextarea").val();
	var goodsId = getGoodsId();
	var data ={
		"question": question,
		"goodsId": goodsId
	          };
		debugger;  
        $.ajax({
            type: 'POST',//方法类型
            url: "/goods/qaInsert",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
	//サーバーが成功した場合
	        var totalPage = $("#totalPageNo").text(totalPage);
                if (result.resultCode == 200) {
	            swalswal("質問ご登録ありがとうございました", {
                        icon: "success",
                    });       

                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        })	
	});
     debugger;
function paging(num){
	var page = $("#currentPageNo").text();
	
	//console.log("selected value ",$('#zv-cqa-select-sort :selected').text());
	var pageNo=0;
	console.log("current page: ",page);
	var url = "/goods/qaSort";
	if (num == 0){
		pageNo = parseInt(page)+1;
	}else if(num == 1){
		pageNo = parseInt(page)-1;
	}else{pageNo = 1}
	var data = {
		"page": pageNo
	           };
	console.log("data",data);  
	debugger;  
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
	//サーバーが成功した場合
	//        var totalPage = $("#totalPageNo").text(totalPage);
                if (result.resultCode == 200) {
	                    var el;
	                    if(result.data.list.length > 0){
						$("#ZVCQuestionsArea").find(".delete").remove();
					  	}
				    
                    var ar = result.data.list;
                    for(let i = 0; i <ar.length;i++){
	
					el = $($(".hiddenQaDiv")[0]).clone().removeClass("hiddenQaDiv");
					el.find(".zv-cqa-question").html(result.data.list[i].question);
					el.find(".zv-cqa-q-info").html(result.data.list[i].submitDate);
					el.find(".zv-cqa-a-text").html(result.data.list[i].answer);
     				el.find(".zv-cqa-a-info").html(result.data.list[i].answerDate);
					el.find(".zv-helpful zv-helpful-yes zv-helpful-yes-58746").html(result.data.list[i].helpedNum);
					$("#detailFooter").before(el);
					/*qa.appendTo("#ZVCQuestionsArea");*/
					}
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        })
    } 
    
         
     
    