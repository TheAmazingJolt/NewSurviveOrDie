package saving;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.Entity;
import entities.creatures.Player;
import inventory.Inventory;
import items.Item;
import main.Handler;
import states.LoadState;
import utils.Utils;

public class Load
{
	
    static String line = null;
    static int lineInt;
    static int lineNum;
    static int amt;
    
    public static void loadSaveData(String worldName, Handler handler) {
    	String fileName = (new StringBuilder("res/saves/")).append(worldName).append("/saveData.txt").toString();
    	lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
            	lineNum++;
            	if(lineNum == 1) {
            		handler.getGame().setCreationDate(line);
            		handler.getGame().setCreationDateSet(true);
            	}else if(lineNum == 2) {
            		handler.getGame().setModifiedDate(line);
            	}else if(lineNum == 3) {
            		handler.getGame().setDifficultyLevel(Float.parseFloat(line));
            	}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void loadOtherWorldData(String worldName, Handler handler) {
    	String fileName = (new StringBuilder("res/saves/")).append(worldName).append("/worldNumSave.txt").toString();
    	lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
            	lineNum++;
            	if(lineNum == 1) {
            		handler.getWorld().setCurrentArea(Utils.parseInt(line));
            	}else if(lineNum == 2) {
            		int subAreaNum = Utils.parseInt(line);
            		handler.getWorld().setSubAreaNum(subAreaNum);
            		handler.getWorld().getEntityManager().setSubAreaNum(subAreaNum);
            	}else if(lineNum == 3) {
            		LoadState.subAreaName = line;
            	}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void loadItemData(Inventory inventory, String worldName)
    {
    	String fileName = "res/saves/" + worldName + "/itemSave.txt";
    	lineNum = 0;
        String tokens[];
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
            	lineNum++;
                tokens = line.split("\\s+");
                for(Item i : Item.getItems()) {
                	if(i.getId() == Utils.parseInt(tokens[0])) {
                		inventory.addItem1(i, Utils.parseInt(tokens[1]));
                	}
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }

    public static void loadEntityData(Handler handler, String worldName, String fN, ArrayList<Entity> entities)
    {
    	String fileName = "res/saves/" + worldName + "/world" + handler.getWorld().getCurrentArea() + "/" + fN + "EntitySave.txt";
        ArrayList<Entity> list = new ArrayList<Entity>();
        list.clear();
        list.addAll(entities);
        int lineNum = -1;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                lineNum++;
                for(int i = 0;i<list.size();i++) {
                	for(Entity e : list) {
                		if(e == handler.getWorld().getEntityManager().getPlayer())
                			continue;
                		if(e.getId() == lineNum) {
                			if(Utils.parseInt(line) == 0) {
                				e.setActive(true);
                				break;
                			}else if(Utils.parseInt(line) == 1) {
                				e.setActive(false);
                				break;
                			}
                		}else {
                			continue;
                		}
                	}
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }


    public static boolean loadOtherData(Handler handler)
    {
    	boolean cantLoad = true;
        String fileName = "res/saves/basicSave.txt";
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
                handler.getWorld().setAmountOfSaves(Utils.parseInt(line));
            cantLoad = false;
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
        return cantLoad;
    }

    public static void loadPlayerData(Player player, String worldName)
    {
        String fileName = (new StringBuilder("res/saves/")).append(worldName).append("/playerSave.txt").toString();
        int lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
                if(++lineNum == 1)
                {
                    player.setHealth(Float.parseFloat(line));
                }else if(lineNum == 2) {
                	player.setMaxHealth(Utils.parseInt(line));
                }else if(lineNum == 3) {
                	player.setX(Utils.parseInt(line));
                }else if(lineNum == 4) {
                	player.setY(Utils.parseInt(line));
                }else if(lineNum == 5) {
                	player.setSpeed(Utils.parseInt(line));
                }else if(lineNum == 6) {
                	player.setKilledEnemies(Utils.parseInt(line));
                }else if(lineNum == 7) {
                	player.setKilledBosses(Utils.parseInt(line));
                }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }

}
