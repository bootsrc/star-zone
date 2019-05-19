var AppjishuUtil = {
    sleep: function (milliSeconds) {
        var startTime = new Date().getTime(); // get the current time
        while (new Date().getTime() < startTime + milliSeconds) ; // hog cpu
    },
    formatDate: function (value) {
        var dateTime = new Date(value);
        var dateStr = dateTime.getFullYear() + "-"
            + (dateTime.getMonth() + 1) + "-"
            + dateTime.getDate() + ' ' +
            dateTime.getHours() + ':' + dateTime.getMinutes() + ':'
            + dateTime.getSeconds();
        return dateStr;
    },
    formatDate: function (timestamp, fmt) {
        var thisDate = new Date(timestamp);
        var o = {
            "M+": thisDate.getMonth() + 1,                 //月份
            "d+": thisDate.getDate(),                    //日
            "h+": thisDate.getHours(),                   //小时
            "m+": thisDate.getMinutes(),                 //分
            "s+": thisDate.getSeconds(),                 //秒
            "q+": Math.floor((thisDate.getMonth() + 3) / 3), //季度
            "S": thisDate.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (thisDate.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                if (fmt && fmt.replace){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
        }
        return fmt;
    }
};



