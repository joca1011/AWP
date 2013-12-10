package de.saig.podio;

import java.util.Date;

public class Workshop {
	
	private Integer id;
	private String titel;
	private Date datum;
	private Category innovationGames;
	private int anzahlRunden;
	
	public Workshop(Integer id, String titel, Date datum,
			Category innovationGames, int anzahlRunden) {
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

	public Category getInnovationGames() {
		return innovationGames;
	}

	public void setInnovationGames(Category innovationGames) {
		this.innovationGames = innovationGames;
	}

	public int getAnzahlRunden() {
		return anzahlRunden;
	}

	public void setAnzahlRunden(int anzahlRunden) {
		this.anzahlRunden = anzahlRunden;
	}
	
}
