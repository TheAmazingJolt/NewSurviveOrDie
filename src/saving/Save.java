package saving;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.Entity;
import entities.creatures.Player;
import items.Item;
import main.Handler;

public class Save {

	static boolean created = false;
    static boolean subCreated1 = false;
    
    private static Handler handler;
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static Date date = new Date();  
	
	public static void createDirectory()
    {
        if(created)
            return;
        if(!created)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave());
            created = directory.mkdir();
        }
    }
    
    public static void createSubDirectory1()
    {
        if(subCreated1)
            return;
        if(!subCreated1)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave()+"/world1");
            subCreated1 = directory.mkdir();
        }
    }
    
    public static void saveSaveData(Handler hndlr, String name) {
    	handler = hndlr;
    	createDirectory();
    	String fileName = (new StringBuilder("res/saves/")).append(name).append("/saveData.txt").toString();
    	try {
    		FileWriter fileWriter = new FileWriter(fileName);
    		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    		
    		if(handler.getGame().creationDateSet)
    			bufferedWriter.write(handler.getGame().creationDate);//date created
    		else if(!handler.getGame().creationDateSet)
    			bufferedWriter.write(dateFormat.format(date));
    		bufferedWriter.newLine();
    		bufferedWriter.write(dateFormat.format(date)); //date modified
    		bufferedWriter.newLine();
    		bufferedWriter.write(Float.toString(handler.getGame().getDifficultyLevel()));
    		bufferedWriter.close();
    	}catch(IOException ex) {
    		createDirectory();
    		saveSaveData(handler, name);
    		System.out.println("Error writing to file '" + fileName +"'");
    	}
    }
    
    public static void saveOtherWorldData(Handler hndlr, String name) {
    	handler = hndlr;
        createDirectory();
        String fileName = (new StringBuilder("res/saves/")).append(name).append("/worldNumSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(Integer.toString(handler.getWorld().getCurrentArea()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(handler.getWorld().getSubAreaNum()));
            bufferedWriter.newLine();
            bufferedWriter.write(handler.getWorld().getSubAreaName());
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveOtherWorldData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void saveItemData(Handler hndlr, String name)
    {
    	handler = hndlr;
        createDirectory();
    	String fileName = "res/saves/" + name + "/itemSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            ArrayList<Item> items = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems();
            
            for(Item i : items) {
            	bufferedWriter.write(i.getId() + " " + i.getCount());
            	bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveItemData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void saveEntityData(Handler handle, String name, String fN)
    {
    	handler = handle;
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list.clear();
    	list.addAll(handler.getWorld().getEntityManager().getEntities());
        createDirectory();
    	createSubDirectory1();
    	String fileName = "res/saves/" + name + "/world" + handler.getWorld().getCurrentArea() + "/" + fN + "EntitySave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for(int i = 0;i<list.size();i++) {
            	for(Entity e : list) {
            		if(e == handler.getWorld().getEntityManager().getPlayer())
            			continue;
            		if(e.getId() == i) {
            			if(e.isActive()) {
                			bufferedWriter.write(Integer.toString(0));
                			bufferedWriter.newLine();
                			break;
                		}else if(!e.isActive()) {
                			bufferedWriter.write(Integer.toString(1));
                			bufferedWriter.newLine();
                			break;
                		}
            		}else {
            			continue;
            		}
            	}
            }
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
        	createSubDirectory1();
            saveEntityData(handler, name, fN);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void savePlayerData(Player player, String name)
    {
        createDirectory();
        String fileName = "res/saves/" + name + "/playerSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Float.toString(player.getHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getMaxHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getX()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getY()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getSpeed()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getKilledEnemies()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getKilledBosses()));
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            savePlayerData(handler.getWorld().getEntityManager().getPlayer(), name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void saveOtherData(int worldCount)
    {
        String fileName = "res/saves/basicSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(worldCount));
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
	
}
