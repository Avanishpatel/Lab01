import javax.json.JsonArray;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter your choice of Operation : ");
        System.out.println("(1) to insert data into database.");
        System.out.println("(2) to get Maximum price of stock between any date range.");
        System.out.println("(3) to get Minimum price of stock between any date range.");
        System.out.println("(4) to get Volume of stock between any date range.");

        int userChoice = scanner.nextInt();

        switch (userChoice) {

            case 1:

                JsonArray jsonArray = ParseJSON.parseJSON();
                InsertData.insertJSONtoDB(jsonArray);
                break;
            case 2:

                System.out.println("Enter the name of stock : ");
                String stockName = scanner.next();
                System.out.println("Enter the start date in format YYYY-MM-DD :");
                String startDate = scanner.next();
                System.out.println("Enter the end date in format YYYY-MM-DD :");
                String endDate = scanner.next();


                double maxValue = ExecuteQuery.getMaxPriceBetweenRangeforStock(stockName, startDate, endDate);
                System.out.println("Maximum value of " + stockName + " between " + startDate + " and " + endDate + " is " + maxValue);

                break;
            case 3:

                System.out.println("Enter the name of stock : ");
                String stockName1 = scanner.next();
                System.out.println("Enter the start date in format YYYY-MM-DD :");
                String startDate1 = scanner.next();
                System.out.println("Enter the end date in format YYYY-MM-DD :");
                String endDate1 = scanner.next();


                double minValue = ExecuteQuery.getMinPriceBetweenRangeforStock(stockName1, startDate1, endDate1);
                System.out.println("Min value of " + stockName1 + " between " + startDate1 + " and " + endDate1 + " is " + minValue);
                break;
            case 4:

                System.out.println("Enter the start date in format YYYY-MM-DD :");
                String startDate2 = scanner.next();
                System.out.println("Enter the end date in format YYYY-MM-DD :");
                String endDate2 = scanner.next();
                long totalVolume = ExecuteQuery.totalValueOfStockInRange(startDate2, endDate2);
                System.out.println("Total volume of trade :" + totalVolume);
                break;

        }

    }


}




