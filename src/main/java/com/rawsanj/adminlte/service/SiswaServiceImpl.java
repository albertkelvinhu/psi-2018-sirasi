package com.rawsanj.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.repository.SiswaRepository;


@Service("siswaService")
public class SiswaServiceImpl implements SiswaService {
	
	private SiswaRepository siswarepository;

	@Autowired
	public void setSiswarepository(SiswaRepository siswarepository) {
		this.siswarepository = siswarepository;
	}
	 

	@Override
	public List<Siswa> listAllSiswa() {
		// TODO Auto-generated method stub
		return siswarepository.findAll();
	}

	@Override
	public Siswa getSiswaById(long id) {
		
		return siswarepository.findOne(id);
	}

	@Override
	public Siswa getSiswaByNISN(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSiswa(Siswa siswa) {
		siswarepository.save(siswa);
		
	}

	@Override
	public void deleteSiswa(Long id) {
		siswarepository.delete(id);
	}


	@Override
	public long getJumlahSiswa() {
		
		return siswarepository.count();
	}


	@Override
	public Siswa getSiswaByUserId(int id) {
		for (Siswa siswa : this.listAllSiswa()) {
			if (siswa.getUser().getId()==id){
				return siswa;
			}
		}
		return null;
	}

	

	
}
