package ui;
import controller.TaskController;
import javax.swing.UIManager;
import model.TaskManager;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { System.err.println("Could not set system look and feel: " + ex.getMessage()); }
        TaskManager manager = new TaskManager();
        manager.loadTasks();
        TaskController controller = new TaskController(manager);
        java.awt.EventQueue.invokeLater(() -> {
            MainForm form = new MainForm(controller);
            controller.setView(form);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }
}
