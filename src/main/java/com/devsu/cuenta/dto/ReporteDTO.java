package com.devsu.cuenta.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReporteDTO {

    private Timestamp fecha;
    private String cliente;
    private long numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;

    public ReporteDTO(Timestamp fecha, String cliente2, long numeroCuenta2, String tipoCuenta2,
            BigDecimal saldoInicial2, Boolean estado2, BigDecimal movimiento, BigDecimal saldoDisponible2) {
        this.fecha = fecha;
        this.cliente = cliente2;
        this.numeroCuenta = numeroCuenta2;
        this.tipoCuenta = tipoCuenta2;
        this.saldoInicial = saldoInicial2;
        this.estado = estado2;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible2;
    }
}
