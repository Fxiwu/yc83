 /*封装的ajax 方法*/
      function post(url,param,callback){
    	   var xhr;
          try{
        	  xhr=new XMLHttpRequest();
          }catch(e){
        	  xhr=new ActiveXObject("Mircosoft.XMLHttp");
          }
          xhr.onreadystatechange=function(){
        	  if(xhr.readyState==4&&xhr.status==200){
        		callback(xhr.responseText);
        	  }
          }
          
          xhr.open("POST",url);//开启连接  第三个参数true 异步 false同步 默认异步
          //如果是文件上传方式，请求不要设置Context-type,因为太复杂
          if(!(param instanceof FormData)){
        	  //POST提交数据的编码方式
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");

          }
          xhr.send(param);//发送请求参数 
   
      }