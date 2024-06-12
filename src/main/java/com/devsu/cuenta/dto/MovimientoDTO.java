package com.devsu.cuenta.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovimientoDTO {

    @NotBlank(message = "Id no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Id debe contener sólo números")
    private long id;

    @NotBlank(message = "Fecha no puede ser vacía")
    private Timestamp fecha;

    @NotBlank(message = "Tipo de movimiento no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Valor de movimiento debe contener sólo números")
    private String tipoMovimiento;

    @NotBlank(message = "Valor de movimiento no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Valor de movimiento debe contener sólo números")
    private BigDecimal valor;

    @NotBlank(message = "Saldo no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Saldo debe contener sólo números")
    private BigDecimal saldo;

    @NotBlank(message = "Cuenta id no puede ser vacía")
    @Pattern(regexp = "^[0-9]+$", message = "Cuenta id debe contener sólo números")
    private long cuentaId;

}
