$(document).ready(function(){
	$.post("/platform/initorder",null,function(data){
		 
		if(data){
			for(var i=0;i<data.length;i++){
				var order = data[i];
				//动态生成页面元素
            	$("<option></option>").html(order.ordid).val(order.ordid).appendTo("#selordid");
			}
		}
	},"json");
	$("#selordid").change(function(){  
		 var ordid=$(this).val();
		 $("#selproid option:gt(0)").remove();
		 if(ordid*1==0){
			 $("#selproid").attr("disabled",true); 
		 }else{
			 $("#selproid").attr("disabled",false);
			 $.post("/platform/getproidbyordid","ordid="+ordid,function(data){
				 if(data){
					 for(var i=0; i<data.length;i++){
						var order=data[i];
						$("<option></option>").html(order.proid).val(order.proid).appendTo("#selproid");
						}
				 }
			},"json");
			
			 	 
			 }
		 
			 
		 });
	
});

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