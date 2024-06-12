package com.devsu.cuenta.util;

import org.springframework.beans.BeanUtils;

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.model.Cuenta;

public class MapeadorCuenta {

    public static CuentaDTO mapearEntidadDTO(Cuenta cuenta){
        CuentaDTO cuentaDTO = new CuentaDTO();

        BeanUtils.copyProperties(cuenta, cuentaDTO);

        return cuentaDTO;

    }

    public static Cuenta mapearDTOEntidad(CuentaDTO cuentaDTO){

        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDTO, cuenta);

        return cuenta;

    }

    public static CuentaDTO mapearEntidadDTOMovimientos(Cuenta cuenta){
        CuentaDTO cuentaDTO = new CuentaDTO();

        BeanUtils.copyProperties(cuenta, cuentaDTO);
        if(!cuenta.getMovimientos().isEmpty()){
            cuentaDTO.setMovimientos(cuenta.getMovimientos().stream().map(movimiento -> 
                MapeadorMovimiento.mapearEntidadDTO(movimiento)).toList());
        }
        

        return cuentaDTO;

    }

}
