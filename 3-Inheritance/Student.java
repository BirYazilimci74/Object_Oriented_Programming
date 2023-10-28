package tr.edu.maltepe.oop;

import java.util.function.Predicate;

public class Student extends Person
{
    public Student(String name, int age)
    {
        super(name, age);
    }

    public void listen(Professor professor, String topic)
    {
        String text = this.getName() + " listen " + topic + " from " + professor.getName();
        System.out.println(text);
    }

    public void tekeNotes(String topic)
    {
        String text = this.getName() + " takes notes of " + topic + " class.";
        System.out.println(text);
    }
}
