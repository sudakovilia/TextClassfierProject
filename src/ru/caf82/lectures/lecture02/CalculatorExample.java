package ru.caf82.lectures.lecture02;

public class CalculatorExample {
        private int x;
        private int y;

        CalculatorExample(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSubst() {
            return x-y;
        }

        public float getSum() {
            return (float) x + y;
        }

    public static void main(String[] args) {
        CalculatorExample calc = new CalculatorExample(5, 2);
        System.out.println(calc.getSum());
        System.out.println(calc.getSubst());

        //todo create new calculator, add abs function from Math
    }
}
