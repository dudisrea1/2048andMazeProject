package Converter;

import model.Model;

public interface ObjectManager {
	/**
	 * Saves a given Model object to a file in XML format
	 * 
	 * @param object
	 *            - the object you want to save
	 * @param FileName
	 *            - the destination for the XML file
	 * @return true if the save succeed, else returns false
	 */
	public boolean ToFile(Model object, String FileName);

	/**
	 * Loads a model from a file
	 * 
	 * @param FileName
	 *            - the destination of the XML file
	 * @return Model - if the model loaded successfully returns the model, else
	 *         returns null
	 */
	public Model FromFile(String FileName);
}
