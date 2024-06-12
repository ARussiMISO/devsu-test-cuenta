package com.devsu.cuenta.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CuentaDTO {
    @NotBlank(message = "Id no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Id debe contener sólo números")
    private long id;

    @NotBlank(message = "Numero de cuenta no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Numero de cuenta debe contener sólo números")
    @Positive(message = "Numero de cuenta debe ser mayor que cero")
    private long numeroCuenta;

    @NotBlank(message = "Tipo cuenta no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Tipo de cuenta debe contener sólo números")
    @Positive(message = "Tipo cuenta debe ser mayor que cero")
    private int tipoCuenta;

    @NotNull(message = "Tipo cuenta no puede ser vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Tipo de cuenta debe contener sólo números")
    @PositiveOrZero(message = "Tipo cuenta debe ser mayor o igual que cero")
    private BigDecimal saldoInicial;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal saldoDisponible;

    @NotBlank(message = "El estado no debe estar vacío")
    private Boolean estado;

    @NotBlank(message = "El cliente no debe estar vacío")
    @Size(max = 100, message = "El cliente debe tener máximo 100 caracteres")
    private String cliente;

    private List<MovimientoDTO> movimientos;
}
