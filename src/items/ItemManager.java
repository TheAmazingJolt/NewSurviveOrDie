package items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import main.Handler;

// Referenced classes of package items:
//            Item

public class ItemManager
{

	private Handler handler;
    private ArrayList<Item> items;
	
    public ItemManager(Handler handler)
    {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick()
    {
        for(Iterator<Item> it = items.iterator(); it.hasNext();)
        {
            Item i = (Item)it.next();
            i.tick();
            if(i.isPickedUp())
                it.remove();
        }

    }

    public void render(Graphics g)
    {
        Item i;
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext(); i.render(g))
            i = (Item)iterator.next();

    }

    public void addItem(Item i)
    {
        i.setHandler(handler);
        items.add(i);
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    
}
