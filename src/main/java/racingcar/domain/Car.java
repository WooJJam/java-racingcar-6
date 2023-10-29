package racingcar.domain;

public class Car {
    private String carName;
    private int position;

    public Car(String carName, int position) {
        this.carName = carName;
        this.position = position;
    }

    public void increasePosition(String position) {
        if(position.equals("STRAIGHT")) {
            this.position++;
        }
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }
}
