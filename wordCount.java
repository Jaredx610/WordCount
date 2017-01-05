import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//Jared McDonald CSC241115
//Date Started:11/13/2014
//Date Completed:11/20/2014
//Purpose:The purpose of this program is to read a file of words, put the words into an array, sort the array alphabetically or by frequency, and print the sorted array. 
/*Variables Used:
 * sort - Char read from the keyboard used to determine the method of sorting
 * letters - String that is manipulated to form words read from the file to use
 * words - Array of Word objects that holds words previously read from the input
 * count - An integer representing the actual number of elements in the array, instead of 5000.
 * keyboard - A Scanner that reads from the input of the keyboard to determine the method of sorting to use
 * input - Scanner that reads from the input of the file
 * line - A String to hold the lines read from input
 * index - A integer used to keep track of the number of characters read from line in order to create a word when the last one is read
 * ch - Character in the line String
 * w - Word object created from letters that is passed to insertWord function
 * i - Integer used in for loops
 * newWords - Array of Word objects that has no null elements like words[5000]
 * next - Element in the array passed to FsortArray
 * location - An integer used in the insertion sort FsortArray function
 * first - An integer to represent the first index in an array
 * last - An integer to represent the last index in an array
 * pivot - First Character of the pivot in the FsortArray function
 * keyb - Scanner used to read from the keyboard in the printArray  function
 * tempWord - A Word object used to hold a temporary Word
 * lastfront - Integer to represent the last element of part of the array that is less than the pivot
 */
//Files Needed: p7.dat or p7a.dat
//ADTs Used: Word.java
/*Sample Input:
	I enjoy eating pie.
	Pie makes me happy.
	Therefore eating pie makes me happy.
	>a
Sample Output:
	WORD:		FREQUENCY:
	eating			2
	enjoy			1
	happy			2
	I				1
	makes			2
	me				2
	pie				3
	Therefore		1
 */
public class prog7 {
	char sort;//Define global variable sort to know what sort we're using
	String letters ="";//Define string that we'll add onto later
	Word[] words = new Word[5000];//Define array of word Objects to add to
	int count;//Define count to keep track of the number of words in the file
	public static void main(String[] args) throws FileNotFoundException {
		new prog7().nonstatic();//Call the nonstatic method
	}

	public void nonstatic() throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);//declare a Scanner object to read from the keyboard
		System.out.println("Do you want to sort alphabetically (Press A) or by frequency(Press F)?");
		sort = Character.toLowerCase(keyboard.next().charAt(0));//Get the first character of what the user puts into the Scanner
		while(sort != 'a'&& sort != 'f'){//While the sort isnt a or f
			System.out.println("Invalid sorting option. Please input A for alphabetical or F for frequency.");
			sort = Character.toLowerCase(keyboard.next().charAt(0));//Ask for it until a or f is given
		}
			if(sort == 'a'){
				System.out.println("Sorting alphabetically.");
			}
			else{
				System.out.println("Sorting by frequency.");
			}
			Scanner input = new Scanner(new FileReader("../p7.dat"));//Where the file is located
			String line = input.nextLine();//Declare a string that holds the nextLine of input
		
			while(input.hasNextLine() && line!= null){//While input has a line to be read and line isnt null
				int index = 0;//No chars have been read in yet
				for(char ch:line.toCharArray()){//for every char in the line String cast to a CharArray
					if((!Character.isAlphabetic(ch)||(index == line.length()-1)) && ch != '\''){//If ch isnt a letter or index isnt at the last char and ch isnt an apostrophe 
						if(index == line.length()-1 && Character.isAlphabetic(ch)){//If ch is the last char that is a letter
							letters += ch;//Add it to letters
						}//End if
						Word w = new Word(letters.trim());//Make a new Word from the trimmed letters String
						letters ="";//Clear letters for the next word
						if(w.getLetters().trim().length() >= 1){//If trimmed w is one letter or more 
							insertWord(w);//Call the insertWord function to put it into the array
							count++;//Increase count
						}
					}
					else{
						letters += ch;//Add ch to letters
					}
					index++;//Increase index
				}
				line = input.nextLine();//Get the next line
			}
			Word[] newWords= resize();//Get an accurately sized array 
			/*if(sort == 'f'){
				FsortArray(newWords,newWords.length);
			}
			else{
				AsortArray(newWords,0,newWords.length-1);
			}*/
			printArray(newWords);//Call the printArray function to print the array
		input.close();//Close input Scanner
		keyboard.close();//Close keyboard Scanner
	}
	public void insertWord(Word w){
		for(int i = 0;i < 5000;i++){//for each element in the array
			if(words[i] != null && words[i].getLetters().equalsIgnoreCase((w.getLetters()))){//Check if the word is already in the array
				words[i].occurance();//If so, increment its frequency
				break;//exit the loop
			}
			else if(words[i] == null){//If the word isnt in the array and the element is empty
				words[i] =  w;//Insert the word
				break;//Exit the loop
			}
		}
	}
	
	/*public void FsortArray(Word[] newWords,int num){
		//Insertion Sort
		for(int i = 1; i < num;i++){
			Word next = newWords[i];
			int location = i;
			while ((location > 0)&&(newWords[location-1].getFrequency() > next.getFrequency())){
				newWords[location] = newWords[location-1];
				location--;
			}
			newWords[location] = next;
		}
	}
	public void AsortArray(Word[] newWords, int first,int last){
		int pivotindex;
		if(first < last){
			pivotindex = partition(newWords,first,last);
			AsortArray(newWords,first,pivotindex-1);
			AsortArray(newWords,pivotindex+1,last);
		}
	}
	
	public int partition(Word[] newWords, int first, int last) {
		Word tempWord;
		char pivot = newWords[first].getLetters().charAt(0);
		int lastfront = first;
		for(int i =first+1;i <= last;i++){
			if(Character.compare(newWords[i].getLetters().charAt(0),pivot) < 0){
				lastfront++;
				tempWord = newWords[i];
				newWords[i] = newWords[lastfront];
				newWords[lastfront] = tempWord;
			}
		}
		tempWord = newWords[first];
		newWords[first] = newWords[lastfront];
		newWords[lastfront]=tempWord;
		return lastfront;
	}*/

	public void printArray(Word[] newWords){
		System.out.println("WORD:\t\tFREQUENCY:");
		Scanner keyb = new Scanner(System.in);//Open a new Scanner
		for(int i = 0;i < newWords.length;i++){//For every element in newWords
			if(newWords[i] != null && !(i % 16 == 0)){//If the element isnt null or the 15th element
				System.out.println(newWords[i].getLetters() +"\t\t"+newWords[i].getFrequency());//Print its word and its frequency
			}
			else if((i % 16 == 0 && i != 0) && newWords[i] != null){//Otherwise...
				System.out.println("15 lines printed. Please press <ENTER> to continue.");
				keyb.nextLine();//Hopefully this will be enter
			}
		}
		keyb.close();//Close the keyb scanner
	}
	
	public Word[] resize(){
		Word[]newWords = new Word[count];//Create a new array that has no null elements like words[5000]
		for(int i = 0;i < words.length;i++){//For every element in words
			if(words[i] != null){//If it isnt null
				newWords[i] = words[i];//Copy its element to newWords
			}
		}
		return newWords;//Return the smaller array
	}
}
