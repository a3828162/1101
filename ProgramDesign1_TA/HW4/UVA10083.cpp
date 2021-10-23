#include<iostream>
using std::cin;
using std::cout;
using std::endl;
#include <math.h>

// returns true if and only if the specified huge integer is zero
bool isZero( int hugeInt[], int size );

// returns true if and only if hugeInt1 < hugeInt2
bool less( int hugeInt1[], int hugeInt2[], int size1, int size2 );

// --hugeInt
void decrement( int hugeInt[], int size );

// minuend -= subtrahend provided that minuend > subtrahend
void subtraction( int minuend[], int subtrahend[], int &minuendSize, int subtrahendSize );

// multiplicand *= multiplier
void multiplication( int multiplicand[], int multiplier[], int &multiplicandSize, int multiplierSize );

// quotient = dividend / divisor; remainder = dividend % divisor
void division( int dividend[], int divisor[], int quotient[], int remainder[],
               int dividendSize, int divisorSize, int &quotientSize, int &remainderSize );

// hugeInt /= 10, or equivalently, shifts right by one position
void divideBy10( int hugeInt[], int &size );

const int arraySize = 200;

int main()
{
   int t, a, b;
   while( cin >> t >> a >> b )
   {
      cout << "(" << t << "^" << a << "-1)/(" << t << "^" << b << "-1) ";

      /*if( t == 1 )
         cout << "is not an integer with less than 100 digits.\n";
      else if( a == b )
         cout << "1\n";
      else if( a < b )
         cout << "is not an integer with less than 100 digits.\n";
      else*/
      if (t == 1)
          cout << "is not an integer with less than 100 digits.\n";
      else if (a == b)
          cout << "1\n";
      else if (a < b)
          cout << "is not an integer with less than 100 digits.\n";
      else if (a % b != 0)
          cout << "is not an integer with less than 100 digits.\n";
      else if ((a - b) * static_cast<int>(log10(t)) > 95)
          cout << "is not an integer with less than 100 digits.\n";
      else
      {
         int dividend[ arraySize ] = {};
         int divisor[ arraySize ] = {};
         int quotient[ arraySize ] = {};
         int remainder[ arraySize ] = {};

         int dividendSize = 1;
         int divisorSize = 1;
         int quotientSize = 1;
         int remainderSize = 1;

         // put all digits of t into base
         int base[ arraySize ] = {};
         int baseSize = 0;
         for( int i = t; i > 0; i /= 10 )
            base[ baseSize++ ] = i % 10;

         // dividend = pow( t, a )
         dividend[ 0 ] = 1;
         for( int i = 0; i < a; ++i )
         {
            multiplication( dividend, base, dividendSize, baseSize );
            if( dividendSize > arraySize )
               break;
         }

         if( dividendSize > arraySize )
            cout << "is not an integer with less than 100 digits.\n";
         else
         {
            // divisor = pow( t, b )
            divisor[ 0 ] = 1;
            for( int i = 0; i < b; ++i )
               multiplication( divisor, base, divisorSize, baseSize );

            decrement( dividend, dividendSize ); // --dividend
            decrement( divisor, divisorSize );   // --divisor

            division( dividend, divisor, quotient, remainder,
                      dividendSize, divisorSize, quotientSize, remainderSize );

            // quotient is an integer with less than 100 digits
            if( quotientSize < 100 && isZero( remainder, remainderSize ) )
               for( int i = quotientSize - 1; i >= 0; i-- )
                  cout << quotient[ i ];
            else
               cout << "is not an integer with less than 100 digits.";
            cout << endl;
         }
      }
   }

   /*
   if( t == 1 )
         cout << "is not an integer with less than 100 digits.\n";
      else if( a == b )
         cout << "1\n";
      else if( a < b )
         cout << "is not an integer with less than 100 digits.\n";
      else if( a % b != 0 )
         cout << "is not an integer with less than 100 digits.\n";
      else if( ( a - b ) * static_cast< int >( log10( t ) ) > 95 )
         cout << "is not an integer with less than 100 digits.\n";
      else
   
   */
}

// returns true if and only if the specified huge integer is zero
bool isZero( int hugeInt[], int size )
{
   for( int i = 0; i < size; i++ )
      if( hugeInt[ i ] != 0 )
         return false;
   return true;
}

// returns true if and only if hugeInt1 < hugeInt2
bool less( int hugeInt1[], int hugeInt2[], int size1, int size2 )
{
    if (size1 > size2) return false;
    else if (size1 < size2)return true;
    else for (int i = size1 - 1; i >= 0; --i) if (hugeInt1[i] < hugeInt2[i]) return true; else if (hugeInt1[i] > hugeInt2[i]) return false;
    return false;

    /*
        if (size1 != size2) return size1 < size2;
        for (int i = size1 - 1; i >= 0; --i) if (hugeInt1[i] != hugeInt2[i]) return hugeInt1[i] < hugeInt2[i];
        return false;
    */
}

// --hugeInt
void decrement( int hugeInt[], int size )
{
    int i[200] = {}, s = 1;
    i[0] = 1;
    subtraction(hugeInt, i, size, s);

}

// minuend -= subtrahend provided that minuend > subtrahend
void subtraction( int minuend[], int subtrahend[], int &minuendSize, int subtrahendSize )
{
    int difference[arraySize] = {}, differenceSize = minuendSize;

    for (int i = 0; i < minuendSize; ++i) if ((difference[i] += minuend[i] - subtrahend[i]) < 0) difference[i] += 10, difference[i + 1]--;
    for (int i = differenceSize - 1; i > 0; --i) if (!difference[i]) differenceSize--; else break;

    minuendSize = differenceSize;
    for (int i = 0; i < minuendSize; ++i) minuend[i] = difference[i];

}

// multiplicand *= multiplier
void multiplication( int multiplicand[], int multiplier[], int &multiplicandSize, int multiplierSize )
{
    int product[arraySize] = {}, productSize = multiplicandSize + multiplierSize;

    for (int i = 0; i < multiplicandSize; ++i) for (int j = 0; j < multiplierSize; ++j) if ((product[i + j] += multiplicand[i] * multiplier[j]) > 9)product[i + j + 1] += product[i + j] / 10, product[i + j] %= 10;
    for (int i = productSize - 1; i > 0 && !product[i]; --i, --productSize);

    multiplicandSize = productSize;
    for (int i = 0; i < multiplicandSize; ++i) multiplicand[i] = product[i];

}

// quotient = dividend / divisor; remainder = dividend % divisor
void division( int dividend[], int divisor[], int quotient[], int remainder[],
               int dividendSize, int divisorSize, int &quotientSize, int &remainderSize )
{
    remainderSize = dividendSize;
    for (int i = 0; i < remainderSize; ++i) remainder[i] = dividend[i];
    if (less(dividend, divisor, dividendSize, divisorSize))
    {
        quotientSize = 1;
        for (int i = 0; i < quotientSize; ++i) quotient[i] = 0;
    }
    else
    {
        int n = dividendSize - divisorSize, buffer[400] = {};
        int bufferSize = divisorSize + n;
        //for (int i = 0; i < bufferSize; ++i) buffer[i + n] = divisor[i];
        for (int i = 0; i < divisorSize; ++i) buffer[i + n] = divisor[i];
        quotientSize = dividendSize - divisorSize;
        if (less(dividend, buffer, dividendSize, bufferSize)) divideBy10(buffer, bufferSize);
        else ++quotientSize;

        for (int i = 0; i < quotientSize; ++i) quotient[i] = 0;

        for (int i = quotientSize - 1; i >= 0; --i)
        {
            while (!less(remainder, buffer, remainderSize, bufferSize))
            {
                subtraction(remainder, buffer, remainderSize, bufferSize);
                quotient[i]++;
                if (isZero(remainder, remainderSize)) return;
            }
            divideBy10(buffer, bufferSize);
        }
    }

}

// hugeInt /= 10, or equivalently, shifts right by one position
void divideBy10( int hugeInt[], int &size )
{
   if( size == 1 )
      hugeInt[ 0 ] = 0;
   else
   {
      for( int i = 1; i < size; i++ )
         hugeInt[ i - 1 ] = hugeInt[ i ];

      size--;
      hugeInt[ size ] = 0;
   }
}