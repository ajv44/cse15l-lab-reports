# Part 1 - Bugs

We'll be exploring the `ArrayExample.java` file to look for bugs.<br> 

## Failure-inducing input
```
	@Test 
	public void testReverseInPlace1() {
	  int[] input1 = { 2, 3, 4 };
	  ArrayExamples.reverseInPlace(input1);
	  assertArrayEquals(new int[]{ 4, 3, 2 }, input1);
	  }
```

## Non-failure-inducing input
```
    @Test
    public void testReversedInPlace2() {
      int[] input1 = { 2 };
      ArrayExamples.reverseInPlace(input1);
      assertArrayEquals(new int[]{ 2 }, input1); 
    }
```

## The Symptom
```
	java -cp ..:lib/hamcrest-core-1.3.jar..:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ArrayTests
	JUnit version 4.13.2
	.E
	Time: 0,001
	There was 1 failure:
	1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
	java.lang.IllegalArgumentException: Could not find class [ArrayTests]
		at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
		at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
		at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
		at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
		at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
	Caused by: java.lang.ClassNotFoundException: ArrayTests
		at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
		at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
		at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:526)
		at java.base/java.lang.Class.forName0(Native Method)
		at java.base/java.lang.Class.forName(Class.java:534)
		at java.base/java.lang.Class.forName(Class.java:513)
		at org.junit.internal.Classes.getClass(Classes.java:42)
		at org.junit.internal.Classes.getClass(Classes.java:27)
		at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:98)
		... 4 more
	
	FAILURES!!!
	Tests run: 2,  Failures: 1
```

## The Bug
Before the change:
```
	static void reverseInPlace(int[] arr) {
	    for(int i = 0; i < arr.length; i += 1) {
	      arr[i] = arr[arr.length - i - 1];
	    }
	  }
```

After the change:
```
	static void reverseInPlace(int[] arr) {
	    for(int i = 0; i < arr.length / 2; i += 1) {
	      int temp = arr[i];
	      arr[i] = arr[arr.length - i - 1];
	      arr[arr.length - i - 1] = temp;
	    }
	  }
```

The method before the change is attempting to reverse the array by 
directly assigning elements at each position `i` with the corresponding element at the opposite end `(arr.length - i - 1)`. 
This causes elements to be prematurely overwritten, which leads to an incorrect result. 
The method after the change swaps the elements around the midpoint. 
This ensures that each element is in the correct position in reversed order. 
Ultimately, the method before the change replaced each element with its counterpart from the end of the array without proper swapping.

# Part 2 - Research Commands

We'll be exploring four different ways to use the `find` command.

## Find a file by name
**Example 1:**
```
# code
find ./technical -type f -name cc2358.txt

# output
./technical/biomed/cc2358.txt
```
**Explanation:** This is searching to find a file by its name inside `/technical`. This is useful because you can navigate to the subdirectory it is in, which in this case is `/biomed`. 

<br>

**Example 2:**
```
# code
find ./technical -type f -name pmed.0020281.txt

# output
./technical/plos/pmed.0020281.txt
```
**Explanation:** This is searching for a file by its name and returned the path which is useful when you want to analyze specific files. 

<br>
**Source:** https://www.redhat.com/sysadmin/linux-find-command
<br>

## Find a file by size 
**Example 1:**
```
# code
find ./technical -type f -size -1k

# output
./technical/plos/pmed.0020191.txt
./technical/plos/pmed.0020226.txt
```
**Explanation:** This is searching for a file by its size which in this case is less than 1 kilobyte. This is helpful because you can quickly identify and manage files from inside the specified directory's structure. 

<br>

**Example 2:**
```
# code
find ./technical -type f -size -2k

# output
./technical/government/Media/Helping_Hands.txt
./technical/government/Media/Campaign_Pays.txt
./technical/government/Media/Fire_Victims_Sue.txt
./technical/government/Media/Court_Keeps_Judge_From.txt
./technical/government/Media/It_Pays_to_Know.txt
./technical/government/Media/Self-Help_Website.txt
./technical/government/Media/Justice_requests.txt
./technical/government/Media/Wilmington_lawyer.txt
./technical/government/Media/Lawyer_Web_Survey.txt
./technical/plos/pmed.0020048.txt
./technical/plos/pmed.0020028.txt
./technical/plos/pmed.0020191.txt
./technical/plos/pmed.0020226.txt
./technical/plos/pmed.0020192.txt
./technical/plos/pmed.0020157.txt
./technical/plos/pmed.0020082.txt
./technical/plos/pmed.0020120.txt
```
**Explanation:** This is searching for a file that is less than 2k. The reason why it's helpful is the same as above. I thought it was fun to notice that the output from Example 1 is also in Example 2.

<br>
**Source:** https://unix.stackexchange.com/questions/638335/find-command-size-behavior
<br>

## Find a file by type
**Example 1:**
```
# code
find ./technical/911report -type f

# output
./technical/911report/chapter-13.4.txt
./technical/911report/chapter-13.5.txt
./technical/911report/chapter-13.1.txt
./technical/911report/chapter-13.2.txt
./technical/911report/chapter-13.3.txt
./technical/911report/chapter-3.txt
./technical/911report/chapter-2.txt
./technical/911report/chapter-1.txt
./technical/911report/chapter-5.txt
./technical/911report/chapter-6.txt
./technical/911report/chapter-7.txt
./technical/911report/chapter-9.txt
./technical/911report/chapter-8.txt
./technical/911report/preface.txt
./technical/911report/chapter-12.txt
./technical/911report/chapter-10.txt
./technical/911report/chapter-11.txt
```
**Explanation:** This is searching for a file that is specifically a regular file, not a directory, symlink, etc. which is helpful when a subdirectory has multiple types of files.

<br>

**Example 2:**
```
# code
find ./technical -type d

# output
./technical
./technical/government
./technical/government/About_LSC
./technical/government/Env_Prot_Agen
./technical/government/Alcohol_Problems
./technical/government/Gen_Account_Office
./technical/government/Post_Rate_Comm
./technical/government/Media
./technical/plos
./technical/biomed
./technical/911report
```
**Explanation:** This is searching for a file that is specifically a directory, which is useful in getting a quick layout of the structure of the directory.

<br>
**Source:** https://www.redhat.com/sysadmin/linux-find-command
<br>

## Find a file by approximate name
**Example 1:**
```
# code
find ./technical/911report  -iname "*chapter*txt"

# output
./technical/911report/chapter-13.4.txt
./technical/911report/chapter-13.5.txt
./technical/911report/chapter-13.1.txt
./technical/911report/chapter-13.2.txt
./technical/911report/chapter-13.3.txt
./technical/911report/chapter-3.txt
./technical/911report/chapter-2.txt
./technical/911report/chapter-1.txt
./technical/911report/chapter-5.txt
./technical/911report/chapter-6.txt
./technical/911report/chapter-7.txt
./technical/911report/chapter-9.txt
./technical/911report/chapter-8.txt
./technical/911report/chapter-12.txt
./technical/911report/chapter-10.txt
./technical/911report/chapter-11.txt
```
**Explanation:** This is searching for a file using its approximate name which is useful in this case if you don't remember the number of chapters there are.

<br>

**Example 2:**
```
# code
find ./technical -iname "*pmed.002018*txt"

# output
./technical/plos/pmed.0020189.txt
./technical/plos/pmed.0020187.txt
./technical/plos/pmed.0020182.txt
./technical/plos/pmed.0020181.txt
./technical/plos/pmed.0020180.txt
```
**Explanation:** This is also searching for a file using its approximate name but in this case it is useful if you don't remember the exact suffix of the numbering system in the `/technical/plos` directory.

<br>
**Source:** https://www.redhat.com/sysadmin/linux-find-command
<br>
