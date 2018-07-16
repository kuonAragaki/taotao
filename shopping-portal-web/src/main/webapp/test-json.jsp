<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="js/jquery-1.6.4.js"></script>
    <script type="text/javascript">
        /* alert($);
         $(function(){
             $.ajax({
                 url:"http://localhost:8890/json",
                 type:"get",
                 dataType:"json",
                 success:function(data){
                     alert(data.abc);
                 }
             })
         })*/
        /*function fun(data) {
            alert(data.abc);
        }*/
        function fun() {
            $.ajax({
                url:"http://localhost:8890/json",
                type:"get",
                dataType:"jsonp",
                success:function (data) {
                    alert(data.abc);
                }
            })
        }
    </script>
    <script type="text/javascript" src="http://127.0.0.1:8090/json"></script>
</head>
<body>

</body>
</html>