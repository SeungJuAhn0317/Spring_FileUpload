<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 멀티 파일 업로드</title>
<style>
.fileDrop, .uploadedList {
	width: 50%;
	height: 300px;
	border: 1px dotted blue;
	margin: 0 auto;
}

h3{
	text-align: center;
}
small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>
	<input type="button" value="모두삭제"/>
	<div class='uploadedList'></div>
	
<script>
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();		
	});
	
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();	
// 		event.originalEvent는 순수한 원래의 DOM이벤트를 가지고 옮
//		JQuery를 사용할경우  순수한 DOM이벤트가 아님
//		dataTransfer는 이벤트와 같이 전달된 데이터를 의미
//		그 안에 포함된 파일 데이터를 찾아내기 위해 dataTransfer.files
//		를 사용함	
		let files = event.originalEvent.dataTransfer.files;			
// 		let file = files[0];
// 		파일이름을 바로 알아낼수 있음
// 		file.name;
// 		alert(file.name+"입니다")
					
		let formData = new FormData();
		$.each(files,function(index,item){
			formData.append("multiFile", item);
		});				
		
		$.ajax({
			  url: '/ajaxMultiFileUpload/uploadAjax.bbs',
			  data: formData,
//			  복수개를 업로드시 
			  dataType:'json',	
			  type: 'POST',
//			  processData: false는
//			  데이터를 일반적인 query string으로 변활할 것인지를 결정, 기본값은 true , 
//			  'application/x-www-form-urlencoded' 타입으로 전송, 다른 형식으로 데이터를
//			  보내기 위하여 자동 변환하고 싶지 않은 경우는 false로 지정
			  processData: false,
			  
//			  contentType: false 는
//			  기본값은 'application/x-www-form-urlencoded', 파일의 경우 'multipart/form-data'
//			  방식으로 전송하기 위해서 false
			  contentType: false,			 
			  success: function(data){
				  let str ="";				 
				  alert(data);				  
				  $.each(data,function(index, fileName){					  					 
					  if(checkImageType(fileName)){						 
						  str ="<div><a href=displayFile?fileName="+getImageLink(fileName)+">"
								  +"<img src='displayFile?fileName="+fileName+"'/>"	
								  +"</a><small class='human' data-src='"+fileName+"'>X</small></div>";
					  }else{
						  str = "<div><a href='displayFile?fileName="+fileName+"'>" 
								  + getOriginalName(fileName)+"</a>"
								  +"<small class='human' data-src='"+fileName+"'>X</small></div>";
					  }
					  
					  $(".uploadedList").append(str);
				  });				 
			  },
			  error : function(xhr){
					alert("error html = " + xhr.statusText);
			  }			  
			});	
	});
</script>	
		
</body>
</html>