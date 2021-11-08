import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;


public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> currencyCodes = new HashMap<>();

        //Add currency codes
        currencyCodes.put(1, "USD");
        currencyCodes.put(2, "MXN");
        currencyCodes.put(3, "EUR");
        currencyCodes.put(4, "AUD");
        currencyCodes.put(5, "CAD");
        currencyCodes.put(6, "PLN");

        String fromCode, toCode;

        float amount;

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the currency converter");

        System.out.println("Currency converter FROM?");
        System.out.println("1 USD (US Dollar) \t 2 MXN (Mexican peso) \t 3 EUR (Euro) \t 4 AUD (Australian dollar) \t 5 CAD (Canadian dollar) \t 6 PLN (Polish zloty)");
        fromCode = currencyCodes.get(sc.nextInt());

        System.out.println("Currency converter TO?");
        System.out.println("1 USD (US Dollar) \t 2 MXN (Mexican peso) \t 3 EUR (Euro) \t 4 AUD (Australian dollar) \t 5 CAD (Canadian dollar) \t 6 PLN (Polish zloty)");
        toCode = currencyCodes.get(sc.nextInt());

        System.out.println("Amount you wish to convert?");
        amount = sc.nextFloat();

        sendHttpGETRequest(fromCode, toCode, amount);


        System.out.println("Thanks for using the currency converter");
    }

    private static void sendHttpGETRequest(String fromCode, String toCode, float amount) throws IOException {

        DecimalFormat f = new DecimalFormat("00.00");

        String GET_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=2bf81dbaa53e74ac895b03c4172b7f80&symbols=USD,AUD,CAD,PLN,MXN&format=1";
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int rensponseCode = httpURLConnection.getResponseCode();

        if(rensponseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }in.close();

            Gson gson = new Gson();

            Currency currency = gson.fromJson(response.toString(), Currency.class);

            float converterfactor = currency.connverter(fromCode, toCode);

            System.out.println(f.format(amount) + " " + fromCode + " = " + f.format((amount*converterfactor)) + " " + toCode);
        }
        else {
            System.out.println("GET request failed");
        }

    }
}
