package com.rawsanj.adminlte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nilai")
public class Nilai {
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private float nilaiUH;
	private float nilaiTugas;
	private float nilaiUTS;
	private float nilaiUAS;
	private float nilaiAkhir;
	

	private String tahunAjaran;
	private int semester;
	
    @ManyToOne
    @JoinColumn(name = "mapel_id")
	private MataPelajaran mapelNilai;
    
    @ManyToOne
    @JoinColumn(name = "siswa_id")
	private Siswa nilaiSiswa;
    
    @ManyToOne
    @JoinColumn(name = "rapor_id")
	private Rapor raporSiswa;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getNilaiUH() {
		return nilaiUH;
	}

	public void setNilaiUH(float nilaiUH) {
		this.nilaiUH = nilaiUH;
	}

	public float getNilaiTugas() {
		return nilaiTugas;
	}

	public void setNilaiTugas(float nilaiTugas) {
		this.nilaiTugas = nilaiTugas;
	}

	public float getNilaiUTS() {
		return nilaiUTS;
	}

	public void setNilaiUTS(float nilaiUTS) {
		this.nilaiUTS = nilaiUTS;
	}

	public float getNilaiUAS() {
		return nilaiUAS;
	}

	public void setNilaiUAS(float nilaiUAS) {
		this.nilaiUAS = nilaiUAS;
	}

	public float getNilaiAkhir() {
		return nilaiAkhir;
	}

	public void setNilaiAkhir(float nilaiAkhir) {
		this.nilaiAkhir = nilaiAkhir;
	}

	public MataPelajaran getMapelNilai() {
		return mapelNilai;
	}

	public void setMapelNilai(MataPelajaran mapelNilai) {
		this.mapelNilai = mapelNilai;
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

	public Rapor getRaporSiswa() {
		return raporSiswa;
	}

	public void setRaporSiswa(Rapor raporSiswa) {
		this.raporSiswa = raporSiswa;
	}

	public Siswa getNilaiSiswa() {
		return nilaiSiswa;
	}

	public void setNilaiSiswa(Siswa nilaiSiswa) {
		this.nilaiSiswa = nilaiSiswa;
	}
	
	
	
	
	
}
