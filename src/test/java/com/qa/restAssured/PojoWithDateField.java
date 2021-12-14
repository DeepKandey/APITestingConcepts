package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PojoWithDateField {

  @Test
  public void pojoToJson1() throws JsonProcessingException {
    Booking pojoToJson = new Booking();
    pojoToJson.setFirstName("Deepak");
    pojoToJson.setLastName("Rai");

    // default format is yyyy-MM-dd
    LocalDate currentDate = LocalDate.now();

    // Get 10 days from current date
    pojoToJson.setCheckin(currentDate.plusDays(10).toString());
    // Get 20 days from current date
    pojoToJson.setCheckout(currentDate.plusDays(20).toString());

    // POJO to JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoToJson);
    System.out.println(jsonString);

    // JSON to POJO
    Booking jsonToPojo = objectMapper.readValue(jsonString, Booking.class);
    System.out.println("Passed checkIn date is: " + jsonToPojo.getCheckin());
    System.out.println("Passed checkOut date is: " + jsonToPojo.getCheckout());
  }

  // custom format of a date
  @Test
  public void pojoToJson2() throws JsonProcessingException {
    Booking pojoToJson = new Booking();
    pojoToJson.setFirstName("Deepak");
    pojoToJson.setLastName("Rai");

    // default format is yyyy-MM-dd
    LocalDate currentDate = LocalDate.now();

    // Get 10 days from current date
    pojoToJson.setCheckin(
        currentDate.plusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    // Get 20 days from current date
    pojoToJson.setCheckout(
        currentDate.plusDays(20).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    // POJO to JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoToJson);
    System.out.println(jsonString);

    // JSON to POJO
    Booking jsonToPojo = objectMapper.readValue(jsonString, Booking.class);
    System.out.println("Passed checkIn date is: " + jsonToPojo.getCheckin());
    System.out.println("Passed checkOut date is: " + jsonToPojo.getCheckout());
  }

  // custom format of a date with timestamp
  @Test
  public void pojoToJson3() throws JsonProcessingException {
    Booking pojoToJson = new Booking();
    pojoToJson.setFirstName("Deepak");
    pojoToJson.setLastName("Rai");

    // default format is yyyy-MM-dd
    LocalDateTime currentDate = LocalDateTime.now();

    // Get 10 days from current date
    pojoToJson.setCheckin(
        currentDate.plusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    // Get 20 days from current date
    pojoToJson.setCheckout(
        currentDate.plusDays(20).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

    // POJO to JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoToJson);
    System.out.println(jsonString);

    // JSON to POJO
    Booking jsonToPojo = objectMapper.readValue(jsonString, Booking.class);
    System.out.println("Passed checkIn date is: " + jsonToPojo.getCheckin());
    System.out.println("Passed checkOut date is: " + jsonToPojo.getCheckout());
  }
}

@Getter
@Setter
class Booking {
  private String firstName;
  private String lastName;
  private String checkin;
  private String checkout;
}
