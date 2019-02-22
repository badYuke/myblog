function frame(a){
	if (document.getElementById(a).style.display !== 'none')
    {
        document.getElementById(a).style.display = 'none';
    }
    else
    {
        document.getElementById(a).style.display = '';
    }
}
function submit(){
	alert("提交失败：java.lang.NullPointerException");
}

function reply(){
	alret("回复成功，等待管理员审核！");
}