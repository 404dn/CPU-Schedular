import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class RoundRobin {

	public static ArrayList<Event> roundRobinlist = new ArrayList<Event>();
	public static int i = 0;
	public static Event z;

	public static void sortEvents() {
		for (int i = 0; i < EventsQueue.queueEvents.size(); i++) {
			Event x = EventsQueue.queueEvents.get(i);
			roundRobinlist.add(x);
		}
		fillLabel();
	}

	public static void fillLabel() {
		for (Event x : roundRobinlist) {
			CpuScheduler.textPane.setText(CpuScheduler.textPane.getText() + "\n" + "Task Number: " + x.getEventNum()
					+ " " + "Task Name: " + x.getProcessName() + " " + "CPU Burst: " + x.getCpuBurst() + " "
					+ "Priority: " + x.getpriority());
		}
		runTasks();
	}

	static class helper extends TimerTask {
		@Override
		public void run() {

			if (z.getCpuBurst() >= 10) {
				
				z.setCpuBurst(z, z.getCpuBurst() - 10);
				CpuScheduler.textPane_1.setText(CpuScheduler.textPane_1.getText() + "\n" + "subtracted--> "
						+ z.getProcessName() + " CPU Burst: " + z.getCpuBurst());
				
				if (z.getCpuBurst() == 0) {
					roundRobinlist.remove(i);
					CpuScheduler.textPane_1.setText(CpuScheduler.textPane_1.getText() + "\n" + "REMOVED--> "
							+ z.getProcessName() + " CPU Burst: " + z.getCpuBurst());
					runTasks();
					
				} else {
					
					i += 1;
					runTasks();
				}
				
			} else if (z.getCpuBurst() <= 10) {
				z.setCpuBurst(z, z.getCpuBurst() - z.getCpuBurst());
				CpuScheduler.textPane_1.setText(CpuScheduler.textPane_1.getText() + "\n" + "subtracted--> "
						+ z.getProcessName() + " CPU Burst: " + z.getCpuBurst());
				
				if (z.getCpuBurst() == 0) {
					roundRobinlist.remove(i);
					CpuScheduler.textPane_1.setText(CpuScheduler.textPane_1.getText() + "\n" + "REMOVED--> "
							+ z.getProcessName() + " CPU Burst: " + z.getCpuBurst());
					runTasks();
				} else {
					i += 1;
					runTasks();
				}
			}
		}
	}

	public static void runTasks() {
		Timer timer = new Timer();
		TimerTask task = new helper();
		
		// when the list = 0 meaning that the list is empty and we are done
		if (roundRobinlist.size() == 0) {
			System.out.println("done");
			JOptionPane.showMessageDialog(null, " Done");
		  //means we are in the middle of the list and we should keep going
		} else if (i < roundRobinlist.size()) {
			z = roundRobinlist.get(i);
			timer.schedule(task, 600);
			
			//reached the end of the list we have to go back to the begining 
		} else if (i == roundRobinlist.size()) {
			i = 0;
			z = roundRobinlist.get(i);
			timer.schedule(task, 600);
		} 

	}

}
