package utils;

public class Timer {

    private long lastTimer;
    private long cooldown;
    private long timer;
    
    private boolean completed = false;
    private boolean notCompleted = false;
    
    private int id;
    
    
    public Timer(long l, int timerId) {
    	cooldown = l;
    	timer = cooldown;
    	this.id = timerId;
    }
    
    public void tick() {
        timer += System.currentTimeMillis() - lastTimer;
        lastTimer = System.currentTimeMillis();
        if(timer < cooldown) {
        	notCompleted = true;
            return;
        }
        timer = 0;
        if(notCompleted) {
        	completed = true;
        }
    }

	public boolean isCompleted() {
		return completed;
	}

	public int getId() {
		return id;
	}
	
}
