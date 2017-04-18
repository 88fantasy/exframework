var app = app||{};
namespace.reg('app.util');
app.util.getTop = function(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += app.util.getTop(e.offsetParent);
	return offset;
};

app.util.reset = function() {

	function getLeft(e) {
		var offset = e.offsetLeft;
		if (e.offsetParent != null)
			offset += getLeft(e.offsetParent);
		return offset;
	}
	var addTable = document.getElementById('addTable');
	var eleTop = app.util.getTop(addTable);
	addTable.style.height = $(window).height() - eleTop - 63 + "px";
};

app.util.getToken = function(){
	return app.boot.token;
}
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
Date.prototype.addDays = function(days) {
    this.setDate(this.getDate() + parseInt(days));
    return this;
};
Date.prototype.GetDateStr = function(AddDayCount) {
	var dd = new Date();
	dd.addDays(AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = (dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd
			.getMonth() + 1);// 获取当前月份的日期，不足10补0
	var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate(); // 获取当前几号，不足10补0
	return y + "-" + m + "-" + d ;// + ' 00:00:00';
}
// input y-m-d
Date.isDate = function (s) {
	if(regs = s.match(/^(\d{4})-(\d{1,2})-(\d{1,2})$/)) {
		  // day value between 1 and 31
	    if(regs[3] < 1 || regs[3] > 31) {
	      return false;
	    }
	    // month value between 1 and 12
	    if(regs[2] < 1 || regs[2] > 12) {
	      form.startdate.focus();
	      return false;
	    }
	    // year value between 1902 and now + 20
	    if(regs[1] < 1902 || regs[1] > (new Date()).getFullYear()+20) {
	      return false;
	    }
	    return true;
	  } else {
	    return false;
	  }
}
app.util.loading = function() {
// var loading = $('.main-loader');
 return {
	 open : function() {
		 $('.main-loader').css("display","flex");
	 },
	 close : function() {
		 $('.main-loader').css("display","none");
	 }
 }
}();

app.util.eventcompatible = function(e) {
	 if(navigator.platform.toUpperCase().indexOf('MAC')>=0){
		 if(e.metaKey) {
			 e.ctrlKey = true;
		 }
	 }
	 return e;
};

app.util.ajax = function(url,setting) {
	setting = setting || {};
	
	if(!setting.timeout) {
		setting.timeout = 30 * 60 * 1000; //30分钟
	}
	
	$.ajax(url,setting);
};

app.util.setSelected = function(ele,selectedClass) {
	 $(ele).addClass(selectedClass).siblings().removeClass(selectedClass);
};

app.util.compare = function(value, order) {
	var change = 1;
	if (order == 'down')
		change = -1;
	return function(a, b) {
		var a1 = a[value];
		var b1 = b[value];
		if (!isNaN(parseInt(a1))) {
			if (a1.indexOf('-') != -1) {
				var a2 = a1.substring(a1.indexOf('-'), a1.length);
				if (a2.indexOf('-') != -1) {
					return (a1.localeCompare(b1)) * change;
				}
			}
			a1 = parseInt(a1);
			b1 = parseInt(b1);
			if (a1 < b1) {
				return -1 * change;
			} else if (a1 == b1) {
				return 0 * change;
			}
			return 1 * change;
		}
		return (a1.localeCompare(b1)) * change;
	};
};


// app.util.reset();
// $(window).resize(app.util.reset);
