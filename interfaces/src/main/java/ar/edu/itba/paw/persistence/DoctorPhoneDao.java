package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.DoctorPhone;

import java.util.List;

public interface DoctorPhoneDao {

    List<DoctorPhone> getByDoctorId(final int id);

}
