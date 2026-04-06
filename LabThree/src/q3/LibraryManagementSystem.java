package q3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementSystem extends JFrame implements ActionListener {

	private JTextField nameField, authorField, priceField;
	private JButton addBtn, deleteBtn, clearBtn;
	private JTable bookTable;
	private DefaultTableModel tableModel;

	public LibraryManagementSystem() {
		setTitle("Library Management System");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Input Panel
		JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		inputPanel.add(new JLabel("Book Name:"));
		nameField = new JTextField();
		inputPanel.add(nameField);

		inputPanel.add(new JLabel("Author:"));
		authorField = new JTextField();
		inputPanel.add(authorField);

		inputPanel.add(new JLabel("Price (Rs):"));
		priceField = new JTextField();
		inputPanel.add(priceField);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		addBtn = new JButton("Add Book");
		deleteBtn = new JButton("Delete Selected");
		clearBtn = new JButton("Clear Fields");

		addBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		buttonPanel.add(addBtn);
		buttonPanel.add(deleteBtn);
		buttonPanel.add(clearBtn);

		// Table Setup
		String[] columns = { "Book Name", "Author", "Price (Rs)" };
		tableModel = new DefaultTableModel(columns, 0);
		bookTable = new JTable(tableModel);
		bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(bookTable);

		// Add row selection listener
		bookTable.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && bookTable.getSelectedRow() != -1) {
				int row = bookTable.getSelectedRow();
				nameField.setText(tableModel.getValueAt(row, 0).toString());
				authorField.setText(tableModel.getValueAt(row, 1).toString());
				priceField.setText(tableModel.getValueAt(row, 2).toString());
			}
		});

		// Add panels
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(inputPanel, BorderLayout.CENTER);
		topPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == addBtn) {
				addBook();
			} else if (e.getSource() == deleteBtn) {
				deleteBook();
			} else if (e.getSource() == clearBtn) {
				clearFields();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Application Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addBook() {
		String name = nameField.getText().trim();
		String author = authorField.getText().trim();
		String priceText = priceField.getText().trim();

		if (name.isEmpty() || author.isEmpty() || priceText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "All fields are required!");
			return;
		}

		try {
			double price = Double.parseDouble(priceText);
			if (price <= 0) {
				JOptionPane.showMessageDialog(this, "Price must be positive!");
				return;
			}

			tableModel.addRow(new Object[] { name, author, price });
			clearFields();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Price must be a valid number!", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteBook() {
		int selectedRow = bookTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a row to delete!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
				"Confirm Delete", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			tableModel.removeRow(selectedRow);
			clearFields();
		}
	}

	private void clearFields() {
		nameField.setText("");
		authorField.setText("");
		priceField.setText("");
		bookTable.clearSelection();
	}

	public static void main(String[] args) {
		new LibraryManagementSystem();
	}
}