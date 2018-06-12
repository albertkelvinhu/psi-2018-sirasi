package com.rawsanj.adminlte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perilaku")
public class Perilaku {
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String kerapihan;
	private String kesopanan;
	private String ketertiban;
	private String kebersihan;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKerapihan() {
		return kerapihan;
	}
	public void setKerapihan(String kerapihan) {
		this.kerapihan = kerapihan;
	}
	public String getKesopanan() {
		return kesopanan;
	}
	public void setKesopanan(String kesopanan) {
		this.kesopanan = kesopanan;
	}
	public String getKetertiban() {
		return ketertiban;
	}
	public void setKetertiban(String ketertiban) {
		this.ketertiban = ketertiban;
	}
	public String getKebersihan() {
		return kebersihan;
	}
	public void setKebersihan(String kebersihan) {
		this.kebersihan = kebersihan;
	}

	
}
