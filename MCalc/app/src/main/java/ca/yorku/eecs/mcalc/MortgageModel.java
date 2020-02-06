package ca.yorku.eecs.mcalc;

public class MortgageModel {
    private double principle;
    private int amortization;
    private double interest;

    public MortgageModel(String p, String a, String i) {

        this.principle = Double.parseDouble(p);
        this.amortization = Integer.parseInt(a) * 12;
        this.interest = (Double.parseDouble(i) / 100) / 12;
    }

    public String getPayment() {
        double payment = (this.interest * this.principle) / (1 - Math.pow((1 + this.interest), (-1*this.amortization)));
        String result = "$" + String.format("%,.2f", payment);
        return result;
    }

    public static void main(String[] args) {
        MortgageModel myModel = new MortgageModel("700000", "25", "2.75");
        System.out.println(myModel.getPayment());

        myModel = new MortgageModel("300000", "20", "4.50");
        System.out.println(myModel.getPayment());
    }
}