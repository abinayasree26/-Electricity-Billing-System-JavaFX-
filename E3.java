import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

abstract class Customer {
    String name;

    public Customer(String n1) {
        name = n1;
    }

    public abstract double calculateBill(double consumedUnits);

    public String getName() {
        return name;
    }
}

class ResidentialCustomer extends Customer {
    public ResidentialCustomer(String name) {
        super(name);
    }

    public static final double COST_PER_UNIT = 0.85;

    public  double calculateBill(double consumedUnits) {
        return consumedUnits * COST_PER_UNIT; 
    }
}

public class E3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electricity Billing System");

        GridPane g1= new GridPane();
        g1.setPadding(new Insets(20, 20, 20, 20));
        g1.setVgap(10);
        g1.setHgap(10);

        Label l1 = new Label("Customer Name:");
        TextField t1 = new TextField();
        GridPane.setConstraints(l1, 0, 0);
        GridPane.setConstraints(t1, 1, 0);

        Label l2 = new Label("Consumed Units:");
        TextField t2 = new TextField();
        GridPane.setConstraints(l2, 0, 1);
        GridPane.setConstraints(t2, 1, 1);

        Button b1 = new Button("Calculate Bill");
        GridPane.setConstraints(b1, 0, 2);

        Label l = new Label("Bill Amount:");
        Label r1 = new Label();
        GridPane.setConstraints(l, 0, 3);
        GridPane.setConstraints(r1, 1, 3);

        b1.setOnAction(e -> {
            try {
                String customerName = t1.getText();
                double consumedUnits = Double.parseDouble(t2.getText());

                Customer customer = new ResidentialCustomer(customerName);
                double billAmount = customer.calculateBill(consumedUnits);

                r1.setText(String.format("$%.2f", billAmount));
            } catch (NumberFormatException ex) {
                r1.setText("Invalid input");
            }
        });

        g1.getChildren().addAll(l1,t1, l2,t2,b1,l,r1);

        Scene s1 = new Scene(g1, 400, 200);
        primaryStage.setScene(s1);
        primaryStage.show();
    }
}