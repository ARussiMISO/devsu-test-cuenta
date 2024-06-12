package com.devsu.cuenta.model;

import java.math.BigDecimal;
import java.util.List;

import com.devsu.cuenta.enums.TipoCuenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numeroCuenta;

    @Enumerated(EnumType.ORDINAL)
    private TipoCuenta tipoCuenta;

    private BigDecimal saldoInicial;

    private BigDecimal saldoDisponible;

    private Boolean estado;

    private String cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "numeroCuenta", referencedColumnName = "numeroCuenta")
    private List<Movimiento> movimientos;

}
