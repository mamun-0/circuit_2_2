package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField inputResistance;
    @FXML
    TextField inputInductance;
    @FXML
    TextField inputCapacitance;
    @FXML
    TextField inputFrequency;
    @FXML
    TextField inputVoltage;
    @FXML
    LineChart<Number,Number> lineChart;
    Complex impedance;

    public void onClickSubmit(){

        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        for (int i=0;i<180;i++){
        series.getData().add(new XYChart.Data<Number, Number>(i,timeDomain(i)));
        }
        lineChart.getData().add(series);
    }

    public Complex equivalentImpedance(){
        impedance = new Complex(0,0);
        double frequency = Double.parseDouble(inputFrequency.getText());
        double resistance = Double.parseDouble(inputResistance.getText());
        double inductance = Double.parseDouble(inputInductance.getText()); // (0, jwL)
        double capacitance = Double.parseDouble(inputCapacitance.getText()); // (0,1/(jwC))

        this.impedance = impedance.plus(new Complex(resistance,0));
        this.impedance = impedance.plus(new Complex(0,frequency*inductance));
        this.impedance = impedance.plus(new Complex(0,-1/(frequency*capacitance)));
        return this.impedance;
    }
    public double timeDomain(int time){
        double frequency = Double.parseDouble(inputFrequency.getText());
        double voltage = Double.parseDouble(inputVoltage.getText());
        double wt = Math.toRadians(time*frequency);
        double tDomain;
        try {
        tDomain = voltage*Math.cos(wt+equivalentImpedance().phase());
        }catch (Exception e){
            return -1;
        }
        return tDomain;

    }
}
