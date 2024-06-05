package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DoctorEntity save(DoctorEntity entity) {
        // Empty implementation
        return null;
    }

    @Override
    public DoctorEntity getOne(Long id) {
        return null;
    }

    @Override
    public DoctorEntity findOne(Long id) {
        return null;
    }

    @Override
    public List<DoctorEntity> findAll() {
        return List.of();
    }

    @Override
    public DoctorEntity update(DoctorEntity entity) {
        return null;
    }

    @Override
    public void delete(DoctorEntity entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) {
        Query query = entityManager.createQuery("SELECT d FROM DoctorEntity d WHERE d.specialization = :specialization");
        query.setParameter("specialization", specialization);
        return query.getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) {
        Query query = entityManager.createQuery("SELECT COUNT(v) FROM VisitEntity v WHERE v.doctor.firstName = :docFirstName AND v.doctor.lastName = :docLastName AND v.patient.firstName = :patientFirstName AND v.patient.lastName = :patientLastName");
        query.setParameter("docFirstName", docFirstName);
        query.setParameter("docLastName", docLastName);
        query.setParameter("patientFirstName", patientFirstName);
        query.setParameter("patientLastName", patientLastName);
        return (long) query.getSingleResult();
    }
}
