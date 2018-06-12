package com.rawsanj.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawsanj.adminlte.model.Siswa;


@Repository("siswaRepository")
public interface SiswaRepository extends JpaRepository<Siswa, Long> {

}
