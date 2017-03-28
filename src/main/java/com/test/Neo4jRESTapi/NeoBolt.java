package com.test.Neo4jRESTapi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.test.Neo4jRESTapi.CypherBuilder;

public class NeoBolt {

	private final static Logger logger = Logger.getLogger(NeoBolt.class.getName());
	 public static StatementResult getResults_multiple(String dataName){
		 
		 	//Bolt Driver connection details
			String bolthost = "//remoteNeo4jDatabase.test.com/";
	    	/*String authUser = "tampleUsr";
	    	String authPassword = "testPW";
			Driver driver = GraphDatabase.driver( "bolt:"+bolthost, AuthTokens.basic( authUser, authPassword ) );
			*/
			Driver driver = GraphDatabase.driver( "bolt:"+bolthost);
	    	Session session = driver.session();
	    	
	    	logger.info("Entered getResults with identifier = "+dataName);

	    	StatementResult result = null;
	    	
	    	//if user specifies productData in API request then
	    	//build relevant cypher query with correct business logic
	    	//*in this case we have a 6 level deep product hierarchy (node0:node5) and results limited to return 3 rows
	    	//* returned hierarchy data would look similar to this:
	    	//* Vehicle|Car|Compact|Honda|Engine|Part1
	    	//* Vehicle|Car|Compact|Honda|Engine|Part2
	    	//* Vehicle|Car|Compact|Honda|Engine|Part3
	    	//return session data
	    	//close session and driver
	    	if(dataName.equals("productData")){
	    		logger.info("Entered NeoBolt.productData");
	    		
	    		String cypher = new String();
	    		
	    		ArrayList<ArrayList<String>> inputParam = new ArrayList<ArrayList<String>>();
	    		
	    		//								Neo4j datatype:     nodeLabel  nodeProperty  relLabel
	    		//  		 nodeSample = CypherBuilder.createNode("nodeLabel", "nodeName", "relType");
	    	    ArrayList<String> node0 = CypherBuilder.createNode("Vehicle","Vehicle","hasSubClass");
	            ArrayList<String> node1 = CypherBuilder.createNode("Car","Automobile","hasInstance");
	            ArrayList<String> node2 = CypherBuilder.createNode("","Compact","hasChild");
	            ArrayList<String> node3 = CypherBuilder.createNode("","Honda","hasChild");
	            ArrayList<String> node4 = CypherBuilder.createNode("","Engine","");
	            ArrayList<String> node5 = CypherBuilder.createNode("","","");
	            
	  	        inputParam.add(node0);
	   	        inputParam.add(node1);
	   	        inputParam.add(node2);
	   	        inputParam.add(node3);
	   	        inputParam.add(node4);
	   	        inputParam.add(node5);
	    	       
	   	        cypher=CypherBuilder.createCypher(inputParam);
	   	        logger.info(cypher);
	   	        
		    	result = session.run(cypher);
		    	session.close();
		    	driver.close();
		    	logger.info("session and driver closed, exiting getResults");
		    	 	
			//return record_list;
	    	
	 }
	    		logger.info("resultL "+result.toString());
	    		return result;
}}