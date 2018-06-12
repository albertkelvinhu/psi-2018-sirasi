package com.rawsanj.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.repository.GuruRepository;
import com.rawsanj.adminlte.repository.MataPelajaranRepository;

@Service("guruService")
public class GuruServiceImpl implements GuruService {

	@Autowired
	private GuruRepository guruRepository;
	
	@Autowired
	private MataPelajaranRepository mapelRepository;
	
	
	@Override
	public List<Guru> listAllGuru() {
		return guruRepository.findAll();
	}

	@Override
	public Guru getGuruById(Long id) {
		return guruRepository.findOne(id);
	}

	@Override
	public Guru getGuruByNIP(String nip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGuru(Guru guru) {
		guruRepository.save(guru);
	}

	@Override
	public void deleteGuru(Long id) {
		guruRepository.delete(id);
	}

	@Override
	public long getJumlahGuru() {
		return guruRepository.count();
	}

	@Override
	public MataPelajaran getMapelByGuruId(Long id) {
		Guru guru = this.getGuruById(id);
		int id_mapel = guru.getMapel().getId();
		return mapelRepository.getOne(id_mapel);
	}

	@Override
	public Guru getGuruByUserId(int id) {
//		Guru foundGuru = new Guru();
		for ( Guru guru : this.guruRepository.findAll()) {
			if (guru.getUser().getId()==id){
				return guru;
			}
		}
		return null;
	}

}
