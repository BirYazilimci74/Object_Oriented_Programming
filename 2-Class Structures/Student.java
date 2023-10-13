package tr.edu.maltepe.oop;

public class Student
{
    String name;
    String department;
    int id;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDepatrment(String name)
    {
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void learns()
    {
        System.out.print(" Learned");
    }
}
