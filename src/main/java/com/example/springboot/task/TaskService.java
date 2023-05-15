package com.example.springboot.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public <S extends Task> S save(S entity) {
        return taskRepository.save(entity);
    }

    public <S extends Task> Iterable<S> saveAll(Iterable<S> entities) {
        return taskRepository.saveAll(entities);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
