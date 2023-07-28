import org.sql2o.*;

public class DB {
  // public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", null, null);

  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-23-21-246-11.compute-1.amazonaws.com:5432/d6isspi7k46sgs", "ldbgmlsfrumefc",  "9f195ab9c22336597bcebf199bba05cf8215cc0ec369c413b42afad28bf85992");
}
