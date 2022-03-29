Ввести с клавиатуры дату в формате "2013-08-18"
Вывести на экран введенную дату в виде "AUG 18, 2013".
Воспользоваться объектом Date и SimpleDateFormat.
2013-08-18

*/

public class Solution {

    public static void main(String[] args) throws Exception {
        // Обычное создание ридера
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ddd = reader.readLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        Date date = formatter.parse(ddd);
        System.out.println(formatter2.format(date).toUpperCase(Locale.ROOT));
    }
}

## ВНИМАНИЕ!!!
если здесь
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
будет не yyyy, а YYYY, то вернется неправильная дата!!!!
        