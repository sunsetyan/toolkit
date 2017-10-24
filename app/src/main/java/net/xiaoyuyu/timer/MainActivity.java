package net.xiaoyuyu.timer;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ResolveInfo> apps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, HorizonService.class);
        startService(intent);

        // 启动本地服务和远程服务
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));

        // 查看所有应用的包名
        loadApps();
        setContentView(R.layout.activity_main);
    }

    private void loadApps() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        apps = getPackageManager().queryIntentActivities(intent, 0);
        //for循环遍历ResolveInfo对象获取包名和类名
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packageName = info.activityInfo.packageName;
            CharSequence cls = info.activityInfo.name;
            CharSequence name = info.activityInfo.loadLabel(getPackageManager());
            Log.e("app packages: ", name + "----" + packageName + "----" + cls);
        }
    }

}
