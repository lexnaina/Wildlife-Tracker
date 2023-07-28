import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }

  public static void main(String[] args) {

    port(getHerokuAssignedPort());
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route for adding an endangeredAnimal sighting
    post("/endangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      String latLong = request.queryParams("latLong");
      String rangerName = request.queryParams("rangerName");
      Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
      sighting.save();
      // model.put("sighting", sighting);
      // model.put("animals", EndangeredAnimal.all());
      String animal = EndangeredAnimal.find(animalIdSelected).getName();
      int animalId = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      EndangeredAnimal updatedAnimal = EndangeredAnimal.find(animalId);
      updatedAnimal.update(health, age);
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


// route for adding non-endangered animal sighting
    post("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
      String latLong = request.queryParams("latLong");
      String rangerName = request.queryParams("rangerName");
      Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", Animal.all());
      String animal = Animal.find(animalIdSelected).getName();
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route when clicking on "Add Animal to System"
    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route for adding new animal form
    post("/animal/new", (request, response) -> {
      boolean endangered = request.queryParams("endangered")!=null;
      if (endangered) {
        String name = request.queryParams("name");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name);
        endangeredAnimal.save();
      } else {
        String name = request.queryParams("name");
        Animal animal = new Animal(name);
        animal.save();
      }
      response.redirect("/");
        return null;
      });

//route when you click on a non-endangered animal
    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route when you click on an endangeredAnimal
    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route when user clicks "Post Sighting"
    get("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route when user clicks "All Animals" or "View Animals"
    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
