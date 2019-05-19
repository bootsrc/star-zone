var vm = new Vue({
    el: '#app',
    data: function() {
        var item = {
            date: '2016-05-02',
            label: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
        };
        return {
            isCollapse: false,
            tableData: Array(10).fill(item)
        }
    },methods:{
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        logout(){
            console.log("logout")
            axios.post(ctxPath + 'logout',{}).then(function (response) {
                console.log(response.data);
            }).catch(function (error) {
                console.log(error);
            })
            axios.post(ctxPath + 'ssoserver/exit',{}).then(function (response) {
                console.log(response.data);
            }).catch(function (error) {
                console.log(error);
            });
        }
    }
})