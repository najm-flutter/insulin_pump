import java.util.Scanner;

public class App {
    static InsulinData[] insulinDatas;

    public static void main(String[] args) throws Exception {

        // Current glucose level
        // مستوى الجلوكوز الحالي
        double currentGlucoseLevel;
        // How many points of sugar are lowered by one unit of insulin
        // عدد النقاط التي يتم خفضها من السكر بواسطة وحدة واحدة من الأنسولين
        double insulinSensitivityFactor = 50;
        // Target (mg/dL)
        // الهدف (ملغ/ديسيلتر)
        double targetGlucoseLevel = 110;
        int currentDoseNumber = 0; // عدد الجرعات الحالية
        Scanner in = new Scanner(System.in);
        printHello(); // طباعة رسالة ترحيبية
        System.out.println("🩺 Welcome to the Insulin Dose Calculator!");
        // 🩺 مرحبًا بك في حاسبة جرعات الأنسولين!

        int numOfDose = in.nextInt(); // إدخال عدد الجرعات
        insulinDatas = new InsulinData[numOfDose]; // إنشاء مصفوفة لتخزين بيانات الجرعات
        while (numOfDose > currentDoseNumber) { // التكرار حتى يتم إدخال جميع الجرعات
            System.out.println("\nPlease choose an operation:");
            // الرجاء اختيار عملية:
            System.out.println("1 - Calculate insulin dose");
            // 1 - حساب جرعة الأنسولين
            System.out.println("2 - Show dose history");
            // 2 - عرض سجل الجرعات
            System.out.print("Enter your choice: ");
            // أدخل اختيارك:

            int choose = in.nextInt(); // إدخال الخيار

            switch (choose) {
                case 1:
                    System.out.print("Enter current glucose level (mg/dL): ");
                    // أدخل مستوى الجلوكوز الحالي (ملغ/ديسيلتر):

                    currentGlucoseLevel = in.nextInt(); // إدخال مستوى الجلوكوز الحالي
                    double dose = injectInsulin(currentGlucoseLevel, targetGlucoseLevel, insulinSensitivityFactor);
                    // حساب الجرعة وحقن الأنسولين
                    insulinDatas[currentDoseNumber] = new InsulinData(); // إنشاء كائن جديد لتخزين بيانات الجرعة
                    insulinDatas[currentDoseNumber].currentGlucoseLevel = currentGlucoseLevel; // تخزين مستوى الجلوكوز الحالي
                    insulinDatas[currentDoseNumber].targetGlucoseLevel = targetGlucoseLevel; // تخزين الهدف
                    insulinDatas[currentDoseNumber].dose = dose; // تخزين الجرعة

                    currentDoseNumber++; // زيادة عدد الجرعات الحالية
                    break;
                case 2:
                    printhistory(insulinDatas); // عرض سجل الجرعات
                    break;
                default:
                    System.out.println("Incorrect choice. Please try again.");
                    // اختيار غير صحيح. الرجاء المحاولة مرة أخرى.
                    break;
            }

        }
        System.out.println("""
                you finshed number of does to show the hestory of does press enter
                """);
        // لقد انتهيت من إدخال عدد الجرعات. لعرض السجل، اضغط على Enter.
        
        printhistory(insulinDatas); // عرض سجل الجرعات
        System.out.println("""
                thank you to use insulion pump rest the program to start again
                """);
        // شكرًا لاستخدام مضخة الأنسولين. أعد تشغيل البرنامج للبدء من جديد.

        in.close(); // إغلاق المدخل
    }

    static double calculateInsulinDose(double currentGlucoseLevel, double targetGlucoseLevel,
            double insulinSensitivityFactor) {

        double excess = currentGlucoseLevel - targetGlucoseLevel; // حساب الفرق بين المستوى الحالي والهدف
        if (excess <= 0) {
            return 0; // لا حاجة للأنسولين
        }
        return excess / insulinSensitivityFactor; // حساب الجرعة المطلوبة
    }

    static double injectInsulin(double currentGlucoseLevel, double targetGlucoseLevel,
            double insulinSensitivityFactor) {
        double dose = calculateInsulinDose(currentGlucoseLevel, targetGlucoseLevel, insulinSensitivityFactor);
        // حساب الجرعة المطلوبة
        if (dose > 0) {
            System.out.printf("✅ Injected %.2f units of insulin%n", dose);
            // ✅ تم حقن %.2f وحدة من الأنسولين

        } else {
            System.out.println("🟢 No need to inject insulin now.");
            // 🟢 لا حاجة لحقن الأنسولين الآن.

        }
        return dose; // إرجاع الجرعة
    }

    static void printhistory(InsulinData[] insulinData) {
        if (insulinData[0] == null) {
            System.out.println("📭 No doses recorded yet.");
            // 📭 لم يتم تسجيل أي جرعات بعد.
            return;
        }
        for (int i = 0; i < insulinData.length; i++) {
            if (insulinData[i] != null) {
                System.out.println("💉 Dose #" + (i + 1) + ":");
                // 💉 الجرعة رقم #
                System.out.println("🩸 Current Glucose: " + insulinData[i].currentGlucoseLevel + " mg/dL");
                // 🩸 الجلوكوز الحالي:
                System.out.println("🎯 Target Glucose: " + insulinData[i].targetGlucoseLevel + " mg/dL");
                // 🎯 الجلوكوز المستهدف:
                System.out.println("💊 Insulin Dose: " + insulinData[i].dose + " units\n");
                // 💊 جرعة الأنسولين:
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

    double currentGlucoseLevel; // مستوى الجلوكوز الحالي
    double targetGlucoseLevel; // مستوى الجلوكوز المستهدف
    double dose; // جرعة الأنسولين
}