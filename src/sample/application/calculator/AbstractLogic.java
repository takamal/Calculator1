package sample.application.calculator;



public abstract class AbstractLogic implements FunctionLogic{

	@Override
	public void doFunction(CalculatorActivity ca) {
		this.doSomething(ca);
		ca.showNumber(ca.strTemp);
	}
	
	public abstract void doSomething(CalculatorActivity ca);
	
}
