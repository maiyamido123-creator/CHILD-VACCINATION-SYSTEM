package ui;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columns = {"Child ID", "Name", "Age", "Barangay", "Vaccine ID"};
    private List<Task> tasks = new ArrayList<>();
    @Override public int getRowCount() { return tasks.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int column) { return columns[column]; }
    @Override public Object getValueAt(int row, int column) {
        Task task = tasks.get(row);
        return switch (column) {
            case 0 -> task.getChildID();
            case 1 -> task.getName();
            case 2 -> task.getAge();
            case 3 -> task.getBarangay();
            case 4 -> task.getVaccine_ID();
            default -> null;
        };
    }
    public void setTasks(List<Task> values) {
        tasks = values == null ? new ArrayList<>() : new ArrayList<>(values);
        fireTableDataChanged();
    }
    public String getTaskId(int row) { return tasks.get(row).getChildID(); }
}
