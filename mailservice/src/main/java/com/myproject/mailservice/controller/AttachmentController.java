package com.myproject.mailservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.AttachmentDTO;
import com.myproject.mailservice.entity.Attachment;
import com.myproject.mailservice.repository.AttachmentRepository;
import com.myproject.mailservice.util.MediaTypeUtils;

@RestController
@RequestMapping(value="mailservice/attachments")
public class AttachmentController {
	
	@Autowired
	private AttachmentRepository attachmentService;
	
	@Autowired
	private ServletContext servletContext;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String DIRECTORY = "C:\\Users\\Sandra\\Pictures\\mailService\\mailserviceUES\\mailservice\\src\\main\\resources\\static\\files";
	
	 @GetMapping(value = "/message/{id}")
	    public ResponseEntity<List<AttachmentDTO>> getAccountsByMessage(@PathVariable("id") Long id){
		 	logger.info("GET request for all attachments for message with id: " + id);
		 	List<Attachment> attachments= attachmentService.getByMessageId(id);
	        List<AttachmentDTO>attachmentsDTO=new ArrayList<>();
	        if(attachments == null)
	            return new ResponseEntity<List<AttachmentDTO>>(HttpStatus.NOT_FOUND);
	        else {
	            for (Attachment a:attachments)
	            	attachmentsDTO.add(new AttachmentDTO(a));
	            
	        }
	        return new ResponseEntity<List<AttachmentDTO>>(attachmentsDTO,HttpStatus.OK);
	    }
	 
	
	 @PostMapping(value= "/download1/{filename}")
	    public ResponseEntity<InputStreamResource> downloadFile1(@PathVariable("filename")String fileName) throws IOException {
		 	logger.info("Download attachment: " + fileName);
	        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
	        System.out.println("fileName: " + fileName);
	        System.out.println("mediaType: " + mediaType);
	        HttpHeaders respHeaders = new HttpHeaders();
            
	        File file = new File(DIRECTORY + "/" + fileName);
	        respHeaders.setContentType(mediaType);
            respHeaders.setContentLength(file.length());
            respHeaders.setContentDispositionFormData("attachment", file.getName());
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	 
	        return new ResponseEntity<InputStreamResource>(resource, respHeaders, HttpStatus.OK);
	    }
	 

}
