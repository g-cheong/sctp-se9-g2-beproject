package com.group2.theminimart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
  @NotBlank(message = "Username must not be blank")
  private String username;
  @NotBlank(message = "Password must not be blank")
  private String password;
}
