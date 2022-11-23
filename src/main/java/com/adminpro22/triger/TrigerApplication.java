package com.adminpro22.triger;

import com.adminpro22.triger.security.dto.NuevoUsuario;
import com.adminpro22.triger.security.entity.Rol;
import com.adminpro22.triger.security.entity.Usuario;
import com.adminpro22.triger.security.enums.RolNombre;
import com.adminpro22.triger.security.service.RolService;
import com.adminpro22.triger.security.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class TrigerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrigerApplication.class, args);
	}


//	@Bean
	CommandLineRunner run(RolService rolService,
						  UsuarioService usuarioService,
						  PasswordEncoder passwordEncoder) {
		return args -> {

			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
			Rol rolUser = new Rol(RolNombre.ROLE_USER);
			Rol rolConta = new Rol(RolNombre.ROLE_CONTA);
			Rol rolSales = new Rol(RolNombre.ROLE_VENTAS);
			Rol rolProduction = new Rol(RolNombre.ROLE_PRODUCCION);
			Rol rolDepot = new Rol(RolNombre.ROLE_ALMACEN);
			Rol rolPurchase = new Rol(RolNombre.ROLE_COMPRAS);

			rolService.save(rolAdmin);
			rolService.save(rolUser);
			rolService.save(rolSales);
			rolService.save(rolProduction);
			rolService.save(rolDepot);
			rolService.save(rolPurchase);
			rolService.save(rolConta);

			NuevoUsuario nuevoUsuario = new NuevoUsuario();

			Set<Rol> roles = new HashSet<>();
			nuevoUsuario.setNombre("Tannia");
			nuevoUsuario.setApellido("Ramos");
			nuevoUsuario.setNombreUsuario("admin");
			nuevoUsuario.setEmail("tannia@gmail.com");
			nuevoUsuario.setEnabled(true);
			nuevoUsuario.setPassword("12345");
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CONTA).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_VENTAS).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_COMPRAS).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_PRODUCCION).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ALMACEN).get());
			Usuario usuario =
					new Usuario(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
							nuevoUsuario.getPhotoName(),passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEnabled());
			usuario.setRoles(roles);
			usuarioService.save(usuario);
			Set<Rol> roles2 = new HashSet<>();
			NuevoUsuario nuevoUsuario2 = new NuevoUsuario();
			nuevoUsuario2.setNombre("User");
			nuevoUsuario2.setApellido("Usuario");
			nuevoUsuario2.setNombreUsuario("user");
			nuevoUsuario2.setEmail("user@gmail.com");
			nuevoUsuario2.setEnabled(true);
			nuevoUsuario2.setPassword("12345");
			roles2.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			Usuario usuario2 =
					new Usuario(nuevoUsuario2.getId(), nuevoUsuario2.getNombre(), nuevoUsuario2.getApellido(), nuevoUsuario2.getNombreUsuario(), nuevoUsuario2.getEmail(),
							nuevoUsuario2.getPhotoName(),passwordEncoder.encode(nuevoUsuario2.getPassword()), nuevoUsuario2.getEnabled());
			usuario2.setRoles(roles2);
			usuarioService.save(usuario2);


		};


	}

}
