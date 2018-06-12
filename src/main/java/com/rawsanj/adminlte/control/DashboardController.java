package com.rawsanj.adminlte.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rawsanj.adminlte.model.User;
import com.rawsanj.adminlte.service.GuruService;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.StaffService;
import com.rawsanj.adminlte.service.UserService;

@Controller
@ControllerAdvice
public class DashboardController {

	@Autowired
	private UserService userservice;

	@Autowired
	private SiswaService siswaservice;

	@Autowired
	private GuruService guruservice;

	@Autowired
	private StaffService staffservice;

	@GetMapping({ "/", "/index" })
	public String dashboard(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User a = userservice.findUserByEmail(auth.getName());
		if (a.getRole().getRole().equalsIgnoreCase("ADMIN")) {
			model.addAttribute("jumlahSiswa", this.getJumlahSiswa());
			model.addAttribute("jumlahGuru", this.getJumlahGuru());
			model.addAttribute("jumlahStaff", this.getJumlahStaff());
			model.addAttribute("user", staffservice.getStaffById(Long.valueOf(a.getId())));
			return "/dashboard/indexAdmin";
		} else if (a.getRole().getRole().equalsIgnoreCase("GURU")) {
			model.addAttribute("user", guruservice.getGuruByUserId(a.getId()));
			return "/dashboard/indexGuru";
		} else if (a.getRole().getRole().equalsIgnoreCase("SISWA")) {
			model.addAttribute("tahunAjaran", GuruController.getTahunAjaran());
			model.addAttribute("semester", GuruController.getSemester());
			model.addAttribute("user", siswaservice.getSiswaByUserId(a.getId()));
			return "/dashboard/indexSiswa";
		}
		return "redirect: /404";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login/login";
	}

	// Added to test 500 page
	@RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
	public void error500() throws Exception {
		throw new Exception();
	}

	@ModelAttribute
	public void getCurrentUser(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User a = userservice.findUserByEmail(auth.getName());
			if (a != null) {
				model.addAttribute("userLogged", a);
				if (a.getRole().getRole().equalsIgnoreCase("ADMIN")) {
					model.addAttribute("adminlogged", true);
				} else if (a.getRole().getRole().equalsIgnoreCase("GURU")) {
					model.addAttribute("gurulogged", true);
				} else if (a.getRole().getRole().equalsIgnoreCase("SISWA")) {
					model.addAttribute("siswalogged", true);
				}
			}
			
			
		} catch (Exception e) {
			e.toString();
		}

	}

	public long getJumlahSiswa() {
		try {
			long jumlah = siswaservice.getJumlahSiswa();
			return jumlah;
		} catch (Exception e) {
			e.toString();
		}
		return 0;
	}

	public long getJumlahGuru() {
		try {
			long jumlah = guruservice.getJumlahGuru();
			return jumlah;
		} catch (Exception e) {
			e.toString();
		}
		return 0;
	}

	public long getJumlahStaff() {
		try {
			long jumlah = staffservice.getJumlahStaff();
			return jumlah;
		} catch (Exception e) {
			e.toString();
		}
		return 0;
	}
}
