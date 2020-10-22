package com.example.scbchallenge;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScbchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScbchallengeApplication.class, args);
	}
	
	public String encodeTxt(String funnyStr) {
		
		//Validate String Parameter is null or empty string
		if (funnyStr.trim().isEmpty()) {
			return "Parameter Value is required!";
		}
		
//		String encode64Str = new String(Base64.getEncoder().encodeToString("A HEN  HAS  MANY   CHICKS".getBytes()));
//		System.out.println("Base64 Encode => "+encode64Str);
		
		//Base64 Decode
		String decodeStr = new String(Base64.getDecoder().decode(funnyStr.trim()));
		//System.out.println("Base64 Decode => "+decodeStr);
		
		//Validate English Capital Letter
		if(!decodeStr.toUpperCase().equals(decodeStr)) {
			return "English Capital Letter can be acceptable only";
		}
		
		//Replace Whitespace with Number and Reverse String
		decodeStr = decodeStr.toLowerCase();
		String revStr = "";
		int cntWS = 0;
		
		for(int i=decodeStr.length()-1;i >= 0; i--) {
			if(decodeStr.charAt(i) == ' ') {
				cntWS++;
			} else {
				if(cntWS != 0) {
					revStr = revStr + Integer.toString(cntWS).charAt(0);
					cntWS = 0;
				}
				revStr = revStr + decodeStr.charAt(i);
				//System.out.println(revStr);
			}
			
		}
		
		//Base64 Encode
		String encodeStr = new String(Base64.getEncoder().encodeToString(revStr.getBytes()));
		
		return encodeStr;
	}

}
