<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <meta http-equiv="pragma" content="no-cache" />
   <meta http-equiv="cache-control" content="no-cache" />
   <meta http-equiv="expires" content="0" /> 
   <meta name="format-detection" content="telephone=no" />  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
   <meta name="format-detection" content="telephone=no" />
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <link type="text/css" rel="stylesheet" href="/css/base.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.base.2012.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
   <title>确认支付页面 - 淘淘商城</title>
   <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
   <script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
   <script type="text/javascript" src="/js/jquery.cookie.js" charset="utf-8"></script>
   <script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
</head> <body id="mainframe">
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w" id="headers">
    <div id="logo"><a href="/"><img alt="淘淘商城" src="/images/taotao-logo.gif"></a></div>
    <ul class="step" id="step3">
        <li class="fore1">1.我的购物车<b></b></li>
        <li class="fore2">2.填写核对订单信息<b></b></li>
        <li class="fore3">3.成功提交订单</li>
    </ul>
    <div class="clr"></div>
</div>
<div class="w" id="safeinfo"></div><!--父订单的ID-->
<div class="w main">
    <div class="m m3 msop">
        <div class="mc" id="success_detail">
            <ul class="list-order">
                <li class="li-st">
                    <div class="fore1">订单号：<a href="javascript:void(0)">${p2_Order }</a></div>
                    <!-- 货到付款 -->
                    <div class="fore2">需要支付：<strong class="ftx-01">${p3_Amt}元</strong></div>
                </li>
            </ul>
        </div>
        <form action="https://www.yeepay.com/app-merchant-proxy/node" method="post">
            <input type="hidden" name="p0_Cmd" value="${p0_Cmd }"/>
            <input type="hidden" name="p1_MerId" value="${p1_MerId }"/>
            <input type="hidden" name="p2_Order" value="${p2_Order }"/>
            <input type="hidden" name="p3_Amt" value="${p3_Amt }"/>
            <input type="hidden" name="p4_Cur" value="${p4_Cur }"/>
            <input type="hidden" name="p5_Pid" value="${p5_Pid }"/>
            <input type="hidden" name="p6_Pcat" value="${p6_Pcat }"/>
            <input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }"/>
            <input type="hidden" name="p8_Url" value="${p8_Url }"/>
            <input type="hidden" name="p9_SAF" value="${p9_SAF }"/>
            <input type="hidden" name="pa_MP" value="${pa_MP }"/>
            <input type="hidden" name="pd_FrpId" value="${pd_FrpId }"/>
            <input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }"/>
            <input type="hidden" name="hmac" value="${hmac }"/>
            <p>
                <input type="submit" value="确认支付"/>
            </p>
        </form>
    </div>
</div>
  <div class="w">
	<!-- links start -->
    <jsp:include page="commons/footer-links.jsp"></jsp:include>
    <!-- links end -->
</div><!-- footer end -->
     </body> 
</html>