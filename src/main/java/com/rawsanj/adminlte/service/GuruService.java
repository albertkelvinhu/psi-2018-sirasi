package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.MataPelajaran;

public interface GuruService {
	List <Guru> listAllGuru();
	long getJumlahGuru();
    Guru getGuruById(Long id);
    Guru getGuruByUserId(int id);
    
    
    Guru getGuruByNIP(String nip);
    MataPelajaran getMapelByGuruId(Long id);
    void saveGuru(Guru guru);
    void deleteGuru(Long id);

}
