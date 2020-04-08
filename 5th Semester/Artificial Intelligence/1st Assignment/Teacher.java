package scheduler;

public class Teacher
{
    private String name;
    private int id;
    private String[] lessonID;
    private int maxWeeklyHours;
    private int maxDailyHours;
    private int teacherCounter;

    public Teacher()
    {
        name = null;
        id = 0;
        lessonID = null;
        maxWeeklyHours = 0;
        maxDailyHours = 0;
    }

    public Teacher(String name, int id, String[] lessonID, int maxWeeklyHours, int maxDailyHours)
    {
        this.name = name;
        this.id = id;
        this.lessonID = lessonID;
        this.maxWeeklyHours = maxWeeklyHours;
        this.maxDailyHours = maxDailyHours;
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

    public String[] getLessonID()
    {
        return lessonID;
    }

    public void setLessonID(String[] lessonID)
    {
        this.lessonID = lessonID;
    }

    public int getMaxWeeklyHours()
    {
        return maxWeeklyHours;
    }

    public void setMaxWeeklyHours(int maxWeeklyHours)
    {
        this.maxWeeklyHours = maxWeeklyHours;
    }

    public int getMaxDailyHours()
    {
        return maxDailyHours;
    }

    public void setMaxDailyHours(int maxDailyHours)
    {
        this.maxDailyHours = maxDailyHours;
    }

    public int getTeacherCounter()
    {
        return teacherCounter;
    }

    public void setTeacherCounter(int teacherCounter)
    {
        this.teacherCounter = teacherCounter;
    }
}