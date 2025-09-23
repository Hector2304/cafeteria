package com.fca.cafeteria.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbebidaingrediente")
@Getter
@Setter
public class BebidaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbebidaingrediente")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idbebida", nullable = false)
    private Bebida bebida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idingrediente", nullable = false)
    private Ingrediente ingrediente;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
