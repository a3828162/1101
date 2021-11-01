#include <iostream>
using namespace std;
#include<algorithm>

// returns the maximum cycle-length over all integers
// between and including first and last
int maxCycleLength( int first, int last );

int cycleLength( int n ); // returns the cycle-length of n

int main()
{
   int i, j;
   while( cin >> i >> j )
   {
      cout << i << ' ' << j << ' ';

      if( i > j )
      {
         int buf = i;
         i = j;
         j = buf;
      }
      
      cout << maxCycleLength( i, j ) << endl;
   }
}

int maxCycleLength( int first, int last )
{
    return (first != last ? std::max(cycleLength(first), maxCycleLength(first+1, last)) : cycleLength(last));
}

int cycleLength( int n )
{
    return (n != 1 ? 1 + (n % 2 == 1 ? cycleLength(3 * n + 1) : cycleLength(n/2)) : 1);
}