package model;
import java.io.*;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import util.IdGenerator;

/** Stores vaccination records and persists them locally. */
public class TaskManager {
    private Map<String, Task> tasks = new HashMap<>();
    private static final String FILE_PATH = "taskslist.ser";

    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Record cannot be null.");
        String id;
        do { id = IdGenerator.generateId(Year.now().getValue()); }
        while (tasks.containsKey(id));
        task.setChildID(id);
        tasks.put(id, task);
    }
    public Task deleteTask(String id) { return tasks.remove(id); }
    public Task findTask(String id) { return tasks.get(id); }
    public Map<String, Task> findAll() { return new HashMap<>(tasks); }
    public void setTask(Task task) {
        if (task != null && task.getChildID() != null && tasks.containsKey(task.getChildID()))
            tasks.put(task.getChildID(), task);
    }
    @SuppressWarnings("unchecked")
    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.isFile()) { tasks = new HashMap<>(); return; }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object data = in.readObject();
            if (data instanceof Map<?, ?>) tasks = (Map<String, Task>) data;
            else tasks = new HashMap<>();
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            tasks = new HashMap<>();
            System.err.println("Could not load saved records: " + ex.getMessage());
        }
    }
    public int saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(tasks);
            return 0;
        } catch (IOException ex) {
            System.err.println("Could not save records: " + ex.getMessage());
            return 1;
        }
    }
}
