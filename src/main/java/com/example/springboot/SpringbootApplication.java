package com.example.springboot;

import com.example.springboot.developer.Developer;
import com.example.springboot.developer.DeveloperService;
import com.example.springboot.migrate.MigrationService;
import com.example.springboot.project.Project;
import com.example.springboot.project.ProjectService;
import com.example.springboot.project.ProjectSummaryService;
import com.example.springboot.task.Task;
import com.example.springboot.task.TaskLabel;
import com.example.springboot.task.TaskService;
import com.example.springboot.tasklist.TaskList;
import com.example.springboot.tasklist.TaskListService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

	private final ProjectService projectService;
	private final TaskListService taskListService;
	private final MigrationService migrationService;
	private final DeveloperService developerService;
	private final TaskService taskService;
	private final ProjectSummaryService projectSummaryService;


	public SpringbootApplication(ProjectService projectService, TaskListService taskListService, MigrationService migrationService, DeveloperService developerService, TaskService taskService, ProjectSummaryService projectSummaryService) {
		this.projectService = projectService;
		this.taskListService = taskListService;
		this.migrationService = migrationService;
		this.developerService = developerService;
		this.taskService = taskService;
		this.projectSummaryService = projectSummaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			addProjects();
			addTaskLists();
			migrationService.migrate();
			addDevelopers();
			assignTasks();
			showSummary();
		};
	}

	private void addProjects() {
		var project = new Project();
		project.setName("ERP Software");

		projectService.save(project);

		project = new Project();
		project.setName("CRM Software");

		projectService.save(project);
	}

	private void addTaskLists() {
		var erp = projectService.findByName("ERP Software");

		var taskList = new TaskList();
		taskList.setName("Backlog");
		taskList.setProject(erp);
		taskList = taskListService.save(taskList);

		var firstErpTask = new Task();
		firstErpTask.setName("Setup Git Repository");
		firstErpTask.setDescription("Setup Git Repository and give access to developers");
		firstErpTask.addLabel(new TaskLabel("Git"));
		firstErpTask.setEstimatedHours(2);
		firstErpTask = taskService.save(firstErpTask);

		taskList.addTask(firstErpTask);

		var secondErpTask = new Task();
		secondErpTask.setName("Setup Server");
		secondErpTask.setDescription("Setup Server for the ERP Project");
		secondErpTask.addLabel(new TaskLabel("ERP"));
		secondErpTask.setEstimatedHours(4);
		secondErpTask = taskService.save(secondErpTask);
		taskList.addTask(secondErpTask);
		taskListService.save(taskList);

		var erpTaskLists = taskListService.findAll();

		System.out.println("ERP Task Lists= " + erpTaskLists);
	}

	private void addDevelopers() {
		var erp = projectService.findByName("Main");
		var developer = new Developer();
		developer.setFirstName("John");
		developer.setLastName("Smith");
		developer.setEmail("john.smith@gmail.com");

		developer = developerService.save(developer);
		erp.addDeveloper(developer);

		erp = projectService.save(erp);

		System.out.println("erp= " + erp);
	}

	private void assignTasks() {
		var developer = developerService.findByEmail("john.smith@gmail.com");
		var tasks = taskService.findAll();

		for (var task : tasks) {
			task.setDeveloper(developer);
			taskService.save(task);
		}

		System.out.println("tasks= " + tasks);
	}

	private void showSummary() {
		var allProjects = projectService.findAll();

		for (var project : allProjects) {
			var summary = projectSummaryService.findProjectSummary(project);
			System.out.println("summary= " + summary);
		}
	}

}
