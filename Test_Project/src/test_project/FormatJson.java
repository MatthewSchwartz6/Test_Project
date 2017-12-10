
package test_project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.egit.github.core.RepositoryCommit;

//Inserts commits as values in custom key names
public class FormatJson {
    public class Wrapper {
        public Object Last;
        public Object ThirdToLast;
        
    }
    
    
    Gson prettyGson; 
    String prettyString;
    public FormatJson(){
        
        prettyGson = new Gson();
    }
    public FormatJson(RepositoryCommit rep, RepositoryCommit rep2){
        prettyGson =  new GsonBuilder().setPrettyPrinting().create();
        String commitStr1 = prettyGson.toJson(rep);
        String commitStr2 = prettyGson.toJson(rep2);
        
        
        
        Wrapper wrapper = new Wrapper();
        //convert to Object to concatenate Json string;
        wrapper.Last = prettyGson.fromJson(commitStr1, Object.class);
        wrapper.ThirdToLast =  prettyGson.fromJson(commitStr2, Object.class);
        
        prettyString = prettyGson.toJson(wrapper);
              
    }
    public String getFormattedString (){
        return prettyString;
    }
 
}
