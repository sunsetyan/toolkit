package net.xiaoyuyu.timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, HorizonService.class);
        startService(intent);

        // 启动本地服务和远程服务
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));

        setContentView(R.layout.activity_main);


    }
}
