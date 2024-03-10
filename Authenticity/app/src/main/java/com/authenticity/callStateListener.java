package com.authenticity;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class callStateListener extends PhoneStateListener {

    private Context context;

    public callStateListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String phoneNumber) {
        super.onCallStateChanged(state, phoneNumber);

        if (state == TelephonyManager.CALL_STATE_RINGING) {
            // Incoming call
            Log.d("MyPhoneStateListener", "Incoming call from: " + phoneNumber);
        }
    }
}
