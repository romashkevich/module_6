package users.service.dto;

import java.util.Objects;

public class AdressDto {
    private int id;
    private String country;
    private String city;
    private String street;
    private int strNum;
    private int apart;

    @Override
    public String toString() {
        return "AdressDto{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", strNum=" + strNum +
                ", apart=" + apart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressDto adressDto = (AdressDto) o;
        return id == adressDto.id && strNum == adressDto.strNum && apart == adressDto.apart && Objects.equals(country, adressDto.country) && Objects.equals(city, adressDto.city) && Objects.equals(street, adressDto.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, strNum, apart);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStrNum() {
        return strNum;
    }

    public void setStrNum(int strNum) {
        this.strNum = strNum;
    }

    public int getApart() {
        return apart;
    }

    public void setApart(int apart) {
        this.apart = apart;
    }
}
