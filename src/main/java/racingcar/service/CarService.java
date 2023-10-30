package racingcar.service;

import racingcar.domain.Car;
import racingcar.utils.BuilderUtils;
import racingcar.utils.RandomUtils;
import racingcar.validator.CarNameValidator;

import java.util.ArrayList;
import java.util.List;


public class CarService {

    private final List<String> carsName = new ArrayList<>();
    private Car[] car;
    private int carCount;
    private BuilderUtils builderUtils;

    public int initInputCarName(String carName) {

        CarNameValidator carNameValidator = new CarNameValidator();

        for (String word : carName.split(",")) {
            if(carNameValidator.validateInputCarName(word)) {
                carsName.add(word);
            }
        }
        carCount = carsName.size();
        return carCount;
    }

    public void initBuilder() {
        builderUtils = new BuilderUtils(carsName);
    }

    public void initCar() {
        car = new Car[carCount];

        for (int i=0; i<carCount; i++) {
            car[i] = new Car(carsName.get(i),0);
        }
    }

    public void updateCarPosition() {
        for (int carIndex = 0; carIndex < carsName.size() ; carIndex++) {
            String position = RandomUtils.detarminePostionByRandomNumber();
            if(car[carIndex].increasePosition(position)) {
                builderUtils.appendToCarPosition(carIndex);
            };
        }
    }

    public String[] getGameStatus() {
        return builderUtils.getCarPositions();
    }

    public List<String> getWinner() {
        List<String> winner = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < carCount; i++) {
            if (car[i].getPosition() > max) {
                winner.clear();
                winner.add(car[i].getCarName());
                max = car[i].getPosition();
            } else if (car[i].getPosition() == max) {
                winner.add(car[i].getCarName());
            }
        }
        return winner;
    }
}
