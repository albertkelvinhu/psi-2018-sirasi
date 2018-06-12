package com.rawsanj.adminlte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.Nilai;
import com.rawsanj.adminlte.repository.NilaiRepository;

@Service
public class NilaiServiceImpl implements NilaiService {

	
	@Autowired
	private NilaiRepository nilairepository;
	
	@Override
	public List<Nilai> listAllNilai() {
		return nilairepository.findAll();
	}

	@Override
	public Nilai getMapelById(int id) {
		for (Nilai nilai2 : this.listAllNilai()) {
			if (nilai2.getMapelNilai().getId()==id){
				return nilai2;
			}
		}
		return null;
	}

	@Override
	public List <Nilai> getNilaiBySiswaMapel(long idSiswa,int idMapel) {
		List <Nilai> foundList = new ArrayList<Nilai>();
		for (Nilai nilai2 : this.listAllNilai()) {
//			if (nilai2.getMapelNilai().getId()==idMapel && nilai2.getSiswaNilai().getId()==idSiswa){
//				foundList.add(nilai2);
//			}
		}
		return foundList;
	}

	@Override
	public void saveNilai(Nilai nilai) {
		nilairepository.save(nilai);
	}

	@Override
	public void deleteNilai(int id) {
		// TODO Auto-generated method stub
		
	}

}
