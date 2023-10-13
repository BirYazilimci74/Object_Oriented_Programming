package tr.edu.maltepe.oop;

public class Prof
{
    String name;
    int id;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void teaches()
    {
        System.out.print(" Teached");
    }

}
