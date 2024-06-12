package com.devsu.cuenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.util.MapeadorCuenta;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaDTO obtenerCuenta(String numeroCuenta) throws NotFoundException {
        if (StringUtils.isNumeric(numeroCuenta) && !numeroCuenta.isBlank()) {
            Optional<Cuenta> cuentaEncontrada = this.cuentaRepository.findByNumeroCuenta(Long.parseLong(numeroCuenta));

            if(cuentaEncontrada.isPresent())
                return MapeadorCuenta.mapearEntidadDTO(cuentaEncontrada.get());
            else 
                throw new NotFoundException("Cuenta no encontrada");
        } else
            throw new ValidationException("Numero cuenta inválido");  
    }
    

}
