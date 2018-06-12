package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.TataUsaha;

public interface StaffService {
	
	List <TataUsaha> listAllStaff();
	long getJumlahStaff();
	TataUsaha getStaffById(Long id);
	TataUsaha getStaffByNIP(String nip);
    void saveStaff(TataUsaha tatausaha);
    void deleteStaff(Long id);


}
