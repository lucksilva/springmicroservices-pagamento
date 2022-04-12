package com.example.microservicespring.pagamento.services;

import com.example.microservicespring.pagamento.data.vo.VendaVO;
import com.example.microservicespring.pagamento.entities.ProdutoVenda;
import com.example.microservicespring.pagamento.entities.Venda;
import com.example.microservicespring.pagamento.exceptions.ResourceNotFoundException;
import com.example.microservicespring.pagamento.repositories.ProdutoRepository;
import com.example.microservicespring.pagamento.repositories.ProdutoVendaRepository;
import com.example.microservicespring.pagamento.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    private final ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create(VendaVO vendaVO) {
        Venda venda = vendaRepository.save(Venda.create(vendaVO));

        List<ProdutoVenda> produtosDaVenda = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosDaVenda.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosDaVenda);

        return VendaVO.create(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    private VendaVO convertToVendaVO(Venda venda) {
        return VendaVO.create(venda);
    }

    public VendaVO findById(Long id) {
        var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        return VendaVO.create(entity);
    }

    public VendaVO update(VendaVO vendaVO) {
        final Optional<Venda> produtoOptional = vendaRepository.findById(vendaVO.getId());

        if (!produtoOptional.isPresent()) {
            throw new ResourceNotFoundException("NO RECORD FOUND FOR THIS PRODUCT ID!");
        }

        return VendaVO.create(vendaRepository.save(Venda.create(vendaVO)));
    }

    public void delete (Long id) {
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NO RECORD FOUND FOR THIS PRODUCT ID!"));

        vendaRepository.delete(entity);
    }
}