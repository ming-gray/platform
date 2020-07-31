//对订单编号下拉列表的操作
$(document).ready(function(){
	$.post("/platform/initorder",null,function(data){
		 
		if(data){
			for(var i=0;i<data.length;i++){
				var order = data[i];
				//动态生成页面元素
            	$("<option></option>").html(order.ordid).val(order.ordid).appendTo("#selordid");
            //	$("<option></option>").html(plan.proid).val(plan.proid).appendTo("#selproid");
			}
		}
	},"json");
	//2.给订单编号下拉列表绑定change事件
	$("#selordid").change(function(){
		var ordid = $(this).val();
		$("#selproid option:gt(0)").remove();
		    //得到产品编号
			$.post("/platform/getproidbyordid","ordid="+ordid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var order = data[i];
						$("<option></option>").html(order.proid).val(order.proid).appendTo("#selproid");
					}
				}
			},"json");
			$("#selproid").attr("disabled",false);
	        //得到产品数量
			$("#selplancount option:gt(0)").remove();
////////////////////////////////////////////////////////////////////////////////////////////////////
			//var num = 0;
			var pcount = 0;
			$.post("/platform/getproordnumbyordid","ordid="+ordid,function(data1){
				if(data1){
					for(var i=0;i<data1.length;i++){
						var order = data1[i];
					    pcount = (order.proordnum*1);
					//	$("<option></option>").html(order.proordnum).appendTo("#selplancount");
					}
				}
			},"json");
			//var pcount = num*1;
			$.post("/platform/getplanbyordid","ordid="+ordid,function(data2){
				//得到此订单包含的所有生产计划
				//获取每个计划的产品数量
				if(data2){
					for(var i=0;i<data2.length;i++){
						var plan = data2[i];
						pcount = (pcount-(plan.plancount*1));
					}
				/*	$("#addplanBtn").click(function(){
					var proordnum = $("#plancount").val();
					if(proordnum>pcount){
						  alert("产品数量错误，超过订单预期");
						  return;
					  }
					});*/
					//对新增按钮创建点击事件
					$("#addplanBtn").click(function(){
						//1.页面验证
						  var ordid = $("#selordid").val();
						  if(!ordid){
							  alert("请填写订单编号");
							  return;
						  }
						  var proid = $("#selproid").val();
						  if(!proid){
							  alert("请填写产品编号");
							  return;
						  }
						//  var proordnum = $("#selplancount").val();
						  var proordnum = $("#plancount").val();
						  if(!proordnum){
							  alert("请填写产品数量");
							  return;
						  }
						  if(proordnum>pcount){
							  alert("产品数量错误，超过订单预期");
							  return;
						  }
					/*	  if(proordnum>pcount){
							  alert("产品数量错误，超过订单预期");
							  return;
						  }*/
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
				//	$("#plancount").html(pcount);//.appendTo("#plancount");
				//	$("<option></option>").html(pcount).appendTo("#selplancount");
				}
			},"json");
		//	$("#plancount").change(function(){
		//	$("#selplancount").attr("disabled",false);
////////////////////////////////////////////////////////////////////////////////////////////////////
	/*		$.post("/platform/getproordnumbyordid","ordid="+ordid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var order = data[i];
						$("<option></option>").html(order.proordnum).appendTo("#selplancount");
					}
				}
			},"json");
			$("#selplancount").attr("disabled",false);*/
	});
});/*
$(function(){
	//对新增按钮创建点击事件
	$("#addplanBtn").click(function(){
		//1.页面验证
		  var ordid = $("#selordid").val();
		  if(!ordid){
			  alert("请填写订单编号");
			  return;
		  }
		  var proid = $("#selproid").val();
		  if(!proid){
			  alert("请填写产品编号");
			  return;
		  }
		//  var proordnum = $("#selplancount").val();
		  var proordnum = $("#plancount").val();
		  if(!proordnum){
			  alert("请填写产品数量");
			  return;
		  }
	/*	  if(proordnum>pcount){
			  alert("产品数量错误，超过订单预期");
			  return;
		  }//
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
});*/