<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 

<title>Array老师登录</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/login/css/styles.css">
 <link rel="stylesheet" href="<%=basePath%>/login/css/reset.css">
 <link rel="stylesheet" href="<%=basePath%>/login/css/supersized.css">
 <link rel="stylesheet" href="<%=basePath%>/login/css/style.css">
<script src="<%=basePath%>/login/js/jquery-1.8.3/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/login/js/supersized.3.2.7.min.js"></script>
</head>

<body>
<div class="htmleaf-container">
	<div class="wrapper"> 
		<div class="container">
			<h1>欢迎登录阿里巴巴内部平台</h1>
			
			<form class="form"   id="formid">
				<div><input type="text" placeholder="请输入登录名" name="username"></div>
				<div><input type="password" placeholder="请输入密码"  name="password" class="password"></div>
				  
				 <div >
				 <input type="text" placeholder="请输入验证码"  name="nsum" onblur="dock()">
				 <div id="code" style="float: left"></div>
				 <a href="">看不清？请点我</a><img alt="" src="getCkCOne.do">
			     </div>
				   
				  <button id="submit" type="submit">Login</button>
			</form>
		</div>
		
		 
	</div>
</div>

<div class="msg" style="display:none">
			<h2>消息</h2>
			<div class="msgp">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
</div>

 
<script>
		$(".btn").click(function(){
			is_hide();
		})
		var u = $("input[name=username]");
		var p = $("input[name=password]");
		$("#submit").live('click',function(){
			if(u.val() == '' || p.val() =='')
			{
				$("#ts").html("用户名或密码不能为空~");
				is_show();
				return false;
			}
			else{
				var reg = /^[0-9A-Za-z]+$/;
				if(!reg.exec(u.val()))
				{
					$("#ts").html("用户名错误");
					is_show();
					return false;
				}
			}
		});
		function is_hide(){ $(".msg").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".msg").show().animate({"top":"45%"}, 300) }
		</script>
		 <script type="text/javascript">
          function dock(){
           $.ajax({
            type : "post",  
         	async : true,    
         	url : "<%=basePath%>dock.do",    
        	data : $('#formid').serialize(),
         	dataType : "text",
         	success : function(result) {   
         	        
	              if (result=="yes") { 
	              	//alert("验证码正确！");
	              	//document.getElementById('code').innerHTML="验证码正确！";
	              	$("#code").text("验证码正确！");
	              } else {
	            	  $("#code").text("验证码错误！");
	            	 //alert("验证码错误！");
	              }
              }
           });
          }
        </script>
       
       <script type="text/javascript">
         function dock(){
        	 $.ajax({
        		type:"post",
        		url:"doCKC.do",
        		data:$('#formid').serialize(),
        		dataType:"text",
        		success:function(data) {
        			if(data=="yes") {
        				$("#code").text("验证码正确！");
        			} else {
        				
        				$("#code").text("验证码错误！");
        			}
        		}	 
        		 
        	 });
         }
       
       
       </script>
    </body>

</body>
</html>
