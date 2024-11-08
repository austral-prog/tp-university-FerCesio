package com.university;

public class examRepository<T extends Entity> implements CRUDRepository<T> {
    int idCounter = 0;
    Class<T> entityClass;


    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getIdentifier(){
        return "Exam";
    }

    public void create(T entity) {
        T.setId(idCounter);
        idCounter++;
        for (Subject subject : University.subjects){

        }
    }

    public T read(int id) {
        for (Student student : University.students) {
            if (student.getId() == id) {
                return (T) student;
            }
        }
        return null;
    }

    public void update(int id, T entity) {
        University.students.remove(read(id));

    }

    @Override
    public void delete(int id) {
        University.students.remove((Student) read(id));
    }
}
