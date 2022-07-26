package com.saikat.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.saikat.todoister.model.Task;
import com.saikat.todoister.util.TaskRoomDatabase;

import java.util.List;

public class DoisterRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> alltasks;

    public DoisterRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        alltasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return alltasks;
    }

    public void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public LiveData<Task> get(long id) {
        return taskDao.get(id);
    }

    public void update(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() ->
                taskDao.update(task)
        );
    }

    public void delete(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()-> taskDao.delete(task));
    }
}
