package GitHubProjects;

import java.util.Scanner;

public class BaseJumper {
    public static void main(String[] args) {
        BaseJumperController baseJumperController = new BaseJumperController();
        baseJumperController.runBaseJumper();
    }
}

class BaseJumperController{
    private final BaseJumperView baseJumperView;
    private final BaseJumperModel baseJumperModel;

    public BaseJumperController(){
        baseJumperModel = new BaseJumperModel();
        baseJumperView = new BaseJumperView();
    }

    public void runBaseJumper(){
        baseJumperView.announceMessage(BaseJumperView.WELCOME_MESSAGE);
        baseJumperView.announceMessage(BaseJumperView.ASKFORINPUT_MESSAGE);

        String input = baseJumperView.getUserInput();
        String[] numbers = baseJumperModel.processInput(input);
        String convertedNumber = baseJumperModel.conversionOfInput(numbers);

        baseJumperView.announceMessage("Your number converted number is: "+ convertedNumber);

        baseJumperView.announceMessage(BaseJumperView.GOODBYE_MESSAGE);




    }

}

class BaseJumperView{
    public final static String WELCOME_MESSAGE = "Welcome to the program. Please select the number, the given base and the desired conversion";
    public final static String GOODBYE_MESSAGE = "Quitting...Bye";
    public final static String ASKFORINPUT_MESSAGE = "Please write your numbers";
    Scanner scanner = new Scanner(System.in);

    public void announceMessage(String message){
        System.out.println(message);
    }

    public String getUserInput(){
        return scanner.nextLine();

    }
}

class BaseJumperModel{

    public String[] processInput(String input){

        return input.split(",");

    }

    public String conversionOfInput(String[] input){
        int decimal = Integer.parseInt(input[0],Integer.parseInt(input[1]) );

        return Integer.toString(decimal,Integer.parseInt(input[2]));
    }
}
