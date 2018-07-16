<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript">
        alert($);
        $(function(){
            $.ajax({
                url:"http://localhost:8890/json",
                type:"get",
                dataType:"json",
                success:function(data){
                    alert(data.abc);
                }
            })
        })
    </script>
</head>
<body>

</body>
</html>