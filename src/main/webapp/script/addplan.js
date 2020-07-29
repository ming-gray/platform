$(function(){
	//对新增按钮创建点击事件
	$("#addplanBtn").click(function(){
		//1.页面验证
		  var ordid = $("#ordid").val();
		  if(!ordid){
			  alert("请填写订单编号");
			  return;
		  }
		  var proid = $("#proid").val();
		  if(!proid){
			  alert("请填写产品编号");
			  return;
		  }
		  var ddl = $("#ddl").val();
		  if(!ddl){
			  alert("请填写交货日期");
			  return;
		  }
		 
		// 发送Ajax请求
		$.post("/platform/addplan",$("[name]").serialize(),function(data){
		  if(data == true){
			  alert("添加生产计划成功！");
			  window.location.href = "/platform/plan.html";
		  }else{
			  alert("添加失败");
		  }
		},"json");
		
	});
});