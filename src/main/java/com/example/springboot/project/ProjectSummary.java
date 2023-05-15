package com.example.springboot.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProjectSummary {

    private String projectName;
    private int taskCount;
    private int totalHours;
}
