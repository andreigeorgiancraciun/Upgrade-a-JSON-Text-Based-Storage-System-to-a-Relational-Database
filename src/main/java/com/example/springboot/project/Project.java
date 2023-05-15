package com.example.springboot.project;

import com.example.springboot.common.BaseEntity;
import com.example.springboot.developer.Developer;
import com.example.springboot.tasklist.TaskList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Project extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Setter(AccessLevel.PROTECTED)
    private Set<Developer> developers;

    public void addDeveloper(Developer toAdd) {
        developers.add(toAdd);
    }

    public void removeDeveloper(Developer toRemove) {
        developers.remove(toRemove);
    }

}