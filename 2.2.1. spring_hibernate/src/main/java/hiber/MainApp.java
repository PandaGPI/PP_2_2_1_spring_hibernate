package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Audi", 500);
        Car car2 = new Car("BMW", 325);
        Car car3 = new Car("Lada", 1110);
        Car car4 = new Car("Suzuki", 5);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user1, null);
        userService.add(user2, car2);
        userService.add(user3, car3);
        userService.add(user4, car4);


//        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

        List<User> usr = userService.getUserByCar(car1);
        for (User user : usr) {
            System.out.println(user);
        }

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }

        context.close();
    }
}
