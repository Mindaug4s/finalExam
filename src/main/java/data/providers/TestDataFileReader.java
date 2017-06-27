package data.providers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class TestDataFileReader {
	static List<Object[]> lines = new ArrayList<Object[]>();
	static BufferedReader myInput = null;

	public static Object[][] fillArrayWithDataFromFile(String... fileLinks) {

		String thisLine;
		for (String currentFile : fileLinks) {
			myInput = null;
			try {
				myInput = new BufferedReader(
						new InputStreamReader(new FileInputStream(currentFile), StandardCharsets.UTF_8));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				while ((thisLine = myInput.readLine()) != null) {
					// lines.add(thisLine.split(","));
					lines.add(thisLine.split("   "));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// lines.remove(0);
		String[][] array = new String[lines.size()][0];

		try {
			myInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines.toArray(array);
	}

}
