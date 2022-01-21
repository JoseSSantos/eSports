/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josesantos.eSports.repository;

import com.josesantos.eSports.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Saslt
 */
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    
}
