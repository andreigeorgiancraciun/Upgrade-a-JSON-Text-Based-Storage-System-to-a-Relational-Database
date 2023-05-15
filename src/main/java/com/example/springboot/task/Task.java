package com.example.springboot.task;

import com.example.springboot.common.BaseEntity;
import com.example.springboot.developer.Developer;
import com.example.springboot.tasklist.TaskList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(exclude = "taskList")
public class Task extends BaseEntity {
    private String name;
    private String description;
    @PositiveOrZero
    private int estimatedHours = 0;

    @ManyToOne
    private TaskList taskList;

    @ManyToOne
    private Developer developer;

    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TaskLabel> labels = new HashSet<>();

    public void addLabel(TaskLabel toAdd) {
        labels.add(toAdd);
    }

    public void removeLabel(TaskLabel toRemove) {
        labels.remove(toRemove);
    }
}