package sample.application.calculator;

import sample.application.calculator.CalculatorActivity;
import sample.application.calculator.FunctionLogic;

public abstract class AbstractLogic implements FunctionLogic{

	@Override
	public void doFunction(CalculatorActivity ca) {
		this.doSomething(ca);
		ca.showNumber(ca.strTemp);
	}
	
	public abstract void doSomething(CalculatorActivity ca);
	
}
