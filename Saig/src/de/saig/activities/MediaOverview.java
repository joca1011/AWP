package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MediaOverview extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_overview);
		
		
	}
	

	public void onBackClick (View view) {
		//TODO validate login
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	    
		
	}
}
