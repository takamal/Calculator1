package sample.application.calculator;

public class Ac extends AbstractLogic {

	@Override
	public void doSomething(CalculatorActivity ca) {
		ca.strTemp = "";
		ca.strResult = "0";
		ca.operator = 0;

//		ca.showNumber(ca.strTemp);
	}

}