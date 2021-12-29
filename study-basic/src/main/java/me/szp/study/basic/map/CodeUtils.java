package me.szp.study.basic.map;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 孙志鹏
 * @since 2021/12/28 9:03 AM
 */
public class CodeUtils {

    private static final String[] SPRING_ANNOTATION = new String[]{"@SpringBootApplication", "@Configuration", "@Component", "@Service", "@Controller", "@RestController", "@Repository"};

    public static void main(String[] args) {

        String filePath = "/Users/szp/IdeaProjects/hyzs/south-net-platform-api";

        List<File> fileList = new ArrayList<>();

        getFileList(filePath, fileList);

        List<ClassObject> classObjects = fileList.stream().map(CodeUtils::parse).collect(Collectors.toList());

        Set<String> referenceClassSet = new HashSet<>();

        classObjects.stream().map(ClassObject::getReferences).forEach(referenceClassSet::addAll);


        classObjects
                .stream()
                .filter(f -> f.getClassName() != null && !f.isIoc() && !f.getFile().getAbsolutePath().contains("/src/test"))
                .forEach(f -> {
                    if (referenceClassSet.contains(f.getClassName())) {
                        return;
                    }
                    boolean contains = classObjects.stream().filter(e -> !e.equals(f)).anyMatch(e -> e.getContent().contains(f.getFile().getName().replace(".java", "")));

                    if (contains) {
                        return;
                    }

                    System.out.printf("未使用的类:%s,文件:%s%n", f.getClassName(), f.getFile().getAbsolutePath());

                    try {
                        copy(f.getFile(), new File(new File(filePath + "/backup"), f.getClassName().replace(".", "/") + ".java"));
                        f.getFile().deleteOnExit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

    }


    private static void copy(File src, File target) throws IOException {
        if (!target.getParentFile().exists()) {
            boolean result = target.getParentFile().mkdirs();
            if (!result) {
                throw new IOException("目录创建失败");
            }
        }
        try (
                InputStream in = new FileInputStream(src);

                OutputStream out = new FileOutputStream(target)) {
            byte[] temp = new byte[1024];

            int flag;

            while ((flag = in.read(temp)) > 0) {
                out.write(temp, 0, flag);
            }
        }
    }

    public static ClassObject parse(File file) {
        Set<String> referenceSet = new HashSet<>();
        String str;
        String className = null;
        String packageName = null;
        boolean ioc = false;

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((str = reader.readLine()) != null) {
                if (str.startsWith("package")) {
                    packageName = str.split("package")[1].replace(";", "").trim();
                    className = packageName + "." + file.getName().replace(".java", "");
                }
                if (str.startsWith("import")) {
                    referenceSet.add(str.split("import")[1].replace(";", "").trim());
                }
                if (!ioc) {
                    ioc = Stream.of(SPRING_ANNOTATION).anyMatch(str::contains);
                }
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClassObject(file, className, packageName, ioc, referenceSet, sb.toString());
    }

    public static void getFileList(String strPath, List<File> fileList) {
        Optional.ofNullable(new File(strPath).listFiles())
                .ifPresent(files -> {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".java")) {
                            fileList.add(file);
                        } else {
                            getFileList(file.getAbsolutePath(), fileList);
                        }
                    }
                });
    }

}
