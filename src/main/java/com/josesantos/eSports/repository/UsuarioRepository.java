package com.josesantos.eSports.repository;

import com.josesantos.eSports.entity.UsuarioEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    public UsuarioEntity findByLoginAndPassword(String login, String password);
    

    	//Page<UsuarioEntity> findByTipousuarioIdAndLoginIgnoreCaseContainingOrDniIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(
	//		Long filtertype, String login, String dni, String apellido1, String apellido2, Pageable oPageable);

	Page<UsuarioEntity> findByLoginIgnoreCaseContaining(
			String nombre, Pageable oPageable);
    
}
