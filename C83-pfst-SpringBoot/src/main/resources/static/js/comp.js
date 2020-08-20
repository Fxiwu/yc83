Vue.component('page-head',{
	
	template:`
	<div class="container header">
	
<div class="span5">
		<div class="logo">
			<a href="index.html">
				<img src="image/r___________renleipic_01/logo.png" alt="依依不舍"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				
				
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="login.html">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="reg.html">注册</a>|
				</li>
				
				
				
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a href="personal.html">会员中心</a>
							|
						</li>
						<li>
							<a href="guide.html">购物指南</a>
							|
						</li>
						<li>
							<a href="aboutus.html">关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="cart.html">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	


<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="index.html">首页</a>
						|
					</li>
					
					<li>
<a href="clist.html?#1&pageIndex=1">
					女装男装
</a>
					|</li>
					
					<li>
<a href="clist.html?#2&pageIndex=1">
					鞋靴箱包
</a>
					|</li>
					
					<li>
<a href="clist.html?#3&pageIndex=1">
					运动户外
</a>
					|</li>
					
					<li>
<a href="clist.html?#4&pageIndex=1">
					珠宝配饰
</a>
					|</li>
					
					<li>
<a href="clist.html?#5&pageIndex=1">
					手机数码
</a>
					|</li>
					
					<li>
<a href="clist.html?#6&pageIndex=1">
					家电办公
</a>
					|</li>
					
					<li>
<a href="clist.html?#7&pageIndex=1">
					护肤彩妆
</a>
					|</li>
							
		</ul>
	</div>


</div>`
});
Vue.component('page-foot',{
	template:`<div class="container footer">
 
	<div class="span24">
		<div class="footerAd">
					<img src="image/r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>`
	 
});