package com.example.springboot.tasklist;

import com.example.springboot.project.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList findByName(String name) {
        return taskListRepository.findByName(name);
    }

    public <S extends TaskList> S save(S entity) {
        return taskListRepository.save(entity);
    }

    public List<TaskList> findAllByProject(Project project) {
        return taskListRepository.findAllByProject(project);
    }


    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }
}
