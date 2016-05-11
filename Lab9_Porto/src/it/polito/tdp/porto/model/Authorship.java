package it.polito.tdp.porto.model;

public class Authorship {
	
	private int id_authorship;
	private long eprintid;
	private int id_creator;
	
	public Authorship(int id_authorship, long eprintid, int id_creator) {
		this.id_authorship = id_authorship;
		this.eprintid = eprintid;
		this.id_creator = id_creator;
	}

	public int getId_authorship() {
		return id_authorship;
	}

	public void setId_authorship(int id_authorship) {
		this.id_authorship = id_authorship;
	}

	public long getEprintid() {
		return eprintid;
	}

	public void setEprintid(long eprintid) {
		this.eprintid = eprintid;
	}

	public int getId_creator() {
		return id_creator;
	}

	public void setId_creator(int id_creator) {
		this.id_creator = id_creator;
	}
	
	
	
	

}
