package Converter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import model.Model;
import Game2048.Game2048Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Game2048XMLManager implements ObjectManager {

	@Override
	public boolean ToFile(Model object, String FileName) {
		XStream xstream = new XStream(new StaxDriver());
		try {
			FileOutputStream outputStream = new FileOutputStream(FileName);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream);
			xstream.toXML(new Game2048Model((Game2048Model) object), writer);
			return true;

		} catch (Exception exp) {
			System.out.print(exp.toString());
			return false;
		}
	}


	@Override
	public Model FromFile(String FileName) {
		XStream xstream = new XStream(new StaxDriver());
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileName));
			StringBuffer buff = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				buff.append(line);
			}

			Game2048Model LoadedModel = (Game2048Model) xstream.fromXML(buff
					.toString());
			br.close();
			return LoadedModel;

		} catch (Exception exp) {
			return null;
		}
	}
}
