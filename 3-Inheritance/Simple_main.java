package tr.edu.maltepe.oop;

public class Simple_main
{
    public static void main(String []args)
    {
        String topic = "Java";

        Professor ensar = new Professor("EnsarGül",50,"Software Enginering");
        Student yahya = new Student("HarunYahya",20);
        Student enver = new Student("Enver",20);


        ensar.teachs(topic);
        yahya.listen(ensar,topic);
        yahya.tekeNotes(topic);
        enver.listen(ensar,topic);
        enver.tekeNotes(topic);
    }
}
