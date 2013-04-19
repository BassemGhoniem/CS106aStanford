/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */


import acm.program.*;
import acm.util.*;




public class Hangman extends ConsoleProgram {
	private HangmanLexicon lexicon = new HangmanLexicon();
	private RandomGenerator rgen = new RandomGenerator();
	private HangmanCanvas canvas;
	
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}

    public void run() {
		println("Welcome to Hangman!");
		String secretWord = lexicon.getWord(rgen.nextInt(0,lexicon.getWordCount()-1));
		String dashedWord = dashing(secretWord);
		canvas.displayWord(dashedWord);
		canvas.reset();

		int guessCount = 8;
		while(!dashedWord.equals(secretWord) && guessCount != 0){
			println("The word now looks like this: "+ dashedWord);
			println("You have " + guessCount + " guesses left.");
			char guessedLetter = readcharachter();
			guessedLetter = Character.toUpperCase(guessedLetter);
			if(guessedCharInSecretTheWord(guessedLetter, secretWord)){
				dashedWord = replaceCharInstances(guessedLetter, secretWord, dashedWord);
				canvas.displayWord(dashedWord);
			}else{
				println("There are no " + guessedLetter + "'s in the word.");
				guessCount--;
				
				canvas.noteIncorrectGuess(guessedLetter);
			}
		}
		if(dashedWord.equals(secretWord)){
			println("That guess is correct.");
			println("You guessed the word: " + secretWord);
			println("You win.");
		}else{
			println("You're completely hung.");
			println("The word was: " + secretWord);
			println("You lose.");
		}
		
	}

	

	private String replaceCharInstances(char guessedLetter, String secretWord, String dashedWord) {
		int pos = secretWord.indexOf(guessedLetter);
		while(pos != -1){
			dashedWord = dashedWord.substring(0,pos) + guessedLetter + dashedWord.substring(pos+1);
			pos = secretWord.indexOf(guessedLetter, pos + 1);
		}
		return dashedWord;
	}



	private boolean guessedCharInSecretTheWord(char guessedLetter , String secretWord) {
		if(secretWord.indexOf(guessedLetter) != -1)
			return true;
		else
			return false;
	}



	private char readcharachter() {
		while(true){
			String character = readLine("Your guess: ");
			println();
			if(character.length() > 1 || character.length() == 0 || !Character.isLetter(character.charAt(0))){
				println("Please Enter your guess single letter!");
			}else{
				return character.charAt(0);
			}
		}
	}

	private String dashing(String secretWord) {
		String dashedWord ="";
		for(int i = 0;i < secretWord.length();i++){
			dashedWord += "-";
		}
		return dashedWord;
	}

}
