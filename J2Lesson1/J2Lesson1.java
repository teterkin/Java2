import animals.*;
import obstacles.*;
import team.*;
import course.*;

/**
 * Write a description of class J2Lesson1 here.
 *
 * @author Sergey Iryupin, Alexander Teterkin
 * @version dated Dec 21, 2017
 * @link https://github.com/
 */
public class J2Lesson1 {
    
    public static void main(String[] args) {
        Animal[] zoo = {new Cat("Мурзик"), new Hen("Иззи"), new Hippo("Гипоппо"), new Hen("Огненая птица")};
        Track track = new Track(80);
        Wall wall = new Wall(3);
        Water water = new Water(10);
        Object[] obstacles = { track, wall, water };

        
//        for (Animal animal : zoo) {
//            System.out.println(animal + " say: " + animal.voice());
//            System.out.println(" run: " + track.doIt(animal));
//            System.out.println(" jump: " + wall.doIt(animal));
//            System.out.println(" swim: " + water.doIt(animal));
//        }

        Team team = new Team("Екатеринбуржский зоопарк", zoo);
        team.getMembers();
        Course course = new Course(obstacles);
        course.doIt(team);
        team.getMembersWhoFinishedTheCourse();
        team.getBoomers();
         System.out.println(team);

    }
   
}