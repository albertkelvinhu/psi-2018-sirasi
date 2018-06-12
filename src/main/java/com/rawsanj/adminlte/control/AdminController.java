package com.rawsanj.adminlte.control;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.Kelas;
import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.model.TataUsaha;
import com.rawsanj.adminlte.model.User;
import com.rawsanj.adminlte.repository.SiswaRepository;
import com.rawsanj.adminlte.repository.UserRepository;
import com.rawsanj.adminlte.service.GuruService;
import com.rawsanj.adminlte.service.KelasService;
import com.rawsanj.adminlte.service.MapelService;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.StaffService;
import com.rawsanj.adminlte.service.UserService;

@Controller
public class AdminController {

	private final String foto = "/file/user.png";

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
	private StaffService tuservice;

	@RequestMapping(value = "/admin/siswa/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listSiswa(Model model, User user) {
		ModelAndView m = new ModelAndView();

		m.addObject("siswas", siswaservice.listAllSiswa());
		System.out.println("dipanggil");
		m.setViewName("/dashboard/admin/listsiswa");
		return m;
	}

	@RequestMapping(value = "/admin/guru/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listGuru(Model model) {
		ModelAndView m = new ModelAndView();
		System.out.println("dipanggil");
		m.addObject("gurus", guruservice.listAllGuru());
		m.setViewName("/dashboard/admin/listguru");
		return m;
	}

	@RequestMapping(value = "/admin/staff/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listStaff(Model model) {
		ModelAndView m = new ModelAndView();
		m.addObject("staffs", tuservice.listAllStaff());
		m.setViewName("dashboard/admin/liststaff");
		return m;
	}

	@RequestMapping(value = "/admin/kelas/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listKelas(Model model) {
		ModelAndView m = new ModelAndView();
		m.addObject("gurus", guruservice.listAllGuru());
		m.addObject("kelases", kelasservice.listAllKelas());
		System.out.println("dipanggil");
		m.setViewName("/dashboard/admin/listkelas");
		return m;
	}

	@RequestMapping(value = "/admin/mapel/list", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listMapel(Model model) {
		ModelAndView m = new ModelAndView();
		m.addObject("mapels", mapelservice.listAllMapel());
		System.out.println("dipanggil");
		m.setViewName("/dashboard/admin/listmapel");
		return m;
	}

	// CRUD SISWA

	@RequestMapping(value = "/admin/siswa/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView toAddSiswa(Siswa siswa, User user, Model model) {
		ModelAndView m = new ModelAndView();
		m.setViewName("dashboard/admin/formsiswa");
		return m;
	}

	@PostMapping(value = "/admin/siswa/add")
	public ModelAndView registerSiswa(@Valid @ModelAttribute(value = "siswa") Siswa siswa,
			BindingResult bindingResultSiswa, @Valid @ModelAttribute(value = "user") User user,
			BindingResult bindingResultUser) throws ConversionFailedException {
		ModelAndView modelAndView = new ModelAndView();
		Siswa sisExist = siswaservice.getSiswaByNISN(siswa.getNisn());
		if (sisExist != null)
			bindingResultSiswa.rejectValue("NISN", "NISNExist", "NISN telah terdaftar");

		if (bindingResultSiswa.hasErrors() || bindingResultSiswa.hasErrors() || bindingResultUser.hasErrors()) {
			System.out.println("Gagal");

			System.out.println(bindingResultSiswa);
			System.out.println(bindingResultUser);
			// this.getCurrentUser(modelAndView);
			modelAndView.setViewName("dashboard/admin/formsiswa");
		} else {

			siswa.setPath(foto);
			user.setName(siswa.getName());
			siswa.setLastName(user.getLastName());
			siswa.setUser(user);
			siswaservice.saveSiswa(siswa);
			userservice.saveUser(siswa.getUser(), "SISWA");
			modelAndView.setViewName("redirect:/admin/siswa/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/siswa/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteSiswa(@PathVariable long id) {
		System.out.println("mau menghapus");
		siswaservice.deleteSiswa(id);
		return "redirect:/admin/siswa/list";
	}
	// -------------------CRUD SISWA

	// CRUD GURU
	@RequestMapping(value = "/admin/guru/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView toAddGuru(Guru guru,MataPelajaran mapel, User user, Model model) {

		ModelAndView m = new ModelAndView();
//		m.addObject("mapels",mapelservice.listAllMapel());
		// this.getCurrentUser(m);
		m.setViewName("dashboard/admin/formguru");
		return m;
	}

	@PostMapping(value = "/admin/guru/add")
	public ModelAndView registerGuru(@Valid @ModelAttribute(value = "guru") Guru guru, BindingResult bindingResultGuru,
			@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResultUser,
			@Valid @ModelAttribute(value = "mapel") MataPelajaran mapel)
			throws ConversionFailedException {
		ModelAndView modelAndView = new ModelAndView();

		Guru guruExist = guruservice.getGuruByNIP(guru.getNip());
		if (guruExist != null)
			bindingResultGuru.rejectValue("NIP", "NIPExist", "NIP telah terdaftar");

		if (bindingResultGuru.hasErrors() || bindingResultUser.hasErrors()) {
			System.out.println("Gagal");
			System.out.println(bindingResultGuru);
			System.out.println(bindingResultUser);
			// this.getCurrentUser(modelAndView);
			modelAndView.setViewName("redirect: /admin/guru/add");
		} else {
			System.out.println("BERHASIL");
			
			guru.setPath(foto);
			guru.setFirstName(user.getName());
			guru.setLastName(user.getLastName());
			guru.setUser(user);
			
			guru.setMapel(null);
			
			System.out.println(guru.getUser().getEmail());
			
//			MataPelajaran mapelFound = mapelservice.getMapelById(mapel.getId());
//			List<Guru> guruList = mapelFound.getGuru();
//			guruList.add(guru);
//			mapel.setGuru(guruList);
//			mapelservice.saveMapel(mapel);
			guruservice.saveGuru(guru);
			userservice.saveUser(guru.getUser(), "GURU");
			modelAndView.setViewName("redirect:/admin/guru/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/guru/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGuru(@PathVariable long id) {
		guruservice.deleteGuru(id);
		return "redirect:/admin/guru/list";
	}
	// -------------------CRUD GURU

	// CRUD KELAS
	@RequestMapping(value = "/admin/kelas/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView toAddKelas(Kelas kelas,Guru guru, User user, Model model) {

		ModelAndView m = new ModelAndView();
		// this.getCurrentUser(m);
		m.addObject("gurus", guruservice.listAllGuru());
		m.setViewName("dashboard/admin/formkelas");
		return m;
	}

	@PostMapping(value = "/admin/kelas/add")
	public ModelAndView registerKelas(@Valid @ModelAttribute(value = "kelas") Kelas kelas,
			@Valid @ModelAttribute(value = "guru") Guru waliKelas,
			BindingResult bindingResultKelas) throws ConversionFailedException {
		ModelAndView modelAndView = new ModelAndView();
		Kelas kelasExist = kelasservice.getKelasByKodeKelas(kelas.getKodeKelas());
		if (kelasExist != null)
			bindingResultKelas.rejectValue("KodeKelas", "KelasExist", "Kelas telah terdaftar");

		if (bindingResultKelas.hasErrors()) {
			System.out.println("Gagal");

			System.out.println(bindingResultKelas);
			modelAndView.setViewName("dashboard/admin/formkelas");
		} else {
			System.out.println("BERHASIL");
//			kelas.setWaliKelas(waliKelas);
//			waliKelas.setKelas(kelas);
//			waliKelas.setAlamat("test");
//			guruservice.saveGuru(waliKelas);
			kelasservice.saveKelas(kelas);
			System.out.println("KELAS TERSIMPAN");
			modelAndView.setViewName("redirect:/admin/kelas/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/kelas/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteKelas(@PathVariable int id) {
		kelasservice.deleteKelas(id);
		return "redirect:/admin/kelas/list";
	}
	// -------------------CRUD KELAS

	// CRUD MAPEL
	@RequestMapping(value = "/admin/mapel/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView toAddMapel(Guru guru, User user, Model model) {

		ModelAndView m = new ModelAndView();
		// this.getCurrentUser(m);
		m.setViewName("dashboard/admin/formguru");
		return m;
	}

	@PostMapping(value = "/admin/mapel/add")
	public ModelAndView registerMapel(@Valid @ModelAttribute(value = "guru") Guru guru, BindingResult bindingResultGuru,
			@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResultUser)
			throws ConversionFailedException {
		ModelAndView modelAndView = new ModelAndView();
		Guru guruExist = guruservice.getGuruByNIP(guru.getNip());
		if (guruExist != null)
			bindingResultGuru.rejectValue("NIP", "NIPExist", "NIP telah terdaftar");

		if (bindingResultGuru.hasErrors() || bindingResultUser.hasErrors()) {
			System.out.println("Gagal");

			System.out.println(bindingResultGuru);
			System.out.println(bindingResultUser);
			// this.getCurrentUser(modelAndView);
			modelAndView.setViewName("dashboard/admin/formguru");
		} else {
			System.out.println("BERHASIL");
			guru.setPath(foto);
			guru.setFirstName(user.getName());
			guru.setLastName(user.getLastName());
			guru.setUser(user);

			System.out.println(guru.getUser().getEmail());
			Guru gurusave = guru;
			guruservice.saveGuru(gurusave);
			userservice.saveUser(user, "GURU");
			modelAndView.setViewName("redirect:/admin/guru/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/mapel/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMapel(@PathVariable long id) {
		guruservice.deleteGuru(id);
		return "redirect:/admin/guru/list";
	}
	// -------------------CRUD MAPEL

	// CRUD STAFF
	@RequestMapping(value = "/admin/staff/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView toAddStaff(TataUsaha tataUsaha, User user, Model model) {
		ModelAndView m = new ModelAndView();
		// this.getCurrentUser(m);
		m.setViewName("dashboard/admin/formstaff");
		return m;
	}

	@PostMapping(value = "/admin/staff/add")
	public ModelAndView registerStaff(@Valid @ModelAttribute(value = "tataUsaha") TataUsaha tu,
			BindingResult bindingResultStaff, @Valid @ModelAttribute(value = "user") User user,
			BindingResult bindingResultUser) throws ConversionFailedException {
		ModelAndView modelAndView = new ModelAndView();
		TataUsaha tuExist = tuservice.getStaffByNIP(tu.getNip());
		if (tuExist != null)
			bindingResultStaff.rejectValue("NIP", "NIPExist", "NIP telah terdaftar");

		if (bindingResultStaff.hasErrors() || bindingResultUser.hasErrors()) {
			System.out.println("Gagal");

			System.out.println(bindingResultStaff);
			System.out.println(bindingResultUser);
			// this.getCurrentUser(modelAndView);
			modelAndView.setViewName("dashboard/admin/formstaff");
		} else {
			System.out.println("BERHASIL");
			tu.setPath(foto);
			tu.setFirstName(user.getName());
			tu.setLastName(user.getLastName());
			tu.setUser(user);

			System.out.println(tu.getUser().getEmail());
			tuservice.saveStaff(tu);
			userservice.saveUser(user, "ADMIN");
			modelAndView.setViewName("redirect:/admin/staff/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/staff/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteStaff(@PathVariable long id) {
		// guruservice.deleteGuru(id);
		return "redirect:/admin/staff/list";
	}
	// -------------------CRUD STAFF

	// INFORMASI
	@RequestMapping(value = "/admin/kelas/view/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void informasi(@PathVariable long id, Kelas kelas, User user, Model model) {
		// ModelAndView m = new ModelAndView();
		// m.addObject("kelas", kelasservice.getKelasById(id));
		System.out.println(kelasservice.getKelasById(id).getKodeKelas());
		System.out.println(kelasservice.getKelasById(id).getWaliKelas().getFirstName());
		// m.setViewName("dashboard/admin/infokelas");
		// return m;

	}

}
