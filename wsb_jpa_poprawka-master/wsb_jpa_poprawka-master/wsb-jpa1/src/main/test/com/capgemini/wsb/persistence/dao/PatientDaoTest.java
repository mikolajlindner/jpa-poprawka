package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitsDao visitsDao;

    @Test
    @Transactional
    public void shouldRemoveVisitsWhenRemovingPatients() {
        // given
        final PatientEntity patient = patientDao.findOne(1L);
        final Collection<VisitEntity> visits = patient.getVisits();

        // when
        patientDao.delete(patient);

        // then
        assertThat(visits).isEmpty();
    }

    @Test
    @Transactional
    public void shouldFindPatientsByDoctor() {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findByDoctor("Jan", "Cygwin");

        // then
        assertThat(patients.size()).isEqualTo(2L);
        assertThat(patients.stream().map(x -> x.getFirstName() + " " + x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Benek Bobo", "Zbigniew Kowalski");
    }

    @Test
    @Transactional
    public void shouldFindPatientsHavingTreatmentType() {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsHavingTreatmentType(TreatmentType.EKG);

        // then
        assertThat(patients.size()).isEqualTo(3L);
        assertThat(patients.stream().map(x -> x.getFirstName() + " " + x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Benek Bobo", "Zbigniew Kowalski", "Kajetan Beton");
    }

    @Test
    @Transactional
    public void shouldFindSharingSameAddressWithDoc() {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsSharingSameLocationWithDoc("Jan", "Beton");

        // then
        assertThat(patients.size()).isEqualTo(2L);
        assertThat(patients.stream().map(x -> x.getFirstName() + " " + x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Krzysio Nowak", "Kajetan Beton");
    }

    @Test
    @Transactional
    public void shouldFindPatientsWithoutAnyAddress() {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsWithoutLocation();

        // then
        assertThat(patients).isEmpty();
    }
}