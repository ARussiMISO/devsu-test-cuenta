package com.devsu.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}