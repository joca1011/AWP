package de.saig.podio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.awp.saig.R;
import de.saig.util.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;


public class PodioService {
	//Activity wird wegen SharedPreferences als Context gebraucht
	public List<Workshop> getWorkshops(Activity activity) throws ClientProtocolException, IOException, JSONException, ParseException {
		String url = "/podio/workshops";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/all/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      //SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
//	      
//	      HttpParams params = new BasicHttpParams();
//	      params.setParameter("podio_email", sharedPrefs.getString(Constants.PODIO_EMAIL, null));
//	      params.setParameter("podio_password", sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
//	      httpGet.setParams(params);
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    List<Workshop> workshops = new ArrayList<Workshop>();
		    JSONArray json = new JSONArray(out.toString());
		    for (int i = 0; i < json.length(); i++) {
				JSONObject jo = json.getJSONObject(i);
				Integer id = jo.getInt("id");
				String titel = jo.getString("titel");
				Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(jo.getString("datum"));
				List<Category> innovationGames = new ArrayList<Category>();
				int runden = jo.getInt("anzahl-runden");
				
				switch (jo.getInt("innovation-games")) {
				case 1:{
					innovationGames.add(Category.ME_AND_MY_SHADOW);
					break;
				}
				case 2:{
					innovationGames.add(Category.PRODUCT_BOX);
					break;
				}
				case 3:{
					innovationGames.add(Category.SPEED_BOAT);
					break;
				}
				case 4:{
					innovationGames.add(Category.PRUNE_THE_PRODUCT_TREE);
					break;
				}

				default:
					break;
				}
				
				workshops.add(new Workshop(id, titel, datum, innovationGames, runden));
			}
		    return workshops;
	}
	
	public Workshop getWorkshopById(Activity activity, int idWorkshop) throws ClientProtocolException, IOException, JSONException, ParseException  {
		
		String url = "/podio/workshops";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/"+idWorkshop+"/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    JSONObject json = new JSONObject(out.toString());
		    
				Integer id = json.getInt("id");
				String titel = json.getString("titel");
				Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("datum"));
				Category innovationGames = null;
				int runden = json.getInt("anzahl-runden");
				
				switch (json.getInt("innovation-games")) {
				case 1:{
					innovationGames = Category.ME_AND_MY_SHADOW;
					break;
				}
				case 2:{
					innovationGames = Category.PRODUCT_BOX;
					break;
				}
				case 3:{
					innovationGames = Category.SPEED_BOAT;
					break;
				}
				case 4:{
					innovationGames = Category.PRUNE_THE_PRODUCT_TREE;
					break;
				}

				default:
					break;
				}
				
		return new Workshop(id, titel, datum, innovationGames, runden);				
	}
	
	public List<DataAnnotation> getAnnotationsByItemId(Activity activity) {
		return null;
		
	}
	
	public DataAnnotation getAnnotationById(Activity activity, int id) {
		return null;
		
	}
	
	public DataObject getObjectById(Activity activity, int id) {
		return null;
		
	}
	
	public List<DataObject> getObjectsByWorkshopId(Activity activity, int id) {
		return null;
		
	}
	
	//Adds geben das Objekt mit der generierten Id zurück
	public DataObject addDataObject(Activity activity, DataObject dataObject) throws ClientProtocolException, IOException {
		String url = "/podio/items";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
	      HttpPost httppost = new HttpPost(url);
	      
	      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("titel", dataObject.getTitel()));
	        nameValuePairs.add(new BasicNameValuePair("bild", "68611780"));
	        nameValuePairs.add(new BasicNameValuePair("app-referenz", "96788073"));
	        nameValuePairs.add(new BasicNameValuePair("kategorien", "1"));
	        nameValuePairs.add(new BasicNameValuePair("runde", "2"));
	        nameValuePairs.add(new BasicNameValuePair("podio_email", "norosoboshi@gmail.com"));
	        nameValuePairs.add(new BasicNameValuePair("podio_password", "cheese44"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	    HttpResponse response = httpclient.execute(new HttpHost("10.0.2.2"), httppost);
	    
	    content = response.getEntity().getContent();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	    StringBuilder out = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        out.append(line);
	    }
	    dataObject.setId(Integer.valueOf(out.toString()));
	        
		return dataObject;
	}
	
	public DataAnnotation addDataAnnotation(Activity activity, DataAnnotation dataAnnotation) {
		return dataAnnotation;
		
	}
	
	
	
}
