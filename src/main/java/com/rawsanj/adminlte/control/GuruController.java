package com.rawsanj.adminlte.control;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.model.Nilai;
import com.rawsanj.adminlte.model.Rapor;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.model.User;
import com.rawsanj.adminlte.service.GuruService;
import com.rawsanj.adminlte.service.KelasService;
import com.rawsanj.adminlte.service.MapelService;
import com.rawsanj.adminlte.service.NilaiService;
import com.rawsanj.adminlte.service.ReportService;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.StaffService;
import com.rawsanj.adminlte.service.UserService;

@RestController
public class GuruController {

	@Autowired
	private SiswaService siswaservice;

	@Autowired
	private GuruService guruservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private KelasService kelasservice;

	@Autowired
	private MapelService mapelservice;

	@Autowired
	private NilaiService nilaiservice;
	

	@Autowired
	private ReportService reportservice;

	// MODEL
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Guru guru;
	private Siswa siswa;

	public Siswa getSiswa() {
		return siswa;
	}

	public void setSiswa(Siswa siswa) {
		this.siswa = siswa;
	}

	public Guru getGuru() {
		return guru;
	}

	public void setGuru(Guru guru) {
		this.guru = guru;
	}

	// END MODEL

	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User a = userservice.findUserByEmail(auth.getName());
		return a;
	}

	@RequestMapping(value = "/guru/siswa/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView listSiswa(Model model, User user) {
		ModelAndView m = new ModelAndView();
		m.addObject("siswas", siswaservice.listAllSiswa());
		m.setViewName("/dashboard/guru/listsiswa");
		return m;
	}

	@RequestMapping(value = "/guru/mapel", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView informasiMapel(Model model, User user) {
		ModelAndView m = new ModelAndView();
		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		m.addObject("mapel", guruservice.getMapelByGuruId(guru.getId()));
		m.setViewName("/dashboard/guru/informasiMapel");
		return m;
	}

	@RequestMapping(value = "/guru/nilai", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView informasiNilaiSiswa(Model model, User user) {
		ModelAndView m = new ModelAndView();
		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());

		m.addObject("siswas", siswaservice.listAllSiswa());
		m.addObject("mapel", guruservice.getMapelByGuruId(guru.getId()));

		m.setViewName("/dashboard/guru/dataNilaiSiswa");
		return m;
	}

	@RequestMapping(value = "/nilai/siswa/{id}/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView memasukkanNilaiSiswa(@PathVariable int id, Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();

		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		MataPelajaran mapel = guruservice.getMapelByGuruId(guru.getId());

		this.setSiswa(siswaservice.getSiswaById(id));
		System.out.println(id);
		// if (siswa.getNilaiSiswa().size() == 0) {
		// System.out.println();
		// }

		m.addObject("nilais", nilaiservice.getNilaiBySiswaMapel(id, mapel.getId()));
		m.addObject("nilai", nilai);

		m.addObject("siswa", this.getSiswa());
		m.addObject("mapel", mapel);
		m.setViewName("/dashboard/guru/inputNilaiSiswa");
		return m;
	}

	@RequestMapping(value = "/nilai/siswa/add", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView memasukkanNilaiSiswa(@Valid @ModelAttribute(value = "nilai") Nilai nilai,
			BindingResult bindingResultNilai, Model model, User user) {
		ModelAndView m = new ModelAndView();
		// if (this.getSiswa().getNilaiSiswa().size() == 0) {
		// System.out.println();
		// }

		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		List<Nilai> nilaiSiswa = this.getSiswa().getNilaiSiswa();

		m.addObject("mapel", guruservice.getMapelByGuruId(guru.getId()));
		m.addObject("siswa", this.getSiswa());

		nilai.setMapelNilai(guruservice.getMapelByGuruId(guru.getId()));
		nilai.setNilaiSiswa(this.getSiswa());
		nilai.setSemester(getSemester());
		nilai.setTahunAjaran(getTahunAjaran());
		nilai.setNilaiAkhir(
				(nilai.getNilaiTugas() + nilai.getNilaiUAS() + nilai.getNilaiUH() + nilai.getNilaiUTS()) / 4);
		nilaiservice.saveNilai(nilai);
		nilaiSiswa.add(nilai);
		this.getSiswa().setNilaiSiswa(nilaiSiswa);
		siswaservice.saveSiswa(this.getSiswa());

		m.setViewName("redirect:/guru/nilai");
		return m;
	}

	@RequestMapping(value = "/nilai/siswa/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView informasiNilaiSiswa(@PathVariable long id, Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();

		this.setSiswa(siswaservice.getSiswaById(id));
		System.out.println(id);
		// if (siswa.getNilaiSiswa().size() == 0) {
		// System.out.println();
		// }
		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		MataPelajaran mapel = guruservice.getMapelByGuruId(guru.getId());

		m.addObject("nilais", nilaiservice.getNilaiBySiswaMapel(id, mapel.getId()));
		m.addObject("siswa", this.getSiswa());
		m.addObject("mapel", mapel);
		m.setViewName("/dashboard/guru/nilaisiswa");
		return m;
	}

	@RequestMapping(value = "/data/kelas/view", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView viewKelas(Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();
		this.setGuru(guruservice.getGuruByUserId(this.getUser().getId()));
		if (this.guru.getKelas() == null) {

			m.addObject("notif", false);
		}
		MataPelajaran mapel = guruservice.getMapelByGuruId(guru.getId());
		m.addObject("siswas", kelasservice.getSiswaByWaliKelas(this.getGuru()));
		m.addObject("semester", getSemester());
		m.addObject("tahunAjaran", getTahunAjaran());

		m.addObject("mapel", mapel);
		m.setViewName("/dashboard/guru/datakelas");
		return m;
	}

	// mengambil tahun ajaran berdasarkan bulan dan tahun sekarang
	static String getTahunAjaran() {
		String tahunAjaran = null;
		Calendar now = Calendar.getInstance();

		System.out.println("Current Year is : " + now.get(Calendar.YEAR));
		System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
		System.out.println("Current Date is : " + now.get(Calendar.DATE));

		if ((now.get(Calendar.MONTH) + 1) <= 6) {
			tahunAjaran = now.get(Calendar.YEAR) - 1 + "/" + now.get(Calendar.YEAR);

		} else if ((now.get(Calendar.MONTH) + 1) >= 6 && (now.get(Calendar.MONTH) + 1) <= 12) {
			tahunAjaran = now.get(Calendar.YEAR) + "/" + now.get(Calendar.YEAR) + 1;
		}

		return tahunAjaran;

	}

	static int getSemester() {
		int semester = 0;
		Calendar now = Calendar.getInstance();
		System.out.println("Current Year is : " + now.get(Calendar.YEAR));
		System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
		System.out.println("Current Date is : " + now.get(Calendar.DATE));
		if ((now.get(Calendar.MONTH) + 1) <= 6) {
			semester = 2;
		} else if ((now.get(Calendar.MONTH) + 1) >= 6 && (now.get(Calendar.MONTH) + 1) <= 12) {
			semester = 1;
		}
		return semester;
	}
	// -------------------------

	// Create RAPOR
	@RequestMapping(value = "/guru/view/rapor", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView raporSiswa(Model model, User user) {
		ModelAndView m = new ModelAndView();
//		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
//		m.addObject("mapel", guruservice.getMapelByGuruId(guru.getId()));
//		m.addObject("guru", guru);
//		m.addObject("siswas", kelasservice.getSiswaByWaliKelas(this.getGuru()));
		m.addObject("reports",reportservice.listAllRapor());
		m.setViewName("/dashboard/guru/daftarRapor");
		return m;
	}

	@RequestMapping(value = "/guru/create/rapor/all", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView createRaporSiswa( Rapor rapor, Model model, User user) {
		ModelAndView m = new ModelAndView();
		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		Rapor raporSiswa = new Rapor();
		m.addObject("mapel", guruservice.getMapelByGuruId(guru.getId()));
		m.addObject("guru", guru);
	
		reportservice.saveRaporForKelasSiswa(guru.getKelas().getId(), raporSiswa);
		m.setViewName("redirect:/data/kelas/view");
		return m;
	}
	
	@RequestMapping(value = "/guru/view/rapor/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView melihatRaporSiswa(@PathVariable int id, Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();

		Guru guru = guruservice.getGuruByUserId(this.getUser().getId());
		MataPelajaran mapel = guruservice.getMapelByGuruId(guru.getId());

		this.setSiswa(siswaservice.getSiswaById(id));
		m.addObject("rapors", this.getSiswa().getRaporSiswa());

		m.addObject("siswa", this.getSiswa());
		m.setViewName("/dashboard/guru/raporsiswa");
		return m;
	}
	
	@RequestMapping(value = "/guru/print/rapor/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('GURU')")
	public ModelAndView printRaporSiswa(@PathVariable long id,Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();
		m.addObject("report", reportservice.getRaporById(id));
		m.addObject("siswa", reportservice.getRaporById(id).getRaporSiswa());
		
		m.addObject("nilaiSUM", reportservice.getJumlahNilaiAkhir(id));
		m.addObject("nilaiAVG", reportservice.getRataNilaiAkhir(id));
		m.setViewName("/dashboard/siswa/rapor");
		return m;
	}
}
