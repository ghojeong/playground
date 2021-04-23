package circular;

public class Pizza implements Food {
    private final Human chef;

    private Pizza(Human chef) {
        this.chef = chef;
    }

    public static Pizza madeBy(Human chef) {
        Pizza pizza = new Pizza(chef);
        validate(pizza);
        return pizza;
    }

    private static void validate(Pizza pizza) {
        Human chef = pizza.chef;
        try {
            // 본인이 먹을 수 있는 음식을 고객에게 내놓아야한다.
            chef.eat(pizza);
            // 위 부분을 주석 처리하면 정상 작동한다.
        } catch (Exception e) {
            System.out.println("피자를 먹고 셰프가 사망하였습니다.");
            System.out.println("이런 쓰레기 같은 음식은 폐기 되어야 합니다.");
            System.exit(1); // 가게 문을 닫는다. (비정상 종료)
        }

        /**
         * chef 만 피자를 먹어서, 피자가 언전한지 확인하고 싶었음
         * 읭? 근데 셰프가 아니고,
         * Restaurant 에 있는 파이로가 미쳤는지 갑자기 피자를 3판씩 처먹는다.
         * run 코드에 따르면 파이로는 분명 피자를 2판만 먹어야한다.
         * 결국 레스토랑은 예상치 못한 적자에 망하게 되었다.
         * 결론: 개발 못하면, 치킨집을 차려도 망한다.
         *
         * 셰프가 피자를 먹고 죽어서 Exception 이 나오는 것은 큰 문제가 아님. 정상적
         * 원래 자기도 못먹을 걸 파는 사람들은 다 죽어야죠.
         * 진짜 문제는, 위의 abnormal 이 Exception 을 터트리지 않고 실행되는게 문제. 공포. 디버깅 불가능
         *
         * 이걸 해결하려고, IoC 컨테이너가 도입됨
         */
    }

    @Override
    public String toString() {
        return String.format("%s가 만든 피자", chef);
    }
}
