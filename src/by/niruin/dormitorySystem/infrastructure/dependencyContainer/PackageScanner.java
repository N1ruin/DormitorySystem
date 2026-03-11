package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class PackageScanner {
    public static final String CLASS_FILE_NAME_POSTFIX = ".class";
    public static final String EMPTY_STRING = "";
    public static final String DOT_SYMBOL = ".";
    public static final String SLASH_SYMBOL = "/";
    private final Logger logger = LoggerFactory.getLogger(PackageScanner.class);

    public Set<Class<?>> scanPackage(String packageName) {
        Set<Class<?>> foundClasses
                = new HashSet<>();
        ClassLoader classLoader = this.getClass().getClassLoader();

        String packagePath = DOT_SYMBOL + SLASH_SYMBOL + packageName.replace(DOT_SYMBOL, SLASH_SYMBOL);
        URL url = classLoader.getResource(packagePath);

        if (url != null) {
            File directory = new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));
            if (directory.exists()) {
                scanDirectory(directory, packageName, foundClasses
                        , classLoader);
            }
        }

        return foundClasses
                ;
    }

    private void scanDirectory(File directory, String packageName, Set<Class<?>> foundClasses
            , ClassLoader classLoader) {
        logger.info(START_SCANNING_DIRECTORY_LOG.formatted(directory.getPath()));
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                scanSubdirectory(file, packageName, foundClasses
                        , classLoader);
            } else if (isClassFile(file)) {
                processClassFile(file, packageName, foundClasses
                        , classLoader);
            }
        }
    }

    private void scanSubdirectory(File directory, String packageName, Set<Class<?>> foundClasses
            , ClassLoader classLoader) {
        String subPackageName = packageName + DOT_SYMBOL + directory.getName();
        scanDirectory(directory, subPackageName, foundClasses
                , classLoader);
    }

    private boolean isClassFile(File file) {
        return file.getName().endsWith(CLASS_FILE_NAME_POSTFIX);
    }

    private void processClassFile(File classFile, String packageName, Set<Class<?>> foundClasses
            , ClassLoader classLoader) {
        String className = packageName + DOT_SYMBOL + classFile.getName().replace(CLASS_FILE_NAME_POSTFIX, EMPTY_STRING);
        try {
            Class<?> clazz = classLoader.loadClass(className);
            if (clazz.isAnnotationPresent(Component.class)) {
                foundClasses.add(clazz);
                logger.info(FIND_COMPONENT_CLASS_FILE_LOG.formatted(className));
            }
        } catch (ClassNotFoundException e) {
            logger.warn(CLASS_LOADING_ERROR_LOG.formatted(className));
            throw new RuntimeException(e);
        }
    }
}
