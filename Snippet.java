package snippet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.TestAutomation.AppTest;

public class Snippet {
	
	
	public JSONObject getJsonString() throws JsonParseException, JsonMappingException, IOException
	{
		 ObjectMapper mapper = new ObjectMapper();
        JSONObject root = mapper.readValue(new File("C:\\Users\\91965\\Desktop\\testEnvieonment.postman_environment.json"), JSONObject.class);

        return root;
	}
	
           //Write into the file
	
	public void writeToFile()
	{
		
		AppTest aptest=new AppTest();
		
		try
		{
            FileWriter file = new FileWriter("C:\\Users\\91965\\Desktop\\sample.json"); 
            {
                file.write(aptest.updateJsonValue().toString());
                System.out.println("Successfully updated json object to file...!!");
            }
            
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}	
}
		
		
		
		
		
		
		
		
/*	
	String str="{\r\n" + 
			"	\"id\": \"9f190027-d613-41cc-8e99-6e3503dfca6f\",\r\n" + 
			"	\"name\": \"testEnvieonment\",\r\n" + 
			"	\"values\": [\r\n" + 
			"		{\r\n" + 
			"			\"key\": \"a\",\r\n" + 
			"			\"value\": \"initval\",\r\n" + 
			"			\"enabled\": true\r\n" + 
			"		},\r\n" + 
			"		{\r\n" + 
			"			\"key\": \"b\",\r\n" + 
			"			\"value\": \"initval\",\r\n" + 
			"			\"enabled\": true\r\n" + 
			"		}\r\n" + 
			"	],\r\n" + 
			"	\"_postman_variable_scope\": \"environment\",\r\n" + 
			"	\"_postman_exported_at\": \"2020-09-01T17:07:17.690Z\",\r\n" + 
			"	\"_postman_exported_using\": \"Postman/7.31.1\"\r\n" + 
			"}";
	
	return str;
	}
	}

*/