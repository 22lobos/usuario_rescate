package com.example.users_service.model;

import jakarta.validation.constraints.*;

public record LoginReq(
  @Email @NotBlank String email,
  @NotBlank String password
) {}
