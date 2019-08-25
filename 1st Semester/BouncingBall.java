import acm.program.*;
import acm.graphics.*;

public class BouncingBall extends GraphicsProgram 
{
	private static final int BALL_SIZE = 100;
	private static final int PAUSE_TIME = 2;
	
	public void run() 
	{
		double x = (getWidth() - BALL_SIZE)/2;
		double y = (getHeight() - BALL_SIZE)/2;
		
		GOval ball = new GOval(x, y, BALL_SIZE, BALL_SIZE);
		ball.setFilled(true);
		add(ball);
		
		int dx = 1;
		int dy = 1;
		
		while (true) 
		{
			ball.move(dx, dy);
			pause(PAUSE_TIME);
			
			x = x + dx;
			y = y + dy;
			
			if (x == 0) 
			{
				dx = -dx;
			}
			if (y == 0) 
			{
				dy = -dy;
			}
			if (x == getWidth() - BALL_SIZE) 
			{
				dx = -dx;
			}
			if (y == getHeight() - BALL_SIZE) 
			{
				dy = -dy;
			}
		}
	}
}