package com.rawsanj.adminlte.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rapor")
public class Rapor {
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String status;
	private String tahunAjaran;
	private int semester;

	@ManyToOne
	@JoinColumn(name = "siswa_id")
	private Siswa raporSiswa;
	
	
	//FetchType.LAZY = fetch when needed(default)
	//FetchType.EAGER = fetch immediately
	//
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guru_id")
	private Guru waliKelas;

	@OneToMany(mappedBy = "raporSiswa")
	private List<Nilai> nilai;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "perilaku_id")
	private Perilaku perilaku;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "absensi_id")
	private Absensi absensi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTahunAjaran() {
		return tahunAjaran;
	}

	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Siswa getRaporSiswa() {
		return raporSiswa;
	}

	public void setRaporSiswa(Siswa raporSiswa) {
		this.raporSiswa = raporSiswa;
	}

	public List<Nilai> getNilai() {
		return nilai;
	}

	public void setNilai(List<Nilai> nilai) {
		this.nilai = nilai;
	}

	public Perilaku getPerilaku() {
		return perilaku;
	}

	public void setPerilaku(Perilaku perilaku) {
		this.perilaku = perilaku;
	}

	public Absensi getAbsensi() {
		return absensi;
	}

	public void setAbsensi(Absensi absensi) {
		this.absensi = absensi;
	}

	public Guru getWaliKelas() {
		return waliKelas;
	}

	public void setWaliKelas(Guru waliKelas) {
		this.waliKelas = waliKelas;
	}
	
	

}
