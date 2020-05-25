package com.nick.firstcode;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends BaseActivity implements View.OnClickListener {

	private Button btFinish,btStartService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btFinish = findViewById(R.id.bt_finish);
		btFinish.setOnClickListener(this);
		btStartService = findViewById(R.id.bt_start_service);
		btFinish.setOnClickListener(this);
		btStartService.setOnClickListener(this);
		test();
	}

	private void test() {
		LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
		instance.sendBroadcast(new Intent("com.nick.first.code.local.broadcast"));
		FileOutputStream fos;
		BufferedWriter writer = null;
		try {
			fos = openFileOutput("data", Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(fos));
			writer.write("this is test data!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			MyService.DownloadBinder downloadBinder = (MyService.DownloadBinder)iBinder;
			downloadBinder.startDownload();
			downloadBinder.getProgress();
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){
			case R.id.item_add:
				Toast.makeText(this, "item add", Toast.LENGTH_SHORT).show();
				break;
			case R.id.item_delete:
				Toast.makeText(this, "item delete", Toast.LENGTH_SHORT).show();
				break;
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.bt_finish:
				finish();
				break;
			case R.id.bt_start_service:
				Intent intent = new Intent(this,MyService.class);
				bindService(intent,connection,BIND_AUTO_CREATE);
				break;
		}
	}
}
