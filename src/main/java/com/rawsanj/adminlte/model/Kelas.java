package com.rawsanj.adminlte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kelas")
public class Kelas {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    @Column(unique = true)
	private String kodeKelas;
	
    @OneToMany(mappedBy = "kelas")
    private List<Siswa> siswa;
    
    @OneToOne(mappedBy = "kelas")
    private Guru waliKelas;
    
	public List<Siswa> getSiswa() {
		return siswa;
	}
	public void setSiswa(List<Siswa> siswa) {
		this.siswa = siswa;
	}
	public Guru getWaliKelas() {
		return waliKelas;
	}
	public void setWaliKelas(Guru waliKelas) {
		this.waliKelas = waliKelas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodeKelas() {
		return kodeKelas;
	}
	public void setKodeKelas(String kodeKelas) {
		this.kodeKelas = kodeKelas;
	}
	
	
	
	
}
