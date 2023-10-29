package racingcar.controller;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.utils.RandomUtils;
import racingcar.validator.CarNameValidator;
import racingcar.validator.RoundCountValidator;

import java.util.ArrayList;
import java.util.List;


public class GameController {

    private Game game;
    private Car[] car;
    private List<String> carsName = new ArrayList<>();


    public void initGame() {
        initInputCarName();
        initGameRound();
        initCar();
        gameStart();
    }



    public void initInputCarName() {
        String carName = inputCarName();
        CarNameValidator carNameValidator = new CarNameValidator();

        for (String word : carName.split(",")) {
            if(carNameValidator.validateInputCarName(word)) {
                carsName.add(word);
            }
        }
    }

    public String inputCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Console.readLine();
    }

    public void initGameRound() {
        RoundCountValidator roundCountValidator = new RoundCountValidator();
        String inputRound = inputGameRound();
        if(roundCountValidator.validateInputGameRound(inputRound)) {
            int round = Integer.parseInt(inputRound);
            game = new Game(carsName.size(), round);
        }
    }

    public String inputGameRound() {
        System.out.println("게임 횟수 입력: ");
        return Console.readLine();
    }

    public void initCar() {
        int carsCount = game.getCarsCount();
        car = new Car[carsCount];

        for (int i=0; i<carsCount; i++) {
            car[i] = new Car(carsName.get(i),0);
        }
    }

    public void gameStart() {
        int roundCount = game.getRoundCount();
        int carCount = carsName.size();
        
        for (int roundIndex = 0; roundIndex <roundCount ; roundIndex++) {
            updateCarPosition(carCount);
        }
    }

    public void updateCarPosition(int carCount) {
        for (int carIndex = 0; carIndex < carCount ; carIndex++) {
            String position = RandomUtils.detarminPostionByRandomNumber();
            car[carIndex].increasePosition(position);
        }
    }


}
