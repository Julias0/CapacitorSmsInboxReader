import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(SmsInboxReader)
public class SmsInboxReader: CAPPlugin {

    @objc func getCount(_ call: CAPPluginCall) {
        call.reject([
            "message": "not available in ios"
        ])
    }

    @objc func getAllMessages(_ call: CAPPluginCall) {
        call.reject([
            "message": "not available in ios"
        ])
    }
}
