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

    double sum = 0;
    boolean operatorChosen = false;
    char currentOperator = 'a';

    Label answer;
    StringBuilder firstOp = new StringBuilder();

    StringBuilder secondOp = new StringBuilder();
    StringBuilder fullString = new StringBuilder();

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
                        handleSetUp(number);

                    }
                });

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
                    fullString.append(currentOperator);
                    answer.setText(fullString.toString());
                }
            });
            grid.add(button, 3, r);
        }

        Button zero = new Button(0 + "");
        zero.setMinSize(50,50);
        zero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    handleSetUp(0);
            }
        });
        grid.add(zero, 0, 3);

        Button c = new Button( "C");
        c.setMinSize(50,50);

        c.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                operatorChosen = false;
                sum = 0;
                answer.setText(sum + "");
                fullString = new StringBuilder();
                firstOp = new StringBuilder();
                secondOp = new StringBuilder();

            }
        });
        grid.add(c, 1, 3);

        Button equals = new Button("=");
        equals.setMinSize(50,50);
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleOperator(currentOperator);
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
                fullString.append("/");
                answer.setText(fullString.toString());
            }
        });
        grid.add(division, 3, 3);

        Button log = new Button("log10");
        log.setMinSize(50,50);
        log.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double logSum = Math.log10(Double.parseDouble(firstOp.toString()));

                firstOp = new StringBuilder();
                fullString = new StringBuilder();
                answer.setText(logSum + "");
            }
        });

        grid.add(log, 0, 4);

        VBox calc = new VBox();

        answer = new Label();
        answer.setText("0");
        calc.getChildren().addAll(answer, grid);
        stage.setScene(new Scene(calc));
        stage.show();
    }

    public void handleOperator(char currentOperator)
    {
        if (currentOperator == '+')
            sum = Double.parseDouble(firstOp.toString()) + Double.parseDouble(secondOp.toString());
        else if (currentOperator == '-')
            sum = Double.parseDouble(firstOp.toString()) - Double.parseDouble(secondOp.toString());
        else if (currentOperator == '/') {
            if (Double.parseDouble(secondOp.toString()) == 0)
            {
                answer.setText("error");
                firstOp = new StringBuilder();
                secondOp = new StringBuilder();
                fullString = new StringBuilder();
                operatorChosen = false;
                return;
            }
            sum = Double.parseDouble(firstOp.toString()) / Double.parseDouble(secondOp.toString());
        }
        else if (currentOperator == 'X')
            sum = Double.parseDouble(firstOp.toString()) * Double.parseDouble(secondOp.toString());
        answer.setText(sum + "");
        firstOp = new StringBuilder();
        secondOp = new StringBuilder();
        fullString = new StringBuilder();
        operatorChosen = false;
    }

    public void handleSetUp(int number)
    {
        if (operatorChosen)
        {
            secondOp.append(number);
            fullString.append(number);

        }
        else
        {
            firstOp.append(number);
            fullString.append(number);

        }
        answer.setText(fullString.toString());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
