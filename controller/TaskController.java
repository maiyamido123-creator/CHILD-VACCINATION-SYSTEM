package controller;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.TaskManager;
import ui.MainForm;

/** Coordinates the form and vaccination-record model. */
public class TaskController {
    private final TaskManager model;
    private MainForm view;
    public TaskController(TaskManager model) { this.model = model; }
    public void setView(MainForm view) { this.view = view; }

    public boolean handleAddTask(String name, String age, String barangay, String vaccineID) {
        if (!valid(name, age, barangay, vaccineID)) return false;
        Task task = new Task();
        task.setName(name.trim()); task.setAge(age.trim());
        task.setBarangay(barangay.trim()); task.setVaccine_ID(vaccineID.trim());
        model.addTask(task);
        return persistAndRefresh();
    }
    public boolean handleUpdateTask(String id, String name, String age, String barangay, String vaccineID) {
        if (id == null || !valid(name, age, barangay, vaccineID)) return false;
        Task task = model.findTask(id);
        if (task == null) return false;
        task.setName(name.trim()); task.setAge(age.trim());
        task.setBarangay(barangay.trim()); task.setVaccine_ID(vaccineID.trim());
        return persistAndRefresh();
    }
    public boolean handleDeleteTask(String id) {
        if (id == null || model.deleteTask(id) == null) return false;
        return persistAndRefresh();
    }
    private boolean valid(String name, String age, String barangay, String vaccineID) {
        return notBlank(name) && notBlank(age) && notBlank(barangay) && notBlank(vaccineID);
    }
    private boolean notBlank(String s) { return s != null && !s.trim().isEmpty(); }
    private boolean persistAndRefresh() {
        boolean saved = model.saveTasks() == 0;
        if (view != null) view.refreshTaskList();
        return saved;
    }
    public List<Task> getAllTask() { return new ArrayList<>(model.findAll().values()); }
    public Task getTaskDetails(String id) { return model.findTask(id); }
}
