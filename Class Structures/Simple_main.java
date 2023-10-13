package tr.edu.maltepe.oop;
public  class Simple_main {

 public static void main(String []args)
 {
       Prof ensar = new Prof();
       Student yahya = new Student();
       Student enver = new Student();

       ensar.setName("Ensar Gül");
       yahya.setName("Yahya");
       enver.setName("Enver");

       yahya.setId(123);

       System.out.println(yahya.getId());
       System.out.println(ensar.getName());

       System.out.print(ensar.getName());
       ensar.teaches();
       System.out.print(", " + yahya.getName());
       System.out.print(" and " + enver.getName());
       yahya.learns();

 }
}


