package com.example.users_service.model;

import jakarta.validation.constraints.*;

public record CreateUserReq(
  @NotBlank String nombre,
  @NotBlank String apellido,
  @Email @NotBlank String email,
  String telefono,
  String comuna,
  String region,
  @NotBlank @Size(min = 4, max = 60) String password
) {}
