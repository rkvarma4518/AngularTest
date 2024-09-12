package Utilities;

import java.util.LinkedHashMap;

import java.io.IOException;

public class ValueComparison {

	public void comparenValues(LinkedHashMap<String, String> map1, LinkedHashMap<String, String> map2) {
		if (map1.equals(map2)) {
			// both maps are equal!
			System.out.println("Data values Matching");
		} else {
			// maps are NOT equal!
			System.out.println("Not matching");
		}
	}

	// To test
	public static void main(String... strings) throws IOException {
		ValueComparison compare = new ValueComparison();
		LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> map2 = new LinkedHashMap<String, String>();
		map1.put("name", "Hary");
		map2.put("name", "Hary");

		compare.comparenValues(map1, map2);

	}

}
