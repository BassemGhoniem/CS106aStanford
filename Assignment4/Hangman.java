/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;


public class Hangman extends ConsoleProgram {
	private HangmanLexicon lexicon = new HangmanLexicon();
	private RandomGenerator rgen = new RandomGenerator();

    public void run() {
		println("Welcome to Hangman!");
		String secretWord = lexicon.getWord(rgen.nextInt(0,lexicon.getWordCount()));
		String dashedWord = dashing(secretWord);
		int guessCount = 8;
		while(!dashedWord.equals(secretWord) && guessCount != 0){
			println("The word now looks like this: "+ dashedWord);
			println("You have " + guessCount + " guesses left.");
			String guessedChar = readcharachter();
			guessedChar = guessedChar.toUpperCase();
			if(guessedCharInSecretTheWord(guessedChar, secretWord)){
				dashedWord = replaceCharInstances(guessedChar, secretWord, dashedWord);
			}else{
				println("There are no " + guessedChar + "'s in the word.");
				guessCount--;
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

	

	private String replaceCharInstances(String guessedChar, String secretWord, String dashedWord) {
		int pos = secretWord.indexOf(guessedChar);
		while(pos != -1){
			dashedWord = dashedWord.substring(0,pos) + guessedChar + dashedWord.substring(pos+1);
			pos = secretWord.indexOf(guessedChar, pos + 1);
		}
		return dashedWord;
	}



	private boolean guessedCharInSecretTheWord(String guessedChar , String secretWord) {
		if(secretWord.indexOf(guessedChar.charAt(0)) != -1)
			return true;
		else
			return false;
	}



	private String readcharachter() {
		while(true){
			String character = readLine("Your guess: ");
			println();
			if(character.length() > 1 || character.length() == 0 || !Character.isLetter(character.charAt(0))){
				println("Please Enter your guess single letter!");
			}else{
				return character;
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
