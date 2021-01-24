# Capacitor Read SMS inbox plugin

A simple plugin around reading messages via capacitor

Contains two methods - 

 - getAllMessages
 - getCount

This currently works in android only.

Feel free to submit a Pull request.

# Android

Add `<uses-permission android:name="android.permission.READ_SMS" />` to AndroidManifest.xml

Check for READ SMS permission before actually doing it - otherwise this throws an error.

Sample code - 

```
  async readMessages() {
    await this.androidPermissions.requestPermissions([this.androidPermissions.PERMISSION.READ_SMS]);
    const isPermitted = this.androidPermissions.checkPermission(this.androidPermissions.PERMISSION.READ_SMS);
    if (isPermitted) {
      const messages = await SmsInboxReader.getAllMessages();
      console.log(messages);
    }
  }
```

Note: Here I have used `https://ionicframework.com/docs/native/android-permissions` for checking android read sms permissions.