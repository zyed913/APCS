package SemesterProject.src;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;

public class DataStore {
    private final String fileName;
    private final Gson gson = new Gson();

    public DataStore(String fileName) {
        this.fileName = fileName;
    }

    public void save(Student[] students) {
        try (FileWriter w = new FileWriter(fileName)) {
            gson.toJson(students, w);
        } catch (Exception e) {
            // keep basic
        }
    }

    public Student[] load() {
        try (FileReader r = new FileReader(fileName)) {
            Student[] arr = gson.fromJson(r, Student[].class);
            if (arr == null) return new Student[100];
            return arr;
        } catch (Exception e) {
            return new Student[100]; // start empty
        }
    }
}

