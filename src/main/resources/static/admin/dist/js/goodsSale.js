	 var MouseOnSearchResultUl;
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
             /*   $("#goodsCoverImg").attr("src", r.data);
                $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;*/
                swal("成功", {
                    icon: "success",
                }); 
            } else {
                alert("error");
            }
        }
    });

 //add download event added by coca 2021/05/19
$('#download').on('click', function(){
	debugger;
	var ids= [ ];
	$('input:checkbox:checked').parent().next().map(function(){
		ids.push($(this).text())
		return ids;
	})
	if (ids==null){
		swal("请选择一条记录", {
                    icon: "success",
                }); 
                return
	}	  
    $.ajax({
            type: 'POST',//方法类
            url: "/admin/downloadFile/post",            
            contentType: 'application/json',
            data: JSON.stringify(ids),
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
 $( "#searchKeyword" ).focus(function(){
	var keyword = $( "#searchKeyword" ).val();
	if(keyword != ""){
		$( "#searchKeyword" ).trigger("keyup");
	}
});		
// delete elements when focus out
$("#searchKeyword").focusout(function(){
	if(MouseOnSearchResultUl)
	return;
    clearResultList()
	//hide #searchResultUl
	$("#searchResultUl").hide();
});
  //ajax　曖昧検索
$("#searchKeyword").keyup(function(){
	debugger;
	var keyword = $("#searchKeyword").val();
	    $.ajax({
            type: 'get',//方法类型  //method = "POST"
            url: "/searchGsHistory/getSearchGsHistory?name="+keyword,  //Post送信先のurl
            dataType:"json",
            success: function (json_data) {
			debugger;
			clearResultList();
			showResultForLikeSearch(json_data);
			debugger;
	   	    var list = json_data.data.list[0];
		    var str = list.name;
		/*    var arr = str.split(" ");
		    // arr.filter(keyword => keyword.includes(keyword),length > 2);  
		    for (var i=0;i<arr.length;i++){
			  if(arr[i].includes(keyword)){
				keyword = arr[i];
			  }
		    }  */
		},
		error: function() {
			debugger;
			alert("Service Error. Pleasy try again later.");
		}
	});
		
});
function clearResultList(){
	$("#searchResultUl").children().toArray().forEach(function(value,index,array){
		var incFlag = $(value).attr('class').includes("dumyLi");
		if(!incFlag){
			$(value).remove();
		}
	})
}
function showResultForLikeSearch(result){
	var list = result.data.list;
	for(var i = 0; i< list.length; i++){
		var el = $(".dumyLi").clone().removeClass("dumyLi");
		var link = el.find("a");
		link.text(list[i].name);
		$(".dumyLi").before(el);
	}
	$("#searchResultUl").show();
	appendToSearchBar($("#searchResultUl"));
}
function appendToSearchBar(el){
	debugger;
	var searchBar = $("#searchKeyword");//jquery object
	//var searchBar = document.getElementById("keyword");//dom
	var rect = searchBar[0].getBoundingClientRect();//转换成dom加[0]  convert jquery object to dom by searchBar[0]
	console.log(rect.top,rect.right,rect.bottom,rect.left);
	var sbHeight = searchBar.height();
	el.css({top: rect.top + sbHeight,left: rect.left,position:'absolute'});//相对定位relative  绝对定位absolute
	}
$("#searchResultUl").mousemove(function(){
	MouseOnSearchResultUl = true;
});
$("#searchResultUl").mouseleave(function(){
	MouseOnSearchResultUl = false;
})
