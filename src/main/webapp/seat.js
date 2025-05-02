let btn = document.querySelectorAll(".seat");
let seat_arr = new Array();     //좌석을 선택한 값을 배열로 담는 변수

//querySelector => addEventListener 기준으로 사용가능
//querySelectorAll => forEach => addEventListener 기준으로 사용가능

btn.forEach((a) => {
   //e => Element 의 속성을 리스트를 모두 출력함
   a.addEventListener("click", function(e){
	
	   //예매고객에 대한 좌석배치 제한 조건문
	   if(seat_arr.length >= person && e.target.className == "seat"){
			alert("더 이상 해당 좌석을 선택 할 수 없습니다.");
	   }    
	   else {	
       //기존에 사용되고 있는 클래스 이름을 변경 또는 추가하여 적용하는 코드
       var no = e.target.attributes.data.value;  

       if(e.target.className == "seat"){    //좌석을 선택
            e.target.className = "seat selected";
            seat_arr.push(no);  //좌석을 선택했을 경우만 배열에 값을 추가
       }
       //filter : 해당 배열에서 해당 값을 제외한 배열 값만 출력하는 형태
       else{    //좌석을 해제
            e.target.className = "seat";
            //var redata = seat_arr.filter((a) => a != no);
            
			var redata = seat_arr.filter(function(a){
                return a != no;
            });
			//해당 filter함수를 통해서 변경된 배열값을 기존에 있는 배열값으로 이관
            seat_arr = redata;  
       }
	  }
   });
});

function movie_ok(){
	if(seat_arr.length == 0){
		alert("좌석을 선택해 주셔야 합니다.");
	}
	else{
		frm.seat_data.value = seat_arr;
		frm.submit();
	}
}




