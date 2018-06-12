package com.rawsanj.adminlte.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "siswa")
public class Siswa {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(min = 10, max = 10, message = "Masukkan NISN yang valid")
	@Column(unique = true)
	private String nisn;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String lastName;

	@Column
	private String jenisKelamin;

	@Column
	private String alamat;

	@Column
	private int tahunMasuk;

	@Column
	private Date tglLahir;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "kelas_id")
	private Kelas kelas;

	@OneToMany(mappedBy = "raporSiswa")
	private List<Rapor> raporSiswa;
	
	@OneToMany(mappedBy = "nilaiSiswa")
	private List<Nilai> nilaiSiswa;

	@Column
	private String path;

	public Siswa(long id, String nisn, String name, String lastName, String jenisKelamin, String alamat, int tahunMasuk,
			Date tglLahir, User user, Kelas kelas, List<Nilai> nilaiSiswa, String path) {
		super();
		this.id = id;
		this.nisn = nisn;
		this.name = name;
		this.lastName = lastName;
		this.jenisKelamin = jenisKelamin;
		this.alamat = alamat;
		this.tahunMasuk = tahunMasuk;
		this.tglLahir = tglLahir;
		this.user = user;
		this.kelas = kelas;
		this.path = path;
	}

	public Siswa() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// setter getter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNisn() {
		return nisn;
	}

	public void setNisn(String nisn) {
		this.nisn = nisn;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(int tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}

	public Kelas getKelas() {
		return kelas;
	}

	public void setKelas(Kelas kelas) {
		this.kelas = kelas;
	}

	public List<Rapor> getRaporSiswa() {
		return raporSiswa;
	}

	public void setRaporSiswa(List<Rapor> raporSiswa) {
		this.raporSiswa = raporSiswa;
	}

	public List<Nilai> getNilaiSiswa() {
		return nilaiSiswa;
	}

	public void setNilaiSiswa(List<Nilai> nilaiSiswa) {
		this.nilaiSiswa = nilaiSiswa;
	}
	
	
	

}
