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

                //Toast.makeText(HorizonService.this, " 打印时间", Toast.LENGTH_SHORT).show();
//              try {
                boolean enableAdb = (Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0) > 0);
                Log.d("TAG", "adb-enable : " + enableAdb);
                //Toast.makeText(HorizonService.this, " adb-enable"  + enableAdb, Toast.LENGTH_SHORT).show();
                Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, 1);
                // if (!enableAdb) {
                    // 这个没法通过非工程机来做
                    // Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, 1);
                    // Log.i("TAG", "adb-enable : " + Settings.Global.ADB_ENABLED + ", success");
                    //Toast.makeText(HorizonService.this, "adb-enable : " + Settings.Global.ADB_ENABLED + ", auto success",
                      //      Toast.LENGTH_SHORT).show();
                // }
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