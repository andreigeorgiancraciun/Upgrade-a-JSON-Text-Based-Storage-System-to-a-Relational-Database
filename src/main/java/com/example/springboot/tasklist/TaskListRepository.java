package com.example.springboot.tasklist;

import com.example.springboot.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    TaskList findByName(String name);

    List<TaskList> findAllByProject(Project project);
}
