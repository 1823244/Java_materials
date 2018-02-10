 public static void main(String[] args)
{
    Set<String> set = new HashSet<String>();
    set.add("Mama");
    set.add("Mila");
    set.add("Ramu");

    //получение итератора для множества
    Iterator<String> iterator = set.iterator();

    while (iterator.hasNext())        //проверка, есть ли ещё элементы   
    {
        //получение текущего элемента и переход на следующий
        String text = iterator.next();

        System.out.println(text);
    }
}

короткий вариант

public static void main(String[] args)
{
    Set<String> set = new HashSet<String>();
    set.add("Mama");
    set.add("Mila");
    set.add("Ramu");

    for (String text : set)   
    {
        System.out.println(text);
    }
}
