import acm.program.*;
import acm.graphics.*;

public class HouseOfUsher extends GraphicsProgram 
{

	private static final int tower_height = 500;
	private static final int tower_width = 100;
	private static final int house_height = 400;
	private static final int house_width = 350;
	private static final int window_height = 70;
	private static final int window_width = 70;
	private static final int door_height = 100;
	private static final int door_width = 50;
	private static final int small_tower_height = 250;
	private static final int small_tower_width = 75;

	GRect tower(int x, int y) 
	{
		GRect rect = new GRect(tower_width, tower_height);
		rect.setLocation(x, y);
		return rect;
	}
		
	GRect house(int x, int y) 
	{
		GRect rect = new GRect(house_width, house_height);
		rect.setLocation(x, y);
		return rect;
	}

	GRect small_tower(int x, int y) 
	{
		GRect rect = new GRect(small_tower_width, small_tower_height);
		rect.setLocation(x, y);
		return rect;
	}

	GRect door(int x, int y) 
	{
		GRect rect = new GRect(door_width, door_height);
		rect.setLocation(x, y);
		return rect;
	}

	GOval window(int x, int y) 
	{
		GOval circle = new GOval(window_width, window_height);
		circle.setLocation(x, y);
		return circle;
	}

	GPolygon tower_top(int x, int y) 
	{
		GPolygon tower_top = new GPolygon();
		tower_top.addVertex(x, y);
		tower_top.addEdge(50, -150);
		tower_top.addEdge(50, 150);
		return tower_top;
	}

	GPolygon house_top(int x, int y) 
	{
		GPolygon house_top = new GPolygon();
		house_top.addVertex(x, y);
		house_top.addEdge(175, -200);
		house_top.addEdge(175, 200);
		return house_top;
	}

	GPolygon door_top(int x, int y) 
	{
		GPolygon door_top = new GPolygon();
		door_top.addEdge(x, y);
		door_top.addEdge(25, -50);
		door_top.addEdge(25, 50);
		return door_top;
	}

	GPolygon small_tower_top(double x, double y) 
	{
		GPolygon small_tower_top = new GPolygon();
		small_tower_top.addVertex(x, y);
		small_tower_top.addEdge(37.5, -150);
		small_tower_top.addEdge(37.5, 150);
		return small_tower_top;
	}

	public void run() 
	{
		add(tower(150, 250));
		add(tower(600, 250));
		add(house(250, 350));
		add(window(300, 450));
		add(window(480, 450));
		add(door(400, 650));
		add(small_tower(900, 500));
		add(small_tower(1100, 500));
		add(small_tower(1300, 500));
		add(tower_top(150, 250));
		add(tower_top(600, 250));
		add(house_top(250, 350));
		add(door_top(400, 650));
		add(small_tower_top(900, 500));
		add(small_tower_top(1100, 500));
		add(small_tower_top(1300, 500));
	}
}
	
	
