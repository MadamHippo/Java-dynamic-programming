/*
Capturing Rainwater:
Learn a Java implementation of the capturing rainwater interview question.

A common technical interview question involving arrays is the “capturing rainwater” problem, sometimes referred to as the “trapping rainwater” problem.

The Problem
Imagine a very heavy rainstorm over a highway with many potholes. The rainwater will collect in the empty holes in the road, creating puddles. Each puddle can only get as high as the road directly surrounding it. Any excess water will just flow away from the pothole.

The capturing rainwater problem asks you to imagine that rainwater has fallen over a histogram, a chart consisting of a series of bars. It asks you to calculate how much rainwater would get trapped in the spaces between these bars. Consider the following histogram:

[https://content.codecademy.com/programs/cs-path/TIP-Lists/histogram%20v1.svg]

This histogram can be represented in Java as an array filled with the values [4, 2, 1, 3, 0, 1, 2]. Here’s how the previous histogram would look filled with water:

[https://content.codecademy.com/programs/cs-path/TIP-Lists/histogram%20v2.svg]

Like with the pothole road, the amount of water that gets captured at any given space cannot be higher than the bounds around it. To solve the problem, we need to write a function that will take in an array of integers and calculate the total water captured. Our function should return 6 for the histogram above. There are multiple ways to solve this problem, but we are going to focus on

1) a naive implementation and 2) an optimized implementation.

The Concept
The foundation of this problem is that the amount of rainwater at any given index is the difference between 1) the shorter bar of the highest bar to the left of the index vs. the highest bar to the right and 2) the height of the bar at the given index.

waterAtIndex = Math.min(highestLeftBound, highestRightBound) - heightOfIndex;
Take a look at the histogram again. The amount of water at index 4 is 2 because its:

Highest left bound = 3 (value at index 3).
Highest right bound = 2 (value at index 6).
The lower of these two values is 2, and when we subtract the height at index 4 from this, we get our answer of 2 for waterAtIndex.
*/


/*
The Naive Solution:
The naive solution to the problem is to:

Traverse every element in the array
Find the highest left bound for the current index
Find the highest right bound for the current index
Take the lower of these two values
Subtract the height of the current index from that lower value
Add the difference to the total amount of water captured
In Java, this implementation looks like this:
*/

public int naiveSolution(int[]heights) {
    int totalWater = 0;
    for (int i = 0; i < heights.length - 1; i++) {
        int leftBound = 0;
        int rightBound = 0;
        // Here we only want to look at elements to the LEFT of index i, which are the elements at the lower indices
        for (int j = 0; j <= i; j++) {
            leftBound = Math.max(leftBound, heights[j]);
        }
        // Now here we only want to look at elements to the RIGHT of index i, which are the elements at the higher indices
        for (int j = i; j < heights.length; j++) {
            rightBound = Math.max(rightBound, heights[j]);
        }
        // Add the amount of water at index i to totalWater
        totalWater += Math.min(leftBound, rightBound) - heights[i];
    }
    return totalWater;
}

//While this is a functional solution, it requires nested for loops, which means it has a big O runtime of O(N^2). Let’s now look at a solution with a more efficient runtime!



/*
The Optimized Solution:

The previous solution had a quadratic runtime, but it’s possible to solve this problem in O(N) time by using the two-pointers approach. One pointer starts at the beginning of the array and one starts at the end, and they move towards each other progressively. This approach is common for problems that require working with arrays as it allows you to go through one in a single loop without the need to copy arrays.

Here are the important variables for this solution:

totalWater = 0
leftPointer = 0
rightPointer = heights.length - 1
leftBound = 0
rightBound = 0
leftPointer and rightPointer will start at the beginning and end of the array, respectively, and move towards each other until they meet. The algorithm is as follows:

while leftPointer < rightPointer:
    if the element at leftPointer <= the element at rightPointer:
        -set leftBound to max value between leftPointer element and leftBound
        -add difference between leftBound & the element at leftPointer to totalWater
        -move leftPointer forward by one
    else:
        -set rightBound to max value between rightPointer element and rightBound
        -add difference between rightBound & the element at rightPointer to totalWater
        -move rightPointer back by one 

return totalWater
*/

public class RainWater {
    
    public int efficientSolution(int[]heights) {
        int totalWater = 0;
        int leftPointer = 0;
        int rightPointer = heights.length - 1;
        int leftBound = 0;
        int rightBound = 0;
        // Fill in the rest of this method with your solution
        while (leftPointer < rightPointer) {
            if (heights[leftPointer] <= heights[rightPointer]) {
                leftBound = Math.max(heights[leftPointer],leftBound);
                totalWater += leftBound - heights[leftPointer];
                leftPointer++;
            } else {
                rightBound = Math.max(heights[rightPointer],rightBound);
                totalWater += rightBound - heights[rightPointer];
                rightPointer--;
            }
        }
        return totalWater;
    }

    public static void main(String[]args) {
        // heights: array representation of the histogram
        int [] heights = new int[]{4, 2, 1, 3, 0, 1, 2};
        RainWater rainWater = new RainWater();
        System.out.println("Amount of water captured: " + rainWater.efficientSolution(heights));
    }
}
