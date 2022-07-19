import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


public class SJF {

	public static ArrayList<Event> SJFlist = new ArrayList<Event>();
	public static int i = 0;
	public static Event z;

	public static void sortEvents() {
		for (int i = 0; i < EventsQueue.queueEvents.size(); i++) {
			Event x = EventsQueue.queueEvents.get(i);
			SJFlist.add(x);
		}
		for (int i = 0; i < SJFlist.size(); i++) {
			for (int j = 0; j < SJFlist.size(); j++) {
				Event x;
				if (SJFlist.get(j).getCpuBurst() > SJFlist.get(i).getCpuBurst()) {
					x = SJFlist.get(i);
					Collections.swap(SJFlist, i, j);
					SJFlist.set(j, x);

				}
			}
		}
		fillLabel();
	}

	public static void fillLabel() {
		for (Event x : SJFlist) {
			CpuScheduler.textPane.setText(CpuScheduler.textPane.getText() + "\n" + "Task Number: " + x.getEventNum()
					+ " " + "Task Name: " + x.getProcessName() + " " + "CPU Burst: " + x.getCpuBurst() + " "
					+ "Priority: " + x.getpriority());
			
		}
		
		runTasks();

	}
	
	static class helper extends TimerTask{

		@Override
		public void run() {	
			String x;
			x = "Task Number: " + z.getEventNum() + " " + "Task Name: " + z.getProcessName() + " " + "CPU Burst: "
					+ z.getCpuBurst() + " " + "Priority: " + z.getpriority();
			CpuScheduler.textPane_1.setText(CpuScheduler.textPane_1.getText() + "\n" + x);
			i +=1;
			runTasks();	
		}
	}
	
	public static void runTasks() {
		
		if (i != SJFlist.size()) {
			z = SJFlist.get(i);
			Timer timer = new Timer();
			TimerTask task = new helper();
			
			timer.schedule(task, z.getCpuBurst()*70);
			
		}else {
			System.out.println("done");
			JOptionPane.showMessageDialog(null, " Done");
		}		
	}

}
