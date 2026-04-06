package q4;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class MDIApplication extends JFrame {
	private JDesktopPane desktopPane;
	private HashSet<String> openFrames;

	public MDIApplication() {
		setTitle("MDI Application");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		openFrames = new HashSet<>();
		desktopPane = new JDesktopPane();
		add(desktopPane);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem studentItem = new JMenuItem("New Student Window");
		JMenuItem courseItem = new JMenuItem("New Course Window");
		JMenuItem exitItem = new JMenuItem("Exit");

		studentItem.addActionListener(e -> openStudentFrame());
		courseItem.addActionListener(e -> openCourseFrame());
		exitItem.addActionListener(e -> System.exit(0));

		fileMenu.add(studentItem);
		fileMenu.add(courseItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		setVisible(true);
	}

	private void openStudentFrame() {
		if (openFrames.contains("Student")) {
			JOptionPane.showMessageDialog(this, "Student window already open!");
			return;
		}

		JInternalFrame frame = new JInternalFrame("Student", true, true, true, true);
		frame.setSize(350, 250);
		frame.setLocation(20, 20);

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("ID:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Name:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Faculty:"));
		panel.add(new JComboBox<>(new String[] { "Science", "Management" }));
		panel.add(new JButton("Save"));
		panel.add(new JButton("Cancel"));

		frame.add(panel);
		desktopPane.add(frame);
		frame.setVisible(true);

		openFrames.add("Student");

		frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
				openFrames.remove("Student");
			}
		});
	}

	private void openCourseFrame() {
		if (openFrames.contains("Course")) {
			JOptionPane.showMessageDialog(this, "Course window already open!");
			return;
		}

		JInternalFrame frame = new JInternalFrame("Course", true, true, true, true);
		frame.setSize(350, 250);
		frame.setLocation(100, 100);

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("Code:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Name:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Credits:"));
		panel.add(new JTextField());
		panel.add(new JButton("Add"));
		panel.add(new JButton("Close"));

		frame.add(panel);
		desktopPane.add(frame);
		frame.setVisible(true);

		openFrames.add("Course");

		frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
				openFrames.remove("Course");
			}
		});
	}

	public static void main(String[] args) {
		new MDIApplication();
	}
}
