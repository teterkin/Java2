public class ArrayMultiThreading
{
    public static final int size = 10000000;
    public static final int h = size / 2;
    public static float[] arr = new float[size];
    public static float[] a1 = new float[h];
    public static float[] a2 = new float[h];
    public static boolean firstRun = true;
    public static long firstTime = 0;
    public static long secondTime = 0;

    public static void main(String[] args)
    {
        System.out.println("\nНачинаю инициализацию...");
        initializeArray();
        System.out.println("\nЗапускаю задачу в 1 поток...");
        oneWayArrayRun();
        System.out.println("\nПовторная иницаилизация (сброс)...");
        initializeArray();
        // Пауза между задачами
        try
        {
            System.out.println("Ждем 4 секунды пока вы читаете вывод... :)");
            Thread.sleep(4000);
        } catch (InterruptedException e)
        {
            System.out.println("Ошибка при ожидании в основном потоке.");
            System.out.println("Трассировка:");
            e.printStackTrace();
        }
        System.out.println("\nЗапускаю задачу в 2 потока...");
        twoWayArrayRun();
        System.out.println("\nРезультаты:");
        System.out.println("В один поток:  " + firstTime + " мс.");
        System.out.println("В 2 потока: " + secondTime + " мс.");
        System.out.println("Разница на " + (firstTime - secondTime)
                + " мс или в " + ((float) firstTime / (float) secondTime) + " раза.");
    }

    private static void initializeArray()
    {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = 1.0f;
        }
        System.out.println("Время инициализации: " + (System.currentTimeMillis() - a) + " мс.");
    }

    private static void oneWayArrayRun()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = (float) (
                    arr[i]
                    * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2)
            );
        }
        long oneWayTime = System.currentTimeMillis() - startTime;
        System.out.println("Время выполнения в 1 поток: "
                + oneWayTime + " мс.");
        firstTime = oneWayTime;
    }

    private static void twoWayArrayRun()
    {
        // Разбиваем массив на 2 части
        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        long splitTime = System.currentTimeMillis() - startTime;

        System.out.println("Время разбиения массива на 2 части: "
                + splitTime + " мс.");

        // Запускаем первый поток
        long a1StartTime = System.currentTimeMillis();
        ThreadEngine th1 = new ThreadEngine("BladeRunner-1");

        // Запускаем второй поток
        long a2StartTime = System.currentTimeMillis();
        ThreadEngine th2 = new ThreadEngine("BladeRunner-2");

        long a1Time = 0;
        long a2Time = 0;
        try
        {
            // Ждем завершения первого потока
            th1.thread.join();
            a1Time = System.currentTimeMillis() - a1StartTime;
            System.out.println("Время выполнения 1-го потока: "
                    + a1Time + " мс.");

            // Ждем завершение второго потока
            th2.thread.join();
            a2Time = System.currentTimeMillis() - a2StartTime;
            System.out.println("Время выполнения 2-го потока: "
                    + a2Time + " мс.");
        }
        catch (InterruptedException e)
        {
            System.out.println("Ошибка: неожиданное прерывание в первом потоке.");
            System.out.println("Трассировка:");
            e.printStackTrace();
        }

        // Склеивание массивов
        long arrJoinStartTime = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long arrJoinTime = System.currentTimeMillis() - arrJoinStartTime;
        System.out.println("Время склейки массивов: "
                + arrJoinTime + " мс.");
        System.out.println();

        long twoWayTime = System.currentTimeMillis() - startTime;
        System.out.println("Общее время выполнения задачи в 2 потока: "
                + twoWayTime + " мс.");
        secondTime = twoWayTime;

        // Анализ подсчета времени
        long maxTime = a1Time;
        if (a2Time > a1Time)
        {
            maxTime = a2Time;
        }
        long allTheTime = splitTime + maxTime + arrJoinTime;
        if (allTheTime == twoWayTime)
        {
            System.out.println("Потерь времени не обнаружено.");
        }
        else
        {
            System.out.println("Есть небольшая потеря: время разбивки" +
                    " + время работы наиболее медленного процесса" +
                    " + время склейки = " + allTheTime + " мс. Разница: " +
                    (twoWayTime - allTheTime) + " мс.");
        }
    }
}

/**
 * Класс потока
 * Класс не использует модификатор volatile, т.к. работающие параллельно потоки
 * не читают и не пишут в одну переменную, каждый поток использует собственный массив.
 */
class ThreadEngine implements Runnable
{
    Thread thread;

    public ThreadEngine(String name)
    {
        thread = new Thread(this, name);
        thread.start();
    }

    public void run()
    {
        if (ArrayMultiThreading.firstRun)
        {
            ArrayMultiThreading.firstRun = false;
            System.out.println("Запускаем поток " + thread.getName() + " ...");
            try
            {
                // Используем первую часть массива
                for (int i = 0; i < ArrayMultiThreading.a1.length; i++)
                {
                    ArrayMultiThreading.a1[i] = (float) (
                            ArrayMultiThreading.a1[i]
                                    * Math.sin(0.2f + i / 5)
                                    * Math.cos(0.2f + i / 5)
                                    * Math.cos(0.4f + i / 2)
                    );
                }
                // Пусть другие поработают
                Thread.sleep(100);
            } catch (InterruptedException ex)
            {
                System.out.println("Ошибка: процесс " + thread.getName() + " неожиданно прерван.");
            }
            System.out.println("Процесс " + thread.getName() + " завершен.");
        }
        else
        {
            // Второй запуск
            System.out.println("Запускаем поток " + thread.getName() + " ...");
            try
            {
                // Используем вторую часть массива
                for (int i = 0; i < ArrayMultiThreading.a2.length; i++)
                {
                    ArrayMultiThreading.a2[i] = (float) (
                            ArrayMultiThreading.a2[i]
                                    * Math.sin(0.2f + i / 5)
                                    * Math.cos(0.2f + i / 5)
                                    * Math.cos(0.4f + i / 2)
                    );
                }
                // Пусть другие поработают
                Thread.sleep(100);
            } catch (InterruptedException ex)
            {
                System.out.println("Ошибка: процесс " + thread.getName() + " неожиданно прерван.");
            }
            System.out.println("Процесс " + thread.getName() + " завершен.");

        }
    }
    // Класс потока
}