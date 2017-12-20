package team;

import animals.*;
import java.util.Arrays;

public class Team
{
    private String name;
    private Animal[] members;
    private boolean[] done = new boolean[4];

    public Team(String name, Animal[] members)
    {
        this.name = name;
        this.members = members;
        // System.out.println("Команда: " + this.name + ":");
        // System.out.println("Добавлены в команду новые товарищи:");
        // System.out.println(Arrays.toString(this.members));
        for (int i = 0; i < done.length; i++)
        {
            done[i] = false;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void getMembers()
    {
        String RC = "";
        System.out.println("\nКоманда \"" + name + "\":");
        // System.out.println(Arrays.toString(this.members));
        // System.out.println("Количество членов команды: " + this.members.length);
        for (int i = 0; i < this.members.length; i++)
        {
            RC += "- " + this.members[i] + "\n";
        }
        System.out.println(RC);
    }

    public Animal[] getMembersArray()
    {
        return members;
    }

    public void getMembersWhoFinishedTheCourse()
    {
        Animal[] tempArray = new Animal[4];
        int memberIdx = 0;
        int tempIdx = 0;
        String RC = "\nВ команде \"" + name + "\" прошли путь:\n";
        for (Animal member : members)
        {
            // Добавляем в новый массив только тех, кто прошел свой путь.
            if (done[memberIdx])
            {
                tempArray[tempIdx] = member;
                tempIdx++;
            }
            memberIdx++;
        }
        if (tempIdx > 0)
        {
            for (int i = 0; i < tempArray.length; i++)
            {
                if (tempArray[i] != null)
                {
                    RC += "- " + tempArray[i] + "\n";
                }
            }
        }
        else
        {
            RC = "Пока никто не дошел до финиша";
        }
        System.out.println(RC);
    }

    public Animal[] getMembersWhoFinishedTheCourseArray()
    {
        Animal[] tempArray = new Animal[4];
        int memberIdx = 0;
        int tempIdx = 0;
        for (Animal member : members)
        {
            if (done[memberIdx])
            {
                tempArray[tempIdx] = member;
                tempIdx++;
            }
            memberIdx++;
        }
        return tempArray;
    }

    /**
     * Устанавливает факт того, что животное прошло свой путь
     * @param idx - положение в массиве флагов done, совпадает с массивом животных
     */
    public void setDone(int idx)
    {
        this.done[idx] = true;
    }

    public boolean getDone(int idx)
    {
        return this.done[idx];
    }

    public void getBoomers()
    {
        Animal[] tempArray = new Animal[4];
        int memberIdx = 0;
        int tempIdx = 0;
        String RC = "";
        for (Animal member : members)
        {
            // Добавляем в новый массив только тех, кто не прошел свой путь.
            if (!done[memberIdx])
            {
                tempArray[tempIdx] = member;
                tempIdx++;
            }
            memberIdx++;
        }
        if (tempIdx > 0)
        {
            for (int i = 0; i < tempArray.length; i++)
            {
                if (tempArray[i] != null)
                {
                    RC += "- " + tempArray[i] + " - сачок! ;)\n";
                }
            }
        }
        else
        {
            RC = "Все поработали. Молодцы!";
        }
        System.out.println(RC);
    }

    @Override
    public String toString()
    {
        return "Team{" +
                "name='" + name + '\'' +
                ", members=" + Arrays.toString(members) +
                ", done=" + Arrays.toString(done) +
                '}';
    }
}
