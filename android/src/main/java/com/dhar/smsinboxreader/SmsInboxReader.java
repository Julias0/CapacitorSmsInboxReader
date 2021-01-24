package com.dhar.smsinboxreader;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import org.json.JSONException;

import java.util.ArrayList;

@NativePlugin(
        permissions = {
                Manifest.permission.READ_SMS
        }
)
public class SmsInboxReader extends Plugin {

    @PluginMethod
    public void getCount(PluginCall call) {
        Cursor cursor = getContext().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        JSObject countWrapper= new JSObject();
        countWrapper.put("count", cursor.getCount());
        call.success(countWrapper);
    }

    @PluginMethod
    public void getAllMessages(PluginCall call) throws JSONException {
        Cursor cursor = getContext().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        ArrayList smsArray = new ArrayList<JSObject>();

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                JSObject currentSmsObject = new JSObject();
                currentSmsObject.put("from",  cursor.getString(cursor.getColumnIndexOrThrow("address")));
                currentSmsObject.put("message", cursor.getString(cursor.getColumnIndexOrThrow("body")));
                smsArray.add(currentSmsObject);
                cursor.moveToNext();
            }
        }

        JSObject smsArrayContainer = new JSObject();

        smsArrayContainer.put("sms", new JSArray(smsArray.toArray()));
        smsArrayContainer.put("count", cursor.getCount());

        call.success(smsArrayContainer);
    }
}
