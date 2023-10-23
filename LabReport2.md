# Lab Report 2
Anthony Villalino<br>
Professor Politz<br>
CSE 15L A00, A08<br>
22 October 2023

## Part 1
```java
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String str = "";
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("%s", str);
        }
        else {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    num++;
                    parameters[1] = parameters[1].replace("+"," ");
                    str += num + ". " + parameters[1] + "\n";
                    return String.format("%s", parameters[1], str);
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
```
<br>
I couldn't continue with the next part of the report because an error was popping up when I tried making a port for `StringServer.java`. I tried re-creating Lab 2 and the same error came up for `NumberServer.java` which is the image below.
<br>

![Image](https://github.com/ajv44/cse15l-lab-reports/blob/main/Screenshot%202023-10-22%20at%2011.34.14%20PM.png)

<br>

## Part 2

## Part 3
