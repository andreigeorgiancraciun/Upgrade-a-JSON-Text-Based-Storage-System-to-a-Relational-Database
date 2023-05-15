package com.example.springboot.project;

import com.example.springboot.tasklist.TaskListService;
import org.springframework.stereotype.Service;

@Service
public class ProjectSummaryService {

    private final TaskListService taskListService;

    public ProjectSummaryService(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    public ProjectSummary findProjectSummary(Project project) {
        var taskLists = taskListService.findAllByProject(project);
        var totalEstimated = 0;
        var totalTasks = 0;

        for(var taskList : taskLists) {
            for (var task : taskList.getTasks()) {
                totalEstimated += task.getEstimatedHours();
                totalTasks++;
            }
        }

        return new ProjectSummary(project.getName(), totalTasks, totalEstimated);
    }
}
