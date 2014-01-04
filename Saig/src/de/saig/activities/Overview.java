package de.saig.activities;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import de.awp.saig.R;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;
import de.saig.util.Constants;


public class Overview extends Activity {

	Spinner spinnerSettings;
	EditText editText2;
	SharedPreferences sharedPrefs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat, oder von der aus eine solche Activity aufgerufen wird
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
		
		editText2 = (EditText) findViewById(R.id.editText2);
		
		spinnerSettings = (Spinner) findViewById(R.id.spinnerSettings);
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinnerSettings, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSettings.setAdapter(adapter);
		spinnerSettings.setOnItemSelectedListener(new MyOnItemSelectedListener());


		
		/* Podio Test Bereich*/
		PodioService ps = new PodioService();
		
		String a = "nö";
		try {
			Workshop ws = ps.getWorkshopById(this,96788031);
			a = ws.getTitel()+" "+ws.getDatum();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		editText2.setText(a);
		
		/*Ende Podio Test Bereich */
		
		
		/* Shared Preferences Test*/
		
		// TODO: Hier WorkshopID, GameID und Rundennummer in setText ausgeben
		// TODO: Wie geplant oben immer anzeigen welcher Workshop, Game, Round, siehe Todo oben
		// TODO: Danach in Gamesettings einstellen, dass die shared preferances angepasst werden
		
		
		String url = "/podio/workshops";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		 
		
		 sharedPrefs = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	     //String test1 = sharedPrefs.getString(Constants.WORKSHOP_ID, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null);
	      
	    // String test1 = sharedPrefs.getString(getString(Constants.WORKSHOP_ID), null);
	    //		 		sharedPrefs.getString(getString(Constants.GAME_ID), null)+" und "+
	    	//	 		sharedPrefs.getString(getString(Constants.ROUND_NR), null);
		 
		int WorkshopId = sharedPrefs.getInt("WorkshopId",Constants.WORKSHOP_ID);
		int test234 = 6;
		String WorkshopIdString =  Integer.toString(WorkshopId);
	     editText2.setText(WorkshopIdString);
		
		
		/*Shared Preferences Test*/
		
	}
	
	
	public class MyOnItemSelectedListener  implements OnItemSelectedListener  {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			String str = parent.getItemAtPosition(pos).toString();	//normalerweise noch .toUpper()
			
			
			if(pos==0){
				
				
				
				
				//editText2.setText(a);
			}
			else if(pos==1){
				editText2.setText(str);
				showLogout (parent);
				
			}
			
			else if(pos==2){
				editText2.setText(str);	
				showExternalLogins (parent);
			}
			
			else if(pos==3){
				editText2.setText(str);		
				showSettingsGame (parent);
			}
			
			else{
				editText2.setText(str);
			}
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	public void showMedia (View view) {
		Intent intent = new Intent(this, MediaOverview.class);
	    startActivity(intent);
	}
	
	
	
	public void recordVoice (View view) {
		Intent intent = new Intent(this, AudioRecording.class);
	    startActivity(intent);
	}
	
	
	public void showLogout (View view) {
		Intent intent = new Intent(this, Logout.class);
	    startActivity(intent);
	}
	
	
	public void showExternalLogins (View view) {
		Intent intent = new Intent(this, ExternalLogins.class);
	    startActivity(intent);
	}
	
	public void showSettingsGame (View view) {
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}
	
	

	
}
