package com.nick.firstcode;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author: MaWei
 * @date: 2020-05-14
 * @Description:
 */
public class MyService extends Service {
	private static final String TAG = MyService.class.getSimpleName();

	private DownloadBinder downloadBinder = new DownloadBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return downloadBinder;
	}

	public class DownloadBinder extends Binder {
		public void startDownload(){
			Log.i(TAG,"start download");
		}
		public int getProgress(){
			Log.i(TAG,"get progress");
			return 0;
		}
	}
}
