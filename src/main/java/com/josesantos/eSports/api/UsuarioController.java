package com.josesantos.eSports.api;

import com.josesantos.eSports.entity.UsuarioEntity;
import com.josesantos.eSports.repository.UsuarioRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saslt
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    HttpSession oHttpSession;


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.findById(id).get(), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<?> get() {

        return new ResponseEntity<List<UsuarioEntity>>(oUsuarioRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
                return new ResponseEntity<Long>(oUsuarioRepository.count(), HttpStatus.OK);

        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC) Pageable oPageable,
            @RequestParam(required = false) Long filtertype, @RequestParam(required = false) String filter) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {

            Page<UsuarioEntity> oPage;
            if (filtertype != null) {
                //	oPage = oUsuarioRepository
                //			.findByTipousuarioId(
                //                               filtertype, filter == null ? "" : filter, filter == null ? "" : filter,
                //				filter == null ? "" : filter, filter == null ? "" : filter, oPageable);
            } else {
                oPage = oUsuarioRepository
                        .findByLoginIgnoreCaseContaining(filter == null ? "" : filter, oPageable);
                
                return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
            }

            
        }return null;
    }
    
    @PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody UsuarioEntity oNewUsuarioEntity) {

		UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
		if (oUsuarioEntity == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} else {
			if (oUsuarioEntity.getTipousuario().getId() == 1) {
				if (oNewUsuarioEntity.getId() == null) {
					//oNewUsuarioEntity.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
					return new ResponseEntity<>(oUsuarioRepository.save(oNewUsuarioEntity), HttpStatus.OK);
				} else {
					return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		}
	}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioEntity oUsuarioEntity) {

		UsuarioEntity oUsuarioEntity2 = (UsuarioEntity) oHttpSession.getAttribute("usuario");
		String password = oUsuarioEntity2.getPassword();
		if (oUsuarioEntity2 == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} else {
			if (oUsuarioEntity2.getTipousuario().getId() == 1) {
				if (oUsuarioRepository.existsById(id)) {
					UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.findById(id).get();
					oUsuarioEntity.setId(id);
					return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
				} else {
					return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.findById(id).get(),
							HttpStatus.NOT_FOUND);
				}
			} else {
				if (oUsuarioEntity2.getId() == id) {
					UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.findById(id).get();
					oUsuarioEntity.setId(oUsuarioEntity2.getId());
					oUsuarioEntity.setPassword(password);
					return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
				}
			}
		}
	}
    
}
