	//add test 图片上传插件初始化 用于商品预览图上传 added by coca 2021/05/13
    new AjaxUpload('#csvUpload', {
        action: '/admin/uploadtest/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif|csv)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif、csv格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                $("#goodsCoverImg").attr("src", r.data);
                $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });

 //add download event added by coca 2021/05/19
$('#download').on('click', function(){
	debugger;
	var _data = [1];	  
    $.ajax({
            type: 'POST',//方法类
            url: "/admin/downloadFile/post",            
            contentType: 'application/json',
            data: JSON.stringify(_data),
            success: function (result) {
				//サーバーが成功した場合
                if (result.resultCode == 200) {
/*	               swal("成功", {
                    icon: "success",
                }); */
                
                  // file path
                  debugger;
              //  window.location.assign(result.data); // the function is same as below
                  Download(result.data); 
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
 });
 
 function Download(url) {
 document.getElementById('my_iframe').src = url;
				};
 
 //ajax 与后台通信 查找查询履历 added by coca 2021/05/20
 var MouseOnSearchResultUl;

$( "#keyword" ).focus(function() {
	debugger;
	var keyword=$("#keyword").val();
  if(keyword != ""){
	$( "#keyword" ).trigger("keyup");
				}
 //url:restful api
      debugger;
      $.ajax({
            type: 'POST',//方法类型
            url: "/searchHistory/getSearchHistory",
            contentType: 'application/json',
         //   data: JSON.stringify(keyword),
            success: function (result) {
			//サーバーが成功した場合
                if (result.resultCode == 200) {
	            	showResult(result);
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
});

// delete elements when focus out
$("#keyword").focusout(function(){
	debugger;
	if(MouseOnSearchResultUl){
		//return means jump out of this function
		return;
	}
	//clear #searchResultUl's elements
	//foreach is javascript's method
	//$("#searchResultUl").children() is jquery
	//toArray() convert $("#searchResultUl").children() to javascript array
	  clearResultList();
	//hide #searchResultUl
	$("#searchResultUl").hide();
});

//ajax　曖昧検索
$( "#keyword" ).keyup(function() {
	debugger;
	var keyword=$("#keyword").val();
	      $.ajax({
            type: 'get',//方法类型    method="post"
            url:"/goods/search?goodsName="+keyword, //post送信先のurl
            //data:keyword, //JSONデータ本体
            //contentType: 'application/json',	//リクエストのcontentType
            dataType:"json",	//レスポンスをJSONとしてパースする
            success: function (json_data) {	//200 ok時
               debugger;
               clearResultList();
               showResultForLikeSearch(json_data);
               debugger;
               var list =json_data.data.list[0];
               var str = list.goodsName;
               var arr = str.split(" ");
             /*  arr.filter(keyword=> keyword.includes(keyword));*/
             for (var i=0;i<arr.length;i++){
     			if(arr[i].includes(keyword)){
    			keyword = arr[i];
     					}
      		}
             
               keywordInsert(keyword);
                 
            },
            error: function () {		//HTTP　エラー時
                debugger;
                alert("Server Error. Please try again later.");
            }
        });	
});

function clearResultList(){
    //clear #searchResultUl's elements
	//foreach is javascript's method
	//$("#searchResultUl").children() is jquery
	//toArray() convert $("#searchResultUl").children() to javascript array
	$("#searchResultUl").children().toArray().forEach(function(value,index,array){
		//check if include class name which is dumyLi
		// value is dom html element
		var incFlag = $(value).attr('class').includes("dumyLi");
		//delete elements besides dumyLi
		if(!incFlag){
			$(value).remove();
		}
	})
}

function showResult(result){
	var list =result.data;
	//href="/goods/detail/10700"
	var _href = "/goods/detail/";
	for(var i=0;i<list.length;i++){
		var el=$(".dumyLi").clone().removeClass("dumyLi");
		var link = el.find("a");
		link.text(list[i].goodsName);
		link.attr("href", _href+ list[i].goodsId)
		$(".dumyLi").before(el);
	}
	$("#searchResultUl").show();
	appendToSearchBar($("#searchResultUl"));
}

function showResultForLikeSearch(result){
	debugger;
	var list =result.data.list;
	//href="/goods/detail/10700"
	var _href = "/goods/detail/";
	for(var i=0;i<list.length;i++){
		var el=$(".dumyLi").clone().removeClass("dumyLi");
		var link = el.find("a");
		link.text(list[i].goodsName);
		link.attr("href", _href+ list[i].goodsId)
		$(".dumyLi").before(el);
	}
	$("#searchResultUl").show();
	appendToSearchBar($("#searchResultUl"));
}

function appendToSearchBar(el){
	debugger;
	var searchBar=$("#keyword");//jquery boject
	// var searchBar=document.getElementById("keyword"); //dom
	var rect=searchBar[0].getBoundingClientRect();//convert jquery object to dom by searchBar[0]
	console.log(rect.top, rect.right, rect.bottom, rect.left);
	var sbHeight = searchBar.height();
	//el.height(rect.top + sbHeight)
	//el.left(rect.left); rect.bottom=rect.top + sbHeight rect.right=rect.left+sbWidth
	el.css({top:rect.top + sbHeight, left: rect.left, position:'absolute' });
}

//check if mouse is over search result
/*function checkIfMousOver(){
	var rect = document.getElementById("searchResultUl").getBoundingClientRect();
}*/

$("#searchResultUl").mousemove(function(){
	MouseOnSearchResultUl = true;
});

$("#searchResultUl").mouseleave(function(){
	MouseOnSearchResultUl = false;
});

function keywordInsert(keyword){
	debugger;
	/*var keyword=$("#keyword").val();*/
	var data ={
		"keyword": keyword,
	          };  
        $.ajax({
            type: 'POST',//方法类型
            url: "/goods/keywordInsert",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
	//サーバーが成功した場合
                if (result.resultCode == 200) {
	            /*swal("質問ご登録ありがとうございました", {
                        icon: "success",
                    });    */   

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