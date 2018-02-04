/*
Есть массив, минимум из трех чисел, в котором все четные, кроме одного.
Другой вариант - все нечетные, кроме одного.
Найти выделяющийся элемент
*/
public class FindOutlier{
  static int find(int[] integers){
  
    int res = 0;
    //проверяем каждую тройку элементов в обратном порядке
    for(int i=2; i<integers.length; i++){
    
		//проверяем на четность все элементы из тройки
        String res1 = integers[i-2] % 2 == 0 ? "even" : "odd";
        String res2 = integers[i-1] % 2 == 0 ? "even" : "odd";
        String res3 = integers[i]   % 2 == 0 ? "even" : "odd";
      
		/*System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
		System.out.println("-----------");*/
      
		if (res1.equals(res2) && res1.equals(res3)) 
			//если все 3 числа - однотипные, переходим к следующей тройке
			continue; 
		else {
			//здесь осталось лишь найти выделяющийся элемент
			if (res1.equals(res2))
				res = integers[i];
			else if   (res1.equals(res3))
				res = integers[i-1];
			else if   (res2.equals(res3))
				res = integers[i-2];
	  
			break;
		}
      
    }
    return res;

  }
}
