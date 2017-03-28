package com.test.Neo4jRESTapi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//endpoint to confirm http get call can communicate with server and return a string 
@RestController
@RequestMapping(Controller.API_BASE_URI)
public class Controller
{
  private static final Logger logger = Logger.getLogger(Controller.class.getName());
  public static final String API_BASE_URI = "/";
  
  @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public String test()
  {
    logger.info("Entered NeoJSON_getHier Request Mapping....test");    

    return "Test Successful";
  }
  
  
  //for business request call.  in this example dataName might be "productData" and this call will return product data rows in a string format
  @RequestMapping(value = "getData/{dataName}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String  getHierarchy(@PathVariable final String dataName){

		logger.info("Entered NeoJSON_getHier Request Mapping");
		
	
		StatementResult result =  NeoBolt.getResults_multiple(dataName);
		List<JSONObject> ljo = new ArrayList<JSONObject>();
	
		while(result.hasNext()){
			Record record = result.next();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(record.get(0).toString(),record.asMap().toString());
			ljo.add(jsonObject);
		}

		return ljo.toString();
	}
}