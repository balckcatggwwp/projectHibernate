<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tick.bean.BookticketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.2.2/css/dataTables.dataTables.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
            integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
        #bodddd {
            width: 90%;
            height: 100px;
            float: right;
            
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        li {
            list-style: none;
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 18px;
            color: #FFD700;
            background-color: #222;
            width: 80%;
            height: 60px;
            margin: 5px 0;
            margin-left: 10%;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
            justify-content: center;
        }

        li:hover {
            background-color: #FFCC00;
            color: #000;
            transform: scale(1.1);
        }

        li i {
            font-size: 24px;
            transition: all 0.3s ease;
        }

        li:active {
            position: relative;
            top: 2px;
            background-color: #B22222;
            color: #FFF;
        }

        figure {
            float: left;
            background-color: #000;
            padding: 20px 0;
            font-size: 100px;
            color: #FFD700;
            width: 10%;
            height: 1000px;
            text-align: center;
        }

        .one {
            margin-top: 100px;
        }

        ul {
            display: flex;
            flex-direction: column;
            gap: 10px;
            padding-left: 0;
        }

        li a {
            display: block;
            width: 100%;
            padding: 10px;
            text-decoration: none;
            color: black;

        }

        li a:active {
            position: relative;
            top: 2px;
            background-color: transparent;
            color: white;
        }

        .clock {
            color: #FFD700;
            font-size: 20%;
            letter-spacing: 7px;
            padding-top: 10px;
        }

        #new {
            font-size: 30px
        }

        #new:hover {
            background-color: yellow;
        }

        body {
            background-color: #9fa19d;
            font-family: Arial, sans-serif;
        }
        a{
            text-decoration: none;
        
        }
        
		.header {
		  font-family: "Orbitron", sans-serif;
		  font-optical-sizing: auto;
		
		}
        .header {
        font-family: "Orbitron", sans-serif;
			border: 2px double #FFD700;
            font-size: 50px;
            background: #000;
           color:#fff;
            text-align: center;
            padding: 20px;
            text-shadow:0.1em 0.1em 0.2em #fff;
         }
         .h1{
         
         text-shadow:0.1em 0.1em 0.2em gray;
         }
         
.myButton {
	box-shadow: 3px 4px 0px 0px #1564ad;
	background:linear-gradient(to bottom, #79bbff 5%, #378de5 100%);
	background-color:#79bbff;
	border-radius:5px;
	border:1px solid #337bc4;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:10px;
	font-weight:bold;
	padding:8.5px 29px;
	text-decoration:none;
	text-shadow:0px 0px 0px #528ecc;
}
.myButton:hover {
	background:linear-gradient(to bottom, #378de5 5%, #79bbff 100%);
	background-color:#378de5;
}
.myButton:active {
	position:relative;
	top:1px;
}
#new {
	box-shadow: 0px 10px 14px -7px #3e7327;
	background:linear-gradient(to bottom, #77b55a 5%, #72b352 100%);
	background-color:#77b55a;
	border-radius:4px;
	border:1px solid #4b8f29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:22px;
	font-weight:bold;
	padding:6px 12px;
	text-decoration:none;
	text-shadow:0px 1px 0px #5b8a3c;
}
#new:hover {
	background:linear-gradient(to bottom, #72b352 5%, #77b55a 100%);
	background-color:#72b352;
}
#new:active {
	position:relative;
	top:1px;
}
#dell {
	box-shadow:inset 0px 1px 0px 0px #f5978e;
	background:linear-gradient(to bottom, #f24537 5%, #c62d1f 100%);
	background-color:#f24537;
	border-radius:6px;
	border:1px solid #d02718;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #810e05;
}
#dell:hover {
	background:linear-gradient(to bottom, #c62d1f 5%, #f24537 100%);
	background-color:#c62d1f;
}
#dell:active {
	position:relative;
	top:1px;
}
#upda {
	box-shadow:inset 0px 1px 3px 0px #91b8b3;
	background:linear-gradient(to bottom, #768d87 5%, #6c7c7c 100%);
	background-color:#768d87;
	border-radius:5px;
	border:1px solid #566963;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px -1px 0px #2b665e;
}
#upda:hover {
	background:linear-gradient(to bottom, #6c7c7c 5%, #768d87 100%);
	background-color:#6c7c7c;
}
#upda:active {
	position:relative;
	top:1px;
}
.form-controlaa {
   
    width: 13%;
    height: 34px;
    padding: 0;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
#tise{
font-size:20px;
font-weight: 800
}
input{
  padding: 10px;
  
  border : solid .1px black;
  border-radius: 35px;
  background-color :  #fff;
  
  outline:none;
}

        </style>
       
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400..900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
  		<!--link rel="stylesheet" href="/resources/demos/style.css"-->
  		  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.17.2/dist/sweetalert2.min.css">
  		  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  		<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
  		
  		
        <title>訂票後台</title>
    </head>

    <body>
     <div class="header">光影之門後台系統MovieTicket</div>
        <figure>


            <ul>
                <div id="MyClockDisplay" class="clock" onload="showTime()"></div>
                <li class="one"><i class="fa-solid fa-house"></i></li>
                <!--  <li class="two"><i class="fa-solid fa-ticket"></i></li>-->
                <!-- <li class="three"><i class="fa-solid fa-clock"></i></li> -->
                <li class="fort"><i class="fa-solid fa-film"></i></li>
                <li class="five"><i class="fa-solid fa-utensils"></i></li>
                <li class="six"><i class="fa-solid fa-bullhorn"></i></li>
            </ul>
        </figure>
        <!-- -------------------------------------------------------------------------------------------- -->
        <div id="selectfind">
       
        <form action="findticketbyOther">
         <span id="tise">選擇你要查詢的內容</span>
        	<select id="findtick" name="select"	class="form-controlaa">
            	<option value="">選項</option>
            	<option value="1">訂票ID</option>
            	<option value="2">訂單編號</option>
            	<option value="3">會員ID</option>
            	<option value="4">日期</option>
            	<option value="5">廳</option>
            	<option value="6">電影名稱</option>
            	<option value="7">是否付款</option>
            </select>
            
            <button id="ok" type="submit" class="myButton">送出</button>
            <a href="http://localhost:8080/project/findticketAll" class="myButton">查全部</a>
            <div id="fromfind">
            </div>
            </form>
         </div>
        
        <div id="bodddd">
        	
            <table id="myTable" class="display">
            
                <thead>
                
                    <tr>
                        <th>訂票ID</th>
                        <th>訂單編號</th>
                        <th>會員ID</th>
                        <th>日期</th>
                        <th>時間</th>
                        <th>座位</th>
                        <th>廳</th>
                        <th>單價</th>
                        <th>電影名稱</th>
                        <th>票種</th>
                        <th>是否付款</th>
                        <th>修改</th>
						<th>刪除</th>
                    </tr>

                </thead>
                <tbody>
				<% //List<BookticketBean> ticks =(ArrayList<BookticketBean>)request.getAttribute("tickinfo");
					//for(BookticketBean tickinfos:ticks){%>
					<c:forEach items="${tickinfo}" var="tickinfos" varStatus="s">
                    <tr>
                        <td>${tickinfos.tickid}</td>
                        <td>${tickinfos.orderid}</td>
                        <td>${tickinfos.userid}</td>
                        <td>${tickinfos.startdate}</td>
                        <td>${tickinfos.starttime}</td>
                        <td>${tickinfos.seatid}</td>
                        <td>${tickinfos.hallid}</td>
                        <td>${tickinfos.onemoney}</td>
                        <td>${tickinfos.moviename}</td>
                        <td>${tickinfos.booktype}</td>
                        <td>${tickinfos.payout}</td>
                        <td><a href="http://localhost:8080/project/Getupdateticketbyid?tickid=${ tickinfos.tickid}" id="upda">修改</a></td>
                        <td><a class="dellc" id="dell" data-value="${tickinfos.tickid}">刪除</a></td>
                    </tr>
                    </c:forEach>
				<%//} %>

                </tbody>
            </table>
		<a href="http://localhost:8080/project/proinser/inserform.html" id="new">新增</a>
        </div>
       	


        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.2.2/js/dataTables.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
        <script>
        
            $(function () {
            	
            	$('.dellc').click(function(){
            	let idtick= $(this).data("value")
            	
            		Swal.fire({
            		  title: "你確定要刪除?",
            		  text: "資料會從這個世界上消失！",
            		  icon: "warning",
            		  showCancelButton: true,
            		  confirmButtonColor: "#3085d6",
            		  cancelButtonColor: "#d33",
            		  confirmButtonText: "是，這筆資料不重要!"
            		}).then((result) => {
            		  if (result.isConfirmed) {
            		    Swal.fire({
            		      title: "刪除成功!",
            		      text: "以已消失的無影無蹤了!!",
            		      icon: "success"
            		    });
            		  // console.log(idtick)
            		  setTimeout(function() {
            		    window.location.href ="http://localhost:8080/project/dele?tickid="+idtick
    		        	
    		          }, 2000);
            		  }
            		});
            	})
            	
            	
            	
            	//////
            	let urlhttp='http://localhost:8080/project/';
            	///////////////////
                $(document).ready(function () {
                    $('#myTable').DataTable();
                })
/////////////////////////////////////////////////////////////
				$('#findtick').on("change",function(){
					//console.log($('#findtick').val())
					 $('#fromfind').empty();
					 if($('#findtick').val()==1){
						console.log(1)
						//
						$('#fromfind').append(`<input placeholder="請輸入訂票ID" name="tickid">`)
					}else if($('#findtick').val()==2){
						console.log(2)

						$('#fromfind').append(`<input placeholder="請輸入訂票編號" name="orderid">`)
					}else if($('#findtick').val()==3){
						console.log(3)
						$('#fromfind').append(`<input placeholder="請輸入會員ID" name="userid">`)
					}else if($('#findtick').val()==4){
						console.log(4)
						$('#fromfind').append(`<p><input type="date" placeholder="請選擇日期" name="startdate" id="date1"></p>`)
						
					}else if($('#findtick').val()==5){
						console.log(5)
						$('#fromfind').append(`<input placeholder="請輸入廳" name="hallid">`)
					}else if($('#findtick').val()==6){
						console.log(6)
						$('#fromfind').append(`<select id="selname" name="findname" class="form-controlaa"></select>`)
						urlname=urlhttp+"findmovienameall"
						$.ajax({
                					url: urlname,
                					dataType: 'json',
                					type: 'GET',
                					success: function (respones) {
                	
                		
				                	$.each(respones,function(i,e){
				                		//$('#moviename').append(`<option value="${e.movieid}">${e.moviename}</option>`)
										//$('#moviename').val(e.moviename)
										$('#selname').append(`<option value="\${e.movieid}">\${e.moviename}</option>`)
						
									})
										
					                }
           				 })
						
					}else if($('#findtick').val()==7){
						console.log(7)
						$('#fromfind').append(`<select name="payout" class="form-controlaa">
												<option value="Y">已付</option>
												<option value="N">未付</option>
											   </select>`)
					}
					
				})
					


///////////////////////////////////////////////
            })



            document.addEventListener('DOMContentLoaded', function () {
                let elem = document.querySelectorAll("li");
                elem.forEach(function (v) {
                    v.addEventListener('mouseenter', function () {

                        if (v.classList == "one") {

                            v.innerHTML = `<a href="http://localhost:8080/project/backEnd.view/HomePage.html"><i class="fa-solid fa-house"></i>會員</a>`
                        } else if (v.classList == "two") {

                            v.innerHTML = `<a href=""><i class="fa-solid fa-ticket"></i>訂票</a>`
                        } else if (v.classList == "three") {

                           // v.innerHTML = `<a href="https://www.youtube.com/"><i class="fa-solid fa-clock"></i>場次</a>`
                        } else if (v.classList == "fort") {

                            v.innerHTML = `<a href="http://localhost:8080/project/JSP-zh/select.html"><i class="fa-solid fa-film"></i>電影</a>`
                        } else if (v.classList == "five") {

                            v.innerHTML = `<a href="http://localhost:8080/project/hw2/menupage.html"><i class="fa-solid fa-utensils"></i>訂餐</a>`
                        } else if (v.classList == "six") {

                            v.innerHTML = `<a href="http://localhost:8080/project/GetAllNews"><i class="fa-solid fa-bullhorn"></i>公告</a>`
                        }

                    })

                    //滑鼠移出時恢復背景顏色
                    v.addEventListener('mouseleave', function () {
                        // this.style.fontSize = '';
                        if (v.classList == "one") {
                            console.log("aaa");
                            v.innerHTML = `<i class="fa-solid fa-house"></i>`
                        } if (v.classList == "one") {

                            v.innerHTML = `<i class="fa-solid fa-house"></i>  `
                        } else if (v.classList == "two") {

                           // v.innerHTML = `<i class="fa-solid fa-ticket"></i>  `
                        } else if (v.classList == "three") {

                            v.innerHTML = `<i class="fa-solid fa-clock"></i>  `
                        } else if (v.classList == "fort") {

                            v.innerHTML = `<i class="fa-solid fa-film"></i>  `
                        } else if (v.classList == "five") {

                            v.innerHTML = `<i class="fa-solid fa-utensils"></i>  `
                        } else if (v.classList == "six") {

                            v.innerHTML = `<i class="fa-solid fa-bullhorn"></i>  `
                        }
                    })
                })
                //滑鼠移入時改變背景顏色（藍）

            })


            function showTime() {
                var date = new Date();
                var h = date.getHours(); // 0 - 23
                var m = date.getMinutes(); // 0 - 59
                var s = date.getSeconds(); // 0 - 59
                var session = "AM";

                if (h == 0) {
                    h = 12;
                }

                if (h > 12) {
                    h = h - 12;
                    session = "PM";
                }

                h = (h < 10) ? "0" + h : h;
                m = (m < 10) ? "0" + m : m;
                s = (s < 10) ? "0" + s : s;

                var time = h + ":" + m + ":" + s + " " + session;
                document.getElementById("MyClockDisplay").innerText = time;
                document.getElementById("MyClockDisplay").textContent = time;

                setTimeout(showTime, 1000);

            }

            showTime();
            
            
            
        </script>
    </body>

    </html>