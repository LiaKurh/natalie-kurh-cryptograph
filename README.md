#  Cryptograph 

## 📜 Project description:
A simple program for working with the Caesar cipher , adapted for multilingualism (EN/UA). Three main functions are 
implemented: 
-	Encryption: shift the letters of the text by a given key.
-	Decryption: reverse shift by a known key. 
-	Brute-force attack: automatically searching through all possible shifting options to find the original text.

### What is implemented
- Bilingual support: The module automatically recognizes and works with letters of the EN/UA alphabets.
- Encryption/Decryption: Classic shifting of letters by key.
- Brute-force method: The method does not simply go through all the options, but automatically determines the most 
  likely original text. 
	* Dictionary check: A mechanism has been implemented to compare the decrypted words with the dictionary of the most 
                      commonly used words for both languages (EN/UA). 
    * Scoring System: Each decryption option receives a score based on the number of matches found in the dictionary. 
                    The program returns the result with the highest rating.
  
The code ignores numbers and special characters, leaving them unchanged.


### 🚀 Getting Started
1. To run the application you should install:
* Java Development Kit (JDK) 21 or later.
* Apache Maven 3.9.6 or later.

2. After installing the required software, fork and clone the project from the GitHub repository.
3. Finally, run this project. You can run this project using Run Configurations or CLI. 
   Program required 2 or 3 arguments:
   - command (ENCRYPT, DECRYPT), absolute file path and key;
   - command (BRUTE_FORCE), absolute file path.
   