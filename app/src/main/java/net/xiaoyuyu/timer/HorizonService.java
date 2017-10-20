package net.xiaoyuyu.timer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import java.util.Date;

public class HorizonService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "打印时间: " + new Date().toString());

//              try {
                boolean enableAdb = (Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0) > 0);
                Log.d("TAG", "adb-enable : " + enableAdb);

                if (!enableAdb) {
                    Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, 1);
                    Log.i("TAG", "adb-enable : " + Settings.Global.ADB_ENABLED + ", success");
                }
//                }catch (Settings.SettingNotFoundException e) {
//
//                }
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int five = 5000; // 这是5s
        long triggerAtTime = SystemClock.elapsedRealtime() + five;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }


}