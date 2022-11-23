package com.adminpro22.triger.security.controller;


import com.adminpro22.triger.security.dto.NuevoUsuario;
import com.adminpro22.triger.security.entity.Rol;
import com.adminpro22.triger.security.entity.Usuario;
import com.adminpro22.triger.security.enums.RolNombre;
import com.adminpro22.triger.security.repository.UsuarioRepository;
import com.adminpro22.triger.security.service.IPhotoService;
import com.adminpro22.triger.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.zip.Deflater;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuariosController {

    @Autowired
    RolService rolService;

    public final UsuarioRepository usuarioRepository;
    public final PasswordEncoder passwordEncoder;
    public final IPhotoService iPhotoService;

    public UsuariosController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, IPhotoService iPhotoService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.iPhotoService = iPhotoService;
    }


    // PETICION DE TODOS LOS SUPPLIERS
    @RequestMapping(value = "/get-usuarios", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> getAllSuppliers() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();

            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Error e) {
//            List<Usuario> usuarios = null;
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update-usuario", method = RequestMethod.PUT)
    public ResponseEntity<String> updateSupplier(@Valid @RequestBody Usuario usuario, BindingResult bindingResult)    {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);

        Set<Rol> roles = usuario.getRoles();
        Set<Rol> newRoles = new HashSet<>();
        for(Rol r: roles) {
            newRoles.add(rolService.getByRolNombre(r.getRolNombre()).get());
        }

        Optional<Usuario> bdUsuario = usuarioRepository.findById(usuario.getId());
        bdUsuario.get().setNombreUsuario(usuario.getNombreUsuario());
        bdUsuario.get().setPassword(passwordEncoder.encode(usuario.getPassword()));
        bdUsuario.get().setApellido(usuario.getApellido());
        bdUsuario.get().setNombre(usuario.getNombre());
        bdUsuario.get().setEmail(usuario.getEmail());
        bdUsuario.get().setEnabled(usuario.getEnabled());
        bdUsuario.get().setRoles(newRoles);


            try {
                usuarioRepository.save(bdUsuario.get());
                String message = "El Usuario se actualizo en la base de datos";
                return new ResponseEntity<>(message, HttpStatus.OK);
            }catch (DataIntegrityViolationException e){
                return new ResponseEntity<>("El nombre de Usuario o Email ya existen",HttpStatus.BAD_REQUEST);
            }

    }

    @RequestMapping(value = "/update-profile", method = RequestMethod.PUT)
    public ResponseEntity<String> guardar(
            @Valid NuevoUsuario nuevoUsuario,
            BindingResult result,
            //  @PathVariable String filename,
            SessionStatus status,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        System.out.println("USER " + nuevoUsuario.getNombre() + "Foto : " + nuevoUsuario.getPhotoName());
        System.out.println("USER ID " + nuevoUsuario.getId());
        System.out.println("File " + file);
        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        String uniqueFilename = null;
        try {
            uniqueFilename = iPhotoService.copy(file);
            nuevoUsuario.setPhotoName(uniqueFilename);

        } catch (IOException e) {
            e.printStackTrace();
       }

        nuevoUsuario.setPhotoName(uniqueFilename);

        Usuario usuarioBD = usuarioRepository.findByNombreUsuario(nuevoUsuario.getNombreUsuario()).get();
       usuarioBD.setApellido(nuevoUsuario.getApellido());
       usuarioBD.setEmail(nuevoUsuario.getEmail());
       usuarioBD.setNombre(nuevoUsuario.getNombre());
       usuarioBD.setPhotoName(nuevoUsuario.getPhotoName());

        usuarioRepository.save(usuarioBD);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PETICION DE BUSQUEDA DE SUPPLIER BY ID
    @RequestMapping(value = "/get-usuario-by-name/{username}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuarioByUserName(@PathVariable(value = "username") String username) {
        try {

            Usuario usuario = usuarioRepository.findByNombreUsuario(username).get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Error e) {
            Usuario usuario = null;
            return new ResponseEntity<>( HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.getById(id);

        if(usuario.getNombreUsuario().equals("admin")) return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);

        usuarioRepository.deleteById(id);

        return new ResponseEntity<>("El Usuario ha sido borrado de la base de datos con exito",HttpStatus.OK);
    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
}
