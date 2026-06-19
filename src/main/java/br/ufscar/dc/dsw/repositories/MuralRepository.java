package br.ufscar.dc.dsw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Long> {
}
