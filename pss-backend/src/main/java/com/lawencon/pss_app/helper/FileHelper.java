package com.lawencon.pss_app.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class FileHelper {

	public String encodeFileToBase64(String filePath) {
	    try {
	        // Read all bytes from the file
	        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
	        // Encode the byte array into a Base64 string
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null; // or handle the error appropriately
	    }
	}
}
