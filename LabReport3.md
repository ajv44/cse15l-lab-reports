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
