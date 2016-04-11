### Resources:

Base path: '/resources'

- Appointment
- Patient
- Doctor
- AppointmentSlot

### Paths

#### Patient

- Create 'POST /patients'
- Delete 'DELETE /patients/{id}'
- List 'GET /patients'
- Show 'GET /patients/{id}'

    ##### Appointment #####
    Base Path: '/patients/{id}'

    - Create 'POST /appointments'
    - Delete 'DELETE /appointments/{id}'
    - List 'GET /appointments'
    - Show 'GET /appointments/{id}


#### Doctor

- Create 'POST /doctors'
- Delete 'DELETE /doctors/{id}
- List 'GET /doctors'
- Show 'GET /doctors/{id}'

    ##### AppointmentSlot #####
    Base Path: '/doctors/{id}'

    - Create 'POST /appointment_slots'
    - Delete 'DELETE /appointment_slots/{id}'
    - List 'GET /appointment_slots'

### Institution

- List 'GET /institution'
- Show 'GET /institution/{id}'

    ##### Doctor #####

    - Create 'POST /doctors'
    - Delete 'DELETE /doctors'
    - List 'GET /doctors'
    - Show 'GET /doctors/{id}'

        #### AppointmentSlot ####

        - Create 'POST /appointment_slots'
        - Delete 'DELETE /appointment_slots/{id}'
        - List 'GET /appointment_slots'
