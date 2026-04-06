package q5;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

public class FileExplorer extends JFrame {

	private JTree fileTree;
	private JTextArea contentArea;
	private File currentDirectory;

	public FileExplorer() {
		setTitle("File Explorer");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		String home = System.getProperty("user.home");
		currentDirectory = new File(home + "/Desktop");

		JToolBar toolBar = new JToolBar();
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(e -> refreshTree());
		toolBar.add(refreshBtn);

		// Create tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Desktop");
		fileTree = new JTree(root);
		fileTree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
			if (node != null) {
				showFileContent(node);
			}
		});

		JScrollPane treeScroll = new JScrollPane(fileTree);

		// Create text area
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		JScrollPane textScroll = new JScrollPane(contentArea);

		// Split pane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, textScroll);
		splitPane.setDividerLocation(250);

		// Add to frame
		add(toolBar, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);

		// Load files
		loadFiles(currentDirectory, root);

		setVisible(true);
	}

	private void loadFiles(File dir, DefaultMutableTreeNode node) {
		try {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					// Skip hidden files
					if (file.getName().startsWith(".")) {
						continue;
					}

					DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.getName());
					node.add(child);

					if (file.isDirectory()) {
						loadFiles(file, child);
					}
				}
			}
		} catch (Exception e) {
			// Skip if can't access
		}
	}

	private void showFileContent(DefaultMutableTreeNode node) {
		try {
			// Build file path
			String fileName = node.toString();
			File file = new File(currentDirectory, fileName);

			if (file.isFile()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuilder content = new StringBuilder();
				String line;

				content.append("File: ").append(fileName).append("\n");

				int lineCount = 0;
				while ((line = reader.readLine()) != null && lineCount < 50) {
					content.append(line).append("\n");
					lineCount++;
				}
				reader.close();

				if (lineCount >= 50) {
					content.append("\n... (first 50 lines only)");
				}

				contentArea.setText(content.toString());
			} else {
				contentArea.setText("Selected: " + fileName + " (Folder)");
			}
		} catch (Exception e) {
			contentArea.setText("Error: " + e.getMessage());
		}
	}

	private void refreshTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Desktop");
		loadFiles(currentDirectory, root);
		fileTree.setModel(new DefaultTreeModel(root));
	}

	public static void main(String[] args) {
		new FileExplorer();
	}
}