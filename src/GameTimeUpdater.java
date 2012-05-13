
public class GameTimeUpdater extends JavaGame implements Runnable{
	
	private GameTime time;
	
	//Updates GameTime
	
	public GameTimeUpdater(GameTime time){
		this.time = time;
	}

	//Increase time by one minute
	
	@Override
	public void run() {
		 while (true) {
	            try {
					Thread.sleep(100);	//1000 is 1 second // 100 for testing to speed things up a little
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		time.increase();	//Increase time
		/* To change Alpha values every second instead of everytime updateGUI runs
		if (ReturnTime.returnTimeOfDay() == TimeOfDay.SUNSET){
			SkyFade.increaseAlpha();
		}else if (ReturnTime.returnTimeOfDay() == TimeOfDay.SUNRISE){
			SkyFade.decreaseAlpha();
		}
		*/
	}
	}
	

}
