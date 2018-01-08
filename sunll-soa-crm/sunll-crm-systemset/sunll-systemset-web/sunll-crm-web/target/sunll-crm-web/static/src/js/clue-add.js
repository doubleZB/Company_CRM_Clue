var vm = new Vue({
    el: "#container",
    data: {
        datte: null,
        businessSceneFont: "",
        rowList: [
            /*      {
             "id": 1019,
             "fieldName": "必填信息",
             "fieldType": "div",
             "fieldAlias": "required_info",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 0,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372975000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [
             {
             "id": 1020,
             "fieldName": "联系人姓名",
             "fieldType": "input",
             "fieldAlias": "contactName",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1019,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372975000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1021,
             "fieldName": "手机",
             "fieldType": "input",
             "fieldAlias": "phone",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1019,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372975000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             }
             ],
             "value": null,
             "selectList": []
             },
             {
             "id": 1022,
             "fieldName": "其他信息",
             "fieldType": "div",
             "fieldAlias": "other_info",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 0,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372975000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [
             {
             "id": 1023,
             "fieldName": "公司名称",
             "fieldType": "input",
             "fieldAlias": "companyName",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1024,
             "fieldName": "跟进状态",
             "fieldType": "select",
             "fieldAlias": "followStatus",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": [
             {
             "id": 37,
             "name": "已联系",
             "value": "1",
             "businessTypeFieldId": 1024,
             "isEnabled": 1,
             "isDel": 1,
             "createTime": 1512372976000,
             "createUserId": 10,
             "updateTime": null,
             "updateUserId": null
             },
             {
             "id": 38,
             "name": "未联系",
             "value": "2",
             "businessTypeFieldId": 1024,
             "isEnabled": 1,
             "isDel": 1,
             "createTime": 1512372976000,
             "createUserId": 10,
             "updateTime": null,
             "updateUserId": null
             }
             ]
             },
             {
             "id": 1025,
             "fieldName": "线索来源",
             "fieldType": "select",
             "fieldAlias": "clueSource",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": [
             {
             "id": 39,
             "name": "朋友推荐",
             "value": "1",
             "businessTypeFieldId": 1025,
             "isEnabled": 1,
             "isDel": 1,
             "createTime": 1512372976000,
             "createUserId": 10,
             "updateTime": null,
             "updateUserId": null
             },
             {
             "id": 40,
             "name": "网上搜索",
             "value": "2",
             "businessTypeFieldId": 1025,
             "isEnabled": 1,
             "isDel": 1,
             "createTime": 1512372976000,
             "createUserId": 10,
             "updateTime": null,
             "updateUserId": null
             }
             ]
             },
             {
             "id": 1026,
             "fieldName": "职务",
             "fieldType": "input",
             "fieldAlias": "position",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1027,
             "fieldName": "部门",
             "fieldType": "input",
             "fieldAlias": "department",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1028,
             "fieldName": "固定电话",
             "fieldType": "input",
             "fieldAlias": "fixedTel",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1029,
             "fieldName": "微信",
             "fieldType": "input",
             "fieldAlias": "wechat",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372976000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1030,
             "fieldName": "邮箱",
             "fieldType": "input",
             "fieldAlias": "email",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372977000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1031,
             "fieldName": "地区",
             "fieldType": "input",
             "fieldAlias": "area",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372977000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1032,
             "fieldName": "网址",
             "fieldType": "input",
             "fieldAlias": "website",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372977000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1033,
             "fieldName": "下次跟进时间",
             "fieldType": "date",
             "fieldAlias": "textFollowTime",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372977000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             },
             {
             "id": 1034,
             "fieldName": "备注",
             "fieldType": "text",
             "fieldAlias": "remarks",
             "isEnabled": 1,
             "isRequired": 1,
             "pid": 1022,
             "enterPrompt": null,
             "businessTypeId": 190,
             "isDel": 1,
             "createUserId": 10,
             "createTime": 1512372977000,
             "updateUserId": null,
             "updateTime": null,
             "businessTypeFieldList": [],
             "value": null,
             "selectList": []
             }
             ],
             "value": null,
             "selectList": []
             }*/
        ],
        flog:true,
    },
    mounted:function(){
        console.log()
    },
    created: function () {
        let self = this;
        var data = {"data": {"businessId": sessionStorage.getItem("clue_clueAdd"), "pid": "0"}, "version": "V1.0"};
        $.ajax({
            url: "/clue/createClue",
            data: JSON.stringify(data),
            type: "POST",
            contentType: "application/json",
            async: false,
            dataType: "JSON",
            error: function () {
                layer.msg("获取线索业务字段出错");
            },
            success: function (data) {
                console.log(data.data)
                if (data.code == 20000) {
                    self.rowList = data.data;
                    console.log(self.rowList)
                } else {
                    layer.msg(data.message);
                }
            }
        })

        lay('#version').html('-v' + laydate.v);
        console.log(this.rowList)
        let dateList = [];
        self.rowList.map((row) => {
            row.businessTypeFieldList.map((col) => {
                if (col.fieldType == 'date') {
                    console.log(`#date${col.id}`)
                    laydate.render({
                        elem: `#date${col.id}` //指定元素,
                        ,type: 'datetime'
                        //, done: function (value, date) {
                        //    // self是Vue的引用
                        //    self.datte = value;
                        //    // this 是laydate中的指向，一般来说，jquery的插件在事件中，都会将this指向到当前dom（至少有一个属性是指向这个dom的）
                        //    $(this.elem[0]).trigger('change');
                        //},
                    });
                  /*  laydate.render({
                        elem: "#date15",
                        type: 'datetime'
                    });*/
                }
            });
            // console.log(rowList)
        });

    },
    methods: {
        reWindow: function(){
            window.history.back(-1);
            sessionStorage.removeItem("clue_clueAdd");
        },
        iframeHref: function (url) {
            if (url) {
                console.log(url);
                window.location.href = url;
                // $("#iframe").attr("src", url);
            }
        },
        save: function () {
            let rowList = this.rowList;
            var listMap = [];
            var flag = true;
            var off=true;
            //var listMap = this.listMap;
            var data = {"version":"V1.0",
                "data":{
                    "userId":sessionStorage.getItem("USER_TOKEN"),
                    "businessId":  sessionStorage.getItem("clue_clueAdd"),
                    "listMap":listMap}
            }
            rowList.map((row) => {
                row.businessTypeFieldList.map((col) => {
                    var fieldAndvalue={fieldId:"",value:""};
                     fieldAndvalue.fieldId=col.id;
                     fieldAndvalue.value=col.value;
                     listMap.push(fieldAndvalue);
                    //if(col.value){
                    //    if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(parseInt(col.value)))){
                    //        console.log(col.value)
                    //        off = false;
                    //        flag = false;
                    //    }
                    //}
                    if (!col.value && col.isRequired == 1) {
                        Vue.set(col, 'error', 'ERROR');
                        flag = false;
                        return;
                    } else {
                        col.error = false
                    }
                });
            });
            if(flag){
                if(vm.flog){
                    $.ajax({
                        url:"/clue/saveClue",
                        data:JSON.stringify(data),
                        type:"POST",
                        async:false,
                        dataType:"json",
                        contentType:"application/json",
                        success:function(data){
                            if(data.code==20000){
                                layer.msg("保存成功");
                                 vm.flog = false;
                                setTimeout(function(){
                                    location.reload();
                                },2000)
                            }
                            else{
                                layer.msg("保存失败");
                            }
                        }
                    })
                }
            }else {
                if(off==false){
                    layer.msg("请输入正确的手机号");
                    return false;
                }
                layer.msg("请填写必填项");
            }

        }
    }
});

