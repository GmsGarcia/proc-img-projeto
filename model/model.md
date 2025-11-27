# Model


![Model](https://raw.githubusercontent.com/GmsGarcia/proc-img-projeto/refs/heads/master/model/model.uml.png)

# Classes
## Task:
- uuid: UUID
- title: String
- description: String
- priority: int
- deadline: Instant
- status: TaskStatus
- taskItems: ArrayList<TaskItem>
- addTaskItem(TaskItem): void
- removeTaskItem(TaskItem): void

## TaskItem:
- uuid: UUID
- title: String
- description: String
- status: TaskStatus

## TaskStatus
- enum: [CREATED, STARTED, COMPLETED]

## TaskManager:
- tasks: HashMap<UUID, ArrayList<Tasks>>
- createTask(UUID, String, String, int, Instant): Task
- saveTasks(): void

## User:
- uuid: UUID
- name: String

## StorageManager:
- loadTasks(): HashMap<UUID, ArrayList<Task>>
- saveTasks(HashMap<UUID, ArrayList<Task>>): void
- saveTasks(UUID, Task): void
