

public class QuestionSA extends Question {
	/**
	 * 
	 */
	public void showQuestion()
	{
		System.out.println(text);
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
