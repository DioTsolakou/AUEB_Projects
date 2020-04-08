package scheduler;

public class Lesson
{
    private String name;
    private int id;
    private String _class;
    private int hours;
    private int lessonCounter;
    private int totalHours;

    public Lesson()
    {
        name = null;
        id = 0;
        _class = null;
        hours = 0;
    }

    public Lesson(String name, int id, String _class, int hours)
    {
        this.name = name;
        this.id = id;
        this._class = _class;
        this.hours = hours;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String get_class()
    {
        return _class;
    }

    public void set_class(String _class)
    {
        this._class = _class;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getLessonCounter()
    {
        return lessonCounter;
    }

    public void setLessonCounter(int lessonCounter)
    {
        this.lessonCounter = lessonCounter;
    }

    public int getTotalHours()
    {
        return totalHours;
    }

    public void setTotalHours(int totalHours)
    {
        this.totalHours = totalHours;
    }

    public String toString()
    {
        return "Name : " +this.getName()+ ", ID : " +this.getId()+ ", Class : " +this.get_class()+ ", Hours : " +this.getHours();
    }
}