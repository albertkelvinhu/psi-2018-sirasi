package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.Nilai;
import com.rawsanj.adminlte.model.Rapor;

public interface ReportService {
	List <Rapor> listAllRapor();
	
	Rapor getRaporById(long id);
	Rapor getRaporBySemesterAndTA(int semester, String ta);
	
	List <Nilai> getNilaiByRapor(long id);
    List <Rapor> bySiswaId(long id);
    	
	void saveRapor(Rapor rapor);
	void saveRaporForKelasSiswa(long idKelas, Rapor rapor);
	
    void deleteRapor(long id);
    
    float getRataNilaiAkhir(long reportId);
    float getJumlahNilaiAkhir(long reportId);
    
    
}
