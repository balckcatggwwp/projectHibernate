<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                width: 1660px;
                height: 100px;
                /* padding-left: 500px; */
                float: right;


            }

            * {
                margin: 0px;
            }

            li {
                list-style-type: none;
                /* position: fixed; */
                font-size: 50px;
                /* padding-bottom: 10px; */
                /* padding-right: 10px; */
                padding-left: 75px;
                margin-top: 10px;
                user-select: none;
                cursor: pointer;
            }

            li:hover {
                font-size: 70px;
                background-color: #FFCC00;
                padding-left: 10px;
                color: #000;
            }

            li:active {
                position: relative;
                top: 8px;
                cursor: pointer;
                background-color: #B22222;
                color: #FFF;
            }

            figure {
                float: left;
                background-color: #000000;
                padding-bottom: 300px;
                /* padding-right: 100px; */
                font-size: 100px;
                color: #FFD700
            }

            .one {
                margin-top: 100px;
            }

            ul {
                padding-left: 0;
                padding-bottom: 100px;
                /* margin-right: 100px; */
                /* font-size: 100px; */
            }



            .clock {
                /* position: absolute; */
                /* top: 50%;
            left: 50%; */
                /* transform: translateX(-50%) translateY(-50%); */
                color: #FFD700;
                font-size: 30px;
                /* font-family: Orbitron; */
                letter-spacing: 7px;
                padding-top: 10px;


            }
        </style>
        <title>Document</title>
    </head>

    <body>
        <figure>


            <ul>
                <div id="MyClockDisplay" class="clock" onload="showTime()"></div>
                <li class="one"><i class="fa-solid fa-house"></i></li>
                <li class="two"><i class="fa-solid fa-ticket"></i></li>
                <li class="three"><i class="fa-solid fa-clock"></i></li>
                <li class="fort"><i class="fa-solid fa-film"></i></li>
                <li class="five"><i class="fa-solid fa-utensils"></i></li>
                <li class="six"><i class="fa-solid fa-bullhorn"></i></li>
            </ul>
        </figure>
        <!-- -------------------------------------------------------------------------------------------- -->
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
                        

                    </tr>

                </thead>
                <tbody>
                    <tr>
                        <td>${tickinfo.tickid}</td>
                        <td>${tickinfo.orderid}</td>
                        <td>${tickinfo.userid}</td>
                        <td>${tickinfo.startdate}</td>
                        <td>${tickinfo.starttime}</td>
                        <td>${tickinfo.seatid}</td>
                        <td>${tickinfo.hallid}</td>
                        <td>${tickinfo.onemoney}</td>
                        <td>${tickinfo.moviename}</td>
                        <td>${tickinfo.booktype}</td>
                        <td>${tickinfo.payout}</td>
                    </tr>


                </tbody>
            </table>
        </div>



        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.2.2/js/dataTables.min.js"></script>

        <script>
            $(function () {

                $(document).ready(function () {
                    $('#myTable').DataTable();
                })





            })



            document.addEventListener('DOMContentLoaded', function () {
                let elem = document.querySelectorAll("li");
                elem.forEach(function (v) {
                    v.addEventListener('mouseenter', function () {

                        if (v.classList == "one") {

                            v.innerHTML = `<i class="fa-solid fa-house"></i>會員`
                        } else if (v.classList == "two") {

                            v.innerHTML = `<i class="fa-solid fa-ticket"></i>訂票`
                        } else if (v.classList == "three") {

                            v.innerHTML = `<i class="fa-solid fa-clock"></i>場次`
                        } else if (v.classList == "fort") {

                            v.innerHTML = `<i class="fa-solid fa-film"></i>電影`
                        } else if (v.classList == "five") {

                            v.innerHTML = `<i class="fa-solid fa-utensils"></i>訂餐`
                        } else if (v.classList == "six") {

                            v.innerHTML = `<i class="fa-solid fa-bullhorn"></i>公告`
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

                            v.innerHTML = `<i class="fa-solid fa-ticket"></i>  `
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