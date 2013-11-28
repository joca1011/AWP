package de.saig.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import de.awp.saig.R;

public class MediaOverview extends Activity{

	Spinner spinnerTest1;
	EditText editText1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_overview);
		spinnerTest1 = (Spinner) findViewById(R.id.spinnerTest1);
		editText1 = (EditText) findViewById(R.id.editText1);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerTestArray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTest1.setAdapter(adapter);
		spinnerTest1.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
	}		
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString().toUpperCase();
			editText1.setText(str);
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}

	public void onBackClick (View view) {
		//TODO validate login
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	    
		
	}
}
