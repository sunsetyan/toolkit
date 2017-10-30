package net.xiaoyuyu.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Log.i("TAG", "开机了~~");
            // Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity

            Intent i = new Intent(context, HorizonService.class);
            context.startService(i);

            // mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(mainActivityIntent);
        }
    }
}