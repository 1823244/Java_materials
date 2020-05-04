//https://javarush.ru/groups/posts/589-patternih-i-singleton--dlja-vsekh-kto-vpervihe-s-nimi-stolknulsja

public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;
        private LazyInitializedSingleton(){}
        public static LazyInitializedSingleton getInstance(){ // #3
        if(instance == null){		//если объект еще не создан
            instance = new LazyInitializedSingleton();	//создать новый объект
        }
        return instance;		// вернуть ранее созданный объект
    }
}