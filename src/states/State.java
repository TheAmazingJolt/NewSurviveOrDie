package states;

import java.awt.Graphics;
import main.Handler;

public abstract class State
{

	private static State currentState = null;
	private static State previousState = null;
    protected static Handler handler;
	
    public static void setState(State state)
    {
        currentState = state;
    }

    public static State getPreviousState() {
		return previousState;
	}

	public static void setPreviousState(State previousState) {
		State.previousState = previousState;
	}

	public static State getState()
    {
        return currentState;
    }

    public State(Handler handle)
    {
        handler = handle;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
