import java.util.Scanner;

class InsulinData {

    double currentLevel; // مستوى الجلوكوز الحالي
    double NATURAL_LEVEL; // مستوى الجلوكوز المستهدف
    double insulinDose; // جرعة الأنسولين
}

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Current glucose level
        // مستوى الجلوكوز الحالي
        double currentLevel;
        // How many points of sugar are lowered by one unit of insulin
        // عدد النقاط التي يتم خفضها من السكر بواسطة وحدة واحدة من الأنسولين
        final double UNIT_INSULIN = 50;
        // Target (mg/dL)
        // الهدف (ملغ/ديسيلتر)
        final double NATURAL_LEVEL = 110;

        printWelcomeMessage(); // طباعة رسالة ترحيبية

        System.out.println("🩺 Welcome to the Insulin Dose Calculator! \n Enter the number of doses for this day:");
        // 🩺 مرحبًا بك في حاسبة جرعات الأنسولين!
        int i = 0;
        int doseCount = scanner.nextInt(); // إدخال عدد الجرعات
        InsulinData[] doesArray = new InsulinData[doseCount]; // إنشاء مصفوفة لتخزين بيانات الجرعات
        while (doseCount > i) {
            System.out.println("\nPlease choose an operation:");
            // الرجاء اختيار عملية:
            System.out.println("1 - Calculate insulin dose");
            // 1 - حساب جرعة الأنسولين
            System.out.println("2 - Show dose history");
            // 2 - عرض سجل الجرعات
            System.out.print("Enter your choice: ");
            // أدخل اختيارك:

            int choice = scanner.nextInt(); // إدخال الخيار

            switch (choice) {
                case 1:
                    System.out.print("Enter current glucose level (mg/dL): ");
                    // أدخل مستوى الجلوكوز الحالي (ملغ/ديسيلتر):

                    currentLevel = scanner.nextDouble(); // إدخال مستوى الجلوكوز الحالي
                    double calculatedDose = calculateDose(currentLevel, NATURAL_LEVEL, UNIT_INSULIN);
                    injectDose(calculatedDose);

                    // حساب الجرعة وحقن الأنسولين
                    doesArray[i] = new InsulinData(); // إنشاء كائن جديد لتخزين بيانات الجرعة
                    doesArray[i].currentLevel = currentLevel; // تخزين مستوى الجلوكوز الحالي
                    doesArray[i].NATURAL_LEVEL = NATURAL_LEVEL; // تخزين الهدف
                    doesArray[i].insulinDose = calculatedDose; // تخزين الجرعة
                    i++;
                    break;
                case 2:
                    showHistory(doesArray); // عرض سجل الجرعات
                    break;
                default:
                    System.out.println("Incorrect choice. Please try again.");
                    // اختيار غير صحيح. الرجاء المحاولة مرة أخرى.
                    break;
            }
        }
        System.out.println("""
                You finished entering the number of doses.
                """);
        // لقد انتهيت من إدخال عدد الجرعات.

        showHistory(doesArray); // عرض سجل الجرعات
        System.out.println("""
                Thank you for using the insulin pump. Restart the program to begin again.
                """);
        // شكرًا لاستخدام مضخة الأنسولين. أعد تشغيل البرنامج للبدء من جديد.

        scanner.close(); // إغلاق المدخل
    }

    static double calculateDose(double currentLevel, double NATURAL_LEVEL, double UNIT_INSULIN) {

        double excess = currentLevel - NATURAL_LEVEL; // حساب الفرق بين المستوى الحالي والهدف
        if (excess <= 0) {
            return 0; // لا حاجة للأنسولين
        } else {
            return excess / UNIT_INSULIN; // حساب الجرعة المطلوبة

        }
    }

    static void injectDose(double dose) {

        // حساب الجرعة المطلوبة
        if (dose > 0) {
            System.out.println("✅ Injected " + dose + " units of insulin");
            // ✅ تم حقن %.2f وحدة من الأنسولين

        } else {
            System.out.println("🟢 No need to inject insulin now.");
            // 🟢 لا حاجة لحقن الأنسولين الآن.

        }

    }

    static void showHistory(InsulinData[] doesArray) {
        if (doesArray[0] == null) {
            System.out.println("📭 No doses recorded yet.");
            // 📭 لم يتم تسجيل أي جرعات بعد.

        } else {
            for (int i = 0; i < doesArray.length; i++) {
                if (doesArray[i] != null) {
                    System.out.println("💉 Dose #" + (i + 1) + ":");
                    // 💉 الجرعة رقم #
                    System.out.println("🩸 Current Glucose: " + doesArray[i].currentLevel + " mg/dL");
                    // 🩸 الجلوكوز الحالي:
                    System.out.println("🎯 Natural Glucose Level: " + doesArray[i].NATURAL_LEVEL + " mg/dL");
                    // 🎯 الجلوكوز المستهدف:
                    System.out.println("💊 Insulin Dose: " + doesArray[i].insulinDose + " units\n");
                    // 💊 جرعة الأنسولين:
                } else {
                    break;
                }
            }
        }

    }

    static void printWelcomeMessage() {

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
        // طباعة فن ASCII للترحيب بالمستخدم
    }
}