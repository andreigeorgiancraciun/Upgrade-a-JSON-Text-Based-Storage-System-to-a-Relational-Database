package com.example.springboot.tasklist;

import com.example.springboot.common.BaseEntity;
import com.example.springboot.project.Project;
import com.example.springboot.task.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class TaskList extends BaseEntity {

    private String name;

    @ManyToOne
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    public void addTask(Task toAdd) {
        toAdd.setTaskList(this);
        tasks.add(toAdd);
    }

    public void removeTask(Task toRemove) {
        if(tasks.contains(toRemove)) {
            toRemove.setTaskList(null);
            tasks.remove(toRemove);
        }
    }
}