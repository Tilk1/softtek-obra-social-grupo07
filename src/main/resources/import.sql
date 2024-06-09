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

INSERT INTO paciente (id, dni, nombre, numerocelular)
VALUES (nextval('paciente_seq'), '12345678', 'Paco', '099123456');

-- TURNOS

INSERT INTO turno (id, fechahoracita, especialista_id, dnipaciente, motivoconsulta, nombrepaciente)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, '12345678', 'Consulta por acne', 'Paco');

INSERT INTO turno (id, fechahoracita, especialista_id, dnipaciente, motivoconsulta, nombrepaciente)
VALUES (nextval('turno_seq'), '2021-10-01 10:00:00', 1, '12345678', 'Consulta por dolor en el pecho', 'Paco');

-- RECETAS

INSERT INTO receta (id, fechaCreacion, turno_id, descripcion)
VALUES (nextval('receta_seq'), '2021-10-01 10:00:00', 1, 'Aplicar crema en la zona afectada 2 veces al dia');


-- USUARIOS

INSERT INTO usuario (id, email, password, dni, nombre)
VALUES (nextval('usuario_seq'), 'prueba@gmail.com', '123', '12345678', 'Paco');

