package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.MataPelajaran;

public interface MapelService {
	
	List <MataPelajaran> listAllMapel();
	MataPelajaran getMapelById(int id);
    void saveMapel(MataPelajaran mapel);
    void deleteMapel(int id);

}
