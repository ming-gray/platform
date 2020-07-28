$(function(){
	//动态生成修改按钮
	$("<input type='button' value='修改'>").click(function(){
		if($(this).val() == "修改"){
		//如果当前用户点击的是修改，则用户点击的是修改功能
		//1.将用户名变为可编辑状态
			//1.1获取当前用户名
			//获取当前行的第二个单元格对象
			var oTd2 =$(this).parent().parent().find("td:eq(1)");
			//获取其中的文本
			var ordid =oTd2.html();
			//1.2清空单元格
			oTd2.empty();
			//1.3动态生成一个文本框，并将用户名初始化到其中并将其放到oTd2中
			$("<input type='text'>").css("width","50px").val(ordid).appendTo(oTd2);
		//3.将按钮变为确定状态
			$(this).val("确定");	
		}else if($(this).val() == "确定"){
		//如果当前按钮上的文字是确定，则用户点击的是确定功能
		//1.页面认证
			//1.1验证用户名是否填写
			//获取到当前行的第二个文本框对象
			var oText2 = $(this).parent().parent().find("td:eq(1) input");
			var ordid = oText2.val();
			if(!ordid){
				alert("请输入dingdan");
				oText2.focus();
				return;
			}
		//2.发送ajax请求进行修改
			//获取当前用户的id
			var ordid = $(this).parent().parent().find("td:eq(1)").html();
			
			var oBtn = $(this);
			
			$.post("/platform/modplan","ordid="+ordid,function(data){
				if(data == "true"){
				//修改成功
				//1.将用户名编程不可编辑状态
					var oTd2=oBtn.parent().parent().find("td:eq(1)");
					var oText2 =oTd2.find("input");
					//获取用户输入的用户名
					var ordid=oText2.val();
					//清空单元格
					oTd2.empty();
					//将新用户名填天会到单元格中
					oTd2.html(ordid);
				//3.将当前按钮变成修改按钮
					oBtn.val("修改");
				//4.提示信息
					alert("修改成功");
				}else{
					//修改失败
					alert("修改失败");
				}
			},"text");
		}
	}).appendTo(oTd);
});