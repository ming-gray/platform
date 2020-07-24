$(function(){
    // 启动验证框架
    $.validationSetup({
        submit : "#btn"
    });
    
    $("#btn").click(function(){
        
        alert($("[name]").serialize());
    });
    
})