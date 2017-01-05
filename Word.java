
public class Word {
	private String letters;//Define String letters of a word
	private int frequency = 0;//Declare variable for the word's frequency
	
	public Word(String input){
		letters = input;
		frequency = 1;
	}
	public void occurance(){//Function to increment frequency of a word
		frequency++;
	}
	public String getLetters(){
		return letters;
	}
	public int getFrequency(){
		return frequency;
	}
}
