package com.example.springboot.migrate;

import com.example.springboot.project.Project;
import com.example.springboot.project.ProjectService;
import com.example.springboot.task.Task;
import com.example.springboot.task.TaskService;
import com.example.springboot.tasklist.TaskList;
import com.example.springboot.tasklist.TaskListService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MigrationService {
    private final TaskListService taskListService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public MigrationService(TaskListService taskListService, ProjectService projectService, TaskService taskService) {
        this.taskListService = taskListService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public void migrate() {
        var mapper = new ObjectMapper();
        var typeReference = new TypeReference<List<Task>>(){};
        var inputStream = TypeReference.class.getResourceAsStream("/json/tasks.json");

        try {
            TaskList backlogList = null;
            var project = projectService.findByName("Main");

            if(project == null) {
                project = new Project();
                project.setName("Main");
            } else {
                var lists = taskListService.findAllByProject(project);

                for (var taskList : lists) {
                    if(taskList.getName().equals("Backlog")) {
                        backlogList = taskList;
                        break;
                    }
                }
            }

            if(backlogList == null) {
                backlogList = new TaskList();
                backlogList.setName("Backlog");
            }

            Iterable<Task> tasks = mapper.readValue(inputStream,typeReference);

            // persist all tasks before adding them to the list
            tasks = taskService.saveAll(tasks);

            // save project first, so we can set it to the task list
            project = projectService.save(project);

            // set project and persist the list
            backlogList.setProject(project);
            backlogList = taskListService.save(backlogList);

            // add tasks to the saved list
            for (var task : tasks) {
                backlogList.addTask(task);
            }

            // save list again so it cascades the children
            taskListService.save(backlogList);

            System.out.println("Successfully migrated tasks= " + tasks);

        } catch (IOException e){
            System.out.println("Unable to migrate tasks: " + e.getMessage());
        }
    }
}
