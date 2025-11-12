package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class PackageScanner {

    public Set<Class<?>> scanPackage(String packageName) {
        Set<Class<?>> findedClasses = new HashSet<>();
        ClassLoader classLoader = this.getClass().getClassLoader();

        String packagePath = "./" + packageName.replace(".", "/");
        URL url = classLoader.getResource(packagePath);

        if (url != null) {
            File directory = new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));
            if (directory.exists()) {
                scanDirectory(directory, packageName, findedClasses, classLoader);
            }
        }

        return findedClasses;
    }

    private void scanDirectory(File directory, String packageName, Set<Class<?>> findedClasses, ClassLoader classLoader) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                String subPackageName = packageName + "." + file.getName();
                scanDirectory(file, subPackageName, findedClasses, classLoader);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");

                try {
                    Class<?> clazz = classLoader.loadClass(className);
                    if (clazz.isAnnotationPresent(Component.class)) {
                        findedClasses.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
