package com.mate1.hq.rest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mate1.hq.core.IRequestManager;
import com.mate1.hq.core.impl.RequestManager;
import com.mate1.hq.core.impl.SessionManager.QueryResult;

@RestController
@RequestMapping("/")
public class QueueController {
	private IRequestManager rManager = new RequestManager() ; 
	private final Logger logger = Logger.getLogger(QueueController.class) ;
	
	@RequestMapping(path="/{bucketName}", method=RequestMethod.GET)
	public QueryResult getData(@RequestHeader(value="sessionID", defaultValue="") String sessionID, 
			@PathVariable ("bucketName") String name){
		if(sessionID == null || sessionID.isEmpty()){
			logger.info("Attempting to get information from [" + name + "].");
			return rManager.getData(name);
		}else{
			logger.info("Attempting to get information for [" + sessionID + "] from [" + name + "].");
			return rManager.getData(sessionID, name) ;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/{bucketName}/{bucketData}")
	public void addData(@PathVariable ("bucketName") String name, 
			@PathVariable ("bucketData") String data){
		logger.info("Attempting to add [" + data + "] to [" + name + "].");
		rManager.addData(name, data);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/{bucketName}")
	public void addEvent(@RequestBody String data, @PathVariable ("bucketName") String name){
		logger.info("Attempting to add [" + data + "] to [" + name + "].");
		rManager.addData(name, data );
	}
}
