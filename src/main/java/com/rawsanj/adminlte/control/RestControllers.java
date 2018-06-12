package com.rawsanj.adminlte.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rawsanj.adminlte.model.Guru;
import com.rawsanj.adminlte.model.Siswa;
import com.rawsanj.adminlte.model.User;
import com.rawsanj.adminlte.service.GuruService;
import com.rawsanj.adminlte.service.KelasService;
import com.rawsanj.adminlte.service.MapelService;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.StaffService;
import com.rawsanj.adminlte.service.UserService;

@RestController
public class RestControllers {

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

	@RequestMapping(path = "/siswas", method = RequestMethod.GET)
	public List<Siswa> getAllSiswa() {
		System.out.println(siswaservice.listAllSiswa());
		System.out.println("terpanggil");
		return siswaservice.listAllSiswa();
	}

	@RequestMapping(value = "/siswa/{id}", method = RequestMethod.GET)
	public Siswa getSiswaById(@PathVariable("id") int id) {
		return siswaservice.getSiswaById(id);
	}

}
