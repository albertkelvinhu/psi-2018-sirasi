package com.rawsanj.adminlte.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.MataPelajaran;
import com.rawsanj.adminlte.model.Nilai;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.model.User;
import com.rawsanj.adminlte.repository.RaporRepository;
import com.rawsanj.adminlte.service.GuruService;
import com.rawsanj.adminlte.service.KelasService;
import com.rawsanj.adminlte.service.MapelService;
import com.rawsanj.adminlte.service.NilaiService;
import com.rawsanj.adminlte.service.ReportService;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.UserService;

@Controller
public class SiswaController {

	
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
	
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User a = userservice.findUserByEmail(auth.getName());
		return a;
	}

	@Autowired
	private ReportService reportservice;
	
	@RequestMapping(value="/siswa/rapor", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SISWA')")
    public ModelAndView raporSiswa(Model model)
    {
		 ModelAndView m = new ModelAndView();
	        m.setViewName("/dashboard/siswa/rapor");
	        return m;
    }
	
	@RequestMapping(value = "/siswa/rapor/view", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SISWA')")
	public ModelAndView melihatRaporSiswa(Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();
		Siswa siswa = siswaservice.getSiswaByUserId(this.getUser().getId());
		
		m.addObject("rapors", siswa.getRaporSiswa());
		m.addObject("siswa",siswa);
		m.setViewName("/dashboard/siswa/raporsiswa");
		return m;
	}
	
	@RequestMapping(value = "/print/rapor/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SISWA')")
	public ModelAndView melihatRaporSiswa(@PathVariable long id,Nilai nilai, Model model, User user) {
		ModelAndView m = new ModelAndView();
		Siswa siswa = siswaservice.getSiswaByUserId(this.getUser().getId());
		m.addObject("report", reportservice.getRaporById(id));
		m.addObject("nilaiSUM", reportservice.getJumlahNilaiAkhir(id));
		m.addObject("nilaiAVG", reportservice.getRataNilaiAkhir(id));
		m.addObject("siswa",siswa);
		m.setViewName("/dashboard/siswa/rapor");
		return m;
	}
	
}
