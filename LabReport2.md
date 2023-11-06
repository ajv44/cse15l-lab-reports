# Lab Report 2
Anthony Villalino<br>
Professor Politz<br>
CSE 15L A00, A08<br>
22 October 2023

## Part 1
Below is my code for StringServer
```java
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    private String str = "";
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                str += ++num + ". " + parameters[1] + "\n";
                return str;
            } else {
                return "Invalid parameter!";
            }
        } else {
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException{
        if(args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}
```


## Part 2
N/A

## Part 3
N/A - - I thought I learned it in the lab but since I can't currently re-create it I guess not lol.
