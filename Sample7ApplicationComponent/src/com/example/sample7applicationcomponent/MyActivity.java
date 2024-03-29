package com.example.sample7applicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends ActionBarActivity {
	TextView messageView;
	public static final String EXTRA_MESSAGE = "message";
	public static final String EXTRA_AGE = "age";
	public static final String EXTRA_NAME = "name";
	public static final String EXTRA_PERSON = "person";
	public static final String PARAM_RESULT_MESSAGE = "resultMessage";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		messageView = (TextView)findViewById(R.id.text_message);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(EXTRA_MESSAGE);
		int age = intent.getIntExtra(EXTRA_AGE, 0);
		String name = intent.getStringExtra(EXTRA_NAME);
//		Person p = (Person)intent.getSerializableExtra(EXTRA_PERSON);
		Person p = intent.getParcelableExtra(EXTRA_PERSON);
		messageView.setText(message);	
				
		Button btn = (Button)findViewById(R.id.btn_finish);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String rmessage = "echo : " + messageView.getText().toString();
				Intent data = new Intent();
				data.putExtra(PARAM_RESULT_MESSAGE, rmessage);
				setResult(Activity.RESULT_OK, data);
				finish();
//				overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
			}
		});
	}
}
