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
import com.devsu.cuenta.handlerException.exception.AlreadyExistsException;
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

    @Operation(summary = "Obtener todas las cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuentas existentes", content = @Content(schema = @Schema(implementation = CuentaDTO.class))),
            @ApiResponse(responseCode = "404", description = "No existen cuentas", content = @Content(schema = @Schema(implementation = ExceptionMessage.class)))
    })
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<CuentaDTO>> obtenerTodasCuentas() throws NotFoundException {
        return ResponseEntity.ok(this.cuentaService.obtenerTodasCuentas());
    }

    @Operation(summary = "Crear Cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada", content = @Content(schema = @Schema(implementation = CuentaDTO.class))),
            @ApiResponse(responseCode = "409", description = "Cuenta ya existe", content = @Content(schema = @Schema(implementation = ExceptionMessage.class))),
            @ApiResponse(responseCode = "400", description =  "La Cuenta tiene atributos invalidos", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody  @Valid CuentaDTO cuentaDTO) throws AlreadyExistsException {
        return new ResponseEntity<>(this.cuentaService.crearCuenta(cuentaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar Cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada", content = @Content(schema = @Schema(implementation = CuentaDTO.class))),
            @ApiResponse(responseCode = "404", description = "No existe Cuenta para actualizar", content = @Content(schema = @Schema(implementation = ExceptionMessage.class)))
    })
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@RequestBody  @Valid CuentaDTO cuentaDTO) throws AlreadyExistsException, NotFoundException {
        return ResponseEntity.ok(this.cuentaService.actualizarCuenta(cuentaDTO));
    }

    @Operation(summary = "Eliminar Cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "No existe Cuenta para eliminar", content = @Content(schema = @Schema(implementation = ExceptionMessage.class)))
    })
    @DeleteMapping(value = "/{numeroCuenta}", produces = "application/json")
    public ResponseEntity<String> eliminarCuenta(@PathVariable @NotBlank String numeroCuenta) throws NotFoundException {
        this.cuentaService.eliminarCuenta(numeroCuenta);
        return new ResponseEntity<>("Cuenta eliminada", HttpStatus.NO_CONTENT);

    }

}
