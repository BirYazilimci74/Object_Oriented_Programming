package tr.edu.maltepe.oop;

public class Professor extends Person
{
    private String department;

    public Professor(String name, int age, String department)
    {
        super(name, age);
        this.department = department;
    }
    public void teachs(String topic)
    {
        String massage = this.getName() + " teachs " + topic;
        System.out.println(massage);
    }

}
