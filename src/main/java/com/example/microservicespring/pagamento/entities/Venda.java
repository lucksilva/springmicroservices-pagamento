package com.example.microservicespring.pagamento.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "data", nullable = false)
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(name = "valorTotal")
    private Double valorTotal;

}
