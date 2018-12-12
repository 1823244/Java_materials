public static void main(String[] args)
{
    List<String> list = new ArrayList<String>();
    list.add("Mama");
    list.add("Mila");
    list.add("Ramu");

    Iterator<String> iterator = list.iterator();//получение итератора для списка

    while (iterator.hasNext())      //проверка, есть ли ещё элементы   
    {
        //получение текущего элемента и переход на следующий
        String text = iterator.next();

        System.out.println(text);
    }
}

короткая запись

    for (String text : list)        
    {
        System.out.println(text);
    }