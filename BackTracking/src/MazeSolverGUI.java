import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeSolverGUI extends JFrame {
    private int n;
    private JButton[][] mazeButtons;
    private int[][] maze;
    private JPanel mazePanel;

    public MazeSolverGUI() {
        setTitle("Maze Solver");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para ingresar el tamaño del laberinto
        JPanel inputPanel = new JPanel();
        JLabel label = new JLabel("Tamaño del Laberinto NxN:");
        JTextField sizeField = new JTextField(5);
        JButton generateButton = new JButton("Generar");
        inputPanel.add(label);
        inputPanel.add(sizeField);
        inputPanel.add(generateButton);
        add(inputPanel, BorderLayout.NORTH);

        // Panel para el laberinto
        mazePanel = new JPanel();
        add(mazePanel, BorderLayout.CENTER);

        // Botón para encontrar la solución
        JButton solveButton = new JButton("Encontrar Solución");
        add(solveButton, BorderLayout.SOUTH);

        // Acción al presionar "Generar"
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n = Integer.parseInt(sizeField.getText());
                maze = new int[n][n];
                mazeButtons = new JButton[n][n];
                mazePanel.removeAll();
                mazePanel.setLayout(new GridLayout(n, n));
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        mazeButtons[i][j] = new JButton();
                        mazeButtons[i][j].setBackground(Color.WHITE);
                        mazeButtons[i][j].addActionListener(new ButtonClickListener(i, j));
                        mazePanel.add(mazeButtons[i][j]);
                    }
                }
                mazePanel.revalidate();
                mazePanel.repaint();
            }
        });

        // Acción al presionar "Encontrar Solución"
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] solution = new int[n][n];
                if (solveMaze(maze, solution, 0, 0)) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (solution[i][j] == 1) {
                                mazeButtons[i][j].setBackground(Color.GREEN);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un camino.");
                }
            }
        });
    }

    private class ButtonClickListener implements ActionListener {
        private int x, y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = mazeButtons[x][y];
            if (maze[x][y] == 0) {
                maze[x][y] = 1;
                button.setBackground(Color.RED);
            } else {
                maze[x][y] = 0;
                button.setBackground(Color.WHITE);
            }
        }
    }

    public boolean solveMaze(int[][] maze, int[][] solution, int x, int y) {
        if (x == n - 1 && y == n - 1 && maze[x][y] == 0) {
            solution[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y, solution)) {
            solution[x][y] = 1;

            // Moverse hacia la izquierda
            if (solveMaze(maze, solution, x, y - 1))
                return true;
            // Moverse hacia abajo
            if (solveMaze(maze, solution, x + 1, y))
                return true;
            // Moverse hacia la derecha
            if (solveMaze(maze, solution, x, y + 1))
                return true;
            // Moverse hacia arriba
            if (solveMaze(maze, solution, x - 1, y))
                return true;

            solution[x][y] = 0;
            return false;
        }

        return false;
    }

    public boolean isSafe(int[][] maze, int x, int y, int[][] solution) {
        return (x >= 0 && x < n && y >= 0 && y < n && maze[x][y] == 0 && solution[x][y] == 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MazeSolverGUI gui = new MazeSolverGUI();
            gui.setVisible(true);
        });
    }
}
