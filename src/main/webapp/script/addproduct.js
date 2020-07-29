$(function(){
	//对新增按钮创建点击事件
	$("#addBtn").click(function(){
		//1.页面验证，验证是否填写产品名称
	      //首先得到产品名称
		  var proname = $("#proname").val();
		  if(!proname){
			  alert("请填写产品名称");
			  return;
		  }
		// 发送Ajax请求
		$.post("/platform/addpro",$("[name]").serialize(),function(data){
		  if(data == "true"){
			  alert("添加新产品成功！");
			  window.location.href = "/platform/product.html";
		  }else{
			  alert("产品名称重复，请重新输入");
		  }
		},"text");
		
	});
});