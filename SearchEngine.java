import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String start = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Anusha's String: %d", start);
        } //else if (url.getPath().equals("/search")) {
            //start += 1;
            //return String.format("Number incremented!");
        //} 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {
                    start += (parameters[1]);
                    return String.format("String increased by %s! It's now %d", parameters[1], start);
                }
            }
            return "404 Not Found!";
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
