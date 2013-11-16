package de.saig.podio;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.app.AppAPI;
import com.podio.app.Application;
import com.podio.item.Item;
import com.podio.item.ItemAPI;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUsernameCredentials;

import de.awp.saig.R;
import de.saig.util.Constants;

public class PodioService {
	
	public APIFactory login(Activity activity) {
		Context context = activity;
		SharedPreferences sharedPref = context.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		ResourceFactory resourceFactory = new ResourceFactory(
		        new OAuthClientCredentials(Constants.CLIENT_ID, Constants.CLIENT_SECRET),
		        new OAuthUsernameCredentials(sharedPref.getString(Constants.PODIO_EMAIL, null), sharedPref.getString(Constants.PODIO_PASSWORD, null)));
		
		return new APIFactory(resourceFactory);
	}
	
	public List<String> getWorkshopsNamesForSpinner(Activity activity) {
		APIFactory apiFactory = login(activity);
		
	 AppAPI aApi = apiFactory.getAPI(AppAPI.class);
		Application app = aApi.getApp(Constants.WORKSHOP_APP_ID);
		ItemAPI iApi= apiFactory.getAPI(ItemAPI.class);
		Item item = iApi.getItems(Constants.WORKSHOP_APP_ID);
	}
	
}
