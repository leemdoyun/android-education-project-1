package com.example.sample6viewanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView)findViewById(R.id.imageView1);
		Button btn = (Button)findViewById(R.id.btn_translate);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_anim);
				imageView.startAnimation(anim);
			}
		});
		
		btn = (Button)findViewById(R.id.btn_rotate);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anim);
				imageView.startAnimation(anim);
			}
		});
		
		btn = (Button)findViewById(R.id.btn_scale);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_anim);
				imageView.startAnimation(anim);
			}
		});
		
		btn = (Button)findViewById(R.id.btn_alpha);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpah_anim);
				imageView.startAnimation(anim);
			}
		});

		btn = (Button)findViewById(R.id.btn_set);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set_anim);
				imageView.startAnimation(anim);
			}
		});

		btn = (Button)findViewById(R.id.btn_set2);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set2_anim);
				imageView.startAnimation(anim);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
