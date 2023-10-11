/**
- Keep track of a list of strings
- Support a path for ADDING a new string to the list
- Path for QUERYING the list of strings
- Return a list of all strings that have been given a substring
**/

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler 
{
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> arList = new ArrayList<String>();

    public String handleRequest(URI url) 
    {
        if(url.getPath().equals("/"))
        {
            String returns = "";
            for(int i = 0; i < arList.size(); i++)
            {
                returns = returns + arList.get(i) + "\n";
            }
            return returns;
        }
        else if (url.getPath().equals("/add")) 
        {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) 
                {
                    arList.add(parameters[1]);
                    return String.format("Added %s!", parameters[1]);
                }
        } 
        else 
        {
            if (url.getPath().contains("/search")) 
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) 
                {
                    String returns = "";
                    for(int i = 0; i < arList.size(); i++)
                    {
                        if(arList.get(i).contains(parameters[1]))
                        {
                            returns = returns + arList.get(i) + "\n";
                        }
                    }
                    return returns;
                }
            }
            return "404 Not Found!";
        }
    return "404 Not Found!";
    }
}

class SearchEngine 
{
    public static void main(String[] args) throws IOException 
  {
        if(args.length == 0)
        {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
