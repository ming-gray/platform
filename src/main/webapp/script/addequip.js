  $(function(){
      //2.给注册按钮绑定注册事件
    $("#addBtn").click(function(){
/*       var eqid = $("#eqid").val();
       if(!eqid){
           alert("请输入用户名");
           //光标进入文本框
           $("#eqid").focus();
           return;
       }*/
       $.post("/platform/addequip",$("[name]").serialize(),function(data){
           if(data == "true"){
               alert("添加成功mua!!!!!!!!!");
               //注册成功，跳转登录
               //window.location.href = "/mavendemo/login.html";
           }
           else{
               //用户名冲突
               alert("wronggggggggggg!!!!!!!!!");
               $("#eqid").focus();
               $("#eqid").select();
           }
       },"text");
       });
});