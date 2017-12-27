public class HomeWork2
{
    public static void main(String[] args)
    {
        boolean gotErrors = false;
        // Good test
        String[][] arrGood = {
                {"0", "1", "0", "1"},
                {"1", "1", "0", "0"},
                {"0", "0", "0", "1"},
                {"1", "1", "1", "1"}};
        MyUtils.printArray(arrGood);
        try
        {
            MyUtils.sum4x4Matrix(arrGood);
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Ошибка в размере матрицы!");
            System.out.println(ex.getMessage());
            gotErrors = true;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Ошибка с исходными данными!");
            System.out.println(ex.getMessage());
        }
        System.out.println("---");

        // Bad test 1
        String[][] arrBad1 = {
                {"0", "1", "0", "1", "1"},
                {"1", "1", "0", "0", "1"},
                {"0", "0", "0", "1", "1"},
                {"1", "1", "1", "1", "1"},
                {"1", "1", "0", "1", "1"}};
        MyUtils.printArray(arrBad1);
        try
        {
            MyUtils.sum4x4Matrix(arrBad1);
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Ошибка в размере матрицы!");
            System.out.println(ex.getMessage());
            gotErrors = true;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Ошибка с исходными данными!");
            System.out.println(ex.getMessage());
        }
        System.out.println("---");

        // Bad test 2
        String[][] arrBad2 = {
                {"0", "1"},
                {"1", "1"}};
        MyUtils.printArray(arrBad2);
        try
        {
            MyUtils.sum4x4Matrix(arrBad2);
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Ошибка в размере матрицы!");
            System.out.println(ex.getMessage());
            gotErrors = true;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Ошибка с исходными данными!");
            System.out.println(ex.getMessage());
        }
        System.out.println("---");

        // Bad test 3
        String[][] arrBad3 = {
                {"0", "1", "0", "1"},
                {"1", "1", "0", "A"},
                {"0", "0", "0", "1"},
                {"1", "1", "1", "1"}};
        MyUtils.printArray(arrBad3);
        try
        {
            MyUtils.sum4x4Matrix(arrBad3);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Ошибка в размере матрицы!");
            System.out.println(ex.getMessage());
            gotErrors = true;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Ошибка с исходными данными!");
            System.out.println(ex.getMessage());
        }
        finally
        {
            System.out.println("В любом случае было весело!");
        }
        System.out.println("---");
    }
}

class MyUtils
{
    public static void sum4x4Matrix(String[][] strings)
    {
        int sum = 0;
        int cols = 0, rows = 0;
        for (String[] strArr : strings)
        {
            rows = 0;
            for (String str : strArr)
            {
                try
                {
                    sum += Integer.parseInt(str);
                    rows++;
                }
                catch (NumberFormatException ex)
                {
                    throw new NumberFormatException("Некорректное число в строке " +
                            (cols + 1) + ", колонке " + (rows + 1) + ".\n" +
                    "Возможно " + str + " не является числом.");
                }
            }
            cols++;
        }
        if (cols != 4 && rows != 4)
        {
            throw new ArrayIndexOutOfBoundsException("Принимаются матрицы только размером 4х4.");
        } else
        {
            System.out.println("Рядов = " + rows + ". Колонок = " + cols + ".");
            System.out.println("Сумма всех элементов: " + sum);
        }
    }

    public static void printArray(String[][] strings)
    {
        System.out.println("Матрица:");
        for (String[] strArr : strings)
        {
            for (String str : strArr)
            {
                System.out.print(str + "  ");
            }
            System.out.println();
        }
    }
}