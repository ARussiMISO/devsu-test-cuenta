package com.devsu.cuenta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.ValidationException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.dto.MovimientoDTO;
import com.devsu.cuenta.handlerException.dto.ExceptionMessage;
import com.devsu.cuenta.handlerException.exception.AlreadyExistsException;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
@Validated
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Operation(summary = "Crear Movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado", content = @Content(schema = @Schema(implementation = MovimientoDTO.class))),
            @ApiResponse(responseCode = "404", description = "No existe la Cuenta involucrada en el movimiento", content = @Content(schema = @Schema(implementation = ExceptionMessage.class))),
            @ApiResponse(responseCode = "400", description =  "El Movimiento tiene atributos invalidos", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<MovimientoDTO> crearMovimiento(@RequestBody  @Valid MovimientoDTO movimientoDTO) throws NotFoundException, ValidationException {
        return new ResponseEntity<>(this.movimientoService.crearMovimiento(movimientoDTO), HttpStatus.CREATED);
    }

    


}
