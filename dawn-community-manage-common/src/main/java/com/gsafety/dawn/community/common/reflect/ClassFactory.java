package com.gsafety.dawn.community.common.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by nusmg 2017-12-18
 */
public class ClassFactory {
    private static Logger logger = LoggerFactory.getLogger(ClassFactory.class);    //日志记录

    private ClassFactory() {
    }

    /**
     * 从package中获取所有的class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {

        Set<Class<?>> classes = new LinkedHashSet<>();
        boolean recursive = true;
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);

            while (dirs.hasMoreElements()) {

                URL url = dirs.nextElement();

                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {

                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    getClassInPackages(url, packageDirName, packageName, recursive, classes);
                }
            }
        } catch (ClassNotFoundException clsEx) {
            logger.error("get class not found error:", clsEx);
        } catch (IOException e) {
            logger.error("IOException - get class error:", e);
        }


        return classes;
    }

    /**
     * 从文件路径中获取所有class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory())
                || (file.getName().endsWith(".class")));
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    logger.error("findAndAddClassesInPackageByFile classes add error:", e);
                }
            }
        }
    }

    private static void getClassInPackages(URL url, String packageDirName, String packageName, boolean recursive, Set<Class<?>> classes) throws IOException, ClassNotFoundException {
        JarFile jar;
        String varPackageName = packageName;

        jar = ((JarURLConnection) url.openConnection())
                .getJarFile();

        Enumeration<JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();

            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                if (idx != -1) {
                    varPackageName = name.substring(0, idx)
                            .replace('/', '.');
                }
                if ((idx != -1 || recursive) && name.endsWith(".class")
                        && !entry.isDirectory()) {
                    String className = name.substring(
                            varPackageName.length() + 1, name
                                    .length() - 6);
                    classes.add(Class
                            .forName(varPackageName + '.'
                                    + className));
                }
            }
        }
    }
}
