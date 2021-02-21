package nbp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        System.out.println("Podaj tabelę: [a/b/c]");
        String table = sc.nextLine();

        LocalDate localDateStart, localDateEnd;

        do {
            System.out.println("Podaj datę [start]:");
            localDateStart = LocalDate.parse(sc.nextLine(), formatter);

            System.out.println("Podaj datę [koniec]:");
            localDateEnd = LocalDate.parse(sc.nextLine(), formatter);
        } while (localDateStart.isAfter(localDateEnd) && localDateEnd.isBefore(LocalDate.now()));

        System.out.println("Podaj kod waluty: [opcjonalne]");
        String kodWaluty = sc.nextLine();

        HttpRequest request;

        if (kodWaluty.isEmpty()) {
            request = HttpRequest.newBuilder().GET()
                    .uri(
                            URI.create("http://api.nbp.pl/api/exchangerates/tables/{table}/{startDate}/{endDate}/"
                                    .replace("{table}", table)
                                    .replace("{startDate}", localDateStart.format(formatter))
                                    .replace("{endDate}", localDateEnd.format(formatter))
                            ))
                    .header("Accept", "application/json")
                    .build();
        } else {
            request = HttpRequest.newBuilder().GET()
                    .uri(
                            URI.create("http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/"
                                    .replace("{table}", table)
                                    .replace("{code}", kodWaluty)
                                    .replace("{startDate}", localDateStart.format(formatter))
                                    .replace("{endDate}", localDateEnd.format(formatter))
                            ))
                    .header("Accept", "application/json")
                    .build();
        }

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseJson = response.body();

            if (kodWaluty.isEmpty()) {
                List<ExchangeTable> rateTables = objectMapper.readValue(responseJson, new TypeReference<List<ExchangeTable>>() {
                });

                rateTables.forEach(
                        System.out::println
                );
            } else {
                CurrencyTable rateTable = objectMapper.readValue(responseJson, CurrencyTable.class);
                System.out.println(rateTable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

