package de.saig.activities;

import de.awp.saig.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
	}
	
	public void signInClick (View view) {
		
		
		//TODO Hier ist vorerst eine Verlinkung zurück. Die Restrierung muuss hier implementiert werden.
		Intent intent = new Intent(this, Login.class);
	    startActivity(intent);
	    
		
	}
	
}
