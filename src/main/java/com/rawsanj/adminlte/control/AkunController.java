package com.rawsanj.adminlte.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rawsanj.adminlte.repository.RoleRepository;
import com.rawsanj.adminlte.service.SiswaService;
import com.rawsanj.adminlte.service.UserService;

@Controller
public class AkunController extends WebMvcConfigurerAdapter {
	
	@Autowired
	private SiswaService siswaservice;
	
	@Autowired 
	private UserService userservice;
	
	@Autowired 
	private RoleRepository rolerepo;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView mengelolaAkun(Model model)
    {
        ModelAndView m = new ModelAndView();
        m.addObject("roles", rolerepo.findAll());
        m.setViewName("/login/registration");
        return m;
    }

}
