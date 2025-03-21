package com.group2.theminimart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserUpdateRequestDto {
  @NotBlank(message = "Username must not be blank")
  private String username;
  @NotBlank(message = "Password must not be blank")
  private String password;
}
