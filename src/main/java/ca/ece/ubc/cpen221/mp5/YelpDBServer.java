package ca.ece.ubc.cpen221.mp5;

public class YelpDBServer {
	
	public static void main(String[] args) {
		
		if(args[0].equals("4949")) {
			runServer r1 = new runServer("Thread1");
			//r1.start();
		}
	}
	
	private static class runServer implements Runnable{

		private Thread t;
		private String threadName;
		
		public runServer(String name) {
			threadName = name;
			System.out.println("YAY YOU");
		}
		   
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("YAY YOU");
		}
		
	}

}
