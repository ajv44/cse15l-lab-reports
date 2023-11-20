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
<br>

**First Screenshot:** <br>
<img width="461" alt="Screenshot 2023-11-05 at 10 07 38 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/f2213a9c-9644-4461-9d27-ee82f81155ef">
<br>

- **Methods Called:** The `handleRequest(URI url)` method is called. <br>
- **Relevant Arguments:** The URL (http://localhost:4000/add-message?s=Hey!). The path is `/add-message` and the query is `s=Hey!`. <br>
- **Relevant Fields:**  It is `port` in the StringServer class. <br>
- **Field Value Changes:** `str` now changed to “Hey!” and `num` incremented to 1 <br>
<br>

**Second Screenshot:** <br>
<img width="526" alt="Screenshot 2023-11-05 at 10 09 18 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/14d474f9-d197-4a75-8320-89320e15b945"> 
<br>
- **Methods Called:** The `handleRequest(URI URL)` method is called. <br>
- **Relevant Arguments:** The URL (http://localhost:4000/add-message?s=How%20you%20doing?). The path is `add-message` and the query is `s=How you doing?`<br>
- **Relevant Fields:** <br>
- **Field Value Changes:** `str` changed to "1. Hey! 2. How you doing?" and `num` incremented to 2.<br> <br>

## Part 2

**The path to the *private* key:** <br>
<img width="550" alt="Screenshot 2023-11-05 at 11 41 04 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/ba4bacf0-8a09-4962-950f-fe49898aab86">
<br>

**The path to the *public* key:** <br>
<img width="574" alt="Screenshot 2023-11-05 at 11 36 26 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/8b573461-52e0-42de-8bb9-851dfcbdc0dc">
<br>
<img width="747" alt="Screenshot 2023-11-05 at 11 37 32 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/0750fd28-a069-4ca9-8d13-34d2034a207b">
<br>
- I don't know why `authorized_keys` wasn't accessible. This message popped up when I tried making the SSH key: <br>
<img width="367" alt="Screenshot 2023-11-05 at 11 51 39 PM" src="https://github.com/ajv44/cse15l-lab-reports/assets/146896794/8da999ca-9499-4518-9847-e245f40ac22c">
<br>

<br>

**Logging into `ieng6` without a password:** <br>
- Due to the reason above, I couldn't log into `ieng6` without my password. 
<br>

## Part 3
I thought I learned in this week's lab how to set up an SSH key and authorize myself to not need my password when connecting to the remote server, but I couldn’t replicate it at home. I don’t know why this is occurring and I wish I spent more time on this just in case a random obstacle came up again. 
