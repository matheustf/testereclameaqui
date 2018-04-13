package br.com.reclameaqui.teste.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils  {
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        System.out.println(jsonContent);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}