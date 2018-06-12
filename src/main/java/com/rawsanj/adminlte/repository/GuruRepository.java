package com.rawsanj.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawsanj.adminlte.model.Guru;


@Repository("guruRepository")
public interface GuruRepository extends JpaRepository<Guru, Long> {
	
	Guru findGuruByNip(String nip);

}
