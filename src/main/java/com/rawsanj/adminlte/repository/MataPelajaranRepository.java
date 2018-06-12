package com.rawsanj.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawsanj.adminlte.model.MataPelajaran;


@Repository("mapelRepository")
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, Integer>{

}
