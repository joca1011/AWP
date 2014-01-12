package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MediaDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_detail);
	}
	
	public void save(View view) {
		//TODO validate login
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
	
	
}
