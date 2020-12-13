Instructions
- Download the project
- Run the application from DemoApplication
- Open multiple browser windows and go to "localhost:8080/"
- Note: The built Vue package is included to the project.
  In order to review the Vue/front-end code, please go to
  https://github.com/ottkaskjr/taskapp-vue

Features
- Clients can add tasks by providing the objective of the 
task and deadline. Time of creation will be added automatically
- Clients can resolve tasks, which removes the task from the list
- Tasks are sorted starting from the closest deadline
- Overdue tasks or tasks which have less than 1 hour till the
deadline will be rendered red
- All changes are updated in real time via websocket connection
This can be tested by using different browser windows or tabs

Data
- All data is saved to the local json file found in src/main/resources/tasks.json

