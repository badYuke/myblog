var nameboolean = false;
var paswdboolean = false;
var paswd2boolean = false;
$("#loginbtn").attr("disabled", true);
$("#registername").blur(
		function() {
			var name = this.value;
			var tips = $("#namehint");
			if (name.length == 0) {
				tips.css("color", "red");
				tips.html("*账号不能为空");
				nameboolean = false;
			} else {
				var ajax = createAjax();
				function createAjax() {
					var ajax = null;
					try {
						ajax = new ActiveXObject("microsoft.xmlhttp");
					} catch (e) {
						ajax = new XMLHttpRequest();
					}
					return ajax;
				}
				ajax.open("POST", "/registerajax");
				ajax.setRequestHeader("content-type",
						"application/x-www-form-urlencoded");
				ajax.send("username=" + name);
				ajax.onreadystatechange = function() {
					if (ajax.readyState === 4 && ajax.status === 200) {
						if (ajax.responseText == "1") {
							tips.css("color", "red");
							tips.html("* 账号已被占用!");
							nameboolean = false;
						}
						if (ajax.responseText == "-1") {
							tips.css("color", "red");
							tips.html("* 账号不能含有特殊字符!");
							nameboolean = false;
						}
						if (ajax.responseText == "0") {
							tips.css("color", "green");
							tips.html("* 恭喜你,帐号可以使用!");
							nameboolean = true;
							check();
						}
					}
				}
			}
		});
$("#registerpswd").blur(function() {
	var password = this.value;
	var tips = $("#pswdhint");
	if (password.length == 0) {
		tips.css("color", "red");
		tips.html("*密码不能为空");
		paswdboolean = false;
	} else {
		tips.html("");
		paswdboolean = true;
		check();
	}
});
$("#registerpswd2").blur(function() {
	var password2 = this.value;
	var password = $("#registerpswd").val();
	var tips = $("#pswd2hint");
	if (password2.length == 0) {
		tips.css("color", "red");
		tips.html("*密码不能为空");
		paswd2boolean = false;
	} else if (password2 != password) {
		tips.css("color", "red");
		tips.html("*两次密码不一致!");
		paswd2boolean = false;
	} else {
		tips.html("");
		paswd2boolean = true;
		check();
	}
});
function check(){
		if(nameboolean && paswdboolean && paswd2boolean){
			$("#loginbtn").attr("disabled", false);
		}
}