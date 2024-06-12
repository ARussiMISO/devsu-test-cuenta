package com.devsu.cuenta.util;

import org.springframework.beans.BeanUtils;

import com.devsu.cuenta.dto.MovimientoDTO;
import com.devsu.cuenta.model.Movimiento;

public class MapeadorMovimiento {

    public static MovimientoDTO mapearEntidadDTO(Movimiento movimiento){
        MovimientoDTO movimientoDTO = new MovimientoDTO();

        BeanUtils.copyProperties(movimiento, movimientoDTO);

        return movimientoDTO;

    }

    public static Movimiento mapearDTOEntidad(MovimientoDTO movimientoDTO){

        Movimiento movimiento = new Movimiento();
        BeanUtils.copyProperties(movimientoDTO, movimiento);

        return movimiento;

    }
}
