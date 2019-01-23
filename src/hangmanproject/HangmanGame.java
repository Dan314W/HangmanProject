public class HangmanGame
{
  // Field variables
  // 1) A String object (word) that will hold the answer word
  // 2) A Stringbuffer object (guessedLetters) that will keep track of which letters of the answer word have been guessed
  // 3) A Stringbuffer object (triedLetters) that will hold the the letters which have been tried
    String word;
    StringBuffer guessedLetters;
    StringBuffer triedLetters;

  // Constructor
  // @param String w - The answer word
  // Assigns parameter to the word field after converting it into upper case letters
  // Initializes the guessedLetters object with the same capacity as the length of the answer word
  // Fills the guessedLetters object with '-' characters
  // Initializes the triedLetters object to size 26
    public HangmanGame(String w) {
        word = w;
        guessedLetters.setLength(w.length());
        guessedLetters.replace(0, w.length(), "-");
        triedLetters.setLength(26);
    }

  // Method getWord
  // @return - the current value of the word field
    public String getWord() {
        return word;
    }

  // Method getGuessed
  // @return - the string equivalent to what is currently stored in the guessedLetters object
    public String getGuessed() {
        return guessedLetters.toString();
    }

  // Method getTried
  // @return - the string equivalent to what is currently stored in the triedLetters object
    public String getTried() {
        return triedLetters.toString();
    }

  // Method tryLetter
  // @return - returns either 0, -1, or 1 based on description in Prob #21
  // @param char letter - this is the letter that is to be tried
  // Don't forget the following:
  //    Convert the letter to upper-case first
  //    Check if the letter has already been tried (return 0)
  //    Check if the letter is not present in answer (return -1)
  //         Include letter in triedLetters list
  //    Check if the letter is present in the answer (return 1)
  //         Include letter in triedLetters list
  //         Fill in all spots of the guessedLetters list with the correct guessed letter
    public int tryLetter(char letter) {
        letter = Character.toUpperCase(letter);
        if (triedLetters.indexOf(Character.toString(letter)) == -1) {
            return 0;
        } else if (word.indexOf(letter) == -1) {
            triedLetters.append(letter);
            return -1;
        } else {
            triedLetters.append(letter);
            guessedLetters.replace(word.indexOf(Character.toString(letter)), word.indexOf(Character.toString(letter))+1, Character.toString(letter));
            return 1;
        }
    }
}
