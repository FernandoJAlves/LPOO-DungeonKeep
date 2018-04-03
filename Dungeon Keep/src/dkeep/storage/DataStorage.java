package dkeep.storage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

import dkeep.gui.DungeonKeep;
import dkeep.logic.Game;

public class DataStorage {
	private String path;
	
	public DataStorage() {
		path = "savefile.ser";

	}
	public void save(Game g) {
	      ObjectOutputStream outStream = null;     
	      try {
	    	  outStream = new ObjectOutputStream(new FileOutputStream(path));
	         outStream.writeObject(g);

	      } catch (IOException ioException) {
	         DungeonKeep.getWindow().setLabelText("Error opening file.");
	         
	      } catch (NoSuchElementException noSuchElementException) {
	         System.err.println("Invalid data.");
	      } finally {
	         try {
	            if (outStream != null)
	               outStream.close();
	         } catch (IOException ioException) {
	        	 DungeonKeep.getWindow().setLabelText("Error closing file.");
	         }
	      }
	      DungeonKeep.getWindow().setLabelText("Game saved with sucess.");
	   }

	   public Game load() {
	      ObjectInputStream inputStream = null;
	      Game g = null;
	      try {
	    	  inputStream = new ObjectInputStream(new FileInputStream(path));

	            g = (Game) inputStream.readObject();
	      } catch (EOFException eofException) {
	    	 DungeonKeep.getWindow().setLabelText("Load sucessfull! You can now play the game.");
	         return g;
	      } catch (ClassNotFoundException classNotFoundException) {
	    	 DungeonKeep.getWindow().setLabelText("Object creation failed.");
	    	 return null;
	      } catch (IOException ioException) {
	    	  DungeonKeep.getWindow().setLabelText("Error opening file.");
	    	  System.out.println(path);
	    	  ioException.printStackTrace();
	    	  return null;
	      } finally {
	         try {
	            if (inputStream != null)
	               inputStream.close();
	         } catch (IOException ioException) {
	        	 DungeonKeep.getWindow().setLabelText("Error closing file.");
	        	 return null;
	         }
	      }
	      DungeonKeep.getWindow().setLabelText("Load sucessfull! You can now play the game.");
	      return g;
	   }
}
