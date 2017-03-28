package com.test.Neo4jRESTapi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CypherBuilder {

	private final static Logger logger = Logger.getLogger(CypherBuilder.class.getName());
	
	//Creates ArrayList with Params for one single node
    public static ArrayList<String> createNode(String nodeLabel,String nodeName,String relLabel){
    	ArrayList<String> node = new ArrayList<String>();
    	 node.add(nodeLabel);
         node.add(nodeName);
         node.add(relLabel);
         
         return node;
    }
    
    //Creates cypher based on node(s) given
    public static String createCypher(ArrayList<ArrayList<String>> inputParam){
    	
    	 String cypherParams = "MATCH ";
		 String cypherReturn = " return ";
    	 
		 for(int i=0;i<inputParam.size();i++){

			 //Input will have "Label","nodeName","Rel Label"
			 //Cases for empty input for productData:
    		 if(i<inputParam.size()-1){
    		 
    			 if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(2).isEmpty()){
    				 
    					cypherParams = cypherParams+"(node"+i;
     	         		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
     	         		cypherParams = cypherParams+"-[r"+i+"]->";
     	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName,";
 				 	
    			 }
    			 else if(inputParam.get(i).get(0).isEmpty()){
    				 
 				 		cypherParams = cypherParams+"(node"+i;
 				 		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
 				 		cypherParams = cypherParams+"-[r"+i+":"+inputParam.get(i).get(2)+"]->";
 				 		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName,";
 				 
    			 }
    			 else if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(1).isEmpty()){
    				 
    				 	cypherParams = cypherParams+"(node"+i;
    	         		cypherParams = cypherParams+")";
    	         		cypherParams = cypherParams+"-[r"+i+":"+inputParam.get(i).get(2)+"]->";
    	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName,";
    			 }
    			 else if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(1).isEmpty() && inputParam.get(i).get(2).isEmpty()){
    				 
    				 	cypherParams = cypherParams+"(node"+i;
    	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName,";
    			 }
    			 
    			 else{
    			 
    			 
		         		cypherParams = cypherParams+"(node"+i+":"+inputParam.get(i).get(0);
		         		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
		         		cypherParams = cypherParams+"-[r"+i+":"+inputParam.get(i).get(2)+"]->";
		         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName,";
    			 }
    			 
    		 }
    		 else{
    			 if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(1).isEmpty() && inputParam.get(i).get(2).isEmpty()){
	 				 
	 				 	cypherParams = cypherParams+"(node"+i;
	 				 	cypherParams = cypherParams+")";
	 	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName";
	 			 }
    			 else if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(2).isEmpty()){
    				 
 					cypherParams = cypherParams+"(node"+i;
  	         		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
  	         		
  	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName";
				 	
    			 }
	 			 else if(inputParam.get(i).get(0).isEmpty() && inputParam.get(i).get(1).isEmpty()){
	 				 
	 				 	cypherParams = cypherParams+"(node"+i;
	 	         		cypherParams = cypherParams+")";
	 	         		
	 	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName";
	 			 }
	 			 else if(inputParam.get(i).get(0).isEmpty()){
    				 
	 				 	cypherParams = cypherParams+"(node"+i;
	 	         		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
	 	         		
	 	         		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName";
				 
	 			 }
	 			 
	 			 else{
	    			 
		    			cypherParams = cypherParams+"(node"+i+":"+inputParam.get(i).get(0);
		          		cypherParams = cypherParams+" {nodeName:\""+inputParam.get(i).get(1)+"\"})";
		          		cypherReturn = cypherReturn+"node"+i+".nodeName as node"+i+"_nodeName";
	    		 }
    		 }
    		 
    		 
    				 
         	
         }
    	 
    	 cypherParams = cypherParams+cypherReturn+" limit 3";
    	 return cypherParams;
    }
	
	
	
}

