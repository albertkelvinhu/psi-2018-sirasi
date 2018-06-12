package com.rawsanj.adminlte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "absensi")
public class Absensi {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int alpha;
	private int izin;
	private int sakit;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	public int getIzin() {
		return izin;
	}
	public void setIzin(int izin) {
		this.izin = izin;
	}
	public int getSakit() {
		return sakit;
	}
	public void setSakit(int sakit) {
		this.sakit = sakit;
	}
	
	
	
}
