-- ESPECIALISTAS

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion, fechacreacion) 
VALUES (nextval('especialista_seq'), 'Dra. Ana Lopez', 'Dermatologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas', '2021-10-01 10:00:00');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion, fechacreacion) 
VALUES (nextval('especialista_seq'), 'Dr. Juan Perez', 'Cardiologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas', '2021-10-01 10:00:00');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion, fechacreacion)
VALUES (nextval('especialista_seq'), 'Dra. Maria Rodriguez', 'Pediatria', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas', '2021-10-01 10:00:00');

INSERT INTO especialista (id, nombre, especialidad, horariosConsulta, ubicacion, fechacreacion)
VALUES (nextval('especialista_seq'), 'Dr. Carlos Sanchez', 'Oftalmologia', 'Lunes a Viernes de 8:00 a 12:00', 'Hospital de Clinicas', '2021-10-01 10:00:00');

-- PACIENTE

INSERT INTO paciente (id, dni, nombre, numerocelular, email, password)
VALUES (nextval('paciente_seq'), '12345678', 'Paco', '099123456', 'prueba@gmail.com', '123');

-- TURNOS

INSERT INTO turno (id, fechahoracita, especialista_id, paciente_id, motivoconsulta, solicitado)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, 1, 'Consulta por acne', true);

INSERT INTO turno (id, fechahoracita, especialista_id, paciente_id, motivoconsulta, solicitado)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, 1, 'Consulta por dolor en el pecho', true);

INSERT INTO turno (id, fechahoracita, especialista_id, paciente_id, motivoconsulta, solicitado)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, null, '', false);

INSERT INTO turno (id, fechahoracita, especialista_id, paciente_id, motivoconsulta, solicitado)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, null, '', false);

-- RECETAS

INSERT INTO receta (id, fechaCreacion, turno_id, descripcion)
VALUES (nextval('receta_seq'), '2021-10-01 10:00:00', 1, 'Aplicar crema en la zona afectada 2 veces al dia');

-- Agrega la receta al turno 1

UPDATE turno SET receta_id = 1 WHERE id = 1;


