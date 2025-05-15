import java.util.Scanner;

public class App {
    static InsulinData[] insulinDatas;

    public static void main(String[] args) throws Exception {

        // Current glucose level
        double currentGlucoseLevel;
        // How many points of sugar are lowered by one unit of insulin
        double insulinSensitivityFactor = 50;
        // Target (mg/dL)
        double targetGlucoseLevel = 110;
        int currentDoseNumber = 0;
        Scanner in = new Scanner(System.in);
        printHello();
        System.out.println("🩺 Welcome to the Insulin Dose Calculator!");

        int numOfDose = in.nextInt();
        insulinDatas = new InsulinData[numOfDose];
        while (numOfDose > currentDoseNumber) {
            System.out.println("\nPlease choose an operation:");
            System.out.println("1 - Calculate insulin dose");
            System.out.println("2 - Show dose history");
            System.out.print("Enter your choice: ");

            int choose = in.nextInt();

            switch (choose) {
                case 1:
                    System.out.print("Enter current glucose level (mg/dL): ");

                    currentGlucoseLevel = in.nextInt();
                    double dose = injectInsulin(currentGlucoseLevel, targetGlucoseLevel, insulinSensitivityFactor);
                    insulinDatas[currentDoseNumber] = new InsulinData();
                    insulinDatas[currentDoseNumber].currentGlucoseLevel = currentGlucoseLevel;
                    insulinDatas[currentDoseNumber].targetGlucoseLevel = targetGlucoseLevel;
                    insulinDatas[currentDoseNumber].dose = dose;

                    currentDoseNumber++;
                    break;
                case 2:
                    printhistory(insulinDatas);
                    break;
                default:
                    System.out.println("Incorrect choice. Please try again.");
                    break;
            }

        }
        System.out.println("""
                you finshed number of does to show the hestory of does press enter
                """);
        in.nextLine();
        printhistory(insulinDatas);
        System.out.println("""
                thank you to use insulion pump rest the program to start again
                """);

        in.close();
    }

    static double calculateInsulinDose(double currentGlucoseLevel, double targetGlucoseLevel,
            double insulinSensitivityFactor) {

        double excess = currentGlucoseLevel - targetGlucoseLevel;
        if (excess <= 0) {
            return 0; // لا حاجة للأنسولين
        }
        return excess / insulinSensitivityFactor;
    }

    static double injectInsulin(double currentGlucoseLevel, double targetGlucoseLevel,
            double insulinSensitivityFactor) {
        double dose = calculateInsulinDose(currentGlucoseLevel, targetGlucoseLevel, insulinSensitivityFactor);
        if (dose > 0) {
            System.out.printf("✅ Injected %.2f units of insulin%n", dose);

        } else {
            System.out.println("🟢 No need to inject insulin now.");

        }
        return dose;
    }

    static void printhistory(InsulinData[] insulinData) {
        if (insulinData[0] == null) {
            System.out.println("📭 No doses recorded yet.");
            return;
        }
        for (int i = 0; i < insulinData.length; i++) {
            if (insulinData[i] != null) {
                System.out.println("💉 Dose #" + (i + 1) + ":");
                System.out.println("🩸 Current Glucose: " + insulinData[i].currentGlucoseLevel + " mg/dL");
                System.out.println("🎯 Target Glucose: " + insulinData[i].targetGlucoseLevel + " mg/dL");
                System.out.println("💊 Insulin Dose: " + insulinData[i].dose + " units\n");
            }
        }
    }

    static void printHello() {

        System.out.println(
                """

                                                                                               ▄███
                                                                                             ▄███└
                                                                                       ╓▄▄▄▀██▀└
                                                                                  ▄▄▀▄███▀███      ▄█▄
                                                                            ▄██████▀▀███┌─▄██     ▄███▌
                                                                          ▄██▀▀▀███   ▀████▀     ▄██▀██▌
                                                                       ╓▓██▀     ▀███▄  ▀██      ██▌░║██
                                                                     ▄███▀██▄      ▀███▄██▀      ▀█████▀
                                                                   ▄███▀   ▀██▌      ╙███           └
                                                                 ▄████▄      ▀▀▀      ▄██
                                                               ▄██▀  ▀██▄              ▀
                                                            ╓▓███      └         ╓▄██
                                                          ▄███▀███▄            ╓▓██▀
                                                        ▄███▀    ▀██▄        ▄███▀
                                                      ▄█████▄      ▀▀      ▄███└     ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                                                    ▄██▀  ╙███           ▄██▀└     ▄██▀▀▀▀▀▀▀▀▀▀▀▀▀███▌
                                                 ╓▓███▄               ╓▄██▀        ██▌             ░║██
                                            ╓╓ ▄███▀▀███▄           ╓███▀          ███╔░░░░░░░░░░░░░▓██
                                         ╓▓██████▀    ╙███▄       ▄███▀             ▀█████████████████
                                         ██▌ ╙▀███▄     └▀      ▄███└                  ██
                                         ▀███▄░╙▀███▄         ▄██▀└                   ███         ▓█
                                           ▐███▄ ░▀███▄    ╓▄██▀                   ╓███▀          ▀███▄
                                          ▄██▀███▄ ░╙███▄▄▓██▀                    ▄██▀              ╙██▌
                                  ▄▄█▄   ▀█▀  ░╙▀██▓▄░╙▀███▀                      ██▌▄▄▄▄▄▄▄▄        ▐██
                                ▄███▀██▄▄     ╓╔▄█████▄░▄███                      ████████████─       ██
                                ███▄░╙▀███▄ ╔▄▓██▀  ▀█████▀                       ██▌░░░░░░░██─       ██
                                 ▀███▄░░▀██████▀       ╙                          ██▌░░░░░░░██─       ██
                                   ╙███▄ ░▀███▄                                   ██▌░░░░░░░▀▀        ██
                                      ▀██▄ ░███─                                  ██▌░░░░░░░▄         ██
                                        ▀█████└                                   ████████████─       ██
                                          ╙╙                                      ██▀▀▀▀▀▀▀▀▀         ██
                                                                                  ███▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄██
                                                                                   ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                        """);
    }
}

class InsulinData {

    double currentGlucoseLevel;
    double targetGlucoseLevel;
    double dose;
}
