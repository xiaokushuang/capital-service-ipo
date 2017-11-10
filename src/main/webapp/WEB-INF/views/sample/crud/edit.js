//@ sourceURL=edit.js
var rowSize;
$(document).ready(function() {
    //设定表单校验规则
    var validator = $("#testForm").validate({
        rules : {
            "name" : {
                required : true,
                // 后台校验
                remote: {
                    url: contextPath + "/sample/remoteValidate",
                    type: "post",
                    data: {
                        name: function() {
                            return $("#name").val();
                        }
                    }
                },
                maxlength: 10
            },
            "type" : {
                required : true,
            },
            "numInt" : {
                required : true,
                digits : true
            },
            "numDouble" : {
                required : true,
                number: true
            },
            "testDate" : {
                required : true,
                dateISO : true
            },
            "testTime" : {
                required : true,
                date : true
            },
            "checkbox" : {
                required : true
            },
            "radiobutton" : {
                required : true
            }
        },
        messages : {
            "name" : {
                remote: "不能等于12345"
            }
        },
        tooltip_options: {
            "_all_": {trigger:'focus'}
        }
    });
    // 为初始化的列表里的行动态添加校验规则
    $("#items tr").each(function(index){
        if (index == 0) {
            return true;
        }
        addRowValidateRules($(this), index);
    })
    //增加一行，并为新增的行动态添加校验规则
    $("#btnAdd").click(function() {
        //往table增加一行
        addRow();
    });
    //保存
    $("#btnSave").click(function() {
        if ($("#testForm").valid()) {
        	submitForm("/sample/save");
        }
    });
    //返回
    $("#btnBack").bind("click", function() {
        movePage("/sample/query?toPageState=back");
    });
    
    rowSize = $("#items tr").length - 1;
    
    // 文件上传
    $("#file").fileupload({
        url: contextPath + "/filetempupload",
        dataType: "text",
        autoUpload: true,
        sequentialUploads: true,
        add: function(e, data) {
            if (data.autoUpload || (data.autoUpload !== false &&
                $(this).fileupload('option', 'autoUpload'))) {
                data.process().done(function () {
                    uploadRequestArray.push(data.submit());
                });
            }
        },
        start: function (e, data) {
            $("#uploadProcess").show();
        },
        send: function(e, data) {
            $("#uploadfileName").text(data.files[0].name);
        },
        done: function (e, data) {
            var fileuploadResult = $.parseJSON(data.result);
            if (fileuploadResult.errorMsg == null || fileuploadResult.errorMsg == undefined) {
                $.each(fileuploadResult, function (index, file) {
                    $('<p>' + file.fileName + '<input type="hidden" name="files" value="' + file.fileId + '"><a href="javascript:void(0)"><i class="fa fa-trash" onclick="removeFileRow(this)"></i></a></p>').appendTo("#fileList");
                });
            }
        },
        fail: function(e, data) {
            console.log(data);
            if (data.textStatus == 'abort') {
                popAlert("上传已取消");
            } else {
                popAlert("上传失败");
            }
        },
        progressall: function(e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10) + '%';
            $("#progressId").css("width", progress);
            $("#progressTextId").html(progress);
        },
        stop: function(e, data) {
            $("#uploadProcess").hide();
            $("#progressId").css("width", "0");
        }

    });
});

var uploadRequestArray = new Array();

function cancelFileUpload() {
    for (var i = 0; i < uploadRequestArray.length; i++) {
        uploadRequestArray[i].abort();
    }
    uploadRequestArray = new Array();
}
function removeFileRow(obj) {
    $(obj).parents("p").remove();
}

//往table增加一行
function addRow() {
    //取得行的模板
    var template = $.trim($("#template").html());
    var $newRow = $(template.replace( new RegExp( "\\[0\\]", "g" ), "["+rowSize+"]").replace( new RegExp( "item0", "g" ), "item"+rowSize));
    $newRow.data("index", rowSize);
    //往table里动态添加一行
    $newRow.appendTo("#items");
    //动态为新增的行添加校验规则
    addRowValidateRules($newRow, rowSize);
    rowSize = refreshTableIndex("items");
}
//删除table的一行
function removeRow(obj) {
    $(obj).parent().parent().remove();
    rowSize = refreshTableIndex("items");
}
// 为每行添加校验规则
function addRowValidateRules($trObj, index) {
    $("input[name*='itemName']", $trObj).rules("add", {
        required : true
    });
    $("select[name*='itemType']", $trObj).rules("add", {
        required : true
    });
    $("input[name*='itemDate']", $trObj).rules("add", {
        required : true,
        dateISO : true
    });
    $("input[name*='itemTime']", $trObj).rules("add", {
        required : true,
        date: true
    });
//	$("input[name*='itemCheckbox']", $trObj).rules("add", {
//	    required : true
//	});
//	$("input[name*='itemRadiobutton']", $trObj).rules("add", {
//	    required : true
//	});
    $("input[name*='itemNumInt']", $trObj).rules("add", {
        required : true,
        digits: true
    });
    $("input[name*='itemNumDouble']", $trObj).rules("add", {
        required : true,
        number : true
    });
}