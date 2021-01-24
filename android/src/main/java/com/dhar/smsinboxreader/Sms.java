package com.dhar.smsinboxreader;

import com.getcapacitor.JSObject;

public class Sms {
    private String from;
    private String message;

    public Sms(String from, String message) {
        this.from = from;
        this.message = message;

    }

    public JSObject getJson() {
        JSObject javascriptObj = new JSObject();
        javascriptObj.put("from", this.from);
        javascriptObj.put("message", this.message);
        return javascriptObj;
    }
}
