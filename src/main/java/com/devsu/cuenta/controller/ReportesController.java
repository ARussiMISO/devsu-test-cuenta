package com.devsu.cuenta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.ValidationException;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.dto.ReporteDTO;
import com.devsu.cuenta.handlerException.dto.ExceptionMessage;
import com.devsu.cuenta.handlerException.exception.NotFoundException;
import com.devsu.cuenta.service.ReportesService;

@RestController
@RequestMapping("/reportes")
@Validated
public class ReportesController {

    @Autowired
    ReportesService reportesService;

    @Operation(summary = "Obtener reporte movimientos cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte generado", content = @Content(schema = @Schema(implementation = ReporteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Movimientos no encontrados", content = @Content(schema = @Schema(implementation = ExceptionMessage.class)))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ReporteDTO>> obtenerReporte(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                                            @RequestParam("cliente") String cliente)
            throws NotFoundException {
        return ResponseEntity.ok(this.reportesService.obtenerReporte(fechaInicio, fechaFin, cliente));
    }

}
