package com.example.users_service.controller;

import com.example.users_service.model.CreateUserReq;
import com.example.users_service.model.Usuario;
import com.example.users_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

  private final UserService service;

  public UsersController(UserService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Map<String, Long>> create(@Valid @RequestBody CreateUserReq body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.get(id));
  }

  @GetMapping
  public List<Usuario> list() { return service.list(); }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
