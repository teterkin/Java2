import java.util.HashMap;
import java.util.TreeMap;

public class PBTester
{
    public static void main(String[] args)
    {
        PhoneBook pb = new PhoneBook(new HashMap<String, String>());
        addEntry(pb, "Петров", "+79123366567");
        addEntry(pb, "Иванов", "+79123287585");
        addEntry(pb, "Сидоров", "+79124374477");
        addEntry(pb, "Петров", "+79124348747");
        addEntry(pb, "Иванов", "+79124738438");

        System.out.println("---");
        System.out.println(pb.get("Петров"));
        System.out.println(pb.get("Иванов"));
        System.out.println(pb.get("Сидоров"));
    }

    public static void addEntry(PhoneBook pb, String family, String phone)
    {
        if(pb.add(family, phone))
        {
            System.out.println(" => Success.");
        }
        else
        {
            System.out.println(" => Failed.");
        }
    }
}
