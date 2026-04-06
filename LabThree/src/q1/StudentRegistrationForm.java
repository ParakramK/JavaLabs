package q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {
	private JTextField nameField, rollField;
	private JPasswordField passField;
	private JRadioButton maleRadio, femaleRadio;
	private ButtonGroup genderGroup;
	private JCheckBox readingBox, musicBox, sportsBox;
	private JComboBox<String> facultyBox;
	private JTextArea displayArea;
	private JButton submitBtn, resetBtn;

	public StudentRegistrationForm() {
		setTitle("Student Registration Form");
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		inputPanel.add(new JLabel("Name:"));
		nameField = new JTextField(20);
		inputPanel.add(nameField);

		inputPanel.add(new JLabel("Password:"));
		passField = new JPasswordField(20);
		inputPanel.add(passField);

		inputPanel.add(new JLabel("Roll Number:"));
		rollField = new JTextField(20);
		inputPanel.add(rollField);

		// Gender
		inputPanel.add(new JLabel("Gender:"));
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		genderPanel.add(maleRadio);
		genderPanel.add(femaleRadio);
		inputPanel.add(genderPanel);

		inputPanel.add(new JLabel("Hobbies:"));
		JPanel hobbyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		readingBox = new JCheckBox("Reading");
		musicBox = new JCheckBox("Music");
		sportsBox = new JCheckBox("Sports");
		hobbyPanel.add(readingBox);
		hobbyPanel.add(musicBox);
		hobbyPanel.add(sportsBox);
		inputPanel.add(hobbyPanel);

		inputPanel.add(new JLabel("Faculty:"));
		String[] faculties = { "Science", "Management", "Arts", "Engineering" };
		facultyBox = new JComboBox<>(faculties);
		inputPanel.add(facultyBox);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		submitBtn = new JButton("Submit");
		resetBtn = new JButton("Reset");
		submitBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		buttonPanel.add(submitBtn);
		buttonPanel.add(resetBtn);

		displayArea = new JTextArea(8, 40);
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);

		add(inputPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(scrollPane, BorderLayout.SOUTH);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			submitForm();
		} else if (e.getSource() == resetBtn) {
			resetForm();
		}
	}

	private void submitForm() {
		// Validation
		String name = nameField.getText().trim();
		String pass = new String(passField.getPassword());
		String roll = rollField.getText().trim();

		if (name.isEmpty() || pass.isEmpty() || roll.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill all mandatory fields!");
			return;
		}

		if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
			JOptionPane.showMessageDialog(null, "Please select gender!");
			return;
		}

		// Build display text
		StringBuilder sb = new StringBuilder();
		sb.append("STUDENT REGISTRATION DETAILS\n");
		sb.append("Name: ").append(name).append("\n");
		sb.append("Roll Number: ").append(roll).append("\n");
		sb.append("Gender: ").append(maleRadio.isSelected() ? "Male" : "Female").append("\n");

		sb.append("Hobbies: ");
		if (readingBox.isSelected())
			sb.append("Reading ");
		if (musicBox.isSelected())
			sb.append("Music ");
		if (sportsBox.isSelected())
			sb.append("Sports ");
		sb.append("\n");

		sb.append("Faculty: ").append(facultyBox.getSelectedItem()).append("\n");

		displayArea.setText(sb.toString());
	}

	private void resetForm() {
		nameField.setText("");
		rollField.setText("");
		passField.setText("");
		genderGroup.clearSelection();
		readingBox.setSelected(false);
		musicBox.setSelected(false);
		sportsBox.setSelected(false);
		facultyBox.setSelectedIndex(0);
		displayArea.setText("");
	}

	public static void main(String[] args) {
		new StudentRegistrationForm();
	}
}