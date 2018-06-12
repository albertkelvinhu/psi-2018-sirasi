package com.rawsanj.adminlte.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.repository.MataPelajaranRepository;


@Service("mapel_service")
public class MapelServiceImpl implements MapelService {

	@Autowired
	private MataPelajaranRepository mapelrepository;
	
	@Override
	public List<MataPelajaran> listAllMapel() {
		return mapelrepository.findAll();
	}

	@Override
	public MataPelajaran getMapelById(int id) {
		return mapelrepository.getOne(id);
	}

	@Override
	public void saveMapel(MataPelajaran mapel) {
		mapelrepository.save(mapel);
		
	}

	@Override
	public void deleteMapel(int id) {
		mapelrepository.delete(id);
		
	}

}
