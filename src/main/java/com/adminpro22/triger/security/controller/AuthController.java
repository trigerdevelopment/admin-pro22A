package com.adminpro22.triger.security.controller;

import com.adminpro22.triger.security.dto.JwtDto;
import com.adminpro22.triger.security.dto.LoginUsuario;
import com.adminpro22.triger.security.dto.NuevoUsuario;
import com.adminpro22.triger.security.entity.Rol;
import com.adminpro22.triger.security.entity.Usuario;
import com.adminpro22.triger.security.enums.RolNombre;
import com.adminpro22.triger.security.jwt.JwtProvider;
import com.adminpro22.triger.security.repository.UsuarioRepository;
import com.adminpro22.triger.security.service.RolService;
import com.adminpro22.triger.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioRepository usuarioRepository;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);

        if (nuevoUsuario.getId() == null) {
            if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
                return new ResponseEntity<>(new Mensaje("El nombre de Usuario ya existe"), HttpStatus.BAD_REQUEST);
            if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
                return new ResponseEntity<>(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario =
                new Usuario(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        nuevoUsuario.getPhotoName(),passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEnabled());
        Set<Rol> roles = new HashSet<>();
//        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("Contabilidad")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CONTA).get());

        }
        if (nuevoUsuario.getRoles().contains("Ventas")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_VENTAS).get());

        }
        if (nuevoUsuario.getRoles().contains("Compras")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_COMPRAS).get());

        }

        if (nuevoUsuario.getRoles().contains("Almacen")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ALMACEN).get());

        }

        if (nuevoUsuario.getRoles().contains("Produccion")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PRODUCCION).get());

        }
        if (nuevoUsuario.getRoles().contains("Usuario")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        }


        if (nuevoUsuario.getRoles().contains("Admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CONTA).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_VENTAS).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PRODUCCION).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ALMACEN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_COMPRAS).get());

        }

        if (nuevoUsuario.getId() != null) {
            Usuario usuarioBd = usuarioService.getByNombreUsuario(nuevoUsuario.getNombreUsuario()).get();
            usuario.setPassword(usuarioBd.getPassword());
        }


        usuario.setRoles(roles);
        usuarioService.save(usuario);


        return new ResponseEntity<>(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new Mensaje("Error al recibir los datos"), HttpStatus.BAD_REQUEST);
        }

        if (!usuarioService.existsByNombreUsuario(loginUsuario.getUsername())) {

            String message = "Error en los datos, contacte a su administrador si continua este mensaje";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }

       Usuario usuario = usuarioService.getByNombreUsuario(loginUsuario.getUsername()).get();
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(),usuario.getNombre().concat(" ").concat(usuario.getApellido()), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}