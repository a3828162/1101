// Huge integer division
#include<iostream>
using std::cin;
using std::cout;
using std::endl;

#include<memory.h>

#include<cmath>
using std::log10;

// returns true if and only if the specified huge integer is zero
bool isZero( int hugeInt[], int size );

// returns true if and only if hugeInt1 < hugeInt2
bool less( int hugeInt1[], int hugeInt2[], int size1, int size2 );

// --hugeInt
void decrement( int hugeInt[], int &size );

// multiplicand *= multiplier
void multiAssign( int multiplicand[], int multiplier[], int &multiplicandSize, int multiplierSize );

// quotient = dividend / divisor; remainder = dividend % divisor
void division( int dividend[], int divisor[], int quotient[], int remainder[],
   int dividendSize, int divisorSize, int &quotientSize, int &remainderSize );

// product = multiplicand * multiplier * ( 10 ^ multiplierPos )
void multiplication( int multiplicand[], int multiplier, int product[],
   int multiplicandSize, int multiplierPos, int &productSize );

// minuend -= subtrahend
void subtraAssign( int minuend[], int subtrahend[], int &minuendSize, int subtrahendSize );

const int arraySize = 200;

int main()
{
   int t, a, b;
   while( cin >> t >> a >> b )
   {
      cout << "(" << t << "^" << a << "-1)/(" << t << "^" << b << "-1) ";

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
            multiAssign( dividend, base, dividendSize, baseSize );
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
               multiAssign( divisor, base, divisorSize, baseSize );

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
}

// --hugeInt
void decrement( int hugeInt[], int &size )
{
    int i[200] = {}, s = 1;
    i[0] = 1;
    subtraAssign(hugeInt, i, size, s);
}

// multiplicand *= multiplier
void multiAssign( int multiplicand[], int multiplier[], int &multiplicandSize, int multiplierSize )
{
    int product[arraySize] = {}, productSize = multiplicandSize + multiplierSize;

    for (int i = 0; i < multiplicandSize; ++i) for (int j = 0; j < multiplierSize; ++j) if ((product[i + j] += multiplicand[i] * multiplier[j]) > 9)product[i + j + 1] += product[i + j] / 10, product[i + j] %= 10;
    while (productSize > 0 && !product[productSize - 1]) --productSize;

    multiplicandSize = productSize;
    for (int i = 0; i < multiplicandSize; ++i) multiplicand[i] = product[i];
}

void division( int dividend[], int divisor[], int quotient[], int remainder[],
               int dividendSize, int divisorSize, int &quotientSize, int &remainderSize )
{
    if (isZero(dividend,dividendSize))
    {
        quotient[0] = 0, quotientSize = 1;
        remainder[0] = 0, remainderSize = 1;
        return;
    }

    for (int i = 0; i < (remainderSize = dividendSize); ++i) remainder[i] = dividend[i];

    if (less(dividend, divisor, dividendSize, divisorSize))
    {
        quotient[0] = 0, quotientSize = 1;
        return;
    }

    int n = dividendSize - divisorSize, buffer[400] = {}, bufferSize = divisorSize + n;
    for (int i = 0; i < divisorSize; ++i) buffer[i + n] = divisor[i];

    quotientSize = n;
    if (!less(dividend, buffer, dividendSize, bufferSize)) quotientSize++;

    for (int i = 0; i < quotientSize; ++i) quotient[i] = 0;

    int a = divisor[divisorSize - 1], j = dividendSize - 1;

    for (int i = dividendSize - divisorSize; i >= 0; i--, j--)
    {
        int b = 10 * remainder[j + 1] + remainder[j];
        if (a <= b)
        {
            quotient[i] = b / a;
            multiplication(divisor, quotient[i], buffer, divisorSize, i, bufferSize);
            while (less(remainder, buffer, remainderSize, bufferSize))
            {
                quotient[i]--;
                multiplication(divisor, quotient[i], buffer, divisorSize, i, bufferSize);
            }
            subtraAssign(remainder, buffer, remainderSize, bufferSize);
        }
    }
}

void multiplication( int multiplicand[], int multiplier, int product[],
   int multiplicandSize, int multiplierPos, int &productSize )
{
    int bufferSize = 0, buffer[arraySize] = {};
    while (multiplier > 0) buffer[bufferSize++] = multiplier % 10, multiplier /= 10;

    productSize = bufferSize + multiplierPos;
    for (int i = 0; i < bufferSize; ++i) product[i + multiplierPos] = buffer[i];

    multiAssign(product, multiplicand, productSize, multiplicandSize);
}

void subtraAssign( int minuend[], int subtrahend[], int &minuendSize, int subtrahendSize )
{
    for (int i = 0; i < minuendSize; ++i)
    {
        if (i < subtrahendSize) minuend[i] -= subtrahend[i];
        if (minuend[i] < 0) minuend[i] += 10, minuend[i + 1] -= 1;
    }
    while (minuendSize > 0 && !minuend[minuendSize - 1]) --minuendSize;
}