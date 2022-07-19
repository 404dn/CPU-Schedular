import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.security.auth.x500.X500Principal;
import javax.swing.JButton;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.DropMode;

public class CpuScheduler {

	private JFrame frame;
	private JTextField textFieldTaskName;
	private JTextField textFieldCpuBurst;
	private JTextField textFieldTaskPriority;
	JComboBox comboBox;
	JButton btnNewButton;
	JLabel lblSdfgsdfg;
	JLabel lblNewLabel;
	JLabel lblSdfgsdfgsd;
	public static JTextPane tasksLbl;
	JButton btnReadFromFile;
	public static JTextPane textPane;
	public static JTextPane textPane_1;

	int EventNum = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CpuScheduler window = new CpuScheduler();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CpuScheduler() {
		initialize();

	}

	public void readFile() {
		String Tname = "";
		String Tpriority = "";
		String Tburst = "";
		try {
			File file = new File("C:\\Users\\Adnan\\eclipse-workspace\\cpu scheduler\\Files\\Schedule_03.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				Tname = scanner.next();
				Tname = Tname.replace(",", "");

				Tpriority = scanner.next();
				Tpriority = Tpriority.replace(",", "");

				Tburst = scanner.next();
				Tburst = Tburst.replace(",", "");

				System.out.println("name " + Tname + " Priority " + Tpriority + " Burst" + Tburst);
				createEvent(Tname, Integer.valueOf(Tpriority), Integer.valueOf(Tburst));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void Execute() {
		String algo = (String) comboBox.getSelectedItem();
		switch (algo) {
		case "FCFS":
			FcFs.sortEvents();
			break;
		case "SJF":
			SJF.sortEvents();
			break;
		case "Prority":
			priorityScheduling.sortEvents();
			break;
		case "RR":
			RoundRobin.sortEvents();
			break;

		default:
			System.out.println("Choose an algo");
			break;
		}

	}

	public void createEvent(String taskName, int priority, int cpuBurst) {
		String x = "";
//		Event Evnt = new Event(EventNum ,textFieldTaskName.getText() ,Integer.valueOf(textFieldCpuBurst.getText()) ,Integer.valueOf(textFieldTaskPriority.getText()));
		Event Evnt = new Event(EventNum, taskName, cpuBurst, priority);

		EventsQueue.addToList(Evnt);
		System.out.println();
		x = "Task Number: " + Evnt.getEventNum() + " " + "Task Name: " + Evnt.getProcessName() + " " + "CPU Burst: "
				+ Evnt.getCpuBurst() + " " + "Priority: " + Evnt.getpriority();
		tasksLbl.setText(tasksLbl.getText() + "\n" + x );

		EventNum += 1;
	}

	public void btnPressed() {
		createEvent(textFieldTaskName.getText(), Integer.valueOf(textFieldTaskPriority.getText()),
				Integer.valueOf(textFieldCpuBurst.getText()));
	}
	
	public static void clear() {
		RoundRobin.roundRobinlist.clear();
		priorityScheduling.priorityList.clear();
		FcFs.FcFslist.clear();
		SJF.SJFlist.clear();
		EventsQueue.queueEvents.clear();
		
		textPane_1.setText(" ");
		textPane.setText(" ");
		tasksLbl.setText(" ");
		
		RoundRobin.i = 0;
		priorityScheduling.i = 0;
		FcFs.i = 0;
		SJF.i = 0;
		
		
		
		System.out.println(RoundRobin.roundRobinlist.size());
		System.out.println(priorityScheduling.priorityList.size());
		System.out.println(FcFs.FcFslist.size());
		System.out.println(SJF.SJFlist.size());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("AddTask");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(418, 533, 156, 27);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(e -> btnPressed());

		lblNewLabel = new JLabel("Priority");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(418, 115, 156, 29);
		frame.getContentPane().add(lblNewLabel);

		lblSdfgsdfg = new JLabel("CPU Burst\r\n");
		lblSdfgsdfg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSdfgsdfg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSdfgsdfg.setBounds(418, 193, 156, 29);
		frame.getContentPane().add(lblSdfgsdfg);

		lblSdfgsdfgsd = new JLabel("Task Name\r\n");
		lblSdfgsdfgsd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSdfgsdfgsd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSdfgsdfgsd.setBounds(418, 37, 156, 29);
		frame.getContentPane().add(lblSdfgsdfgsd);

		textFieldTaskName = new JTextField();
		textFieldTaskName.setBounds(418, 77, 156, 27);
		frame.getContentPane().add(textFieldTaskName);
		textFieldTaskName.setColumns(10);

		textFieldCpuBurst = new JTextField();
		textFieldCpuBurst.setColumns(10);
		textFieldCpuBurst.setBounds(418, 233, 156, 27);
		frame.getContentPane().add(textFieldCpuBurst);

		textFieldTaskPriority = new JTextField();
		textFieldTaskPriority.setColumns(10);
		textFieldTaskPriority.setBounds(418, 155, 156, 27);
		frame.getContentPane().add(textFieldTaskPriority);

		String Algo[] = { "FCFS", "SJF", "Prority", "RR" };
		comboBox = new JComboBox(Algo);
		comboBox.setBounds(418, 464, 156, 20);
		frame.getContentPane().add(comboBox);

		JButton btnExecute = new JButton("Execute");
		btnExecute.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExecute.setBounds(418, 609, 156, 27);
		frame.getContentPane().add(btnExecute);
		btnExecute.addActionListener(e -> Execute());

		JLabel lblNewLabel_2 = new JLabel("Task Queue");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(78, -1, 271, 27);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(10, 37, 398, 169);
		frame.getContentPane().add(sp);

		tasksLbl = new JTextPane();
		sp.setViewportView(tasksLbl);
		tasksLbl.setEditable(false);

		btnReadFromFile = new JButton("Read From File");
		btnReadFromFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReadFromFile.setBounds(418, 571, 156, 27);
		frame.getContentPane().add(btnReadFromFile);

		JScrollPane sp2 = new JScrollPane();
		sp2.setBounds(10, 256, 398, 169);
		frame.getContentPane().add(sp2);

		textPane = new JTextPane();
		textPane.setEditable(false);
		sp2.setViewportView(textPane);

		JLabel lblReadyQeue = new JLabel("Ready Queue");
		lblReadyQeue.setHorizontalAlignment(SwingConstants.CENTER);
		lblReadyQeue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReadyQeue.setBounds(78, 224, 271, 27);
		frame.getContentPane().add(lblReadyQeue);

		JScrollPane sp3 = new JScrollPane();
		sp3.setBounds(10, 467, 398, 169);
		frame.getContentPane().add(sp3);

		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		sp3.setViewportView(textPane_1);

		JLabel lblT = new JLabel("Done tasks");
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblT.setBounds(78, 434, 271, 27);
		frame.getContentPane().add(lblT);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(418, 495, 156, 27);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(e -> clear());
		
		
		btnReadFromFile.addActionListener(e -> readFile());

	}
}
