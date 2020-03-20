

public class QuestionTF extends Question {
	/**
	 * 
	 */
	public void showQuestion()
	{
		if(correct == false)
		System.out.println(text);
	}
	/**
	 * 
	 * @return
	 */
	public boolean checkAnswer(String userAns)
	{
		userAns.toUpperCase();
		if(userAns.startsWith(answer))
		{
			markCorrect();
			return true;
		}
		if(answer.equalsIgnoreCase(userAns) )
		{
			markCorrect();
			return true;
		}
		else
			return false;
		
	}
}
