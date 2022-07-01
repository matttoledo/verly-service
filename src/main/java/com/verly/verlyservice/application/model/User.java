package com.verly.verlyservice.application.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="USER")
@DiscriminatorValue("USER")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

}
