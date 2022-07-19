import java.awt.Taskbar;
import java.awt.peer.TaskbarPeer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class FcFs {

	public static ArrayList<Event> FcFslist = new ArrayList<Event>();
	public static int i = 0;
	public static Event z;

	public static void sortEvents() {
		for (int i = 0; i < EventsQueue.queueEvents.size(); i++) {
			Event x = EventsQueue.queueEvents.get(i);
			FcFslist.add(x);
		}
		for (int i = 0; i < FcFslist.size(); i++) {
			for (int j = 0; j < FcFslist.size(); j++) {
				
				Event x;
				
				if (FcFslist.get(j).getEventNum() > FcFslist.get(i).getEventNum()) {
					x = FcFslist.get(i);
					Collections.swap(FcFslist, i, j);
					FcFslist.set(j, x);

				}
			}
		}
		fillLabel();
	}

	public static void fillLabel() {
		for (Event x : FcFslist) {
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
		
		if (i != FcFslist.size()) {
			z = FcFslist.get(i);
			Timer timer = new Timer();
			TimerTask task = new helper();
			
			timer.schedule(task, z.getCpuBurst()*70);
			
		}else {
			System.out.println("done");
			JOptionPane.showMessageDialog(null, " Done");
		}		
	}
}













