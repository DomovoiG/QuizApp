

public abstract class Question {
	protected String text;
	protected String answer;
	protected boolean correct = false;
	//----------------------------------------
	
	public void markCorrect()
	{
		correct = true;
	}
	//========================================
	public boolean isCorrect()
	{
		
		return false;
	}
	//========================================
	public void showQuestion()
	{
		
	}
	public void showAnswer()
	{
		System.out.println(answer);
	}
	//========================================
	public boolean checkAnswer(String givenAnswer)
	{
		if(givenAnswer.contentEquals(answer))
		{
			return true;
		}
		else 
			return false;
	}
}