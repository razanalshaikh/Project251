package jahezli.app;

import java.io.FileNotFoundException;

public class Customer extends User {

    String phone;
    String city;
    String name;
    String password;
    Reservation reserve;
    private double totalPrice = 0;
    private int CVV;
    private String cardExpirationDate;
    private String cardNum;

    public Customer() throws FileNotFoundException {
        super();
    }

    public Customer(String phone, String city, String password, double totalPrice) throws FileNotFoundException {
        super.setPassword(password);
        super.setUserName(phone);
        this.city = city;
        this.totalPrice = totalPrice;
    }

    public Customer(String name, String phone, String city, String password) throws FileNotFoundException {
        this.name = name;
        super.setPassword(password);
        super.setUserName(phone);
        this.city = city;
        this.totalPrice = totalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
 public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Reservation getReserve() {
        return reserve;
    }

    public void setReserve(Reservation reserve) {
        this.reserve = reserve;
    }
    //check method parameter and return

    public void reserve() {

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = totalPrice;
    }

    public paymentMethod makePayment() {
        double total = getTotalPrice();
        while (true) {
            System.out.println("Please choose the pament method you prefer :");
            System.out.println(" ********************************************** ");
            System.out.println("1.CASH");
            System.out.println("2.CREDIT CARD");
            System.out.println(" ********************************************** ");
            int choosenNumberForPymmentMethod = input.nextInt();
            switch (choosenNumberForPymmentMethod) {
                case 1:
                    System.out.println("\t\tPay by cash");
                    System.out.println(" ********************************************** ");

                    System.out.print("Please enter your city :");
                    city = input.next();
                    return makePaymentByCash(total);

                case 2:
                    System.out.println("\t\tPay by credit card");
                    System.out.println(" ********************************************** ");
                    System.out.println("Please enter the credit card information :");
                    System.out.print("A credit card number must have between 13 and 19 digits): \nIt must start with:\n"
                            + "4 for Visa cards\n"
                            + "5 for Master cards\n"
                            + "37 for American Express cards\n"
                            + "6 for Discover cards\n\nCard number : ");
                    cardNum = input.next();
                    while (!Checker.isValidCardNumber(cardNum)) {
                        System.out.println("Invalid Card number ,please enter a valid Card number again :");
                        cardNum = input.next();
                    }

                    System.out.print("Card expiration date in MM/YY format : ");
                    cardExpirationDate = input.next();
                    while (!Checker.isValidExpiryDate(cardExpirationDate)) {
                        System.out.println("Invalid expiration date ,please enter a valid expiration date again :");
                        cardExpirationDate = input.next();
                    }
                    System.out.print("CVV (It should be 3 0r 4 digits): ");
                    CVV = input.nextInt();
                    while (!Checker.isValidCVVNumber(CVV)) {
                        System.out.println("Invalid CVV ,please enter a valid CVV again :");
                        CVV = input.nextInt();
                    }
                    System.out.print("Please enter your location :");
                    city = input.next();
                    creditCard creditCardPayment = new creditCard(total, cardNum, cardExpirationDate, CVV);
                    return makePaymentByCreditCard(total);

                default:
                    break;

            }
        }

    }

    public Cash makePaymentByCash(double totalPrice) {
        Cash cashPayment = new Cash((int) totalPrice);
        return cashPayment;
    }

    public creditCard makePaymentByCreditCard(double total) {
        creditCard creditCardPayment = new creditCard(total, cardNum, cardExpirationDate, CVV);
        return creditCardPayment;

    }

}
