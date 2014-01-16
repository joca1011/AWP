package de.saig.podio;

import java.util.Date;
import java.util.List;


public class Workshop {
	
	private Integer id;
	private String titel;
	private Date datum;
	private List<Category> innovationGames;
	private int anzahlRunden;
	
	public Workshop(Integer id, String titel, Date datum,
			List<Category> innovationGames, int anzahlRunden) {
		super();
		this.id = id;
		this.titel = titel;
		this.datum = datum;
		this.innovationGames = innovationGames;
		this.anzahlRunden = anzahlRunden;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public List<Category> getInnovationGames() {
		return innovationGames;
	}

	public void setInnovationGames(List<Category> innovationGames) {
		this.innovationGames = innovationGames;
	}

	public int getAnzahlRunden() {
		return anzahlRunden;
	}

	public void setAnzahlRunden(int anzahlRunden) {
		this.anzahlRunden = anzahlRunden;
	}
	
	
	
	
	
	@Override
	public String toString() {
		//This two opportunities of date are just needed for the spinner headline
		String datum;
		if(this.id==0){datum="";}
		else {datum =" (" + this.getDatum().getDate() + "." + this.getDatum().getMonth() + "." + (this.getDatum().getYear()+1900) + ")" ;}
		
		return this.getTitel() + datum;
	}
}
