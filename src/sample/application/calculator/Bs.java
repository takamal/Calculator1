package sample.application.calculator;

public class Bs extends AbstractLogic {

	@Override
	public void doFunction(CalculatorActivity ca) {
		if(ca.strTemp.length() == 0){
			return;
		}else{
			ca.strTemp = ca.strTemp.substring(0,ca.strTemp.length()-1);
		}
//		ca.showNumber(ca.strTemp);
	}

}