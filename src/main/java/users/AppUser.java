package users;

import books.service.ServiceBookImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import users.service.ServiceUser;
import users.service.ServiceUserImpl;
import users.service.dto.AdressDto;
import users.service.dto.RoleDto;
import users.service.dto.SexDto;
import users.service.dto.UserDto;

import java.util.List;
import java.util.Scanner;


public class AppUser {

    private static final Logger root = LogManager.getRootLogger();

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServiceUser serviceUser = context.getBean("serviceUser", ServiceUserImpl.class);
        boolean process = true;
        while (process) {
            System.out.println("\n\t conditon : all, id, create, update, delete, email, lastname, count, login or exit (for exit program)");
            Scanner in = new Scanner(System.in);
            String input = in.next().toLowerCase().trim();
            try {
                switch (input) {
                    case "all":
                        System.out.println("list users: ");
                        List<UserDto> userDtoList = serviceUser.getAllUserDto();
                        if (!userDtoList.isEmpty()) {
                            userDtoList.forEach(System.out::println);
                        }
                        break;

                    case "id":
                        System.out.println("please input user id");
                        Scanner in1 = new Scanner(System.in);
                        Long id = in1.nextLong();
                        System.out.println(serviceUser.getUserDtoById(id));
                        break;

                    case "create":
                        UserDto userDto4 = getUserDto();
                        serviceUser.createUserDto(userDto4);
                        break;

                    case "update":
                        System.out.println("please input user's login");
                        Scanner login = new Scanner(System.in);//login
                        UserDto userDto4Update = serviceUser.getUserDtoByLolin(login.nextLine());//
                        serviceUser.updateUserDto(setUserDto(userDto4Update));
                        break;

                    case "delete":
                        System.out.println("please input user id for delete");
                        Scanner in2 = new Scanner(System.in);
                        serviceUser.deleteUserDto(in2.nextLong());
                        break;

                    case "email":
                        System.out.println("please input user's email(for example: xxxx@yy.com)");
                        Scanner in3 = new Scanner(System.in);
                        UserDto userDto1 = serviceUser.getUserDtoByEmail(in3.nextLine());
                        System.out.println(userDto1);

                        break;

                    case "lastname":
                        System.out.println("please input user's lastname");
                        Scanner in4 = new Scanner(System.in);
                        List<UserDto> userDtoList1 = serviceUser.getUsersDtoByLastName(in4.next());
                        if (!userDtoList1.isEmpty()) {
                            userDtoList1.forEach(System.out::println);
                        }
                        break;

                    case "count":
                        System.out.print("count users in db: ");
                        System.out.println(serviceUser.countAllUsersDto());
                        break;

                    case "login":
                        System.out.println("please input user's login");
                        Scanner login1 = new Scanner(System.in);
                        UserDto userDto2 = serviceUser.getUserDtoByLolin(login1.nextLine());
                        System.out.println(userDto2);
                        break;


                    case "exit":
                        System.out.println("program is over");
                        process = false;
                        break;

                    default:
                        System.out.println("invalid command, try again");
                }
            } catch (Exception e) {
                root.error(e);
            }
        }

    }

    private static UserDto getUserDto() {

        UserDto userDto = new UserDto();
        System.out.println("input user data");

        System.out.println("login");
        Scanner login = new Scanner(System.in);
        userDto.setLogin(login.nextLine());

        System.out.println("password");
        Scanner password = new Scanner(System.in);
        userDto.setPassword(password.nextLine());

        System.out.println("telNumber");
        Scanner tel = new Scanner(System.in);
        userDto.setTelNum(tel.nextLine());

        System.out.println("firstName");
        Scanner firstName = new Scanner(System.in);
        userDto.setFirstName(firstName.nextLine());

        System.out.println("lastName");
        Scanner lastName = new Scanner(System.in);
        userDto.setLastName(lastName.nextLine());

        System.out.println("email");
        Scanner email = new Scanner(System.in);
        userDto.setEmail(email.nextLine());

        System.out.println("Sex(for choice: MAN WOMAN)");
        Scanner sex = new Scanner(System.in);
        userDto.setSexDto(setSexDtoApp(sex.nextLine()));

        System.out.println("Role(for choice: manager,admin,customer)");
        Scanner role = new Scanner(System.in);
        userDto.setRoleDto(setRoleDtoApp(role.nextLine()));

        System.out.println("Adress");
        System.out.println("inter adress data");
        userDto.setAdress(setAdressDtoApp());

        return userDto;
    }

    private static UserDto setUserDto(UserDto userDto1) {

        UserDto userDto = new UserDto();
        System.out.println("input user data");

        userDto.setLogin(userDto1.getLogin());

        System.out.println("password");
        Scanner password = new Scanner(System.in);
        userDto.setPassword(password.nextLine());

        System.out.println("telNumber");
        Scanner tel = new Scanner(System.in);
        userDto.setTelNum(tel.nextLine());

        System.out.println("firstName");
        Scanner firstName = new Scanner(System.in);
        userDto.setFirstName(firstName.nextLine());

        System.out.println("lastName");
        Scanner lastName = new Scanner(System.in);
        userDto.setLastName(lastName.nextLine());

        userDto.setEmail(userDto1.getEmail());

        System.out.println("Sex(for choice: MAN WOMAN)");
        Scanner sex = new Scanner(System.in);
        userDto.setSexDto(setSexDtoApp(sex.nextLine()));

        System.out.println("Role(for choice: manager,admin,customer)");
        Scanner role = new Scanner(System.in);
        userDto.setRoleDto(setRoleDtoApp(role.nextLine()));

        System.out.println("Adress");
        System.out.println("inter adress data");
        userDto.setAdress(setAdressDtoApp());

        return userDto;
    }

    private static SexDto setSexDtoApp(String sexDto) {
        SexDto sexDto1 = sexDto.toUpperCase() == "MAN" ? SexDto.MAN : SexDto.WOMAN;
        return sexDto1;
    }

    private static RoleDto setRoleDtoApp(String roleDto) {
        RoleDto roleDto1;
        switch (roleDto.toUpperCase()) {
            case ("ADMIN"):
                roleDto1 = RoleDto.ADMIN;
                break;
            case ("MANAGER"):
                roleDto1 = RoleDto.MNGR;
                break;
            default:
                roleDto1 = RoleDto.CUST;
                break;
        }
        return roleDto1;
    }

    private static AdressDto setAdressDtoApp() {

        AdressDto adressDto = new AdressDto();

        System.out.println("country");
        Scanner country = new Scanner(System.in);
        adressDto.setCountry(country.nextLine());

        System.out.println("city");
        Scanner city = new Scanner(System.in);
        adressDto.setCity(city.nextLine());

        System.out.println("street");
        Scanner street = new Scanner(System.in);
        adressDto.setStreet(street.nextLine());

        System.out.println("street number");
        Scanner strNum = new Scanner(System.in);
        adressDto.setStrNum(strNum.nextInt());

        System.out.println("apartment");
        Scanner apart = new Scanner(System.in);
        adressDto.setApart(apart.nextInt());

        return adressDto;
    }


}




