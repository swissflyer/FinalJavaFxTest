package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    int sum = 0;
    boolean operatorChosen = false;
    char currentOperator = 'a';
    char operator = 'a';
    Label answer;
    String firstOp = "";
    String secondOp = "";
    String fullString = "";

    @Override
    public void start(Stage stage) throws Exception{
        GridPane grid = new GridPane();


        grid.setHgap(5);
        grid.setVgap(5);

        int count = 1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int number = count;
                Button button = new Button(String.valueOf(number));
                button.setMinSize(50,50);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (operatorChosen)
                        {
                            secondOp += number;
                            fullString += number;

                        }
                        else
                        {
                            firstOp += number;
                            fullString += number;

                        }
                        answer.setText(fullString);

                    }
                });
                //button.setMaxSize(20,20);
                grid.add(button, c, r);
                count++;
            }
            char operator = 'a';
            if (r == 0)
                operator = 'X';
            else if (r == 1)
                operator = '-';
            else if (r == 2)
                operator = '+';


            Button button = new Button(operator + "");
            button.setMinSize(50,50);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    currentOperator=  button.getText().charAt(0);
                    operatorChosen = true;
                    fullString += currentOperator;
                    answer.setText(fullString);
                }
            });
            grid.add(button, 3, r);
        }

        Button zero = new Button(0 + "");
        zero.setMinSize(50,50);
        zero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (operatorChosen)
                {
                    if (currentOperator == '/') {
                        answer.setText("Error");
                        fullString = "";
                        firstOp = "";
                        secondOp = "";
                        operatorChosen = false;
                    }

                    else if (currentOperator == 'X')
                        sum = 0;
                }
            }
        });
        grid.add(zero, 0, 3);

        Button C = new Button( "C");
        C.setMinSize(50,50);



        C.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                operatorChosen = false;
                sum = 0;
                answer.setText(sum + "");
                fullString = "";
                firstOp = "";
                secondOp = "";

            }
        });
        grid.add(C, 1, 3);

        Button equals = new Button("=");
        equals.setMinSize(50,50);
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentOperator == '+')
                    sum = Integer.parseInt(firstOp) + Integer.parseInt(secondOp);
                else if (currentOperator == '-')
                    sum = Integer.parseInt(firstOp) - Integer.parseInt(secondOp);
                else if (currentOperator == '/')
                    sum = Integer.parseInt(firstOp) / Integer.parseInt(secondOp);
                else if (currentOperator == 'X')
                    sum = Integer.parseInt(firstOp) * Integer.parseInt(secondOp);
                answer.setText(sum + "");
                firstOp = "";
                secondOp = "";
                fullString = "";
                operatorChosen = false;

            }


        });
        grid.add(equals, 2, 3);

        Button division = new Button("/");
        division.setMinSize(50,50);
        division.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentOperator=  division.getText().charAt(0);
                operatorChosen = true;
                fullString += "/";
            }


        });
        grid.add(division, 3, 3);

        VBox calc = new VBox();

        answer = new Label();
        answer.setText("0");
        calc.getChildren().addAll(answer, grid);
        stage.setScene(new Scene(calc));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
