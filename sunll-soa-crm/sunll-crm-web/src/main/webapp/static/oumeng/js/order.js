/**
 * Created by szq on 2017/11/13.
 */
function Dictionary(){
    this.data = new Array();

    this.put = function(key,value){
        this.data[key] = value;
    };

    this.get = function(key){
        return this.data[key];
    };

    this.remove = function(key){
        this.data[key] = null;
    };

    this.isEmpty = function(){
        return this.data.length == 0;
    };

    this.size = function(){
        return this.data.length;
    };
}

var statusList = new Dictionary();
var subjectList = new Dictionary();
var sceneList = new Dictionary();
var sourceList = new Dictionary();


//订单状态装换
statusList.put("1", "待支付");
statusList.put("2", "已支付");
statusList.put("3", "待寄送");
statusList.put("4", "寄送中");
statusList.put("5", "已到样");
statusList.put("6", "已出报告");
//statusList.put("7", "样本异常");
statusList.put("8", "已取消");
statusList.put("9", "待退款");
//statusList.put("10", "部分退款");
statusList.put("11", "已退款");
statusList.put("12", "拒绝退款");
statusList.put("13", "已退款");
//角色
subjectList.put("1", "医生");
subjectList.put("2", "代理商");
subjectList.put("3", "经销商");
subjectList.put("4", "第三方实验室");
subjectList.put("5", "患者");
//订单场景
sceneList.put("1", "医院科室");
sceneList.put("2", "代理商");
sceneList.put("3", "经销商");
sceneList.put("4", "第三方实验室");
sceneList.put("5", "医生推荐");
//订单来源
sourceList.put("1", "微信端");
sourceList.put("2", "PC前端");
sourceList.put("3", "PC后台");

var customerTypeList = new Dictionary();
//1:呼入 2:呼出
customerTypeList.put("1", "呼入");
customerTypeList.put("2", "呼出");