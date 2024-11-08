package com.university;

public class courseRepository<T extends Entity> implements CRUDRepository<T> {
    int idCounter = 0;
    Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getIdentifier(){
        return "Course";
    }

    public void create(T entity) {
        T.setId(idCounter);
        idCounter++;
        University.subjects.add((Subject) entity);
    }

    public T read(int id) {
        for (Subject subject : University.subjects) {
            if (subject.getId() == id) {
                return (T) subject;
            }
        }
        return null;
    }

    public void update(int id, T entity) {}

    public void delete(int id) {
        University.subjects.remove((Subject) read(id));
    }
}
