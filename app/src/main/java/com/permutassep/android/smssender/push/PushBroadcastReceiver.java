package com.permutassep.android.smssender.push;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushBroadcastReceiver extends ParsePushBroadcastReceiver {

    public static final String PUSH_PHONE_NUMBERS_EXTRA = "phone_numbers";
    public static final String PUSH_POSSIBLE_PHONE_NUMBERS_EXTRA = "possible_phone_numbers";

    @Override
    protected void onPushReceive(Context context, Intent intent) {

        String smsContent = "Hola! Veo que buscas una permuta. Esta app te puede ayudar a encontrarla https://play.google.com/store/apps/details?id=com.permutassep";
        SmsManager smsManager = SmsManager.getDefault();

        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra(KEY_PUSH_DATA));
            JSONArray phoneNumbers = pushData.getJSONArray(PUSH_PHONE_NUMBERS_EXTRA);
            for (int i = 0; i < phoneNumbers.length(); i++) {
                smsManager.sendTextMessage(phoneNumbers.getString(i), null, smsContent, null, null);
            }
            JSONArray possiblePhoneNumbers = pushData.getJSONArray(PUSH_POSSIBLE_PHONE_NUMBERS_EXTRA);
            for (int i = 0; i < possiblePhoneNumbers.length(); i++) {
                smsManager.sendTextMessage(possiblePhoneNumbers.getString(i), null, smsContent, null, null);
            }

        } catch (JSONException e) {
            try {
                JSONObject pushData = new JSONObject(intent.getStringExtra(KEY_PUSH_DATA));
                JSONArray possiblePhoneNumbers = pushData.getJSONArray(PUSH_POSSIBLE_PHONE_NUMBERS_EXTRA);
                for (int i = 0; i < possiblePhoneNumbers.length(); i++) {
                    smsManager.sendTextMessage(possiblePhoneNumbers.getString(i), null, smsContent, null, null);
                }
            } catch (JSONException mE) {
                mE.printStackTrace();
            }
        }
    }
}
