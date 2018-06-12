package com.rawsanj.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawsanj.adminlte.model.Rapor;


@Repository("raporRepository")
public interface RaporRepository extends JpaRepository<Rapor, Long> {

}
