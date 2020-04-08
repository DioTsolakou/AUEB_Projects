package scheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{

    private int[][] genes;
    private int fitness;

    Main main = new Main();


    public Chromosome()
    {
        genes = new int[main.totalHours * 3][2]; //for the 3 divisions in each class
        Random random = new Random();
        for (int j = 0; j < main.totalHours*3; j++)
        {
            this.genes[j][0] = random.nextInt(main.lessonCounter) + 1;
            this.genes[j][1] = random.nextInt(main.teacherCounter) + 1;
        }
        this.calculateFitness();
    }

    public Chromosome(int[][] array)
    {
        this.genes = new int[array.length][array[0].length];
        for (int i = 0; i < genes.length; i++)
        {
            for (int j = 0; j < genes[0].length; j++)
            {
                this.genes[i][j] = array[i][j];
            }
        }
        this.calculateFitness();
    }

    public int[][] getGenes()
    {
        return this.genes;
    }

    public void setGenes(int[][] genes)
    {
        if (genes.length < genes[0].length)
        {
            for (int i = 0; i < genes.length; i++)
            {
                for (int j = 0; j < genes[0].length; j++)
                {
                    this.genes[i][j] = genes[i][j];
                }
            }
        }
        else
        {
            for (int i = 0; i < genes[0].length; i++)
            {
                for (int j = 0; j < genes.length; j++)
                {
                    this.genes[j][i] = genes[j][i];
                }
            }
        }
    }

    public int getFitness()
    {
        return this.fitness;
    }

    public void setFitness(int fitness)
    {
        this.fitness = fitness;
    }

    private void calculateFitness()
    {
        int conflicts = 0;
        int currentTeacher;
        int currentLesson;
        for (int j = 0; j < this.genes.length; j++)
        {
            currentTeacher = this.genes[j][1];
            if (currentTeacher == main.teacherArrayList.get(currentTeacher-1).getId()) //A teacher should only teach the lessons that are on his lessonID column
            {
                if (!Arrays.asList(main.teacherArrayList.get(currentTeacher-1).getLessonID()).contains(Integer.toString(this.genes[j][0])))
                {
                    conflicts++;
                }
            }
            currentLesson = this.genes[j][0];
            if (currentLesson == main.lessonArrayList.get(currentLesson-1).getId()) //A lesson should be taught only to its actual class
            {
                if (main.lessonArrayList.get(currentLesson-1).get_class().equals("A") && (j > 104))
                {
                    conflicts++;
                }
                else if (main.lessonArrayList.get(currentLesson-1).get_class().equals("B") && (j > 209 || j < 105))
                {
                    conflicts++;
                }
                else if (main.lessonArrayList.get(currentLesson-1).get_class().equals("C") && (j < 210))
                {
                    conflicts++;
                }
            }

            for (int k = j + 1; k < this.genes.length - 1; k++)
            {
                if (this.genes[j][1] == this.genes[k + 1][1]) //No more than 2 consecutive hours of teaching for a teacher
                {
                    conflicts++;
                }
                if (this.genes[j][0] == this.genes[k+1][0])
                {
                    conflicts++;  //The hours of a lesson should be evenly distributed in a week, e.g not all hours of a lesson in a single day
                }
            }
        }
        //System.out.println("Total conflicts : " +conflicts);
        this.fitness = conflicts;
    }

    public void mutate()
    {
        Random random = new Random();
        this.genes[random.nextInt(main.totalHours*3)][0] = random.nextInt(main.lessonArrayList.size()) + 1;
        this.genes[random.nextInt(main.totalHours*3)][1] = random.nextInt(main.teacherArrayList.size()) + 1;
        this.calculateFitness();
    }

    public File printFile() throws IOException
    {
        File schedule = new File("schedule.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(schedule));
        writer.write("The first number is the ID of the lesson and the second number is the ID of the teacher. See the two properly named txt files for reference.\n \n");
        writer.write("Max ID for A class is 11, for class B 22 and for class C 33. Any other number in each class means it's wrong...");
        writer.write("\n \n");

        for (int i = 0; i < 9; i++) //9 for the amount of divisions
        {
            //there are 9 divisions and the total hours are 315, 315/9 = 35 which is the weekly hours, with each day having 7 hours
            if (i == 0) //first division, A1
            {
                writer.write("Weekly Schedule for A1 \n");
                writer.write("|| Monday || ");
                for (int j = 0; j < 7; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 7; j < 14; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 14; j < 21; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 21; j < 28; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 28; j < 35; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 1) //second division, A2
            {
                writer.write("Weekly Schedule for A2 \n");
                writer.write("|| Monday || ");
                for (int j = 35; j < 42; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 42; j < 49; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 49; j < 56; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 56; j < 63; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 63; j < 70; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 2) //third division, A3
            {
                writer.write("Weekly Schedule for A3 \n");
                writer.write("|| Monday || ");
                for (int j = 70; j < 77; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 77; j < 84; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 84; j < 91; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 91; j < 98; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 98; j < 105; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 3) //fourth division, B1
            {
                writer.write("Weekly Schedule for B1 \n");
                writer.write("|| Monday || ");
                for (int j = 105; j < 112; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 112; j < 119; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 119; j < 126; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 126; j < 133; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 133; j < 140; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 4) //fifth division, B2
            {
                writer.write("Weekly Schedule for B2 \n");
                writer.write("|| Monday || ");
                for (int j = 140; j < 147; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 147; j < 154; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 154; j < 161; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 161; j < 168; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 168; j < 175; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 5) //sixth division, B3
            {
                writer.write("Weekly Schedule for B3 \n");
                writer.write("|| Monday || ");
                for (int j = 175; j < 182; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 182; j < 189; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 189; j < 196; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 196; j < 203; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 203; j < 210; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 6) //seventh division, C1
            {
                writer.write("Weekly Schedule for C1 \n");
                writer.write("|| Monday || ");
                for (int j = 210; j < 217; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 217; j < 224; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 224; j < 231; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 231; j < 238; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 238; j < 245; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 7) //eighth division, C2
            {
                writer.write("Weekly Schedule for C2 \n");
                writer.write("|| Monday || ");
                for (int j = 245; j < 252; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 252; j < 259; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 259; j < 266; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 266; j < 273; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 273; j < 280; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
            else if (i == 8) //ninth division, C3
            {
                writer.write("Weekly Schedule for C3 \n");
                writer.write("|| Monday || ");
                for (int j = 280; j < 287; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1] + " |");
                }
                writer.write("\n");
                writer.write("|| Tuesday || ");
                for (int j = 287; j < 294; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Wednesday || ");
                for (int j = 294; j < 301; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Thursday || ");
                for (int j = 301; j < 308; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n");
                writer.write("|| Friday || ");
                for (int j = 308; j < 315; j++)
                {
                    writer.write(genes[j][0]+ ", " +genes[j][1]+ " |");
                }
                writer.write("\n \n");
            }
        }
        writer.flush();
        writer.close();
        return schedule;
    }

    @Override
    public int hashCode()
    {
        int hashcode = 0;
        for(int i = 0; i< this.genes[0].length; i++)
        {
            for (int j = 0; j < this.genes.length; j++)
            {
                hashcode += this.genes[j][i];
            }
        }
        return hashcode;
    }

    @Override
    public int compareTo(Chromosome x)
    {
        return this.fitness - x.fitness;
    }
}