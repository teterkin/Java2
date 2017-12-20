package course;

import animals.Animal;
import team.Team;
import obstacles.*;

public class Course
{
    private Object[] obstacles = new Object[3];
    private boolean[] localDone = new boolean[3];

    public Course(Object[] obstacles)
    {
        this.obstacles = obstacles;
        for (int i = 0; i < localDone.length; i++)
        {
            localDone[i] = false;
        }
    }

    public void doIt (Team team)
    {
        if(obstacles.length > 0)
        {
            for (int i = 0; i < obstacles.length; i++)
            {
                if (!localDone[i])
                {
                    int aniNum = 0;
                    for (Animal animal : team.getMembersArray())
                    {
                        String obs = obstacles[i].getClass().getName();
                        // System.out.println(obs);
                        // System.out.println(animal);
                        if (obs == "obstacles.Track")
                        {
                            if(team.getDone(aniNum))
                            {
                                System.out.println("> " + animal + " уже поработал. Отдыхай!");
                            }
                            else
                            {
                                Boolean canRun = ((Track) obstacles[i]).doIt(animal);
                                // System.out.println(canRun);
                                if (canRun)
                                {
                                    System.out.println("> " + animal + " хорошо сбегал. Молодец!");
                                    team.setDone(aniNum);
                                    localDone[i] = true;
                                    break;
                                }
                            }
                        } else if (obs == "obstacles.Wall" && ! team.getDone(aniNum))
                        {
                            if(team.getDone(aniNum))
                            {
                                System.out.println("> " + animal + " уже поработал. Отдыхай!");
                            }
                            else
                            {
                                Boolean canJump = ((Wall) obstacles[i]).doIt(animal);
                                // System.out.println(canJump);
                                if (canJump)
                                {
                                    System.out.println("> " + animal + " хорошо прыгнул. Молодец!");
                                    team.setDone(aniNum);
                                    localDone[i] = true;
                                    break;
                                }
                            }
                        } else if (obs == "obstacles.Water" && ! team.getDone(aniNum))
                        {
                            if(team.getDone(aniNum))
                            {
                                System.out.println("> " + animal + " уже поработал. Отдыхай!");
                            }
                            else
                            {
                                Boolean canSwim = ((Water) obstacles[i]).doIt(animal);
                                // System.out.println(canSwim);
                                if (canSwim)
                                {
                                    System.out.println("> " + animal + " хорошо сплавал. Молодец!");
                                    team.setDone(aniNum);
                                    localDone[i] = true;
                                    break;
                                }
                            }
                        }
                        aniNum++;
                    }
                }
            }
        }
        else
        {
            System.out.println("Сначала добавьте припятствия!");
        }
    }

}
