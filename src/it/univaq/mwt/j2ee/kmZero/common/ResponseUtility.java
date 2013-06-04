package it.univaq.mwt.j2ee.kmZero.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtility {

	public static void generateJsonResponse(HttpServletResponse response, String value) {
		response.setContentType("application/json");
		try {
			response.getWriter().write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
