package com.rawsanj.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.Nilai;
import com.rawsanj.adminlte.model.Rapor;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.repository.GuruRepository;
import com.rawsanj.adminlte.repository.KelasRepository;
import com.rawsanj.adminlte.repository.NilaiRepository;
import com.rawsanj.adminlte.repository.RaporRepository;
import com.rawsanj.adminlte.repository.SiswaRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private RaporRepository raporrepository;

	@Autowired
	private SiswaRepository siswarepository;

	@Autowired
	private NilaiRepository nilairepository;

	@Autowired
	private KelasRepository kelasrepository;
	
	@Autowired
	private GuruRepository gururepository;

	@Override
	public List<Rapor> listAllRapor() {
		return raporrepository.findAll();
	}

	@Override
	public Rapor getRaporById(long id) {
		return raporrepository.findOne(id);
	}

	@Override
	public Rapor getRaporBySemesterAndTA(int semester, String ta) {
		for (Rapor rapor : this.listAllRapor()) {
			if (rapor.getSemester() == semester && rapor.getTahunAjaran().equalsIgnoreCase(ta)) {
				return rapor;
			}
		}
		return null;
	}

	@Override
	public List<Nilai> getNilaiByRapor(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rapor> bySiswaId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRapor(Rapor rapor) {
		raporrepository.save(rapor);
	}

	@Override
	public void deleteRapor(long id) {
		raporrepository.delete(id);
	}

	@Override
	public void saveRaporForKelasSiswa(long idKelas, Rapor rapor) {
		List<Rapor> listRapor = null;
		for (Siswa siswa : kelasrepository.getOne(idKelas).getSiswa()) {
			listRapor = siswa.getRaporSiswa();
			listRapor.add(rapor);
			rapor.setRaporSiswa(siswa);
			rapor.setTahunAjaran("2017/2018");
			rapor.setSemester(2);
			rapor.setWaliKelas(gururepository.getOne(kelasrepository.getOne(idKelas).getWaliKelas().getId()));
			rapor.setStatus("menunggu");
			siswa.setRaporSiswa(listRapor);
			siswarepository.save(siswa);
			this.saveRapor(rapor);
		}
		
	}

	@Override
	public float getRataNilaiAkhir(long reportId) {
		Rapor rapor = this.getRaporById(reportId);
		int size = rapor.getNilai().size();
		float sum = 0;
		for (Nilai nilai : rapor.getNilai()) {
			sum += nilai.getNilaiAkhir();
		}
		return sum/size;
	}

	@Override
	public float getJumlahNilaiAkhir(long reportId) {
		Rapor rapor = this.getRaporById(reportId);
		float sum = 0;
		for (Nilai nilai : rapor.getNilai()) {
			sum += nilai.getNilaiAkhir();
		}
		return sum;
	}

}
