import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    String start = "";
    ArrayList <String> list = new ArrayList <String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("%s", start);
        } 
        else{
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    start += (parameters[1] + "\n");
                    list.add(parameters[1]);
                    return String.format("%s", parameters[1], start);

                }
            }
            return "404 Not Found!";
        }
    }
}   

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

