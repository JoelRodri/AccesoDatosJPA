CREATE TABLE escuderia(escuderia varchar(20) primary key);

CREATE TABLE piloto(numero varchar(5) primary key GENERATED ALWAYS AS IDENTITY,
                    nombre varchar(30),
                    escuderia varchar (20) references escuderia(escuderia),
                    pais varchar (20),
                    podiums varchar (10),
                    puntosTotales varchar (10),
                    gpCompletados varchar (5),
                    titulosMundiales varchar (5),
                    mejorPos varchar (10),
                    mejorClas varchar (5),
                    fechaNacimiento varchar (20),
                    nacionalidad varchar (30));