package com.devsu.cuenta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.dto.MovimientoDTO;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.MovimientoRepository;
import com.devsu.cuenta.util.MapeadorMovimiento;

import jakarta.xml.bind.ValidationException;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    public MovimientoDTO crearMovimiento(MovimientoDTO movimientoDTO) throws NotFoundException, ValidationException {
        CuentaDTO cuentaInvolucrada = this.cuentaService.obtenerCuenta(String.valueOf(movimientoDTO.getNumeroCuenta()));

        BigDecimal saldoDisponible = cuentaInvolucrada.getSaldoDisponible();
        BigDecimal saldoActual = saldoDisponible.add(movimientoDTO.getValor());

        if(saldoActual.compareTo(BigDecimal.ZERO) < 0){
            throw new ValidationException("Saldo no disponible");
        }
        cuentaInvolucrada.setSaldoDisponible(saldoActual);
        this.cuentaService.actualizarCuenta(cuentaInvolucrada);

        Movimiento movimientoNuevo = MapeadorMovimiento.mapearDTOEntidad(movimientoDTO);
        movimientoNuevo.setSaldo(saldoActual);
        return  MapeadorMovimiento.mapearEntidadDTO(this.movimientoRepository.save(movimientoNuevo));


    }

    public List<MovimientoDTO> buscarMovimientosPorFechasYCliente(LocalDate fechaInicio, LocalDate fechaFin, long numeroCuenta ){
        return this.movimientoRepository.findByNumeroCuentaAndDateBetween(numeroCuenta, fechaInicio, fechaFin).stream().map(movimiento -> MapeadorMovimiento.mapearEntidadDTO(movimiento)).toList();
    }

    

}
