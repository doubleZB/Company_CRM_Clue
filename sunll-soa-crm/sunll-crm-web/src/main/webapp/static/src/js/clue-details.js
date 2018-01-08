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
        showAll: false,
        userInfo: [],
        rowList: [],
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
        checkedDepartmentId: '',
        checkedUserId: "",
        clueList: [],
        showPointStore: 0,
        isShowPointStore: 0,
        pointStoreId: "",
        pointStore: [],
        clueSystemInfo: ""
    },
    created: function () {
        this.getClueDetail();
    },
    methods: {
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
                    if (data.code == 20000) {
                        that.clueSystemInfo = data.data[0];
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //改变门店
        savePointStore: function () {
            if ($('.selectCheck').val() == '' || $('.selectCheck').val() == null){
                // $(".selMen").attr(".data-dismiss","");
                layer.msg('请选择门店');
            } else {
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
                            console.log($('.selectCheck').val())
                            $(".fade").hide();
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            }

        },
        sendOther: function () {
            var sendOther = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '85%'],
                content: "/jspSkip/selectDepAndUser1",
                anim: 1, btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        var length = iframeWin.vm.peopleId.length;
                        var userIds = [];
                        var departmentIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            if (iframeWin.vm.peopleNum[i].id[0] == "d") {
                                var length = iframeWin.vm.peopleNum[i].id.length;
                                departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                            } else {
                                userIds.push(iframeWin.vm.peopleNum[i].id);
                            }
                        }
                        if (departmentIds[0] != null) {
                            vm.checkedDepartmentId = departmentIds[0];
                            vm.checkedUserId = null;
                            console.log("部门id：" + vm.checkedDepartmentId);
                        } else {
                            vm.checkedDepartmentId = null;
                            vm.checkedUserId = userIds[0];
                            console.log("用户id：" + vm.checkedUserId);
                        }
                        layer.close(sendOther);
                        //transferClue(vm.checkedUserId);
                        //var self =this;
                        vm.clueList.push($("#clueId").val());
                        var data= {
                            "version": "V1.0",
                            "data": {
                                "clueList":vm.clueList,
                                "userId":vm.checkedUserId
                            }
                        };
                        $.ajax({
                            url:"/clue/transferClue",
                            data:JSON.stringify(data),
                            type:"post",
                            dataType:"json",
                            contentType:"application/json",
                            success:function(data){
                                if(data.code==20000){
                                    layer.msg("转让成功");
                                }
                                else {
                                    layer.msg("转让失败");
                                }
                            }

                        });
                    }
                },
                //取消
                btn2: function (index, layero) {
                }
            })
        },
        //跳转编辑页面
        updateClue: function () {
            var businessTypeId = $("#businessTypeId").val();
            var clueId = $("#clueId").val();
            sessionStorage.setItem("BUSINESS_TYPE_ID_UPDATE", businessTypeId);
            sessionStorage.setItem("CLUE_ID_UPDATE", clueId);
            window.location.href = "/jspSkip/updateClue";
        },
        //跳转到呼叫页面
        skipCall: function () {
            var clueId = $("#clueId").val();
            var businessTypeId = $("#businessTypeId").val();
            window.location.href = "/clue/skipCall?clueId=" + clueId + "&businessTypeId=" + businessTypeId;
        },
        //呼叫功能
        callClue: function () {
            let self = this;
            var data = {
                "version": "V1.0",
                "data": {
                    "toPhone": self.phone,
                    "fromPhone": sessionStorage.getItem("LOGIN_IPHONE")
                }
            };
            $.ajax({
                url: "/clue/callClue",
                data: JSON.stringify(data),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == 20000) {
                        layer.alert("正在呼叫" + self.contactName + "中。。。。。")
                    }
                }
            });
        }
        ,

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
            var that = this;
            var data = {
                "data": {
                    "clueId": $("#clueId").val(),
                    "businessTypeId": parseInt($("#businessTypeId").val()),
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
        getClueDetail: function () {
            let self = this;
            var data = {
                "data": {
                    "businessTypeId": parseInt($("#businessTypeId").val()),
                    "pid": 0,
                    "clueId": $("#clueId").val()
                },
                "version": "V1.0"
            };
            $.ajax({
                url: "/clue/clueDetail",
                data: JSON.stringify(data),
                type: "POST",
                contentType: "application/json",
                async: false,
                dataType: "JSON",
                error: function () {
                    layer.msg("获取线索业务字段出错");
                },
                success: function (data) {
                    if (data.code == 20000) {
                        self.rowList = data.data;
                        self.userInfo = data.data[0].businessTypeFieldList;
                        for (var i = 0; i < self.rowList.length; i++) {
                            for (var j = 0; j < self.rowList[i].businessTypeFieldList.length; j++) {
                                if (self.rowList[i].businessTypeFieldList[j].fieldAlias == "contactName") {
                                    self.contactName = self.rowList[i].businessTypeFieldList[j].value;
                                    continue;
                                }
                                if (self.rowList[i].businessTypeFieldList[j].fieldAlias == "phone") {
                                    self.phone = self.rowList[i].businessTypeFieldList[j].value;
                                    continue;
                                }
                                if (self.rowList[i].businessTypeFieldList[j].fieldAlias == "followStatus") {
                                    self.isShowFollowStatus = 1;
                                    self.followStatusId = self.rowList[i].businessTypeFieldList[j].id;
                                    self.followStatus = self.rowList[i].businessTypeFieldList[j].selectList;
                                    for (var a = 0; a < self.followStatus.length; a++) {
                                        if (self.rowList[i].businessTypeFieldList[j].value == self.followStatus[a].name) {
                                            self.showFollowStatus = self.followStatus[a].value;
                                            break;
                                        }
                                    }
                                    continue;
                                }
                                if (self.rowList[i].businessTypeFieldList[j].fieldAlias == "pointStore") {
                                    self.isShowPointStore = 1;
                                    self.pointStoreId = self.rowList[i].businessTypeFieldList[j].id;
                                    self.pointStore = self.rowList[i].businessTypeFieldList[j].selectList;
                                    for (var a = 0; a < self.pointStore.length; a++) {
                                        if (self.rowList[i].businessTypeFieldList[j].value == self.pointStore[a].name) {
                                            self.showPointStore = self.pointStore[a].value;
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
            })
        },
        //返回按钮
        back: function () {
            window.history.go(-1);
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
        },
        iframeHref: function (url) {
            if (url) {
                console.log(url);
                ;
                window.location.href = url;
                // $("#iframe").attr("src", url);
            }
        },
        // -- 顶部 显示全部
        fnShowAll: function () {
            this.showAll = !this.showAll;
            if (this.showAll)
                $('.showAll').css('display', 'none');
            else
                $('.showAll').css('display', 'block')

        },

    }

});
vm.getClueSystemInfo();
vm.selectFollowRecord();