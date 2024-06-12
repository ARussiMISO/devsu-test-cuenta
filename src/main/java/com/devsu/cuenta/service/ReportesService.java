package com.devsu.cuenta.service;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.dto.MovimientoDTO;
import com.devsu.cuenta.dto.ReporteDTO;
import com.devsu.cuenta.handlerException.exception.NotFoundException;

import jakarta.xml.bind.ValidationException;

@Service
public class ReportesService {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    public List<ReporteDTO> obtenerReporte(LocalDate fechaInicio, LocalDate fechaFin, String cliente)
            throws NotFoundException {
        List<CuentaDTO> cuentasEncontradas = this.cuentaService.obtenerCuentasPorNombre(cliente);
        Timestamp inicioTimestamp = Timestamp.valueOf(fechaInicio.atStartOfDay());
        Timestamp finTimestamp = Timestamp.valueOf(fechaInicio.atTime(23, 59, 59));
        List<ReporteDTO> reporteGenerado = new ArrayList<>();
        for (CuentaDTO cuentaEncontrada : cuentasEncontradas) {
            reporteGenerado = cuentaEncontrada.getMovimientos().stream()
                    .filter(movimiento -> !movimiento.getFecha().before(inicioTimestamp)
                            && !movimiento.getFecha().after(finTimestamp))
                    .map(movimiento -> new ReporteDTO(
                            movimiento.getFecha(),
                            cuentaEncontrada.getCliente(),
                            movimiento.getNumeroCuenta(),
                            cuentaEncontrada.getTipoCuenta() == 0 ? "Ahorros" : "Corriente",
                            cuentaEncontrada.getSaldoInicial(),
                            cuentaEncontrada.getEstado(),
                            movimiento.getValor(),
                            cuentaEncontrada.getSaldoDisponible()))
                    .collect(Collectors.toList());

        }

        return reporteGenerado;

    }

}
