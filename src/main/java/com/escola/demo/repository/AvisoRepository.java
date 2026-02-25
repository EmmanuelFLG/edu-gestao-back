package com.escola.demo.repository;

import com.escola.demo.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {

    List<Aviso> findByAtivoTrue();

    List<Aviso> findByTurmaId(Long turmaId);

    List<Aviso> findByAtivoTrueAndTurmaIdOrTurmaIsNull(Long turmaId);

}
