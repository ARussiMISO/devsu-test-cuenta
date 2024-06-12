package com.devsu.cuenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.handlerException.exception.AlreadyExistsException;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.util.MapeadorCuenta;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaDTO obtenerCuenta(String numeroCuenta) throws NotFoundException {
        if (StringUtils.isNumeric(numeroCuenta) && !numeroCuenta.isBlank()) {
            Optional<Cuenta> cuentaEncontrada = this.cuentaRepository.findByNumeroCuenta(Long.parseLong(numeroCuenta));

            if (cuentaEncontrada.isPresent())
                return MapeadorCuenta.mapearEntidadDTO(cuentaEncontrada.get());
            else
                throw new NotFoundException("Cuenta no encontrada");
        } else
            throw new ValidationException("Numero cuenta inválido");
    }

    public List<CuentaDTO> obtenerTodasCuentas() throws NotFoundException {
        List<Cuenta> cuentasEncontradas = this.cuentaRepository.findAll();

        if (cuentasEncontradas.isEmpty())
            throw new NotFoundException("No hay registros de cuentas");
        else
            return cuentasEncontradas.stream().map(cuenta -> MapeadorCuenta.mapearEntidadDTO(cuenta)).toList();

    }

    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) throws AlreadyExistsException {
        if (this.cuentaRepository.findByNumeroCuenta(cuentaDTO.getNumeroCuenta()).isPresent())
            throw new AlreadyExistsException("Cuenta ya existe");

        Cuenta cuentaNueva = this.cuentaRepository.save(MapeadorCuenta.mapearDTOEntidad(cuentaDTO));
        return MapeadorCuenta.mapearEntidadDTO(cuentaNueva);
    }

    public CuentaDTO actualizarCuenta(CuentaDTO cuentaDTO) throws NotFoundException {
        Optional<Cuenta> cuentaEncontrada = this.cuentaRepository.findByNumeroCuenta(cuentaDTO.getNumeroCuenta());

        if (cuentaEncontrada.isPresent()){
            Cuenta cuentaActualizada = this.cuentaRepository.save(cuentaEncontrada.get());
            return MapeadorCuenta.mapearEntidadDTO(cuentaActualizada);  
        } else
            throw new NotFoundException("Cuenta no encontrada");
        
        
    }

    public void eliminarCuenta(String numeroCuenta) throws NotFoundException {
        Optional<Cuenta> cuentaEncontrada = this.cuentaRepository.findByNumeroCuenta(Long.parseLong(numeroCuenta));

        if (cuentaEncontrada.isPresent())
            this.cuentaRepository.delete(cuentaEncontrada.get());
        else
            throw new NotFoundException("Cuenta no encontrada");

        
    }

}
