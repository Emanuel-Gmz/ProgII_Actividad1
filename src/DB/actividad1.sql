CREATE SCHEMA actividad1;
USE actividad1;

CREATE TABLE Paciente (
    nroPaciente INT AUTO_INCREMENT PRIMARY KEY,
    telefono INT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Consultorio (
    nroConsultorio INT PRIMARY KEY,
    medico VARCHAR(100) NOT NULL
);

CREATE TABLE Turno (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    dia DATE NOT NULL,
    hora TIME NOT NULL,
    nroConsultorio INT NOT NULL,
    nroPaciente INT NOT NULL,
    
    CONSTRAINT fk_consultorio 
        FOREIGN KEY (nroConsultorio) 
        REFERENCES Consultorio(nroConsultorio)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
        
    CONSTRAINT fk_paciente 
        FOREIGN KEY (nroPaciente) 
        REFERENCES Paciente(nroPaciente)
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);