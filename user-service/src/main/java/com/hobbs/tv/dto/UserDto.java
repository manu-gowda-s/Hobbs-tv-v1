package com.hobbs.tv.dto;

import com.hobbs.tv.entity.Country;
import com.hobbs.tv.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private long id;
    private String name;
    @Email
    @Size(max = 100)
    @Indexed(unique = true)
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobile;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private Gender gender;

    private Country country;

    private boolean primeUser;
}
