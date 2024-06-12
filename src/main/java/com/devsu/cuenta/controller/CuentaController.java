package com.devsu.cuenta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

import com.devsu.cuenta.dto.CuentaDTO;
import com.devsu.cuenta.handlerException.dto.ExceptionMessage;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;


    @Operation(summary = "Obtener cuenta por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta con número dado existe", content = @Content(schema = @Schema(implementation = CuentaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cuenta con número dado no existe", content = @Content(schema = @Schema(implementation = ExceptionMessage.class)))
    })
    @GetMapping(value = "/{numeroCuenta}", produces = "application/json")
    public ResponseEntity<CuentaDTO> obtenerCuenta(@PathVariable @NotBlank String numeroCuenta)
            throws NotFoundException {
        return ResponseEntity.ok(this.cuentaService.obtenerCuenta(numeroCuenta));
    } 

}
