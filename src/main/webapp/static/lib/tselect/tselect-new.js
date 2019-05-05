/**
 * start 2018/7/10
 * end 2018/7/17
 * function 下拉多选框
 * 特殊标记：
 * 		setRecord方法和getRecord方法为插件内部全局数据设置及获取方法
 * 		具体数据如下: 挂在在当前input的data下（this.$.data）
 * 			selectedIds  选中id   				Array
 * 			text		 选中数据的名称			String
 * 			default      原始一维数据  	    	JSON
 * 			openDwon	 是否展开下拉框			Boolean
 * 			treeData	 处理后的数据				JSON
 * 			mouseFlag	 点击区分iframe位置   	0/1
 * 			innerTimer   定时器，控制mouseFlag   （页面点击记录target，设置mouseFlag=1，延迟设置mouseFlag=0）
 * 			target		 点击页面记录e.target 	Object
 * 			outTimer	 获取焦点立即执行定时器，在innerTimer开始之前执行 控制下拉框显示隐藏
 * 			triggerOpen  确定当前input的触发打开是自然触发还是trigger触发		Boolean
 * 			selectWidth  下拉框宽度				String
 * 			openFlow	 标记下拉向上/下弹		String  'up'/'down'
 * 			selectDom	 diff模板返回的实例		constructor
 *			clear		 清空开关				Boolean
 *			blurRuleInTo 关闭下拉更新dom到原始状态开关，设置此开关是为了不调用callback    Boolean
 *			input标签上的属性		idxx：参数value的值，pidxx：参数pid的值，selfxx：参数id的值
 *			initFlag 	 初始化开关				Boolean
 *			ztreeFather  是否左浮动				ztreeFather/noStyle
 * */
(function() {
	'use strict';
	$.tSelectSubmitClick = function(){
		console.warn('This method is useless,by yck')
	}
	$.fn.extend({
		/**
		 * 实例化初始类并传参
		 * @param {reloadData} 二次调用tselectInit方法才有效，配置选项后重置作用
		 * @param {option} 参数列表
		 * */
		tselectInit: function(reloadData,option) {//初始化方法
	        return this.each(function() {
	            var $this = $(this);
	            var data = $this.data('zui.SelectMod');
	            var options = typeof option == 'object' && option;
	            if (!data){
	            	$this.data('zui.SelectMod', (data = new SelectMod(this,reloadData, options)));
	            } else{
	            	if(reloadData!=null){
		            	data.init(reloadData);
	            	}
	            }
	            if (typeof option == 'string') data[option]();
	        });
	    },
		tselectClear : function(fun) {//清空方法
			return this.each(function() {
				var $this = $(this);
				if(!!$this.data('zui.SelectMod')){
					$this.data('zui.SelectMod').clear(fun)
				}
			});
		},
		tselectReload : function(data){//重载
			return this.each(function(){
				var $this = $(this);
				if(!!$this.data('zui.SelectMod')){
					$this.data('zui.SelectMod').reload(data)
				}
			})
		},
		tselectReloadZH : function(data){//重载汉字
			return this.each(function(){
				if(!!$(this).data('zui.SelectMod')){
					$(this).data('zui.SelectMod').reloadZH(data)
				}
			})
		}
	});
	/**
	 * 初始类
	 * */
    var SelectMod = function(element,reloadData, options) {
    	this.$ = $(element);//元素jquery代理对象
    	this.store = null;//dom树结构数据
        this.options = this.getOptions(options);//配置参数
        this.selectDom = null;//dom初始类实例
        this.reloadData = reloadData;//初始化数据
        this.init();//初始化
        
    };
    /**
	 * 下拉菜单默认参数
	 */
    SelectMod.defaults = {
		inputType : 'checkbox',
		dataType : 'common',//数据是否是已处理过的 @param {common | tree}
		customCallBack : null, 
		submitCallBack : null, //点击确定回调
		id : 'id',
		pid : 'pid',
		name : 'name',
		value : 'id',
		grade : 1,
		resultType : 'grade',
		selectedIds : null,
		style : {
			isInline : false,
			tWidth : null,
			tMaxHeight : 300,
			tTop : null,
			tLeft : null,
			tButtonMargin : 20
		},
		openDown:true,//是否初始展开tree
		inputSearch:true,//是否需要搜索功能
		allCheck:false,//是否显示全选按钮
		isTop:false,//是否显示在顶层页面上	
		allShow:false,//是否提供全部展开和全部隐藏按钮
		clickDiy:false,//自定义checkbox点击规则
		showParentRadio: false,
	};
    /**
     * 替换默认参数
     * */
    SelectMod.prototype.getOptions = function(options) {
        options = $.extend({}, SelectMod.defaults, options);
		options.style = $.extend({}, SelectMod.defaults.style, options.style);
        return options;
    };
    /**
     * 初始化方法
     * */
    SelectMod.prototype.init = function(reloadData) {
    	if(reloadData){
    		this.setRecord('treeData',reloadData);
    		this.store.get('tree').treeData=reloadData;
    		this.setRecord('initFlag',true)
    		//this.store.set('treeData',reloadData)
    	}else{
    		this.setRecord('initFlag',false)
        	this.load(true);
    	}
    };
    
    /**
     * 数据处理
     * @param {flag} 区分是初始化还是单纯的储存数据
     * */
    SelectMod.prototype.load = function(flag,reloadData) {
    	//初始化后就清空默认选中参数，以免重载时覆盖修改过得值
    	var $this = this;
    	this.$.attr('readonly','readonly')
    	this.setRecord('ztreeFather','ztreeFather');
    	this.setRecord('tMaxHeight',this.options.style.tMaxHeight)//保存最大高度
    	$this.setRecord('updown',true)//设置全部展开和全部收起开关
    	if(this.options.selectedIds!=null&&this.options.selectedIds.length>0){
//    		console.log(this.options.selectedIds.split(','))
    		this.setRecord('selectedIds',this.options.selectedIds.split(','))//设置默认选中数据
    		this.options.selectedIds = null
    	}else{
    		this.setRecord('selectedIds',[])
    	}
    	if((this.$.attr('json-data')!=undefined||this.$.attr('json-data')!=null)&&this.reloadData==null){
//    		console.log(this.$.attr('json-data'))
    		if(this.$.attr('json-data').length>0){
            	var data = JSON.parse(this.$.attr('json-data'));
    		}else{
    			console.warn('No data has been obtained,by yck')
        		return
    		}
    	}else{
    		var data = this.reloadData;
    	}
    	if(this.getRecord('default')==undefined){
    		if(this.options.dataType=='common'){
            	this.setRecord('default',data);//设置原始数据
    		}else{
    			this.setRecord('ztreeFather','noStyle');
            	this.setRecord('default',this.goToDefault(data));//设置原始数据
    		}
        	this.setRecord('openDwon',this.options.openDown)
    	}
    	data = this.selectData(data);//拿到tree数据
//    	console.log(data)
    	this.setRecord('treeData',data);//设置处理过得数据
    	this.setRecord('mouseFlag',0);//鼠标点击区分iframe位置
    	this.setRecord('innerTimer',0);//进入定时器
    	this.setRecord('outTimer',1);//进入定时器
		this.setRecord('clickDiy',this.options.clickDiy)//自定义点击
		this.setRecord('allCheck',this.options.allCheck)//是否带有全选功能
		this.setRecord('showParentRadio',this.options.showParentRadio)//是否显示父级radio
    	//$this.setRecord('triggerOpen',true);//确定当前input的触发单位是当前点击
    	this.setRecord('clear',false)//清空开关
    	$this.setRecord('blurRuleInTo',true)//关闭下拉框时更新dom，但不调用回调函数作用开关
    	$(document).on('mousedown ',function(e){
    		clearTimeout($this.getRecord('innerTimer'))
    		$this.setRecord('mouseFlag',1);
    		$this.setRecord('target',e.target)
//    		console.log($this.getRecord('mouseFlag')+'       11111111111111111111111111111111111111')
    		$this.setRecord('innerTimer',setTimeout(function(){
        			$this.setRecord('mouseFlag',0)
//        			console.log($this.getRecord('mouseFlag')+'      222222222222222222222222222')
        		},320)
    		)
    	})
    	var width = this.$.width()+parseInt(this.$.css('padding-right'))+parseInt(this.$.css('padding-left'))+2
    	$this.setRecord('selectWidth',width)
    	//绑定点击下拉事件
    	$this.$.focus(function(e){
    		//最大高度
    		var tMaxHeight = $this.options.style.tMaxHeight;
    		//获取父级真正宽度
    		var width = $(this).width()+parseInt($(this).css('padding-right'))+parseInt($(this).css('padding-left'))+2
    		$this.setRecord('selectWidth',width)
    		$(this).parent().find('.t-select-table').css('width',width)
    		
    		//确定弹出框向上弹还是向下弹
    		var offsetT = $(e.target).offset().top,//下拉的input距离当前window顶部距离
    			bodyH = document.body.offsetHeight;//当前window的body内容高度
    			$('.t-select-table',$this.$.parent()).addClass('getHeight')
				var tableH = $('.t-select-table',$this.$.parent()).height();//当前input下下拉框的高度
//    			console.log(tableH)
    			$('.t-select-table',$this.$.parent()).removeClass('getHeight')
		  //如果    下面放不下                        并且   （上面放得下） 或者 （上面下面都放不下，但是上面比下面大）       
			if((tableH>bodyH-offsetT-30&&(offsetT>tableH))||(tableH>offsetT>bodyH-offsetT-30)){
				$this.setRecord('openFlow','up')
//				if(offsetT<tableH){//多出的高度在默认参数的最大高度上扣除
//					$this.options.style.tMaxHeight=tMaxHeight+(offsetT-tableH)
//				}
				var search = $this.options.inputSearch?40:0;//有没有搜索
				var H = tableH<$this.options.style.tMaxHeight+search+51?$('.t-select-table',$this.$.parent()).height():$this.options.style.tMaxHeight+search+51
				$(this).parent().find('.t-select-table').css('top','-'+H+'px')
			}else{//下面放不下并且上面也放不下并且下面比上面大或者下面放得下
				$this.setRecord('openFlow','down')
//				if(bodyH-offsetT-30<tableH){//多出的在参数的最大高度上扣除，如果下面放不下，从新设置最大高度
//					$this.options.style.tMaxHeight=tMaxHeight+(bodyH-offsetT-30-tableH)
//				}else{
//					$this.options.style.tMaxHeight = $this.getRecord('tMaxHeight')
//				}
				$(this).parent().find('.t-select-table').css('top',30+'px')
			}
			$('.t-select-cell',$this.$.parent()).css('max-height',$this.options.style.tMaxHeight+'px')
//			if($this.getRecord('triggerOpen')){
//				$this.selectAskId(false)//下拉的时候设置默认选中
//			}else{
//    			$this.setRecord('triggerOpen',true);
//			}
			$this.setRecord('blurRuleInTo',true)
    		$('.t-select-table',$this.$.parent()).slideDown('fast');
    	})
//    	console.log(this.getRecord('selectedIds'))
    	flag&&this.loadDom()
    	
    };
    
    /**
     * dom节点渲染方法
     * */
    SelectMod.prototype.loadDom = function() {
    	//添加图标
    	var _this = this;
    	this.$.parent().prepend('<i class="fa fa-caret-down t-select-icon" style="font-size:18px;position:absolute;right:15px;top:7px;cursor:pointer;"></i>')
    	this.$.prev().on('click',function(){
    		_this.$.trigger('focus')
    	})
    	//绑定点击document下拉框显示规则
    	this.showRule();
    	//取到tree得数据和选中得数据
    	var $this = this;
    	var data = this.getRecord('treeData');
    	var timer;
    	//实例化diff模板
    	var aoy = Aoy.init();
    	this.store = aoy.store;
    	var el = aoy.el;
    	//添加数据
    	this.store.add('tree',{
    		treeData:data,
    		openIconDown:'fa fa-caret-down',
    		openIconRight:'fa fa-caret-right',
    		searchStr:'',
    		selectWidth:$this.getRecord('selectWidth'),
    		selectDisplay:'none',
    		openDowns:$this.getRecord('openDwon'),
    		selectId:[]
    	})
    	/**
    	 * 组装 Virtual Dom
    	 * @param {obj} 数据
    	 * @param {$this} 插件本身指向的类
    	 * @param {doc} selectDom
    	 * @param {flg} 是否第二次进入函数
    	 * */
		function doms(obj,$this,doc,flg){
    		var openDowns = $this.store.get('tree').openDowns;
    		var type = $this.options.inputType
    		//flag为true代表就一层，否则就是有子集的
			var own,content,inputs,returnData;
			inputs = [
			         el('input',{type:type,class:'id'+obj[$this.options.value]+' pid'+obj[$this.options.pid]+' self'+obj[$this.options.id],key:obj[$this.options.id],onblur:doc.blur}),
			         el('span',{class:'tabindex',onblur:doc.blur},obj[$this.options.name])
			         ];
			if(type=='radio'){
				if(obj.children&&obj.children.length>0){
					inputs = [el('span',{class:'tabindex',onblur:doc.blur},obj[$this.options.name])];
				}
			}
			content = el('label',{class:'tabindex',onmouseup:$this.getRecord('clickDiy')?doc.clickdiy:doc.memory,onblur:doc.blur},inputs)
//			console.log(obj[$this.options.id])
			own = el('div',{class:'checkbox tabindex',key:obj[$this.options.id],onblur:doc.blur},[content])
			if(flg){//子集进入时设置开关
				var views = openDowns?'block':'none';
				returnData =  el('div',{class:'grade tabindex',style:{'display':views},onblur:doc.blur},[own])
				return returnData
			}else{
				returnData =  el('div',{class:'grade tabindex',onblur:doc.blur},[own])
				return returnData
			}
		}
    	/**
    	 * 递归数据方法
    	 * @param {objc} 数据
    	 * @param {select} 插件本身指向的类
    	 * @param {doc} selectDom
    	 * @param {bodyArr} 数组
    	 * @param {flg} 是否第二次进入函数
    	 * */
    	function crile(objc,select,doc,bodyArr,flg){
    		//遍历数据，做结构
    		var openDowns = $this.store.get('tree').openDowns//$this.getRecord('openDwon')
			var op = openDowns?$this.store.get('tree').openIconDown:$this.store.get('tree').openIconRight
			objc.map(function(obj,idx){
				var index = flg?idx+2:idx//如果是第二次进来，bodyArr前两个元素是三角和父级选项
					bodyArr[index]=doms(obj,select,doc,flg)
				if(obj.children&&obj.children.length>0){
//					console.log(obj)
					//分支代表有子集，有子集前面要有三角开关
					bodyArr[index].children.unshift(el('i',{class:op,onclick:doc.open,onblur:doc.blur}))
//					console.log(bodyArr[index].children)
					crile(obj.children,select,doc,bodyArr[index].children,true)
				}
				if((!flg)&&!(obj.children&&obj.children.length>0)){
					//1层我就让他左浮动切没有左侧边距
					bodyArr[index].className.push('no-padding')
					bodyArr[index].className.push($this.getRecord('ztreeFather'))
				}
			})
    	}
	    this.selectDom = aoy.createComponent({
    		el:this.$.parent().get(0),
    		/**
    		 * checkbox点击规则
    		 * */
    		memory:function(data){
    			if($this.options.inputType == 'radio'){
    				if($(this).parent().find('input').length>0){
        				$this.$.parent().find('input').prop('checked',false);
        				$(this).parent().find('input').prop('checked',true);
    				}
    			}
    			if($this.options.inputType == 'checkbox'){
    				var result = $(this).find('input').is(':checked')==false;
    				if($(this).parent().parent().find('.grade').length!=0){//console.log('有子集')
    					$(this).parent().siblings('.grade').find('input').prop('checked',result)
    				}
    				if($(this).parent().parent().siblings('.checkbox').length!=0){//console.log('有父级')
    					var this_ = this
    					setTimeout(function(){//此事件发生在默认事件发生之前，所以获取到的值不包含本次点击后的操作结果，做延迟处理
        					var inputs = $(this_).parents('.grade').parents('.grade').children('.grade').children('.checkbox');
        					var checkeds = $(':checked',inputs).length;
        					if(checkeds==inputs.length){
        						$(this_).parents('.grade').siblings('.checkbox').find('input').prop('checked',true)
        					}else{
        						$(this_).parents('.grade').siblings('.checkbox').find('input').prop('checked',false)
        					}
    					},1)
    				}
    			}
    				
    		},
			clickdiy:function(){
    			var sseNames = '深交所,深交所主板,深交所中小板,深交所创业板';
				var sseKeys = '03,04,05';
				var rangeCheckStr = this.textContent;
        		var thisKey = this.firstChild.key;
				if(sseNames.indexOf(rangeCheckStr) != -1){
        			var rangeCheckResult = $(this).find('input').is(':checked') == false;
        			var rangeCheckbox = $("#rangeSelect").data('zui.SelectMod').$.parent().find(':checkbox');
					if(rangeCheckStr == '深交所'){
						rangeCheckbox.each(function(idx,obj){
							if(sseKeys.indexOf(obj.key ) != -1) {
								$(obj).prop("checked",rangeCheckResult);
                        	}
                   	 })
					}else{
						var allCheck = rangeCheckResult;
						rangeCheckbox.each(function(idx,obj){
							if(sseKeys.indexOf(obj.key ) != -1) {
								if(obj.key != thisKey) {
                                	if ($(obj).is(':checked') == false) {
                                    	allCheck = false;
                                	}
                            	}
                        	}
                    	});
						$(rangeCheckbox[0]).prop("checked",allCheck);
					}
				}
			},
    		/**
    		 * 有搜索功能时的规则
    		 * @param {event} 
    		 * @param {obj} trigger触发时传来的作用域
    		 * */
    		search:function(event,obj,flags){
    			if(flags==undefined){//表示正常调用
    				$this.$.parent().find('.grade').show();
    				$('.fa.fa-caret-right',$this.$.parent()).removeClass('fa-caret-right').addClass('fa-caret-down')
    			}
				var _this_ = this
				//console.log(obj)
				if(obj!=undefined){
					_this_ = obj
				}
				var searchBox = $(_this_).parent().next()
				if(_this_.value.length>0){
					if($this.options.allShow){
						$this.setRecord('updown',false)
					}
//					console.log(_this_.value)
					//过滤第一步，先全隐藏了
    				searchBox.find('.grade').addClass('searchNone').removeClass('searchBlock');
    				//第二部，把匹配的都显示
    				searchBox.find('label').each(function(idx,obj){
    					if($(obj).find('span').text().indexOf(_this_.value)>=0){
    						$(obj).parent().parent().addClass('searchBlock').removeClass('searchNone')
    						if($(obj).parent().siblings('.grade').length>0){
        						$(obj).parent().siblings('.grade').addClass('searchBlock').removeClass('searchNone');
        						$(obj).parent().siblings('.grade').find('.grade').addClass('searchBlock').removeClass('searchNone');
        					}
    					}
    					
    				})
    				searchBox.find('.grade').each(function(idx,obj){
    					if($(obj).hasClass('searchBlock')){
    						$(obj).parents('.grade').addClass('searchBlock').removeClass('searchNone')
    					}
    				})
				}else{
					if($this.options.allShow){
						$this.setRecord('updown',true)
					}
					//打开下拉
					searchBox.find('.grade').removeClass('searchNone').removeClass('searchBlock')
				}
    				//如果向上弹，得到结果计算高度
    				var shows = $this.getRecord('openFlow');
    				if(shows == 'up'){
    					console.log('up')
    		    		var tMaxHeight = $this.options.style.tMaxHeight;//最大高度
    		    		//确定弹出框向上弹还是向下弹
    		    		var offsetT = $this.$.offset().top,//下拉的input距离当前window顶部距离
    						tableH = $('.t-select-table',$this.$.parent()).height();//当前input下下拉框的高度
    						if(offsetT<tableH){//多出的在参数的最大高度上扣除
    							$this.options.style.tMaxHeight=tMaxHeight+(offsetT-tableH)
    						}
    						var search = $this.options.inputSearch?40:0;
    						var H = tableH<$this.options.style.tMaxHeight+search+51?$('.t-select-table',$this.$.parent()).height():$this.options.style.tMaxHeight+search+51
    						$this.$.parent().find('.t-select-table').css('top','-'+H+'px')
    					$('.t-select-cell',$this.$.parent()).css('max-height',$this.options.style.tMaxHeight+'px')
    				}
    				
    				//过滤数据搜索方式，易董需求不匹配
//    				var arr = [];
//    				$this.getRecord('default').map(function(obj,idx){
//    					if(_this_.value!=''&&obj[$this.options.name].match(_this_.value)!=null){
//    						arr.push(obj)
//    					}
//    				})
//    				if(arr.length!=0){
//    					store.get('tree').treeData = $this.selectData(arr)
//					}else{
//						store.get('tree').treeData = $this.getRecord('treeData')
//					}
    		},
    		/**
    		 * 点击三角
    		 * */
    		open:function(e){
    			if(this.className!=$this.store.get('tree').openIconDown){
    				this.className=$this.store.get('tree').openIconDown
    				$(this).siblings('.grade').css('display','block')
    			}else{
    				this.className=$this.store.get('tree').openIconRight;
    				$(this).siblings('.grade').css('display','none')
    			}
    			//$this.setRecord('triggerOpen',false);
    			$this.$.trigger('focus')
    		},
    		/**
    		 * 点击清空确定按钮规则
    		 * */
    		sureAndClear:function(flag,op){
    			var tTable = $this.$.parent().find('[type='+$this.options.inputType+']');
    			if(flag){//确定
    				//selectIds设置规则
    				var id = [],pid=[],self=[],txt='',result = [],tbvalue = [],pidStr = ',',allTxt='';
    				$this.setRecord('blurRuleInTo',true)
					tTable.each(function(idx,obj){
    					if($(obj).is(':checked')){
    						var arr = obj.className.split(' ')
    						id.push(arr[0].substring(2,arr[0].length))
    						pid.push(arr[1].substring(3,arr[1].length))
    						self.push(arr[2].substring(4,arr[2].length))
    						txt+=obj.nextSibling.innerText+','
    					}
    				})
    				pid.map(function(obj,idx){
    					pidStr+=obj+','
    				})
    				self.map(function(obj,idx){
    					//var inText = ','+obj+','
    					if(pidStr.split(',').indexOf(obj)<0){
    						result.push(obj)
    					}
    				})
    				tTable.each(function(idx,obj){
    					var arr = obj.className.split(' ')
    					var id = arr[2].substring(4,arr[2].length);
    					var value = arr[0].substring(2,arr[0].length);
    					if(result.indexOf(id)>=0){
    						txt+=obj.nextSibling.innerText+',';
    						tbvalue.push(value)
    					}
    				})
					$this.setRecord('selectedIds',tbvalue)

    				
//    				var doms = document.createElement('a');
//    				var widthNow = $this.$.width();
//    				$(doms).css({ "visibility": "hidden", "whiteSpace": "nowrap","width":"auto" }).html(txt).appendTo(document.body);
//    				var width = $(doms).width();
//    				if(widthNow<width){
//    					var num = $this.getRecord('selectedIds').length;
//    					txt = $this.$.attr('placeholder')+'('+num+')';
//    				}
//    				$this.$.val(txt);
//    				$(doms).remove();
//    				var data = {
//						value : $this.getRecord('selectedIds'),
//						name : allTxt,
//						allName : txt
//					};
    				
//    				if(op==undefined){
//    					setTimeout(function(){
//    						if($this.getRecord('blurRuleInTo')){
//        						$this.options.submitCallBack($this.$,data)
//        					}else{
//        						$this.setRecord('blurRuleInTo',true)
//        					}
//    					},25)
//    					$this.hide();
//    				}
    				$this.store.get('tree').selectId = $this.getRecord('selectedIds');
    				$this.setRecord('initFlag',false);
    				$this.store.get('tree').selectDisplay = 'none';
//    				if($this.getRecord('clear')==true){
//    					$this.setRecord('clear',false)
//    					$this.store.get('tree').selectId = $this.getRecord('selectedIds');
//    				}
    				
    			}else{//清空
    				tTable.prop('checked',false)
    			}
    		},
    		/**
    		 * 点击失去焦点规则
    		 * */
    		blur:function(e){
    			$this.blurRule();
    		},
    		/**
    		 * 点击全部展开收起
    		 * */
    		someAny:function(e){
    			if($this.getRecord('updown')){
        			$this.setRecord('blurRuleInTo',false)
        			$this.store.get('tree').selectDisplay = 'block';
        			$this.setRecord('blurRuleInTo',false)
        			$this.store.get('tree').openDowns = !$this.store.get('tree').openDowns;
    			}
    		},
    		/**
    		 * 全选按钮点击事件
    		 * */
    		allCheck:function(){
    			var tTable = $this.$.parent().find('[type='+$this.options.inputType+']');
    			tTable.prop('checked',true)
    		},
    		/**
    		 * dom渲染规则
    		 * */
    		render:function(){
    			var header = '',body = '',footer = '',box = ''//搜索下拉头部检索，tree部分，确定清空按钮，下拉盒子
    			if($this.options.inputSearch){//头部结构
    				header = el('div',
    							{class:'t-select-input tabindex',onblur:this.blur},
								[
	    							 el('input',{type:'text',class:'form-control t-select-search',oninput:this.search,onblur:this.blur,value:this.tree.searchStr}),
	    							 el('i',{class:'fa fa-search searchIcon tabindex',onblur:this.blur})
    							 ]
    							);
    			}
    			var somes = $this.options.allShow?el('span',{class:'btn t-select-clear tabindex openDowns colloge',style:{'margin-right':'20px'},onclick:this.someAny,onblur:this.blur},$this.store.get('tree').openDowns?'全部合并':'全部展开'):el('span',null);
    			var allCheck = $this.getRecord('allCheck')?el('span',{class:'btn t-select-allcheck tabindex',style:{'margin-right':'20px'},onclick:this.allCheck,onblur:this.blur},'全选'):el('span',null);
    			
    			//确定清空按钮结构
    			footer = el('div',
    						{class:'t-select-footer tabindex',onblur:this.blur},
    						[
    						 	 somes,
    						 	 allCheck,
	    						 el('span',{class:'btn t-select-clear tabindex',style:{'margin-right':'20px'},onclick:this.sureAndClear.bind(this,false),onblur:this.blur},'清空'),
	    						 el('span',{class:'btn btn-primary t-select-submit tabindex',onclick:this.sureAndClear.bind(this,true,undefined)},'确定')
    						 ]
    						);
    			var bodyArr = [];
    			//数据遍历树结构
    			crile(this.tree.treeData,$this,this,bodyArr,false)
    			var tMaxHeight = $this.options.style.tMaxHeight
    			var heightStr = 'max-height:'+tMaxHeight+'px;';
    			if(header!=''&&$this.getRecord('openFlow')=='down'){
    				heightStr='max-height:'+(tMaxHeight-41)+'px;'
    			}
    			//拼接成身体
    			body = el('div',{class:'t-select-cell tabindex',style:{'display':'block','max-height':tMaxHeight+'px','overflow':'auto','margin-bottom':'51px'},onblur:this.blur},bodyArr)
    			if(header!=''){
        			box = el('div',{class:"t-select-table tabindex",style:{'display':this.tree.selectDisplay,'width':$this.getRecord('selectWidth')+'px'},onblur:this.blur,key:this.tree.selectDisplay},[header,body,footer])
    			}else{
    				box = el('div',{class:"t-select-table tabindex",style:{'display':this.tree.selectDisplay,'width':$this.getRecord('selectWidth')+'px'},onblur:this.blur,key:this.tree.selectDisplay},[body,footer])
    			}
    			setTimeout(function(){//选中默认
    				$this.selectAskId(true)
    			},1)
//    			console.log(box)
    			return box
    		}
    	})
    	$this.setRecord('selectDom',this.selectDom);
    	aoy.connect(this.selectDom,'tree');//挂载
    };
    /**
     * 设置选中ID
     * */
    SelectMod.prototype.selectAskId = function(data) {
    	var $this = this;
		var selectId = this.getRecord('selectedIds');
		var checkBoxType = this.options.inputType;
		var tTable = this.$.parent().find('.t-select-table');
		if(checkBoxType == 'radio'){
			if(selectId!=undefined&&selectId!=''){
				tTable.find('input').prop('checked', false);
//				console.log(selectId)
				tTable.find('.id'+selectId[0]).prop('checked', true);
			}else{
				tTable.find('input').prop('checked', false);
			}
		}
		if(checkBoxType == 'checkbox'){
			if(selectId!=undefined&&selectId!=''){
				tTable.find("input[type='" + checkBoxType + "']").prop('checked',false)
				// 设置选中
				for (var i = 0; i < selectId.length; i++) {
					tTable.find('.id'+selectId[i]).prop('checked', true);
				}
				tTable.find("input[type='" + checkBoxType + "']:checked").each(function(idx, obj) {
					// 选中子孙级（易董用不到）
					$(obj).parents('.grade').eq(0).find('input[type="' + checkBoxType + '"]').prop('checked', true);
					// 选中父节点
					var flag = true;
					$(obj).parents('.grade').eq(0).siblings('.grade').each(function(i,o){
						if($(o).find('input[type="' + checkBoxType + '"]').prop('checked')==false){
							flag = false
						}
					})
					if(flag){
						$(obj).parents('.grade').eq(0).siblings('.checkbox').find('input[type="' + checkBoxType + '"]').prop('checked', true);
					}
				});
			}else{
				tTable.find("input[type='" + checkBoxType + "']").prop('checked',false)
			}
		}
		
		if(data){
        	this.$.parent().find('.tabindex').attr('tabindex',0)
        	if(!$this.getRecord('selectedIds')) return;
        	var id = $this.getRecord('selectedIds');
        	var txt = '';
        	var allTxt;
        	if(id.length>0&&id[0].length>0){
            	id.map(function(obj,idx){
            		if($('.id'+obj,$this.$.parent()).next().length>0) txt+=$('.id'+obj,$this.$.parent()).next().html()+','
            	})
            	if(txt.length>1) txt = txt.substring(0,txt.length-1);
            	var doms = document.createElement('a');
            	//算宽
    			var widthNow = parseInt($this.$.width())-15;
    			$(doms).css({ "visibility": "hidden", "whiteSpace": "nowrap","width":"auto" }).html(txt).appendTo(document.body);
    			var width = $(doms).width();
    			allTxt = txt
    			if(widthNow<width){
    				var num = $this.getRecord('selectedIds').length;
    				txt = $this.$.attr('placeholder')+'('+num+')';
    			}
    			$(doms).remove();
        	}else{
        		txt = '';
            	allTxt = txt;
        	}
        	$this.$.val(txt);
        	var va = [];
        	if($this.options.value!='id'){
            	$this.getRecord('default').map(function(obj,idx){
            		if($this.getRecord('selectedIds').indexOf(obj[$this.options.value])>=0){
            			va.push(obj[$this.options.value])
            		}
            	})
        	}else{
        		va = $this.getRecord('selectedIds')
        	}
        	va = va.join(',');
			var data = {
				value : va,
				name : txt,
				allName : allTxt
			};
			$this.$.attr('param',va);
			setTimeout(function(){
//				console.log($this.getRecord('blurRuleInTo')+'----------------')
				if($this.getRecord('blurRuleInTo')){
					//console.warn('ID submits a query for '+$this.$.attr('id')+' of the element')
					if($this.getRecord('initFlag')){
						$this.options.submitCallBack($this.$,data);
					}else{
						$this.setRecord('initFlag',true)
					}
					
				}
			},30)
    	}else{
    		//this.getRecord('selectDom').sureAndClear(true,true)
    	}
		
    };
    /**
     * 隐藏下拉方法
     * */
    SelectMod.prototype.hide = function(){
    	var $this = this;
    	$('.t-select-table',$this.$.parent()).slideUp('fast');
    	if($this.$.parent().find('.t-select-search').length>0){
			$this.$.parent().find('.t-select-search').val('');
			$this.getRecord('selectDom').search(window.event,$this.$.parent().find('.t-select-search').get(0),true)
			//$this.setRecord('blurRuleInTo',false)
		}
    }
    /**
     * 数据过滤器
     * */
    SelectMod.prototype.selectData = function(data) {
    	//表示数据没处理过，是一维的
    	if(this.options.dataType=='common'){
    		return MultidimensionalData(data,this.options,this)
    	}else{
    		//否则是处理过的，直接返回
    		return data
    	}
    };
    /**
     *  已处理过的数据(没用上)
     *  处理数据过滤器
     * */
    SelectMod.prototype.goToDefault = function(data){
    	var arr = [];
    	MultidimenData(data,arr)
    	return arr
    }
    /**
     * 行为记录器设置
     * */
    SelectMod.prototype.setRecord = function(name,data) {
    	this.$.data(name,data)
    };
    /**
     * 行为记录器获取
     * */
    SelectMod.prototype.getRecord = function(name) {
    	return this.$.data(name)
    };
    /**
     * 原始input输入框点击事件
     * */
    SelectMod.prototype.showRule = function(){
    	var $this = this;
    	this.$.blur(function(){
    		$this.blurRule();
    	})
    }
    SelectMod.prototype.blurRule = function(){
    	var $this = this;
    	clearTimeout($this.getRecord('outTimer'));
    	$this.setRecord('outTimer',setTimeout(function(){
//    		console.log($this.getRecord('mouseFlag')+'         333333333333333333333')
//    		console.log($this.getRecord('target'))
//    		console.log(!$this.$.parent().get(0).contains($this.getRecord('target')))
    			if($($this.getRecord('target')).hasClass('openDowns')){
    				return
    			};//特殊处理收起展开
            	if($this.getRecord('mouseFlag')==1){
            		if(!$this.$.parent().get(0).contains($this.getRecord('target'))){
            	    	$this.setRecord('blurRuleInTo',false)
            	    	//$this.setRecord('triggerOpen',true);
//            			console.log('应该关闭')
            			$this.hide()
            	    	$this.store.get('tree').selectId = $this.getRecord('selectedIds');
//            			setTimeout(function(){
//            				$this.setRecord('blurRuleInTo',true)
//            				console.log('变成了true')
//            			},400)
            		}
            	}else{
                	$this.setRecord('blurRuleInTo',false)
                	//$this.setRecord('triggerOpen',true);
//            		console.log('更应该关闭')
            		$this.hide()
                	$this.store.get('tree').selectId = $this.getRecord('selectedIds');
//            		setTimeout(function(){
//        				$this.setRecord('blurRuleInTo',true)
//        				console.log('变成了true')
//        			},400)
            	}
        	},20)
    	)
    	
    }
    SelectMod.prototype.clear = function(func){
    	this.setRecord('selectedIds',[]);
    	this.setRecord('initFlag',false)
    	this.setRecord('clear',true)
    	this.setRecord('blurRuleInTo',true)
    	this.selectAskId(true);
    	if(typeof func == 'function'){
    		func(this.$)
    	}
    }
    SelectMod.prototype.reload = function(data){
    	this.setRecord('selectedIds',data.split(','));
    	this.setRecord('blurRuleInTo',true)
    	this.setRecord('initFlag',false)
    	this.selectAskId(true);
    }
    SelectMod.prototype.reloadZH = function(data){
    	var $this = this;
		var selectId = this.getRecord('selectedIds');
		var checkBoxType = this.options.inputType;
		var tTable = this.$.parent().find('.t-select-table').find('span');
		if(checkBoxType=='checkbox'){
			var arr = data.split(','),result = [],parentId=[],self=[],datas=[];
			tTable.each(function(idx,obj){
				if(arr.indexOf(obj.innerText)>=0){
					var ids = obj.previousSibling.className.split(' ');
					var id = ids[2].substring(4,ids[2].length)
					var pid = ids[1].substring(3,ids[1].length)
					parentId.push(pid);
					self.push(id)
				}
			})
			self.map(function(obj,idx){
				if(parentId.indexOf(obj)<0){
					result.push(obj)
				}
			})
			result.map(function(obj,idx){
				var ids = $('.id'+obj).get(0).className.split(' ');
				datas.push(ids[0].substring(2,ids[0].length))
			})
			this.setRecord('selectedIds',result)
			this.setRecord('blurRuleInTo',true)
			this.selectAskId(true);
		}
    }
    //深复制对象方法    
    var cloneObj = function (obj) {
      var newObj = {};
      if (obj instanceof Array) {
        newObj = [];
      }
      for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。  
        newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
      }
      return newObj;
    };
    /**
     * 1维数据改成多维数据
     * ${datalist} 数据
     */
    function MultidimensionalData(datalist,options,$this) {
    	var id = options.id,
    		parentId = options.pid;
      // 拿到第一层方法
      function getOne(data) {
        var one = [],list
        data.map(function (obj, idx) {
          var flag = 0;
          data.map(function (o, i) {
            if (obj[parentId] == o[id]) {
              flag++
            }
            return o
          })
          if (flag == 0) {
            list = cloneObj(obj)
            list.children = [];
            one.push(list)
          }
          return obj
        })
        return one;
      }
      // 递归方法
      function fns(one, data) {
        one.map(function (obj, idx) {
          data.map(function (o, i) {
            if (obj[id] == o[parentId]) {
    			$this.setRecord('ztreeFather','noStyle');
            	var objStr = JSON.stringify(obj.children);
				var list = cloneObj(o);
				list.children = [];
            	var oStr = JSON.stringify(list);
            	if(objStr.indexOf(oStr)<0){
    				obj.children.push(list)
            	}
				fns(obj.children, data)
            }
          })
        })
      }
      var middle = [].concat(datalist)
      var one = getOne(middle);
      fns(one, middle)
      return one
    }
    /**
     * 多维数据变成1维数据(没用上)
     * ${datalist} 数据
     */
    function MultidimenData(data,arry){
    	var list;
    	data.map(function(obj,idx){
    		list = cloneObj(obj)
			list.children = [];
			arry.push(list)
    		if(obj.children&&obj.children.length>0){
    			MultidimenData(obj.children,arry)
    		}
    	})
    }
}).call(this);
