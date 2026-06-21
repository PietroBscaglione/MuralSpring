package br.ufscar.dc.dsw.repository;

import br.ufscar.dc.dsw.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m ORDER BY m.id DESC")
    List<Message> findAllOrderedByIdDesc();
}
