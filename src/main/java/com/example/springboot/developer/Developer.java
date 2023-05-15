package com.example.springboot.developer;

import com.example.springboot.common.BaseEntity;
import com.example.springboot.project.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Developer extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @ManyToMany(mappedBy = "developers")
    @ToString.Exclude
    private Set<Project> projects;
}
