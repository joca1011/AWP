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

	Spinner spinnerWorkshop;
	Spinner spinnerGame;
	Spinner spinnerRound;
	EditText editText1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_overview);
		
		editText1 = (EditText) findViewById(R.id.editText1);
		
		spinnerWorkshop = (Spinner) findViewById(R.id.spinnerWorkshop);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerWorkshop, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerWorkshop.setAdapter(adapter);
		spinnerWorkshop.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		  ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.spinnerGame, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGame.setAdapter(adapter2);
		spinnerGame.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		spinnerRound = (Spinner) findViewById(R.id.spinnerRound);
		  ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.spinnerRound, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRound.setAdapter(adapter3);
		spinnerRound.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
	}		
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
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
