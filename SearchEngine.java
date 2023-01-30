import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String start = "";
    ArrayList <String> list = new ArrayList <String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Anusha's String: %s", start);
        } else if (url.getPath().equals("/search")) {
            //String[] parameters = url.getQuery().split("=");
            for (String element : list){
                if (element.contains(parameters[1])){
                    System.out.println(element);
                }

            }
            //return String.format("Number incremented!");
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    start += (parameters[1] + " ");
                    list.add(parameters[1]);
                    return String.format("String increased by %s! It's now %s", parameters[1], start);
                }
            }
            return "404 Not Found!";
        }
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
