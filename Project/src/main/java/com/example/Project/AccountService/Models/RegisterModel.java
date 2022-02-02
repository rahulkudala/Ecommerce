package com.example.Project.AccountService.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class RegisterModel {

    private Integer id;
    @NotNull(message = "First name should not be null")
    @NotEmpty
    private String firstName;
    @NotNull(message = "Last name should not be null")
    @NotEmpty
    private String lastName;
    @Email(message = " Email should be in proper format")
    private String email;
    private String password;
    @Pattern(regexp = "[0-9]{10}", message = "Phone Number should be 10 digits only")
    private Long phoneNo;
    private List<CustomerAddressModel> customerAddress;

    public RegisterModel(String firstName, String lastName, String email, String password, Long phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public RegisterModel(Integer id, String firstName, String lastName, String email, String password, Long phoneNo, List<CustomerAddressModel> customerAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.customerAddress = customerAddress;
    }

    public RegisterModel(Integer id, String firstName, String lastName, String email, String password, Long phoneNo) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }
}
