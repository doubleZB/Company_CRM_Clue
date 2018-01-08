<!-- 自定义filter名称为'time' -->
Vue.filter('time',
    <!-- value 格式为13位unix时间戳 -->
    <!-- 10位unix时间戳可通过value*1000转换为13位格式 -->
    function (value) {
        if (value != '' && value != null) {
            var date = new Date(value);
            Y = date.getFullYear(),
                m = date.getMonth() + 1,
                d = date.getDate(),
                H = date.getHours(),
                i = date.getMinutes(),
                s = date.getSeconds();
            if (m < 10) {
                m = '0' + m;
            }
            if (d < 10) {
                d = '0' + d;
            }
            if (H < 10) {
                H = '0' + H;
            }
            if (i < 10) {
                i = '0' + i;
            }
            if (s < 10) {
                s = '0' + s;
            }
            <!-- 获取时间格式 2017-01-03 10:13:48 -->
            var t = Y + '-' + m + '-' + d + ' ' + H + ':' + i + ':' + s;
            <!-- 获取时间格式 2017-01-03 -->
            //var t = Y + '-' + m + '-' + d;
            return t;
        } else {
            return '';
        }
    }
);
var vm = new Vue({
    el: "#container",
    data: {
        clueDetail: [],
        contactName: '',
        phone: '',
        followStatus: [],
        showFollowStatus: 1,
        businessTypeId: 0,
        followStatusId: 0,
        followUp: 0,
        followRecordList: [],
        total: 0,
        smsTemList: [],
        smsModuleVal: '',
        clueIds: [],
        isShowFollowStatus: 0,
        clueSystemInfo: "",
        calling: "",
        showPointStore: 0,
        isShowPointStore: 0,
        pointStoreId: "",
        pointStore: []
    },
    created: function () {
        this.getClueDetail();
    },
    methods: {
        //改变门店
        savePointStore: function () {
            if(!this.showPointStore){
                layer.msg("请选择门店");
                return false;
            }
            var that = this;
            var data = {
                "data": {
                    "clueId": $("#clueId").val(),
                    "businessTypeId": parseInt($("#businessTypeId").val()),
                    "followStatusId": that.pointStoreId,
                    "value": that.showPointStore
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/clue/changeFollowStatus",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("指向门店出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getClueDetail();
                        layer.msg("指向门店成功");
                        $(".fade").hide();
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //获取线索系统信息
        getClueSystemInfo: function () {
            var that = this;
            var data = {
                "version": "V1.0",
                "data": {
                    "clueId": $("#clueId").val()
                }
            };
            var index;
            $.ajax({
                url: "/clue/getClueSystemInfo",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取系统信息出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    console.log(data.data)
                    if (data.code == 20000) {
                        that.clueSystemInfo = data.data[0];
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //呼叫
        callClue: function () {
            let clueIds = this.clueIds;
            let self = this;
            var clueId = $("#clueId").val();
            clueIds.push(clueId);
            var fromPhone = "";
            var phone = sessionStorage.getItem("USER_MOBILE");
            var ext = sessionStorage.getItem("USER_TELEPHONE");
            var showcaller = sessionStorage.getItem("COMPANY_TELEPHONE");
            if (ext != '' && ext != 'null' && ext != null) {
                fromPhone = ext;
            } else if ((ext == '' || ext == 'null' || ext == null) &&
                (phone != '' && phone != 'null' && phone != null)) {
                fromPhone = phone;
            } else {
                layer.msg("当前未配置分机号和手机号，请前往商户平台去配置");
                return false;
            }
            if (self.phone == '') {
                layer.msg("该线索不存在手机号");
                return false;
            }
            if (showcaller == ''|| showcaller == 'null' || showcaller == null) {
                layer.msg("当前未配置主机号，请前往商户平台去配置");
                return false;
            }
            //存放页面获取的线索
            var data = {
                "version": "V1.0",
                "data": {
                    "showcaller": showcaller,
                    "toPhone": self.phone,
                    "fromPhone": fromPhone
                }
            };
            $.ajax({
                url: "/clue/callClue",
                data: JSON.stringify(data),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == 1000 || data.code == "1000"){
                        self.calling = "通话中";
                    }
                    layer.msg(data.message);
                }
            });
        },
        //发送短信
        fnSms: function () {
            let clueIds = this.clueIds;
            let self = this;
            var clueId = $("#clueId").val();
            clueIds.push(clueId);

            //存放页面获取的线索
            var data = {
                "version": "V1.0",
                "data": {
                    "CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN"),
                    "clueId": clueIds,
                    "contentId": self.smsModuleVal.id,
                    "content": self.smsModuleVal.content,
                    "signature": self.smsModuleVal.signature
                }
            };
            $.ajax({
                url: "/clue/sendMsg",
                data: JSON.stringify(data),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data)
                    if (data.code == 20000) {
                        layer.msg("发送成功", {offset: '50px'});
                        vm.clueIds = [];
                        $('#smsModule').modal('hide')
                    }
                    else {
                        layer.msg("发送失败");


                    }
                }
            });
        },
        //获取短信模板信息
        getSmsTem: function () {
            let self = this;
            var data = {
                "data": {
                    "companyId": parseInt(sessionStorage.getItem("CRM_COMPANY_TOKEN"))
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/clue/getSmsTem",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取模板信息出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        self.smsTemList = data.data;
                        if (self.smsTemList.length > 0) {
                            self.smsModuleVal = self.smsTemList[0];
                        }

                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        delFollowRecord: function (id) {
            var that = this;
            var data = {
                "data": {
                    "followRecordId": id
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/followRecord/delFollowRecord",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("删除跟进信息出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        layer.msg("跟进记录删除成功");
                        that.selectFollowRecord();
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        clearFollowRecord: function () {
            $("#followContent").val('');
            $("#followTextTime").val('');
            $("#followReminderContent").val('');
            this.followUp = 0;
        },
        //保存跟进信息
        saveFollowRecord: function () {
            var followContent = $("#followContent").val();
            var followTextTime = $("#followTextTime").val();
            var followReminderContent = $("#followReminderContent").val();
            if (followContent == '') {
                layer.msg("跟进内容不能为空");
                return false;
            }
            if (followContent.length > 500) {
                layer.msg("跟进内容不能超过500字");
                return false;
            }
            //if (this.followUp == 0) {
            //    layer.msg("请选择跟进方式");
            //    return false;
            //}
            //if (followTextTime == '') {
            //    layer.msg("请选择下次跟进时间");
            //    return false;
            //}
            //if (followReminderContent == '') {
            //    layer.msg("请输入提醒内容");
            //    return false;
            //}
            //if (followReminderContent.length > 30) {
            //    layer.msg("提醒内容不能超过30字");
            //    return false;
            //}
            var that = this;
            var clueId = $("#clueId").val();
            var businessTypeId = $("#businessTypeId").val();
            var data = {
                "data": {
                    "businessTypeId": parseInt($("#businessTypeId").val()),
                    "followRecord": {
                        "followContent": followContent,
                        "followUp": this.followUp,
                        "followNextTime": followTextTime,
                        "followReminderContent": followReminderContent,
                        "followSourceId": clueId,
                        "followSourceType": 1,
                        "createUserId": parseInt(sessionStorage.getItem("USER_TOKEN"))
                    }
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/followRecord/insertFollowRecord",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("插入跟进信息出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        layer.msg("跟进记录保存成功");
                        that.clearFollowRecord();
                        that.selectFollowRecord();
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //改变跟进状态
        changeFollowStatus: function () {
            var clueId = $("#clueId").val();
            var businessTypeId = $("#businessTypeId").val();
            var that = this;
            var data = {
                "data": {
                    "clueId": $("#clueId").val(),
                    "businessTypeId": businessTypeId,
                    "followStatusId": that.followStatusId,
                    "value": that.showFollowStatus
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/clue/changeFollowStatus",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("改变跟进状态出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getClueDetail();
                        layer.msg("改变跟进状态成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },

        //返回按钮
        back: function () {
            window.history.go(-1);
        },

        //获取线索的详细信息
        getClueDetail: function () {
            var clueId = $("#clueId").val();
            var businessTypeId = $("#businessTypeId").val();
            var that = this;
            var data = {
                "data": {
                    "businessTypeId": parseInt(businessTypeId),
                    "pid": 0,
                    "clueId": clueId
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/clue/clueDetail",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取业务数据出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    console.log(data.data);
                    if (data.code == 20000) {
                        that.clueDetail = data.data;
                        for (var i = 0; i < that.clueDetail.length; i++) {
                            for (var j = 0; j < that.clueDetail[i].businessTypeFieldList.length; j++) {
                                if (that.clueDetail[i].businessTypeFieldList[j].fieldAlias == "contactName") {
                                    that.contactName = that.clueDetail[i].businessTypeFieldList[j].value;
                                    continue;
                                }
                                if (that.clueDetail[i].businessTypeFieldList[j].fieldAlias == "phone") {
                                    that.phone = that.clueDetail[i].businessTypeFieldList[j].value;
                                    continue;
                                }
                                if (that.clueDetail[i].businessTypeFieldList[j].fieldAlias == "followStatus") {
                                    that.isShowFollowStatus = 1;
                                    that.followStatusId = that.clueDetail[i].businessTypeFieldList[j].id;
                                    that.followStatus = that.clueDetail[i].businessTypeFieldList[j].selectList;
                                    for (var a = 0; a < that.followStatus.length; a++) {
                                        if (that.clueDetail[i].businessTypeFieldList[j].value == that.followStatus[a].name) {
                                            that.showFollowStatus = that.followStatus[a].value;
                                            break;
                                        }
                                    }
                                    continue;
                                }
                                if (that.clueDetail[i].businessTypeFieldList[j].fieldAlias == "pointStore") {
                                    that.isShowPointStore = 1;
                                    that.pointStoreId = that.clueDetail[i].businessTypeFieldList[j].id;
                                    that.pointStore = that.clueDetail[i].businessTypeFieldList[j].selectList;
                                    for (var a = 0; a < that.pointStore.length; a++) {
                                        if (that.clueDetail[i].businessTypeFieldList[j].value == that.pointStore[a].name) {
                                            that.showPointStore = that.pointStore[a].value;
                                            break;
                                        }
                                    }
                                    continue;
                                }
                            }
                        }
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //改变跟进状态
        selectFollowRecord: function () {
            var that = this;
            var data = {
                "data": {
                    "followSourceId": $("#clueId").val()
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/followRecord/selectFollowRecord",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取业务数据出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3, '#fff']
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.followRecordList = data.data.followRecordTimelineList;
                        that.total = data.data.total;
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        }
    }
});
vm.getClueSystemInfo();
vm.selectFollowRecord();
vm.callClue();
