//添加产品按钮绑定点击事件
$(function(){
	$("#addBtn").click(function(){
        
        window.location.href = "/platform/addproduct.html";
        
	});
});

//分页查询的功能实现
$(function(){
	//给显示的查询按钮绑定点击事件
	$("#searchBtnShow").click(function(){
		$("#pageNum").val(1);
		$("#searchBtn").click();
	});
	
	// 给隐藏查询按钮绑定点击事件
	$("#searchBtn").click(function(){
		// 发送Ajax请求
		$.post("/platform/searchpro",$("[name]").serialize(),function(data){
			if(data && data.size>0){
				// 1.动态添加信息
				// 添加总记录条数
				$("#total").html(data.total);
				// 添加总页数
				$("#pages").html(data.pages);
				// 添加当前页数
				$("#curpage").html(data.pageNum);
				
				// 2.添加记录
				// 先清空之前的查询记录，删除原有记录行
				$("#resulttable tr:gt(0)").remove();
				//循环遍历结果集，添加数据行
				for(var i=0;i< data.list.length;i++){
					var productinfo = data.list[i];
					//创建一行，生成tr元素  tr代表行
					var oTr = $("<tr></tr>");
					// 生成单元格,写入数据，放到tr中
					$("<td></td>").html(i+1).appendTo(oTr);
					$("<td></td>").html(productinfo.proid).appendTo(oTr);
					$("<td></td>").html(null).appendTo(oTr);
					$("<td></td>").html(productinfo.proname).appendTo(oTr);
					// 将te放入表格中
					oTr.appendTo("#resulttable");
					//动态生成删除按钮
					var oTd=$("<td></td>").appendTo(oTr);
						$("<input type='button' value='删除''>'").click(function(){
							//进行二次确认
							var isOK=confirm("请再次确认要删除此产品");
							//真正删除产品
							if(isOK){
								//如何获取当前删除的id
								//通过Dao模型之间的父子关系来获取proid
								var proid=$(this).parent().parent().find("td:eq(1)").html();
								//发ajax请求删除数据
								$.post("/platform/delpro","proid="+proid,function(data){
									if(data == "true"){
										//删除成功
										alert("删除成功");
										$("#searchBtn").click();
									}else{
										//删除失败
										alert("已有状态为已接单的关联订单,删除失败,请重试");
									}
								},"text")
								
							}
						}).appendTo(oTd);
						//动态生成修改按钮
						$("<input type='button' value='修改'>").click(function(){
							if($(this).val() == "修改"){
							//如果当前用户点击的是修改，则用户点击的是修改功能
							//1.将用户名变为可编辑状态
								//1.1获取当前用户名
								//获取当前行的第二个单元格对象
								var oTd4 =$(this).parent().parent().find("td:eq(3)");
								//获取其中的文本
								var proname =oTd4.html();
								//1.2清空单元格
								oTd4.empty();
								//1.3动态生成一个文本框，并将用户名初始化到其中并将其放到oTd2中
								$("<input type='text'>").css("width","50px").val(proname).appendTo(oTd4);
							//3.将按钮变为确定状态
								$(this).val("确定");	
							}else if($(this).val() == "确定"){
							//如果当前按钮上的文字是确定，则用户点击的是确定功能
							//1.页面认证
								//1.1验证用户名是否填写
								//获取到当前行的第二个文本框对象
								var oText4 = $(this).parent().parent().find("td:eq(3) input");
								var proname = oText4.val();
								if(!proname){
									alert("请输入产品名称");
									oText4.focus();
									return;
								}
							//2.发送ajax请求进行修改
								//获取当前用户的id
								var proid = $(this).parent().parent().find("td:eq(1)").html();
								
								var oBtn = $(this);
								
								$.post("/platform/modpro","proid="+proid+"&proname="+proname,function(data){
									if(data == "true"){
									//修改成功
									//1.将用户名编程不可编辑状态
										var oTd4=oBtn.parent().parent().find("td:eq(3)");
										var oText4 =oTd4.find("input");
										//获取用户输入的用户名
										var proname=oText4.val();
										//清空单元格
										oTd4.empty();
										//将新用户名填天会到单元格中
										oTd4.html(proname);
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
							
				}
				
				// 3.页面的控制
				// 结果表格和分页控制按钮的显示
				   $("#resultdiv").show();
				   $("#pagectrl").show();
				//上一页和下一页按钮的控制
				   if(data.isFirstPage){
					   // 当前是第一页
					   $("#prePage").attr("disabled",true);
				   }else{
					   $("#prePage").attr("disabled",false);
				   }
				   
				   if(data.isLastPage){
					   $("#nextPage").attr("disabled",true);
				   }else{
					   $("#nextPage").attr("disabled",false);
				   }
				
			}
			else{
				   $("#resultdiv").hide();
				   $("#pagectrl").hide();
				   alert("没有查到数据");
			   }
		},"json");
		
	});
});
//分页控制
$(function(){
	//上一页功能
	$("#prePage").click(function(){
		//将页面上隐藏的pageNum的值-1
		//val()可以获取值，也可以赋值
		var pageNum = $("#pageNum").val();
		$("#pageNum").val(pageNum-1);
		//再进行一次查询操作,即点击一次查询按钮
		//click同val类似，给function为绑定事件，不给则认为进行一次点击操作
		$("#searchBtn").click();
		
	});
	
	//完成下一页功能
	$("#nextPage").click(function(){
		//pageNum是字符串类型，字符串无减的操作，而+会被字符串认为是连接操作
		var pageNum = $("#pageNum").val();
		$("#pageNum").val(pageNum*1+1);
		//点击查询按钮
		$("#searchBtn").click();
	});
	
	//完成Go按钮功能
	$("#goBtn").click(function(){
		//获取跳转页数，转成数字
		var gopage = $("#gopage").val()*1;
		//判断用户输入
			//页面上用户输入东西都是字符串
			if(gopage<1){
				gopage = 1;
			}
			//获取总页数
			var pages = $("#pages").html();
			if(gopage> pages*1){
				gopage = pages;
			}
			$("#pageNum").val(gopage);
			$("#searchBtn").click();
});
});