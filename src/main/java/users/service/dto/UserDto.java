package users.service.dto;

import java.util.Objects;

public class UserDto {
    private long id;
    private String login;
    private String password;
    private AdressDto adress;
    private String telNum;
    private String firstName;
    private String lastName;
    private SexDto sex;
    private String email;
    private RoleDto roleDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id && Objects.equals(login, userDto.login) && Objects.equals(password, userDto.password) && Objects.equals(adress, userDto.adress) && Objects.equals(telNum, userDto.telNum) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && sex == userDto.sex && Objects.equals(email, userDto.email) && roleDto == userDto.roleDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, adress, telNum, firstName, lastName, sex, email, roleDto);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", adress=" + adress +
                ", telNum='" + telNum + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", roleDto=" + roleDto +
                '}';
    }

    public RoleDto getRoleDtoClass() {
        return roleDto;
    }

    public String getRoleDto() {
        return roleDto.name();
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdressDto getAdress() {
        return adress;
    }

    public void setAdress(AdressDto adress) {
        this.adress = adress;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SexDto getSexDtoClass() {
        return sex;
    }

    public void setSexDto(SexDto sex) {
        this.sex = sex;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
