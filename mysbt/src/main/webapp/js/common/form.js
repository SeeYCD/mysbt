//   表单校验，自定义扩展校验规则
function initValidatebox(){
	$.extend($.fn.validatebox.defaults.rules, {
	    equals: {
			validator: function(value,param){
				return value == $(param[0]).val();
			},
			message: '密码不一致，请重新输入！'
	    },
		minLength: {
			validator: function(value, param){
				return value.length >= param[0];
			},
			message: 'Please enter at least {0} characters.'
	    }
	});
}