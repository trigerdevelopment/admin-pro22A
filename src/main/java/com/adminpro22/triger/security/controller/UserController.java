/***********************************************************************************************
++++++++++ Este Controlador se utiliza para que acceda el Usuario Registrado  +++++++++++++++++*
*+++++++++ El Usuario puede modificar Sus datos excepto los Roles del Usuario +++++++++++++++++
*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.adminpro22.triger.security.controller;

import com.adminpro22.triger.security.dto.UserDto;
import com.adminpro22.triger.security.entity.Usuario;
import com.adminpro22.triger.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UsuarioRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PutMapping()
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)    {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);


        Optional<Usuario> bdUser= userRepository.findById(userDto.getId());
        bdUser.get().setNombreUsuario(userDto.getNombreUsuario());
        bdUser.get().setPassword(passwordEncoder.encode(userDto.getPassword()));
        bdUser.get().setApellido(userDto.getApellido());
        bdUser.get().setNombre(userDto.getNombre());
        bdUser.get().setEmail(userDto.getEmail());


        try {
            userRepository.save(bdUser.get());

            String message = "se actualizaron los datos!";

            return new ResponseEntity<>(message, HttpStatus.OK);

        }catch (DataIntegrityViolationException e){

            return new ResponseEntity<>("El nombre de Usuario o Email ya existen",HttpStatus.BAD_REQUEST);
        }

    }
}
