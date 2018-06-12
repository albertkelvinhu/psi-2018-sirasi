package com.rawsanj.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawsanj.adminlte.model.Kelas;


@Repository("kelasRepository")
public interface KelasRepository extends JpaRepository<Kelas, Long> {
	Kelas findKelasByKodeKelas(String kode);
}
