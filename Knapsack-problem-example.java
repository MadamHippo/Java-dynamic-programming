/* 
SET UP:

Imagine that you’re a thief breaking into a house. There are so many valuables to steal - diamonds, gold, jewelry, and more! But remember, you’re just one person who can only carry so much. Each item has a weight and value, and your goal is to maximize the total value of items while remaining within the weight limit of your knapsack. This is called the knapsack problem and is commonly used in programming interviews. We will solve this problem in two ways: recursively, and using dynamic programming.
*/

/* 
The Problem:

The first step to solving this problem is to understand the parameters involved. You will be given:

~the total amount of weight you can carry (weightCap)
~the weights of all of the items in an array (weights)
~the values of all of the items in an array (values)

Your function should return the maximum value that you will be able to carry.
*/


/*
An Example
Let’s say that you have a knapsack that can only carry 5 pounds, and the house you’re robbing has three items that you want to steal:

A ring that weighs 1 pound with a value of $250
Earrings that weigh 3 pounds with a value of $300
A necklace that weighs 5 pounds with a value of $500
This information can be summarized as follows:

weightCap = 5 
weights = [1, 3, 5]
values = [250, 300, 500]

You have four possible ways to fill your knapsack:
~Take only the ring, giving you $250
~Take only the earrings, giving you $300
~Take only the necklace, giving you $500
~Take the ring and the earrings, giving you $550

Since the ring and the earrings have a combined weight of 4 pounds, taking both gives you the maximum value while staying within your weight capacity. Now that you’re familiar with the problem, let’s take a look at two different approaches to solving it!
*/

/* 
RECURSIVE Brute Force Solution:

The brute force solution to this problem is to look at every subset of the items that has a total weight less than weightCap. Then you simply take the maximum of those subsets, giving you the optimized subset with the highest value possible.

You will need an additional parameter, i, that tells us where we are in the list of items. With each step, we will break the problem down into subproblems, and compare them to find the maximum value. There are three possibilities for every call of the function:

1. weightCap or i are zero, meaning the knapsack can hold no weight, or there are no more items to look at. In either case, we return 0.
2. The weight of the item we’re looking at exceeds weightCap, in which case we just move on, calling the function on the next item.
3. If neither of the above are true, that means we have to consider whether or not the item we are at (i) should be included in the optimal solution.
*/

public class Knapsack {
  static int knapSack (int weightCap, int weights[], int values[], int i)
  {        
    if (i == 0 || weightCap == 0) {
      return 0;
    }
    else if (weights[i - 1] > weightCap) {
      return knapSack(weightCap, weights, values, i - 1);
    } else {
      return Math.max(values[i - 1] + knapSack(weightCap - weights[i - 1], weights, values, i - 1), knapSack(weightCap, weights, values, i - 1));
  } 
}

// While this recursive solution works, it has a big O runtime of O(2^n). In the worst case, each step would require us to evaluate two subproblems, sometimes repeatedly, as there’s overlap between subproblems. We can drastically improve on this runtime by using dynamic programming.

/*
DYNAMIC Programming Approach:
The knapsack problem is suited for dynamic programming because memoization will allow us to store information instead of making duplicate calls. We will store this information in a two-dimensional array that has a row for every item and weightCap + 1 number of columns where each element in the 2D array (matrix) represents a subproblem. The element at the bottom right will be the optimal solution.

But what exactly do the rows and columns represent? The rows represent the items we have seen. So if we are at row 4, then we have only seen the first 4 items, meaning the others aren’t being considered yet. The columns represent how much weight the knapsack can hold. If we are at column 7, then we are looking at a subset of the larger problem where our knapsack has a weight capacity of 7. The number stored inside matrix is the maximum value we can take given the weight capacity and number of items we have seen for that subproblem. By the time we get to the bottom right space in matrix, we have considered every possible subproblem and taken the maximum possible value.

There are some elements in the matrix that will be easy to fill. Every element in the zeroeth row represents a subproblem with 0 items to consider, so there is no value. Likewise, every element in the zeroeth column represents a subproblem where our knapsack has a capacity of 0, giving us no value to take. Because of this, we start by filling the zeroeth row and column with 0.

Pseudocode in comments.
*/

public class Knapsack {

    static int knapSack(int weightCap, int weights[], int values[], int i)
    {
        int index, weight;
        
        // matrix = array with length equal to number of items:
        int matrix[][] = new int[i + 1][weightCap + 1];
        
        // for every number of items you can carry (index): 
        for (index = 0; index <= i; index++) {
        
            //   fill matrix[index] with an array of length weightCap + 1:
            //   for every weight < weightCap (weight):
            for (weight = 0; weight <= weightCap; weight++) {
            
                // if index or weight == 0:
                if (index == 0 || weight == 0)
                
                // set element at [index][weight] to 0
                    matrix[index][weight] = 0;
                    
                // else if the weight of element at index - 1 <= weight:
                else if (weights[index - 1] <= weight)
               
                // find possible values of including and excluding the item:
                // set element at [index][weight] to max of those values (using Math.max)

                    matrix[index][weight] = Math.max(
                            values[index - 1] + matrix[index - 1][weight - weights[index - 1]],
                            matrix[index - 1][weight]);
                
                // else....set element at [index][weight] to element one above
                else {
                    matrix[index][weight] = matrix[index - 1][weight];
                }
            }
        }
        // return element at bottom right of matrix
        return matrix[i][weightCap];
    }


    public static void main(String[] args) {
        int values[] = new int[] { 70, 20, 39, 37, 7, 5, 10 };
        int weights[] = new int[] { 31, 10, 20, 19, 4, 3, 6 };
        int weightCap = 50;
        int i = values.length;
        Knapsack myNapsack = new Knapsack();
        System.out.println(myNapsack.knapSack(weightCap, weights, values, i));
    }
}

// Congratulations! You have implemented the dynamic programming approach to the knapsack problem. This version has a big O runtime of O(index * weight) compared to the recursive implementation’s runtime of O(2^n). While this optimized runtime might seem worse using small cases, it is much more efficient as the parameters grow.
