<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>优惠券列表</title>
<meta name="Keywords" content="java,shopjava" />
<meta name="description" content="在shop中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch20131008.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch.onebox.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/pop_compare.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>

</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="../commons/header.jsp" />
<!-- header end -->
<div class="w main">
	<div class="crumb">全部结果&nbsp;&gt;&nbsp;<strong>"${query}"</strong></div>
	<div class="clr"></div>
	<div class="m clearfix" id="bottom_pager">
		<div  id="pagin-btm" class="pagin fr" clstag="search|keycount|search|pre-page2">
			<span class="prev-disabled">上一页<b></b></span>
			<a href="javascript:void(0)" class="current">1</a>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=2">2</a>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=3">3</a>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=4">4</a>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=5">5</a>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=6">6</a>
			<span class="text">…</span>
			<a href="search?keyword=java&enc=utf-8&qr=&qrst=UNEXPAND&rt=1&page=2" class="next">下一页<b></b></a>
			<span class="page-skip"><em>&nbsp;&nbsp;共${totalPages}页&nbsp;&nbsp;&nbsp;&nbsp;到第</em></span>
		</div>
	</div>
	<div class="m psearch " id="plist">
		<ul class="list-h clearfix" tpl="2">
			<c:forEach items="${coupons}" var="c">
				<li class="item-book" bookid="11078102">
					<div class="p-img">
						<img width="130" height="120" data-img="1" data-lazyload="${c.img}" />
					</div>
					<div class="p-name" align="center">
							${c.name}
					</div>
					<div class="p-price" align="center">

						<strong  style="margin-left: 100px">￥${c.money }</strong>
					</div>
					<div class="service"  align="center" style="height: 100px;"><button onclick="receive(${c.id})">立即领取</button> </div>
				</li>
			</c:forEach>
		</ul></div>
</div>
<!-- footer start -->
<jsp:include page="../commons/footer.jsp" />
<!-- footer end -->
<script type="text/javascript" src="/js/jquery.hashchange.js"></script>
<script type="text/javascript" src="/js/search_main.js"></script>
<script type="text/javascript">
    function receive(id) {
		$.ajax({
			type:'post',
			url:"/coupon/receive.action",
			data:{
                couponId:id
			},
			dataType:'json',
			success:function (data) {
				alert(data.data);
            }
		})
    }
</script>
</body>
</html>