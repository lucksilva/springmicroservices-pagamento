package com.example.microservicespring.pagamento.repositories;

import com.example.microservicespring.pagamento.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}