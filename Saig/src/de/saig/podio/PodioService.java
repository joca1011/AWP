package de.saig.podio;

import java.util.List;
import android.app.Activity;


public class PodioService {
	//Activity wird wegen SharedPreferences als Context gebraucht
	public List<Workshop> getWorkshops(Activity activity) {
		return null;
		
	}
	
	public Workshop getWorkshopById(Activity activity, int id) {
		return null;
		
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
	
	//Adds geben das Objekt mit der generierten Id zurück
	public DataObject addDataObject(Activity activity, DataObject dataObject) {
		return dataObject;
		
	}
	
	public DataAnnotation addDataAnnotation(Activity activity, DataAnnotation dataAnnotation) {
		return dataAnnotation;
		
	}
	
	
	
}
