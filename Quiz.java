

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Quiz {
	private ArrayList<Question> questions =  new ArrayList<Question>();
	private int numIncorrect;
	private String fileName;
	private boolean fileNameIsValid;
	//------------------------------------------------------
	/**
	 * 
	 * @param dataFile
	 */
	Quiz(String dataFile)
	{
		fileName = dataFile;

		loadQuestions(dataFile);
		if(questions.isEmpty())
		{
			System.err.println("Error: no questions loaded.");
			return;
		}
			
	}

	//=====================================================
	/**
	 * 
	 * @param dataFile
	 * @return
	 */
	private boolean loadQuestions(String dataFile)
	{
		questions.clear();
	
		String fileData;
	
		File file = new File(dataFile);
		
		try {
			
			Scanner fsc = new Scanner(file);
			while(fsc.hasNextLine())
			{
				fileData = fsc.nextLine();
				if(checkData(fileData))
				{
					addQuestion(fileData);
					
				}
			}
			fsc.close();
			return true;
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error: not found.");
		System.err.println("File could not be opened. File name invalid.\n");
			return false;
		}
	}
	//========================================================
	/**
	 * 
	 * @param data
	 */
	private void addQuestion(String data)
	{
		 String[] tokens = data.split("[|]");
		 
		 	if(tokens[0].matches("SA"))
		 	{
		 		Question  newQuestion = new QuestionSA();
		 		newQuestion.text = tokens[2];
		 		newQuestion.answer = tokens[3];
		 		
		 		questions.add(newQuestion);
		 		
		 	}
			if(tokens[0].matches("TF"))
			{
				Question newQuestion = new QuestionTF();
				newQuestion.text = tokens[2];
		 		newQuestion.answer = tokens[3];
		 		
				questions.add(newQuestion);
				
			}
			
			if(tokens[0].matches("MC"))
			{
				QuestionMC newQuestion = new QuestionMC();
				newQuestion.mAnswers = tokens[3];
				newQuestion.text =tokens[2];
		 		newQuestion.answer = tokens[4];
		
				questions.add(newQuestion);
			}
	}
	//========================================================
	/**
	 * 
	 * @return
	 */
	private int totalQuestions()
	{
		if(questions.isEmpty())
			return 0;
		else
		return questions.size();
	}
	//=========================================================
	/**
	 * 
	 */
	void deliverQuiz(Scanner scan)
	{
		
		numIncorrect = 0;
		String choice ="Y";
		
		while(choice.equals("Y") ||choice.equals("y"))
		{
		for(int i = questions.size()-1; i >= 0; i--)
		{
		
			String userAnswer;
			if(questions.get(i).correct == false)
			{
				questions.get(i).showQuestion();
			
				userAnswer = scan.nextLine();
			
				if(questions.get(i).checkAnswer(userAnswer))
				{
					if(questions.get(i).correct)
						{
							questions.get(i).markCorrect();
							System.out.println("great job!");
						}
					}
					else
					{
						numIncorrect++;
						System.out.println("Thats too bad, the correct answer is ");
						questions.get(i).showAnswer();
					}
				}
				
			}
		System.out.println("You got "+ getCorrectCount() + " correct." );
			if(numIncorrect != 0)
			{
				System.out.println("Would you like to continue? (Y/N)");
				choice = scan.nextLine();
				numIncorrect = 0;
			}
			else break;
			
		}
	}
	
	//========================================================
	/**
	 * 
	 * @return
	 */
	private int getCorrectCount()
	{
		return questions.size()-numIncorrect;
	}
	//========================================================
	/**
	 * 
	 * @return
	 */
	private int getIncorrectCount()
	{
		return numIncorrect;
	}

	//========================================================
	/**
	 * 
	 * @param str2Chk
	 * @return boolean
	 */
	 private static boolean checkData(String str2Chk)
	 {
		 str2Chk.toUpperCase();
		 str2Chk.strip();
		 if(str2Chk.isBlank()) {
			 System.err.println("blank space.");
			 return false;
			 
		 }
		 else if(str2Chk.charAt(0)=='#')
		 {
			 return false;
		 }
		 String[] tokens = str2Chk.split("[|]", 4);
		 if(tokens.length < 4)//checks for valid number of fields. minimum off 4 to be valid
			{
				System.err.println("Bad Line, Invalid format, or too few fields");
				return false;
			}
		 if(!tokens[0].matches("SA") && !tokens[0].matches("TF") && !tokens[0].matches("MC"))// checks first field for valid type
			{
				System.err.println("Invalid type.");
				return false;
			}
			
			if(tokens[1].length()==1)//checks the length of the field.
			{
				
				int i =Integer.parseInt(tokens[1]);
				if(!(i<10 && i > 0))
				{// checks field value, must be betweeen 1 and 9 (inclusive) to be valid
					System.err.println("Invalid level");
				return false;
				}
				return true;
			}
			else
			{
				
				System.err.println("Invalid level.");
			}
			
			if(tokens[2].isBlank())
			{
				System.err.println("Invalid Question.");
				return false;
			}
			
			if(tokens[0].matches("TF"))
			{
				if(!(tokens[3].toUpperCase().matches("T") || tokens[3].toUpperCase().matches("F")))
				{
					System.err.println("Invalid answer, must be T or F");
					return false;
				}
				
			}
			
			if(tokens[0].matches("MC"))
			{
				String[] token = tokens[3].split("[:]");
				int numQs = 0;
				for(String word:token)
				{
					numQs++;
				}
				if(numQs<2 || numQs>10)
				{
					System.err.println("Wrong number of Answers, this field must "
							+ "contain no more than 10 and no less than 2\n");
					return false;
				}
				
			}
	
		 return true;
	 }
	
}
