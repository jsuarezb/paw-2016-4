package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.WorksIn;
import ar.edu.itba.paw.models.errors.InvalidDayOfWeekException;
import ar.edu.itba.paw.models.errors.InvalidHourException;

public class AppointmentSlotBuilder implements Builder<AppointmentSlot> {

    private WorksIn worksIn;
    private Integer dayOfWeek;
    private Integer hour;

    public AppointmentSlotBuilder setWorksIn(final WorksIn worksIn) {
        this.worksIn = worksIn;
        return this;
    }

    public AppointmentSlotBuilder setDayOfWeek(final Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public AppointmentSlotBuilder setHour(final Integer hour) {
        this.hour = hour;
        return this;
    }

    @Override
    public AppointmentSlot build() {
        if (hour < 0 || hour > 23)
            throw new InvalidHourException();

        if (dayOfWeek < 0 || dayOfWeek > 6)
            throw new InvalidDayOfWeekException();

        return new AppointmentSlot(dayOfWeek, hour, worksIn);
    }
}
