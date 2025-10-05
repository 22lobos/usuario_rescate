package com.example.users_service.model;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "CREDENCIAL_SEQ", sequenceName = "CREDENCIAL_SEQ", allocationSize = 1)
public class Credencial {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENCIAL_SEQ")
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPasswordHash() { return passwordHash; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
  public Usuario getUsuario() { return usuario; }
  public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
