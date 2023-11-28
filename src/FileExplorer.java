import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FileExplorer extends JFrame {

  private JTextArea textArea;
  private JFileChooser fileChooser;

  public FileExplorer() {
    textArea = new JTextArea();
    fileChooser = new JFileChooser();

    JButton createButton = new JButton("Create");
    createButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showSaveDialog(FileExplorer.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          try {
            file.createNewFile();
            textArea.setText("Created: " + file.getName() + ".");
          } catch (IOException ex) {
            textArea.setText("Error creating file: " + ex.getMessage());
          }
        } else {
          textArea.setText("Create command cancelled by user.");
        }
      }
    });

    JButton renameButton = new JButton("Rename");
    renameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(FileExplorer.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File oldFile = fileChooser.getSelectedFile();
          String newName = JOptionPane.showInputDialog(FileExplorer.this, "Enter new name:");
          File newFile = new File(oldFile.getParent() + File.separator + newName);
          boolean success = oldFile.renameTo(newFile);
          if (success) {
            textArea.setText("Renamed: " + oldFile.getName() + " to " + newFile.getName() + ".");
          } else {
            textArea.setText("Error renaming file: " + oldFile.getName());
          }
        } else {
          textArea.setText("Rename command cancelled by user.");
        }
      }
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(FileExplorer.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          boolean success = file.delete();
          if (success) {
            textArea.setText("Deleted: " + file.getName() + ".");
          } else {
            textArea.setText("Error deleting file: " + file.getName());
          }
        } else {
          textArea.setText("Delete command cancelled by user.");
        }
      }
    });

    setLayout(new FlowLayout());
    add(createButton);
    add(renameButton);
    add(deleteButton);
    add(new JScrollPane(textArea));

    setSize(400, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new FileExplorer();
  }
}
