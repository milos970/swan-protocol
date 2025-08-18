package org.spring.web.vaii.entity.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.web.vaii.Authority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @NotBlank
    private String username;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{12,}$",
          message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;


    private Authority authority;

}
