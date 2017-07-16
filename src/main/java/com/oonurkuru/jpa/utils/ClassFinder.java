package com.oonurkuru.jpa.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static com.oonurkuru.jpa.utils.Constants.CLASS_SUFFIX;
import static com.oonurkuru.jpa.utils.Constants.TARGET_PACKAGE;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Package içerisinde ki sınıfların çeşitli parametrelere göre bulunmasını sağlayan fonksiyonları içerir.
 */
public abstract class ClassFinder {

    /**
     * TARGET_PACKAGE ve parametre olarak verilen className birleştirilerek ilgili sınıfın bulur
     *
     * @param className TARGET_PACKAGE altında bulunması istenen sınıfı belirtir.
     * @return eğer ilgili sınıf bulunursa geriye Class objesi döndürür.
     * @throws ClassNotFoundException geçerli bir sınıf ismi verilmediği takdir de bu hatayı fırlatır.
     */
    public static Class findClassByName(String className) throws ClassNotFoundException {
        return Class.forName(TARGET_PACKAGE + "." + className);
    }


    /**
     * Bir package içerisindeki sınıfların bulunmasını sağlar. Şu anki çalışan Thread üzerinden ilgili package bulunurak
     * içerisinde CLASS_SUFFIX uzantılı dosyalar bulunur.
     *
     * @param packageName içerisinde ki java sınıflarının bulunması istenilen package ismi.
     * @return bulunan sınıflar List<Class> olarak geri döndürülür.
     */
    public static List<Class> findClassesByPackage(String packageName) throws UnsupportedEncodingException {

        List<Class> classList = new ArrayList<>();
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        Assert.notNull(scannedUrl, "Package not found.");

        String decodedUrl = URLDecoder.decode(scannedUrl.getFile(), java.nio.charset.StandardCharsets.UTF_8.toString());
        File scannedDir = new File(decodedUrl);

        for (File file : scannedDir.listFiles()) {
            if (!file.isDirectory() && file.getName().endsWith(CLASS_SUFFIX)) {
                String className = file.getName().replace(CLASS_SUFFIX, "");

                try {
                    classList.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    System.out.print(className + " not found.");
                }
            }
        }

        return classList;
    }


    /**
     * Tanımlı package içerisinde ki sınıfları notasyona göre bulan fonksiyon.
     *
     * @param annotationClass Sınıf ilgili notasyonu içeriyorsa geri döndürülmek üzere listeye eklenir.
     * @return bulunan sınıflar List<Class> olarak geri döndürülür.
     */
    public static List<Class> findClassesByAnnotation(Class<? extends Annotation> annotationClass) throws UnsupportedEncodingException {
        List<Class> classList = ClassFinder.findClassesByPackage(Constants.TARGET_PACKAGE);
        List<Class> annotedClassList = new ArrayList<>();

        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(annotationClass)) {
                annotedClassList.add(clazz);
            }
        }

        return annotedClassList;
    }

}
