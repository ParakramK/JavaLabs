package q2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeSalaryCalculator extends JFrame implements ActionListener {
	private JTextField basicField;
	private JComboBox<String> deptBox;
	private JButton calcBtn;
	private JLabel resultLabel;

	public EmployeeSalaryCalculator() {
		setTitle("Employee Salary Calculator");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		inputPanel.add(new JLabel("Basic Salary:"));
		basicField = new JTextField(15);
		inputPanel.add(basicField);

		inputPanel.add(new JLabel("Department:"));
		String[] depts = { "IT", "HR", "Finance", "Marketing", "Operations" };
		deptBox = new JComboBox<>(depts);
		inputPanel.add(deptBox);

		calcBtn = new JButton("Calculate Salary");
		calcBtn.addActionListener(this);
		inputPanel.add(new JLabel());
		inputPanel.add(calcBtn);

		JPanel resultPanel = new JPanel(new FlowLayout());
		resultPanel.add(new JLabel("Gross Salary: "));
		resultLabel = new JLabel("Rs. 0.00");
		resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
		resultPanel.add(resultLabel);

		add(inputPanel, BorderLayout.NORTH);
		add(resultPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == calcBtn) {
			calculateSalary();
		}
	}

	private void calculateSalary() {
		try {
			double basic = Double.parseDouble(basicField.getText().trim());

			if (basic <= 0) {
				JOptionPane.showMessageDialog(this, "Salary must be positive!");
				return;
			}

			double hra = basic * 0.20;
			double da = basic * 0.10;
			double gross = basic + hra + da;

			resultLabel.setText(String.format("Rs. %.2f", gross));

			if (gross > 50000) {
				resultLabel.setForeground(Color.GREEN);
			} else if (gross > 30000) {
				resultLabel.setForeground(Color.BLUE);
			} else {
				resultLabel.setForeground(Color.RED);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric value for Basic Salary.",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new EmployeeSalaryCalculator();
	}
}