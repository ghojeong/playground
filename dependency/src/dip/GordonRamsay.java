package dip;

public class GordonRamsay {
    public Pizza createPizza() {
        System.out.println("고든 램지가 피자를 만듭니다.");
        return new Pizza();
    }

    public Pasta createPasta() {
        System.out.println("고든 램지가 파스타를 만듭니다.");
        return new Pasta();
    }

    public Steak createSteak() {
        System.out.println("고든 램지가 스테이크를 만듭니다.");
        return new Steak();
    }
}
