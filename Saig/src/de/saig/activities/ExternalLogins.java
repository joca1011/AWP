package de.saig.activities;

import de.awp.saig.R;
import de.saig.podio.PodioService;
import de.saig.util.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExternalLogins extends Activity{
	
	Button saveButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_external_logins);
		
		saveButton = (Button) findViewById(R.id.saved_button);
		
	}
	
	public void onSave (View view) {
		
		SharedPreferences sharedPrefs = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		
		EditText editText = (EditText) findViewById(R.id.external_logins_podio_email);
		editor.putString(Constants.PODIO_EMAIL, editText.getText().toString());
		editText = (EditText) findViewById(R.id.external_logins_podio_password);
		editor.putString(Constants.PODIO_PASSWORD, editText.getText().toString());
		
		editText = (EditText) findViewById(R.id.external_logins_evernote_email);
		editor.putString(Constants.EVERNOTE_EMAIL, editText.getText().toString());
		editText = (EditText) findViewById(R.id.external_logins_evernote_password);
		editor.putString(Constants.EVERNOTE_PASSWORD, editText.getText().toString());
		
		editText = (EditText) findViewById(R.id.external_logins_auphonic_email);
		editor.putString(Constants.AUPHONIC_EMAIL, editText.getText().toString());
		editText = (EditText) findViewById(R.id.external_logins_auphonic_password);
		editor.putString(Constants.AUPHONIC_PASSWORD, editText.getText().toString());
		
		editor.commit();
		    
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
		
	}
	
}
