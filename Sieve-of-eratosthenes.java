/*
Sieve of Eratosthenes
In this interactive article, implement the classic Sieve of Eratothenes algorithm for finding prime numbers.

The Sieve of Eratosthenes is one of the oldest-known algorithms, and it’s still helpful for deriving prime numbers! The algorithm is attributed to Eratosthenes, a Greek mathemetician born in the third century BCE.

The sieve provides a set of steps for finding all prime numbers up to a given limit. In this article, we’ll cover implementing the Sieve of Eratosthenes in Java. As a reminder, a prime number is a positive number with no divisors but 1 and itself. 2, 3, 11, and 443 are all prime numbers.

The sieve works by first assuming that all numbers from

{2...n}

are prime, and then successively marking them as NOT prime.

The algorithm works as follows:

Create a list of all integers from 2 to n.
Start with the smallest number in the list (2, the smallest prime number).
Mark all multiples of that number up to n as not prime.
Move to the next non-marked number and repeat this process until the entire list has been covered.
When the steps are complete, all remaining non-marked numbers are prime.

Implementation Steps in Java
There are many possible ways of implementing this algorithm in Java. We’ll outline a basic approach here and then walk through it step-by-step.

Create an array of all integers from 2 to n
Set all elements of the array to true
Starting with 2, iterate through the array. If the current element is true, it is still considered prime. Since we know that all multiples of that number are NOT prime, iterate through all multiples of that number up to n and set them equal to false
Change the current element to the next non-false index.
Return the corresponding number value for any element still marked as prime (value of true).
If you’d like to try your hand at implementing the algorithm using these steps, jump to the complete algorithm code block below. Otherwise, we’ll do walk through a step-by-step breakdown below.

Step One: Create the Array
First, we’ll create the array. In this case, we’ll create an array to represent all numbers up to the input limit, but we’ll use the array index to represent the number, and its value (true/false) to represent whether it is prime or not. The original algorithm said to use an array of 2, ..., n, but since we’re using indices to represent the actual number, we’ll start the array from 0 and essentially ignore the values of array[0] and array[1].

For example, after running our sieve, an array representing the primes up to 7 would look like this, with elements at [2], [3], [5], and [7] marked true:

[false, false, true, true, false, true, false, true]

*/

// Fill in the blanks of the sieveOfEratosthenes() method to create an array with the correct size. Mark the numbers 0 and 1 as non-prime.

public class SieveOfEratosthenes {
    void sieveOfEratosthenes(int limit) {
        boolean output[] = new boolean[limit + 1];
        for (int x = 0; x <= limit; x++) {
            output[x] = true;
        }
        output[0] = false;
        output[1] = false;
    }
}

/*
Step Two: Iterate
Now we’ll implement the bulk of the algorithm to iterate and mark numbers as non-prime. We’ll do this in two steps:

Create an outer loop to iterate from 2 to the limit.
Inside, check if the current number is still marked prime. If it is, we’ll mark all its multiples using another loop.
*/

// Implement the non-prime marking phase of the Sieve of Eratosthenes. For each number from 2 to the limit, check if it has not been already marked as non-prime. If it has not, mark all its multiples as non-prime.

public class SieveOfEratosthenes {
    void sieveOfEratosthenes(int limit) {
        boolean output[] = new boolean[limit + 1];
        for (int x = 0; x <= limit; x++) {
            output[x] = true;
        }
        output[0] = false;
        output[1] = false;
        for (int i = 2; i <= limit; i++) {
            if (output[i] == true) {
                for (int j = i * 2; j <= limit; j = j + i) {
                    output[j] = false;
                }
            }
        }
    }
}


/*
Step Three: Return Values
Now it’s time to pare down our array and only return the actual primes. There are many ways to do this, so we’re going to let you figure out an approach to this part of the algorithm isolated from the rest of the code.

HINT: Create a result List that we will fill with Integer values and set equal to a new ArrayList.
Loop through the input array, arr, and check if the value of arr at the current index is equal to true.
If it is, call the add() method on result and pass in arr[i].
We have already provided you with the code to print the result list.
*/

/*
Complete the findTrueIndices() method. It should have a single parameter (an array), and return a new array of all indices in the input that are true.

For instance,

[false, false, true, true, false, true, false, true]
should return [2, 3, 5, 7].

You can assume that all array elements will be either true or false.
*/

import java.util.*;

class FindTrueIndices {

   void findTrueIndices(boolean[] arr) {
    
    // Create a result List that we will fill with Integer values and set equal to a new ArrayList
    ArrayList result = new ArrayList();

    // Loop through the input array, arr, and check if the value of arr at the current index is equal to true.
    for (int i = 0; i < output.length; i++){
      if (output[i]==true){
        // If it is, call the add() method on result and pass in arr[i].
        result.add(i);

      }
    }

     
    // use for-loop to add index of `arr` elements to `result` that are `true` by calling `add()` method
    
    // Print `result`
    System.out.println(Arrays.toString(result.toArray()));
  }

  }
  
  public static void main(String[] args) {
    FindTrueIndices myFindTrueIndices = new FindTrueIndices();
    boolean[] testValues = {false, false, true, true, false, true, false, true};
    myFindTrueIndices.findTrueIndices(testValues);
  }
}

/* Runtime: O(n log (logn))
There are two operations to take into account: the creation of the array and the incrementing and multiple-marking loops.

Creation happens in O(n) time, since it creates an element for each number from 2 to n.

Multiple marking happens in O(n log log n) time. The reasons for this come down to some complex math, but briefly:
It begins with n / 2 because initially all multiples of 2 are marked as non-prime (this will happen 50 times with a limit of 100, as each even number is marked). This process continues up until the square root of n. Through some fancy mathematical proofs, this works out to an overall time complexity of
*/

// Conclusion

// The Sieve of Eratosthenes is a millennia-old algorithm, so there are some more improvements and more advanced methods for finding primes invented since its discovery, but it’s still a great way to generate lists of prime numbers. Included below is our sieve code including optimizations:

import java.util.*;
 
public class SieveOfEratosthenes {
    void sieveOfEratosthenes(int limit) {
        boolean[] output = new boolean[limit + 1];
        for (int x = 0; x <= limit; x++) {
            output[x] = true;
        }
        output[0] = false;
        output[1] = false;
 
        for (int i = 2; i <= Math.pow(limit, 0.5); i++) {
            if (output[i] == true) {
                for (int j = (int) Math.pow(i, 2); j <= limit; j = j + i) {
                    output[j] = false;
                }
            }
        }
 
        List<Integer> result = new ArrayList<Integer>();
 
        for (int i = 0; i < output.length; i++) {
            if (output[i] == true) {
                result.add(i);
            }
        }
 
        System.out.println(Arrays.toString(result.toArray()));
    }
 
public static void main(String[] args) {
        int n = 7;
        SieveOfEratosthenes g = new SieveOfEratosthenes();
        g.sieveOfEratosthenes(n);
    }
 
}
