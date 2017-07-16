package com.oonurkuru.jpa;

import com.oonurkuru.jpa.examples.Example;
import com.oonurkuru.jpa.utils.AnnotationHandler;
import com.oonurkuru.jpa.utils.ClassFinder;
import com.oonurkuru.jpa.utils.ConnectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Örnekleri çalıştırmak için gerekli fonksiyonları içerir
 */
public class RunExample {

    private final static String PERSISTENCE_UNIT_NAME = "jpa_basics";
    private final static String INPUT_RUN_AGAIN = "RUN";

    public static void main(String args[]) throws UnsupportedEncodingException {

        Example exampleClass = null;
        String runAgain = INPUT_RUN_AGAIN;
        EntityManagerFactory entityManagerFactory = ConnectionUtils.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        final Map<String, Class> classCodeList = AnnotationHandler.createClassMap();

        //INPUT_RUN_AGAIN değeri run olduğu sürece dön
        while (INPUT_RUN_AGAIN.equals(runAgain.toUpperCase())) {

            //çalıştırılmaya uygun bir sınıf bulunana kadar dön
            while (exampleClass == null) {

                String userWantToRun = getUserInput(createCodeList(classCodeList));

                try {
                    exampleClass = (Example) ClassFinder.findClassByName(userWantToRun).newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    //Eğer çalışması istenen sınıf isminden bulunamamışsa ExampleClassCode değerine göre aramayı dene
                    try {
                        exampleClass = (Example) classCodeList.get(userWantToRun).newInstance();
                    } catch (InstantiationException | IllegalAccessException ex) {
                        System.out.println("Bira hata oluştu: " + e);
                    }

                    if (exampleClass == null) {
                        System.out.println("Sınıf ismi bulunamadı tekrar deneyiniz.");
                    }
                }
            }

            runExample(exampleClass, entityManagerFactory);
            runAgain = getUserInput("Başka bir örnek çalıştırmak istiyorsanız RUN, çıkmak istiyorsanız herhangi bir ifade giriniz: ");
            exampleClass = null;
        }

        ConnectionUtils.closeEntityManagerFactory(entityManagerFactory);
    }

    /**
     * Kullanıcının girişlerini okuyan fonksiyon
     *
     * @param message kullanıcıya giriş için gösterilen mesajı içerir
     * @return kullanıcıdan alınan girişi geri döndürür.
     */
    private static String getUserInput(String message) {
        String userInput = null;

        while (userInput == null || "".equals(userInput)) {
            System.out.print(message);
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine().trim();
        }
        return userInput;
    }

    /**
     * Kullanıcının girişi sonucunda uygun bir sınıf bulunursa bu fonksiyon ile çalıştırılır.
     *
     * @param exampleClass         Çalıştırılacak sınıf parametresi
     * @param entityManagerFactory transaction yönetimi için gerekli olan entity manager factory nesnesi
     */
    private static void runExample(Example exampleClass, EntityManagerFactory entityManagerFactory) {

        System.out.println("Örnek Çalıştırılmaya Başlandı.");
        EntityManager entityManager = ConnectionUtils.createEntityManager(entityManagerFactory);
        ConnectionUtils.startTransaction(entityManager);

        exampleClass.runExample(entityManager);

        ConnectionUtils.commitTransaction(entityManager);
        ConnectionUtils.closeEntityManager(entityManager);
        System.out.println("Örnek Çalıştırıldı.");
    }

    /**
     * Sınıf kodlarını ve sınıf isimlerinden bir çıktı üretir.
     *
     * @param classList listelenmesi istenen sınıf ve değeri
     */
    private static String createCodeList(Map<String, Class> classList) {

        StringBuilder stringBuilder = new StringBuilder().append("Sınıf Kodu - Sınıf\n");

        for (Map.Entry<String, Class> entry : classList.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" - ");
            stringBuilder.append(entry.getValue().getSimpleName());
            stringBuilder.append("\n");
        }

        stringBuilder.append("Sınıf kodunu ya da sınıf ismini giriniz :");
        return stringBuilder.toString();
    }

}
