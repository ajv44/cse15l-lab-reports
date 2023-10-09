# Lab Report 1
Anthony Villalino<br>
Professor Politz<br>
CSE 15L A00, A08<br>
9 October 2023

## The Basic FileSystem Commands
`cd` - "Change Directory" <br>
**Example 1:** Command with *no* arguments. <br>
```
# code block
[user@sahara ~]$ cd

# output
[user@sahara ~]$ 
```
The Working Directory for this command is `/home`. <br>
Output Explanation: Running `cd` will take you to the mentioned path from your current working directory. This output is not an error because there is no directory to change to.<br>

**Example 2:** Command with a path to a *directory* as an argument. <br>
```
# code block
[user@sahara ~]$ cd lecture1

# output
[user@sahara ~/lecture1]$ 
```
The Working Directory for this command is `/lecture1`. <br>
Output Explanation: This output is not an error. This shows that we are in the `lecture1` directory and can access its content. <br>

**Example 3:** Command with a path to a *file* as an argument. <br>
```
# code block
[user@sahara ~]$ cd lecture1/messages

# output
[user@sahara ~/lecture1/messages]$ 
``` 
The Working Directory for this command is `/home`. <br>
Output Explanation: This output is not an error. This shows that we changed the directory to `lecture1/messages` and can access its content.<br>

***

`ls` - "List" <br>
**Example 1:** Command with *no* arguments. <br>
```
# code block
[user@sahara ~]$ ls

# output
lecture1
```
The Working Directory for this command is `/home`. <br>
Output Explanation: The purpose of `ls` is to list the files and folders inside the given path. Since it is listing everything in the working directory, this output is expected and not an error. <br>

**Example 2:** Command with a path to a *directory* as an argument. <br>
```
# code block
[user@sahara ~]$ ls lecture1

# output
Hello.class    Hello.java    messages    README
```
The Working Directory for this command is `/lecture1`. <br>
Output Explanation: Within this working directory, the `ls` command listed all of the relevant content. This output is not an error.<br>

**Example 3:** Command with a path to a *file* as an argument. <br>
```
# code block
[user@sahara ~]$ ls lecture1/messages

# output
en-us.txt    es-mx.txt    zh-cn.txt
```
The Working Directory for this command is `/lecture1/messages`. <br>
Output Explanation: This output is what's expected within this working directory. **However**, if I my input was `[user@sahara ~]$ ls messages` I would receive an error. <br>
```
# output
ls: cannot access 'messages': No such file or directory
```
***

`cat` - "Concatenate"<br>
**Example 1:** Command with *no* arguments. <br>
```
# code block
[user@sahara ~]$ cat

# output

```
The Working Directory for this command is `/home`. <br>
Output Explanation: The `cat` command is meant to print the contents of one or more files given by the paths. Since there are no arguments, it wouldn't print anything. Therefore, this output is not an error. <br>

**Example 2:** Command with a path to a *directory* as an argument. <br>
```
# code block
[user@sahara ~]$ cat lecture1

# output
cat: lecture1: Is a directory
```
The Working Directory for this command is `/home`. <br>
Output Explanation: There isn't any content to concatenate/print, so it returns a confirmation that `lecture1` is a directory. This output is not an error. <br>

**Example 3:** Command with a path to a *file* as an argument. <br>
```
# code block
[user@sahara ~]$ cat lecture1/Hello.java

# output
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hello {
  public static void main(String[] args) throws IOException {
    String content = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);    
    System.out.println(content);
  }
```
The Working Directory for this command is `home`. <br>
Output Explanation: This output is not an error because it printed all of the content in `Hello.java`. <br>
