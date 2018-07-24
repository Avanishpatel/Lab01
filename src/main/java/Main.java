import javax.json.JsonArray;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter your choice of Operation : ");
        System.out.println("(1) to insert data into database.");
        System.out.println("(2) to get Maximum price of stock on particular day.");
        System.out.println("(3) to get Minimum price of stock on particular day.");
        System.out.println("(4) to get Total Volume of stock on particular day.");

        int userChoice = scanner.nextInt();

        switch (userChoice) {

            case 1:

                JsonArray jsonArray = ParseJSON.parseJSON();
                InsertData.insertJSONtoDB(jsonArray);
                break;
            case 2:

                System.out.println("Enter the name of stock : ");
                String stockName = scanner.next();
                System.out.println("Enter the date in format YYYY-MM-DD :");
                String date = scanner.next();


                double maxValue = ExecuteQuery.getMaxPriceOfStock(stockName, date);
                System.out.println("Maximum value of " + stockName + " on " + date + " is " + maxValue);

                break;
            case 3:

                System.out.println("Enter the name of stock : ");
                String stockName1 = scanner.next();
                System.out.println("Enter the start date in format YYYY-MM-DD :");
                String date1 = scanner.next();


                double minValue = ExecuteQuery.getMinPriceofStock(stockName1, date1);
                System.out.println("Minimum value of " + stockName1 + " on " + date1 + " is " + minValue);
                break;

            case 4:

                System.out.println("Enter the stock name :");
                String stockName2 = scanner.next();
                System.out.println("Enter the end date in format YYYY-MM-DD :");
                String date2 = scanner.next();
                long totalVolume = ExecuteQuery.totalVolumeOfTrade(stockName2, date2);
                System.out.println("Total volume of trade on "+ date2 + " is " + totalVolume);
                break;

        }

    }


}




