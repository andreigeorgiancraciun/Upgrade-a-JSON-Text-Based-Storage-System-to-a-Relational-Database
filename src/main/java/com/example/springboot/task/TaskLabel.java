package com.example.springboot.task;

import com.example.springboot.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskLabel extends BaseEntity {

    @NotBlank
    private String label;

}
