//고객의 고유번호 난수 생성 코드
var w = 0;
let usercode = "";
while(w < 4){
 usercode += Math.floor(Math.random()*9);
 w++;
}
frm.usercode.value = usercode;

//오늘날짜를 자동으로 입력하는 script 코드
frm.mdate.value = new Date().toISOString().substring(0,10);

var reserve = () => {	
    if(frm.mname.value==""){
		alert("고객명을 입력하세요");
		return false;
	}
	else if(frm.mtime.value==""){
		alert("예매시간을 선택해 주세요");
		return false;
	}
	else{
		frm.submit();
	}
}