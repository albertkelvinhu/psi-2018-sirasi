package com.rawsanj.adminlte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mata_pelajaran")
public class MataPelajaran {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	@Size(max = 32)
	private String namaMapel;

	@OneToMany(mappedBy = "mapel")
	private List<Guru> guru;
	
	@OneToMany(mappedBy = "mapelNilai")
    private List<Nilai> nilaiSiswa;
	
	private int kkm;

	public int getKkm() {
		return kkm;
	}

	public void setKkm(int kkm) {
		this.kkm = kkm;
	}

	public List<Guru> getGuru() {
		return guru;
	}

	public void setGuru(List<Guru> guru) {
		this.guru = guru;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaMapel() {
		return namaMapel;
	}

	public void setNamaMapel(String namaMapel) {
		this.namaMapel = namaMapel;
	}

	public List<Nilai> getNilaiSiswa() {
		return nilaiSiswa;
	}

	public void setNilaiSiswa(List<Nilai> nilaiSiswa) {
		this.nilaiSiswa = nilaiSiswa;
	}
	
	
	
}
