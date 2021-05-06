	$(function(){
	debugger;
	$(".previousPage").css("pointer-events","none").css("color","grey");
	//閉じるボタン非表示させる
	$("#closeBtn").hide();
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
	  
	  
	$("#closeBtn").click(function(){
	$(".g-reviewList").hide();
	$("#closeBtn").hide();
	$("#showMoreReviewBtn").show();
		
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
	                $(".g-reviewList").show();
	                if(list === undefined){
					swal(error, {
                        icon: "error",
                    });
	            }      
				if(list !=undefined && list.length!=0 ){
					for(i=0;i<list.length;i++){
						var el=$(".hiddenList").clone().removeClass("hiddenList");
	//					el.find(".g-clip").html(list[i].id);
						el.find(".hidSpForRevId").html(list[i].id);
						el.find(".helpNumSpan").on("click",helpNumClickFunc);
						debugger;
						$(".hiddenList").before(el);
					}
				}
						//レビューをもっと見るの非表示
						$("#showMoreReviewBtn").hide();
						//閉じるボタンを表示させる
						$("#closeBtn").show();
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
	
	function helpNumClickFunc(){
	var reviewId = $(this).parent().find(".hidSpForRevId").text();
	var data = {
		
		"reviewId":reviewId
	}
	var url="/goods/helpNum"
	debugger; 
	var _this= $(this);
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
	//サーバーが成功した場合
	//        var totalPage = $("#totalPageNo").text(totalPage);
                if (result.resultCode == 200) {
	               debugger;
	           /*    swal("成功", {
                    icon: "success",
                }); */  
                _this.text("参考になった（"+ result.data +"人）"); 
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
        });	
	}
						
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
	
	// change image add by coca 2021/05/05
	function clickImage(src){
	$(".swiper-container").find("img").attr('src', src);	
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
	            swal("質問ご登録ありがとうございました", {
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
        });
    } 
    
         
     
    