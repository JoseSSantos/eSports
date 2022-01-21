//package com.josesantos.eSports.api;
//
//import com.josesantos.eSports.entity.FileEntity;
//import com.josesantos.eSports.entity.UsuarioEntity;
//import com.josesantos.eSports.repository.FileRepository;
//import com.josesantos.eSports.repository.PartidoRepository;
//import java.util.List;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @Autowired
//    FileRepository oFileRepository;
//
//    @Autowired
//    HttpSession oHttpSession;
//
////    @GetMapping("/{id}")
// //   public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
// //       UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
////
////       if (oUsuarioEntity == null) {
// ////           return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
// //       } else {
//   //         return new ResponseEntity<FileEntity>(oFileRepository.findById(id).get(), HttpStatus.OK);
//  //      }
// //   }
////    
////        @GetMapping("/all")
////        public ResponseEntity<?> get() {
////        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
////        if (oUsuarioEntity == null) {
////            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
////        } else {
////            return new ResponseEntity<FileEntity>(oFileRepository.findAll(), HttpStatus.OK);
////        }
////    }
//
//    @GetMapping("/count")
//    public ResponseEntity<?> count() {
//        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
//        if (oUsuarioEntity == null) {
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        } else {
//            return new ResponseEntity<Long>(oFileRepository.count(), HttpStatus.OK);
//        }
//    }
//
////    @PostMapping("/new")
////    public ResponseEntity<?> create(@RequestBody FileEntity oNewFileEntity) {
////
////        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
////        if (oUsuarioEntity == null) {
////            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
////        } else {
////            if (oNewFileEntity.getId() == null) {
////                //oNewUsuarioEntity.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
////                return new ResponseEntity<>(oFileRepository.save(oNewFileEntity), HttpStatus.OK);
////            } else {
////                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
////            }
////        }
////    }
//    
//    
//}
