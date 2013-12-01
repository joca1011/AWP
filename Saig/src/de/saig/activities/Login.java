package de.saig.activities;

import de.awp.saig.R;
import de.saig.podio.PodioService;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
	}
	
	public void signIn (View view) {
		//TODO validate login
		Intent intent = new Intent(this, ExternalLogins.class);
	    startActivity(intent);
	    
		
	}
	
	public void signUp (View view) {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);
	}
	

	public void onClickEvernote (View view) {
		//TODO validate login
		Intent intent = new Intent(this, HelloEDAM.class);
	    startActivity(intent);
	    
		
	}
	
}
