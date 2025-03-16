<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tick.bean.BookticketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>更新訂票</title>
    
    <style>
        body {
            font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        #success_message {
            display: none;
        }

        #datepicker {
            position: relative;
            z-index: 1000000;
        }
        .ud{
        pointer-events: none;
        background: #fff !important;
        }
        html{
        font-size:16px ;
        }
        
    </style>
    <!--[if lt IE 9]>       
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>       
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>     
  <![endif]-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="ui/jquery-ui-1.14.1.custom/jquery-ui.css">
    <!-- <link rel="stylesheet" href="/ui/jquery-ui-1.14.1.custom/jquery-ui.js"> -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.17.2/dist/sweetalert2.min.css">
<script>

</script>
</head>

<body>
    <div class="container">

        <form class="well form-horizontal" action="Updatea" method="post" id="contact_form">
            <fieldset>

                <!-- Form Name -->
                <legend>修改訂票</legend>

                <!-- Text input-->
				<input name="tickid" type="hidden" value="${tickinfo.tickid}">
                <div class="form-group">
                    <label class="col-md-4 control-label">訂單編號</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-clipboard-list"></i></span>
                            <input name="orderid" placeholder="訂單編號" class="form-control ud" type="text" value="${tickinfo.orderid}">
                            
                        </div>
                    </div>
                </div>

                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label">會員</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-user"></i></span>
                            <input name="userid" placeholder="會員" class="form-control ud" type="text" value="${tickinfo.userid }">
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">日期</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-calendar-days"></i></span>
                            <input type="text" id="datepicker" class="form-control" placeholder="日期" value="${tickinfo.startdate}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">場次</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-clock"></i></span>
                            <select name="starttime" class="form-control selectpicker" id="time">
                                <option value=" ">場次</option>
                                
                            </select>
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">廳</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-house"></i></span>
                        	 <select name="hallid" class="form-control selectpicker" id="halls">
                                <option value="">廳</option>
                                

                            </select>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label">座位</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-chair"></i></span>
                            <select name="Seatid" class="form-control selectpicker" id="seat">
                                <option value="">座位</option>
                                

                            </select>
                        </div>
                    </div>
                </div>

                <!-- Text input-->
 				<div class="form-group">
                    <label class="col-md-4 control-label">票種</label>
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-ticket"></i></span>
                            <select name="tickettypeid" class="form-control selectpicker" id="type">
                                <option value=" ">票種</option>
                            </select>
                        </div>
                    </div>
                </div>

                

                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label">電影名稱</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-film"></i></span>
                            
                            <select name="movieid" class="form-control selectpicker" id="moviename">
                                <option value=" ">電影</option>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- Select Basic -->

               
				<div class="form-group">
                    <label class="col-md-4 control-label">單價</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa-solid fa-coins"></i></span>
                            <input name="onemoney" placeholder="單價" class="form-control" type="text" id="money">
                        </div>
                    </div>
                </div>
                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label">是否付款</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                        
                         
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <select name="payout" class="form-control selectpicker">
                                <option value=" ">是否付款</option>
                                <option value="Y" <c:if test="${tickinfo.payout =='Y'}">
                        selected
                        </c:if>>已付</option>
                                <option value="N" <c:if test="${tickinfo.payout =='N'}">
                        selected
                        </c:if>>未付</option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button  class="btn btn-warning" id="seed">Send <span
                                class="glyphicon glyphicon-send"></span></button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
   <!-- /.container -->
    <!-- <script src="https://code.jquery.com/jquery-3.7.1.js"></script> -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
    <!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https//cdnjs.cloudflare.com/ajax/libs/validate.js/0.13.1/validate.min.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="ui/jquery-ui-1.14.1.custom/jquery-ui.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.17.2/dist/sweetalert2.all.min.js"></script>
    <script>

    $(function () {
    	//Swal.fire("SweetAlert2 is working!");
    const $myForm=	$('#contact_form').on("submit",function(e){
    		e.preventDefault();
    		Swal.fire({
    			  title: "要進行修改嗎?",
    			  icon: "warning",
    			  showDenyButton: true,
    			  showCancelButton: true,
    			  confirmButtonText: "修改",
    			  denyButtonText: `我在想想`,
    			  didOpen: () => {
    				    $('#datepicker').prop('disabled', true) // 禁用 datepicker
    				      .css({
    				        'background-color': '#999999',  // 設定與其他輸入框相同的背景色
    				        'color': '#000',  // 讓文字顏色一致
    				        'cursor': 'not-allowed',
    				        'border':'1px solid #7d7d7d'// 讓滑鼠變成禁止點擊的樣式
    				      });
    				  },
    				  didClose: () => {
    				    $('#datepicker').prop('disabled', false) // 恢復可用
    				      .css({
    				        'background-color': '', // 恢復原本的樣式
    				        'color': '',
    				        'cursor': '',
    				        'border':''
    				      });
    				  }
                 // buttonsStyling: false
    			}).then((result) => {
    			  
    			  if (result.isConfirmed) {
    			    //Swal.fire("Saved!", "", "success",customClass: {
                    //    confirmButton: 'btn btn-success'
                  //  })
    		        Swal.fire({
                                icon: 'success',
                                title: '修改成功!!',
                                text: '',
                                customClass: {
                                    confirmButton: 'btn btn-success'
                                    
                                }
                            });
    		        setTimeout(function() {
    		        	$myForm[0].submit()
    		          }, 2000); // submit the DOM form
    		       
    			  } else if (result.isDenied) {
    			    Swal.fire("Changes are not saved", "", "info");
    			  }
    			});
    	})
    	///////////////////////////////////////
    	let urlhttp='http://localhost:8080/project/';
    	//console.log(urlhttp)
/////////////////////////////////////////////////////////////    	
    	$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd'});
        $("#from-datepicker").on("change", function () {
            var fromdate = $(this).val();
            alert(fromdate);
        });
        
        //點選日期改時間
        let urltime = urlhttp+'findshowtime';
        $("#datepicker").on("change",function () {
        	let selectdate=$("#datepicker").val();
        	$("#time").empty();
            $.ajax({
                url: urltime,
                data:{startdate:selectdate},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
                	$('#time').append(`<option value="">場次</option>`);
					$.each(respones,function(i,e){
						
						$('#time').append(`<option value="\${e.showtimeid}">\${e.starttime}</option>`)
					})
                }
            })
    });
        //ready畫面加載預設時間
        
        $(document).ready(function(){
        	let selectdate=$("#datepicker").val();
        	$("#time").empty();
            $.ajax({
                url: urltime,
                data:{startdate:selectdate},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
                	$('#time').append(`<option value="">場次</option>`);
					$.each(respones,function(i,e){
						let tickshow='${tickinfo.showtimeid}'
						//console.log(tickshow)
						let selectshow = (tickshow==e.showtimeid)?'selected':''
						$('#time').append(`<option value="\${e.showtimeid}" \${selectshow} >\${e.starttime}</option>`)
					})
                },
                error:function(){console.log('time')}
            })
        });
        
        
        
        
        
        //
        let urlhall = urlhttp+'findhalls';
        
        $(document).ready(function () {
                $.ajax({
                    url: urlhall,
                    dataType: 'json',
                    type: 'GET',
                    success: function (respones) {
						$.each(respones,function(i,e){
                    	
						    let tickhall = ${tickinfo.hallid} // 直接輸出數值
		                    let selectshows = (tickhall == e.hallid) ? 'selected' : '';
		                    
		                    //console.log(e.hallid);
							$('#halls').append(`<option value="\${e.hallid}" \${selectshows}>\${e.hallid}</option>`)
							$('#moviename').empty();
        	
        	
        	//console.log(hall)
        	
					})
					let hall=$("#halls").val();
					/////
					urlname=urlhttp+"findmoviename"
        	$.ajax({
                url: urlname,
                data:{hallid:hall},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
                	console.log(respones)
                	$('#moviename').empty();
                	$.each(respones,function(i,e){
                		//$('#moviename').append(`<option value="${e.movieid}">${e.moviename}</option>`)
						//$('#moviename').val(e.moviename)
						$('#moviename').append(`<option value="\${e.movieid}">\${e.moviename}</option>`)
						
				})
					
                }
            })
				////	
                }
            })
    });
        let urlseat = urlhttp+'findseatbytimehall'
        $("#halls").on("change",function(){
        	let selecttime=$("#time").val();
        	
        	let selecthall=$("#halls").val();
        	//console.log(selecthall)
        	$("#seat").empty();
        	let soldSeats=[];
        	$.ajax({
                url: urlseat,
                data:{showtimeid:selecttime,hallid:selecthall},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
					$.each(respones,function(i,e){
						
						soldSeats.push(e.seatid);
					})
						let allSeats = [];
        // 生成所有座位 (假設 5 排，每排 5 個座位)
        for (let row of ["A", "B", "C", "D", "E"]) {
            for (let col = 1; col <= 10; col++) {
                allSeats.push(row + col);
            }
        }
        // 取得 <select> 元素
        let select = document.getElementById("seat");
        // 過濾出尚未售出的座位
        let availableSeats = allSeats.filter(seat => !soldSeats.includes(seat));
        // 生成 <option> 並添加到 <select>
        availableSeats.forEach(seat => {
            //let option = document.createElement("option");
            //option.value = seat;
           // option.textContent = seat;
            //select.appendChild(option);
            \$('#seat').append(`<option value="\${seat}">\${seat}</option>`)
			
        })
            	}
        	})
        })
        $(document).ready(function(){
        	let selecttime=$("#time").val();
        	let selecthall=$("#halls").val();
        	$("#seat").empty();
        	let soldSeats=[];
        	$.ajax({
                url: urlseat,
                data:{showtimeid:selecttime,hallid:selecthall},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
					$.each(respones,function(i,e){
						soldSeats.push(e.seatid);
					})
						let allSeats = [];
        // 生成所有座位 (假設 5 排，每排 5 個座位)
        for (let row of ["A", "B", "C", "D", "E"]) {
            for (let col = 1; col <= 10; col++) {
                allSeats.push(row + col);
            }
        }
        // 取得 <select> 元素
        let select = document.getElementById("seat");
        // 過濾出尚未售出的座位
        let availableSeats = allSeats.filter(seat => !soldSeats.includes(seat));
        // 生成 <option> 並添加到 <select>
        availableSeats.forEach( function(seat) {
            //let option = document.createElement("option");
            //option.value = seat;
           // option.textContent = seat;
            //select.appendChild(option);
            let seats='${tickinfo.seatid}'
            //console.log(seat)
            //console.log(urlhttp)
            //console.log(seats)
            let selectse = (seats == seat) ? 'selected':'';
            //console.log(selectse)
            //
            $('#seat').append(`<option value="\${seat}" \${selectse}>\${seat}</option>`)
			
        })
            	},
            	error:function(){console.log('time')}
        	})
        })
        let money=[];
                let urltype = urlhttp+'findtype';
        console.log(urlhall)
        $(document).ready(function () {
                $.ajax({
                    url: urltype,
                    dataType: 'json',
                    type: 'GET',
                    success: function (respones) {
						$.each(respones,function(i,e){
							let ticktyp=${tickinfo.tickettypeid}+1;/////////////////////////////////////
							
							//console.log(ticktyp)
							let se=e.tickettypeid
							let selectty=(ticktyp==se)?'selected':'';
							//console.log(ticktyp)
							//console.log(se)
							//console.log(selectty)
							$('#type').append(`<option value="\${e.tickettypeid}" \${selectty}>\${e.tickettype}</option>`)
							money.push(e.moneytype)
							 let tp=	$('#type').val();
					        let time=$('#time').text();
					        //console.log(time)
					        let mf=0
					        	if(tp==1){
					        		
					        		mf=money[tp-1];
					        		$("#money").val(mf)
					        	console.log()      
					        	}
					        	else if(tp==2){
					        		
					        		mf=money[tp-1];
					        		$("#money").val(mf)
					            	}	
								else if(tp==3){
					        		
									mf=money[tp-1];
									$("#money").val(mf)
					            	}else if(tp==4){
					            		
					            		mf=money[tp-1];
					            		$("#money").val(mf)
					                	}
					})
                }
            })
    });
        $('#type').on("change",function(){
        let tp=	$('#type').val();
        let time=$('#time').text();
        //console.log(time)
        let mf=0
        	if(tp==1){
        		
        		mf=money[tp-1];
        		$("#money").val(mf)
        	console.log()      
        	}
        	else if(tp==2){
        		
        		mf=money[tp-1];
        		$("#money").val(mf)
            	}	
			else if(tp==3){
        		
				mf=money[tp-1];
				$("#money").val(mf)
            	}else if(tp==4){
            		
            		mf=money[tp-1];
            		$("#money").val(mf)
                	}
        })
        
        
        
        $("#halls").on("change",function(){
        	$('#moviename').empty();
        	let hall=$("#halls").val();
        	
        	urlname=urlhttp+"findmoviename"
        	$.ajax({
                url: urlname,
                data:{hallid:hall},
                dataType: 'json',
                type: 'GET',
                success: function (respones) {
                	
                		
                	$.each(respones,function(i,e){
                		//$('#moviename').append(`<option value="${e.movieid}">${e.moviename}</option>`)
						//$('#moviename').val(e.moviename)
						$('#moviename').append(`<option value="\${e.movieid}">\${e.moviename}</option>`)
						
				})
					
                }
            })
        })
        $(document).ready(function(){
        	
            
        })
        
        
        
        
        
        
        
        
        
        
    
    ////////////////////////////////////////////////
    });
        


     

    </script>
</body>

</html>