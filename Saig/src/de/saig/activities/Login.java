package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
	}
	
	public void signIn (View view) {
		//TODO validate login
		
	}
	
	public void signUp (View view) {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);

	}
	
}
