package com.dhar.smsinboxreader;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

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
    public void getAllMessages(PluginCall call) {
        Cursor cursor = getContext().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        ArrayList smsArray = new ArrayList<Sms>(cursor.getCount());

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
               for(int idx = 0; idx < cursor.getColumnCount(); idx++)
               {
                   smsArray.set(idx, new Sms(
                           cursor.getString(cursor.getColumnIndexOrThrow("address")),
                           cursor.getString(cursor.getColumnIndexOrThrow("body"))
                   ));
               }
            } while (cursor.moveToNext());
        } else {
           // empty box, no SMS
        }

        JSObject smsArrayContainer = new JSObject();


        ArrayList smsJsArray = new ArrayList<JSObject>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            smsJsArray.set(i, ((Sms)smsArray.get(i)).getJson());
        }
        smsArrayContainer.put("sms", smsJsArray);
        smsArrayContainer.put("count", cursor.getCount());

        call.success(smsArrayContainer);
    }
}
