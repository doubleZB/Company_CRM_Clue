var vm = new Vue({
    el: "#container",
    data: {
        datte: null,
        rowList: []
    },
    created: function () {
        let self = this;
        var sendData = {
            "data": {"businessTypeId":parseInt(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE")), "pid": 0, "clueId": sessionStorage.getItem("CLUE_ID_UPDATE")},
            "version": "V1.0"
        };
        console.log(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE"));
        console.log(sessionStorage.getItem("CLUE_ID_UPDATE"));
        $.ajax({
            url: "/clue/clueDetail",
            data: JSON.stringify(sendData),
            type: "POST",
            contentType: "application/json",
            async: false,
            dataType: "JSON",
            error: function () {
                alert("获取业务数据出错");
            },
            success: function (data) {
                console.log("获取业务数据")
                console.log(data.data)
                if (data.code == 20000) {
                    self.rowList = data.data;
                } else {
                    alert(data.message);
                }
            }
        });
        lay('#version').html('-v' + laydate.v);
        let dateList = [];
        self.rowList.map((row) => {
            row.businessTypeFieldList.map((col) => {
                if (col.fieldType == 'date') {
                    laydate.render({
                        elem: `#date${col.id}` //指定元素
                        , done: function (value, date) {
                            self.datte = value;
                            $(this.elem[0]).trigger('change');
                        },
                    });
                }
            });
        });
    },
    methods: {
        back: function () {
            window.history.go(-1);
        },
        //跳转页面
        iframeHref: function (url) {
            if (url) {
                window.location.href = url;
                // $("#iframe").attr("src", url);
            }
        },
        //更新线索
        updateClue: function () {
                let rowList = this.rowList;
                var listMap = [];
                rowList.map((row) => {
                    row.businessTypeFieldList.map((col) => {
                        var fieldAndvalue={fieldId:"",value:""};
                        fieldAndvalue.fieldId=col.id;
                        fieldAndvalue.value=col.value;
                        listMap.push(fieldAndvalue);
                        if (!col.value && col.isRequired == 1) {
                            Vue.set(col, 'error', 'ERROR');
                        } else {
                            col.error = false
                        }
                    });
                });
                var data = {"version":"V1.0",
                    "data":{"businessTypeId":parseInt(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE")),"userId":parseInt(sessionStorage.getItem("USER_TOKEN")),
                        "clueId": sessionStorage.getItem("CLUE_ID_UPDATE"),
                        "listMap":listMap}
                };
            console.log("data数据");
                console.log(data);
                $.ajax({
                    url:"/clue/updateEditClue",
                    data:JSON.stringify(data),
                    type:"POST",
                    dataType:"json",
                    contentType:"application/json",
                    error: function () {
                        alert("更新线索出错");
                    },
                    success:function(data){
                        if(data.code==20000){
                            alert("更新线索成功");
                        }
                        else{
                            alert("更新线索失败")
                        }
                    }
                })
            }
        }
    });

