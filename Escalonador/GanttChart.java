import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GanttTask extends JFrame {
    private String title;
    private int start;
    private int end;

    public GanttTask(String title, int end) {
        this.title = title;
        this.start = 0; // Início é calculado automaticamente
        this.end = end;

    }

    public String getTitle() {
        return title;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setStart(int start) {
        this.start = start;
    }
}

public class GanttChart extends JFrame {

    private JPanel panel;
    private List<List<GanttTask>> tasks;
    private List<Color> colors;
    private Map<String, Integer> titleToRow;
    private int currentTime = 0;
    private String processo;

    public GanttChart(String processo) {
        tasks = new ArrayList<>();
        colors = new ArrayList<>();
        titleToRow = new HashMap<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);
        this.processo = processo;

        setTitle("Diagrama de Gantt " + processo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int rowHeight = (getHeight() - 100) / tasks.size();
                int chartWidth = getWidth() - 100;
                g.drawLine(50, 50, 50, getHeight() - 50); // Linha vertical esquerda
                g.drawLine(50, getHeight() - 50, getWidth() - 50, getHeight() - 50); // Linha horizontal inferior
                // Desenhar barras
                for (int i = 0; i < tasks.size(); i++) {
                    List<GanttTask> row = tasks.get(i);
                    for (GanttTask task : row) {
                        int start = task.getStart();
                        int end = task.getEnd();
                        int width = (int) ((end - start) * (chartWidth * 1.0 / getMaxEnd()));
                        g.setColor(colors.get(i % colors.size()));
                        g.fillRect(50 + (int) (start * (chartWidth * 1.0 / getMaxEnd())), 50 + rowHeight * i, width,
                                rowHeight - 20);
                        g.setColor(Color.BLACK);
                        g.drawString(task.getTitle(), 50 + (int) (start * (chartWidth * 1.0 / getMaxEnd())),
                                40 + rowHeight * i);
                        g.drawLine(50 + (int) (start * (chartWidth * 1.0 / getMaxEnd())), 50 + rowHeight * i,
                                50 + (int) (start * (chartWidth * 1.0 / getMaxEnd())), getHeight() - 50); // Linha
                                                                                                          // vertical
                                                                                                          // para o
                                                                                                          // início
                        g.drawLine(50 + (int) (end * (chartWidth * 1.0 / getMaxEnd())), 50 + rowHeight * i,
                                50 + (int) (end * (chartWidth * 1.0 / getMaxEnd())), getHeight() - 50); // Linha
                                                                                                        // vertical para
                                                                                                        // o fim
                    }
                }
                // Desenhar números
                for (int i = 50; i <= getMaxEnd(); i += 50) {
                    g.drawString(String.valueOf(i), 40 + (int) (i * (chartWidth * 1.0 / getMaxEnd())),
                            getHeight() - 30);
                }
                // Adicionar números de início e fim
                for (int i = 0; i < tasks.size(); i++) {
                    List<GanttTask> row = tasks.get(i);
                    for (GanttTask task : row) {
                        int start = task.getStart();
                        int end = task.getEnd();
                        g.drawString(String.valueOf(start), 50 + (int) (start * (chartWidth * 1.0 / getMaxEnd())),
                                getHeight() - 10);
                        g.drawString(String.valueOf(end), 50 + (int) (end * (chartWidth * 1.0 / getMaxEnd())),
                                getHeight() - 10);
                    }
                }
            }
        };

        add(panel);
    }

    private int getMaxEnd() {
        int maxEnd = Integer.MIN_VALUE;
        for (List<GanttTask> row : tasks) {
            for (GanttTask task : row) {
                if (task.getEnd() > maxEnd) {
                    maxEnd = task.getEnd();
                }
            }
        }
        return maxEnd;
    }

    public void add(String title, int end) {
        title = "  " + title;
        if (titleToRow.containsKey(title)) {
            List<GanttTask> row = tasks.get(titleToRow.get(title));
            GanttTask prevTask = row.get(row.size() - 1);
            int start = Math.max(prevTask.getEnd(), currentTime);
            GanttTask newTask = new GanttTask(title, end);
            newTask.setStart(start);
            row.add(newTask);
        } else {
            List<GanttTask> newRow = new ArrayList<>();
            GanttTask newTask = new GanttTask(title, end);
            newTask.setStart(currentTime);
            newRow.add(newTask);
            tasks.add(newRow);
            titleToRow.put(title, tasks.size() - 1);
        }
        currentTime = end; // Atualiza o tempo atual
        panel.repaint();
    }

}
