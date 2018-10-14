package sample;

import java.util.Arrays;

public class TEST {
    public int[] Arr(int[]  array, int index){
        int[] array2 = new int[array.length-1];

            System.arraycopy(array, 1, array2, 0, array2.length);

         if (index == array.length-1) {
            for (int i = 0; i < array.length-1; i++) {
                array2[i] = array[i];
            }
        }
        else{
            for (int i = 0; i < array.length; i++) {
                if (i > index) {
                    array2[i-1] = array[i];
                }
                if (i == index) {
                    continue;
                }
                if (i < index) {
                    array2[i] = array[i];
                }
            }

        }
        return array2;
    }
  public static void main(String[] args) {
         TEST test = new TEST();
        int []array ={5,2,6,1};
         int ind = 0;
        int []arrrr = test.Arr(array,ind);
      System.out.println(Arrays.toString(arrrr));
//        try{
//        Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\Lab Java EE\\SQLite\\Lab_1_JavaEE.db");
//        Statement stm = con.createStatement();
//        ResultSet rs = stm.executeQuery("select * from CD");
//        while (rs.next() ){
//            System.out.println(rs.getString("dfsdfsdf \"dfsdfsdfs\""+2));
//        }
//        con.close();
//
//    }catch ( Exception e) {
//            System.out.println( "error ");
//        }
//      Scanner sc = new Scanner(System.in);
//      System.out.println("Введите любые 2 слова или фразу: ");
//      System.out.println("xbckj");
//      int fsdf=sc.nextInt();
//      System.out.println("words");
//      String phrase1 = sc.nextLine();
//      System.out.println("words");
//      String phrase2 = sc.nextLine();
//      System.out.println(phrase1 + " "+ fsdf+ phrase2);
    }
}
