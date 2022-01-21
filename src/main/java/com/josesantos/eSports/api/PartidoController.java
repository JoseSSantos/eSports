package com.josesantos.eSports.api;

/**
 *
 * @author Saslt
 */
import com.josesantos.eSports.entity.PartidoEntity;
import com.josesantos.eSports.entity.UsuarioEntity;
import com.josesantos.eSports.repository.PartidoRepository;
import com.josesantos.eSports.repository.UsuarioRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    PartidoRepository oPartidoRepository;

    @Autowired
    HttpSession oHttpSession;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<PartidoEntity>(oPartidoRepository.findById(id).get(), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<?> get() {

        return new ResponseEntity<List<PartidoEntity>>(oPartidoRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<Long>(oPartidoRepository.count(), HttpStatus.OK);

        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody PartidoEntity oPartidoEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 2) {
                if (oPartidoEntity.getId() == null) {
                    if (oUsuarioEntity.getEquipo().getId() == oPartidoEntity.getEquipo1().getId()
                            && oPartidoEntity.getEquipo1().getId() != oPartidoEntity.getEquipo2().getId()) {
                        return new ResponseEntity<>(oPartidoRepository.save(oPartidoEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oPartidoEntity.getId() == null && oPartidoEntity.getEquipo1().getId() != oPartidoEntity.getEquipo2().getId()) {
                    return new ResponseEntity<>(oPartidoRepository.save(oPartidoEntity), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PartidoEntity oPartidoEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        Long idEquipo1 = oPartidoRepository.findById(id).get().getEquipo1().getId();

        if (oUsuarioEntity == null || (oUsuarioEntity.getTipousuario().getId() != 1 && oUsuarioEntity.getTipousuario().getId() != 2)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        oPartidoEntity.setId(id);

        if (oUsuarioEntity.getTipousuario().getId() == 1) {
            return new ResponseEntity<>(oPartidoRepository.save(oPartidoEntity), HttpStatus.OK);
        } else if (oUsuarioEntity.getTipousuario().getId() == 2) {

            if (oPartidoRepository.findById(id).get().getEquipo2() == null) {

                if (oUsuarioEntity.getEquipo().getId() == oPartidoEntity.getEquipo1().getId() && oPartidoEntity.getEquipo2() == null) {
                    return new ResponseEntity<>(oPartidoRepository.save(oPartidoEntity), HttpStatus.OK);
                } else if (oUsuarioEntity.getEquipo().getId() == oPartidoEntity.getEquipo2().getId() && oPartidoEntity.getEquipo1().getId() == idEquipo1) {
                    return new ResponseEntity<>(oPartidoRepository.save(oPartidoEntity), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
