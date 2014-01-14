package de.saig.podio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
				List<Category> innovationGames = new ArrayList<Category>();
				int runden = json.getInt("anzahl-runden");
				
				switch (json.getInt("innovation-games")) {
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
				
		return new Workshop(id, titel, datum, innovationGames, runden);				
	}
	
	public List<DataAnnotation> getAnnotationsByItemId(Activity activity, int id) throws JSONException, IllegalStateException, IOException {
		String url = "/podio/items/"+ id + "/annotation";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    List<DataAnnotation> dataAnnotations = new ArrayList<DataAnnotation>();
		    JSONArray json = new JSONArray(out.toString());
		    for (int i = 0; i < json.length(); i++) {
				JSONObject jo = json.getJSONObject(i);
				
				id = jo.getInt("id");
				String titel = jo.getString("titel");
				int appReferenz = jo.getInt("app-referenz");
				int fileId = jo.getInt("file-id");
				dataAnnotations.add(new DataAnnotation(id, titel, appReferenz, null));
		    }
		return dataAnnotations;
	}
	
	public DataAnnotation getAnnotationById(Activity activity, int id) throws ClientProtocolException, IOException, JSONException {
		String url = "/podio/annotations";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/"+id+"/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    JSONObject json = new JSONObject(out.toString());
		id = json.getInt("id");
		String titel = json.getString("titel");
		int appreferenz = json.getInt("app-referenz");
		int fileId = json.getInt("file-id");
		
		return new DataAnnotation(id, titel, appreferenz, null);
	}
	
	public DataObject getObjectById(Activity activity, int id) throws JSONException, ClientProtocolException, IOException {
		
		String url = "/podio/items";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/"+id+"/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    JSONObject json = new JSONObject(out.toString());
		    
				id = json.getInt("id");
				String titel = json.getString("titel");
				int bild= json.getInt("bild");
				int app_referenz = json.getInt("app-referenz");
				Category kategorie = null;
				int runde = json.getInt("runde");
		
				
				
				switch (json.getInt("kategorien")) {	
	case 1:{
		kategorie = Category.ME_AND_MY_SHADOW;
		break;
	}
	case 2:{
		kategorie = Category.PRODUCT_BOX;
		break;
	}
	case 3:{
		kategorie = Category.SPEED_BOAT;
		break;
	}
	case 4:{
		kategorie = Category.PRUNE_THE_PRODUCT_TREE;
		break;
	}

	default:
		break;
	}
				
				return new DataObject(titel, null, app_referenz, kategorie, runde);
	}
	
	public List<DataObject> getObjectsByWorkshopId(Activity activity, int id) throws ClientProtocolException, IOException, JSONException {
		String url = "/podio/workshops/"+ id + "/items";
		InputStream content = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		
		  SharedPreferences sharedPrefs = activity.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
	      
	      HttpGet httpGet = new HttpGet(url+"/"+sharedPrefs.getString(Constants.PODIO_EMAIL, null)+"/"+sharedPrefs.getString(Constants.PODIO_PASSWORD, null));
	      
	      
	      
	      HttpResponse response = httpclient.execute(new HttpHost(PodioConfig.host), httpGet);
		    
		    content = response.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    StringBuilder out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    List<DataObject> dataObjects = new ArrayList<DataObject>();
		    JSONArray json = new JSONArray(out.toString());
		    for (int i = 0; i < json.length(); i++) {
				JSONObject jo = json.getJSONObject(i);
			
				id = jo.getInt("id");
				String titel = jo.getString("titel");
				int bild= jo.getInt("bild");
				int app_referenz = jo.getInt("app-referenz");
				Category kategorie = null;
				int runde = jo.getInt("runde");
		
				
				
				switch (jo.getInt("kategorien")) {	
	case 1:{
		kategorie = Category.ME_AND_MY_SHADOW;
		break;
	}
	case 2:{
		kategorie = Category.PRODUCT_BOX;
		break;
	}
	case 3:{
		kategorie = Category.SPEED_BOAT;
		break;
	}
	case 4:{
		kategorie = Category.PRUNE_THE_PRODUCT_TREE;
		break;
	}

	default:
		break;
	}
				dataObjects.add(new DataObject(titel, null, app_referenz, kategorie, runde));
		    }
		    return dataObjects;
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
	
	
	
	
	
	public String uploadFile(String sourceFileUri) throws IOException {


	      String fileName = sourceFileUri;

	      HttpURLConnection conn = null;
	      DataOutputStream dos = null;  
	      String lineEnd = "\r\n";
	      String twoHyphens = "--";
	      String boundary = "*****";
	      int bytesRead, bytesAvailable, bufferSize;
	      byte[] buffer;
	      int maxBufferSize = 1 * 1024 * 1024; 
	      File sourceFile = new File(sourceFileUri); 

	     
	      
	           

	                 // open a URL connection to the Servlet
	               FileInputStream fileInputStream = new FileInputStream(sourceFile);
	               URL url = new URL(PodioConfig.host+"/upload");

	               // Open a HTTP  connection to  the URL
	               conn = (HttpURLConnection) url.openConnection(); 
	               conn.setDoInput(true); // Allow Inputs
	               conn.setDoOutput(true); // Allow Outputs
	               conn.setUseCaches(false); // Don't use a Cached Copy
	               conn.setRequestMethod("POST");
	               conn.setRequestProperty("Connection", "Keep-Alive");
	               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	               conn.setRequestProperty("uploaded_file", fileName); 

	               dos = new DataOutputStream(conn.getOutputStream());

	               dos.writeBytes(twoHyphens + boundary + lineEnd); 
	               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
	                                         + fileName + "\"" + lineEnd);

	               dos.writeBytes(lineEnd);

	               // create a buffer of  maximum size
	               bytesAvailable = fileInputStream.available(); 

	               bufferSize = Math.min(bytesAvailable, maxBufferSize);
	               buffer = new byte[bufferSize];

	               // read file and write it into form...
	               bytesRead = fileInputStream.read(buffer, 0, bufferSize);  

	               while (bytesRead > 0) {

	                 dos.write(buffer, 0, bufferSize);
	                 bytesAvailable = fileInputStream.available();
	                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);   

	                }

	               // send multipart form data necesssary after file data...
	               dos.writeBytes(lineEnd);
	               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

	               // Responses from the server (code and message)
	               //serverResponseCode = conn.getResponseCode();
	               String serverResponseMessage = conn.getResponseMessage();

	              

	              

	               //close the streams //
	               fileInputStream.close();
	               dos.flush();
	               dos.close();
	               return serverResponseMessage;
	     } 
	
	
	
}
