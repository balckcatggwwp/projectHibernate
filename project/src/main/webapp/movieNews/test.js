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