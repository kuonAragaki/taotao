var TT = TAOTAO = {
    checkLogin : function(){
        var _ticket = $.cookie("TT_TOKEN");
        if(!_ticket){
            return ;
        }
        $.ajax({
            url : "http://localhost:8101/user/token/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                //data=JSON.parse(data);
                if(data.status == 200){
                    //var _data = JSON.parse(data.data);
                    var html =data.data.username+"，欢迎来到shop！<a href=\"http://localhost:8101/user/logout\" class=\"link-logout\">[退出]</a>";
                    $("#loginbar").html(html);
                }
            }
        });
    }
}

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
});