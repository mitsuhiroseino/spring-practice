package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/practice")
public class AppController {
	
    @RequestMapping(method=RequestMethod.GET)
    public String get() {
        return "GET OK!";
    }
	
    @PostMapping(
		consumes = {
    		MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    		MediaType.MULTIPART_FORM_DATA_VALUE
		},
		produces=MediaType.APPLICATION_JSON_VALUE
	)
    public @ResponseBody Map<String, Object> post(@RequestParam Map<String, Object> json, List<MultipartFile> file1, List<MultipartFile> file2) {
    	Map<String, Object> response = new HashMap<>();
    	
    	response.put("json", json);
    	
    	if (file1 != null) {
        	List<String> file1Response = new ArrayList<>();
        	for (MultipartFile f1 : file1) {
        		File toFile1 = new File("C:\\temp", f1.getOriginalFilename()); 
        	    try {
					f1.transferTo(toFile1);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}         		
        		file1Response.add(f1.getOriginalFilename());
        	}
        	response.put("file1", file1Response);
    	}

    	if (file2 != null) {
	    	List<String> file2Response = new ArrayList<>();
	    	for (MultipartFile f2 : file2) {
        		File toFile2 = new File("C:\\temp", f2.getOriginalFilename()); 
        	    try {
					f2.transferTo(toFile2);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}         		
	    		file2Response.add(f2.getOriginalFilename());
	    	}
	    	response.put("file2", file2Response);
    	}
    	
        return response;
    }
    
}
