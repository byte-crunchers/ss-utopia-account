package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private LocalDate dob;

//    private Long ssn;  //ssn now has to be decrypted somehow

    private String email;

    private String first_name;

    private String last_name;

    private Boolean active;

    private String street_address, city, state;
    
    private Integer zip;
    
    private Long phone;

    private Boolean is_admin;

    //the db only has boolean is_admin, so convert that to a string
    public String getRoles() {
    	if(is_admin)
    		return "ADMIN";
    	else
    		return "USER";
    }

    //the db doesn't have a column for permissions
    public String getPermissions() {
        return "";
    }

    public List<String> getRoleList(){
    	if(is_admin)
    		return Arrays.asList(new String[] {"ADMIN"});
    	else
    		return Arrays.asList(new String[] {"USER"});
    }

    public List<String> getPermissionList(){
        return new ArrayList<>();
    }

}
