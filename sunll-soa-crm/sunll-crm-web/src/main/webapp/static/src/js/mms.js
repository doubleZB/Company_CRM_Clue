var vm = new Vue({
    el: "#container",
    data: {
        messageId: 0,
        // -- 短信模板
        smsModuleVal: '',
        smsModule: [],
        //flag 1我的 2 我下属 3 全公司 场景
        flag: "1",
        PageNum: 1,
        PageSize: 10,
        UserIdDepId: "",
        departmentId: "",
        userId: "",
        pageSizeField: [10, 20, 30, 40, 50],
        startTime: "",
        endTime: "",
        pageNum: 1,
        pageSize: 10,
        pages: 0,//共多少页
        total: 50,
        checkedDepartmentId: "",
        checkedUserId: "",
        tableHead: [
            {name: '发信人'},
            {name: '短信状态'},
            {name: '提交时间'},
            {name: '短信内容'},
            {name: '发送条数'},
            {name: '到达条数'},
            {name: '操作'},
        ],
        operate: [
            {
                "id": 0,
                "name": "",
                "smsStatus": "",
                "submitTime": "",
                "smsContent": "",
                "sendNumber": "",
                "arrivalNumber": ""
            },
        ],
        //type 0全部 1发送成功 2发送失败 发送状态
        serviceType: [
            {name: '全部', id: 0},
            {name: '发送成功', id: 1},
            {name: '发送失败', id: 2},
            {name: '发送中', id: 3}
        ],
        //flag 1我的 2 我下属 3 全公司 场景
        serviceSceneStr: '1',
        serviceScene: [
            {name: '我发送的短信', id: '1'},
            {name: '我下属发送的短信', id: '2'},
            {name: '全部短信', id: '3'}
        ],
        userStatus: true
    },
    created: function () {
        let self = this;
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#date'
                , range: true
            });
        })
    },
    methods: {
        sendMessagesBySendContentTem: function (sendContentTem,messageId) {
            $('#smsModule').modal('hide')
            console.log("发送短信！");
            let self = this;
            console.log(sessionStorage.getItem("USER_TOKEN"));
            var data = {
                "version": "V1.0",
                "data": {
                    "userId": parseInt(sessionStorage.getItem("USER_TOKEN")),
                    "contentId": parseInt(sendContentTem),
                    "messageId": parseInt(messageId)
                }
            };
            $.ajax({
                url: "/message/sendMsgBySendContentTem",
                data: JSON.stringify(data),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == 20000) {
                        layer.msg("短信已发出");
                        $('#smsModule').modal('hide')
                    }
                    else {
                        layer.msg('发送失败');
                        $('#smsModule').modal('hide')
                    }
                }
            })
        },
        sendMessages: function () {
            let self = this;
            console.log(sessionStorage.getItem("USER_TOKEN"));
            console.log(this.smsModuleVal)
            var data = {
                "version": "V1.0",
                "data": {
                    "userId": parseInt(sessionStorage.getItem("USER_TOKEN")),
                    "contentId": self.smsModuleVal.id,
                    "content": self.smsModuleVal.content,
                    "signature": self.smsModuleVal.signature,
                    "messageId": vm.messageId
                }
            }
            $.ajax({
                url: "/message/sendMsg",
                data: JSON.stringify(data),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == 20000) {
                        layer.msg("发送成功");
                        $('#smsModule').modal('hide')
                    }
                    else {
                        layer.msg('发送失败');
                        $('#smsModule').modal('hide')
                    }
                }
            })
        },
        /**
         * 获取短信模板
         * @param messageId 短信id
         */
        resendMessage: function (messageId) {
            vm.messageId = messageId;
            let self = this;
            var data = {"version":"V1.0",
                "data":{
                    "companyId": parseInt(sessionStorage.getItem("CRM_COMPANY_TOKEN"))
                }
            };
            $.ajax({
                url:"/clue/showMessageTemplate",
                data:JSON.stringify(data),
                type:"post",
                dataType:"json",
                contentType:"application/json",
                success:function(data){
                    if(data.code==20000){
                        self.smsModule = data.data;
                    }
                }

            })
        },
        fnChangeServiceScene: function () {
            console.log(this.serviceSceneStr)
            if (this.serviceSceneStr !== '1') {
                vm.userStatus = false
            } else {
                vm.userStatus = true
            }
            console.log(vm.userStatus)
        },
        lastPage: function () {//上一页
            if (vm.pageNum > 1) {
                vm.pageNum--;
                vm.reload();
            } else {
                layer.msg("已经是第一页了",{time:2000});
            }
        },
        nextPage: function () {//下一页
            if (vm.pageNum < vm.pages) {
                vm.pageNum++;
                vm.reload();
            } else {
                layer.msg("已经是最后一页了",{time:2000});
            }
        },
        jumpPage: function () {
            if ($("#jumpPage").val() <= vm.pages) {
                vm.pageNum = $("#jumpPage").val();
                vm.reload();
            } else {
                layer.msg("没有此页",{time:2000});
            }
        },
        checkedPageSize: function (e) {
            vm.pageSize = $(e.target).val();
            vm.reload();
        },
        reload: function () {
            var date = $("#date").val();
            var array = ["",""];
            if(date != "" && date != null){
                var array = date.split(" - ");
            }
            if ($("#selectServiceScene").val() == "" || $("#selectServiceScene").val() == null) {
                var flag = "1";
            } else {
                var flag = $("#selectServiceScene").val();
            }
            if ($("#selectServiceType").val() == "" || $("#selectServiceType").val() == null) {
                var type = 0;
            } else {
                var type = $("#selectServiceType").val();
            }
            $.ajax({
                url: "/message/selectMessageList",
                type: 'POST',
                async: false,
                data: {
                    "CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN"),
                    "startTime": array[0],
                    "endTime": array[1],
                    "PageNum": vm.pageNum,
                    "PageSize": vm.pageSize,
                    "checkedDepartmentId": vm.checkedDepartmentId,
                    "checkedUserId": vm.checkedUserId,
                    "flag": flag,
                    "type": type
                },
                dataType: "json",
                error: function () {
                },
                success: function (data) {
                    console.log(data.list);
                    vm.operate = data.list;
                    vm.total = data.total;
                    vm.pages = data.pages;
                    vm.pageNum = data.pageNum;
                    //layer.close(index);
                }
            });
        },
        query: function () {
            vm.reload();
        },
        // -- 选择部门
        sendOther: function () {
            var sendOther = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '85%'],
                content: "/jspSkip/selectDepAndUser",
                anim: 1, btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        var length = iframeWin.vm.peopleId.length;
                        var userIds = [];
                        var departmentIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            if (iframeWin.vm.peopleNum[i].id[0] == "d") {
                                var length = iframeWin.vm.peopleNum[i].id.length
                                departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                            } else {
                                userIds.push(iframeWin.vm.peopleNum[i].id)
                            }
                        }
                        if (departmentIds[0] != null) {
                            vm.checkedDepartmentId = departmentIds[0];
                            vm.checkedUserId = null;
                        } else {
                            vm.checkedDepartmentId = null;
                            vm.checkedUserId = userIds[0];
                        }
                        $("#departmentName").text(iframeWin.vm.peopleName);
                        layer.close(sendOther);
                    }
                },
                //取消
                btn2: function (index, layero) {
                }
            })
        },
        exportExcel: function () {
            var date = $("#date").val();
            var array = ["",""];
            if(date != "" && date != null){
                var array = date.split(" - ");
            }
            if ($("#selectServiceScene").val() == "" || $("#selectServiceScene").val() == null) {
                var flag = "1";
            } else {
                var flag = $("#selectServiceScene").val();
            }
            if ($("#selectServiceType").val() == "" || $("#selectServiceType").val() == null) {
                var type = 0;
            } else {
                var type = $("#selectServiceType").val();
            }
            $("input[name='CRM_TOKEN']").val(sessionStorage.getItem("CRM_TOKEN"));
            $("input[name='startTime']").val(array[0]);
            $("input[name='endTime']").val(array[1]);
            $("input[name='PageNum']").val(vm.pageNum);
            $("input[name='PageSize']").val(vm.pageSize);
            $("input[name='checkedDepartmentId']").val(vm.checkedDepartmentId);
            $("input[name='checkedUserId']").val(vm.checkedUserId);
            $("input[name='flag']").val(flag);
            $("input[name='type']").val(type);
            $("#exf").submit();
        }
    }
});
vm.reload();