package com.rawsanj.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.Kelas;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.repository.KelasRepository;

@Service("kelas_service")
public class KelasServiceImpl implements KelasService {

	@Autowired
	private KelasRepository kelasrepository;

	@Override
	public List<Kelas> listAllKelas() {
		return kelasrepository.findAll();
	}

	@Override
	public Kelas getKelasById(long id) {
		return kelasrepository.getOne(id);
	}

	@Override
	public Kelas getKelasByKodeKelas(String kode) {
		// TODO Auto-generated method stub
		return kelasrepository.findKelasByKodeKelas(kode);
	}

	@Override
	public List<Siswa> getSiswaByWaliKelas(Guru guru) {
		for (Kelas kelas : this.listAllKelas()) {
			if (kelas.getWaliKelas() == guru) {
				return kelas.getSiswa();
			}
		}
		return null;
	}

	@Override
	public void saveKelas(Kelas kelas) {
		kelasrepository.save(kelas);
	}

	@Override
	public void deleteKelas(int id) {
		kelasrepository.delete(getKelasById(id));
	}

}
