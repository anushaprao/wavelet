import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
//import java.util.Iterator;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String start = "";
    ArrayList <String> list = new ArrayList <String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("%s\n", start);
        } 
        else{
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                for (String element : list){
                    if (element.contains(parameters[1])){
                        System.out.println(element);
                    }
                }
            }
            return "404 Not Found!";
        }
            //return "404 Not Found!";
    }
}   

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
/*public class StringServer {
    
}*/
