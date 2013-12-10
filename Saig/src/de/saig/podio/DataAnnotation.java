package de.saig.podio;

import java.io.File;

public class DataAnnotation {
	
	private Integer id;
	private String titel;
	private int appReferenz;
	private File file;
	
	public DataAnnotation(String titel, int appReferenz, File file) {
		super();
		this.titel = titel;
		this.appReferenz = appReferenz;
		this.file = file;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getAppReferenz() {
		return appReferenz;
	}

	public void setAppReferenz(int appReferenz) {
		this.appReferenz = appReferenz;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
