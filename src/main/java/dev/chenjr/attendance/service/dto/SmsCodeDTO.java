package dev.chenjr.attendance.service.dto;

import dev.chenjr.attendance.service.dto.validation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SmsCodeDTO {
    @NotBlank
    @Schema(example = "register", allowableValues = {"register", "login", "reset_password"})
    @Pattern(regexp = "(register)|(login)|(reset_password)", message = "仅接受register/login/reset_password")
    public String type;
    @NotBlank
    @PhoneNumber
    public String phone;
}
