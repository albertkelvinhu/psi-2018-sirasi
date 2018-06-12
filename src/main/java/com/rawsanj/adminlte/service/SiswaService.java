package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.Siswa;

public interface SiswaService {
	List <Siswa> listAllSiswa();
	long getJumlahSiswa();
    Siswa getSiswaById(long id);
    Siswa getSiswaByNISN(String id);
    Siswa getSiswaByUserId(int id);
    void saveSiswa(Siswa siswa);
    void deleteSiswa(Long id);
	

}
