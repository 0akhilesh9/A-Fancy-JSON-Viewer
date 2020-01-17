import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.util.concurrent.TimeUnit;

public class FileChooser extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fc;
	File f;

public FileChooser() {
	super(new BorderLayout());
	log = new JTextArea(5,20);
	log.setMargin(new Insets(5,5,5,5));
	log.setEditable(false);
	JScrollPane logScrollPane = new JScrollPane(log);
	fc = new JFileChooser();
	fc.setFileFilter(new FileNameExtensionFilter("JSON Save files", "json"));
	openButton = new JButton("Open a File...");
	openButton.addActionListener(this);
	JPanel buttonPanel = new JPanel(); 
	buttonPanel.add(openButton);
	add(buttonPanel, BorderLayout.PAGE_START);
	add(logScrollPane, BorderLayout.CENTER);
}
	
public void startDisplay(FileChooser filechoose){
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			UIManager.put("swing.boldMetal", Boolean.FALSE); 
			createAndShowGUI(filechoose);
		}
	});
	while(filechoose.f==null){
		try{
		TimeUnit.SECONDS.sleep(1);
		}catch(Exception e){
			
		}
		
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == openButton) {
		int returnVal = fc.showOpenDialog(FileChooser.this);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = fc.getSelectedFile().getAbsolutePath();
			if(fileName.endsWith(".json")) {
				File file = fc.getSelectedFile();
				this.f=file;
				log.setText("");
				log.append("Opening: " + file.getName() + "." + newline);
			}
			else{
				log.setText("");
				log.append("Please select json files\n");
			}
		} else {
			log.setText("");
			log.append("Open command cancelled by user." + newline);
        }
		log.setCaretPosition(log.getDocument().getLength());
	} 
}

public static void createAndShowGUI(FileChooser filechoose) {
    JFrame frame = new JFrame("File Chooser ");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(filechoose);
	frame.pack();
	frame.setVisible(true);
}

}
