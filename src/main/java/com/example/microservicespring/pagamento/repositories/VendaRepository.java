package com.example.microservicespring.pagamento.repositories;

import com.example.microservicespring.pagamento.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}