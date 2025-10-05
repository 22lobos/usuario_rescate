package com.example.users_service.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) private String nombre;
  @Column(nullable = false) private String apellido;
  @Column(nullable = false, unique = true) private String email;

  private String telefono;
  private String comuna;
  private String region;

  // getters & setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getApellido() { return apellido; }
  public void setApellido(String apellido) { this.apellido = apellido; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getTelefono() { return telefono; }
  public void setTelefono(String telefono) { this.telefono = telefono; }
  public String getComuna() { return comuna; }
  public void setComuna(String comuna) { this.comuna = comuna; }
  public String getRegion() { return region; }
  public void setRegion(String region) { this.region = region; }
}
