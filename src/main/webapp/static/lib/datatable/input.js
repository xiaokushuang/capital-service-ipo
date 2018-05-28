/**
 * Sometimes for quick navigation, it can be useful to allow an end user to
 * enter which page they wish to jump to manually. This paging control uses a
 * text input box to accept new paging numbers (arrow keys are also allowed
 * for), and four standard navigation buttons are also presented to the end
 * user.
 *
 *  @name Navigation with text input
 *  @summary Shows an input element into which the user can type a page number
 *  @author [Allan Jardine](http://sprymedia.co.uk)
 *  @author [Gordey Doronin](http://github.com/GDoronin)
 *
 *  @example
 *    $(document).ready(function() {
 *        $('#example').dataTable( {
 *            "pagingType": "input"
 *        } );
 *    } );
 */

//需求1226 Wang Guili & Gerry
(function ($) {
	function calcDisableClasses(oSettings) {
		var start = oSettings._iDisplayStart;
		var length = oSettings._iDisplayLength;
		var visibleRecords = oSettings.fnRecordsDisplay();
		var all = length === -1;

		// Gordey Doronin: Re-used this code from main jQuery.dataTables source code. To be consistent.
		var page = all ? 0 : Math.ceil(start / length);
		var pages = all ? 1 : Math.ceil(visibleRecords / length);

		var disableFirstPrevClass = (page > 0 ? '' : oSettings.oClasses.sPageButtonDisabled);
		var disableNextLastClass = (page < pages - 1 ? '' : oSettings.oClasses.sPageButtonDisabled);

		return {
			'first': disableFirstPrevClass,
			'previous': disableFirstPrevClass,
			'next': disableNextLastClass,
			'last': disableNextLastClass
		};
	}

	function calcCurrentPage(oSettings) {
		return Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength) + 1;
	}

	function calcPages(oSettings) {
		return Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength);
	}

	var firstClassName = 'first';
	var previousClassName = 'previous';
	var nextClassName = 'next';
	var lastClassName = 'last';

	var paginateClassName = 'paginate';
	var paginateOfClassName = 'paginate_of';
	var paginatePageClassName = 'paginate_page';
	var paginateInputClassName = 'paginate_input';
	
	//若引入bootstrap样式，则按钮文本框会显示bootstrap样式
	var paginateButtonClassName = 'btn btn-default';
    var paginateInputNewClassName = 'form-control';
    
	$.fn.dataTableExt.oPagination.input = {
		'fnInit': function (oSettings, nPaging, fnCallbackDraw) {
			var nFirst = document.createElement('span');
			var nPrevious = document.createElement('span');
			var nNext = document.createElement('span');
			var nLast = document.createElement('span');
			var nInput = document.createElement('input');
			var nPage = document.createElement('span');
			var nOf = document.createElement('span');

			var language = oSettings.oLanguage.oPaginate;
			var classes = oSettings.oClasses;

			nFirst.innerHTML = language.sFirst;
			nPrevious.innerHTML = language.sPrevious;
			nNext.innerHTML = language.sNext;
			nLast.innerHTML = language.sLast;

			nFirst.className = firstClassName + ' ' + classes.sPageButto + ' ' + paginateButtonClassName;
			nPrevious.className = previousClassName + ' ' + classes.sPageButton + ' ' + paginateButtonClassName;
			nNext.className = nextClassName + ' ' + classes.sPageButton + ' ' + paginateButtonClassName;
			nLast.className = lastClassName + ' ' + classes.sPageButton + ' ' + paginateButtonClassName;

			nOf.className = paginateOfClassName;
			nPage.className = paginatePageClassName;
			nInput.className = paginateInputClassName + ' ' + paginateInputNewClassName;

			if (oSettings.sTableId !== '') {
				nPaging.setAttribute('id', oSettings.sTableId + '_' + paginateClassName);
				nFirst.setAttribute('id', oSettings.sTableId + '_' + firstClassName);
				nPrevious.setAttribute('id', oSettings.sTableId + '_' + previousClassName);
				nNext.setAttribute('id', oSettings.sTableId + '_' + nextClassName);
				nLast.setAttribute('id', oSettings.sTableId + '_' + lastClassName);
			}

			nInput.type = 'text';
			nPage.innerHTML = ' 第 ';

			nPaging.appendChild(nFirst);
			nPaging.appendChild(nPrevious);
			nPaging.appendChild(nNext);
			nPaging.appendChild(nLast);
			nPaging.appendChild(nPage);
			nPaging.appendChild(nInput);
			nPaging.appendChild(nOf);
			

			$(nFirst).click(function(event) {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== 1) {
					oSettings.oApi._fnPageChange(oSettings, 'first');
					fnCallbackDraw(oSettings);
					event.preventDefault();
				}
			});

			$(nPrevious).click(function(event) {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== 1) {
					oSettings.oApi._fnPageChange(oSettings, 'previous');
					fnCallbackDraw(oSettings);
					event.preventDefault();
				}
			});

			$(nNext).click(function(event) {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== calcPages(oSettings)) {
					oSettings.oApi._fnPageChange(oSettings, 'next');
					fnCallbackDraw(oSettings);
					event.preventDefault();
				}
			});

			$(nLast).click(function(event) {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== calcPages(oSettings)) {
					oSettings.oApi._fnPageChange(oSettings, 'last');
					fnCallbackDraw(oSettings);
					event.preventDefault();
				}
			});

			$(nInput).keyup(function (e) {
				// 38 = up arrow, 39 = right arrow
				if (e.which === 38 || e.which === 39) {
					this.value++;
				}
				// 37 = left arrow, 40 = down arrow
				else if ((e.which === 37 || e.which === 40) && this.value > 1) {
					this.value--;
				}

				if (this.value === '' || this.value.match(/[^0-9]/)) {
					 //Nothing entered or non-numeric character 
					this.value = this.value.replace(/[^\d]/g, ''); // don't even allow anything but digits
					event.preventDefault();
					return;
				}

				var iNewStart = oSettings._iDisplayLength * (this.value - 1);
				if (iNewStart < 0) {
					iNewStart = 0;
				}
				if (iNewStart >= oSettings.fnRecordsDisplay()) {
					iNewStart = (Math.ceil((oSettings.fnRecordsDisplay()) / oSettings._iDisplayLength) - 1) * oSettings._iDisplayLength;
				}
				
				oSettings._iDisplayStart = iNewStart;
				fnCallbackDraw(oSettings);
				event.preventDefault();
			});

			// Take the brutal approach to cancelling text selection.
			$('span', nPaging).bind('mousedown', function () { return false; });
			$('span', nPaging).bind('selectstart', function() { return false; });

			// If we can't page anyway, might as well not show it.
			var iPages = calcPages(oSettings);
			if (iPages <= 1) {
				$(nPaging).hide();
			}
		},

		'fnUpdate': function (oSettings) {
			if (!oSettings.aanFeatures.p) {
				return;
			}

			var iPages = calcPages(oSettings);
			var iCurrentPage = calcCurrentPage(oSettings);

			var an = oSettings.aanFeatures.p;
			
			var disableClasses = calcDisableClasses(oSettings);
			if (iPages < 1) // hide paging when we can't page
			{
				$(an).show();
				
				changeClass(an, firstClassName, oSettings, disableClasses);
				changeClass(an, previousClassName, oSettings, disableClasses);
				changeClass(an, nextClassName, oSettings, disableClasses);
				changeClass(an, lastClassName, oSettings, disableClasses);

				var messageInfo = ' 页，共 ' + 0 + ' 页 ';
				changeInfo(an, paginateOfClassName, messageInfo);

				// Current page numer input value
				$(an).children('.' + paginateInputClassName).val(0).attr('disabled','disabled');
				
				return;
			}

			$(an).show();
			
			changeClass(an, firstClassName, oSettings, disableClasses);
			changeClass(an, previousClassName, oSettings, disableClasses);
			changeClass(an, nextClassName, oSettings, disableClasses);
			changeClass(an, lastClassName, oSettings, disableClasses);
			
			var messageInfo = ' 页，共 ' + iPages + ' 页 ';
			changeInfo(an, paginateOfClassName, messageInfo);

			// Current page numer input value
			$(an).children('.' + paginateInputClassName).val(iCurrentPage).removeAttr('disabled');
		}
	};
	//设置按钮的class
	function changeClass(_pageRoot, _buttonClass, _newSettings, _disableClasses){
		$(_pageRoot).children('.' + _buttonClass)
		.removeClass(_newSettings.oClasses.sPageButtonDisabled)
		.addClass(_disableClasses[_buttonClass]);
	}
	//设置显示信息
	function changeInfo(_pageRoot, _paginateClassName, _message){
		$(_pageRoot).children('.' + _paginateClassName).html(_message);
	}
	
})(jQuery);
