

public class QuestionMC extends Question {
	String mAnswers;
	/**
	 * 
	 */
	public void showQuestion()
	{
		System.out.println(text + "\n"+"==========================================="
	+ " Options start are A through J");
		String[] tokens = mAnswers.split(":");
		for(int i = 0; i < tokens.length; i++)
		{
			System.out.println(tokens[i].stripLeading());
		}
	}
	/**
	 * 
	 * @return
	 */
	public boolean checkAnswer(String userAns)
	{
		if(answer.equalsIgnoreCase(userAns) )
		{
			markCorrect();
			return true;
		}
		else
			return false;
		
	}
}
