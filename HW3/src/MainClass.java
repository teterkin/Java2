import java.util.Arrays;
import java.util.HashSet;

public class MainClass
{
    public static void main(String[] args)
    {
        HashSet<String> hs = new HashSet<String>();

        String[] fruits = {"Манго", "Яблоко", "Мандарин", "Груша", "Груша",
            "Апельсин", "Апельсин", "Манго", "Манго", "Яблоко"};
        System.out.println("> Оригинальный массив:");
        System.out.println(Arrays.toString(fruits));

        System.out.println("> Хэшсет (уникальные фрукты):");
        for (String fruit : fruits)
        {
            hs.add(fruit);
        }
        System.out.println(hs);

        System.out.println("> Подсчёт:");
        int hsCount = 0;
        for (String h : hs)
        {
            int count = 0;
            System.out.print(h + " -> ");
            for (String fruit : fruits)
            {
                if (fruit == h)
                {
                    count++;
                }
            }
            if (hsCount < (hs.size()-1))
            {
                System.out.print(count + ", ");
            }
            else
            {
                System.out.println(count + ".");
            }
            hsCount++;
        }

    }
}
