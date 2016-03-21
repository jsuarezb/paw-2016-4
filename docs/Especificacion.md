# Sistema de administración de turnos médicos #

## Resumen ##

El proyecto consiste en un sistema web para administrar turnos de pacientes de una institución médica.

## Actores ##

- Pacientes
- Médicos

## Otras entidades ##

- Turnos
- Horarios de los médicos

## Acciones relevantes ##

### Pacientes ###

- Ver turnos libres
- Marcar cuando un médico le cancela un turno sin previo aviso
- Reservar un turno
- Cancelar un turno
- Ver historial de turnos
- Modificar un turno

### Médicos ###

- Ver horarios y turnos reservados (incluye ver nombres de pacientes)
- Ver historial de turnos por paciente
- Modificar horarios
- Cancelar turnos
- Marcar que un paciente no fue a un turno

### General ###

- Mails de notificación a pacientes ante cancelación de turnos


## Condiciones relevantes ##

- 1 turno por fecha-horario-medico
- Los turnos tienen duración fija
