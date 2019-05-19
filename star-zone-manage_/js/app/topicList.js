'use strict';

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var vm = new Vue({
    el: '#app',
    data: function data() {
        var item = {
            date: '2016-05-02',
            label: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
        };

        return {
            base: '',
            total: 0,
            totalPages: 0,
            isCollapse: false,
            tableData: [],
            currentRow: null,
            pageForm: {
                pageNo: 0,
                pageSize: 10
            },
            addForm: {
                topicId: 0,
                title: '',
                introduction: '',
                content: '',
                status: 1,
                img: '',
                createTime: null,
                updateTime: null,
                serial: 100
            },
            dialogFormVisible: false,
            formLabelWidth: '120px',
            idVisible: false,
            uploadAction: ctxPath + "/resourceapi/upload?creater=admin",
            fileList: []
        };
    },
    methods: _defineProperty({
        handleUploadSuccess: function handleUploadSuccess(res, file) {
            vm.addForm.img = res.url;
        },
        handlePreview: function handlePreview() {},
        handleRemove: function handleRemove() {},
        beforeRemove: function beforeRemove() {},
        handleExceed: function handleExceed() {},
        getTable: function getTable() {
            axios.post(ctxPath + '/topicManage/getTable', vm.pageForm).then(function (response) {
                console.log(response);
                vm.tableData = response.data.data;
                vm.total = response.data.count;
            }).catch(function (error) {
                console.log(error);
            });
        },
        openAddDialog: function openAddDialog() {
            vm.addForm.topicId = 0;
            vm.addForm.title = '';
            vm.addForm.content = '';
            vm.addForm.status = 1;
            vm.addForm.img = '';
            vm.addForm.createTime = null;
            vm.addForm.updateTime = null;
            vm.addForm.serial = 100;
            vm.dialogFormVisible = true;
        },
        saveTopic: function saveTopic() {
            axios.post(ctxPath + '/topicManage/save', vm.addForm).then(function (response) {
                console.log(response);
                // window.location.href = ctxPath + 'blackList';
                vm.getTable();
                vm.dialogFormVisible = false;
            }).catch(function (error) {
                console.log(error);
                vm.dialogFormVisible = false;
            });
        },
        handleCurrentChange: function handleCurrentChange(row) {
            this.currentRow = row;
        },
        onSubmit: function onSubmit() {
            this.getTable();
        },
        delTopic: function delTopic(row) {
            console.log(row);
            this.$confirm('此操作将删除话题"' + row.title + '", 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function (_ref) {
                var value = _ref.value;

                axios.post(ctxPath + '/topicManage/delete', row).then(function (response) {
                    vm.getTable();
                }).catch(function (error) {
                    console.log(error);
                });
            }).catch(function () {});
        },
        openEditDialog: function openEditDialog(row) {
            vm.addForm = row;
            vm.dialogFormVisible = true;
        },
        handleOpen: function handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose: function handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleSizeChange: function handleSizeChange(val) {
            console.log("每页" + val + "条");
        }
    }, 'handleCurrentChange', function handleCurrentChange(val) {
        console.log('当前页: ${val}');

        this.pageForm.pageNo = val;
        this.getTable();
    })
});

console.log(vm.base);

vm.getTable();

//# sourceMappingURL=topicList.js.map