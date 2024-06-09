-- ESPECIALISTAS

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion) 
VALUES (nextval('especialista_seq'), 'Dra. Ana Lopez', 'Dermatologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion) 
VALUES (nextval('especialista_seq'), 'Dr. Juan Perez', 'Cardiologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion)
VALUES (nextval('especialista_seq'), 'Dra. Maria Rodriguez', 'Pediatria', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion)
VALUES (nextval('especialista_seq'), 'Dr. Carlos Sanchez', 'Oftalmologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas');

-- TURNOS

INSERT INTO turno (id, fechahoracita, especialista_id, dnipaciente, motivoconsulta, nombrepaciente)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, null, 'Consulta por acne', null);

INSERT INTO turno (id, fechahoracita, especialista_id, dnipaciente, motivoconsulta, nombrepaciente)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, null, 'Consulta por dolor en el pecho', null);

-- RECETAS

-- INSERT INTO receta (id, fechaCreacion, turno_id, descripcion)
-- VALUES (nextval('receta_seq'), '2021-10-01 10:00:00', 1, 'Aplicar crema en la zona afectada 2 veces al dia');

-- PACIENTES

-- INSERT INTO paciente (id, dni, nombre, apellidos, fechanacimiento)
-- VALUES (nextval('paciente_seq'), '12345678', 'Paco', 'Perez', '1990-06-01');

-- INSERT INTO paciente (id, dni, nombre, apellidos, fechanacimiento)
-- VALUES (nextval('paciente_seq'), '87654321', 'Luis', 'Lopez', '1991-01-15');


