# codingquiz7

## Running Time Alg Time and Space Complexity:
The time complexity of this algorithm would be O(n^2). This is because first we intitialize an integer, which is constant. Then we loop through our input array, which is linear. Next we have a while loop within our for loop, which is O(n*n). Within our while loop, we are doing constant work. Thus, the overal time complexity is O(n^2).
The space complexity of this algorithm is constant. This is because we are not using more space than our input array. Thus, the space complexity is O(c).

## Ice Cream Parlor
I remembered how to solve this problem with dynamic porgramming like we talked about in class. However, I had trouble changing it to the variation. First I tried to set the algorithm to only return the answer if the two flavors sum to the money had. Next, I was trying to create multiple result lists that get the multiple answers within the DP table, if it is not null. However, I started to confuse myself with 3D arrays and how numbers enter the table. I then wanted to return the size of those lists to get the total possible combinations. 
The recursive formulation for this was if m = 0 and the number of ice cream was 2, then those indices are a possible combination. If the number in the input array was above m, you would not subtract that price from the money. Otherwise, you would  include the number and subtract it from the money and also exclude it and see which one returns 0. This would continue until it goes through all possible combinations. 
While I could not figure this out with dynamic programming, I believe I completed it iteratively. 
