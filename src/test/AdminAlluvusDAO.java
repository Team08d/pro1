package test;

import java.sql.Date;

public class AdminAlluvusDAO {
	
	private int admin_alluvus_id;
	private int ylemus_yksus_id;
	private int alluvus_yksus_id;
	
	private Date alates;
	private Date kuni;
	
	private String kommentaar;
	
	private Date avatud;
	private Date muudetud;
	
	public int getAdmin_alluvus_id() {
		return admin_alluvus_id;
	}
	public void setAdmin_alluvus_id(int admin_alluvus_id) {
		this.admin_alluvus_id = admin_alluvus_id;
	}
	public int getYlemus_yksus_id() {
		return ylemus_yksus_id;
	}
	public void setYlemus_yksus_id(int ylemus_yksus_id) {
		this.ylemus_yksus_id = ylemus_yksus_id;
	}
	public int getAlluvus_yksus_id() {
		return alluvus_yksus_id;
	}
	public void setAlluvus_yksus_id(int alluvus_yksus_id) {
		this.alluvus_yksus_id = alluvus_yksus_id;
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
	public String getKommentaar() {
		return kommentaar;
	}
	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
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