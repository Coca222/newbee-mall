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
var index = ids.indexOf("キャンペーンID");
  if (index > -1) {
  ids.splice(index, 1);
}
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

//to add filter by coca 2021/05/26
debugger;
(function(document) {
  'use strict';
  var LightTableFilter = (function(Arr) {
    var _input;
    function _onInputEvent(e) {
      _input = e.target;
      var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
      Arr.forEach.call(tables, function(table) {
        Arr.forEach.call(table.tBodies, function(tbody) {
          Arr.forEach.call(tbody.rows, _filter);
        });
      });
    }
 
    function _filter(row) {
      var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
      row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
    }
 
    return {
      init: function() {
        var inputs = document.getElementsByClassName('light-table-filter');
        Arr.forEach.call(inputs, function(input) {
          input.oninput = _onInputEvent;
        });
      }
    };
  })(Array.prototype);
 
  document.addEventListener('readystatechange', function() {
    if (document.readyState === 'complete') {
      LightTableFilter.init();
    }
  });
 
})(document);
  
  //2021/05/24 modal test
$(function(){
 $("#newAdd").click(function(){
  $(".modal").fadeIn();
 });
 $("#cancell").click(function(){
  $(".modal").fadeOut();
 });
});
//2021/05/24 insertSale 绑定modal上的保存按钮
$("#saveSaleButton").click(function(){ 
 //var id = $("#saleId").val();
 var name = $("#campaignSaleName").val();
 var startDate = $("#startDateSale").val();
 var endDate = $("#endDateSale").val();
    data = {
//	"id":id,
   "name":name,
 "startDate":startDate,
 "endDate":endDate,
    };   
    $.ajax({
        type: 'POST',//方法类型
        url: '/admin/goods/insertSale',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
//サーバーが成功した場合
            if (result.resultCode == 200) {
   debugger;     
     swal("ご登録ありがとうございました！" ,{
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
  
  // Listen for click on toggle checkbox added by coca 2021/05/24
$('#checkAll').click(function(event) {   
    if(this.checked) {
        // Iterate each checkbox
        $(':checkbox').each(function() {
            this.checked = true;                        
        });
    } else {
        $(':checkbox').each(function() {
            this.checked = false;                       
        });
    }
});

//achieve sorting goods sale by coca 2021/05/25 https://notepad-blog.com/content/127/
$(function(){
 
  // カラムのクリックイベント
  $("th").click(function(){
    // ★span要素の独自属性（sort）の値を取得
    var sortClass = $(this).find("span").attr("sort");
    var sortFlag = "asc";
    // 初期化
    $("table thead tr span").text("");
    $("table thead tr span").attr("sort", "");

    if(isBlank(sortClass) || sortClass == "asc") {
      $(this).find("span").text("▼");
      // ★独自属性（sort）の値を変更する
      $(this).find("span").attr("sort", "desc");
      sortFlag = "desc";
    } else if(sortClass == "desc") {
      $(this).find("span").text("▲");
      $(this).find("span").attr("sort", "asc"); 
      sortFlag = "asc";
    }

    var element = $(this).attr("id");
    sort(element, sortFlag);
  });


  /******** 共通関数 ********/
  function sort(element, sortFlag) {
    // ★sort()で前後の要素を比較して並び変える。※対象が文字か数値で処理を変更
    var arr = $("table tbody tr").sort(function(a, b) {
      if ($.isNumeric($(a).find("td").eq(element).text())) {
        // ソート対象が数値の場合
        var a_num = Number($(a).find("td").eq(element).text());
        var b_num = Number($(b).find("td").eq(element).text());

        if(isBlank(sortFlag) || sortFlag == "desc") {
          // 降順
          return b_num - a_num;
        } else {
          // 昇順
          return a_num - b_num;
        }
      } else {
        // ソート対象が数値以外の場合
        debugger;
        var sortNum = 1;
        if($(a).find("td").eq(element).text() 
             > $(b).find("td").eq(element).text()) {
          sortNum = 1;
        } else {
          sortNum = -1;
        }
        if(isBlank(sortFlag) || sortFlag == "desc") {
          // 降順
          sortNum *= (-1) ;
        }

        return sortNum;
      }
    });
    // ★html()要素を置き換える
    $("table tbody").html(arr);
  }


  //バリデーションチェック
  function isBlank(data){
    if (data.length ==0 || data == ''){
      return true;
    } else {
      return false;
    }
  }
});