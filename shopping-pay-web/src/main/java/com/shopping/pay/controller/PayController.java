package com.shopping.pay.controller;

import com.shopping.coupon.service.CouponService;
import com.shopping.order.service.OrderService;
import com.shopping.pay.utils.PaymentUtil ;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CouponService couponService;
	/**
	 * 进行支付
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(String pdFrpId,String orderId,String payment,Model model){
//		payment = "0.01";
		String p0_Cmd = "Buy";		//业务类型
		String p1_MerId = "10001126856";	//商户编号(我们用的是易宝支付测试的商户编号，也就是意味各位支付钱支付给易宝支付的测试账号了)
		String p2_Order = orderId;	//商户订单号。为 ””时，易宝支付会自动生成随机的商户订单号
		String p3_Amt = payment;		// 支付金额(单位:元，精确到分。我们为了减少成本，所以每次1分钱)
		String p4_Cur = "CNY";	//交易币种
		String p5_Pid = "";	//商品名称(可以为空，此参数如用到中文，请注意转码.)
		String p6_Pcat = "";//商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = "http://localhost:8107/success.html";	//商品支付成功后重定向的地址
		String p9_SAF = "0";	//送货地址.为“1”: 需要用户将送货地址留在易宝支付系统;为“0”: 不需要，默认为 ”0”.
		String pa_MP = "";	//商户扩展信息
		String pd_FrpId = pdFrpId;	//支付通道编码(选择要支付的银行卡的类型)
		String pr_NeedResponse = "1"; //应答机制
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";	//商户密钥(使用易宝支付测试密钥)
		//生成hmac
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		//----------------跳转到确定支付的页面(在点击后就直接进行支付，所以得讲这些参数传递到确认页面)------------------------
		model.addAttribute("p0_Cmd", p0_Cmd);
		model.addAttribute("p1_MerId", p1_MerId);
		model.addAttribute("p2_Order", p2_Order);
		model.addAttribute("p3_Amt", p3_Amt);
		model.addAttribute("p4_Cur", p4_Cur);
		model.addAttribute("p5_Pid", p5_Pid);
		model.addAttribute("p6_Pcat", p6_Pcat);
		model.addAttribute("p7_Pdesc", p7_Pdesc);
		model.addAttribute("p8_Url", p8_Url);
		model.addAttribute("p9_SAF", p9_SAF);
		model.addAttribute("pa_MP", pa_MP);
		model.addAttribute("pd_FrpId", pd_FrpId);
		model.addAttribute("pr_NeedResponse", pr_NeedResponse);
		model.addAttribute("hmac", hmac);
		return "confirmpay";
	}
	
	/**
	 * 进入到选择银行卡界面
	 * @return
	 */
	@RequestMapping("/success")
	public String success(String r3_Amt,String r6_Order,Model model){
		//修改订单支付状态
		orderService.updateOrderPay(r6_Order);
		model.addAttribute("payment", r3_Amt);	//支付成功的金额
		model.addAttribute("orderId", r6_Order);	//订单编号
		//修改优惠券使用状态
		couponService.updateLog(r6_Order);
		//预计送达时间是三天后
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plusDays(3);
		model.addAttribute("date",dateTime.toString("yyyy-MM-dd"));
		return "success";
	}

}
