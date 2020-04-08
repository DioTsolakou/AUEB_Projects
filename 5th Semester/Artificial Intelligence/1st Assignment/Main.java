package scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main
{
    public static ArrayList<Teacher> teacherArrayList = new ArrayList<>();
    public static ArrayList<Lesson> lessonArrayList = new ArrayList<>();
    public static int lessonCounter = 0;
    public static int totalHours = 0;
    public static int teacherCounter = 0;

    public static void main(String args[]) throws IOException
    {
        Random random = new Random();

        //Teacher file reading
        try {
            String path = "src//scheduler//teachers.txt";
            Reader reader;
            String line;
            StringTokenizer stringTokenizer;
            String token;
            String[] splits;
            reader = Files.newBufferedReader(Paths.get(path));
            BufferedReader buffReader = new BufferedReader(reader);

            line = buffReader.readLine();
            while ((line = buffReader.readLine()) != null)
            {
                Teacher newTeacher = new Teacher();
                stringTokenizer = new StringTokenizer(line, "/");
                for (int i = 0; i < 5; i++)
                {
                    token = stringTokenizer.nextToken();
                    if (i == 0)
                    {
                        newTeacher.setName(token);
                    }
                    else if (i == 1)
                    {
                        newTeacher.setId(Integer.parseInt(token));
                    }
                    else if (i == 2)
                    {
                        splits = token.split(",");
                        newTeacher.setLessonID(splits);
                    }
                    else if (i == 3)
                    {
                        newTeacher.setMaxWeeklyHours(Integer.parseInt(token));
                    }
                    else if (i == 4)
                    {
                        newTeacher.setMaxDailyHours(Integer.parseInt(token));
                    }
                }
                teacherCounter++;
                teacherArrayList.add(newTeacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lesson file reading
        try {
            String path = "src//scheduler//lessons.txt";
            Reader reader;
            String line;
            StringTokenizer stringTokenizer;
            String token;
            reader = Files.newBufferedReader(Paths.get(path));
            BufferedReader buffReader = new BufferedReader(reader);

            line = buffReader.readLine();
            while ((line = buffReader.readLine()) != null)
            {
                Lesson newLesson = new Lesson();
                stringTokenizer = new StringTokenizer(line, "/");
                for (int i = 0; i < 4; i++)
                {
                    token = stringTokenizer.nextToken();
                    if (i == 0)
                    {
                        newLesson.setName(token);
                    }
                    else if (i == 1)
                    {
                        newLesson.setId(Integer.parseInt(token));
                    }
                    else if (i == 2)
                    {
                        newLesson.set_class(token);
                    }
                    else if (i == 3)
                    {
                        newLesson.setHours(Integer.parseInt(token));
                        totalHours += Integer.parseInt(token);
                    }
                }
                lessonCounter++;
                lessonArrayList.add(newLesson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] infoArray = new int[totalHours*3][2];
        for (int i = 0; i < totalHours*3; i++)
        {
            infoArray[i][0] = lessonArrayList.get(random.nextInt(lessonCounter)).getId();
            infoArray[i][1] = teacherArrayList.get(random.nextInt(teacherCounter)).getId();
        }

        long timeStart = System.currentTimeMillis();
        Genetic genetic = new Genetic();
        Chromosome x = new Chromosome();
        x.setGenes(infoArray);
        x = genetic.geneticAlgorithm(100, 0.03, 5000,2500);
        x.printFile();
        long timeEnd = System.currentTimeMillis();
        float seconds = (timeEnd - timeStart) / 1000F;
        System.out.println("Time taken : " +seconds+ " seconds");

        //Time taken on AMD FX-8320 3.7GHz, AMD Radeon HD 7750 2GB, 8GB 1866Hz RAM : approximately 57 seconds to reach 2500 steps
    }
}
