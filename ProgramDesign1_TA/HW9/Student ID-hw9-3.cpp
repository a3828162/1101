#include <iostream>
using namespace::std;
#include <fstream>
#include <cstring>
#include <string>
#include <vector>
#include <ctime>

// input all words from the file Dictionary.txt, and put them to the vector "dictionary"
void readDictionary( vector< string > &dictionary );

// return true if and only if wordToCheck == dictionary[ i ] for some i
bool legal( string wordToCheck, vector< string > dictionary );

// Find every word w in the vector "dictionary" which satisfies two conditions:
// 1. the length of w is equal to the length of wordToCheck, and
// 2. wordToCheck and w are different on exactly one character.
// Put these words into the vector "result".
void spellCheck1( string wordToCheck, vector< string > dictionary, vector< string > &result );

// Find every word w in the vector "dictionary" which satisfies two conditions:
// 1. the length of w is one greater than the length of wordToCheck, and
// 2. wordToCheck is equal to the substring obtained by deleting a character from w.
// Put these words into the vector "result".
void spellCheck2( string wordToCheck, vector< string > dictionary, vector< string > &result );

// Find every word w in the vector "dictionary" which satisfies two conditions:
// 1. the length of w is one less than the length of wordToCheck, and
// 2. w is equal to the substring obtained by deleting a character from wordToCheck.
// Put these words into the vector "result".
void spellCheck3( string wordToCheck, vector< string > dictionary, vector< string > &result );

// find the words in the vector "dictionary" whose length is equal to the last parameter "length",
// and puts these words into the vector "words"
void findWords( vector< string > &words, vector< string > dictionary, size_t length );

// output all words in the vector "result" to the file result
void saveWords( vector< string > result );

int main()
{
   srand( 1 );
   vector< string > dictionary;

   readDictionary( dictionary );

   string wordToCheck;
   vector< string > result;
   for( int i = 0; i < 10; i++ )
   {
      wordToCheck = dictionary[ rand() + dictionary.size() - 32768 ]; // skip short words
      wordToCheck.erase( rand() % wordToCheck.size(), 1 );

      if( legal( wordToCheck, dictionary ) )
         cout << wordToCheck << " is a legal english word.\n";
      else
      {
         cout << wordToCheck << endl;
         result.push_back( wordToCheck );

         spellCheck1( wordToCheck, dictionary, result );
         spellCheck2( wordToCheck, dictionary, result );
         spellCheck3( wordToCheck, dictionary, result );
         result.push_back( "" );
      }
   }

   saveWords( result );

   system( "pause" );
}

void readDictionary( vector< string > &dictionary )
{
    ifstream data("Dictionary.txt", ios::in);
    string word;
    if (!data) cout << "Wrong open!/n", exit(1);
    while (data >> word) dictionary.push_back(word);
}

bool legal( string wordToCheck, vector< string > dictionary )
{
   for( size_t i = 0; i < dictionary.size(); i++ )
      if( dictionary[ i ] == wordToCheck )
         return true;
   return false;
}

void spellCheck1( string wordToCheck, vector< string > dictionary, vector< string > &result )
{
   size_t length = wordToCheck.size();
   vector< string > words;

   findWords( words, dictionary, length );

   string buffer1;
   string buffer2;
   for( size_t i = 0; i < words.size(); i++ )
      for( size_t j = 0; j < length; j++ )
      {
          buffer1 = wordToCheck, buffer2 = words[i];

          char tmp = buffer2[j];
          buffer1[j] = '?', buffer2[j] = '?';

          if (buffer1 == buffer2) {
              buffer2[j] = tmp, result.push_back(buffer2);
              break;
          }

      }
}

void spellCheck2( string wordToCheck, vector< string > dictionary, vector< string > &result )
{
   size_t length = wordToCheck.size() + 1;
   vector< string > words;

   findWords( words, dictionary, length );

   string buffer;
   for( size_t i = 0; i < words.size(); i++ )
      for( size_t j = 0; j < length; j++ )
      {
          bool isRight = true;

          for (int c_word = 0, c_WTC = 0; c_WTC < length - 1; ++c_word, ++c_WTC)
          {
              if (c_word == j) ++c_word;
              if (words[i][c_word] != wordToCheck[c_WTC]) isRight = false;
          }

          if (isRight)
          {
              result.push_back(words[i]);
              break;
          }

      }
}

void spellCheck3( string wordToCheck, vector< string > dictionary, vector< string > &result )
{
   size_t length = wordToCheck.size() - 1;
   vector< string > words;

   findWords( words, dictionary, length );

   string buffer;
   for( size_t i = 0; i < words.size(); i++ )
      for( size_t j = 0; j < wordToCheck.size(); j++ )
      {
          bool isRight = true;

          for (int c_word = 0, c_WTC = 0; c_word < length; ++c_word, ++c_WTC)
          {
              if (c_WTC == j) ++c_WTC;
              if (words[i][c_word] != wordToCheck[c_WTC]) isRight = false;
          }

          if (isRight)
          {
              result.push_back(words[i]);
              break;
          }

      }
}

void findWords( vector< string > &words, vector< string > dictionary, size_t length )
{
   for( size_t i = 0; i < dictionary.size(); i++ )
      if( dictionary[ i ].size() == length )
         words.push_back( dictionary[ i ] );
}

void saveWords( vector< string > result )
{
    ofstream data("Ans3.txt", ios::out);
    for (int i = 0; i < result.size(); i++) data << result[i] << endl;
}