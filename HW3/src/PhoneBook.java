import java.util.HashMap;
import java.util.Map;

public class PhoneBook
{
    private HashMap<String, String> book; // Phone is a Key, Family name is a Value.

    public PhoneBook(HashMap<String, String> book)
    {
        this.book = book;
    }

    public boolean add(String familyName, String phone)
    {
        boolean success;
        System.out.print("Добавляю запись => {\"" + familyName + "\",\"" + phone + "\"}.");
        try
        {
            book.put(phone, familyName); // Phone is a Key, Family name is a Value.
            success = true;
        }
        catch (Exception ex)
        {
            System.out.print("\nОшибка при добавлении записи.");
            success = false;
        }

        return success;
    }

    public String get(String familyName)
    {
        String phoneMany = "Для фамилии \""+ familyName + "\" найдены телефоны:\n";
        String phoneOne = "Для фамилии \""+ familyName + "\" найден телефон:\n";
        String phones = "";
        int num = 0;

        for (Map.Entry<String, String> entry : book.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.equals(familyName))
            {
                phones += key + "\n";
                num++;
            }
        }
        // System.out.println(num);
        if (num>1)
        {
            phones = phoneMany + phones;
        }
        else
        {
            phones = phoneOne + phones;
        }

        return phones;
    }
}
