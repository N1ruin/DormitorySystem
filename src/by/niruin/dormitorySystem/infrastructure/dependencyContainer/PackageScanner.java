package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.niruin.dormitorySystem.constant.LoggerMessage.FIND_COMPONENT_CLASS_FILE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.START_SCANNING_DIRECTORY_LOG;

public class PackageScanner {
    private final Logger logger = LoggerFactory.getLogger(PackageScanner.class);

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
    //todo декомпозировать
    private void scanDirectory(File directory, String packageName, Set<Class<?>> findedClasses, ClassLoader classLoader) {
        logger.info(START_SCANNING_DIRECTORY_LOG.formatted(directory.getPath()));
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
                        logger.info(FIND_COMPONENT_CLASS_FILE_LOG.formatted(className));
                    }
                } catch (ClassNotFoundException e) {
                    logger.warn(e.getMessage());
                    logger.warn(Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }
}
