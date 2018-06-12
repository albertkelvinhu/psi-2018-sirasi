package com.rawsanj.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawsanj.adminlte.model.TataUsaha;
import com.rawsanj.adminlte.repository.StaffRepository;

@Service("staff_service")
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	StaffRepository staffrepo;
	
	@Override
	public List<TataUsaha> listAllStaff() {
		return staffrepo.findAll();
	}

	@Override
	public TataUsaha getStaffById(Long id) {
		
		for (TataUsaha tu : this.listAllStaff()) {
			if (tu.getUser().getId()==id) {
				return tu;
			}
		}
		
		
		return null;
	}

	@Override
	public TataUsaha getStaffByNIP(String nip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveStaff(TataUsaha tatausaha) {
		staffrepo.save(tatausaha);
		
	}

	@Override
	public void deleteStaff(Long id) {
		staffrepo.delete(id);
	}

	@Override
	public long getJumlahStaff() {
		return staffrepo.count();
	}

}
