package com.company;


import com.esotericsoftware.yamlbeans.YamlReader;
import org.json.JSONObject;
import org.json.XML;
import org.yaml.snakeyaml.Yaml;

import javax.xml.crypto.dsig.XMLObject;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Main {



    /** Paths by default.
     *  public static String path="YourPathOfXmlFile";
     *  public static String pathToWrite="YourPathOfYamlFileToWrite";
     */

    /**
     *  @PRETTY_PRINT_INDENT_FACTOR=4
     *  @String Path
     *  @String PathToWrite
     *  Currently the paths are hardcoded, need to be aware when you change the computer change also the path to your computer dir.
     */

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    public static String path="YourPathOfXmlFile";
    public static String pathToWrite="YourPathOfYamlFileToWrite";

    public static String yamlConversion(String path,String pathToWrite){

        try {

            /*create a fileReader and read the path and than give to the bufferReader*/
            FileReader fileReader=new FileReader(path);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();
            StringBuilder builderAppend=new StringBuilder();
            String line;


            while((line=bufferedReader.readLine())!= null)
            {
                builderAppend=builder.append(line);
            }

            /*create a jsonObject and turn the XML toJsonObject*/


            Yaml yaml=new Yaml();
            JSONObject jsonObject = XML.toJSONObject(builderAppend.toString());
            String jsonPString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
            Map<String ,Object> map= (Map<String,Object>)yaml.load(jsonPString) ;
            //System.out.println(jsonPString);

            //System.out.println(yaml.dump(map));
            try (Writer file = new FileWriter(pathToWrite)) {
                yaml.dump(map,file);
                System.out.println("Successfully Copied Yaml Object to File...");
                System.out.println("\nYAML Object: ");
                file.close();

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }

    public static void main(String[] args) {


        yamlConversion(path,pathToWrite);
    }
}
