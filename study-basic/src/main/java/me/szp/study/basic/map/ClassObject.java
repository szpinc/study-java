package me.szp.study.basic.map;

import java.io.File;
import java.util.Set;

/**
 * @author 孙志鹏
 * @since 2021/12/28 10:08 AM
 */
public class ClassObject {

    private File file;

    private String packageName;

    private String className;

    private boolean ioc;

    private Set<String> references;

    private String content;

    public ClassObject() {
    }

    public ClassObject(File file, String packageName, String className, boolean ioc, Set<String> references, String content) {
        this.file = file;
        this.packageName = packageName;
        this.className = className;
        this.ioc = ioc;
        this.references = references;
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isIoc() {
        return ioc;
    }

    public void setIoc(boolean ioc) {
        this.ioc = ioc;
    }

    public Set<String> getReferences() {
        return references;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setReferences(Set<String> references) {
        this.references = references;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
