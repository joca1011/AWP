package de.saig.activities;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
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
import android.widget.Spinner;
import android.widget.TextView;
import de.awp.saig.R;
import de.saig.podio.Category;
import de.saig.podio.DataAnnotation;
import de.saig.podio.DataObject;
import de.saig.podio.PodioService;
import de.saig.podio.Workshop;
import de.saig.util.Constants;


public class SettingsGame extends Activity{
	
	Spinner spinnerWorkshop;
	Spinner spinnerGame;
	Spinner spinnerRound;
	SharedPreferences preferences;
	PodioService ps = new PodioService();
	TextView textView1;

	Category selectedCategory = null;
	Workshop selectedWorkshop = null;
	int selectedRound;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_game);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		
		

		
		// Dieser Teil muss in jede Activity die einen Netzwerkzugriff hat
		StrictMode.ThreadPolicy policy = new 
		StrictMode.ThreadPolicy.Builder().permitAll().build(); 
		StrictMode.setThreadPolicy(policy);
		
	
		
		//Aktuellen Workshop bestimmen
		preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);

		
		spinnerWorkshop = (Spinner) findViewById(R.id.settings_workshop_spinner);
		ArrayAdapter<Workshop> workshopAdapter = null;
		try {
			workshopAdapter = new ArrayAdapter  <Workshop> (this, android.R.layout.simple_spinner_item, ps.getWorkshops(this) );
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
		spinnerWorkshop.setAdapter(workshopAdapter);
		spinnerWorkshop.setOnItemSelectedListener(new MyOnWorkshopSelectedListener());
		
		
		if (preferences.getInt("currentWorkshopId",-1) != -1) {
			for (int i = 0; i < workshopAdapter.getCount(); i++) {
				if(workshopAdapter.getItem(i).getId() == preferences.getInt("currentWorkshopId",-1)){
					spinnerWorkshop.setSelection(i);
					setSpinnerGame(workshopAdapter.getItem(i).getInnovationGames());
				}
			}
		}

	}
	
	

	public class MyOnWorkshopSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			
			
			selectedWorkshop = (Workshop) parent.getItemAtPosition(pos);
			setSpinnerGame(selectedWorkshop.getInnovationGames());
			setSpinnerRounds(selectedWorkshop.getAnzahlRunden());
			
			
			
			  int workshopId = selectedWorkshop.getId();
			  preferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);


			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("currentWorkshopName",selectedWorkshop.getTitel());
			  editor.putInt("currentWorkshopId",selectedWorkshop.getId());
			  
			  editor.commit();
			  
			  
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	

	
	public void setSpinnerRounds(int rounds) {
		List<Integer> listRounds = new ArrayList<Integer>();
		for (int i = 1; i <= rounds; i++) {
			listRounds.add(i);
		}
		
		spinnerRound = (Spinner) findViewById(R.id.spinnerRound);
		ArrayAdapter<Integer> roundAdapter = new ArrayAdapter  <Integer> (this, android.R.layout.simple_spinner_item, listRounds );
		spinnerRound.setAdapter(roundAdapter);
		spinnerRound.setOnItemSelectedListener(new MyOnRoundSelectedListener());
	}
	
	
	public class MyOnGameSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
		     selectedCategory = (Category) parent.getItemAtPosition(pos);
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("currentGameName",selectedCategory.getString());
			  editor.putInt("currentGameId",selectedCategory.getId());
			  editor.commit();
		     
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}
	
	
	
	
	
	public class MyOnRoundSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {
			selectedRound = (Integer) parent.getItemAtPosition(pos);
			
			SharedPreferences.Editor editor = preferences.edit();
			editor.putInt("currentRoundId",selectedRound);
			editor.commit();
			  
		}
		
		@Override
		public void onNothingSelected(AdapterView parent){
			//Tue nichts
		}
	}	
	
	
	
	public int  getGamePositionInSpinner(List<DataObject> listGames,int currentGameId){
		int idx = 0;
		int pos = 0;
		for (DataObject w : listGames) {
		    if(w.getId()==currentGameId){pos=idx; break;}
		    idx++;
		}
		return pos;
	}
	
	
	
	public int  getWorkshopPositionInSpinner(List<Workshop> listAllWorkshops,int currentWorkshopId){
		int idx = 0;
		int pos = 0;
		for (Workshop w : listAllWorkshops) {
		    if(w.getId()==currentWorkshopId){pos=idx; break;}
		    idx++;
		}
		return pos;
	}
	
	
	
	
	
	public void setSpinnerGame(){
		List<Workshop> listAllWorkshops = null;
		try {
			listAllWorkshops = ps.getWorkshops(this);
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
		
		//listAllWorkshops.add( 0, new Workshop(0, "bitte Workshop auswählen",new Date(),new ArrayList<Category>(), 0) );
		
		
		int currentWorkshopId = preferences.getInt("currentWorkshopId",0);
		Workshop selectedWorkshop = null;
		if (currentWorkshopId != 0) {
		try {
			selectedWorkshop = ps.getWorkshopById(this, currentWorkshopId);
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
		}
		else {
			selectedWorkshop = listAllWorkshops.get(0);
		}
		
		List<Category> games = new ArrayList<Category>(Arrays.asList(Category.values()));
		ArrayAdapter  <Category> gameAdapter = 
				new ArrayAdapter  <Category> (this, android.R.layout.simple_spinner_item, selectedWorkshop.getInnovationGames());
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		spinnerGame.setAdapter(gameAdapter);
		/*
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		*/
		//spinnerGame.setSelection(gameListPos);
	}
	
	
	
	
	
	public void setSpinnerGame(List<Category> games){
		//Spinner Game	
		List<DataObject> listAllGamesAndRoundsOfTheWorkshop = null;
		
	

		spinnerGame = (Spinner) findViewById(R.id.spinnerGame);
		ArrayAdapter  <Category> gameAdapter = 
				new ArrayAdapter  <Category> (this, android.R.layout.simple_spinner_item, games );
		spinnerGame.setAdapter(gameAdapter);
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		//spinnerGame.setSelection(gameListPos);
	}
	
	
	public void changeSpinnerGame(int currentWorkshopId){
		//Spinner Game	
		
		
		
		/*
		
		List<DataObject> listAllGamesAndRoundsOfTheW = null;
		
		if(currentWorkshopId!=0)		//Means: If a workshop is selected
		{
			try {
				listAllGamesAndRoundsOfTheW = ps.getObjectsByWorkshopId(this,currentWorkshopId);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//For the spinner Headline
	
		try {
		listAllGamesAndRoundsOfTheW.add( 0, new DataObject("bitte Auswählen") );
		} 
		catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spinnerGame = (Spinner) findViewById(R.id.spinnerGame); 
		
		
		ArrayAdapter  <DataObject> gameAdapter = 
				new ArrayAdapter  <DataObject> (this, android.R.layout.simple_spinner_item, listAllGamesAndRoundsOfTheW );
		
		
		gameAdapter.notifyDataSetChanged();
		
		
		
		gameAdapter = (ArrayAdapter) spinnerGame.getAdapter();
		gameAdapter.notifyDataSetChanged();
		
		
		//spinnerGame.setAdapter(gameAdapter);
		
		/*
		spinnerGame.setOnItemSelectedListener(new MyOnGameSelectedListener());
		*/
		
	
	
	}

	
	
	
	
	//Neuen Workshop erstellen
	public void newWorkshop (View view) {
		Intent intent = new Intent(this, NewWorkshop.class);
	    startActivity(intent);
	}
	
	//Neues Spiel erstellen
	public void newGame (View view) {
		Intent intent = new Intent(this, NewGame.class);
	    startActivity(intent);
	}	
	
	//Neue Runde erstellen
	public void newRound (View view) {
		Intent intent = new Intent(this, NewRound.class);
	    startActivity(intent);
	}
	

	
	public void backToOverview (View view) {
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
	
	
	public void saveSettingsGame (View view) {
		//TODO Funktionalität, dass hier dann auch gespeichert wird fehlt noch
		Intent intent = new Intent(this, Overview.class);
	    startActivity(intent);
	}
	
	public void reload(View view) {
		//TODO Funktionalität, dass hier dann auch gespeichert wird fehlt noch
		Intent intent = new Intent(this, SettingsGame.class);
	    startActivity(intent);
	}
	
	
}
