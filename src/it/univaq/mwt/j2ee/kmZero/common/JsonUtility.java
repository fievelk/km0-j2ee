package it.univaq.mwt.j2ee.kmZero.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
	
	public static String writeValueAsString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
