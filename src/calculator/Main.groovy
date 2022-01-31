package calculator

import org.junit.runner.RunWith

class Main {
    static void main(String[] args){
        Calculator calculator = new Calculator()
        assert calculator.add(5,5) ==10
        assert calculator.subtract(7,2) ==5
        assert calculator.multiply(3,3) ==9
        assert calculator.divide(10,3)==3.3333333f

        try {
            calculator.divide(9,0)
        }catch (RuntimeException e){
            assert e.getMessage() == "Cannot divide by zero"
        }
    }
}
