package test;

import java.sql.Date;

public class RiigiAdminYksuseLiikDAO {
	
	private int riigiAdminYksuseLiikId;
	
	private String kood;
	private String nimetus;
	private String kommentaar;
	
	private Date alates;
	private Date kuni;
	
	private Date avatud;
	private Date muudetud;
	
	public int getRiigiAdminYksuseLiikId() {
		return riigiAdminYksuseLiikId;
	}
	public void setRiigiAdminYksuseLiikId(int riigiAdminYksuseLiikId) {
		this.riigiAdminYksuseLiikId = riigiAdminYksuseLiikId;
	}
	public String getKood() {
		return kood;
	}
	public void setKood(String kood) {
		this.kood = kood;
	}
	public String getNimetus() {
		return nimetus;
	}
	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}
	public String getKommentaar() {
		return kommentaar;
	}
	public void setKommentaar(String kommentraar) {
		this.kommentaar = kommentaar;
	}
	public Date getAlates() {
		return alates;
	}
	public void setAlates(Date alates) {
		this.alates = alates;
	}
	public Date getKuni() {
		return kuni;
	}
	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}
	public Date getAvatud() {
		return avatud;
	}
	public void setAvatud(Date avatud) {
		this.avatud = avatud;
	}
	public Date getMuudetud() {
		return muudetud;
	}
	public void setMuudetud(Date muudetud) {
		this.muudetud = muudetud;
	}

}
