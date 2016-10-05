package Core;




public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Clock clock = new Clock();
		// TODO Auto-generated method stub
		Project project = new Project("Universitat","Tasques d universitat", null);
		Project projectnew = new Project("Disseny de software", "Practiques",project);
		project.addActivity(projectnew);
		Task task = new Task("Practica1", "Projecte",projectnew);
		//Task task2 = new Task("Practica2", "Projecte2",projectnew);
		projectnew.addActivity(projectnew);
		task.start(clock);
		//task2.start(clock);
		
		/*
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//task2.stop(clock);

		//task.stop(clock);
		
	}
}
