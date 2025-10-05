package com.example.users_service.service;

import com.example.users_service.model.*;
import com.example.users_service.repository.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

  private final UsuarioRepository usuarioRepo;
  private final CredencialRepository credRepo;
  private final PasswordEncoder encoder;

  public UserService(UsuarioRepository usuarioRepo, CredencialRepository credRepo, PasswordEncoder encoder) {
    this.usuarioRepo = usuarioRepo;
    this.credRepo = credRepo;
    this.encoder = encoder;
  }

  // Crear usuario
  public Map<String, Long> create(CreateUserReq b) {
    if (credRepo.findByEmail(b.email()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
    }

    Usuario u = new Usuario();
    u.setNombre(b.nombre());
    u.setApellido(b.apellido());
    u.setEmail(b.email());
    u.setTelefono(b.telefono());
    u.setComuna(b.comuna());
    u.setRegion(b.region());
    usuarioRepo.save(u);

    Credencial c = new Credencial();
    c.setEmail(b.email());
    c.setPasswordHash(encoder.encode(b.password()));
    c.setUsuario(u);

    try { credRepo.save(c); }
    catch (DataIntegrityViolationException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
    }

    return Map.of("id_usuario", u.getId());
  }

  // Login simple (más adelante JWT)
  public LoginRes login(LoginReq req) {
    var cred = credRepo.findByEmail(req.email())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email o clave inválidos"));

    if (!encoder.matches(req.password(), cred.getPasswordHash())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email o clave inválidos");
    }
    String token = "token-" + cred.getUsuario().getId();
    return new LoginRes(token, cred.getUsuario().getId());
  }

  public Usuario get(Long id) {
    return usuarioRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<Usuario> list() { return usuarioRepo.findAll(); }

  public void delete(Long id) {
    var user = usuarioRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    credRepo.findByEmail(user.getEmail()).ifPresent(credRepo::delete);
    usuarioRepo.deleteById(id);
  }
}
