package com.nick.firstcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author: MaWei
 * @date: 2020-05-11
 * @Description:
 */
public class BaseActivity extends AppCompatActivity {
	private static final String TAG = "BaseActivity";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG,"Current Activity: "+this.getClass().getSimpleName());
	}
}
