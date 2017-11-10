/**
 * 创建日期：17年3月25日
 * 创建人：yanchangkun
 * 功能描述：公用类功能函数
 */

/**
 * 功能：下拉框
 * 描述：带有3级下拉和checkbox
 * 示例：violationFavour.js和violationFavour.jsp
 */

//带有checkbox的ztree初始化参数，nameView为显示数据名称
function setting(nameView){
	var ztreeSelect = {
	   	async: {
			enable: true,
			contentType: "application/json",
			url: "",
			autoParam: ["id", "name"]
		},
		check: {
			enable: true
		},
		data:{
			simpleData : {
	            enable : true,
	            idKey : "id",
	            pIdKey : "parentId",
	            rootPId : null
	        },
	        key : {
	            name : nameView
	        }
		},
		view:{
			showIcon:false,
			showLine:false,
			txtSelectedEnable:true
		},
		callback : {
	        onClick : checkedCallback//点击节点checkbox选中
	        //onCheck : onCheck
	    }
	};
	return ztreeSelect;
}

//弹框里的确定,按钮事件，参数传this和tree的ID,参数(this,'id')
function submits(obj,treeBox){
	//找到弹框盒子及input
	var saveIdInput = $(obj).parents('.selectBox').find('.originCheck');
	var _this = $(obj).parents('.selectBox');
	var Input = _this.prev();
	
	//拿到选中的json
	var zTree = $.fn.zTree.getZTreeObj(treeBox);
	var nodes = zTree.getCheckedNodes(true);
	
	//拿到子节点选中的
	var newNodes = [];
	nodes.map(function(idx,obj){
		if(idx.check_Child_State==-1){
			newNodes.push(idx)
		}
	})
	
	//拿到name和id分别拼成串
    var msg = "",names = "";
    for (var i = 0; i < newNodes.length; i++) {
    	msg +=newNodes[i].value+',';
    	names += newNodes[i].label+',';
    }
    id = msg.substring(0,msg.length-1);
    names = names.substring(0,names.length-1);
    
    //处理input里的文字显示
    if(Input.width()<names.length*14){
    	Input.val(Input.attr('placeholder')+'('+newNodes.length+')')
    }else{
    	Input.val(names)
    }
    
    //点击确定，弹框隐藏
    _this.slideUp('fast');
    _this.prev().removeClass('active');
    
    //给隐藏input赋值
    saveIdInput.val(id)
    
    //搜索
    search(true);
}

//弹框里的清空，参数为tree的ID（'id'）
function clears(treeBox){
	var jsonBox = $("#"+treeBox).parent().find('.jsonBox')
	var zNodes = $.parseJSON(jsonBox.text());
	zNodes.map(function(o,i){
   		var zTree = $.fn.zTree.getZTreeObj(treeBox);
    	var node = zTree.getNodeByParam("id",o.id);
    	zTree.checkNode(node, false, false);
    	zTree.updateNode(node);
    })
    var UL = document.getElementById(treeBox);
    var Input = $(UL).parent().prev();
    Input.val('');
}

//input获取焦点事件，绑在要下拉的input上，参数传this
function slideDowns(obj){
	var $this = $(obj);
	$this.addClass('active');
	var _this = $this.parent().find('.selectBox')
	_this.css('width',_this.parent().width());
	_this.css('left',_this.parent().css('padding-left'))
	_this.slideDown('fast');
}

//点击复位
//初始化后的回调函数，不用管
//data：yi隐藏input变数组；e：鼠标点击event；treeBox：树盒子；
function resetCheck(data,e,treesBox){
	var t = $(e.target);
	var input = $('#'+treesBox).parent().prev();
	if (t.parents('.selectBox').length <= 0&&t.attr('id') !== input.attr('id')) {
		if(data==''||null){
			clears(treesBox)
		}else{
			clears(treesBox)
			data.map(function(o,i){//初始化时设置节点选中状态
		   		var zTree = $.fn.zTree.getZTreeObj(treesBox);	
		    	var node = zTree.getNodeByParam("value",o);
		    	zTree.checkNode(node, true, true);
		    	zTree.updateNode(node);
		   	})
			//console.log(data)
			//拿到选中的json
		   	var zTree = $.fn.zTree.getZTreeObj(treesBox);
			var nodes = zTree.getCheckedNodes(true);
			
			//拿到子节点选中的
			var newNodes = [];
			nodes.map(function(idx,obj){
				if(idx.check_Child_State==-1){
					newNodes.push(idx)
				}
			})
			
			//拿到name和id分别拼成串
		    var msg = "",names = "";
		    for (var i = 0; i < newNodes.length; i++) {
		    	msg +=newNodes[i].id+',';
		    	names += newNodes[i].label+',';
		    }
		    id = msg.substring(0,msg.length-1);
		    names = names.substring(0,names.length-1);
		    
		    //处理input里的文字显示
		    if(input.width()<names.length*14){
		    	input.val(input.attr('placeholder')+'('+newNodes.length+')')
		    }else{
		    	input.val(names)
		    }
		   	
	   	}
		input.removeClass('active');
		input.next().slideUp('fast');
	}
}

//点击节点回调函数，点击节点当前节点checkbox被选中功能
function checkedCallback(e, treeId, treeNode, clickFlag){
	var id = $(e.delegateTarget).attr('id');
	var zTree = $.fn.zTree.getZTreeObj(id);
	zTree.checkNode(treeNode, !treeNode.checked, true);
}

//下拉框初始化，参数：tree的ID和数据显示名字('treeIllegal','label')
function selectCheckInit(treeBox,view){
	var input =$("#"+treeBox).parent('.selectBox').prev();
	var hiddenInput = $("#"+treeBox).parent('.selectBox').find('.originCheck');
	var jsonBox = $("#"+treeBox).parent('.selectBox').find('.jsonBox');
	var zNodes = $.parseJSON(jsonBox.text());
  	var zTreeObj = $.fn.zTree.init($("#"+treeBox), setting(view), zNodes);
	$(document).click(function(e) {
		input.off('click');
		var data = hiddenInput.val().split(',');
		resetCheck(data,e,treeBox)

	});
}


//分享懂你社区

//$(function() {
//    //图片导入方法
//    inputimage();
//
//    //一些样式
//    $('.img_share').css({
//        display : 'none',
//        position : 'absolute',
//        cursor : 'pointer'
//    });
//
//    //对文件进行选中
//    var funGetSelectTxt = function() {
//        var txt = '';
//        if(document.selection) {
//            txt = document.selection.createRange().text;
//        } else {
//            txt = document.getSelection();
//        }
//        return txt.toString();
//    };
//
//    //显示图片
//    $('html,body').mouseup(function(e) {
//        if (e.target.id == 'imgSinaShare' || e.target.id == 'imgQqShare') {
//            return
//        }
//        e = e || window.event;
//        var txt = funGetSelectTxt(),
//            sh = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0,
//            left = (e.clientX - 40 < 0) ? e.clientX + 20 : e.clientX - 40,
//            top = (e.clientY - 40 < 0) ? e.clientY + sh + 20 : e.clientY + sh - 40;
//        if (txt) {
//            $('#imgSinaShare').css({
//                display : 'inline',
//                left : left,
//                top : top+50
//            });
//            $('#imgQqShare').css({
//                display : 'inline',
//                left : left + 30,
//                top : top+50
//            });
//        } else {
//            $('#imgSinaShare').css('display', 'none');
//            $('#imgQqShare').css('display', 'none');
//        }
//    });
//
//    //设置cookie值及过期时间；
////  function setCookie(name,value,time){
////      var oDate=new Date();
////      oDate.setDate(oDate.getDate()+time);
////      document.cookie=name+'='+value+';expires='+oDate;
////  }
//
//var newPageWith = ''
//    //分享到董你社区
//    $(document).on('click','#imgSinaShare',function() {
//        var txt = funGetSelectTxt(), title = $('title').html();
//        if (txt) {
//        	newPageWith = window.open();
//        	ajaxData("/home/checkTel", null , function(data){
//        		if (txt) {
//                	if(getValue(data) != ''){
//                		newPageWith.location = "http://dev-wecenter.valueonline.cn/?/publish/ansync_publish/?title="+txt+"&"+data;
//                		//window.open("http://dev-wecenter.valueonline.cn/?/account/ydlogin/?" + data);
//                    }else{
//                    	newPageWith.close();
//                    	//进入登录页面
//                		popWin("登录董你社区","/home/loginFormEasyboard", "",
//                        "400px", "350px", null, null);
//                    }
//                }
//        		
//        	});
//        }
//    });
//})
//function inputimage(){
//   $('<a href="javascript:void(0)" id="imgSinaShare" class="img_share" title="选中内容分享到董你社区"></a>').appendTo('body');
//}