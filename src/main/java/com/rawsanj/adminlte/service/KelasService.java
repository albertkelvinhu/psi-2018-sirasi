package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.Kelas;
import com.rawsanj.adminlte.model.Siswa;

public interface KelasService {
	
	List <Kelas> listAllKelas();
    Kelas getKelasById(long id);
    Kelas getKelasByKodeKelas(String nip);
    List <Siswa> getSiswaByWaliKelas(Guru guru);
    void saveKelas(Kelas kelas);
    void deleteKelas(int id);

}
