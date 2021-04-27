$("#zv-cqa-select-sort").change( function(){
        var page = $("#currentPageNum").text();
       
        var url = "/goods/qaSort";
        var data = {
			"page":page
				   };
	debugger;
	$.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.resultCode == 200) {
                   
                    /*swal("保存成功", {
                        icon: "success",
                    });*/
                    // get hidden element
                    var ar = result.data.list;
                    if(ar.length>0){
					$("#ZVCQuestionsArea").find(".zv-cqa").remove();
				}
                    for(let i=0; i<ar.length;i++){
					var qa =$(".hiddenQaDiv").clone().removeClass("hiddenQaDiv");
					qa.find(".zv-cqa-q-text").html(ar[i].question);
					$("#detailFooter").before(qa);
					//qa.appendTo("#ZVCQuestionsArea");
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
        
		        
})