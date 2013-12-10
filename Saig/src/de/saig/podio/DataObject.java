package de.saig.podio;

import java.io.File;

public class DataObject {
	
	private Integer id;
	private String titel;
	private File bild;
	private int appReferenz;
	private Category kategorien;
	private int runde;
	
	public DataObject(String titel, File bild, int appReferenz,
			Category kategorien, int runde) {
		super();
		this.titel = titel;
		this.bild = bild;
		this.appReferenz = appReferenz;
		this.kategorien = kategorien;
		this.runde = runde;
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

	public File getBild() {
		return bild;
	}

	public void setBild(File bild) {
		this.bild = bild;
	}

	public int getAppReferenz() {
		return appReferenz;
	}

	public void setAppReferenz(int appReferenz) {
		this.appReferenz = appReferenz;
	}

	public Category getKategorien() {
		return kategorien;
	}

	public void setKategorien(Category kategorien) {
		this.kategorien = kategorien;
	}

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}
	
}
