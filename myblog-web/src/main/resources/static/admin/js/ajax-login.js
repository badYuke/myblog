$(document).ready(function(){
	$("button").click(function(){
		if($("#loginname").val() == "" && $("#loginpswd").val() == ""){
			console.log("username or password is null");
		}else{
			$.ajax({
				type:"POST",
				url:"/loginvalidation",
				data:{username:$("#loginname").val(),password:$("#loginpswd").val()},
				dataType:"json",
				success:function(data){
					if(data == "1"){
						alert("用户名或密码错误！");
					}else if (data == "0"){
						alert("欢迎回来！");
					}
				},
				error:function(e){
					console.log(e);
				}
			});
		}
	});
});