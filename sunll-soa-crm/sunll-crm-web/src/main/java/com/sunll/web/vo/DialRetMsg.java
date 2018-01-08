package com.sunll.web.vo;

/**
 * Created by Administrator on 2017/12/27.
 */

public class DialRetMsg {
    String statuscode;
    String msge;
    Callback callbck;

    public DialRetMsg(String statuscode, String msge,String tid,String from,String to,String ctime) {
        this.statuscode = statuscode;
        this.msge = msge;
        this.callbck = new Callback();
        this.callbck.tid = tid;

        this.callbck.from = from;
        this.callbck.to = to;
        this.callbck.ctime = ctime;
    }
}

class Callback {
    String tid;
    String from;
    String to;
    String ctime;
}

