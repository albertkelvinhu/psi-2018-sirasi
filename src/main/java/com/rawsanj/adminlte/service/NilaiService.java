package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.model.Nilai;

public interface NilaiService {
	List <Nilai> listAllNilai();
	
	Nilai getMapelById(int id);
	List <Nilai> getNilaiBySiswaMapel(long idSiswa,int idMapel);
    
	void saveNilai(Nilai nilai);
    void deleteNilai(int id);

}
