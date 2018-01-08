
var vm = new Vue({
    el: "#container",
    data: {
        have:true,
        departmentIds: [],
        colId: [],
        checkBox:"",
        checkedDepartmentId: "",
        checkedUserId: "",
        businessTypeId: "",
        businessName:"",
        data: [],
        clueIds: [],
        serviceClueField: "",
        clueList: [],
        selectClueField: [],
        common: 50,//共多少条
        pages: 1,
        Page: 1,//第几页
        PageSizeFont: "10",
        paging: [10, 20, 30, 40, 50],
        DisplayMost: 10,//最多显示
        // -- 业务类型
        businessSceneFont: "",
        serviceType: [
            {name: '请选择'},
            {name: 'aa', id: '1'},
            {name: 'bb', id: '2'},
            {name: 'cc', id: '3'},
        ],
        // -- 场景
        serviceSceneFont: '3',
        serviceScene: [
            //{name: '请选择',id:''},
            {name: '我的线索', id: '1'},
            {name: '我下属的线索', id: '2'},
            {name: '全部线索', id: '3'},
        ],
        // -- 表格中--跟进状态
        statusListType: false,
        statusList: [
            {name: '全部'},
            {name: '已联系'},
            {name: '未联系'},
            {name: '已标记'}
        ],
        // -- 短信模板
        smsModuleVal: '',
        smsModule: [
            //{
            //    businessId: 34,
            //    businessTypeId: 190,
            //    content: "asdf你好，很高兴认识你",
            //    id: 1,
            //    name: "模板1",
            //    userId: 1
            //}
            //, {
            //    businessId: 34,
            //    businessTypeId: 190,
            //    content: "你好，很高兴aerdf认识你",
            //    id: 2,
            //    name: "模板2",
            //    userId: 1
            //}

        ],
        // -- 导入线索
        incClue: [
            {name: '联系人姓名'},
            {name: '联系人姓名'},
            {name: '联系人姓名'}
        ],
        // -- 指定门店
        specifiedStore: [
            {name: '联系人姓名'},
            {name: '联系人姓名'},
            {name: '联系人姓名'}
        ],

        // todo -- 操作表格
        operateType: false,
        currentSelected: [], // 当前选中的项目，此处，存入ID即可
        showFiled: [],
        allFiled: [],
        tableList: [],
        // 这里是表格中的数据，过滤器要对它做属性的筛选
        rows: []
    },
    created:function(){
        let self = this;
        var data = {"version":"V1.0",
                    "data":{
                        "companyId": parseInt(sessionStorage.getItem("CRM_COMPANY_TOKEN"))
                    }
        }
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
    methods: {
        getColId: function (id) {
            this.colId.push(id);
            console.log(this.colId)
        },
        skipDetail: function (clueId) {
            window.location.href = "/clue/skipDetail?clueId=" + clueId + "&businessTypeId=" + this.businessTypeId;
        },
        saveUploadFile: function(){
         var file=$("#uploadFile").val();
        if(file==""){
        layer.msg("请选择要上传的文件");
        return false;
       }
      var formData = new FormData($("#uploadForm")[0]);
       $.ajax({
        url:"/clue/importClues/"+sessionStorage.getItem("USER_TOKEN")+"/"+sessionStorage.getItem("CRM_COMPANY_TOKEN"),
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType: "json",
        success:function(data){
            if(data.code!=20000){
                $("#errorId").show();
                $("#errorMessage").hide();
                $("#errorMessageExcel").attr("href", "/contract/contractDownLoad?fileName=" + data.data);

                //  layer.msg("导入成功");
            }else {
                $("#errorId").hide();
                if (data.data == "1") {
                    $("#errorMessage").attr("style", "margin-top: 20px;color: lawngreen;")

                } else {
                    $("#errorMessage").attr("style", "margin-top: 20px;color: #ff0000;")
                }
                $("#errorMessage").show();
                $("#errorMessage").text(data.message);
              }
               $("#uploadFile").val("");
            }
       })
    },
        exportClueExcel: function () {
            var data = {
                "version": "V1.0",
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                    "businessId": vm.businessSceneFont,
                    "typeStatus": "1",
                    "userId": sessionStorage.getItem("USER_TOKEN")
                }
            };
            $("#clueDataExcel").val(JSON.stringify(data))
            //$("input[name='clueData']").val(JSON.stringify(data));
            $("#clueFormExcel").submit();
        },
        exportClue: function () {
            var data = {
                "version": "V1.0",
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                    "businessId": vm.businessSceneFont,
                    "typeStatus": "1",
                    "userId": sessionStorage.getItem("USER_TOKEN"),
                    "identifier": vm.serviceSceneFont,
                    "data": vm.data
                }
            };
            $("#clueData").val(JSON.stringify(data))
            //$("input[name='clueData']").val(JSON.stringify(data));
            $("#clueForm").submit();
        },
        checkBusiness: function () {
            reloadPage(1);
        },
        searchFieldValue: function () {
            vm.data = [];
            if ($("#fieldValue").val() != "" && vm.serviceClueField != "") {
                var value = {"fieldId": vm.serviceClueField, "value": "%" + $("#fieldValue").val() + "%"}
                vm.data.push(value)
                reloadPage(1);
            } else if ($("#fieldValue").val() == "" && vm.serviceClueField != "") {
                reloadPage(1);
            }
        },
        skipCall: function (clueId) {
            var loginUserId = parseInt(sessionStorage.getItem("USER_TOKEN"));
            window.location.href = "/clue/skipCall?clueId="+clueId+"&loginUserId="+
                loginUserId+"&businessTypeId="+this.businessTypeId;
        },
        //删除线索
        delClue: function () {
            let self = this;
            var data = {
                "data": {
                    "clueIdList": this.currentSelected
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/clue/delClue",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("删除线索出错");
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
                        layer.msg("删除线索成功");
                        reloadPage(1);
                        self.currentSelected = [];
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        checkBusiness: function () {
            for(var i=0;i<vm.serviceType.length;i++){
                if (vm.businessSceneFont == vm.serviceType[i].id){

                    vm.businessTypeId = vm.serviceType[i].businessTypeId;
                }
            }
            reloadPage(1);
        },
        searchFieldValue: function () {
            vm.data = [];
            if ($("#fieldValue").val() != "" && vm.serviceClueField != "") {
                var value = {"fieldId": vm.serviceClueField, "value": "%" + $("#fieldValue").val() + "%"}
                vm.data.push(value)
                reloadPage(1);
            } else if ($("#fieldValue").val() == "" && vm.serviceClueField != "") {
                reloadPage(1);
            }
        },
        skipPageNumber: function () {
            reloadPage($("#pageNumber").val());
        },

            checkIdentifier: function () {
                reloadPage(1);
            },
            changePageSize: function () {
                reloadPage(1);
            },
            PreviousPage: function () {//上一页
                if (this.Page > 1) {
                    this.Page--;
                    reloadPage(this.Page);
                } else {
                    layer.msg("已经是第一页了");
                }
            },
            nextPage: function () {//下一页
                if (this.Page < this.pages) {
                    this.Page++;
                    reloadPage(this.Page);
                } else {
                    layer.msg("已经是最后一页了");
                }
            },
            iframeHref1:function(){
                var tem= vm.businessSceneFont;
                sessionStorage.setItem("clue_clueAdd",tem);
                window.location.href = "/clue/skipAddClue";
            },
            //-- 页面跳转
            iframeHref: function (url) {
                if (url) {
                    console.log(url);
                    window.location.href = url;
                    // $("#iframe").attr("src", url);
                }
            }
            ,
            // -- 转让
            sendOther: function () {
                let self = this;
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
                                        var length = iframeWin.vm.peopleNum[i].id.length
                                        departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                                    } else {
                                        userIds.push(iframeWin.vm.peopleNum[i].id)
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
                                var data= {
                                    "version": "V1.0",
                                    "data": {
                                        "clueList":self.currentSelected,
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
                                            self.currentSelected=[];
                                            setTimeout(function(){
                                                location.reload();
                                            },1500)
                                        }
                                        else {
                                            layer.msg("转让失败");
                                            self.currentSelected=[];
                                            setTimeout(function(){
                                                location.reload();
                                            },1500)
                                        }
                                    }
                                })
                            }
                        },
                        //取消
                        btn2: function (index, layero) {
                        }
                    })

        },
        saveFieldShow: function () {
            var showFieldIds = [];
            var showId = [];
            for (var i = 0; i < vm.showFiled.length; i++) {
                if ("a" == vm.showFiled[i].id) {
                    showId.push('1')
                } else if ("b" == vm.showFiled[i].id) {
                    showId.push("2")
                } else {
                    showFieldIds.push(vm.showFiled[i].id)
                }

            }
            if (showFieldIds.length > 0 || showId.length > 0) {
                insertClueUserShowField(showFieldIds, showId);
            }
        },
        // -- 跟进状态
        fnStatusListType: function () {
            this.statusListType = !this.statusListType;
        }
        ,
        fnSmsSend: function () {
            let self = this;
            if( self.currentSelected.length>0){
                self.clueIds  = self.currentSelected;
            }
            else if(this.colId.length>0){
                self.clueIds = self.colId;
            }
            else if(self.clueIds.length==0){
                layer.msg("请选择线索");
                $('#smsModule').modal('hide')
                return;
            }
                var data = {
                    "version": "V1.0",
                    "data": {
                        "userId": parseInt(sessionStorage.getItem("USER_TOKEN")),
                        "clueId": self.clueIds,
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
                        if (data.code == 20000) {
                            layer.msg("短信已发送", {offset: '50px'});
                            self.currentSelected=[];
                            self.clueIds = [];
                            self.colId=[];
                            $('#smsModule').modal('hide')
                        }
                        else {
                            layer.msg(data.msg);
                            self.clueIds = [];
                            self.colId=[];
                            self.currentSelected=[];
                            $('#smsModule').modal('hide')
                        }
                    }
                });


        },
        fnSms: function () {
            console.log(this.smsModuleVal)
        },    // 全选或者全不选
        setAllSelected: function (isAllSelected) {
            if (isAllSelected) {
                this.have = false;
                $(".smsShow").attr("data-target","#smsModule");
                this.currentSelected = this.rows.map(row => row.filedId);
            } else {
                this.have = true;
                $(".smsShow").attr("data-target","");
                this.currentSelected = [];
            }
        },
        // 改变过滤条件
        changeFilter(filter,$event) {
//                var index = this.showFiled.indexOf(filter);
            console.log(filter);
            filter.select = !filter.select;
            let idArr = [];
            this.showFiled.map(c => {
                idArr.push(c.id)
            });
            var index = idArr.indexOf(filter.id);
            //if(showFiled.length > 8){
            //    alert('最多只可选8个字段')
            //
            //}else if(showFiled.length < 8){
            //    this.showFiled.push(filter);
            //}
            if (index === -1) {
                if(this.showFiled.length <= 7){
                    this.showFiled.push(filter);
                }else{
                    alert('最多只可选8个字段')
                    $event.preventDefault();
                }
            } else {
                this.showFiled.splice(index, 1);
            }
        },
        // 选中/反选单个条目
        changeItem: function (event, item, index) {
            const id = item.filedId;
            let isSelected = event.target.checked;
            if (isSelected) {
                this.have = false;
                $(".smsShow").attr("data-target","#smsModule");
                if (this.currentSelected.indexOf(id) === -1) {
                    this.currentSelected.push(id);
                }
            } else if (!isSelected) {
                this.have = true;
                $(".smsShow").attr("data-target","");
                let index = this.currentSelected.indexOf(id);
                if (index !== -1) {
                    this.currentSelected.splice(index, 1);
                }
            }
        }
    },
    computed: {
        // -- 当前是否全部选中
        isAllSelected: function () {
            return this.currentSelected.length === this.rows.length;
        }
        ,
        // 用于展示的数据，即应用了过滤条件的数据
        filtedData: function () {
            return this.rows.map(row => {
                let result = {};
                result.filedId = row['filedId'];
                for (let i = 0; i < this.showFiled.length; i++) {
                    let filter = this.showFiled[i];
                    result[filter.id] = row[filter.id];
                }
                return result;
            });
        }
    }
});


listDepartment()
function listDepartment(){
    $.ajax({
        url: "/unitAccountInterface/getDepartmentListbyUserId",
        type: 'POST',
        async: false,
        data: {"userId":sessionStorage.getItem("USER_TOKEN"),"companyId":sessionStorage.getItem("CRM_COMPANY_TOKEN")},
        dataType: "json",
        error: function () {
            console.log("chu cuo le !")
        },
        success: function (data) {
            console.log("根据用户id查询部门id集合")
            console.log(data)
            console.log(data[0].id)
            for(var i=0;i<data.length;i++){
                vm.departmentIds.push(data[i].id);
            }
            selectBusiness();
        }
    });
}


function selectBusiness() {
    var data = {
        "version": "V1.0",
        "data": {
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
            "typeStatus": "1",
            "organizationList": vm.departmentIds
        }
    };
    $.ajax({
        url: "/clue/selectBusiness",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("数据出错");
        },
        success: function (data) {
            vm.serviceType = data.data;
            vm.businessSceneFont = data.data[0].id;
            vm.businessTypeId = data.data[0].businessTypeId;
            reloadPage(1);
        }
    })
}
function transferClue(e) {
    var self = this;
    var data = {
        "version": "V1.0",
        "data": {
            "clueList": self.clueList,
            "userId": e
        }
    };
    $.ajax({
        url: "/clue/transferClue",
        data: JSON.stringify(data),
        type: "post",
        dataType: "json",
        content: "application/json",
        success: function (data) {
            if (data.code == 20000) {
                layer.msg("转让成功");
            }
            else {
                layer.msg("转让失败");
            }
        }

    })
}
function listCule(pageNumber) {
    vm.selectClueField = [];
    vm.rows = [];
    vm.showFiled = [];
    var self = this;
    var data = {
        "version": "V1.0",
        "data": {
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
            "businessId": vm.businessSceneFont,
            "typeStatus": "1",
            "pageNumber": pageNumber,
            "pageSize": vm.PageSizeFont,
            "userId": sessionStorage.getItem("USER_TOKEN"),
            "identifier": vm.serviceSceneFont,
            "data": vm.data
        }
    };
    var index = layer.load(0, {shade: [0.3, '#000']});
    $.ajax({
        url: "/clue/listClue",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("数据出错");
        },
        success: function (data) {
            vm.common = data.data.value.total;
            vm.Page = data.data.value.pageNum;
            vm.pages = data.data.value.pages;
            vm.allFiled = data.data.allFiled;
            self.tableList = data.data.value.list;
            layer.close(index);
            for (var i = 0; i < data.data.allFiled.length; i++) {
                if ("1" == data.data.allFiled[i].showId) {
                    data.data.allFiled[i].id = "a";
                } else if ("2" == data.data.allFiled[i].showId) {
                    data.data.allFiled[i].id = "b";
                }
                if ("1" == data.data.allFiled[i].must) {
                    vm.showFiled.push(data.data.allFiled[i])
                }
                if ("contactName" == data.data.allFiled[i].fieldAlias) {
                    vm.selectClueField.push(data.data.allFiled[i]);
                    continue;
                }
                if ("companyName" == data.data.allFiled[i].fieldAlias) {
                    vm.selectClueField.push(data.data.allFiled[i]);
                    continue;
                }
                if ("phone" == data.data.allFiled[i].fieldAlias) {
                    vm.selectClueField.push(data.data.allFiled[i]);
                    continue;
                }
                if ("fixedTel" == data.data.allFiled[i].fieldAlias) {
                    vm.selectClueField.push(data.data.allFiled[i]);
                    continue;
                }
            }
        }
    });
    // -- 根据返回数据构建符合要求的数据
    if( this.tableList.length>0) {
        this.tableList.map((parent) => {
            let obj = {};
            obj['filedId'] = parent.id;
            parent.data.map(child => {
                obj[child.filedId] = child.value;
            });
            vm.rows.push(obj)
        });
    }
    this.row = this.row;
    // -- 返回显示的数据
    var c = vm.showFiled,
        a = vm.allFiled;
    for (let i = 0; i < c.length; i++) {
//                console.log(c[i])
        for (let j = 0; j < a.length; j++) {
//                    console.log(a[j]);
            if (c[i].id === a[j].id) Vue.set(a[j], 'select', true);
        }
    }
}

function reloadPage(pageNumber) {
    if (pageNumber == 0) {
        layer.msg("当前是第一页啦，没有上一页！");
        return;
    }
//        var userNameToFind = $("#userNameToFind").val();
//        var userDepartmentId = $("#userDepartmentId").val();
    listCule(pageNumber);
}

function insertClueUserShowField(showFieldIds, showId) {
    var data = {
        "version": "V1.0",
        "data": {
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
            "businessId": vm.businessSceneFont,
            "userId": sessionStorage.getItem("USER_TOKEN"),
            "businessTypeId": vm.businessTypeId,
            "showField": showFieldIds,
            "showId": showId
        }
    };

    $.ajax({
        url: "/clue/insertClueUserShowField",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("数据出错");
        },
        success: function (data) {
            if (data.data.flag) {
                layer.msg("保存成功！")
                vm.operateType = false;
            } else {
                layer.msg("保存失败！")
            }
        }
    })
}
//上传企业logo
function asyncUpload(inp_id, img_id, hid_id) {
    var companyId = $("#companyId").val();
    var index;
    $("#" + inp_id).upload({
        url:"/clue/importClues/"+sessionStorage.getItem("USER_TOKEN")+"/"+sessionStorage.getItem("CRM_COMPANY_TOKEN")+"/"+vm.checkBox,
        params: '',
        dataType: 'json',
        onSend: function (obj, str) {
            index = layer.load(1, {shade: [0.1, '#fff']});
            return true;
        },
        onComplate: function (data) {
            layer.close(index);
            // $("#" + img_id).attr('src', data.reurl);
            //vm.companyLogo = data.reurl;
            //  $("#" + hid_id).val(data.reurl);
            //  $('#contractFileName').attr('href', '/contract/contractDownLoad?fileName=' + data.reurl);
            //  $("#contractFileName").text(fileName);
        }
    });
    $("#" + inp_id).upload("ajaxSubmit");
}
$("body").click(function(){
    $("input[name='phone']").each(function(x){
        if($(this).prop("checked")){
            //console.log($(this).val());
            vm.checkBox=$(this).val();
        }
    })
})

function checkBusinessStatus(){
    var data = {
        "version": "V1.0",
        "data": {
            "businessTypeId": vm.businessTypeId
        }
    };
    $.ajax({
        url:"/clue/checkBusinessStatus",
        data:JSON.stringify(data),
        type:"POST",
        dataType:"json",
        async: false,
        contentType: "application/json",
        success:function(data){
            if(data.code==20000){
                //alert(vm.businessTypeId);
                vm.businessName=data.data;
            }
            else{
                document.getElementById("maskId").style.display ="block";
            }
        }
    })
}
checkBusinessStatus();
