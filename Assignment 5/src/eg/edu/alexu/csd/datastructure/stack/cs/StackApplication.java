package eg.edu.alexu.csd.datastructure.stack.cs;
/**
 * 
 * @author katoos
 *
 */
public class StackApplication implements IExpressionEvaluator {
	/**
	 * 
	 * @param temp 
	 * @return true if the entered parameter is ( + or - or * or / ) and false other wise
	 */
	public boolean isOperator(char temp) {
		if (temp == '+' || temp == '-' || temp == '*' || temp == '/') {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * @param expression in string type 
	 * @return Postfix form for the entered string which was in infix form
	 */
	public String infixToPostfix(String expression) {
		StringBuilder Postfix = new StringBuilder();
		
		/**
		 * @param to calculate number of " ( "
		 */
		int openParenthesesNum = 0;
		/**
		 * @param to calculate number of " ) "
		 */
		int closeParenthesesNum = 0 ;
		/**
		 * @param to calculate number of operands
		 */
		int operandsNum = 0;
		/**
		 * @param to calculate number of operators
		 */
		int operatorsNum = 0;
		/**
		 * check if expression is empty or not
		 */
		if (expression == null || expression == "") {
			throw new RuntimeException("No Input");
		}
		else {
			LinkedListedStack B = new LinkedListedStack();
			for (int i=0; i<expression.length(); i++) {
				if (Character.isAlphabetic(expression.charAt(i))) {
					operandsNum++;
					Postfix.append(expression.charAt(i));
					Postfix.append(" ");
					i++;
					try {
						if (Character.isAlphabetic(expression.charAt(i))) {
							throw new RuntimeException("Wrong Input");
						}
					}
					catch (Exception e) {
						e.getMessage();
					}
					i--;
				}
				else if (Character.isDigit(expression.charAt(i))) {
					StringBuilder semiOperand = new StringBuilder();
					semiOperand.append(expression.charAt(i));
					i++;
					String finalOperand;
					try {
						while (Character.isDigit(expression.charAt(i)) && i<expression.length()) {
							semiOperand.append(expression.charAt(i));
							finalOperand = semiOperand.toString();
							i++;						
						}
					}
					catch (Exception  e) {
						e.getMessage();
					}
					i--;
					finalOperand = semiOperand.toString();
					operandsNum++;
					Postfix.append(finalOperand);
					Postfix.append(" ");
				}
				else if (expression.charAt(i) == ' ') {
					continue;
				}
				else if (isOperator(expression.charAt(i)) && B.isEmpty()) {
					operatorsNum++;
					B.push(expression.charAt(i));
				}
				else if (((expression.charAt(i) == '*' || expression.charAt(i) == '/') && !B.isEmpty())) {
					operatorsNum++;
					try {
						if (B.peek().equals('+') || B.peek().equals('-')) {
							B.push(expression.charAt(i));
						}
						else if (B.peek().equals('*') || B.peek().equals('/')) {
							while (B.peek().equals('*') || B.peek().equals('/')) {
								Postfix.append(B.pop());
								Postfix.append(" ");
							}
							B.push(expression.charAt(i));
						}
					}
					catch (Exception e) {
						e.getMessage();
					}
				}
				else if (((expression.charAt(i) == '+' || expression.charAt(i) == '-') && !B.isEmpty())) {
					operatorsNum++;
					try {
						while(B.peek().equals('+') || B.peek().equals('-') || B.peek().equals('*') || B.peek().equals('/') ) {
							Postfix.append(B.pop());
							Postfix.append(" ");
						}
					}
					catch (Exception e){
						e.getMessage();
					}
					B.push(expression.charAt(i));
				}
				else if (expression.charAt(i) == '(') {
					openParenthesesNum++;
					B.push(expression.charAt(i));
				}
				else if (expression.charAt(i) == ')') {
					closeParenthesesNum++;
					if (!B.isEmpty()) {
						try {
							while (!B.isEmpty() && !(B.peek().equals('('))) {
								Postfix.append(B.pop());
								Postfix.append(" ");
							}
							if (B.peek().equals('(')) {
								B.pop();
							}
							else {
								throw new RuntimeException("Wrong Input");
							}
						}
						catch (Exception e) {
							e.getMessage();
						}
					}
					else {
						throw new RuntimeException();
					}
				}	
				else {
					throw new RuntimeException("Wrong Input");
				}
			}
			while (!(B.isEmpty())) {
				Postfix.append(B.pop());
				if(!(B.isEmpty())) {
					Postfix.append(" ");
				}
			}
			if (openParenthesesNum != closeParenthesesNum) {
				throw new RuntimeException("Wrong Input");
			}
			if ((operandsNum-1) != operatorsNum) {
				throw new RuntimeException("Wrong Input");				
			}
		}
		return Postfix.toString();
	}
	/**
	 *
	 * @param expression in postfix form to evaluate its value
	 * 
	 * @return value after calculating 
	 */
	public int evaluate(String expression) {
		if (expression == null || expression == "") {
			throw new RuntimeException("No Input");
		}
		else {
			LinkedListedStack A = new LinkedListedStack();
			String [] outSpacedExpression = expression.split(" ");
			for(int i=0; i<outSpacedExpression.length; i++) {
				if (isOperator(outSpacedExpression[i].charAt(0))) {
					
					float num1, num2;	
					switch(outSpacedExpression[i].charAt(0)) {
					case '+':
						num2 = (float)A.pop();
						num1 = (float)A.pop();
						A.push(num1 + num2);
						break;
					case '-':
						num2 = (float)A.pop();
						num1 = (float)A.pop();
						A.push(num1 - num2);						
						break;
					case '*':
						num2 = (float)A.pop();
						num1 = (float)A.pop();
						A.push(num1 * num2);
						break;
					case '/':
						num2 = (float)A.pop();
						num1 = (float)A.pop();
						if (num2 == 0) {
							throw new ArithmeticException("You Can't divide by 0, Sorry :(");
						}
						else {
							A.push(num1 / num2);	
						}
						break;
					}
				}
				else {
					if (Character.isDigit(outSpacedExpression[i].charAt(0))) {
						A.push(Float.parseFloat(outSpacedExpression[i]));
					}
					else {
						throw new RuntimeException("Wrong Input");
					}
					
				}
			}
			return Math.round((float)A.peek());
		}
		
	}
		
}
